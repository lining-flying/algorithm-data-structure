package leetcode;

/**
 * 最大正方形
 */
public class leetcode_221 {
    public static int maximalSquare(char[][] matrix) {
        //边界case
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0 ;
        }
        int rLen = matrix.length;
        int cLen = matrix[0].length;

        int[][] dp = new int[rLen][cLen];

        int maxSide = 0 ;

        for(int i=0;i<rLen;i++){
            for(int j=0;j<cLen;j++){
                if(matrix[i][j] == '0'){
                    dp[i][j] = 0 ;
                }else{
                    if(i == 0 || j == 0){
                        dp[i][j] = 1 ;
                    }else{
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                }
                maxSide = Math.max(maxSide,dp[i][j]);
            }
        }

        return maxSide * maxSide ;
    }

    public static void main(String[] args) {

    }
}
