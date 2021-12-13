import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

    private int n; // #nodes in list

    // rather than use nodes first and last, here to use header and
    // tailer to represent that first.prev=header and last.next=tailer
    private final Node header;
    private final Node tailer;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // Construct an empty deque
    public Deque() {
        this.n = 0;
        header = new Node();
        tailer = new Node();
        header.item = null;
        tailer.item = null;
        header.prev = null;
        tailer.next = null;
        header.next = tailer;
        tailer.prev = header;
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
        if (item == null)
            throw new IllegalArgumentException("Add a null item to the back");
        Node oldlast = tailer.prev;
        Node last = new Node();
        last.item = item;
        last.next = tailer;
        last.prev = oldlast;
        oldlast.next = last;
        tailer.prev = last;
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
        Node last = tailer.prev;
        Node beforelast = last.prev;
        Item item = last.item;
        tailer.prev = beforelast;
        beforelast.next = tailer;
        n--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {

        // do not call DequeIterator<Item>(), otherwise Item
        // will be thought as a new generic which is different from
        // Item in Iterator<Item>. Potential casting error.
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        
        // here make current points to header rather than header.first, 
        // because the deque maybe empty in which case header.next=tailer.
        // private Node current = header.next;
        private Node current = header;

        public boolean hasNext() {
            return current.next != tailer;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            current = current.next;
            return current.item;
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
        StdOut.println("Current size " + deque.size());
        StdOut.println("Currently empty? " + deque.isEmpty());

        int first = deque.removeFirst();
        int last = deque.removeLast();
        StdOut.println("Remove the first: " + first);
        StdOut.println("Remove the last: " + last);

        // Test on iterator
        Iterator<Integer> iterator = deque.iterator();
        int item;
        while (iterator.hasNext()) {
            item = iterator.next();
            StdOut.print(item + " ");
        }
    }
}