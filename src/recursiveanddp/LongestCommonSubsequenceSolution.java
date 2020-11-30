package recursiveanddp;

/**
 * 最长公共子序列
 * 子序列： 不一定连续
 * 子串：一定时连续的
 */
public class LongestCommonSubsequenceSolution {


    /**
     * 动态规划解
     * @param str1
     * @param str2
     * @return
     */
    public static int lcse(String str1,String str2){
        if(str1.length() == 0 || str2.length() == 0){
            return 0 ;
        }

        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();

        //dp[i][j] 表示str1从0...i，str2从0...j中最长公共子序列的长度
        int[][] dp = new int[chs1.length][chs2.length];
        dp[0][0] = chs1[0] == chs2[0] ? 1 : 0 ;
        for(int i=1;i<chs1.length;i++){
            dp[i][0] = Math.max(chs1[i] == chs2[0] ? 1 : 0,dp[i-1][0]);
        }

        for(int j=1;j<chs2.length;j++){
            dp[0][j] = Math.max(chs1[0] == chs2[j] ? 1 : 0,dp[0][j-1]);
        }

        for(int i=1;i<chs1.length;i++){
            for(int j=1;j<chs2.length;j++){
                //公共子序列以chs1[i]结尾但不以chs2[j]结尾，以chs2[j]结尾但不以chs1[i]结尾的的情况下的最大值
                dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
                if(chs1[i] == chs2[j]){
                    dp[i][j] = Math.max(dp[i][j],dp[i-1][j-1]+1);
                }
                //此处else没有也可以，因为dp[i-1][j]和dp[i][j-1]一定不会比dp[i-1][j-1]小
               /* else{
                    dp[i][j] = Math.max(dp[i][j],dp[i-1][j-1]);
                }*/
            }
        }

        return dp[chs1.length-1][chs2.length-1] ;
    }
}
