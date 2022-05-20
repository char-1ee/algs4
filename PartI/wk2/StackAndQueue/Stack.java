public interface Stack<Item> {
    
    /**
     * Add an item.
     * @param item
     */
    void push(Item item);

    /**
     * Remove the most recently added item.
     * @return object in generic type
     */
    Item pop();

    /**
     * Is the stack empty?
     */
    boolean isEmpty();

    /**
     * Number of items in the stack.
     */
    int size();
}
