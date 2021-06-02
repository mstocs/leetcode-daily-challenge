package problems.reverse;


import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 2021年05月30日20:28:09
 *
 * 给出一个字符串 s（仅含有小写英文字母和括号）。
 *
 * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
 *
 * 注意，您的结果中 不应 包含任何括号。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseSubstringsBetweenEachPairOfParentheses {
    public String reverseParentheese(String s) {
        Deque<String> stack = new LinkedList<>();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            //当碰到左括号时，向栈中push左侧字符串，并且重新开始记录
            if(ch=='(') {
                stack.push(sb.toString());
                sb.setLength(0);
            } else if(ch == ')') {
                //右括号，首先翻转当前字符串，然后将栈顶字符串插入到翻转字符串前面
                sb.reverse();
                sb.insert(0, stack.pop());
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.reverse();
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();

    }
}
