package datastructure.binaryTree;

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
}
