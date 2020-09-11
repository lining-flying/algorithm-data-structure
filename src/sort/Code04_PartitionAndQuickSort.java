package sort;

/**
 * 快速排序
 * 最好 O(NlogN)
 * 最坏 O(N^2)
 * 平均 O(NlogN)
 * 额外空间复杂度最好 O(logN)
 */
public class Code04_PartitionAndQuickSort {

    // 荷兰国旗问题划分
    // 每次一arr[R] 作为基准来进行分区
    // 返回值为数组res[2]  其中 res[0] 为等于arr[R]的最左侧下标，res[1]为等于arr[R]的最右侧下标
    private static int[] netherlandFlag(int[] arr,int L,int R){
        if(L>R){
            return new int[]{-1,-1};
        }else if(L == R){
            return new int[]{L,R};
        }

        int less = L-1 ; //<区右边界
        int more = R ; // >区左边界
        int index = L ;

        //arr[R] 作为基准
        while(index < R){ //结束条件
            if(arr[index]  == arr[R]){
                index ++ ; // ==区向右扩
            }else if(arr[index] < arr[R]){
                swap(arr,index++,++less); //交换当前元素和<区的下一位置的元素，<区右扩， 同时index向右移
            }else{
                swap(arr,index,--more); //交换当前元素和>区的左位置的元素，>区左扩 index不变，因为该位置元素还没有比较
            }
        }
        swap(arr,more,R);
        return new int[]{less+1,more};
    }

    private static void swap(int[] arr,int L,int R){
        int temp = arr[L] ;
        arr[L] = arr[R];
        arr[R] = temp ;
    }

    /**
     * 快排2.0版本，考虑了等于target的情况
     * @param arr
     */
    public static void quickSort2(int[] arr){
        if(arr == null || arr.length < 2){
            return ;
        }
        process02(arr,0,arr.length-1);
    }

    private static void process02(int[] arr,int L,int R){
        if(L >= R){
            return ;
        }
        int[] equalArea = netherlandFlag(arr,L,R);
        process02(arr,L,equalArea[0]-1);
        process02(arr,equalArea[1]+1,R);
    }


    /**
     * 快排3.0版本，随机快排，即随机抽取一个元素作为基准
     * @param arr
     */
    public static void quickSort3(int[] arr){
        if(arr == null || arr.length<2){
            return ;
        }

        process03(arr,0,arr.length-1);
    }

    private static void process03(int[] arr, int L, int R) {
        int index = L + (int) (Math.random()*(R-L+1)) ;
        swap(arr,index,R);
        int[] equalArea = netherlandFlag(arr,L,R);
        process03(arr,L,equalArea[0]-1);
        process03(arr,equalArea[1]+1,R);
    }
}
