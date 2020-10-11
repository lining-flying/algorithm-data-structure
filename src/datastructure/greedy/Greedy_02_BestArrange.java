package datastructure.greedy;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.Arrays;
import java.util.Comparator;

public class Greedy_02_BestArrange {

    /**
     * 会议安排-暴力解法
     * 全排列
     * @param programs
     * @return
     */
    public static int bestArrange1(int[][] programs){
        if(programs == null || programs.length == 0){
            return 0 ;
        }
        return process01(programs,0,0);
    }

    /**
     * 递归计算剩余的会议中能安排的最大会议树
     * @param programs 剩余的会议
     * @param done 已经完成的会议数
     * @param timeLine 已经完成的会议结束时间
     * @return
     */
    private static int process01(int[][] programs, int done, int timeLine) {
        if(programs.length == 0){
            return done ;
        }

        int max = done ;

        for(int i=0;i<programs.length;i++){
            if(programs[i][0]>=timeLine){
                int[][] newPrograms = copyButExcep(programs,i);
                max = Math.max(max,process01(newPrograms,done+1,programs[i][1]));
            }
        }
        return max ;
    }

    private static int[][] copyButExcep(int[][] programs, int excepIndex) {
        int[][] res = new int[programs.length-1][2];
        int index = 0 ;
        for(int i=0;i<programs.length;i++){
            if(i != excepIndex){
                res[index][0] = programs[i][0];
                res[index][1] = programs[i][1];
                index ++ ;
            }
        }
        return res ;
    }


    /**
     * 最多会议安排 贪心解法
     * 按照会议结束时间升序排列，优先安排结束时间早的
     * @param programs
     * @return
     */
    public static int bestArrange2(int[][] programs){
        if(programs == null || programs.length == 0){
            return 0 ;
        }

        Arrays.sort(programs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int result = 0 ;
        int timeLine = 0 ;
        for(int i=0;i<programs.length;i++){
            if(programs[i][0] >= timeLine){
                result ++ ;
                timeLine = programs[i][1] ;
            }
        }
        return result ;
    }

    public static void main(String[] args) {
        //TODO 对数器
    }

}
