package datastructure.binaryTree;

/**
 * Morris遍历及其应用
 */
public class MorrisSolution {

    /**
     * Morris遍历
     * 有左树的节点会访问两次，无左树的节点会访问一次
     * @param root
     */
    public static void morris(BinaryTreeNode root){
        if(root == null){
            return ;
        }

        BinaryTreeNode cur = root ;
        BinaryTreeNode mostRight = null ;

        while(cur != null){
            //判断cur有没有左树
            mostRight = cur.getLeft() ;
            if(mostRight != null) {
                //如果cur有左树，找到左树上的最右侧节点
                while (mostRight.getRight() != null && mostRight.getRight() != cur) {
                    mostRight = mostRight.getRight();
                }
                //此时mostRight一定是cur的左树上的最右侧节点
                //第一次来到最右侧节点，将最右侧节点指向cur
                if (mostRight.getRight() == null) {
                    mostRight.setRight(cur);
                    cur = cur.getLeft();
                    continue;
                } else {
                    //第二次来到最右侧节点，将最右侧节点的right恢复为null
                    mostRight.setRight(null);
                }
            }

            // cur继续向右移动
            cur = cur.getRight() ;
        }
    }

    /**
     * 使用Morris遍历实现中序遍历
     * 第一次遍历到节点时不打印，第二次遍历时打印
     * @param root
     */
    public static void morrisIn(BinaryTreeNode root){
        if(root == null){
            return ;
        }

        BinaryTreeNode cur = root ;
        BinaryTreeNode mostRight = null ;

        while(cur != null){
            //判断cur有没有左树
            mostRight = cur.getLeft() ;
            //如果cur右左树，找到左树上的最右侧节点
            while(mostRight.getRight() != null && mostRight.getRight() != cur){
                mostRight = mostRight.getRight() ;
            }
            //此时mostRight一定是cur的左树上的最右侧节点
            //第一次来到最右侧节点，将最右侧节点指向cur
            if(mostRight.getRight() == null){
                mostRight.setRight(cur);
                cur = cur.getLeft();
                continue;
            }else{
                //第二次来到最右侧节点，将最右侧节点的right恢复为null
                mostRight.setRight(null);
            }

            // cur继续向右移动
            System.out.print(cur.getValue() + " " );
            cur = cur.getRight() ;
        }

        System.out.println();
    }

    /**
     * 使用Morris遍历实现先序遍历
     * 第一次遍历到时打印节点值，第二次不打印
     * @param root
     */
    public static void morrisPre(BinaryTreeNode root){
        if(root == null){
            return ;
        }

        BinaryTreeNode cur = root ;
        BinaryTreeNode mostRight = null ;

        while(cur != null){
            //判断cur有没有左树
            mostRight = cur.getLeft() ;
            //如果cur有左树，找到左树上的最右侧节点
            if(mostRight != null) {
                while (mostRight.getRight() != null && mostRight.getRight() != cur) {
                    mostRight = mostRight.getRight();
                }
                //此时mostRight一定是cur的左树上的最右侧节点
                //第一次来到最右侧节点，将最右侧节点指向cur
                if (mostRight.getRight() == null) {
                    mostRight.setRight(cur);
                    System.out.print(cur.getValue() + " ");
                    cur = cur.getLeft();
                    continue;
                } else {
                    //第二次来到最右侧节点，将最右侧节点的right恢复为null
                    mostRight.setRight(null);
                }
            }else{
                System.out.print(cur.getValue() + " ");
            }

            // cur继续向右移动
            cur = cur.getRight() ;
        }
        System.out.println();
    }

    /**
     * 使用Morris遍历实现后序遍历
     * @param root
     */
    public static void morrisPost(BinaryTreeNode root){
        if(root == null){
            return ;
        }

        BinaryTreeNode cur = root ;
        BinaryTreeNode mostRight = null ;

        while(cur != null){
            //判断cur有没有左树
            mostRight = cur.getLeft() ;
            //如果cur右左树，找到左树上的最右侧节点
            while(mostRight.getRight() != null && mostRight.getRight() != cur){
                mostRight = mostRight.getRight() ;
            }
            //此时mostRight一定是cur的左树上的最右侧节点
            //第一次来到最右侧节点，将最右侧节点指向cur
            if(mostRight.getRight() == null){
                mostRight.setRight(cur);
                cur = cur.getLeft();
                continue;
            }else{
                //第二次来到最右侧节点，将最右侧节点的right恢复为null
                mostRight.setRight(null);
                printEdge(cur.getLeft());
            }

            // cur继续向右移动
            cur = cur.getRight() ;
        }
        printEdge(root);
        System.out.println();
    }

    //逆序打印树的右边界
    private static void printEdge(BinaryTreeNode root) {
        BinaryTreeNode tail = reverseEdge(root);
        BinaryTreeNode cur = tail;
        while (cur != null) {
            System.out.print(cur.getValue() + " ");
            cur = cur.getRight();
        }
        reverseEdge(tail);
    }

