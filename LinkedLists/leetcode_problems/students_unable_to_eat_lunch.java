// https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/description/

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
        Queue<Integer> studentQueue = new LinkedList<>();
        Queue<Integer> sandwichStack = new LinkedList<>();
        
        for (int student : students) {
            studentQueue.add(student);
        }
        for (int sandwich : sandwiches) {
            sandwichStack.add(sandwich);
        }
        
        int count = 0;

        while (!sandwichStack.isEmpty()) {
            if (studentQueue.peek().equals(sandwichStack.peek())) {
                sandwichStack.poll();
                studentQueue.poll();
                count = 0;
            }
            else {
                studentQueue.offer(studentQueue.poll());
                count++;
            }
            if (count == studentQueue.size()) {
                break;
            }
        }
        return studentQueue.size();
    }
}