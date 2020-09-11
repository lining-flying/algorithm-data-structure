package datastructure.trietree;

import java.util.Map;

public class TrieTree26 implements TrieTree{

    private Node root ;

    public TrieTree26() {
        this.root = new Node();
    }

    public void insert(String str){
        if(!validStr(str)){
            return ;
        }

        char[] strs = str.toCharArray() ;

        Node node = root ;
        node.pass ++ ;

        int path = 0 ;
        for(int i=0;i<strs.length;i++){
            path = strs[i] - 'a' ;
            if(node.next[path] == null){
                node.next[path] = new Node();
            }
            node = node.next[path] ;
            node.pass ++ ;
        }

        node.end ++ ;

    }

    /**
     * 所有插入的字符串中插入了几次word
     * @param word
     * @return
     */
    public int search(String word){
        if(!validStr(word)){
            return 0 ;
        }

        char[] strs = word.toCharArray();

        Node node = root ;
        int index ;
        for(char ch : strs){
            index = ch - 'a' ;
            if(node.next[index] == null){
                return 0 ;
            }
            node = node.next[index] ;
        }

        return node.end ;
    }

    /**
     * 所有加入的字符串中有几个时由pre这个字符串为前缀的
     * @param pre
     * @return
     */
    public int prefixNumber(String pre){
        if(!validStr(pre)){
            return 0 ;
        }

        char[] chs = pre.toCharArray() ;

        Node node = root ;
        int index ;

        for(char ch : chs){
            index = ch - 'a' ;
            if(node.next[index] == null){
                return 0 ;
            }
            node = node.next[index] ;
        }
        return node.pass ;
    }

    public void delete(String word){
        if(search(word) != 0){
            char[] chs = word.toCharArray();
            Node node = root ;
            node.pass -- ;
            int path ;
            for(char ch : chs){
                path = ch - 'a';
                if( -- node.next[path].pass == 0){
                    node.next[path] = null ;
                    return ;
                }
                node = node.next[path] ;
            }
            node.end -- ;
        }
    }

    private boolean validStr(String str){
        return str != null && !"".equals(str) ;
    }



    static class Node {
        int pass;
        int end;
        Node[] next;

        public Node() {
            this.next = new Node[26];
        }

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

        public Node[] getNext() {
            return next;
        }

        public void setNext(Node[] next) {
            this.next = next;
        }
    }


}
