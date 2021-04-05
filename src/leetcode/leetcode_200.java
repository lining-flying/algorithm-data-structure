package leetcode;

public class leetcode_200 {

    //深度优先搜索
    public static int numIslands(char[][] grid) {
        int count = 0 ;
        boolean[][] visit = new boolean[grid.length][grid[0].length];
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                //只有数据为一且没有访问过时
                if(grid[i][j] == '1' && !visit[i][j]){
                    dfs(grid,visit,i,j);
                    count ++ ;
                }
            }
        }

        return count ;
    }

    private static void dfs(char[][] grid,boolean[][] visit,int row,int col){
        //base case 数组越界
        if(row<0 || row>=grid.length || col<0 || col>=grid[0].length){
            return ;
        }else if(grid[row][col] == '0' || visit[row][col]){
            //base case 遇到水时终止
            return ;
        }

        //确定grid[row][col]==1
        visit[row][col] = true ; //设置访问标志
        int[][] dir = new int[][]{{0,-1},{0,1},{-1,0},{1,0}};
        for(int i=0;i<dir.length;i++){
            dfs(grid,visit,row+dir[i][0],col+dir[i][1]);
        }
    }

    public static void main(String[] args) {
        char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        System.out.println(numIslands(grid));
    }
}
