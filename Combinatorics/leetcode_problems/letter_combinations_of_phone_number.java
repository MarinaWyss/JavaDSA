// https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> combos = new ArrayList<>();

        // Edge cases
        if (digits == null || digits.length() == 0) return combos;

        // Create mapping
        Map<Character, String> mapping = new HashMap<>();
        mapping.put('2', "abc");
        mapping.put('3', "def");
        mapping.put('4', "ghi");
        mapping.put('5', "jkl");
        mapping.put('6', "mno");
        mapping.put('7', "pqrs");
        mapping.put('8', "tuv");
        mapping.put('9', "wxyz");

        // Recursive backtracking
        backtrack(combos, mapping, digits, 0, new StringBuilder());
        return combos;
    }

    public void backtrack(List<String> combos, Map<Character, String> mapping, String digits, int i, StringBuilder curCombo) {
        if (curCombo.length() == digits.length()) {
            combos.add(curCombo.toString());
            return;
        }

        // Get the letters that the current digit maps to, and loop through them
        String letters = mapping.get(digits.charAt(i));
        for (char letter : letters.toCharArray()) {
            // Append current letter to combo
            curCombo.append(letter);
            // Recur with next digit
            backtrack(combos, mapping, digits, i + 1, curCombo);
            // Backtrack by removing the last letter
            curCombo.deleteCharAt(curCombo.length() - 1);
        }

    }
}