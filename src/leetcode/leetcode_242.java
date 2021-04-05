package leetcode;

import java.util.Arrays;

public class leetcode_242 {
    public static boolean isAnagram(String s, String t) {
        int len1 = s == null ? -1 : s.length();
        int len2 = t == null ? -1 : t.length();
        if(len1 != len2){
            return false ;
        }else if(len1 == -1 || len1 == 0){
            return true ;
        }
        char[] chs1 = s.toCharArray();
        char[] chs2 = t.toCharArray();

        Arrays.sort(chs1);
        Arrays.sort(chs2);

        for(int i=0;i<len1;i++){
            if(chs1[i] != chs2[i]){
                return false ;
            }
        }
        return true ;
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("a","b"));
    }
}
