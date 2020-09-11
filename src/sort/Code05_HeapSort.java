package sort;

/**
 * 堆排序
 * 时间复杂度O(NlogN)
 * 额外空间复杂度O(1)
 */
public class Code05_HeapSort {

    public static void heapSort(int[] array){
        if(array == null || array.length <2){
            return ;
        }

        int len = array.length ;

        //构建大顶堆
        //从上往下构建大顶堆
        //时间复杂度O(NlogN)
        /*for(int i=0;i<len; i++){
            buildBigHeap(array,i);
        }*/

        /**
         * 从下而上构建大顶堆
         * 时间复杂度收敛于O(N)
         */
        for(int i=len-1;i>=0;i--){
            heapify(array,i,len);
        }

        /**
         * 排序：调整堆
         * 时间复杂度O(NlogN)
         */
        for(int i=len-1;i>=0;i--){
            swap(array,0,i);
            heapify(array,0,i);
        }
    }

    private static void heapify(int[] array,int index,int heapSize){
        int leftChildIndex ;
        while((leftChildIndex = index*2+1)<heapSize){
            int biggerChildIndex = leftChildIndex+1>=heapSize || array[leftChildIndex]>=array[leftChildIndex+1] ? leftChildIndex : leftChildIndex+1 ;

            if(array[index] >= array[biggerChildIndex]){
                break;
            }

            swap(array,index,biggerChildIndex);

            index = biggerChildIndex ;
        }
    }

    private static void buildBigHeap(int[] array, int index) {
        int parentIndex ;
        while((parentIndex = (index-1)/2) >= 0 && array[parentIndex] < array[index]){
            swap(array,parentIndex,index);
            index = parentIndex ;
        }
    }

    private static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2] ;
        array[index2] = temp ;
    }

    private static void print(int[] arr){
        System.out.print("[");

        for (int ele : arr){
            System.out.print(ele + "  ");
        }

        System.out.println("]");
    }


    public static void main(String[] args) {
        int[] array = new int[]{3,4,2,8,2,9,7,6,5};

        heapSort(array);

        print(array);
    }
}
