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
    private int first; // pointer of first element of queue
    private int last; // pointer of last element of queue

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
        first = 0;
        last = 0;
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
        queue[last++] = item;
        if (last == queue.length)
            last = 0;
        n++;
    }

    // resize the array
    private void resize(int capacity) {
        assert capacity >= n;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = queue[(first + i) % queue.length]; // avoid stack overflow
        }
        queue = copy;
        first = 0;
        last = n;
    }

    // remove and return an random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        StdRandom.shuffle(queue);
        Item item = queue[first];
        queue[first] = null;
        n--;
        first++;
        if (first == queue.length)
            first = 0;
        if (n > 0 && n == queue.length / 4)
            resize(queue.length / 2);
        return item;
    }

    // return an random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        int i = StdRandom.uniform(n);
        return queue[i];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item>{
        private int i = 0; // index of array

        public boolean hasNext() {
            return i < n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            StdRandom.shuffle(queue);
            Item item = queue[(i + first) % queue.length];
            i++;
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
