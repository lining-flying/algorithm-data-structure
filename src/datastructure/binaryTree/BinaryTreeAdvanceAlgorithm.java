package datastructure.binaryTree;

import java.util.*;

/**
 * 二叉树-高级算法
 * 1. 给定二叉树中的一个节点，找到对应的后继节点
 * 2. 给定二叉树中的一个节点，找到对应的前驱节点
 * 3. 打印制片折痕
 */
public class BinaryTreeAdvanceAlgorithm {

    /**
     * 给定二叉树中的一个节点，找到对应的后继节点
     * @param node
     * @return
     */
    public static BinaryTreeNode getSuccessorNode(BinaryTreeNode node){
        if(node == null){
            return null ;
        }
        if(node.getRight() != null){
            return getLeftMost(node.getRight());
        }else{
            //无右子树，向上找到所在子树为父节点的左子树的父节点，或者父节点为null
            BinaryTreeNode temp = node ;
            BinaryTreeNode parent = temp.getParent();
            while(parent != null && parent.getLeft() != temp){
                temp = parent ;
                parent = temp.getParent() ;
            }
            return parent ;
        }
    }

    private static BinaryTreeNode getLeftMost(BinaryTreeNode node) {
        BinaryTreeNode resNode = node;

        while(resNode.getLeft() != null){
            resNode = resNode.getLeft() ;
        }

        return resNode ;
    }

    /**
     * 给定二叉树中的一个节点，找到对应的前驱节点
     * @param node
     * @return
     */
    public static BinaryTreeNode getPrecursorNode(BinaryTreeNode node){
        // TODO
        if(node == null){
            return null ;
        }
        if(node.getLeft() != null){
            return getRightMost(node.getLeft());
        }else{
            BinaryTreeNode temp = node ;
            BinaryTreeNode parent = temp.getParent();
            while(parent != null && parent.getRight() != temp){
                temp = temp.getParent() ;
                parent = temp.getParent() ;
            }
            return parent ;
        }
    }

    private static BinaryTreeNode getRightMost(BinaryTreeNode node) {
        BinaryTreeNode resNode = node ;

        while(node.getRight() != null){
            resNode = resNode.getRight();
        }
        return resNode ;
    }

    /**
     * 纸片折痕打印
     * 思路：折痕实际上以中间折痕上下构成了一个二叉树
     * 上位左子树，下位右子树
     * 左子树根节点都为凹，右子树根节点都为凸
     * 因此可以利用二叉树的特性递归打印
     * @param N
     */
    public static void printPaperFolding(int N){
        printProcess(1,N,true);
    }

    /**
     *
     * @param i 第几次
     * @param n 折痕次数
     * @param down true-凹 ， false-凸
     */
    private static void printProcess(int i, int n, boolean down) {
        if(i>n){
            return ;
        }
        printProcess(i+1,n,true);
        System.out.println(down ? "凹" : "凸");
        printProcess(i+1,n,false);
    }

    /**
     * 判断二叉树是否平衡二叉树——递归实现
     * @param root
     * @return
     */
    public static boolean isBalanced_2(BinaryTreeNode root){
        return isBalancedProcess_2(root).isBalanced ;
    }

    private static Info isBalancedProcess_2(BinaryTreeNode node) {
        if(node == null){
            return new Info(true,0);
        }

        Info leftInfo = isBalancedProcess_2(node.getLeft());
        Info rightInfo = isBalancedProcess_2(node.getRight());

        int height = Math.max(leftInfo.height,rightInfo.height)+1 ;

        boolean isBalanced = true ;
        if(!leftInfo.isBalanced || !rightInfo.isBalanced || Math.abs(leftInfo.height - rightInfo.height) > 1){
            isBalanced = false ;
        }

        return new Info(isBalanced,height);
    }

    static class Info{
        boolean isBalanced ;
        int height ;

        public Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    /**
     * 二叉树的最大距离
     * @param root
     * @return
     */
    public static int maxDistanceOfBinaryTree(BinaryTreeNode root){
        return maxDistanceProcess(root).maxDistance;
    }

