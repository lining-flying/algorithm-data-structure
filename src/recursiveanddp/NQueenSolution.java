package recursiveanddp;

/**
 * N皇后问题
 */
public class NQueenSolution {

    /**
     * 暴力递归解法
     * @param n
     * @return
     */
    public static int nQueen(int n){
        if(n == 1){
            return 1 ;
        }
        return process(0,new int[n],n);
    }

    /**
     * 递归逻辑
     * @param i 当前在第几行
     * @param record 前i-1行的皇后摆放位置
     * @param n 有多少行
     * @return
     */
    private static int process(int i, int[] record, int n) {
        if(n == i){
            return 1 ;
        }
        int res = 0 ;
        for(int j=0;j<n;j++){
            if(isValid(record,i,j)){
                record[i] = j ;
                res += process(i+1,record,n);
            }
        }
        return res ;
    }

    /**
     * 判断i行j列是否可以摆放皇后
     * @param record
     * @param i
     * @param j
     * @return
     */
    private static boolean isValid(int[] record, int i, int j) {
        for(int k=0;k<i;k++){
            // 判断两个坐标(a,b),(c,d)如果在同一个斜线上，必有|a-c| == |b-d|
            if(j == record[k] || Math.abs(record[k]-j) == Math.abs(k-i)){
                return false ;
            }
        }
        return true ;
    }


    /**
     * 暴力递归解法二：使用位运算优化时间复杂度的常数项
     * 限制：n不能大于32，因为会超过int能表示的最大值
     * @param n
     * @return
     */
    public static int nQueen2(int n){
        if(n<0 || n>32){
            return 0 ;
        }
        //如果是n皇后问题，则limit的低n位全为1
        int limit = n == 32 ? -1 : (1<<n) -1 ;
        return process2(limit,0,0,0);
    }

    /**
     *
     * @param limit 划定了问题的规模
     * @param colLimit 列限制 某位上为1，不能放皇后，0表示可以放皇后
     * @param leftSlopLimit 左斜线限制  某位上为1，不能放皇后，0表示可以放皇后
     * @param rightSlopLimit 右斜线限制 某位上为1，不能放皇后，0表示可以放皇后
     * @return
     */
    private static int process2(int limit, int colLimit, int leftSlopLimit, int rightSlopLimit) {
        //base case
        //说明n皇后放满了
        if(colLimit == limit){
            return 1 ;
        }
        //所有候选皇后的限制都在pos上
        // colLimit | leftSlopLimit | rightSlopLimit 是总限制
        // 但是左侧0是干扰项，取反之后都左侧的0都会变成1
        // 然后再和limit与运算，剩余为1的位置就是可以放皇后的位置
        int pos = limit & (~(colLimit | leftSlopLimit | rightSlopLimit)) ;

        int mostRightOne = 0 ;
        int res = 0 ;
        while(pos >0){
            //提取出pos上最右侧的1
            mostRightOne = pos & (~pos+1);
            pos = pos - mostRightOne ;
            res += process2(limit,
                    colLimit | mostRightOne, //下一行的列限制
                    (leftSlopLimit | mostRightOne) <<1 , //下一行的左斜线限制
                    (rightSlopLimit | mostRightOne) >>> 1); //下一行的右斜线限制，注意是无符号右移
        }
        return res ;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(nQueen(10) + " ,time cost:"+(System.currentTimeMillis()-start));
        start = System.currentTimeMillis() ;
        System.out.println(nQueen2(10) + " ,time cost:"+(System.currentTimeMillis()-start));
    }
}
