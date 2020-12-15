package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class leetcode_107 {

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<List<Integer>>();
        if(root == null){
            return res ;
        }
        Queue<TreeNode> queue1 = new LinkedList<TreeNode>();
        Queue<TreeNode> queue2 = new LinkedList<TreeNode>();

        queue1.offer(root);

        while(!queue1.isEmpty() || !queue2.isEmpty()){
            List<Integer> level = new ArrayList<Integer>();
            if(queue1.isEmpty()){
                while(!queue1.isEmpty()){
                    TreeNode node = queue1.poll();
                    level.add(node.val);
                    if(node.left != null){
                        queue2.offer(node.left);
                    }
                    if(node.right != null){
                        queue2.offer(node.right);
                    }
                }
            }else{
                while(!queue2.isEmpty()){
                    TreeNode node = queue2.poll();
                    level.add(node.val);
                    if(node.left != null){
                        queue1.offer(node.left);
                    }
                    if(node.right != null){
                        queue1.offer(node.right);
                    }
                }
            }
            res.addFirst(level);
        }

        return res ;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        levelOrderBottom(node);
    }
}
