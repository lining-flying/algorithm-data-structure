package recursiveanddp;

import java.net.BindException;

/**
 * 给定一个数组，代表每个人喝完咖啡清洗咖啡杯的时间
 * 只有一台咖啡机，一次也只能清晰一个杯子，时间耗费为a，
 * 咖啡杯也可以自己挥发干净，耗时b， 咖啡杯可以并行挥发
 * 返回让所有咖啡杯变干净的最早完成时间（即最短耗时）
 *
 */
public class CoffeeCupCleanSolution {

    /**
     * 暴力递归解法
     * @param drinks 每个人喝完咖啡的时间点,数组一定是有序的
     * @param a 咖啡洗干净的耗时
     * @param b 咖啡自动挥发干净的耗时
     * @return
     */
    public static int earlestWashTime(int[] drinks, int a,int b){
        if(drinks == null || drinks.length == 0){
            return 0 ;
        }
        if(a >= b){
            return drinks[drinks.length-1] + b ;
        }
        return process(drinks,a,b,0,0);
    }

    /**
     * 从index开始的咖啡杯清洗干净的最早完成时间
     * @param drinks
     * @param a
     * @param b
     * @param index
     * @param washline 咖啡机洗完的时间
     * @return
     */
    private static int process(int[] drinks, int a, int b, int index, int washline) {
        //base case 如果最后只剩下一个杯子的话，则完成的最早时间为洗咖啡杯完成的时间和挥发完成的时间的最小值
        if(index == drinks.length-1){
            return Math.min(Math.max(washline,drinks[index])+a,drinks[index]+b);
        }

        //如果剩余不止一个杯子时
        //情况一：如果index杯子选择咖啡机洗
        //洗完index杯子的时间
        int wash = Math.max(washline,drinks[index]) + a ;
        //后面所有杯子都清洗干净的最早完成时间
        int next1 = process(drinks,a,b,index+1,wash);
        //情况一的最早完成时间
        int p1 = Math.max(wash,next1) ;

        //情况二：如果index杯子选择自己挥发
        //index杯子干净的时间
        int dry = drinks[index] + b ;
        //洗完后面所有杯子的最早完成时间
        int next2 = process(drinks, a, b, index+1, washline);
        int p2 = Math.max(dry,next2);

        return Math.min(p1,p2);
    }

    /**
     * 动态规划解法
     * @param drinks
     * @param a
     * @param b
     * @return
     */
    public static int earliestWashTimeDpSolution(int[] drinks,int a,int b){
        if(drinks == null || drinks.length == 0){
            return  0 ;
        }
        if(a >= b){
            return drinks[drinks.length-1] + b ;
        }

        //获取边界，所有杯子都去清洗的完成时间
        int limit = 0 ; //可以洗咖啡杯的空闲时间点
        for(int time : drinks){
            limit = Math.max(limit,time) + a ;
        }

        int N = drinks.length ;

        //dp[i][j]表示前i个杯子洗完了，洗杯子的空闲时间点为j时的最早完成时间
        int[][] dp = new int[N][limit+1] ;

        //base case
        for(int washline = 0;washline<=limit;washline++){
            dp[N-1][washline] = Math.min(Math.max(washline,drinks[N-1])+a,drinks[N-1]+b);
        }

        for(int index = N-2;index>=0;index--){
            for(int washline = 0;washline<=limit;washline++){

                int p1 = Integer.MAX_VALUE ;
                int wash = Math.max(washline,drinks[index]) + a ;
                if(wash <= limit) { //主要时为了防止数组越界
                    p1 = Math.max(wash, dp[index + 1][wash]);
                }

                int dry = drinks[index] + b ;
                int p2 = Math.max(dry,dp[index+1][washline]);

                dp[index][washline] = Math.min(p1,p2) ;
            }
        }


        return dp[0][0] ;
    }


    public static void main(String[] args) {
        int[] drinks = {1,1,2,2,3,3,4,4,5,5,5,5,10};
        int a = 3 ;
        int b = 10 ;
        System.out.println(earlestWashTime(drinks,a,b));
        System.out.println(earliestWashTimeDpSolution(drinks,a,b));
    }
}
