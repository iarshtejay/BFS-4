// ## Problem1: Minesweeper (https://leetcode.com/problems/minesweeper/)

// DFS solution
// We have connected components, so can use either BFS or DFS
// Time: O(M*N)
// Space: O(M*N)

class Solution {
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

    private void dfs(char[][] board, int r, int c, int[][] dirs){
        //base
        if(r<0||c<0||r==board.length||c==board[0].length||board[r][c]=='B') return;

        //logic
        board[r][c]='B';
        int count = countMinesAround(board,r,c,dirs);
        // process neighbors
        if(count>0){
             board[r][c]=(char)(count+'0');
        }else{
            for(int[] dir: dirs){
                dfs(board,r+dir[0],c+dir[1],dirs);
            }
        }
    }

    public char[][] updateBoard(char[][] board, int[] click) {
        // check mine at click
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }

        int[][] dirs = {{0,1}, {1,0}, {0,-1},{-1,0},{1,1},{-1,-1},{1,-1},{-1,1}};

        // DFS - Time: O(M*N)
        dfs(board, click[0], click[1], dirs);
       
        return board;
    }
}

