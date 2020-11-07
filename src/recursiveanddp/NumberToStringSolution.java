package recursiveanddp;

/**
 * 给定字符串1-A，2-B，3-C...，
 * 那么一个字符串111，可以转化为AAA，AK和KA
 * 求给定只有数字组成的字符串，求能转化出的字符串个数
 */
public class NumberToStringSolution {

    /**
     *  暴力递归：从左向右尝试模型
     * @param str
     * @return
     */
    public static int numberToString(String str){
        if(str == null || str.length() == 0){
            return 0 ;
        }
        return process(str.toCharArray(),0);
    }

    private static int process(char[] chs, int index) {
        if(index == chs.length ){
            return 1 ;
        }
        //index位置为0，则无法转化，返回0
        if(chs[index] == '0'){
            return 0 ;
        }

        //如果index位置为1
        if(chs[index] == '1'){
            int res = process(chs,index+1);
            //如果index+1没有越界，则可以考虑index和index+1组成一个字母的情况
            if(index+1 < chs.length){
                res += process(chs,index+2);
            }
            return res ;
        }
        //如果index位置为2
        if(chs[index] == '2'){
            int res = process(chs,index+1);
            //如果index+1没有越界，并且index+1位置小于等于6，则额外考虑index和index+1组成一个字母的情况
            if(index+1 < chs.length && chs[index+1] <='6'){
                res += process(chs,index+2);
            }
            return res ;
        }
        //如果index位置为3-9，只可能一位组成一个字母，因为两位一定会越界（超过26）
        return process(chs,index+1);
    }

    private static int dpways(String str) {
        if(str == null || str.length() == 0){
            return 0 ;
        }

        char[] chs = str.toCharArray();
        int length = chs.length;
        int[] dp = new int[length+1];
        dp[length]= 1;
        for (int index = length-1; index >= 0; index--){
            //index位置为0，则无法转化，返回0
            if(chs[index] == '0'){
                dp[index] =  0 ;
                continue;
            }

            //如果index位置为1
            if(chs[index] == '1'){
                dp[index] = dp[index+1];
                //如果index+1没有越界，则可以考虑index和index+1组成一个字母的情况
                if(index+1 < chs.length){
                    dp[index] += dp[index+2];
                }
                continue;
            }
            //如果index位置为2
            if(chs[index] == '2'){
                dp[index] = dp[index+1];
                //如果index+1没有越界，并且index+1位置小于等于6，则额外考虑index和index+1组成一个字母的情况
                if(index+1 < chs.length && chs[index+1] <='6'){
                    dp[index] += dp[index+2];
                }
                continue;
            }
            //如果index位置为3-9，只可能一位组成一个字母，因为两位一定会越界（超过26）
            dp[index]= dp[index+2];
        }

        return dp[0];
    }
}
