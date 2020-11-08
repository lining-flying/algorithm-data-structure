package slidewindow;

import java.util.LinkedList;

/**
 * 子数组个数
 * 给定一个数组，求其子数组个数，子数组要求最大值与最小值之差不超过num
 */
public class ValidSubArrayCountSolution {

    /**
     * 解法一： 枚举滑动窗口长度
     * 时间复杂度O(n^2)
     * @param arr
     * @param num
     * @return
     */
    public static int validSubArrayCount(int[] arr,int num){
        if(arr == null || arr.length == 0){
            return 0 ;
        }

        int validCount = 0 ;
        //枚举窗口的长度，从1到len
        for(int w = 1;w<=arr.length;w++){

            LinkedList<Integer> qmax = new LinkedList<>(); //窗口最大值
            LinkedList<Integer> qmin = new LinkedList<>(); //窗口最小值

            //求滑动窗口中所包含子数组的最大值最小值，判断有效性
            for(int R=0;R < arr.length ;R++){

                while(!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]){
                    qmax.pollLast() ;
                }

                while(!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[R]){
                    qmin.pollLast() ;
                }

                qmax.addLast(R);
                qmin.addLast(R);

                if(qmax.peekFirst() == R-w){
                    qmax.pollFirst() ;
                }
                if(qmin.peekFirst() == R-w){
                    qmin.pollFirst() ;
                }

                //长度凑够w时才可能会右结果
                if(R >= w-1){
                    if(arr[qmax.peekFirst()] - arr[qmin.peekFirst()] >= num){
                        validCount ++ ;
                    }
                }
            }


        }

        return validCount ;
    }

    /**
     * 解法二：
     * 如果一个数组[L...R]满足最大值-最小值>=num，则其中的子数组一定满足
     * 思路: 从L出发，找到第一个不满足条件的窗口R，则以arr[L]开头的满足条件的子数组为R-L
     * 更新结果，L++，R继续向右移动，直到找到下一个不满足条件的子数组，依次循环
     * @param arr
     * @param num
     * @return
     */
    public static int validSubArrayCount2(int[] arr,int num){
        if(arr == null || arr.length == 0){
            return 0 ;
        }

        LinkedList<Integer> qmax = new LinkedList<>(); //窗口最大值
        LinkedList<Integer> qmin = new LinkedList<>(); //窗口最小值

        int validCount = 0 ;

        int L = 0,R=0 ;

        while(L<arr.length){

            //L值固定，窗口向右扩直到违规为止
            while(R<arr.length){

                while(!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]){
                    qmax.pollLast() ;
                }

                while(!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[R]){
                    qmin.pollLast() ;
                }

                qmax.addLast(R);
                qmin.addLast(R);

                //找到第一个使得窗口内子数组不满足条件的R，终止循环
                if(arr[qmax.peekFirst()] - arr[qmin.peekFirst()] > num){
                    break;
                }
                R++ ;
            }
            //R-L是以L开头的满足条件的子数组个数
            validCount += (R-L) ;

            if(qmax.peekFirst() == L){
                qmax.pollFirst() ;
            }
            if(qmin.peekFirst() == L){
                qmin.pollFirst() ;
            }
            L++ ;
        }

        return validCount ;
    }
}
