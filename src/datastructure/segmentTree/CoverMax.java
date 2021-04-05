package datastructure.segmentTree;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 最大线段匹配个数
 */
public class CoverMax {

    static class Line{
        int start ;
        int end ;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int cover(int[][] m){
        Line[] lines = new Line[m.length];
        for(int i=0;i<m.length;i++){
            lines[i] = new Line(m[i][0],m[i][1]);
        }
        Arrays.sort(lines, new Comparator<Line>() {
            @Override
            public int compare(Line o1, Line o2) {
                return o1.start - o2.start;
            }
        });

        PriorityQueue<Line> heap = new PriorityQueue<>(new Comparator<Line>() {
            @Override
            public int compare(Line o1, Line o2) {
                return o1.end - o2.end;
            }
        });

        int max = 0 ;

        for(Line line : lines){
            while(!heap.isEmpty() && heap.peek().end<=line.start){
                heap.poll() ;
            }
            heap.add(line);
            max = Math.max(max,heap.size());
        }

        return max ;
    }
}
