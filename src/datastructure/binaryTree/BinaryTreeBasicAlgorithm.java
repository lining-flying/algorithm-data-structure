package datastructure.binaryTree;

import sun.awt.image.ImageWatched;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 二叉树的基本操作
 * 1. 先序遍历 中序遍历 后序遍历 层序遍历 （非递归实现）
 * 2. 二叉树的层序遍历 序列化和反序列化
 * 3. 二叉树的最大宽度（使用map和不使用map）
 */
public class BinaryTreeBasicAlgorithm {

    /**
     * 二叉树的前序遍历-非递归实现
     * @param root
     * @param <T>
     */
    public static <T> void preNoRecursive(BinaryTreeNode<T> root){
        if(root == null){
            return ;
        }

        System.out.println("pre-order:");

        BinaryTreeNode<T> node = root ;

        LinkedList<BinaryTreeNode<T>> stack = new LinkedList<>();
        stack.push(node);
        while(!stack.isEmpty()){
            // 弹出打印
            node = stack.pop();
            System.out.print(node.getValue() + "  ");
            // 先压右孩子
            if(node.getRight() != null){
                stack.push(node.getRight());
            }
            // 再压左孩子
            if(node.getLeft() != null){
                stack.push(node.getLeft());
            }
        }

        System.out.println();
    }

    /**
     * 二叉树的后序遍历-非递归实现
     * 方法一
     * @param root
     * @param <T>
     */
    public static <T> void  postNoRecursive_1(BinaryTreeNode<T> root){
        if(root == null){
            return ;
        }

        System.out.println("post-order");

        BinaryTreeNode<T> node ;

        LinkedList<BinaryTreeNode<T>> stack = new LinkedList<>();
        LinkedList<T> valueStack = new LinkedList<>();
        stack.push(root);
        while(!stack.isEmpty()){
            // 弹出不打印
            node = stack.pop();
            valueStack.push(node.getValue());
            // 先压左孩子
            if(node.getLeft() != null){
                stack.push(node.getLeft());
            }
            // 再压右孩子
            if(node.getRight() != null){
                stack.push(node.getRight());
            }
        }

        while(!valueStack.isEmpty()){
            System.out.print(valueStack.pop());
        }
        System.out.println();
    }

    /**
     * 二叉树的后序遍历-非递归实现
     * 方法二： 只用一个栈
     * @param root
     * @param <T>
     */
    public static <T> void postNoRecursive_2(BinaryTreeNode<T> root){
        if(root == null){
            return ;
        }

        System.out.println("post-order");

        BinaryTreeNode<T> node = root ;
        BinaryTreeNode<T> c = null ;

        LinkedList<BinaryTreeNode<T>> stack = new LinkedList<>();
        stack.push(node);

        while(!stack.isEmpty()){
            c = stack.peek();
            if(c.getLeft() != null && node != c.getLeft() && node != c.getRight()){
                //左树还没有处理完，处理左树
                stack.push(c.getLeft());
            }else if(c.getRight() != null && node != c.getRight()){
                stack.push(c.getRight());
                //右树还没有处理完，处理右树
            }else{
                //左右树都处理完了，打印当前子树的根节点元素
                System.out.println(stack.pop().getValue() + " ");
                node = c ;
            }
        }
        System.out.println();
    }

    /**
     * 二叉树的中序遍历-非递归实现
     * @param root
     * @param <T>
     */
    public static <T> void inNoRecursive(BinaryTreeNode<T> root){
        if(root == null){
            return ;
        }

        System.out.println("in-order:");

        /**
         * 先压左边界，再弹出打印，然后再对右子树执行相同操作
         */

        LinkedList<BinaryTreeNode<T>> stack = new LinkedList<>();

        BinaryTreeNode<T> node = root ;
        while(!stack.isEmpty() || node != null){
            if(node != null){
                stack.push(node);
                node = node.getLeft();
            }else{
                //弹出打印
                node = stack.pop();
                System.out.print(node.getValue());
                node = node.getRight();
            }
        }
        System.out.println();
    }

    /**
     * 二叉树的层序遍历 - 非递归实现
     * 其实就是树的宽度优先遍历，使用队列
     * @param root
     * @param <T>
     */
    public static <T> void levelNoRecursive(BinaryTreeNode<T> root){
        BinaryTreeNode<T> node = root ;
        if(node != null){
            LinkedList<BinaryTreeNode<T>> queue = new LinkedList<>();
            queue.add(node);
            while(!queue.isEmpty()){
                BinaryTreeNode<T> cur = queue.poll();
                System.out.print(cur.getValue() + " ");
                if(cur.getLeft() != null){
                    queue.add(cur.getLeft());
                }
                if(cur.getRight() != null){
                    queue.add(cur.getRight());
                }
            }
            System.out.println();
        }
    }


