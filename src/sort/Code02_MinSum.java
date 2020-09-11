package sort;

/**
 * 数组小和： 数组每个元素的左边比当前元素小的元素之和
 * 思路: 通过归并排序，在merge的时候计算
 */
public class Code02_MinSum {
    public static int minSum(int[] arr){
        if(arr == null || arr.length < 2){
            return 0 ;
        }
        return process(arr,0,arr.length-1);
    }

    public static int process(int[] arr,int l,int r){
        if(l == r){
            return 0;
        }

        int sum = 0 ;

        int mid = l + ((r-l) >> 1) ;

        sum += process(arr,l,mid);
        sum += process(arr,mid+1,r);
        sum += merge(arr,l,mid,r);
        return sum ;
    }

    public static int merge(int[] arr,int l,int m,int r){
        int sum = 0 ;
        int[] help = new int[r-l+1];
        int index = 0 ;
        int p1 = l,p2 = m+1 ;
        while(p1 <=m && p2 <= r){
            if(arr[p1] <= arr[p2]){
                sum += arr[p1] * (r-p2+1);
                help[index++] = arr[p1++];
            }else{
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

    //TODO test
    public static void main(String[] args) {

    }
}
