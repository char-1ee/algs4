package wk5;

import edu.princeton.cs.algs4.RedBlackBST;

/**
 * Design a generalized queue data type that supports all of the following
 * operations in logarithmic time (or better) in the worst case.
 * - Create an empty data structure.
 * - Append an item to the end of the queue.
 * - Remove an item from the front of the queue.
 * - Return the ith item in the queue.
 * - Remove the ith item from the queue.
 */
public class GeneralizedQueue<Item> {

    private int index;
    private RedBlackBST<Integer, Item> bst;

    /** Create a empty data structure */
    public GeneralizedQueue() {
        index = 0;
        bst = new RedBlackBST<Integer, Item>();
    }

    /** Append an item to the end of the queue */
    public void enqueue(Item item) {
        bst.put(index++, item);
    }

    /** Remove an item from the front of the queue */
    public void dequeue() {
        bst.deleteMin(); // delete the smallest key and associate value
    }

    /** return the i-th item in the queue */
    public Item query(int i) {

        // a node in a BST has rank k if precisely k other keys in the BST are smaller,
        // each node with rank k will take k-th place.
        int key = bst.rank(i);
        return bst.get(key);
    }

    /** Remove the i-th item from the queue */
    public void remove(int i) {
        bst.delete(bst.rank(i));
    }
}