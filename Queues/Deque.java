import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

    private int n; // #nodes in list

    // rather than use nodes first and last, here to use header and
    // trailer to represent that first.prev=header and last.next=trailer
    private Node header;
    private Node trailer;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // Construct an empty deque
    public Deque() {
        this.n = 0;
        header = new Node();
        trailer = new Node();
        header.item = null;
        trailer.item = null;
        header.prev = null;
        trailer.next = null;
        header.next = trailer;
        trailer.prev = header;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Add a null item to the front");
        Node oldfirst = header.next;
        Node first = new Node();
        first.item = item;
        first.prev = header;
        first.next = oldfirst;
        header.next = first;
        oldfirst.prev = first;
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (Item == null)
            throw new IllegalArgumentException("Add a null item to the back");
        Node oldlast = trailer.prev;
        Node last = new Node();
        last.item = item;
        last.next = trailer;
        last.prev = oldlast;
        oldlast.next = last;
        trailer.prev = last;
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("Remove front but empty");
        Node first = header.next;
        Node nextfirst = first.next;
        Item item = first.item;
        header.next = nextfirst;
        nextfirst.prev = header;
        n--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("Remove back but empty");
        Node last = trailer.prev;
        Node beforelast = last.prev;
        Item item = last.item;
        trailer.prev = beforelast;
        beforelast.next = trailer;
        n--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator<Item>();
    }

    private class DequeIterator<Item> implements Iterator<Item> {
        private Node current = header.next;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing
    public static void main(String[] args) {
        // Initialize
        Deque<Integer> deque = new Deque<Integer>();
        StdOut.println("Current size" + deque.size());
        StdOut.println("Currently empty? " + deque.isEmpty());

        // Test on operations
        while (!StdIn.isEmpty()) {
            int num = StdIn.readInt();
            if (deque.size() % 2 == 0)
                deque.addFirst(num);
            else
                deque.addLast(num);
        }
        StdOut.println("Current size" + deque.size());
        StdOut.println("Currently empty? " + deque.isEmpty());

        int first = deque.removeFirst();
        int last = deque.removeLast();
        StdOut.println("Remove the first: " + first);
        StdOut.println("Remove the last: " + last);

        // Test on iterator
        Deque.DequeIterator<Integer> iterator = deque.iterator();
        int item;
        while (iterator.hasNext()) {
            item = iterator.next();
            StdOut.print(item + " ");
        }
    }
}