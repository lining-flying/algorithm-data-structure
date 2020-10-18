package datastructure.graph;

import datastructure.unionfind.UnionSet;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 最小生成树
 */
public class Graph_alg_02_MinimumSpanningTree {

    /**
     * Kruskal算法
     * 利用并查集和队列
     * @param graph
     * @return
     */
    public static Set<Graph.Edge> minimumSpanningTree_Kruskal(Graph graph){
        Graph.Node[] arr = new Graph.Node[graph.getNodes().size()];
        //构造并查集
        UnionSet<Graph.Node> unionSet = new UnionSet(graph.getNodes().values().toArray(arr));

        PriorityQueue<Graph.Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Graph.Edge::getWeight));
        for(Graph.Edge edge : graph.getEdges()){
            priorityQueue.add(edge);
        }

        Set<Graph.Edge> result = new HashSet<>();

        while(!priorityQueue.isEmpty()){
            Graph.Edge edge = priorityQueue.poll();
            if(!unionSet.isSameSet(edge.getFrom(),edge.getTo())){
                result.add(edge);
                unionSet.union(edge.getFrom(),edge.getTo());
            }
        }

        return result ;
    }

    /**
     * Prim算法
     * @param graph
     * @return
     */
    public static Set<Graph.Edge> minimumSpanningTree_Prim(Graph graph){
        Set<Graph.Edge> result = new HashSet<>();

        Set<Graph.Node> nodeSet = new HashSet<>(); //解锁的节点
        Set<Graph.Edge> edgeSet = new HashSet<>(); //解锁的边,没有亦可，不影响结果，但是加入此set可以提升性能

        PriorityQueue<Graph.Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Graph.Edge::getWeight)); //小顶堆，用于排序

        //此循环无非是为了找到一个初始的节点
        for(Graph.Node node : graph.getNodes().values()){
            nodeSet.add(node);
            for(Graph.Edge edge : node.getEdges()){ //由一个点解锁所有相邻的边
                if(!edgeSet.contains(edge)){
                    edgeSet.add(edge);
                    priorityQueue.add(edge);
                }
            }

            while(!priorityQueue.isEmpty()){
                Graph.Edge edge = priorityQueue.poll(); //取出解锁的边中最小的边
                Graph.Node toNode = edge.getTo(); //可能的一个新的点
                if(!nodeSet.contains(toNode)){ //如果to点没有被解锁，则将该边加入的结果集中
                    nodeSet.add(toNode);
                    result.add(edge);
                    for(Graph.Edge nextEdge :toNode.getEdges()){  //解锁toNode点所有相邻的边
                        if(!edgeSet.contains(nextEdge)){
                            edgeSet.add(nextEdge);
                            priorityQueue.add(nextEdge);
                        }
                    }
                }
            }

            break; //去除之后，可以防止森林
        }


        return result ;
    }
}
