package org.example;

import java.util.Stack;

public class BalancedParentheses {

    public static boolean checkIsBalanced(String str) {
        Stack<Character> stack = new Stack<>();

        for (char c : str.toCharArray()) {
            // Push to the stack if an open parentheses is found
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false;
                }

                // Pop the stack if a closing parentheses is found
                char top = stack.pop();

                // Verify the correct parentheses is matching
                if ((c == ')' && top != '(') || (c == ']' && top != '[') || (c == '}' && top != '{')) {
                    return false;
                }
            }
        }
        // Final check to see if there are no more elements in the stack
        return stack.isEmpty();
    }
}
