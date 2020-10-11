package datastructure.greedy;

import java.util.HashSet;

/**
 * 街道最少放置灯数
 */
public class Greedy_03_MinLightArrange {

    /**
     * 街道最少放灯数 -- 暴力解法
     * 全排列
     * 仅用作对数器
     * @param str
     * @return
     */
    public static int minLightsArrange_1(char[] str){
        if(str == null || str.length == 0){
            return 0 ;
        }
        return process_01(str,0,new HashSet<Integer>());
    }

    private static int process_01(char[] str, int index, HashSet<Integer> lights) {
        if(index == str.length){
            for(int i=0;i<index;i++){
                if(str[i] != 'X') {
                    if (!lights.contains(i - 1) && !lights.contains(i) && lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        }else{
            int no = process_01(str,index+1,lights);
            int yes = Integer.MAX_VALUE;
            if(str[index] == '.'){
                lights.add(index);
                yes = process_01(str,index+1,lights);
                lights.remove(index);
            }
            return Math.min(yes,no);
        }
    }

    /**
     * 街道最少放灯数 —— 贪心解法
     * @param str
     * @return
     */
    public static int minLightsArrange_2(char[] str){
        //TODO 贪心解法实现
        if(str == null || str.length == 0){
            return 0 ;
        }

        int index = 0 ;
        int light = 0 ;

        while(index < str.length){
            if(str[index] == 'X'){
                index ++ ;
            }else{
                light ++ ;
                if(index +1 == str.length){
                    break;
                }else{
                    if(str[index+1] == 'X'){
                        index = index +2 ;
                    }else {
                        index = index + 3 ;
                    }
                }
            }
        }


        return light ;
    }


    public static void main(String[] args) {
        //TODO 对数器
    }

}
