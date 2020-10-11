package datastructure.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 最大利润
 * 给定初始资金w和做项目次数k，
 * 给定每个项目的花费和利润，
 * 求做项目所能获取的最大金额
 */
public class Greedy_05_MaxProfit {


    /**
     * 贪心算法实现
     * 贪心策略： 每一次都做当前成本下能获得的最大利润
     * @param cost
     * @param profit
     * @param w
     * @param k
     * @return
     */
    public static int maxProfit01(int[] cost, int[] profit,int w, int k){
        // 成本小顶堆
        PriorityQueue<Program> minCostQ = new PriorityQueue<>(new Comparator<Program>() {
            @Override
            public int compare(Program p1, Program p2) {
                return p1.c - p2.c;
            }
        });

        // 利润大顶堆
        PriorityQueue<Program> maxProfitQ = new PriorityQueue<>(new Comparator<Program>() {
            @Override
            public int compare(Program p1, Program p2) {
                return p2.p - p1.p ;
            }
        });

        for(int i=0;i<cost.length;i++){
            minCostQ.add(new Program(cost[i],profit[i]));
        }

        int W = w ;

        for(int i=0;i<k;i++){
            while(!minCostQ.isEmpty()){
                if(minCostQ.peek().c <= w){
                    maxProfitQ.add(minCostQ.poll());
                }
            }
            if(maxProfitQ.isEmpty()){
                // 手中资金无法继续做任何项目时，直接结束
                return W ;
            }

            // 每一次都做当前资金能做的项目中最大利润的项目
            W += maxProfitQ.poll().p ;

        }

        return W ;
    }

    static class Program{
        int c ; //成本
        int p ; //利润

        public Program(int c, int p) {
            this.c = c;
            this.p = p;
        }
    }
}
