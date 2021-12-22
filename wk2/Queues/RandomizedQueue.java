import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    // initial capacity of default resizing array
    private static final int INIT_CAPACITY = 8;

    private Item[] queue; // queue elements
    private int n; // size of queue items

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (n == queue.length)
            resize(2 * queue.length);
        queue[n++] = item;
    }

    // resize the array
    private void resize(int capacity) {
        assert capacity >= n;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = queue[i];
        }
        queue = copy;
    }

    // remove and return an random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        // StdRandom.shuffle(queue); -> linear time -> deprecated
        int i = StdRandom.uniform(n);
        Item item = queue[i];
        queue[i] = queue[n - 1]; // save time compared to in-place substitution of array
        queue[n - 1] = null;

        if (n > 0 && n == queue.length / 4) {
            resize(queue.length / 2);
        }
        n--;
        return item;
    }

    // return an random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        int i = StdRandom.uniform(n);
        Item item = queue[i];
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    private class RandomizedIterator implements Iterator<Item> {
        private int ptr;
        private int[] randomIndex; // independent itreators

        public RandomizedIterator() {
            ptr = 0;
            for (int i = 0; i < n; i++) {
                randomIndex[i] = i;
            }
            StdRandom.shuffle(randomIndex); // replacement of uniform()
        }

        public boolean hasNext() {
            return ptr != n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return queue[randomIndex[ptr++]];
        }
    }

    // unit testing
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            if (!str.equals("-"))
                queue.enqueue(str);
            else if (!queue.isEmpty())
                StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("Current size: " + queue.size());
        StdOut.println("Current empty? " + queue.isEmpty());

        Iterator<String> t = queue.iterator();
        String str2;
        while (t.hasNext()) {
            str2 = t.next();
            StdOut.print(str2 + " ");
        }
    }
}