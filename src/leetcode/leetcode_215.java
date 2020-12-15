package leetcode;

public class leetcode_215 {
    public int findKthLargest(int[] nums, int k) {
        return process(nums,0,nums.length-1,nums.length-k);
    }

    public int process(int[] nums,int L,int R,int index){
        if(L == R){
            return nums[index] ;
        }

        int pivot = nums[L + (int) (Math.random() * (R-L+1))] ;
        int[] range = paritition(nums,L,R,pivot);

        if(range[0]<=index && range[1]>=index){
            return nums[index] ;
        }else if(range[0] > index){
            return process(nums,L,range[0]-1,index);
        }else{
            return process(nums,range[1]+1,R,index);
        }
    }

    public int[] paritition(int[] nums,int L,int R,int pivot){
        if(L > R){
            return new int[]{-1,-1};
        }else if(L == R){
            return new int[]{L,R};
        }

        int less = L-1 ;
        int more = R+1 ;
        int index = L ;
        while(index<more){
            if(nums[index] == pivot){
                index ++ ;
            }else if(nums[index]<pivot){
                swap(nums,index++,++less);
            }else{
                swap(nums,index,--more);
            }
        }
        return new int[]{less+1,more-1};
    }

    private void swap(int[] nums,int i,int j){
        int temp = nums[i] ;
        nums[i] = nums[j] ;
        nums[j] = temp ;
    }

    public static void main(String[] args) {
        leetcode_215 main = new leetcode_215();
        int[] nums = new int[]{3,2,1,5,6,4};
        System.out.println(main.findKthLargest(nums,2));
    }
}
