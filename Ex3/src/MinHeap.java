import java.util.Arrays;

/**
 * @author lesyk
 *
 *Write a class that represents a minimal heap.  The heap class should at a minimum support the following methods:
-          AllocTinyHeap() which should initialize the heap with a given amount of bytes
-          DeleteTinyHeap() which frees all memory associated with the heap
-          TinyAlloc() which allocates a given number of bytes on the heap if there is room
-          TinyFree() which frees a specific location on the heap
You may define whatever parameters are necessary for the above methods as well as write any additional methods.
Overall consideration will be given to correctness, design, code readability as well as any unit testing done.
As part of a final solution please submit test cases you used to verify correctness in addition to any unit tests done.


Questions about the task:
  TinyAlloc(), does it mean that using specific index new value is inserted, and than tree re-balanced?
																		   , then old value is moved to the end, and then tree re-balanced? 
  Or does it mean to add one value, if heap is not full? (implemented add() methods)
  					 change the size of the heap? (implemented addHeapCapacity() method)
  TinyFree(), what means frees? Does it mean, remove value?
  														   and decrease size of the heap?
Notes:
  TinyFree() I understood as a removing node by index, and than re-balance.
  Also, do we consider 0 as freed memory? (Since when new array created, it consist only form 0s)
  After TinyFree() execution should I remove 0s, or not? (I thought no)
  Implemented in array representation, since as I wrote in letter other version I did before.
  I took into considerations other ways of implementation, for instance, Fibonacci method, but as Robert Sedgewick 
  mentioned: "Such advanced data structure usually is too complex for usage".  
 */

public class MinHeap {

	/* default size */
    private static final int DEF_CAPACITY = 10;
    
    /* array which contains the heap */
    private byte[] heap;
    
    /* 
     * heap getter
     */
    public byte[] getHeap(){
    	return heap;
    }
    
    /* size of the heap */
    private int size;
    
    /*
     * size getter
     */
    public int getSize(){
    	return this.size;
    }

	/**
	 * checks requirements and initializes empty heap
	 * @param bytes is a # of bytes for heap array
	 * @return new object of a class
	 * @throws OutOfMemoryError | NegativeArraySizeException and assigns default value if bytes value is not good enough 
	 */
    public void allocTinyHeap(int bytes) {
    	try {
			this.heap = new byte[bytes];
		} catch (OutOfMemoryError | NegativeArraySizeException e) {
		    System.err.println("Expected int > 0, within JAVA array bounds requirements, got "+ bytes +". Changing array size to " + DEF_CAPACITY + ".");
		    this.heap = new byte[DEF_CAPACITY];
		}
		
    	this.size = 0;
    }
    
    /*
     * one of possible TinyAlloc methods 
     * increases heap capacity
     * @param bytes shows on how many bytes heap should be increased
     */
    public void addHeapCapacity(int bytes){
    	if (bytes < 0 || bytes == 0) {
    		System.err.println("Expected int > 0, got " + bytes + "."); 
    		return;
    	}
    	
    	try {
    		this.heap = Arrays.copyOf(this.heap, bytes + this.heap.length);
    	} catch (OutOfMemoryError e) {
    	    System.err.println(e.getMessage() + ", can not increase buffer size.");
    	}
    }
    
    /*
     * deletes heap 
     * GC handle rest 
     */
    public void deleteTinyHeap() {
		this.heap = null;
		this.size = 0;
	}
   
    /*
     * heap traversal------------------------------------------------------------- 
     * math stuff
     * @param k index of element in a heap
     * on math/oop/algorithm & data sctructures/... (courses, which I attended) it was "always" k-th element, not i-th
     * @return left/right child or parent of k-th element 
     */
    private int getLeftChildIndex(int k) {
    	return 2 * k + 1;
    }

    private int getRightChildIndex(int k) {
        return 2 * k + 2;
    }

    private int getParentIndex(int k) {
        return (k - 1) / 2;
    }
    // ---------------------------------------------------------------------------
    
