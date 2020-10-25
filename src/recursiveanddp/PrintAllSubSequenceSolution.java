package recursiveanddp;

import java.util.*;

/**
 * 打印给定字符串的所有子序列
 */
public class PrintAllSubSequenceSolution {

    public static List<String> allSubSequence(String str) {
        List<String> result = new ArrayList<>();
        process(0, str, result);
        result.add("");
        return result;
    }


    private static void process(int i,String str, Collection<String> result) {
        if(i>=str.length()){
            return ;
        }
        if(!result.isEmpty()) {
            Set<String> newStrs= new HashSet<>(result.size());
            for (String ele : result) {
                newStrs.add(ele + str.charAt(i));
            }
            result.addAll(newStrs);
        }
        result.add(str.charAt(i)+"");
        process(i+1,str,result);
    }

    public static Set<String> allSubSquenceWithNoRepeat(String str){
        Set<String> result = new HashSet<>();
        process(0,str,result);
        result.add("");
        return result ;
    }


    public static void print(Collection<String> strs){
        for(String str : strs){
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        String str = "abc";
        List<String> subSquences = allSubSequence(str);
        print(subSquences);

        System.out.println("============================");

        String strs = "aabca";
        Set<String> subSequence2 = allSubSquenceWithNoRepeat(strs);
        print(subSequence2);
    }
}
