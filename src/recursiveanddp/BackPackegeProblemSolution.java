package recursiveanddp;

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
     * 从左往右的尝试模型
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

}
