package datastructure.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 贪心算法
 */
public class Greedy_01 {

    /**
     * 字符串拼接 - 最小字典序字符串
     * @param strs
     * @return
     */
    public static String minorStr(String[] strs){
        if(strs == null || strs.length == 0){
            return null ;
        }
        Arrays.sort(strs,new MyComparator<>());

        StringBuilder sb = new StringBuilder();
        for(String str : strs){
            sb.append(str);
        }
        return sb.toString();
    }

    static class MyComparator<String> implements Comparator<String>{

        @Override
        public int compare(String s1, String s2) {
            return (s1 +""+ s2).compareTo((s2 + "" + s1));
        }
    }
}
