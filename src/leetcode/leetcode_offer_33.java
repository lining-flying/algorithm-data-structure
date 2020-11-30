package leetcode;

public class leetcode_offer_33 {

    public static int missingNumber(int[] nums) {
        int left = 0 ;
        int right = nums.length-1 ;
        while(left<right){
            int mid = (left + right) >> 1 ;
            if(mid == nums[mid]){
                left = mid +1 ;
            }else{
                right = mid -1 ;
            }
        }
        return nums[left] == left ? left + 1 : left ;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,2,3};
        System.out.println(missingNumber(nums));
    }
}
