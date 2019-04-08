package array;

/**
 * @author enzo
 * @date 2019/4/5 18:36
 * 腾讯2019春招正式批第二道算法题。给定字符串只包含0、1。遇到01，或者10，消除。最后剩下的字符串长度比如101100
 * 返回0，
 * 这种消除如果限制比较高的话可以用栈来解决，如果像这一题，限制比较随意，可以直接算出1的个数，2的个数，然后相减，得到
 * 正数结果。
 **/

import java.util.Scanner;
import java.util.Stack;

public class ZeroAndOne {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        String s = in.next();
        in.close();
        Stack<Character> stack = new Stack<>();
        char[] c = s.toCharArray();
        if(len != c.length){
            System.out.println("input error");
            System.exit(-1);
        }
        for (int i = 0; i < c.length; i++) {
            if((c[i] != '0' && c[i] != '1')){
                System.out.println("input error");
                System.exit(-1);
            }
            if(stack.isEmpty()){
                stack.push(c[i]);
            }else{
                if(c[i] == stack.peek()){
                    stack.push(c[i]);
                }else{
                    stack.pop();
                }
            }
        }
        System.out.println(stack.size());
    }
}
