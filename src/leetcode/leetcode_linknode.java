package leetcode;

import datastructure.linklist.ListNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class leetcode_linknode {

    static class ListNode{
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * 删除重复链表
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){
            return head ;
        }

        Set<Integer> valDeleted = new HashSet<Integer>(); //已经删除的元素
        Map<Integer,ListNode> valMap = new HashMap<Integer,ListNode>(); //key对应的node
        Map<ListNode,ListNode> prevNodeMap = new HashMap<ListNode,ListNode>(); //node的前一个节点

        ListNode node = head ;
        ListNode prev = null ;

        while(node != null){
            //如果valMap中已经有原来的值了,说明已经重复了
            if(valMap.containsKey(node.val)){
                if(valDeleted.add(node.val)){
                    //首先检查是否和前一个节点重复
                    if(prev.val == node.val){
                        prev = prevNodeMap.get(prev);
                        if(prev == null){
                            head = node.next ;
                        }else{
                            prev.next = node.next ;
                        }
                    }else{
                        ListNode dupNode = valMap.get(node.val);
                        ListNode dupNodePre = prevNodeMap.get(dupNode);
                        if(dupNodePre == null){
                            head = dupNode.next ;
                        }else{
                            dupNodePre.next = dupNode.next ;
                        }
                    }
                }else{
                    //如果已经被删除过
                    if(prev == null){
                        head = node.next ;
                    }else{
                        prev.next = node.next ;
                    }
                }
            }else{
                //val没有重复值
                valMap.put(node.val,node);
                prevNodeMap.put(node,prev);
                prev = node ;
            }
            node = node.next ;
        }

        return head ;
    }


    /**
     * k个一组翻转链表
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        if(k<=1 || head == null){
            return head ;
        }

        ListNode p1 = head ;
        ListNode p2 = head ;
        ListNode nextSegHead = null ;
        ListNode preTail = null ;
        int index = 1 ;
        while(p1 != null && p2 != null){
            if(index == k){
                nextSegHead = p2.next ;
                doReverse(p1,p2);
                //上一段的尾指向这一段的头
                if(preTail == null){
                    head = p2 ;
                }else{
                    preTail.next = p2 ;
                }
                preTail = p1 ;
                //重置下一段
                p1 = nextSegHead ;
                p2 = nextSegHead ;
                index = 1 ;
            }else{
                p2 = p2.next ;
                index ++ ;
            }
        }

        if(preTail != null){
            preTail.next = nextSegHead ;
        }

        return head ;
    }

    private static void doReverse(ListNode p1, ListNode p2) {
        ListNode pre = p1 ;
        ListNode post = pre.next ;

        while(pre != p2){
            ListNode temp = post.next ;
            post.next = pre ;
            pre = post ;
            post = temp ;
        }
    }


    private static void printf(ListNode head){
        ListNode node = head ;
        while(node != null){
            System.out.print(node.val);
            System.out.print("-->");
            node = node.next ;
        }
        System.out.println("null");
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);

//        head = deleteDuplicates(head);
//        head = reverseKGroup(head,2);

        splitListToParts(head,3);

        printf(head);
    }


    public static ListNode[] splitListToParts(ListNode root, int k) {
        int len = 0 ;

        //知道链表长度
        ListNode node = root ;
        while(node != null){
            node = node.next ;
            len++ ;
        }

        int n = len/k ;
        int mod = len%k ;
        ListNode[] result = new ListNode[k];
        node = root ;
        int index = 0 ;
        while(index < k){
            ListNode head = null ;
            ListNode tail = null ;
            int count = 0 ;
            while(node != null && count<n){
                count++ ;
                if(head == null){
                    head = node ;
                    tail = head ;
                }else{
                    tail.next = node ;
                    tail = node ;
                }
                node = node.next ;
            }
            if(mod>0 && node != null){
                if(head == null){
                    head = node ;
                    tail = node ;
                }else{
                    tail.next = node ;
                    tail = tail.next ;
                }
                mod -- ;
                node = node.next ;
            }
            //截断链表
            if(tail != null){
                tail.next = null ;
            }
            result[index++] = head ;
        }
        return result ;
    }
}
