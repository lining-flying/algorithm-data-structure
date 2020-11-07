package recursiveanddp;

/**
 * 从暴力递归到动态规划
 * 题目一、
 * 给定一个数组，长度为N
 * 给定一个起始点M，目的点P，需要走的步数K
 * 当在最左侧时可以向右走，在最右侧时可以向左走，中间位置，可以向左或者向右
 * 求从M点走W点的走法有多少中
 */
public class WaysToWalkSolution {
    /**
     * 暴力递归
     * @param N 长度
     * @param M 起点
     * @param K 允许的步数
     * @param P 目的终点
     * @return
     */
    public static int waysTo(int N,int M,int K,int P){
        //非法参数
        if(N <= 1 || M<1 || M>N || P<1 || P>N){
            return 0 ;
        }
        return walk(N,M,K,P);
    }

    /**
     * 从当前点到终点在有限步数下可以走的方法个数
     * @param N 路的长度 固定参数
     * @param cur 当前位置 可变参数
     * @param rest 剩余步数 可变参数
     * @param P 终点 固定参数
     * @return
     */
    private static int walk(int N, int cur, int rest, int P) {
        //如果没有路可以走了
        //如果当前位置是终点，则return 1
        //如果当前位置不是终点，则return 0
        if(rest == 0){
            return cur == P ? 1 : 0 ;
        }

        //如果是在1位置，则只能往右走
        if(cur == 1){
            return walk(N,2,rest-1,P);
        }

        //如果是在N位置，则只能往右走
        if(cur == N){
            return walk(N, N-1, rest-1,P);
        }

        return walk(N,cur-1,rest-1,P) + walk(N,cur+1,rest-1,P);

    }

    /**
     * 暴力递归解法二：使用缓存来解决重复计算问题
     * 记忆化搜索
     * @param N
     * @param M
     * @param K
     * @param P
     * @return
     */
    public static int waysToCache(int N,int M,int K,int P){
        //非法参数
        if(N <= 1 || M<1 || M>N || P<1 || P>N){
            return 0 ;
        }

        int[][] dp = new int[N+1][K+1];
        for(int row=0;row<=N;row++){
            for(int col=0;col<=K;col++){
                dp[row][col] = -1 ;
            }
        }
        return walkCache(N,M,K,P,dp);
    }

    /**
     * 从当前点到终点在有限步数下可以走的方法个数
     * @param N 路的长度 固定参数
     * @param cur 当前位置 可变参数
     * @param rest 剩余步数 可变参数
     * @param P 终点 固定参数
     * @param dp 缓存数组
     * @return
     */
    private static int walkCache(int N, int cur, int rest, int P, int[][] dp) {
        //如果已经计算过，则不再重复计算
        if(dp[cur][rest] != -1){
            return dp[cur][rest];
        }

        int res ;

        //如果没有路可以走了
        //如果当前位置是终点，则return 1
        //如果当前位置不是终点，则return 0
        if(rest == 0){
            res =  cur == P ? 1 : 0 ;
        }else if(cur == 1){
            //如果是在1位置，则只能往右走
            res = walkCache(N,2,rest-1,P, dp);
        }else if(cur == N){
            //如果是在N位置，则只能往右走
            res = walkCache(N, N-1, rest-1,P, dp);
        }else {
            // 向左走 向右走 之和
            res = walkCache(N, cur - 1, rest - 1, P, dp) + walkCache(N, cur + 1, rest - 1, P, dp);
        }
        dp[cur][rest] = res ;
        return  res ;
    }

    /**
     * 动态规划解法
     * @param N
     * @param M
     * @param K
     * @param P
     * @return
     */
    public static int waysToByDp(int N,int M,int K,int P){
        if(N <= 1 || M<1 || M>N || P<1 || P>N || K<0){
            return 0 ;
        }

        //dp[i][j]表示从i到P剩余j步需要的步数
        int dp[][] = new int[N+1][K+1];
        dp[P][0] = 1 ; //已经到达P点，需要1步

        for(int j=1;j<=K;j++){
            //因为dp[i][j]依赖的是dp[i+1][j-1]和dp[i-1][j-1]的状态，所以需要按列遍历
            for(int i=1;i<=N;i++){
                //状态转移方程
                if(i<N){
                    dp[i][j] = dp[i-1][j-1]+dp[i+1][j-1];
                }else{
                    dp[i][j] = dp[i-1][j-1];
                }
            }
        }

        return dp[M][K];
    }

    public static void main(String[] args) {
        System.out.println(waysToByDp(7,2,5,3));
    }
}
