package recursiveanddp;

import javax.xml.stream.events.StartDocument;
import java.util.HashMap;

/**
 * 纸片拼接字符串问题
 */
public class StickerSpellWordSolution {

    /**
     * 暴力递归解法：带记忆化搜索
     * @param target
     * @param arr
     * @return
     */
    public static int minSticker1(String target,String[] arr){
        if(target == null || "".equals(target.trim())){
            return 0 ;
        }
        //每张贴纸对应字母词频统计
        int[][] map = new int[arr.length][26];
        for(int i=0;i<arr.length;i++){
            char[] chs = arr[i].toCharArray() ;
            for(char ch : chs){
                map[i][ch - 'a'] ++ ;
            }
        }

        HashMap<String,Integer> dp = new HashMap<>();
        dp.put("",0);
        return process1(dp,map,target);
    }

    private static int process1(HashMap<String, Integer> dp, int[][] map, String rest) {
        if(dp.containsKey(rest)){
            return dp.get(rest);
        }

        int[] restMap = new int[26] ;
        char[] chs = rest.toCharArray();
        for(char ch : chs){
            restMap[ch - 'a'] ++ ;
        }
        int ans = Integer.MAX_VALUE ;
        //遍历每个贴纸，先使用map[i]时的结果
        for(int i=0;i<map.length;i++){

            //贪心策略：必须包含某一个字符串，来加速运算
           if(map[i][chs[0]-'a'] == 0){
                continue;
            }


            StringBuilder next = new StringBuilder(); //选取当前纸片之后剩余的字符串
            // 枚举26个英文字母
            for(int j=0;j<26;j++){
                //目标字符串中不包含则跳过
                if(restMap[j] == 0){
                    continue;
                }
                for(int k=0;k<Math.max(0,restMap[j]-map[i][j]);k++){
                    next.append((char)('a'+j));
                }
            }
            int temp = process1(dp,map,next.toString());
            if(temp != -1){
                ans = Math.min(ans,temp+1);
            }
        }
        dp.put(rest,ans == Integer.MAX_VALUE ? -1 : ans) ;
        return ans ;
    }
}
