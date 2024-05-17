// https://leetcode.com/problems/baseball-game/description/

class Solution {
    private ArrayList<Integer> stack;

    public Solution() {
        stack = new ArrayList<>();
    }

    public int calPoints(String[] operations) {
        for (String str : operations) {
            if (isInteger(str)) {
                stack.add(Integer.parseInt(str));
            } else if (str.equals("+")) {
                stack.add(stack.get(stack.size() -1) + stack.get(stack.size() -2));
            } else if (str.equals("D")) {
                stack.add(stack.get(stack.size() -1) * 2);
            } else if (str.equals("C")) {
                stack.remove(stack.size() - 1);
            } else {
                System.out.println("Skipping invalid string" + str);
            }
        } 

        int sum = sumList(stack);
        return sum;
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int sumList(List<Integer> list) {
        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        return sum;
    }
}
