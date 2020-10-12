package datastructure.unionfind;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 并查集实现
 * @param <E>
 */
public class UnionSet<E> {

    private Map<E,Node<E>> nodeMap ; // 元素 -> 节点映射
    private Map<Node<E>,Node<E>> parentMap ; //节点父亲关系
    private Map<Node<E>,Integer> sizeMap ; // 集合大小，只有在head节点存在值

    public UnionSet(E[] eles){
        nodeMap = new HashMap<>();
        parentMap = new HashMap<>();
        sizeMap = new HashMap<>();
        for (E ele : eles) {
            Node<E> node = new Node<>(ele);
            nodeMap.put(ele,node);
            parentMap.put(node,node);
            sizeMap.put(node,1);
        }
    }

    private Node<E> findHead(Node<E> node){
        Stack<Node<E>> stack = new Stack<>();
        Node<E> cur = node ;
        while(cur != parentMap.get(cur)){
            stack.push(cur);
            cur = parentMap.get(cur);
        }
        //扁平化操作，将沿途所有节点的parent指向head
        while(!stack.isEmpty()){
            parentMap.put(stack.pop(),cur);
        }
        return cur ;
    }

    public boolean isSameSet(E ele1,E ele2){
        if(!nodeMap.containsKey(ele1) || !nodeMap.containsKey(ele2)){
            return false ;
        }

        return findHead(nodeMap.get(ele1)) == findHead(nodeMap.get(ele2));
    }

    public void union(E ele1,E ele2){
        if(!nodeMap.containsKey(ele1) || !nodeMap.containsKey(ele2)){
            return ;
        }
        Node<E> head1 = findHead(nodeMap.get(ele1));
        Node<E> head2 = findHead(nodeMap.get(ele2));
        if(head1 != head2){
            int size1 = sizeMap.get(head1);
            int size2 = sizeMap.get(head2);
            Node<E> big = size1 >= size2 ? head1 : head2 ;
            Node<E> small = big == head1 ? head2 : head1 ;
            parentMap.put(small,big);
            sizeMap.put(big,size1 + size2) ;
            sizeMap.remove(small) ;
        }
    }

    public Map<E, Node<E>> getNodeMap() {
        return nodeMap;
    }

    public Map<Node<E>, Node<E>> getParentMap() {
        return parentMap;
    }

    public Map<Node<E>, Integer> getSizeMap() {
        return sizeMap;
    }

    public static class Node<E>{
        private E val ;

        public Node(E val) {
            this.val = val;
        }

        public E getVal() {
            return val;
        }

        public void setVal(E val) {
            this.val = val;
        }
    }
}
