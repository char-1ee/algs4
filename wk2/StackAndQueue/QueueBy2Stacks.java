public class QueueBy2Stacks {
    
    private StackLLImpl<Integer> inStack;   // Enqueue
    private StackLLImpl<Integer> outStack;  // Dequeue
    private int size;

    public QueueBy2Stacks() {
        inStack = new StackLLImpl();
        outStack = new StackLLImpl();
    }

    public void enqueue(Integer item) {
        inStack.push(item);
        size++;
    }

    public int dequeue() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }

        int item = 0;
        if (!outStack.isEmpty()) {
            item = outStack.pop();
            size--;
        }
        return item;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
