// https://leetcode.com/problems/valid-parentheses/

class Solution {

    private ArrayList<Character> stack;

    public Solution() {
        stack = new ArrayList<>();
    }

    public boolean isValid(String s) {
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.add(ch);
            } else if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.size() == 0) {
                    return false;
                }

                char top_element = stack.remove(stack.size() - 1);
                if ((ch == ')' && top_element != '(') ||
                    (ch == '}' && top_element != '{') ||
                    (ch == ']' && top_element != '[')) {
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }
}
