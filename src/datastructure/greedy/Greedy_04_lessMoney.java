package datastructure.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 哈夫曼树原理
 * 给定一个金条，拆分的花费就是金条的长度，
 * 给定目标长度数组，求如何拆分可以使用花费最小
 */
public class Greedy_04_lessMoney {

    /**
     * 暴力穷举
     * 全排列
     * @param arr
     * @return
     */
    public static int lessMoney_01(int[] arr){
        //TODO
        return 0 ;
    }

    /**
     * 贪心算法 -- 堆实现
     * @param arr
     * @return
     */
    public static int lessMoney_02(int[] arr){
        if(arr == null || arr.length == 0){
            return 0 ;
        }

        //PrioryQueue底层实现就是一个小顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int ele : arr){
            queue.add(ele);
        }
        int cost = 0;
        while(queue.size()>1){
            int cur = queue.poll() + queue.poll() ;
            cost += cur ;
            queue.add(cur);
        }
        return cost;
    }

    /**
     * 贪心算法 -- 实现2
     * @param arr
     * @return
     */
    public static int lessMoney_03(int[] arr){
        if(arr == null || arr.length == 0){
            return 0 ;
        }

        Arrays.sort(arr);

        int sum = 0 ;
        for(int i=0;i<arr.length;i++){
            sum += arr[i];
        }
        int cost = 0 ;
        for(int i=arr.length-1;i>0;i--){
            cost += sum ;
            sum -= arr[i];
        }

        return cost ;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{10,20,30};
        System.out.println(lessMoney_03(arr));
    }
}
