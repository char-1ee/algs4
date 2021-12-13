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
    private int last; // pointer of last element of queue

    // construct an empty randomized queue
    public RandomizedQueue() {
        @SuppressWarnings("unchecked") // type item is unknown until runtime
        Item[] q = (Item[]) new Object[INIT_CAPACITY];
        queue = q;
        n = 0;
        last = -1;
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
        queue[++last] = item;
        n++;
    }

    // resize the array
    private void resize(int capacity) {
        assert capacity >= n;
        @SuppressWarnings("unchecked") // type of Item is only known at runtime
        Item[] copy = (Item[]) new Object[capacity];
        int i = 0, j = 0;
        while (i <= last) {
            copy[j++] = queue[i++];
        }
        queue = copy;
        last = j - 1;
    }

    // remove and return an random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        // StdRandom.shuffle(queue); -> linear time -> deprecated
        int i = StdRandom.uniform(n);
        Item item = queue[i];
        queue[i] = queue[last]; // save time compared to in-place substitution of array
        queue[last--] = null;

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

        // the random pointer may points to a null index
        // which belongs to the after-resized enlarged space
        Item item = null;
        while (item == null) { // until item != null
            int i = StdRandom.uniform(n);
            item = queue[i];
        }
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int lastOfCopy;
        private Item[] copy;

        // we have to use a copy of queue to deal with randomized operations
        // otherwise queue without copy will be randomized.
        RandomizedQueueIterator() {
            lastOfCopy = 0;
            @SuppressWarnings("unchecked")
            Item[] temp = (Item[]) new Object[n];
            for (int i = 0; i < n; i++) {
                temp[i] = queue[i];
            }
            copy = temp;
            lastOfCopy = last;
        }

        public boolean hasNext() {
            return lastOfCopy >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            int i = StdRandom.uniform(n);
            Item item = copy[i];
            copy[i] = copy[lastOfCopy];
            copy[lastOfCopy--] = null;
            return item;
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