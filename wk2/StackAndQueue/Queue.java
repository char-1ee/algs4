public interface Queue<Item> {

    /**
     * Add an item.
     * @param item
     */
    void enqueue(Item item);

    /**
     * Remove the least recently added item.
     * @return object in generic type parameter
     */
    Item dequeue();

    /**
     * Is the queue empty
     * @return true if empty, false if nonempty
     */
    boolean isEmpty();

    /**
     * @return Number of items in the queue.
     */
    int size();
}