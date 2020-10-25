package recursiveanddp;

import java.util.LinkedList;

/**
 * 汉诺塔问题
 * N层汉诺塔需要移动的步数一定是2^N-1
 */
public class HanoiTowerSolution {

    /**
     * 暴力递归解法一
     * 问题拆解为子问题
     * @param n
     */
    public static void hanoi_1(int n){
        leftToRight(n);
    }

    private static void leftToRight(int n) {
        if(n == 1){
            System.out.println("move 1 from left to right");
            return ;
        }
        leftToMid(n-1);
        System.out.println("move "+ n + " from left to right");
        midToRight(n-1);
    }

    private static void leftToMid(int n) {
        if(n == 1){
            System.out.println("move 1 from left to mid");
            return ;
        }
        leftToRight(n-1);
        System.out.println("move "+n+" from left to mid");
        rightToMid(n-1);
    }

    private static void rightToMid(int n) {
        if(n == 1){
            System.out.println("move 1 from right to mid");
            return ;
        }
        rightToLeft(n-1);
        System.out.println("move "+n+" from right to mid");
        leftToMid(n-1);
    }

    private static void rightToLeft(int n) {
        if(n == 1){
            System.out.println("move 1 from right  to left");
            return ;
        }
        rightToMid(n-1);
        System.out.println("move "+n+" from right to left");
        midToLeft(n-1);
    }

    private static void midToLeft(int n) {
        if(n == 1){
            System.out.println("move 1 from mid to left");
            return ;
        }
        midToRight(n-1);
        System.out.println("move "+n+" from mid to left");
        rightToLeft(n-1);
    }

    private static void midToRight(int n) {
        if(n == 1){
            System.out.println("move 1 from mid to right");
            return  ;
        }
        midToLeft(n-1);
        System.out.println("move "+n+" from mid to right");
        leftToRight(n-1);
    }


    /**
     * 暴力递归解法二
     * 对解法一的代码优化
     */
    public static void hanoi_2(int n){
        if(n > 0){
            func(n,"left","right","mid");
        }
    }

    private static void func(int n, String from, String to, String other) {
        if(n == 1){
            System.out.println("move 1 from "+from+" to "+to);
        }else{
            //第一步
            func(n-1,from,other,to);
            //第二步
            System.out.println("move "+n+" from "+ from +" to "+to);
            //第三步
            func(n-1,other,to,from);
        }
    }

    static class HanoiRecord {
        private boolean finish1 ; //第一步是否完成
        private int base ;
        private String from ;
        private String to ;
        private String other ;

        public HanoiRecord(int base, String from, String to, String other) {
            this.base = base;
            this.from = from;
            this.to = to;
            this.other = other;
        }
    }

    /**
     * 汉诺塔非递归实现
     * @param n
     */
    public static void hanoi_no_recursive(int n){
        if(n<1){
            return ;
        }
        String pattern = "move %d from %s to %s" ;

        LinkedList<HanoiRecord> stack = new LinkedList<>();
        stack.push(new HanoiRecord(n,"left","right","mid"));

        while(!stack.isEmpty()){
            HanoiRecord cur = stack.pop();

            if(cur.base == 1){
                //直接打印步骤
                System.out.println(String.format(pattern,1,cur.from,cur.to));
                //置父问题的第一步执行结果完成
                if(!stack.isEmpty()) {
                    stack.peek().finish1 = true;
                }
            }else{
                if(cur.finish1){
                    //第一步执行完成，则直接打印步骤
                    System.out.println(String.format(pattern,cur.base,cur.from,cur.to));
                    //将剩余步骤压栈
                    stack.push(new HanoiRecord(cur.base-1,cur.other,cur.to,cur.from));
                }else{
                    //第一步尚未完成，则应继续压栈
                    stack.push(cur);
                    //将第一步子问题压栈
                    stack.push(new HanoiRecord(cur.base-1,cur.from,cur.other,cur.to));
                }
            }
        }
    }




    public static void main(String[] args) {
        int n=3 ;
        hanoi_1(n);
        System.out.println("------------------------");
        hanoi_2(n);
        System.out.println("------------------------");
        hanoi_no_recursive(n);
    }
}
