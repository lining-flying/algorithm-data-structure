package leetcode;

import java.util.Stack;

/**
 * 字符串解码
 */
public class leetcode_394 {
    public static String decodeString(String s) {
        if(s == null || s.length() == 0){
            return s ;
        }

        Stack<String> stack = new Stack<String>();

        StringBuilder sb = new StringBuilder();
        int i=0;
        boolean isNum = false ;
        while(i<s.length()){
            char ch = s.charAt(i++);
            if(ch == '['){
                if(!"".equals(sb.toString())){
                    stack.push(sb.toString());
                    isNum = false;
                    sb = new StringBuilder();
                }
                stack.push(ch + "");
            }else if(ch == ']'){
                if(!"".equals(sb.toString())){
                    stack.push(sb.toString());
                    isNum = false;
                    sb = new StringBuilder();
                }

                String str = "" ;
                while(!stack.isEmpty()){
                    String str1 = stack.pop();
                    if("[".equals(str1)){
                        break;
                    }else{
                        str = str1 + str ;
                    }
                }
                String count = "" ;
                while(!stack.isEmpty()){
                    String str1 = stack.pop();
                    if(str1.charAt(0) <'0' || str1.charAt(0)>'9'){
                        stack.push(str1);
                        break;
                    }else{
                        count = str1 + count ;
                    }
                }
                count = "".equals(count) ? "1" : count ;
                str = buildString(str,Integer.valueOf(count));
                stack.push(str);
            }else if(ch >='0' && ch <= '9'){
                if(!isNum && !"".equals(sb.toString())){
                    stack.push(sb.toString());
                    sb = new StringBuilder();
                }
                sb.append(ch);
                isNum = true ;
            }else if(ch<'0' || ch > '9'){
                if(isNum && !"".equals(sb)){
                    stack.push(sb.toString());
                    sb = new StringBuilder();
                }
                sb.append(ch);
                isNum = false ;
            }
        }

        String res = sb.toString() ;
        while(!stack.isEmpty()) {
            res = stack.pop() + res;
        }
        return res;
    }

    private static String buildString(String str,int count){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<count;i++){
            sb.append(str);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(decodeString("3[a]2[bc]"));
    }
}
