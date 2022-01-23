package wk4;

import java.util.PriorityQueue;

public class TaxicabNumber implements Comparable<TaxicabNumber> {
    private final int i;
    private final int j;
    private final long sum;

    public TaxicabNumber(int i, int j) {
        this.i = i;
        this.j = j;
        this.sum = (long) i * i * i + (long) j * j * j;
    }

    @Override
    public int compareTo(TaxicabNumber that) {
        if (this.sum < that.sum)
            return -1;
        else if (this.sum > that.sum)
            return +1;
        else if (this.i < that.i)
            return -1;
        else if (this.i > that.i)
            return +1;
        else
            return 0;
    }

    public String toString() {
        return i + "^3 + " + j + "^3";
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);

        PriorityQueue<TaxicabNumber> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++)
            pq.add(new TaxicabNumber(i, i));

        int cnt = 1;
        TaxicabNumber prev = new TaxicabNumber(-1, -1);
        while (!pq.isEmpty()) {
            TaxicabNumber curr = pq.poll();
            if (prev.sum == curr.sum) {
                cnt++;
                if (cnt == 2)
                    System.out.print(prev.sum + " = " + prev);
                System.out.println();
            } else {
                if (cnt > 1)
                    System.out.println();
                cnt = 1;
            }
            prev = curr;
            if (curr.j < N)
                pq.add(new TaxicabNumber(curr.i, curr.j + 1));
        }
        if (cnt > 1)
            System.out.println();
    }
}