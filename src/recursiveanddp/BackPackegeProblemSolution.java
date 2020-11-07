package recursiveanddp;

import sun.font.StandardGlyphVector;

import java.util.HashMap;

/**
 * 背包问题
 * 给定一组重量weight和价值value的货，
 * 给定一个容量为C的容器，求能装入货物的最大价值
 * 1. 暴力递归
 * 2. 动态规划 TODO
 */
public class BackPackegeProblemSolution {

    /**
     * 暴力递归解法
     *
     * @param bag 背包空间
     * @param w weight数组
     * @param v value数组
     * @return
     */
    public static int maxValue(int bag,int[] w,int[] v){
        return process(w,v,0,0,bag);
    }

    /**
     * 返回 index及之后能装入的最大价值
     * @param w weight数组
     * @param v value数组
     * @param index 当前索引下标
     * @param alreadyW 已经装入的重量
     * @param bag 背包容量
     * @return
     */
    private static int process(int[] w, int[] v, int index, int alreadyW, int bag) {
        //重量超了，返回-1，标识方案不存在
        if(alreadyW > bag){
            return -1 ;
        }
        //没有货物了,返回0
        if(index == v.length){
            return 0 ;
        }

        //分情况讨论
        //1. 没有装入index位置的货物
        int p1 = process(w,v,index+1,alreadyW,bag);
        //2. 装入的index位置的货物
        int p2Next = process(w,v,index+1,alreadyW+w[index],bag);
        int p2 = -1 ;
        if(p2Next != -1){
            p2 = p2Next + v[index] ;
        }

        return Math.max(p1,p2);
    }

    public static int maxValue2(int bag,int[] w,int[] v){
        return process(w,v,0,bag);
    }

    /**
     * 返回index及之后能产生的最大价值
     * @param w weight数组
     * @param v value数组
     * @param index 当前元素索引下标
     * @param rest 背包剩余空间
     * @return -1标识方案无效，其余标识剩余空间装入的最大价值
     */
    private static int process(int[] w,int[] v,int index, int rest){
        // base case 1
        if(rest < 0){
            return -1 ; //方案无效
        }
        // base case 2
        if(index == v.length){
            return 0 ; //没有其他货物了
        }

        //分情况讨论
        // case 1 不装入当前货物
        int p1 = process(w,v,index+1,rest);
        // case 2 装入当前货物
        int p2 = -1 ;
        int p2Next = process(w,v,index+1,rest-w[index]);
        if(p2Next != -1){
            p2 = v[index] + p2Next;
        }
        return Math.max(p1,p2);
    }

    /**
     * 暴力递归： 记忆化搜索
     * @param bag
     * @param w
     * @param v
     * @return
     */
    public static int maxValue3(int bag,int[] w,int[] v){
        Integer[][] dp = new Integer[w.length+1][bag+1];
        return process3(w,v,0,bag,dp);
    }

    private static int process3(int[] w, int[] v, int index, int rest, Integer[][] dp){
        // base case 1
        if(rest < 0){
            return -1; //方案无效
        }

        if(dp[index][rest] != null){
            return dp[index][rest];
        }

        // base case 2
        if(index == v.length){
            dp[index][rest] = 0 ;
            return dp[index][rest] ; //没有其他货物了
        }

        //分情况讨论
        // case 1 不装入当前货物
        int p1 = process3(w,v,index+1,rest,dp);
        // case 2 装入当前货物
        int p2 = -1 ;
        int p2Next = process3(w,v,index+1,rest-w[index],dp);
        if(p2Next != -1){
            p2 = v[index] + p2Next;
        }
        dp[index][rest] = Math.max(p1,p2);
        return dp[index][rest] ;
    }


    /**
     * 动态规划解法
     * @param bag
     * @param w
     * @param v
     * @return
     */
    public static int maxValue4(int bag,int[] w,int[] v){
        if(bag<=0 || w == null || w.length == 0 || v == null || v.length == 0){
            return 0 ;
        }

        HashMap<String,Integer> map = new HashMap<>();
        for(int i=0;i<=bag;i++){
            map.put(genKey(w.length,i),0);
        }

        for(int i=w.length-1;i>=0;i--){
            for(int j=0;j<=bag;j++){
                int val = Math.max(get(map,i+1,j),get(map,i+1,j-w[i])+v[i]);
                map.put(genKey(i,j),val);
            }
        }

        return map.get(genKey(0,bag));
    }

    private static String genKey(int i,int j){
        return i+":"+j ;
    }

    private static int get(HashMap<String,Integer> map, int i,int j){
        if(j<0){
            return Integer.MIN_VALUE ;
        }

        return map.get(genKey(i,j));
    }


    /**
     * 动态规划实现二
     * @param bag
     * @param w
     * @param v
     * @return
     */
    public static int maxValueDpWay(int bag,int[] w,int[] v){
        int N = w.length ;
        int[][] dp = new int[N+1][bag+1];

        for(int index=N-1;index>=0;index--){
            for(int rest=0;rest<=bag;rest++){
                if(rest-w[index]<0){
                    dp[index][rest] = dp[index+1][rest];
                }else{
                    dp[index][rest] = Math.max(dp[index+1][rest],v[index]+dp[index+1][rest-w[index]]);
                }
            }
        }

        return dp[0][bag];
    }


    public static void main(String[] args) {
        int[] w = new int[]{1,5,3,2,8,6};
        int[] v = new int[]{3,6,5,2,1,4};
        int bag = 6 ;
        System.out.println(maxValue(bag,w,v));
        System.out.println(maxValue2(bag,w,v));
        System.out.println(maxValue3(bag,w,v));
        System.out.println(maxValue4(bag,w,v));
        System.out.println(maxValueDpWay(bag,w,v));
    }
}
