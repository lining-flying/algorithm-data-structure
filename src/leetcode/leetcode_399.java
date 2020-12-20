package leetcode;

import java.util.*;

public class leetcode_399 {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] res = new double[queries.size()];

        UnionSet unionSet = new UnionSet(equations,values);

        int index = 0 ;
        for(List<String> query : queries){
            if(!unionSet.isSameSet(query.get(0),query.get(1))){
                res[index++] = -1.0d ;
                continue ;
            }else{
                res[index++] = unionSet.dist.get(query.get(1)) / unionSet.dist.get(query.get(0));
            }
        }

        return res ;
    }

    class UnionSet{
        Set<String> set ;
        Map<String,String> parent ;
        Map<String,Double> dist ;

        UnionSet(List<List<String>> equations, double[] values){
            parent = new HashMap<String,String>();
            dist = new HashMap<String,Double>();
            set = new HashSet<String>();

            int index = 0 ;
            for(List<String> equation : equations){
                String head0 = parent.get(equation.get(0));
                String head1 = parent.get(equation.get(1));

                if(head0 == null && head1 == null){
                    parent.put(equation.get(1),equation.get(0));
                    dist.put(equation.get(1),values[index]);
                }else{

                }


                index++ ;
            }
        }

        public boolean isSameSet(String str1,String str2){
            if(!set.contains(str1) || !set.contains(str2)){
                return false ;
            }
            String head1 = findHead(str1);
            String head2 = findHead(str2);

            return head1.equals(head2);
        }

        private String findHead(String str){
            String head = parent.get(str);
            if(head == null){
                parent.put(str,str);
                dist.put(str,1.0);
                head = str ;
            }

            String headParent = parent.get(head);
            while(headParent != null && !headParent.equals(head)){
                parent.put(str,headParent);
                double dist1 = dist.get(str);
                double dist2 = dist.get(head);
                dist.put(str,dist2*dist1);
                head = headParent ;
                headParent = parent.get(head);
            }

            return head ;
        }
    }

    public static void main(String[] args) {
        leetcode_399 main = new leetcode_399();

        List<List<String>> equations = Arrays.asList(Arrays.asList("a","c"),Arrays.asList("b","e"),Arrays.asList("c","d"),Arrays.asList("e","d"));
        double[] values = new double[]{2.0,3.0,0.5,5.0};
        //[["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
        List<List<String>> queries = Arrays.asList(Arrays.asList("a","b"));

        double[] res = main.calcEquation(equations,values,queries);
    }
}
