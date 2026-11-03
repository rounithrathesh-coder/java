/**
 * Daily Java Challenge - Valid Parentheses
 * Topic: Stack Data Structure
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
import java.util.ArrayDeque;
import java.util.Deque;

public class Day003_Valid_Parentheses_Stack {

    public static boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '{') stack.push('}');
            else if (c == '[') stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c) return false;
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String test1 = "()[]{}";
        String test2 = "([)]";
        System.out.println(test1 + " -> " + isValid(test1));
        System.out.println(test2 + " -> " + isValid(test2));
    }
}
