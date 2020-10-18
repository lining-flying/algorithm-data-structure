package datastructure.graph;

import java.util.*;

/**
 * 图基本算法
 * 深度优先遍历
 * 广度优先遍历（宽度优先遍历）
 */
public class GraphBasicAlgorithm {
    //TODO 实现

    /**
     * 深度优先遍历
     * (1) 先打印节点，并将节点入栈，加入set
     * (2) 如果栈不为空，开始循环
     *  弹出栈顶节点，如果该节点有邻居节点，遍历邻居节点，如果存在任意一个没有遍历过的邻居节点，则将节点再次入栈，并将该邻居节点入栈，加入set，打印该邻居节点，
     *  开始下一次循环
     *  栈里面存储的是遍历到该节点所走过的路径
     * @param node
     */
    public static void dfs(Graph.Node node){
        if(node == null){
            return ;
        }
        Set<Graph.Node> set = new HashSet<>();
        Stack<Graph.Node> stack = new Stack<>();
        stack.add(node);
        set.add(node);
        System.out.println(node.getValue());
        while(!stack.isEmpty()){
            Graph.Node cur = stack.pop();
            if(cur.getNext() != null && !cur.getNext().isEmpty()){
                for(Graph.Node next : cur.getNext()){
                    if(!set.contains(next)){
                        stack.push(cur);
                        stack.push(next);
                        set.add(next);
                        System.out.println(next.getValue());
                        break;
                    }
                }
            }
        }
    }

    /**
     * 图的宽度优先遍历
     * @param node
     */
    public static void bfs(Graph.Node node){
        if(node == null){
            return ;
        }

        Queue<Graph.Node> queue = new LinkedList<>();
        Set<Graph.Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while(!queue.isEmpty()){
            Graph.Node cur = queue.poll();
            System.out.println(cur.getValue());
            if(cur.getNext() != null && !cur.getNext().isEmpty()){
                for(Graph.Node next : cur.getNext()){
                    if(!set.contains(next)){
                        queue.add(next);
                        set.add(next);
                    }
                }
            }
        }
    }
}