    /*
     * one of possible TinyAlloc methods
     * adds one value at a time
     * if there is a place in a heap, new element is added to the end of a heap
     * after that swapping according heap rule executes recursively
     * @param newValue 
     * @throws IllegalArgumentException if bytes # is not positive
     */
    public void add(byte newValue) {
        if (this.size == this.heap.length) {
        	System.err.println("Heap is full, can not insert more values.");
    	}else{
    		this.size++;
    		this.heap[this.size - 1] = newValue;
            siftUp(this.size - 1);
        }
  	}
    
    
    /*
     * adds values from array to heap
     * if the there is a place in a heap all elements are added to the end of a heap
     * after that swapping according heap rule executes recursively beginning from the root
     * @param newValues is an array of new values
     * @throws IllegalArgumentException if bytes # is not positive
     */
    public void add(byte[] newValues) throws NullPointerException, IllegalArgumentException {
    	if (newValues == null){
    		System.err.println("Inserting array is empty.");
    		return;
    	}
    	
    	/* checking if new array will fit */
    	if (newValues.length > 0 && newValues.length + this.size < this.heap.length) {
    	    System.arraycopy(newValues, 0, this.heap, this.size, newValues.length);
    	    this.size += newValues.length;

    	    // check and fix heap rule
    	    for (int i = 0; i < this.size; i++) {
    	    	siftUp(i);
    	    }
    	} else{
    		System.err.println("Inserting array's size is too big for heap, or heap is full, can not insert more values.");
    	}
    }
 
    /*
     * siftUp also know as trickle, heapify, bubble or percolate (choose current name since wiki uses it)
     * 
     * @param k is an index of element in a heap
     */
	private void siftUp(int k) {
        int parentIndex;
        byte tmp;
        
        /* if root nothing to do */
        if (k != 0) {
              parentIndex = getParentIndex(k);
              /* checks if parent is bigger than current node */
              if (heap[parentIndex] > heap[k]) {
            	/* swaps values, and siftsUp */
				tmp = heap[parentIndex];
                heap[parentIndex] = heap[k];
                heap[k] = tmp;
                siftUp(parentIndex);
              }
        }
	}
	
	/*
	 * removes minimum value, which in our heap is a root
	 * after that moves last element to root & decreasing size & sifting down new root
	 */
	public void removeMin() {
        if (isEmpty()) {
        	System.err.println("Heap is empty.");
		} else {
			/* assigning last element to root and removing last element's value */
			heap[0] = heap[size - 1];
            heap[size - 1] = 0;
            size--;
            /* checks if the is point to siftDown */
            if (size > 0) {
            	siftDown(0);
            }
        }
	}
	
	/*
	 * removes maximum value by heap property, which in our case is the last element
	 * after that decreases heap's size 
	 */
	public void removeMax() {
		heap[--size] = 0;
	}

	/*
	 * 
	 * @param k is an index of element in a heap
	 */
	private void siftDown(int k) {
        int leftChildIndex, rightChildIndex, minIndex;
        byte tmp;
        leftChildIndex = getLeftChildIndex(k);
        rightChildIndex = getRightChildIndex(k);
        if (rightChildIndex >= size) {
        	if (leftChildIndex >= size)
        		return;
        	else
        		minIndex = leftChildIndex;
        } else {
        	/* compares which child's value is smaller */
        	minIndex = heap[leftChildIndex] <= heap[rightChildIndex] ? leftChildIndex : rightChildIndex;
        }
        /* swaps values with smaller child and siftsDown it */
        if (heap[k] > heap[minIndex]) {
        	tmp = heap[minIndex];
            heap[minIndex] = heap[k];
            heap[k] = tmp;
            siftDown(minIndex);
        }
	}
	
	/*
	 * removes element from heap by removing element from array
	 * @param k index of element which will be removed
	 */
	public void tinyFree(int k) {
		if ((k < 0) || (k > this.size - 1)) System.err.println("There is no such index in heap, expected int > 0, got " + k + ".");
		
		/* calculating where to cut array */
		int cutPosition = this.size - k;
		
		/* checks if element is in the end of array */
		if (cutPosition > 0) {
		    // creates new empty array
		    byte[] newHeapArray = new byte[this.heap.length];
		    
		    /* copies first part of array */
		    System.arraycopy(this.heap, 0, newHeapArray, 0, k);
		    
		    /* copies second part of array */
		    System.arraycopy(this.heap, k + 1, newHeapArray, k, cutPosition);
		    this.heap = newHeapArray;
		} else {
		    this.heap = Arrays.copyOf(this.heap, this.size);
		}
	}

	/*
	 * checks if empty
	 * @return TRUE if empty or otherwise
	 */
	public boolean isEmpty() {
		return (size == 0);
	}
   
	public static void main(String[] args) {

	}
}
