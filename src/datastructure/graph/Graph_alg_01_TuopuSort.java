package datastructure.graph;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * 拓扑排序
 * 给定一张有向无环图，要求输出其拓扑排序姐u共
 */
public class Graph_alg_01_TuopuSort {

    public static List<Graph.Node> TuopuSort(Graph graph){
        Map<Graph.Node,Integer> inMap = new HashMap<>();
        Queue<Graph.Node> queue = new LinkedList<>();

        // 1. 初始化inMap
        for(Map.Entry<Integer,Graph.Node> entry : graph.getNodes().entrySet()){
            inMap.put(entry.getValue(),entry.getValue().getIn());
            if(entry.getValue().getIn() == 0){
                queue.add(entry.getValue());
            }
        }
        // 2. 遍历队列，出队加入至结果集中
        List<Graph.Node> result = new ArrayList<>();
        while(!queue.isEmpty()){
            Graph.Node node = queue.poll();
            result.add(node);
            // 3. 消除当前入度为0的节点的影响，即将其邻居节点的入度减一
            // 如果入度为0，加入队列
            for(Graph.Node next :node.getNext()){
                inMap.put(next,inMap.get(next)-1);
                if(inMap.get(next) == 0){
                    queue.add(next);
                }
            }
        }

        return result ;
    }

    public static void main(String[] args) {

    }
}
