import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();

        // amazing solution for bonus problem
        // refer to https://www.cnblogs.com/lidunot-fear/p/8025840.html
        for (int i = 0; i < k; i++) {
            queue.enqueue(StdIn.readString());
        }
        int n = k;
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            n++;
            if (StdRandom.uniform(n) < k) {
                queue.dequeue();
                queue.enqueue(str);
            }
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }
    }
}