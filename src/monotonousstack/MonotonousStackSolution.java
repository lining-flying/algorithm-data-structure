package monotonousstack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 单调栈
 */
public class MonotonousStackSolution {

    /**
     * 获取数组中每个元素左右比其小的最近元素的索引下标
     * 数组元素不重复
     * @param arr
     * @return i行表示arr第i个元素的信息，0列表示第i个元素左边比其小的最近的下标，1列表示第i个元素右边比其小的最近的下标， -1表示没有比其小的值
     */
    public static int[][] getNearLessNoRepeat(int[] arr){
        if(arr == null || arr.length == 0){
            return null ;
        }

        int[][] res = new int[arr.length][2];

        LinkedList<Integer> stack = new LinkedList<>();

        for(int i = 0 ;i<arr.length;i++){
            if(stack.isEmpty() || arr[stack.peekFirst()] < arr[i]){
                stack.push(i);
            }else{
                while(!stack.isEmpty() && arr[stack.peekFirst()] < arr[i]){
                    Integer index = stack.pop();
                    res[index][1] = i ;
                    res[index][0] = stack.isEmpty() ? -1 : stack.peekFirst() ;
                }
                stack.push(arr[i]);
            }
        }

        while(!stack.isEmpty()){
            Integer index = stack.pop();
            res[index][1] = -1 ;
            res[index][0] = stack.isEmpty() ? -1 : stack.peekFirst();
        }
        return res ;
    }

    /**
     * 获取数组中每个元素左右比其小的最近元素的索引下标
     * 数组元素允许重复
     * @param arr
     * @return
     */
    public static int[][] getNearLess(int[] arr){
        if(arr == null || arr.length == 0){
            return null ;
        }

        int[][] res = new int[arr.length][2];

        //栈 底 -> 顶， 所表示的元素值 小 -> 大
        LinkedList<List<Integer>> stack = new LinkedList<>();

        for(int i=0;i<=arr.length;i++){
            //栈为空 || 栈顶元素小于待入栈元素， 直接入栈
            if(stack.isEmpty() || arr[stack.peekFirst().get(0)] < arr[i]){
                stack.push(new ArrayList<>(i));
            }else if(arr[stack.peekFirst().get(0)] == arr[i]){
                //栈顶元素和待入栈元素相等时，直接添加至栈顶列表的最后一个位置
                List<Integer> list = stack.pop();
                list.add(i);
                stack.push(list);
            }else{
                //如果栈顶元素比待入栈元素大时，
                while(!stack.isEmpty() && arr[stack.peekFirst().get(0)] < arr[i]){
                    //弹出栈顶元素索引列表
                    List<Integer> list = stack.pop();
                    //取出当前栈顶元素索引列表的最后一个索引下标作为左边最近小的索引
                    int leftLessIndex = stack.isEmpty() ? -1 : stack.peekFirst().get(stack.peekFirst().size()-1);
                    for(int index : list) {
                        res[index][1] = i; //右边最近小的索引
                        res[index][0] = leftLessIndex; //左边最近小的索引
                    }
                }
                //此时栈为空或者栈顶元素表示的值比arr[i]小
                stack.push(new ArrayList<>(i));
            }
        }

        while(!stack.isEmpty()){
            List<Integer> list = stack.pop();
            //取出当前栈顶元素索引列表的最后一个索引下标作为左边最近小的索引
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peekFirst().get(stack.peekFirst().size()-1);
            for(int index : list){
                res[index][1] = -1 ;
                res[index][0] = leftLessIndex;
            }
        }

        return res ;
    }

    public static void main(String[] args) {

    }
}
