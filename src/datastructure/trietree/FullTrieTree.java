package datastructure.trietree;

import java.util.HashMap;
import java.util.Map;

/**
 * 时间复杂度O(N) N=字符串的长度
 */
public class FullTrieTree implements TrieTree {

    private Node root ;

    public FullTrieTree(){
        root.next = new HashMap<>();
    }

    @Override
    public void insert(String word) {
        if(word == null){
            return ;
        }

        char[] chs = word.toCharArray() ;

        Node node = root ;
        root.pass ++ ;

        int index = 0 ;
        for(char ch : chs){
            index = (int) ch ;
            Node node1 = node.next.get(index) ;
            if(node1 == null){
                node1 = new Node();
                node.next.put(index,node1);
            }
            node1.pass ++;
            node = node1;
        }
        node.end ++ ;
    }

    @Override
    public int search(String word) {
        if(word == null){
            return 0 ;
        }

        char[] chs = word.toCharArray();

        Node node = root ;
        int index ;

        for(char ch : chs){
            index = ch;
            if(node.next.get(ch) == null){
                return  0;
            }
            node = node.next.get(ch);
        }

        return node.end;
    }

    @Override
    public int prefixNumber(String prefix) {
        if(prefix == null){
            return 0 ;
        }

        char[] chs = prefix.toCharArray();

        Node node = root ;
        int index ;

        for(char ch : chs){
            index = ch;
            if(node.next.get(ch) == null){
                return  0;
            }
            node = node.next.get(ch);
        }

        return node.pass;
    }

    @Override
    public void delete(String word) {
        if(search(word) != 0){
            char[] chs = word.toCharArray();
            Node node = root ;
            node.pass -- ;
            int path ;
            for(char ch : chs){
                path = ch ;
                if( -- node.next.get(path).pass == 0){
                    node.next.remove(path);
                    return ;
                }
                node = node.next.get(path) ;
            }
            node.end -- ;
        }
    }


    static class Node{
        private int pass ;
        private int end ;
        private Map<Integer,Node> next ;

        public int getPass() {
            return pass;
        }

        public void setPass(int pass) {
            this.pass = pass;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public Map<Integer, Node> getNext() {
            return next;
        }

        public void setNext(Map<Integer, Node> next) {
            this.next = next;
        }
    }
}