    private static Info_MaxDistance maxDistanceProcess(BinaryTreeNode node) {
        if(node == null){
            return new Info_MaxDistance(0,0);
        }

        Info_MaxDistance leftInfo = maxDistanceProcess(node.getLeft());
        Info_MaxDistance rightInfo = maxDistanceProcess(node.getRight());

        int height = Math.max(leftInfo.height,rightInfo.height) + 1 ;
        int maxDistance = Math.max(Math.max(leftInfo.maxDistance,rightInfo.maxDistance),
                leftInfo.height + 1 + rightInfo.height) ;

        return new Info_MaxDistance(height,maxDistance);
    }


    static class Info_MaxDistance{
        int height ;
        int maxDistance ;

        public Info_MaxDistance(int height, int maxDistance) {
            this.height = height;
            this.maxDistance = maxDistance;
        }
    }

    /**
     * 二叉树的最大二叉搜索子树树节点个数
     * @param head
     * @return
     */
    public static int maxSubBSTCount(BinaryTreeNode<Integer> head){
        return maxSubBSTNodeProcess(head).maxSubBSTSize;
    }

    private static Info_MaxSubBSTCount maxSubBSTNodeProcess(BinaryTreeNode<Integer> node){
        if(node == null){
            return null ;
        }

        Info_MaxSubBSTCount leftInfo = maxSubBSTNodeProcess(node.getLeft());
        Info_MaxSubBSTCount rightInfo = maxSubBSTNodeProcess(node.getRight());

        int max = node.getValue() ;
        int min = node.getValue() ;
        if(leftInfo != null){
            max = Math.max(max,leftInfo.max);
            min = Math.min(min,leftInfo.min);
        }
        if(rightInfo != null){
            max = Math.max(max,leftInfo.max);
            min = Math.min(min,leftInfo.min);
        }

        int maxSubBSTSize = 0;
        if(leftInfo != null){
            maxSubBSTSize = leftInfo.maxSubBSTSize ;
        }
        if(rightInfo != null){
            maxSubBSTSize = Math.max(maxSubBSTSize,rightInfo.maxSubBSTSize);
        }

        boolean isAllBST = false;
        if((leftInfo == null || leftInfo.isAllBST) && (rightInfo == null || rightInfo.isAllBST)
            && (leftInfo == null || leftInfo.max < node.getValue()) && (rightInfo == null || rightInfo.min > node.getValue())){
            maxSubBSTSize = 1 + (leftInfo == null ? 0 : leftInfo.maxSubBSTSize) + (rightInfo == null ? 0 : leftInfo.maxSubBSTSize) ;
            isAllBST = true ;
        }

        return new Info_MaxSubBSTCount(isAllBST,maxSubBSTSize,min,max);
    }

    static class Info_MaxSubBSTCount {
        boolean isAllBST ;
        int maxSubBSTSize ;
        int min ;
        int max ;

        public Info_MaxSubBSTCount(boolean isAllBST, int maxSubBSTSize, int min, int max) {
            this.isAllBST = isAllBST;
            this.maxSubBSTSize = maxSubBSTSize;
            this.min = min;
            this.max = max;
        }
    }


    /**
     * 返回二叉树的最大搜索二叉子树的头节点
     * @param head
     * @return
     */
    public static BinaryTreeNode<Integer> maxSubBSTHead(BinaryTreeNode<Integer> head){
        Info_MaxSubBSTHead info =  process_MaxSubBSTHead(head);
        return info == null ? null : info.maxSubBSTHead ;
    }

