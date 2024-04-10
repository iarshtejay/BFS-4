// ## Problem 2 Snakes and ladders (https://leetcode.com/problems/snakes-and-ladders/)


// Time: O(N*N)
// Space: O(N*N)
// We have connected components, so we sed graph traversal
// Since we need min number of moves, we used BFS

import java.util.LinkedList;
import java.util.Queue;

class SnakesAndLadders {
    public int snakesAndLadders(int[][] board) {
        //1. Flatten the board - O(N*N)
        int n = board.length;
        int[] flattened = new int[n*n];

        int idx=0;
        boolean dir = false;

        for(int r=n-1;r>=0;r--){
            if(!dir){
                // left to right
                for(int c=0;c<n;c++){
                    if(board[r][c] > 0 ){
                        flattened[idx] = board[r][c] - 1;
                    }else{
                        flattened[idx] = -1;
                    }
                    idx++;
                }
            }else{
                //right to left
                for(int c=n-1;c>=0;c--){
                    if(board[r][c] > 0 ){
                        flattened[idx] = board[r][c] - 1;
                    }else{
                        flattened[idx] = -1;
                    }
                    idx++;
                }
            }

            //reverse direction
            dir = !dir;
        }


    
        //2. BFS - O(N*N)
        int moves = 0;
        Queue<Integer> q = new LinkedList<>();
        // add starting position and mark visited
        q.add(0);
        flattened[0] = -2;

        while(!q.isEmpty()){
            int sizeQ = q.size();
            for(int i=0;i<sizeQ;i++){
                int curr = q.poll();

                // check if destination
                if(curr == flattened.length -1){
                    return moves;
                }

                // process neighbors
                for(int j=1;j<=6;j++){
                    int neighbor = Math.min(curr+j, flattened.length -1);

                    // check if not visited, then process
                    if(flattened[neighbor] != -2){
                        if(flattened[neighbor] == -1){
                            // regular spot
                            q.add(neighbor);
                            flattened[neighbor] = -2;
                        }else{
                            // snake or ladder
                            q.add(flattened[neighbor]);
                            flattened[neighbor] = -2;
                        }
                    }
                }
            }
            moves++;
        }

        return -1;
    }
}