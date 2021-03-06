import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

/**
 * A great explanation on its randomized probability distribution
 * https://stackoverflow.com/questions/12167630/algorithm-for-shuffling-a-linked-list-in-n-log-n-time/27624106#27624106
 */
public class ShuffleLinkedList {    // TODO: merge shuffled linked list
    private class Node {
        int item;
        Node next;
    }

    /**
     * Mergesort shuffle list.
     * Time: O(nlogn); Space: O(1)
     */
    public void shuffle(Node first) {
        if (first == null || first.next == null)
            return;

        Node sublist1 = new Node();
        Node sublist2 = new Node();
        Node ptr = first;
        while (ptr != null) {
            int item = ptr.item;
            Node nextNode = ptr.next;
            sublist1.item = item;
            sublist1.next = nextNode;
            ptr = ptr.next;

            if (ptr != null) {
                int item2 = ptr.item;
                Node nextNode2 = ptr.next;
                sublist2.item = item2;
                sublist2.next = nextNode2;
            }
        }

        shuffle(sublist1);
        shuffle(sublist2);

        if (length(sublist2) < length(sublist1)) {
            Random rand = new Random();
            int i = rand.nextInt(length(sublist2));
            // insert a dummy node into sublist2 at location i
        }

        while (sublist1 != null && sublist2 != null) {
            Random rand = new Random();
            int k = rand.nextInt(2);
            if (k == 1) {
                // move the front node from sublist1 to list
            } else {
                // move the front node from sublist2 to list
            }
        }
        if (sublist1 != null) {
            // append rest of sublist1 to list
        } else if (sublist2 != null) {
            // append rest of sublist2 to list
        }

        // remove the dummy node from list
    }

    private int length(Node first) {
        int cnt = 0;
        Node curr = first;
        while (curr != null) {
            cnt++;
            curr = curr.next;
        }
        return cnt;
    }

    /**
     * Use built-in sorting methods.
     */
    public void builtInDS() {
        LinkedList<Object> list = new LinkedList<>();
        Random rand = new Random();
        Object[] arr = list.toArray();
        int n = arr.length;

        list.add('A');
        list.add('B');
        list.add('C');
        list.add('D');
        list.add('E');
        list.add('F');

        System.out.println("Before shuffled: " + list.toString());

        for (int i = 0; i < n - 1; i++) {
            int j = rand.nextInt(i + 1);
            Object temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }

        ListIterator<Object> lIterator = list.listIterator();
        for (Object o : arr) {
            lIterator.next();
            lIterator.set(o);
        }

        System.out.println("After shuffled: " + list.toString());
    }

    /**
     * Firstly decompose list into array, then sort array and then reform the list.
     * Time: O(n); Space O(n)
     */
    public void shuffleByAux(Node head) {
        if (head == null || head.next == null)
            return;

        Node curr = head;   // an pointer to current header
        int cnt = 0;        // count of node numbers
        while (curr != null) {
            curr = curr.next;
            cnt++;
        }

        // auxiliary array contains nodes' values
        int[] arr = new int[cnt];
        curr = head;

        // extract nodes' value from list into array
        for (int i = 0; curr != null; i++) {
            arr[i] = curr.item;
            curr = curr.next;
        }

        // Fisher-Yates shuffle the array
        Random rand = new Random();
        for (int n = cnt - 1; n > 0; n--) {
            int j = rand.nextInt(n + 1);
            int temp = arr[j];
            arr[j] = arr[n];
            arr[n] = temp;
        }

        // reform the list by the sorted array
        curr = head;
        int i = 0;
        while (curr != null) {
            curr.item = arr[i++];
            curr = curr.next;
        }
    }
}