    private static Info_MaxSubBSTHead process_MaxSubBSTHead(BinaryTreeNode<Integer> node){
        if(node == null){
            return null ;
        }
        Info_MaxSubBSTHead leftInfo = process_MaxSubBSTHead(node.getLeft());
        Info_MaxSubBSTHead rightInfo = process_MaxSubBSTHead(node.getRight());
        int min = node.getValue();
        int max = node.getValue() ;
        int maxSubBSTSize = 0 ;
        BinaryTreeNode<Integer> maxSubBSTHead = null ;
        if(leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
            if (maxSubBSTSize < leftInfo.maxSubBstSize) {
                maxSubBSTSize = leftInfo.maxSubBstSize;
                maxSubBSTHead = leftInfo.maxSubBSTHead;
            }
        }
        if(rightInfo != null){
            min = Math.min(min,rightInfo.min);
            max = Math.max(max,rightInfo.max);
            if (maxSubBSTSize < rightInfo.maxSubBstSize) {
                maxSubBSTSize = rightInfo.maxSubBstSize;
                maxSubBSTHead = rightInfo.maxSubBSTHead;
            }
        }

        if((leftInfo == null || leftInfo.maxSubBSTHead == node.getLeft()) && (rightInfo == null || rightInfo.maxSubBSTHead == node.getRight())){
            maxSubBSTHead = node;
            maxSubBSTSize = 1 + (leftInfo == null ? 0 : leftInfo.maxSubBstSize) + (rightInfo == null ? 0 : rightInfo.maxSubBstSize) ;
        }

        return new Info_MaxSubBSTHead(maxSubBSTHead,maxSubBSTSize,min,max);
    }

    static class Info_MaxSubBSTHead{
        BinaryTreeNode<Integer> maxSubBSTHead;
        int maxSubBstSize ;
        int min ;
        int max ;

        public Info_MaxSubBSTHead(BinaryTreeNode<Integer> maxSubBSTHead, int maxSubBstSize, int min, int max) {
            this.maxSubBSTHead = maxSubBSTHead;
            this.maxSubBstSize = maxSubBstSize;
            this.min = min;
            this.max = max;
        }
    }




    /**
     * 最大幸福值 —— 树形DP
     * 对于每一个节点：只有两种情况 来或者不来
     * 问题转化为求当前节点来或者不来时的最大值
     * 当前节点来时的最大值为子节点不来时的最大值之和+当前节点的幸福值
     * 当前节点不来时的最大值为各子节点的来和不来的最大值之和
     * @param root
     * @return
     */
    public static int maxHappyValue(Employee root){
        Info_MaxHappy info = maxHappyValueProcess(root);
        return Math.max(info.yes,info.no);
    }

    public static Info_MaxHappy maxHappyValueProcess(Employee node){
        if(node.getNexts() == null || node.getNexts().isEmpty()){
            return new Info_MaxHappy(node.getHappy(),0);
        }

        int yes = node.getHappy() ;
        int no = 0 ;

        for (Employee next : node.getNexts()) {
            Info_MaxHappy info = maxHappyValueProcess(next);
            yes += info.no ;
            no += Math.max(info.yes,info.no);
        }

        return new Info_MaxHappy(yes,no);
    }

    static class Info_MaxHappy{
        int yes ; //当前节点员工来时的最大值
        int no ; //当前节点员工不来时的最大值

        public Info_MaxHappy(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }


    /**
     * 判断二叉树是否完全二叉树
     * 非递归实现
     * @param head
     * @param <T>
     * @return
     */
    public static <T> boolean isCBT(BinaryTreeNode<T> head){
        if(head == null){
            return true ;
        }
        Queue<BinaryTreeNode<T>> queue = new LinkedList<>();
        queue.add(head);
        BinaryTreeNode<T> left = null ;
        BinaryTreeNode<T> right = null ;
        boolean flag = false ; //标识某个节点的左右孩子不满
        while(!queue.isEmpty()){
            BinaryTreeNode<T> node = queue.poll() ;
            left = node.getLeft() ;
            right = node.getRight() ;
            if((flag && !(left == null && right == null)) || (node.getLeft() == null && node.getRight() != null)){
                return false ;
            }
            if(left != null){
                queue.add(left) ;
            }
            if(right != null){
                queue.add(right);
            }
            if(left == null || right == null){
                flag = true ;
            }
        }

        return true ;
    }

    /**
     * 是否是完全二叉树
     * 递归实现
     * @param root
     * @param <T>
     * @return
     */
    public static <T> boolean isCBTRecursive(BinaryTreeNode<T> root){
        return process_CBT(root).isCBT ;
    }

