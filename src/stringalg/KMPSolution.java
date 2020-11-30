package stringalg;

/**
 * KMP算法
 * 解决的时字符串匹配问题
 * 即 字符串A中是否包含值为字符串B的子串
 */
public class KMPSolution {

    /**
     * 时间复杂度O(N)
     * @param s 源字符串 长度为N
     * @param m 目标字符串 长度为M
     * @return -1 表示没有匹配，其他表示匹配到的s中第一个子串的第一个元素的下标
     */
    public static int kmp(String s,String m){
        if(s == null || m == null || s.length() == 0 || m.length() == 0 || s.length()<m.length()){
            return -1 ;
        }

        char[] str = s.toCharArray() ; //O(N)
        char[] match = m.toCharArray() ; //O(M)
        int x = 0 ; //str中比对到的位置
        int y = 0 ; //match中比对到的位置
        // O(?)
        int[] next = getNextArray(match); //next[i]表示match[0..i-1]中前缀和后缀相等的最大长度

        // O(N)
        while(x < s.length() && y < m.length()){
            if(str[x] == match[y]){ //当前位置字符匹配，两个位置都往后移动
                x++ ;
                y++ ;
            }else if(y == 0){ //如果y还在起始位置，说明起始位置就不匹配，x向后移
                x++ ;
            }else{
                y = next[y] ; //相当于从x-next[y]起始的字符串继续看是否匹配
            }
        }

        return y == m.length() ? x-y : -1 ;
    }

    private static int[] getNextArray(char[] match) {
        if(match.length == 1){
            return new int[]{-1};
        }

        int[] next = new int[match.length] ;
        next[0] = -1 ;
        next[1] = 0 ;

        int cn = 0 ;
        int i=2 ;

        while(i<match.length){
            if(match[i-1] == match[cn]){
                next[i++] = ++cn ;
            }else if(cn>0){
                cn = next[cn];
            }else{
                next[i++] = 0 ;
            }
        }
        return next ;
    }
}