    public static BinaryTreeNode reverseEdge(BinaryTreeNode from) {
        BinaryTreeNode pre = null;
        BinaryTreeNode next = null;
        while (from != null) {
            next = from.getRight();
            from.setRight(pre);
            pre = from;
            from = next;
        }
        return pre;
    }


    /**
     * 使用Morris遍历判断一个二叉树是否BST
     * 事件复杂度O(N),空间复杂度O(1)
     * @param root
     * @return
     */
    public static boolean isBSTByMorrisIn(BinaryTreeNode<Integer> root){
        if(root == null){
            return true;
        }

        BinaryTreeNode<Integer> cur = root ;
        BinaryTreeNode<Integer> mostRight = null ;

        Integer pre = null ;
        while(cur != null){
            //判断cur有没有左树
            mostRight = cur.getLeft() ;
            //如果cur有左树，找到左树上的最右侧节点
            if(mostRight != null) {
                while (mostRight.getRight() != null && mostRight.getRight() != cur) {
                    mostRight = mostRight.getRight();
                }
                //此时mostRight一定是cur的左树上的最右侧节点
                //第一次来到最右侧节点，将最右侧节点指向cur
                if (mostRight.getRight() == null) {
                    mostRight.setRight(cur);
                    System.out.print(cur.getValue() + " ");
                    cur = cur.getLeft();
                    continue;
                } else {
                    //第二次来到最右侧节点，将最右侧节点的right恢复为null
                    mostRight.setRight(null);
                }
            }

            if(pre != null && pre>=cur.getValue()){
                return false ;
            }

            pre = cur.getValue() ;

            // cur继续向右移动
            cur = cur.getRight() ;
        }
        return true ;
    }

    /**
     * 求二叉树的最小高度
     * 使用Morris遍历实现
     * @param root
     * @return
     */
    public static int minHeighByMorris(BinaryTreeNode root){
        if(root == null){
            return 0;
        }

        BinaryTreeNode cur = root ;
        BinaryTreeNode mostRight = null ;
        int curLevel = 0 ; //当前所在的层数
        int minHeight = Integer.MAX_VALUE ;

        while(cur != null){
            //判断cur有没有左树
            mostRight = cur.getLeft() ;

            if(mostRight != null) {
                int rightBoardSize = 1 ;
                //如果cur右左树，找到左树上的最右侧节点
                while (mostRight.getRight() != null && mostRight.getRight() != cur) {
                    rightBoardSize ++ ;
                    mostRight = mostRight.getRight();
                }
                //此时mostRight一定是cur的左树上的最右侧节点
                //第一次来到最右侧节点，将最右侧节点指向cur
                if (mostRight.getRight() == null) {
                    //第一次到达时层数加1，
                    curLevel ++ ;
                    mostRight.setRight(cur);
                    cur = cur.getLeft();
                    continue;
                } else {
                    //没有左树时，说明时叶子节点，直接更新一下最小高度
                    if(mostRight.getLeft() == null){
                        minHeight = Math.min(minHeight,curLevel) ;
                    }
                    //第二次到达时所在层数为当前层数减去节点右边界长度
                    curLevel -= rightBoardSize ;
                    //第二次来到最右侧节点，将最右侧节点的right恢复为null
                    mostRight.setRight(null);
                }
            }else{
                //没有左树，只有一次到达，层数直接加一即可
                curLevel ++ ;
            }

            // cur继续向右移动
            cur = cur.getRight() ;
        }

        //树的最右侧节点无法更新，需要判断一下是否叶子节点，如果是，更新一下最小高度
        int finalRight = 1 ;
        cur = root ;

        while(cur.getRight() != null){
            cur = cur.getRight() ;
            finalRight ++ ;
        }

        if(cur.getLeft() == null){
            minHeight = Math.min(minHeight,finalRight);
        }

        return minHeight ;
    }

    /**
     * 是否二叉搜索树
     * 使用Morris遍历判断
     * @param root
     * @return
     */
    public static boolean isBSTByMorris(BinaryTreeNode<Integer> root){
        if(root == null){
            return true ;
        }

        boolean ans = true ;
        Integer pre = null ;

        BinaryTreeNode<Integer> cur = root ;
        BinaryTreeNode<Integer> mostRight = null ;

        while(cur != null){
            mostRight = cur.getLeft() ;

            if(mostRight != null){
                while(mostRight.getRight() != null && mostRight.getRight() != cur){
                    mostRight = mostRight.getRight() ;
                }

                if(mostRight.getRight() == null){
                    mostRight.setRight(cur);
                    cur = cur.getLeft() ;
                    continue;
                }else{
                    mostRight.setRight(null);
                }

                if(pre != null && pre >= cur.getValue()){
                    return false ;
                }
            }

            pre = cur.getValue() ;
            cur = cur.getRight() ;
        }

        return ans ;
    }
}
