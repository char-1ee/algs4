import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation { // TODO: challenge memory of queue in k
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            queue.enqueue(str);
        }
        for (int i = 0; i < k; i++) {
            String str2 = queue.dequeue();
            StdOut.println(str2);
        }
    }
}
