# Union Find

## Dynamic connectivity

**Operations.** Given a set of N objects

- Union: connect two object
- Find/connected query: is there a a path connecting the two objects?

**Union-find data type**

![1642653391769.png](image/UnionFind/1642653391769.png)

**Dynamica-connectivity client**

```java
public static void main(String[] args)
{
	int N = StdIn.readInt();
	UF uf = new UF(N);
	while (!StdIn.isEmpty()) {
		int p = StdIn.readInt();
		int q = StdIn.readInt();
		if (!uf.connected(p, q)) {
			uf.union(p, q);
			StdOut.println(p + " " + q);
		}
	}
} 
```

## Quick find

**A eager approach**

![1642653792521.png](image/UnionFind/1642653792521.png)

**Quick-find: Java implementation**

```java
public class QuickFindUF
{
	private int[] id;

	// set id of each object to itself (N array accesses)
	public QuickFindUF(int N) {
		id = new int[N];
		for (int i = 0; i < N; i++)
			id[i] = i;
	}

	// check whether p and q are in the same component (2 arrays accesses)
	public boolean connected(int p, int q) {
		return id[p] == id[q];
	}

	// change all entries with id[p] to id[q] (at most 2N+2 array accesses)
	public void union(int p, int q) {
		int pid = id[p];
		int qid = id[q];
		for (int i = 0; i < id.length; i++)
			if (id[i] == pid) id[i] == qid;
	}
}
```

**Expensive costs**

Quick-find costs $N^2$ array accesses to process a sequence of $N$ union commands on $N$ objects.

## Quick union

**Lazy approach**

![1642654451202.png](image/UnionFind/1642654451202.png)

**Quick-union: Java implementation**

```java
public class QuickUnionUF {
	private int[] id;

	// set id of each object to itself (N array access)
	public QuickFindUF(int N) {
		id = new int[N];
		for (int i = 0; i < N; i++) id[i] = i;
	}

	// chase paretn of pointers until reach root (depth of i array access)
	private int root(int i) {
		while (i != id[i]) i = id[i];
		return i;
	}

	// check if p and q have same root (depth of p and q array access)
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}

	// change root of p to point to root of q (depth of q and p array access)
	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		id[i] = j;
	}
}
```

**Costs summary**

![1642655017840.png](image/UnionFind/1642655017840.png)

## Improvements 1: weighting

![1642655127493.png](image/UnionFind/1642655127493.png)

![1642655227874.png](image/UnionFind/1642655227874.png)![1642655344798.png](image/UnionFind/1642655344798.png)![1642655443457.png](image/UnionFind/1642655443457.png)![1642655474480.png](image/UnionFind/1642655474480.png)

## Improvement 2: path compression

![1642655805417.png](image/UnionFind/1642655805417.png=50x50) ![1642655985650.png](image/UnionFind/1642655985650.png=50x50)

![1642656034532.png](image/UnionFind/1642656034532.png)![1642656164503.png](image/UnionFind/1642656164503.png)![1642656169871.png](image/UnionFind/1642656169871.png)

## Application

Refer to Percolation problem.

![1642656245966.png](image/UnionFind/1642656245966.png)
