package sort;

/**
 * 桶排序
 */
public class Code06_BucketSort {

    /**
     * 计数排序
     */


    /**
     * 基数排序
     */
    public static void baseSort(int[] array){
        if(array == null || array.length<2){
            return ;
        }

        radixSort(array,0,array.length-1, maxDigit(array));
    }

    private static void radixSort(int[] array, int L, int R, int maxDigit) {
       final int radix = 10 ;
       int i=0,j=0;
       //有多少个数准备多少个辅助空间
       int[] help = new int[R-L+1];;

       for(int d=1;d<=maxDigit;d++){ //有多少位就进出多少次
           //count[i]表示当前位小于等于i的数子由多少个

           int[] count = new int[radix];

           for(i=L;i<=R;i++){
               j = getDigitNum(array[i],d);
               count[j]++ ;
           }

           for(i=1;i<radix;i++){
               count[i] = count[i] + count[i-1];
           }

           //必须逆序处理
           for(i=R; i>=L; i--){
               j = getDigitNum(array[i],d);
               help[count[j]-1] = array[i];
               count[j]-- ;
           }
           //此时help中已经是按照d位排序后的结果了

           for(i=L,j=0;i<=R;i++,j++){
               array[i] = help[j];
           }
       }
    }

    private static int maxDigit(int[] array) {
        int max = array[0] ;
        for(int i=1;i<array.length;i++){
            max = Math.max(max,array[i]);
        }

        return getDigitCount(max);
    }

    private static int getDigitCount(int max) {
        int count = 0 ;

        while(max>0){
            count ++ ;
            max /= 10 ;
        }
        return count ;
    }

    private static int getDigitNum(int num,int digit){
        int result = 0 ;
        while(digit>0){
            if(num == 0){
                return 0 ;
            }
            result = num % 10 ;
            num /= 10 ;
            digit -- ;
        }
        return result ;
    }

    public static void main(String[] args) {
        int[] array = new int[]{101,200,12,11,202,403};
        baseSort(array);
        print(array);

//        System.out.println(getDigitNum(101,2));
    }

    private static void print(int[] arr){
        System.out.print("[");

        for (int ele : arr){
            System.out.print(ele + "  ");
        }

        System.out.println("]");
    }

}
