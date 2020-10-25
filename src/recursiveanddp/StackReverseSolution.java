package recursiveanddp;

import java.util.Stack;

/**
 * 栈逆序
 * 不申请额外空间
 */
public class StackReverseSolution {

    /**
     * 栈逆序
     * @param stack
     */
    public static void reverse(Stack<Integer> stack){
        if(stack.isEmpty()){
            return ;
        }
        int last = f(stack);
        reverse(stack);
        stack.push(last);
    }

    /**
     * 从栈中去掉并返回栈底元素
     * @param stack
     */
    public static int f(Stack<Integer> stack){
        int r = stack.pop();
        if(!stack.isEmpty()){
            int last = f(stack);
            stack.push(r);
            return last ;
        }
        return r ;
    }



    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(2);
        stack.push(1);
        reverse(stack);

        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
}
