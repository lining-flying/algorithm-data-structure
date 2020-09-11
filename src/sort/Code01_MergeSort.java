package sort;

/**
 * 时间复杂度O(nlogn)
 */
public class Code01_MergeSort {

    //递归实现
    public static void mergeSort(int[] arr){
        process(arr,0,arr.length-1);
    }

    public static void process(int[] arr,int l,int r){
        if(l == r){
            return ;
        }

        int mid = l + ((r-l) >> 1) ;

        process(arr,l,mid);
        process(arr,mid+1,r);
        merge(arr,l,mid,r);
    }

    public static void merge(int[] arr,int l,int m,int r){
        int[] help = new int[r-l+1];
        int index = 0 ;
        int p1 = l,p2 = m+1 ;
        while(p1 <=m && p2 <= r){
            help[index++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++] ;
        }

        while(p1<=m){
            help[index++] = arr[p1++];
        }

        while(p2<=r){
            help[index++] = arr[p2++] ;
        }

        for(int i=0;i<index;i++){
            arr[l+i] = help[i] ;
        }
    }


    public static void mergeSort2(int[] arr){
        if(arr == null || arr.length<2){
            return ;
        }
        int mergeSize = 1 ; //每一组的元素个数
        int N = arr.length ;
        while(mergeSize<N){
            int L = 0 ;
            while(L<N){
                int M = L+mergeSize-1 ;
                if(M>=N){
                    break;
                }
                int R = Math.min(M+mergeSize,N-1);
                merge(arr,L,M,R);
                L = R + 1 ;
            }
            if(mergeSize > (N>>1)){
                break;   //功能上没有影响，实际上是为了防止溢出
            }
            mergeSize <<= 1 ;
        }
    }


    public static void main(String[] args) {

    }
}
