public class StackArrImpl {
    private String[] s;
    private int ptr = 0; // Always pointe to a null elements

    public StackArrImpl() {
        s = new String[1];
    }

    public boolean isEmpty() {
        return ptr == 0;
    }

    public void push(String str) {
        if (ptr == s.length)
            resize(2 * s.length);
        s[ptr++] = str;
    }

    public String pop() {
        String item = s[--ptr];
        s[ptr] = null;
        if (ptr > 0 && ptr == s.length / 4)
            resize(s.length / 2);
        return item;
    }

    private void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < ptr; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }
}
