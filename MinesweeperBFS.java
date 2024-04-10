// ## Problem1: Minesweeper (https://leetcode.com/problems/minesweeper/)

// BFS solution
// We have connected components, so can use either BFS or DFS
// Time: O(M*N)
// Space: O(M*N)

class MinesweeperBFS {
    private int countMinesAround(char[][] board, int r, int c, int[][] dirs){
        int count=0;
        for(int[] dir: dirs){
            int nr=r+dir[0];
            int nc=c+dir[1];

            if(nr>=0 && nc>=0 && nr<board.length && nc<board[0].length && board[nr][nc]=='M'){
                count++;
            }
        }
        return count;
    }

    public char[][] updateBoard(char[][] board, int[] click) {
        // check mine at click
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }

        int[][] dirs = {{0,1}, {1,0}, {0,-1},{-1,0},{1,1},{-1,-1},{1,-1},{-1,1}};

        // BFS - Time: O(M*N)
        Queue<int[]> q = new LinkedList<>();
        // add initial to queue and mark as visited
        q.add(click);
        board[click[0]][click[1]] = 'B';
        while(!q.isEmpty()){
            int[] curr = q.poll();
            // count mines around curr
            int mines = countMinesAround(board, curr[0], curr[1], dirs);
            if(mines>0){
                // neighboring mines, set curr cell to count and don't process neighbors
                board[curr[0]][curr[1]] = (char)(mines+'0');
            }else{
                // process neighbors
                for(int[] dir: dirs){
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];

                    if(nr>=0 && nc>=0 && nr<board.length && nc<board[0].length && board[nr][nc]!='B'){
                        // mark as visited and add to queue
                        board[nr][nc] = 'B';
                        q.add(new int[] {nr,nc});
                    }
                }
            }
        }
        return board;
    }
}

