/* 
	Solution 1 (LEET CODE SOLUTION)
	
	Use a priority queue (min-heap) and keep the heap size at k.
*/


/* 
	Time Complexity: O(N log(N) + M log(k)) where N is the length of nums, and M is the number of 
		calls to add(). 
	Space Complexity: O(N)
*/

class KthLargest {
    private static int k;
    private PriorityQueue<Integer> heap;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        heap = new PriorityQueue<>();
        
        for (int num: nums) {
            heap.offer(num);
        }
        
        while (heap.size() > k) {
            heap.poll();
        }
    }
    
    public int add(int val) {
        heap.offer(val);
        if (heap.size() > k) {
            heap.poll();
        }

        return heap.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */

/*
    LEET CODE RESULTS

    Runtime: 19 ms, faster than 39.49% of Java online submissions for Kth Largest Element in a 
        Stream.
    Memory Usage: 50 MB, less than 5.53% of Java online submissions for Kth Largest Element in a 
        Stream.
*/