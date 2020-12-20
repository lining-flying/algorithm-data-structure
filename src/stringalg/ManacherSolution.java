package stringalg;

public class ManacherSolution {

    /**
     * manacher算法：最长回文子串长度
     * @param s
     * @return
     */
    public static int manacher(String s){
        if(s == null || s.length() == 0){
            return 0 ;
        }

        //补充分隔符#
        char[] str = manacherString(s);

        int R = -1 ; //最右侧回文半径位置+1,即第一个违规的位置
        int C = -1 ; //左右侧回文半径对应的对称中心

        int[] parr = new int[str.length];

        int max = Integer.MIN_VALUE ;

        int i = 0 ;
        while(i<str.length){
            //先确定的第i个位置回文半径至少是多少，即不需要验的位置
            //i>=R时，需要向两边扩，所以至少为1
            //i<=R时，看i相对于C的对称点的i'的回文范围：
            // 1. i'的回文范围在C的回文范围之内，i的回文半径即为parr[i']
            // 2. i‘的回文范围压在C的回文范围左边界，i的回文半径至少为R-i
            parr[i] = R>i ? Math.min(parr[2*C-i],R-i) : 1 ;

            while(i+parr[i] < str.length && i-parr[i]>-1 ){
                if(str[i+parr[i]] == str[i-parr[i]]){
                    parr[i]++ ;
                }else{
                    break;
                }
            }

            if(i+parr[i]>R){
                R = i+parr[i] ;
                C = i ;
            }

            max = Math.max(max,parr[i]) ;

            i++ ;
        }

        return max-1 ;
    }

    private static char[] manacherString(String s) {
        char[] manacherString = new char[s.length()*2+1];

        char[] charArr = s.toCharArray();
        int index = 0 ;
        for(int i=0;i<manacherString.length;i++){
            manacherString[i] = (i&1) == 0 ? '#' : charArr[index++];
        }
        return manacherString ;
    }



    public static  int countSubstrings(String s) {
        if(s == null || s.length() == 0){
            return 0 ;
        }
        //补充分隔符#
        char[] str = manacherString(s);

        int R = -1 ; //最右侧回文半径的下一个位置，即第一个违规的位置
        int C = -1 ; //最右侧回文半径的中心

        int[] parr = new int[str.length]; //记录每个位置的回文半径

        int i=0;
        while(i<str.length){
            parr[i] = R>i ? Math.min(parr[2*C-i],R-i) : 1 ;

            while(i+parr[i] < str.length && i-parr[i]>-1 ){
                if(str[i+parr[i]] == str[i-parr[i]]){
                    parr[i]++ ;
                }else{
                    break;
                }
            }

            if(i+parr[i]>R){
                R = i+parr[i] ;
                C = i ;
            }

            i++ ;
        }

        int count = 0 ;
        for(i=0;i<parr.length;i++){
            count+= parr[i]/2 ;
        }

        return count ;
    }


    public static int countSubstrings2(String s) {
        if(s == null || s.length() == 0){
            return 0 ;
        }
        //补充分隔符#
        char[] str = manacherString(s);

        int ans = 0;
        for(int i=0;i<str.length;i++){
            int j=1 ;
            int c = 1 ;
            while(i-j>-1 && i+j<str.length){
                if(str[i-j] == str[i+j]){
                    c ++ ;
                    j++ ;
                }else{
                    break ;
                }
            }
            ans += c/2 ;
        }

        return ans ;
    }





    // for test
    public static int right(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherString(s);
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            int L = i - 1;
            int R = i + 1;
            while (L >= 0 && R < str.length && str[L] == str[R]) {
                L--;
                R++;
            }
            max = Math.max(max, R - L - 1);
        }
        return max / 2;
    }

    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        /*int possibilities = 5;
        int strSize = 20;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            if (manacher(str) != right(str)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");*/

        System.out.println(countSubstrings("aba"));
        System.out.println(countSubstrings2("aba"));
    }
}