    /**
     * 求二叉树的最大宽度 --- 每一层的最大节点个数
     * @param root
     * @return
     */
    public static int maxWidthUseMap(BinaryTreeNode root){
        if(root == null){
            return 0 ;
        }

        LinkedList<BinaryTreeNode> queue = new LinkedList<>();

        Map<BinaryTreeNode,Integer> levelMap = new HashMap<>();
        BinaryTreeNode node = root ;
        levelMap.put(node,1);
        queue.add(node);
        int curLevel = 1 ; //当前正在统计哪一层
        int curLevelNodes = 0 ; //当前层的节点个数
        int max = 0 ;

        while(!queue.isEmpty()){
            BinaryTreeNode cur = queue.poll() ;
            int curNodeLevel = levelMap.get(cur);
            if(cur.getLeft() != null){
                levelMap.put(cur.getLeft(),curNodeLevel + 1);
                queue.add(cur.getLeft());
            }
            if(cur.getRight() != null){
                levelMap.put(cur.getRight(),curNodeLevel + 1) ;
                queue.add(cur.getLeft());
            }
            //如果是当前层，计数+1
            if(curNodeLevel == curLevel){
                curLevelNodes ++ ;
            }else{
                //说明是新的层了，计算截至当前层的最大宽度值
                max = Math.max(curLevelNodes,max);
                //层数加1
                curLevel ++ ;
                //重置当前层节点数为1
                curLevelNodes = 1 ;
            }
        }
        //不要忘记最后一层的统计值和当前最大值比较取最大值
        max = Math.max(max,curLevelNodes);

        return max ;
    }

    /**
     * 求二叉树的最大宽度 --- 每一层的最大节点个数
     * @param root
     * @return
     */
    public static int maxWidthUseNoMap(BinaryTreeNode root){
        if(root == null){
            return 0 ;
        }

        int max = 0 ;

        LinkedList<BinaryTreeNode> queue = new LinkedList<>();

        BinaryTreeNode node = root ;
        queue.add(node) ;
        BinaryTreeNode curEnd = node ; //当前层的最右节点
        BinaryTreeNode nextEnd = null ; //下一层的最右节点
        int curLevelNodes = 0 ; //当前层的节点数

        while(!queue.isEmpty()){
            BinaryTreeNode cur = queue.poll();

            curLevelNodes ++ ;

            if(cur.getLeft() != null){
                queue.add(cur.getLeft());
                nextEnd = cur.getLeft();
            }

            if(cur.getRight() != null){
                queue.add(cur.getRight());
                nextEnd = cur.getRight() ;
            }

            if(cur == curEnd){
                //节点层序遍历至当前层的最右侧节点
                //计算截至当前层的最大宽度
                max = Math.max(max,curLevelNodes);
                //重置当前层的最右节点
                curEnd = nextEnd ;
                //重置当前层的节点数
                curLevelNodes = 0 ;
            }
        }
        return max ;
    }

    /**
     * 二叉树的层序序列化
     * @param root
     * @return
     */
    public static Queue<String> levelSerial(BinaryTreeNode<String> root){
        if(root == null){
            return null ;
        }

        Queue<String> serialQueue = new LinkedList<>();
        Queue<BinaryTreeNode<String>> queue = new LinkedList<>();
        BinaryTreeNode<String> node = root ;
        queue.add(node);
        serialQueue.add(node.getValue());
        while(!queue.isEmpty()){
            BinaryTreeNode<String> cur = queue.poll();

            if(cur.getLeft() != null){
                queue.add(cur.getLeft());
                serialQueue.add(cur.getLeft().getValue());
            }else{
                serialQueue.add(null);
            }

            if(cur.getRight() != null){
                queue.add(cur.getRight());
                serialQueue.add(cur.getRight().getValue());
            }else{
                serialQueue.add(null);
            }
        }

        return serialQueue ;
    }

    /**
     * 二叉树的层序反序列化
     * @param serialQueue
     * @return
     */
    public static BinaryTreeNode<String> levelParse(Queue<String> serialQueue){
        if(serialQueue == null || serialQueue.isEmpty()){
            return null ;
        }

        Queue<BinaryTreeNode<String>> queue = new LinkedList<>();

        BinaryTreeNode head = generateNode(serialQueue.poll());
        queue.add(head);

        while(!queue.isEmpty()){
            BinaryTreeNode<String> cur = queue.poll() ;
            cur.setLeft(generateNode(serialQueue.poll()));
            cur.setRight(generateNode(serialQueue.poll()));

            if(cur.getLeft() != null){
                queue.add(cur.getLeft());
            }

            if(cur.getRight() != null){
                queue.add(cur.getRight());
            }
        }

        return head ;
    }

    private static BinaryTreeNode<String> generateNode(String val){
        if(val == null){
            return null ;
        }
        return new BinaryTreeNode<>(val);
    }

    public static void main(String[] args) {

    }
}
