package recursiveanddp;

/**
 * 跳马问题：
 * 给定棋盘10*9，马在起点（0，0），马只能走日，且只能走K步
 * 求有多少中走法
 */
public class HorseJumpSolution {

    public static int ways(int x,int y,int k){
        //边界
        if(k <= 0 || x<0 || x>9 || y<0 || y>8){
            return 0 ;
        }

        return process(x,y,0,0,k);
    }

    /**
     * 从(curRow,curCol)到（x,y）走rest步到达的方法数
     * @param x 终点行坐标
     * @param y 终点列坐标
     * @param curRow 当前位置行坐标
     * @param curCol 当前位置列坐标
     * @param rest 当前剩余步数
     * @return
     */
    private static int process(int x, int y, int curRow, int curCol, int rest) {
        //base case
        if(rest == 0){
            return curRow == x && curCol == y ? 1 : 0 ;
        }

        if(curRow <0 || curRow>9 || curCol<0 || curCol>8){
            return 0 ;
        }
        //分情况讨论 马走日的8中case
        return process(x,y,curRow+1,curCol-2,rest-1) + process(x,y,curRow+1,curCol +2,rest -1)
                + process(x,y,curRow+2,curCol-1,rest-1) + process(x,y,curRow+2,curCol+1,rest-1)
                + process(x,y,curRow-1,curCol-2,rest-1) + process(x,y,curRow-1,curCol +2,rest -1)
                + process(x,y,curRow-2,curCol-1,rest-1) + process(x,y,curRow-2,curCol+1,rest-1) ;
    }

    /**
     * 递归解法
     * @param x
     * @param y
     * @param k
     * @return
     */
    public static int waysDp(int x,int y,int k){
        //边界
        if(k <= 0 || x<0 || x>9 || y<0 || y>8){
            return 0 ;
        }

        int[][][] dp= new int[10][9][k+1] ;
        dp[x][y][0] = 1 ;
        for(int level=1;level<=k;level++){
            for(int i=9;i>=0;i--){
                for(int j=8;j>=0;j--){
                    dp[i][j][level] =
                            getDp(dp,i+1,j-2,level-1) +
                            getDp(dp,i+1,j+2,level-1) +
                            getDp(dp,i-1,j-2,level-1) +
                            getDp(dp,i-1,j+2,level-1) +
                            getDp(dp,i+2,j-1,level-1) +
                            getDp(dp,i+2,j+1,level-1) +
                            getDp(dp,i-2,j-1,level-1) +
                            getDp(dp,i-2,j+1,level-1)  ;
                }
            }
        }


        return dp[0][0][k] ;
    }

    private static int getDp(int[][][] dp,int i,int j,int level){
        if(i <0 || i>9 || j<0 || j>8){
            return 0 ;
        }
        return dp[i][j][level] ;
    }

    public static void main(String[] args) {
        int x = 6 ;
        int y = 8 ;
        int k = 8 ;
        System.out.println(ways(x,y,k));
        System.out.println(waysDp(x,y,k));
    }
}
