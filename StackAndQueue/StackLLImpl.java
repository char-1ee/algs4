import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class StackLLImpl<Item> {
    private Node first = null;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        return item;
    }

    public static void main(String[] args) {
        StackLLImpl stack = new StackLLImpl();
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            if (str.equals("-"))
                StdOut.print(stack.pop());
            else
                stack.push(str);
        }
    }
}