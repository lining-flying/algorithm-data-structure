package recursiveanddp;

import java.sql.PreparedStatement;

/**
 * 找钱问题
 * 给定一组货币面值coin[n]
 * 给定一个金额，求右多少中组合方式组合出给定的金额
 * 假定coin的元素不重复，且每个面值可以重复使用，不限定个数
 */
public class CoinCombineCountSolution {

    /**
     * 暴力递归解法
     * @param target
     * @param coins
     * @return
     */
    public static int coinCombineCountRecursive(int target,int[] coins){
        if(target <=0 || coins == null || coins.length == 0){
            return 0 ;
        }
        return process(coins,0,target);
    }

    /**
     * 从index开始往后的面值可以使用，拼成rest的方式个数
     * @param coins
     * @param index
     * @param rest
     * @return
     */
    private static int process(int[] coins, int index, int rest) {
        // base case 1
        if(rest<0){
            return 0 ;
        }

        //base case 2
        if(index == coins.length){
            return rest == 0 ? 1 : 0 ;
        }

        int res = 0 ;
        // 1. 选取了当前index的元素
        res = process(coins,index,rest-coins[index]);
        // 2. 不选当前index的元素
        res += process(coins,index+1,rest);
        return res ;
    }

    /**
     * 暴力递归解法二
     * @param target
     * @param coins
     * @return
     */
    public static int coinCombineCountRecursive2(int target,int[] coins){
        if(target <=0 || coins == null || coins.length == 0){
            return 0 ;
        }
        return process2(coins,0,target);
    }


    private static int process2(int[] coins, int index, int rest) {
        // base case 1
        if(rest<0){
            return 0 ;
        }

        //base case 2
        if(index == coins.length){
            return rest == 0 ? 1 : 0 ;
        }

        int ways = 0 ;
        // 1. 选取了当前index的元素
        for(int count=0;count*coins[index]<=rest;count++){
            ways += process2(coins,index+1,rest-coins[index]*count);
        }
        return ways ;
    }


    /**
     * 暴力递归优化：记忆化搜索
     * @param target
     * @param coins
     * @return
     */
    public static int coinCombineCountRemember(int target,int[] coins){
        if(target <=0 || coins == null || coins.length == 0){
            return 0 ;
        }
        Integer[][] dp = new Integer[coins.length+1][target+1];
        return process3(coins,0,target,dp);
    }

    private static int process3(int[] coins, int index, int rest, Integer[][] dp) {
        if(dp[index][rest] != null){
            return dp[index][rest];
        }
        //base case 2
        if(index == coins.length){
            dp[index][rest] =  rest == 0 ? 1 : 0 ;
            return dp[index][rest] ;
        }

        int ways = 0 ;
        // 1. 选取了当前index的元素
        for(int count=0;count*coins[index]<=rest;count++){
            ways += process3(coins,index+1,rest-coins[index]*count, dp);
        }
        dp[index][rest] = ways ;
        return ways ;
    }

    /**
     * 动态规划解法
     * @param target
     * @param coins
     * @return
     */
    public static int coinCombineCountDp1(int target,int[] coins){
        if(target <=0 || coins == null || coins.length == 0){
            return 0 ;
        }
        int len = coins.length ;
        int[][] dp = new int[len+1][target+1];
        dp[len][0] = 1 ;
        for(int index = len-1;index>=0;index--){
            for(int rest = 0;rest<=target;rest++){
                int ways = 0 ;
                for(int count=0;coins[index]*count<=rest;count++){
                    ways += dp[index+1][rest-coins[index]*count];
                }
                dp[index][rest] = ways ;
            }
        }


        return dp[0][target];
    }

    /**
     * 动态规划解法二： 精简
     * @param target
     * @param coins
     * @return
     */
    public static int coinCombineCountDp2(int target,int[] coins){
        if(target <=0 || coins == null || coins.length == 0){
            return 0 ;
        }
        int len = coins.length ;
        int[][] dp = new int[len+1][target+1];
        dp[len][0] = 1 ;
        for(int index = len-1;index>=0;index--){
            for(int rest = 0;rest<=target;rest++){
                dp[index][rest] = dp[index+1][rest] ;
                if(rest - coins[index] >=0){
                    dp[index][rest] += dp[index][rest-coins[index]];
                }
            }
        }


        return dp[0][target];
    }

    public static void main(String[] args) {
        int[] coins = {5,10,50,100};
        System.out.println(coinCombineCountRecursive(1000,coins));
        System.out.println(coinCombineCountRecursive2(1000,coins));
        System.out.println(coinCombineCountRemember(1000,coins));
        System.out.println(coinCombineCountDp1(1000,coins));
        System.out.println(coinCombineCountDp2(1000,coins));
    }
}
