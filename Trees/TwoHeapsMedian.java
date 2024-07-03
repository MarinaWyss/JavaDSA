import java.util.PriorityQueue;
import java.util.Collections;

public class TwoHeapsMedian {
    
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    public TwoHeapsMedian() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }

    // Time: O(log n)
    public void insert(int num) {
        // By default, start by inserting into max heap.
        maxHeap.add(num);

        // Ensure the maxHeap always has the smaller half of numbers
        if (!minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
            int val = maxHeap.poll();
            minHeap.add(val);
        }

        // Balance the heaps so that maxHeap can have at most one extra element than minHeap
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    // O(1)
    public double getMedian() {
        // If the number of elements is odd, and maxHeap is larger than 
        // minHeap, median is the max of the maxHeap
        if (maxHeap.size() > minHeap.size()) {
            return (double) maxHeap.peek();
        }
        // If num elements is odd and minHeap is larger than maxHeap,
        // the median is the min of the minHeap
        else if (minHeap.size() > maxHeap.size()) {
            return (double) minHeap.peek();
        }
        // If the num elements is even, the median is the average of the
        // max from maxHeap and min from minHeap
        else {
            return (double) (maxHeap.peek() + minHeap.peek()) / 2;
        }
    }

    public static void main(String[] args) {
        TwoHeapsMedian medianFinder = new TwoHeapsMedian();
        medianFinder.insert(1);
        medianFinder.insert(2);
        System.out.println(medianFinder.getMedian()); // Output: 1.5
        medianFinder.insert(3);
        System.out.println(medianFinder.getMedian()); // Output: 2.0
    }
}
