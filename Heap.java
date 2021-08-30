/*

	Heap:
		An array visualized as a nearly complete binary tree.

	Heap as a Tree:
		Root of tree: first element (i = 1)
		parent(i) : i/2
		left(i) : 2i
		right(i) : 2i + 1

	Max-Heap:
		property: The key of a node is >= they key of its children, for all nodes

	Min-Heap:
		property: The key of a node is <= the key of its children, for all nodes

	Heap Operations:
		build_max_heap: Produces a max heap from an unordered array.
			O(n)
			for i = n/2 downto 1
				do max_heapify(i)
			This starts at n/2 since (n/2 + 1 .... n) are all leafs.

			Observe max_heapify takes O(1) for nodes that are one level above leaves and in general
			O(L) time for nodes that are L levels above the leaves.
			n/4 nodes with level 1, n/8 with level 3, ..., 1 node at log(n) level.
			Asymptotically this converges to < 3 (bounded by a constant). This gives you O(n).


		max_heapify: Correct a single violation of the heap property in a subtrees root.
			O(log n)
			Pre-condition: Assume the left and right subtrees are already max-heaps, just fix root.

		
		heap_sort:
			1. build_max_heap from unordered array
			2. find max element A[1]
			3. swap elements A[n] with A[1] (max element is at end of array)
			4. discard node n from heap (decrement heap size)
			5. new root may violate max_heapify but children are max-heaps, so call max_heapify on
				new root.
			There are n steps of doing max_heapify, so the runtime is O(n log n).
*/

// Java program to implement Max Heap
public class MaxHeap {
    private int[] Heap;
    private int size;
    private int maxsize;
  
    // Constructor to initialize an
    // empty max heap with given maximum
    // capacity.
    public MaxHeap(int maxsize)
    {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new int[this.maxsize + 1];
        Heap[0] = Integer.MAX_VALUE;
    }
  
    // Returns position of parent
    private int parent(int pos) { return pos / 2; }
  
    // Below two functions return left and
    // right children.
    private int leftChild(int pos) { return (2 * pos); }
    private int rightChild(int pos)
    {
        return (2 * pos) + 1;
    }
  
    // Returns true of given node is leaf
    private boolean isLeaf(int pos)
    {
        if (pos > (size / 2) && pos <= size) {
            return true;
        }
        return false;
    }
  
    private void swap(int fpos, int spos)
    {
        int tmp;
        tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }
  
    // A recursive function to max heapify the given
    // subtree. This function assumes that the left and
    // right subtrees are already heapified, we only need
    // to fix the root.
    private void maxHeapify(int pos)
    {
        if (isLeaf(pos))
            return;
  
        if (Heap[pos] < Heap[leftChild(pos)]
            || Heap[pos] < Heap[rightChild(pos)]) {
  
            if (Heap[leftChild(pos)]
                > Heap[rightChild(pos)]) {
                swap(pos, leftChild(pos));
                maxHeapify(leftChild(pos));
            }
            else {
                swap(pos, rightChild(pos));
                maxHeapify(rightChild(pos));
            }
        }
    }
  
    // Inserts a new element to max heap
    public void insert(int element)
    {
        Heap[++size] = element;
  
        // Traverse up and fix violated property
        int current = size;
        while (Heap[current] > Heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }
  
    public void print()
    {
        for (int i = 1; i <= size / 2; i++) {
            System.out.print(
                " PARENT : " + Heap[i]
                + " LEFT CHILD : " + Heap[2 * i]
                + " RIGHT CHILD :" + Heap[2 * i + 1]);
            System.out.println();
        }
    }
  
    // Remove an element from max heap
    public int extractMax()
    {
        int popped = Heap[1];
        Heap[1] = Heap[size--];
        maxHeapify(1);
        return popped;
    }
}