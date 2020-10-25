package recursiveanddp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 输出一个字符串的全排列
 * 理论上假设字符串每个字符不重复
 */
public class PrintAllPermunationsSolution {

    /**
     * 字符串的全排列
     * @param str
     * @return
     */
    public static List<String> allPermunations(String str){
        List<String> result = new ArrayList<>();
        if(str == null || str.length() == 0){
            return result ;
        }
        char[] chs = str.toCharArray();
        process(0,chs,result);
        return result ;
    }

    private static void process(int index, char[] chs, List<String> result) {
        if(index == chs.length-1){
            result.add(new String(chs));
        }

//        按层去重
        Set<Character> set = new HashSet<>();

        for(int i=index ; i<chs.length;i++){
            if(set.add(chs[i])) {
                swap(chs, index, i);
                process(index + 1, chs, result);
                swap(chs, index, i);
            }
        }
    }

    private static void swap(char[] chs,int i,int j){
        if(i == j){
            return ;
        }
        char temp = chs[i];
        chs[i] = chs[j];
        chs[j] = temp ;
    }

    public static void main(String[] args) {
        String str = "abcd";
        List<String> results = allPermunations(str);

        System.out.println(results.size());

        for (String result : results) {
            System.out.println(result);
        }
    }
}
