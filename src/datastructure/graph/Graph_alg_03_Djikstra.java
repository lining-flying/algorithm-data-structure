package datastructure.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 狄更特斯拉算法
 * 给定一个非负权重边值的图和一个起点
 * 求起点到图中所有点的最小距离
 */
public class Graph_alg_03_Djikstra {

    /**
     *
     * @param from
     * @return key： 图中的节点，value：起点到该节点的距离，key不存在标识距离为正无穷
     */
    public static Map<Graph.Node,Integer> dijkstra01(Graph.Node from){
        Map<Graph.Node,Integer> result = new HashMap();

        Set<Graph.Node> selectedNodes = new HashSet<>();
        result.put(from,0); //from 到 from 距离一定为0
       /* selectedNodes.add(from);
        Graph.Node minNode = from ;*/
        Graph.Node minNode = getMinNodeNotInSelectedNodes(result,selectedNodes); //取得未遍历的距离最小的节点，第一次取出的一定为from自己
        while(minNode != null){
            int distance = result.get(minNode);
            for(Graph.Edge edge : minNode.getEdges()){
                Graph.Node toNode = edge.getTo();
                if(result.containsKey(toNode)){
                    //不经过minNode时已经可达
                    result.put(toNode,Math.min(result.get(toNode),distance + edge.getWeight()));
                }else{
                    //不经过minNode时尚不可达，
                    result.put(toNode,distance + edge.getWeight()) ;
                }
            }
            selectedNodes.add(minNode); //添加至已遍历节点中
            minNode = getMinNodeNotInSelectedNodes(result,selectedNodes);
        }
        return result ;
    }

    private static Graph.Node getMinNodeNotInSelectedNodes(Map<Graph.Node, Integer> distanceMap, Set<Graph.Node> selectedNodes) {
        Graph.Node minNode = null ;
        int minDistance = Integer.MAX_VALUE;
        for(Graph.Node node : distanceMap.keySet()){
            if(selectedNodes.contains(node))
                continue;
            if(minDistance > distanceMap.get(node)){
                minDistance = distanceMap.get(node);
                minNode = node ;
            }
        }

        return minNode ;
    }
}