    /**
     * 1. 是否是满二叉树
     * 2. 左树是完全二叉树，右树是满二叉树，且左树高度比右树多1
     * 3. 左树是满二叉树，右树是满二叉树，且左树高度比右树高度多1
     * 4. 左树是满二叉树，右树是完全二叉树，且左右树高度相等
     *
     */
    private static <T> Info_CBT process_CBT(BinaryTreeNode<T> node){
        if(node == null){
            return new Info_CBT(true,true,0);
        }

        Info_CBT leftInfo = process_CBT(node.getLeft());
        Info_CBT rightInfo = process_CBT(node.getRight());

        int height = Math.max(leftInfo.height,rightInfo.height)+1 ;
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height ;

        boolean isCBT = false ;
        if(isFull){
            isCBT = true ;
        }else{
            if(leftInfo.isFull ){
                if(rightInfo.isFull && leftInfo.height == rightInfo.height+1){
                    isCBT = true ;
                }else if(rightInfo.isCBT && leftInfo.height == rightInfo.height){
                    isCBT = true ;
                }
            }else if(leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height+1){
                isCBT = true ;
            }
        }
        return new Info_CBT(isFull,isCBT,height);
    }

    static class Info_CBT{
        boolean isFull ;
        boolean isCBT ;
        int height ;

        public Info_CBT(boolean isFull, boolean isCBT, int height) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }

    /**
     * 两个节点的最低公共祖先
     * 非递归实现
     * @param root
     * @param a
     * @param b
     */
    public static BinaryTreeNode lowestAncestor(BinaryTreeNode root,BinaryTreeNode a,BinaryTreeNode b){
        //遍历二叉树
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        Map<BinaryTreeNode,BinaryTreeNode> nodeMap = new HashMap<>();
        while(queue.isEmpty()){
            BinaryTreeNode node = queue.poll();
            if(node.getLeft() != null){
                queue.add(node.getLeft());
                nodeMap.put(node.getLeft(),node);
            }
            if(node.getRight() != null){
                queue.add(node.getRight());
                nodeMap.put(node.getRight(),node);
            }
        }
        //存储a的父节点
        Set<BinaryTreeNode> aParentSet = new HashSet<>();
        aParentSet.add(a);
        BinaryTreeNode node ;
        BinaryTreeNode node1 = a;
        while((node = nodeMap.get(node1)) != null){
            aParentSet.add(node);
            node1 = node ;
        }
        //遍历b的父节点，包含b
        node1 = b ;
        while(node1 != null){
            //如果a的父节点包含了b的父节点，则一定是最低公共祖先
            if(aParentSet.contains(node1)){
                return node1 ;
            }
            node1 = nodeMap.get(node1);
        }
        return null ;
    }

    /**
     * 最低公共祖先
     * 递归实现
     * @param root
     * @param a
     * @param b
     * @return
     */
    public static BinaryTreeNode lowestAncestorRecursive(BinaryTreeNode root,BinaryTreeNode a,BinaryTreeNode b){
        return process_LowestAncestor(root,a,b).ans;
    }

    private static Info_LowestAncestor process_LowestAncestor(BinaryTreeNode node,BinaryTreeNode a,BinaryTreeNode b){
        if(node == null){
            return new Info_LowestAncestor(null,false,false);
        }

        Info_LowestAncestor leftInfo = process_LowestAncestor(node.getLeft(),a,b);
        Info_LowestAncestor rightInfo = process_LowestAncestor(node.getRight(),a,b);

        boolean findA = node == a || leftInfo.findA || rightInfo.findA ;
        boolean findB = node == b || leftInfo.findB || leftInfo.findB ;

        BinaryTreeNode ans = null ;
        if(leftInfo.ans != null){
            ans = leftInfo.ans ;
        }
        if(rightInfo.ans != null){
            ans = rightInfo.ans ;
        }

        if(ans == null && findA && findB){
            ans = node ;
        }
        return new Info_LowestAncestor(ans,findA,findB);
    }

    static class Info_LowestAncestor{
        BinaryTreeNode ans ;
        boolean findA ;
        boolean findB ;

        public Info_LowestAncestor(BinaryTreeNode ans, boolean findA, boolean findB) {
            this.ans = ans;
            this.findA = findA;
            this.findB = findB;
        }
    }
}
