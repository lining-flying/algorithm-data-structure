package datastructure.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 图的简单描述
 */
public class Graph {

    private HashMap<Integer,Node> nodes ;
    private HashSet<Edge> edges ;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }

    public HashMap<Integer, Node> getNodes() {
        return nodes;
    }

    public void setNodes(HashMap<Integer, Node> nodes) {
        this.nodes = nodes;
    }

    public HashSet<Edge> getEdges() {
        return edges;
    }

    public void setEdges(HashSet<Edge> edges) {
        this.edges = edges;
    }

    /**
     * 图的构造（转化）
     * @param matrix 边 int[n][3]  [x][0]-weight,[x][1]-from,[x][2]-to
     * @return
     */
    public static Graph translate(int[][] matrix){
        Graph graph = new Graph();

        for(int i=0;i<matrix.length;i++){
            int weight = matrix[i][0];
            int from = matrix[i][1];
            int to = matrix[i][2];

            if(!graph.nodes.containsKey(from)){
                graph.nodes.put(from,new Node(from));
            }
            if(!graph.nodes.containsKey(to)){
                graph.nodes.put(to,new Node(to));
            }

            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);

            fromNode.addNext(graph.nodes.get(to));
            toNode.addIn();

            Edge edge = new Edge(weight,fromNode,toNode);
            fromNode.addEdge(edge);
            graph.edges.add(edge);
        }

        return graph ;
    }


    public static class Node{
        int value ; //编号
        int out ; //出度
        int in ; //入度
        List<Node> next ; //直接邻居（当前节点出发可抵达的节点）
        List<Edge> edges ; //当前节点出发的边

        public Node(int value) {
            this.value = value;
            this.out = 0 ;
            this.in = 0 ;
            this.next = new ArrayList<>();
            this.edges = new ArrayList<>();
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getOut() {
            return out;
        }

        public void setOut(int out) {
            this.out = out;
        }

        public int getIn() {
            return in;
        }

        public void setIn(int in) {
            this.in = in;
        }

        public List<Node> getNext() {
            return next;
        }

        public void setNext(List<Node> next) {
            this.next = next;
        }

        public List<Edge> getEdges() {
            return edges;
        }

        public void setEdges(List<Edge> edges) {
            this.edges = edges;
        }

        public void addNext(Node node) {
            this.next.add(node);
            this.out ++ ;
        }

        public void addEdge(Edge edge) {
            this.edges.add(edge);
        }

        public void addIn() {
            this.in++ ;
        }
    }

    public static class Edge{
        int weight ;
        Node from ;
        Node to ;

        public Edge(int weight, Node from, Node to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Node getFrom() {
            return from;
        }

        public void setFrom(Node from) {
            this.from = from;
        }

        public Node getTo() {
            return to;
        }

        public void setTo(Node to) {
            this.to = to;
        }
    }
}
