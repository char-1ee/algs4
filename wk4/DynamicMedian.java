package wk4;

import java.util.PriorityQueue;

/**
 * Dynamic median.
 * Design a data type that supports:
 * - insert in logarithmic time,
 * - find-the-median in constant time,
 * - remove-the-median in logarithmic time.
 * If the number of keys in the data type is even, find/remove the lower median.
 * 
 */
public class DynamicMedian {

    private static final int PQ_SIZE = 11;

    private PriorityQueue<Integer> maxHeap; // MaxPQ
    private PriorityQueue<Integer> minHeap; // MinPQ

    /** Create an empty medion pq */
    public DynamicMedian() {
        this.maxHeap = new PriorityQueue<>(PQ_SIZE, (o1, o2) -> o2 - o1);
        this.minHeap = new PriorityQueue<>(PQ_SIZE); // natural ordering
    }

    /** Insert an int */
    public void insert(int x) {
        if (isEmpty())
            maxHeap.add(x);
        int median = median();
        if (x > median)
            minHeap.add(x);
        else
            maxHeap.add(x);
        balance();
    }

    /** Return the median */
    public int median() {
        if (isEmpty())
            throw new IllegalStateException("No element in queue.");
        int L = maxHeap.size();
        int R = minHeap.size();
        if (L >= R)
            return maxHeap.peek();
        else
            return minHeap.peek();
    }

    /** Remove the median */
    public boolean remove() {
        if (isEmpty())
            return false;
        int L = maxHeap.size();
        int R = minHeap.size();
        if (L > R)
            maxHeap.poll();
        else
            minHeap.poll();
        balance();
        return true;
    }

    /** Re-balance the heaps */
    private void balance() {
        int L = maxHeap.size();
        int R = minHeap.size();
        if (Math.abs(L - R) > 1) {
            if (L > R) {
                minHeap.add(maxHeap.peek());
                maxHeap.poll();
            } else {
                maxHeap.add(minHeap.peek());
                minHeap.poll();
            }
        }
    }

    /** is the queue empty */
    public boolean isEmpty() {
        return maxHeap.isEmpty() && minHeap.isEmpty();
    }
}
