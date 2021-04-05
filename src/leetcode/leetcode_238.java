package leetcode;

public class leetcode_238 {

    public static int[] productExceptSelf(int[] nums) {
        int len = nums.length ;
        int[] preProduct = new int[len] ;
        int[] postProduct = new int[len] ;

        preProduct[0] = 1 ;
        postProduct[len-1] = 1 ;

        int i = 1 ;
        while(i<=len-1){
            preProduct[i] = preProduct[i-1] * nums[i-1] ;
            postProduct[len-1-i] = postProduct[len-i] * nums[len-i];
            i++ ;
        }

        int[] res = new int[len];
        for(i=0;i<len;i++){
            res[i] = preProduct[i] * postProduct[i] ;
        }
        return res ;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        System.out.println(productExceptSelf(nums));
    }
}
