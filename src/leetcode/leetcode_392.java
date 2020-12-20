package leetcode;

public class leetcode_392 {

    public static boolean isSubsequence(String s, String t) {
        int len1 = s.length() ;
        int len2 = t.length() ;

        if(len1 == 0){
            return true ;
        }else if(len2 == 0){
            return false ;
        }

        char[] ch1 = s.toCharArray();
        char[] ch2 = t.toCharArray();

        //dp[i][j]表示t的前i个字符中包含了s的前j个字符
        boolean[] dp = new boolean[len1];

        dp[0] = ch1[0] == ch2[0] ;
        for(int i=1;i<len2;i++){
            boolean[] newDp = new boolean[len1];
            for(int j=0;j<len1;j++){
                if(ch2[i] == ch1[j]){
                    newDp[j] = (j>0 ? dp[j-1] : true) || dp[j] ;
                }else{
                    newDp[j] = dp[j] ;
                }
            }
            dp = newDp ;
        }

        return dp[len1-1] ;
    }

    public static void main(String[] args) {
        String s = "twn" ;
        String t = "xtxwxn" ;
        System.out.println(isSubsequence(s,t));
    }
}
