package slidewindow;

import java.util.LinkedList;

/**
 * 滑动窗口内的最大值
 * 题目： 给定数组arr,和窗口大小w，求arr在每个窗口中的最大值
 * 单调栈及滑动窗口
 */
public class WindowMaxSolution {

    public static int[] maxValueOfWindow(int[] arr,int w){
        if(arr == null || arr.length == 0 || w < 0){
            return null ;
        }

        if(w>arr.length){
            w = arr.length ;
        }

        LinkedList<Integer> qmax = new LinkedList<>();

        int[] res = new int[arr.length - w +1];
        int index = 0 ;


        for(int R = 0 ; R < arr.length ;R++){
            //如果队尾的值不比当前值大，则需要从队尾弹出
            while(!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]){
                qmax.pollLast() ;
            }

            qmax.addLast(R);

            //只有当L==R-w时才弹出
            //维护滑动窗口
            if(qmax.peekFirst() == R-w){
                qmax.pollFirst() ;
            }

            //只有R = w-1时，窗口才第一次形成
            if(R >= w-1) {
                res[index++] = arr[qmax.peekFirst()];
            }
        }

        return res ;
    }

    public static void main(String[] args) {
        int[] arr = {3,4,2,6,1,5,8,3};
        int w = 3 ;

        int[] res = maxValueOfWindow(arr,w);

        for(int r : res){
            System.out.println(r);
        }
    }
}
