package datastructure.segmentTree;

import java.util.*;

/**
 * 矩形的最大重合个数
 * 解决思路：将矩形先按照从底边从小到大排序
 * 然后批次处理底边相同的矩形，因为矩形重合，则其中最内侧的矩形底边一定能贯穿所有矩形，
 * 则在底边固定的情况下，问题可以转化为线段重合的问题
 */
public class CoverMaxRectangle {

    static class Rectangle{
        int up ;
        int down ;
        int left ;
        int right ;

        public Rectangle(int up, int down, int left, int right) {
            this.up = up;
            this.down = down;
            this.left = left;
            this.right = right;
        }
    }

    static class DownComparator implements Comparator<Rectangle>{

        @Override
        public int compare(Rectangle o1, Rectangle o2) {
            return o1.down - o2.down;
        }
    }

    static class LeftComparator implements Comparator<Rectangle>{

        @Override
        public int compare(Rectangle o1, Rectangle o2) {
            return o1.left - o2.left;
        }
    }

    static class RightComparator implements Comparator<Rectangle>{

        @Override
        public int compare(Rectangle o1, Rectangle o2) {
            return o1.right - o2.right;
        }
    }

    public static int maxCover(Rectangle[] recs){
        if(recs == null || recs.length == 0){
            return 0 ;
        }
        //先根据底边排序
        Arrays.sort(recs,new DownComparator());
        //可能会对当前底边的公共局域，产生影响的矩形
        TreeSet<Rectangle> leftOrdered = new TreeSet<>();

        int ans = 0 ;
        for(int i=0;i<recs.length;i++){ //依次考察每一个矩形的底边
            //对同一个底边的进行批次处理
            do {
                leftOrdered.add(recs[i++]);
            } while (i < recs.length && recs[i].down == recs[i - 1].down);
            //删除顶部小于等于当前矩形底的矩形
            removeUpLowerThanCurDown(leftOrdered,recs[i-1].down);
            //接下来就和线段重合问题一样了
            // 维持了右边界排序的容器
            TreeSet<Rectangle> rightOrdered = new TreeSet<>(new RightComparator());
            for(Rectangle rectangle : leftOrdered){
                removeRightLowerThanCurLeft(rightOrdered,rectangle.left);
                rightOrdered.add(rectangle);
                ans = Math.max(ans,rightOrdered.size());
            }
        }
        return ans ;
    }

    private static void removeRightLowerThanCurLeft(TreeSet<Rectangle> rightOrdered, int left) {
        Iterator<Rectangle> iterator = rightOrdered.iterator();
        while(iterator.hasNext()){
            Rectangle next = iterator.next();
            if(next.right <= left){
                iterator.remove();
            }
        }
    }

    private static void removeUpLowerThanCurDown(TreeSet<Rectangle> leftOrdered, int down) {
        Iterator<Rectangle> iterator = leftOrdered.iterator();
        while(iterator.hasNext()){
            Rectangle next = iterator.next();
            if(next.up<=down){
                iterator.remove();
            }
        }
    }
}
