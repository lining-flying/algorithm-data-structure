package recursiveanddp;

/**
 * 纸牌游戏
 * 玩家A和玩家B，两人都只能从最左或者最右侧取牌，最终谁的手中的牌分数最大谁获胜，
 * 假设玩家A和玩家B都绝顶聪明，返回获胜者的分数
 */
public class CardGameProblemSolution {

    /**
     * 暴力递归解法 范围尝试模型
     * @param cards
     * @return
     */
    public static int battlePoint(int[] cards){
        if(cards == null || cards.length == 0){
            return 0 ;
        }
        return Math.max(f(cards,0,cards.length-1),s(cards,0,cards.length-1)) ;
    }

    /**
     * 后手拿牌能拿到的最小值
     * @param cards
     * @param L
     * @param R
     * @return
     */
    private static int s(int[] cards, int L, int R) {
        //base case
        if(L == R){
            return 0 ;
        }
        //分情况讨论 1. 对手先拿左边，2. 对手先拿右边
        // 后手拿牌，对手一定是要让自己拿牌最小
        return Math.min(f(cards,L+1,R),f(cards,L,R-1));
    }

    /**
     * 先手拿牌能拿到的最大值
     * @param cards
     * @param L
     * @param R
     * @return
     */
    private static int f(int[] cards, int L, int R) {
        // base case
        if(L == R){
            return cards[L];
        }
        // 分情况讨论 1. 先拿左边，2. 先拿后边
        // 先手拿牌一定是要拿能让自己拿牌最大的
        return Math.max(cards[L]+s(cards,L+1,R),cards[R]+s(cards,L,R-1));
    }

    /**
     * 动态规划解法
     * @param cards
     * @return
     */
    public static int battlePointDpWay(int[] cards){
        if(cards == null || cards.length == 0){
            return 0 ;
        }
        int N = cards.length ;
        int[][] f = new int[N][N];
        int[][] s = new int[N][N];
        for(int i=0;i<N;i++){
            f[i][i] = cards[i];
            s[i][i] = 0 ;
        }

        for(int i=1;i<N;i++){
            int L = 0 ;
            int R = i ;
            while(L<N && R<N){

                f[L][R] = Math.max(cards[L]+s[L+1][R],cards[R]+s[L][R-1]);
                s[L][R] = Math.min(f[L+1][R],f[L][R-1]);

                L++ ;
                R++ ;
            }
        }

        return Math.max(f[0][N-1],s[0][N-1]);
    }


    public static void main(String[] args) {

        int[] cards = {1,9,3,6,4,2,8};

        System.out.println(battlePoint(cards));

        System.out.println(battlePointDpWay(cards));

    }
}
