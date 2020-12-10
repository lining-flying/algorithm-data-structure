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
    private static int[] partition(int[] arr, int L, int R){
        if(L>R){
            return new int[]{-1,-1};
        }else if(L == R){
            return new int[]{L,R};
        }

        int less = L-1 ; //<区右边界
        int more = R ; // >区左边界
        int index = L ;

        //arr[R] 作为基准
        while(index < more){ //结束条件
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



    // 荷兰国旗问题划分
    // 每次一arr[R] 作为基准来进行分区
    // 返回值为数组res[2]  其中 res[0] 为等于arr[R]的最左侧下标，res[1]为等于arr[R]的最右侧下标
    private static int[] partition2(int[] arr, int L, int R,int pivot){
        if(L>R){
            return new int[]{-1,-1};
        }else if(L == R){
            return new int[]{L,R};
        }

        int less = L-1 ; //<区右边界
        int more = R+1 ; // >区左边界
        int index = L ;

        //arr[R] 作为基准
        while(index < more){ //结束条件
            if(arr[index]  == pivot){
                index ++ ; // ==区向右扩
            }else if(arr[index] < pivot){
                swap(arr,index++,++less); //交换当前元素和<区的下一位置的元素，<区右扩， 同时index向右移
            }else{
                swap(arr,index,--more); //交换当前元素和>区的左位置的元素，>区左扩 index不变，因为该位置元素还没有比较
            }
        }
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
        int[] equalArea = partition(arr,L,R);
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
        //随机抽一个数字跟R-1位置做交换
        int index = L + (int) (Math.random()*(R-L+1)) ;
        swap(arr,index,R);
        int[] equalArea = partition(arr,L,R);
        process03(arr,L,equalArea[0]-1);
        process03(arr,equalArea[1]+1,R);
    }


    /**
     * top k问题 或者 bottom k文件
     * @param arr
     * @param k
     * @return
     */
    public static int findKthNum(int[] arr,int k){
        return process04(arr,0,arr.length-1,k-1);
    }

    private static int process04(int[] arr, int L, int R, int index) {
        if(L == R){
            return arr[index] ;
        }

        int pivot = L + (int) (Math.random() * (R-L+1));
        swap(arr,pivot,R);

        int[] range = partition(arr,L,R);

        if(range[0]<=index && range[1]>=index){
            return arr[index];
        }else if(range[0]>index){
            return process04(arr,L,range[0]-1,index);
        }else{
            return process04(arr,range[1]+1,R,index);
        }
    }

    /**
     * top k问题 或者 bottom k问题
     * 使用bfprt算法
     * @param arr
     * @param k
     * @return
     */
    public static int findKthNum2(int[] arr,int k){
        int[] array = copyArray(arr);
        return bfprt(array,0,array.length-1,k-1);
    }

    //bfprt算法
    private static int bfprt(int[] array, int left, int right, int index) {
        if(left == right){
            return array[index] ;
        }
        //先根据bfprt找到中间位置
        int pivot = medianOfMedians(array,left,right);
        int[] range = partition(array,left,right);
        if(range[0]<=index && range[1]>=index){
            return array[index] ;
        }else if(range[0]>index){
            return bfprt(array,left,range[0]-1,index);
        }else{
            return bfprt(array,range[1]+1,right,index);
        }
    }

    //（1） 先对array[left ... right]5个一组，求取每组的中位数，组成数组marray
    // （2） 再对marray取出中位数返回
    private static int medianOfMedians(int[] array, int left, int right) {
        int size = right - left +1 ;
        int offset = size % 5 == 0 ? 0 : 1 ;
        int[] marray = new int[size / 5 + offset];

        int index = 0 ;
        for(int i=left;i<=right;i=i+5){
            int end = i+4 ;
            marray[index++] = getMedian(array,i,Math.min(end,right));
        }
        //找到中位数
        return bfprt(marray, 0, marray.length - 1, marray.length / 2);
    }

    private static int getMedian(int[] array, int left, int right) {
        insertSort(array,left,right);
        return array[(left+right)/2];
    }

    //插入排序
    private static void insertSort(int[] array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            for (int j = i - 1; j >= left && array[j] > array[j + 1]; j--) {
                swap(array, j, j + 1);
            }
        }
    }

    private static int[] copyArray(int[] arr) {
        int[] res = new int[arr.length];
        for(int i=0;i<arr.length;i++){
            res[i] = arr[i];
        }
        return res ;
    }

    public static void main(String[] args) {
        int[] array = new int[]{2,4,5,8,3,1,7,6};
        int k = 3 ;

//        int[] range = partition(array,0,array.length-1);
//        System.out.println(range[0]);
//        System.out.println(range[1]);
        System.out.println(findKthNum(array,k));
        System.out.println(findKthNum2(array,k));
    }
}
