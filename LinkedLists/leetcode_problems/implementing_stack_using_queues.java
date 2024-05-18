// https://leetcode.com/problems/implement-stack-using-queues/description/

import java.util.LinkedList;
import java.util.Queue;

class MyStack {
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }
    
    public void push(int x) {
        queue1.offer(x);
    }
    
    public int pop() {
        // Move all but last element from queue1 to queue2
        while (queue1.size() > 1) {
            queue2.offer(queue1.poll());
        }
        int val = queue1.poll(); // Get last element in queue1
        swapQueues();
        return val;
    }
    
    public int top() {
        // Move all but last element from queue1 to queue2
        while (queue1.size() > 1) {
            queue2.offer(queue1.poll());
        }
        int val = queue1.peek(); // Peek last element in queue1
        queue2.offer(queue1.poll()); // Move last element to queue2
        swapQueues();
        return val;
    }
    
    public boolean empty() {
        return queue1.size() == 0;
    }

    private void swapQueues() {
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }
}