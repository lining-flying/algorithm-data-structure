package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Dota2参议院
 */
public class leetcode_649 {

    public String predictPartyVictory(String senate) {
        int n = senate.length();

        Queue<Integer> radiant = new LinkedList<>();
        Queue<Integer> dire = new LinkedList<>();

        for(int i=0;i<n;i++){
            if(senate.charAt(i) == 'R'){
                radiant.offer(i);
            }else{
                dire.offer(i);
            }
        }

        while(!radiant.isEmpty() && !dire.isEmpty()){
            int radiantIndex = radiant.poll() ;
            int direIndex = dire.poll() ;

            if(radiantIndex<direIndex){
                radiant.offer(radiantIndex + n) ; //+n保证数据不重复
            }else{
                dire.offer(direIndex + n );
            }
        }

        return !radiant.isEmpty() ? "Radiant" : "Dire";
    }
}
