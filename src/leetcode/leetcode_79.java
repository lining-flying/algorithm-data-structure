package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 单词搜索
 */
public class leetcode_79 {

    public boolean exist(char[][] board, String word) {
        char[] chs = word.toCharArray();
        int h = board.length, w = board[0].length;
        boolean[][] viewed = new boolean[h][w]; //表示是否访问过

        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){
                boolean flag = check(board,viewed,i,j,chs,0);
                if(flag){
                    return true ;
                }
            }
        }
        return false ;
    }

    //表示从board[i][j]开始是否能找到chs[index...]的单词
    //典型的深度优先搜索||回溯算法
    private boolean check(char[][] board,boolean[][] viewed,int i,int j,char[] chs,int index){
        if(board[i][j] != chs[index]){
            return false ;
        }else if(index == chs.length-1){
            return true ;
        }
        boolean result = false ;
        viewed[i][j] = true ;
        int[][] direction = new int[][]{{0,-1},{0,1},{-1,0},{1,0}}; //表示四个方向
        for(int[] dir : direction){
            int newi = dir[0] + i ;
            int newj = dir[1] + j ;
            if(newi>=0 && newi<board.length && newj>=0 && newj<board[0].length){
                if(!viewed[newi][newj]){
                    boolean flag = check(board,viewed,newi,newj,chs,index+1);
                    if(flag){
                        result = true ;
                        break;
                    }
                }
            }
        }
        viewed[i][j] = false ;
        return result ;
    }

    public static void main(String[] args) {
        leetcode_79 main = new leetcode_79();

        char[][] board = new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCED" ;

        System.out.println(main.exist(board,word));
    }
}
