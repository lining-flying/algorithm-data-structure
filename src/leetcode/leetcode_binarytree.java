package leetcode;

import sun.plugin.javascript.navig4.Link;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class leetcode_binarytree {

    /**
     * 不同的二叉搜索树
     * 暴力递归解法
     * @param n
     * @return
     */
    public static int numTrees(int n) {
        return process(1,n);
    }

    private static int process(int start,int end) {
        if(start >= end){
            return 1 ;
        }
        int res = 0 ;
        for(int i=start;i<=end;i++){
            res += process(start,i-1) * process(i+1,end);
        }
        return res ;
    }

    /**
     * 动态规划解法
     * @param n
     * @return
     */
    public static int numTrees2(int n){
        int [][] dp = new int[n+2][n+2];

        for(int i=0;i<=n+1;i++){
            for(int j=0;j<=i;j++){
                dp[i][j] = 1 ;
            }
        }

        for(int start = 0;start<=n;start++){
            int s = start ;
            int e = start+1;
            while(s<=n && e<=n) {
                int res = 0 ;
                for(int i=s;i<=e;i++){
                    if(i>=1) {
                        res += dp[s][i - 1] * dp[i + 1][e];
                    }else{
                        res += 1 ;
                    }
                }
                dp[s][e] = res ;
                s++;
                e++;
            }
        }

        return dp[1][n] ;
    }

    private static int getDp(int[][] dp,int i,int j){
        if(i<0){
            return 1;
        }
        if(j>=dp.length){
            return 1 ;
        }
        if(i>=j){
            return 1 ;
        }
        return dp[i][j] ;
    }


    public List<TreeNode> generateTrees(int n) {
        if(n == 0){
            return new LinkedList<>();
        }

        return generateTrees(1,n);
    }

    private List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<>();
        if(start>end){
            allTrees.add(null);
            return allTrees ;
        }

        if(start == end){
            allTrees.add(new TreeNode(start));
            return allTrees ;
        }

        for(int i=start;i<=end;i++){
            List<TreeNode> leftTreeNodes = generateTrees(start,i-1);
            List<TreeNode> rightTreeNodes = generateTrees(i+1,end);

            for(TreeNode left : leftTreeNodes){
                for(TreeNode right : rightTreeNodes){
                    TreeNode root = new TreeNode(i);
                    root.right = right ;
                    root.left = left ;
                    allTrees.add(root);
                }
            }
        }

        return allTrees ;
    }


    public static void main(String[] args) {
        System.out.println(numTrees(3)) ;
        System.out.println(numTrees2(3));

        System.out.println(new ArrayList<Integer>(3));
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
