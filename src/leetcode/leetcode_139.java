package leetcode;

import java.util.Arrays;
import java.util.List;

public class leetcode_139 {
    public static boolean wordBreak(String s, List<String> wordDict) {
        if(s == null || s.length() == 0){
            return false ;
        }

        int len = s.length();

        boolean[][] dp = new boolean[len][len];

        for(int i=0;i<len;i++){
            for(int j=0;j<=i;j++){
                if(j<i){
                    dp[i][j] = true ;
                }else{
                    dp[i][j] = wordDict.contains(s.substring(i,j+1));
                }
            }
        }

        for(int i=len-2;i>=0;i--){
            int j=len-1;
            while(j>i){
                String subStr = s.substring(i,j+1);
                if(subStr.length() == 1){
                    dp[i][j] = wordDict.contains(subStr);
                }else{
                    boolean res = false ;
                    for(String word : wordDict){
                        int index = subStr.indexOf(word) ;
                        if(index == -1){
                            continue ;
                        }

                        res = (index>0 ? dp[i][i+index-1] : true ) && (i+index+word.length()<=j ? dp[i+index+word.length()][j] : true) ;

                        if(res){
                            break ;
                        }
                    }
                    dp[i][j] = res ;
                }
                j-- ;
            }
        }

        return dp[0][len-1];
    }

    public static void main(String[] args) {
        String s = "leetcode" ;
        List<String> dict = Arrays.asList("leet","code");
        System.out.println(wordBreak(s,dict));
    }
}
