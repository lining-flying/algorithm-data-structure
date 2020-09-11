package sort;

/**
 * 数组中的逆序对
 * 思路：归并排序
 */
public class Code03_DecreasePair {

    public static int decreasePairs(int[] arr){
        if(arr == null || arr.length < 2){
            return 0 ;
        }
        return process(arr,0,arr.length);
    }

    public static int process(int[] arr,int l,int r){
        if(l == r){
            return 0;
        }

        int mid = l + ((r-l) >> 1) ;

        return process(arr,l,mid) + process(arr,mid+1,r) + merge(arr,l,mid,r);
    }

    public static int merge(int[] arr,int l,int m,int r){
        int sum = 0 ;
        int[] help = new int[r-l+1];
        int index = 0 ;
        int p1 = l,p2 = m+1 ;
        while(p1 <=m && p2 <= r){
            if(arr[p1] <= arr[p2]){
                help[index++] = arr[p1++];
            }else{
                sum ++ ;
                help[index++] = arr[p2++];
            }
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

        return sum ;
    }
}
