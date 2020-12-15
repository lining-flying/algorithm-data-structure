package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class leetcode_1006 {
    public static int clumsy(int N) {
        int res = N ;
        if(--N>0){
            res *= N ;
        }
        if(--N>0){
            res /= N ;
        }

        if(--N>0){
            res += f(N);
        }
        return res ;
    }

    private static int f(int N){
        int res = N ;
        int temp = 0 ;
        if(--N>0){
            temp = N ;
            if(--N>0){
                temp *= N ;
            }
            if(--N>0){
                temp /= N ;
            }
        }
        res -= temp ;
        if(--N>0){
            res += f(N);
        }
        return res ;
    }

    public static void main(String[] args) {
        System.out.println(clumsy(4));


    }
}
