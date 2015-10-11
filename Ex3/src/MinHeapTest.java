import static org.junit.Assert.*;
import org.junit.Test;

public class MinHeapTest {

	private MinHeap heapO = new MinHeap();
	
	/* tests allocTinyHeap & deleteTinyHeap */
	@Test
	public void testAllocAndDeleteTinyHeap() {
		/* alloc and delete */
		heapO.allocTinyHeap(30);
		assertEquals("created 30 should be 30", 30, heapO.getHeap().length);
		
		heapO.deleteTinyHeap();
		assertEquals("deleted should be null", null, heapO.getHeap());
		
		/* alloc with too big number for array and delete it */
		heapO.allocTinyHeap(1000000000);
		assertEquals("out of memory, thus assign default value, should be 10", 10, heapO.getHeap().length);
		
		heapO.deleteTinyHeap();
		
		/* alloc with negative number and delete it */
		heapO.allocTinyHeap(-12);
		assertEquals("wrong size, thus assign default value, should be 10", 10, heapO.getHeap().length);
		
		heapO.deleteTinyHeap();
	}
	
	/* tests AddHeapCapacity */
	@Test
	public void testAddHeapCapacity() {
		heapO.allocTinyHeap(30);
		heapO.addHeapCapacity(10);		
		heapO.addHeapCapacity(0);
		heapO.addHeapCapacity(-100);
		assertEquals("added 10 must be 40, other adding should not influence", 40, heapO.getHeap().length);
		
		heapO.addHeapCapacity(1000000000);
		assertEquals("out of memory, should be no influence", 40, heapO.getHeap().length);
	}

	/* tests addValue */
	@Test
	public void testAddValue() {
		heapO.allocTinyHeap(5);
		addValueFactory();
		assertEquals("fetching 1st element", 0, heapO.getHeap()[0]);
		assertEquals("fetching 2nd element", 1, heapO.getHeap()[1]);
		assertEquals("fetching 3d element", 3, heapO.getHeap()[2]);
		assertEquals("fetching 4th element", 4, heapO.getHeap()[3]);
		assertEquals("fetching 5th element", 2, heapO.getHeap()[4]);
		assertEquals("size should eq to 5", 5, heapO.getSize());
		
		/* testing full heap situation */
		heapO.add((byte) 5);
		assertEquals("fetching 5th element", 2, heapO.getHeap()[4]);
		assertEquals("size should eq to 5", 5, heapO.getSize());
	}
	
	private void addValueFactory(){
		heapO.add((byte) 4);
		heapO.add((byte) 3);
		heapO.add((byte) 2);
		heapO.add((byte) 1);
		heapO.add((byte) 0);
	}
	
	/* tests addValue for array input */
	@Test
	public void testAddArrayOfValues() {
		heapO.allocTinyHeap(15);
		addArrayValuesFactory();
		assertEquals("fetching 1st element", -3, heapO.getHeap()[0]);
		assertEquals("fetching 2nd element", -2, heapO.getHeap()[1]);
		assertEquals("fetching 3d element", 0, heapO.getHeap()[2]);
		assertEquals("fetching 4th element", 9, heapO.getHeap()[3]);
		assertEquals("fetching 5th element", 14, heapO.getHeap()[4]);
		assertEquals("fetching 6th element", 7, heapO.getHeap()[5]);
		assertEquals("fetching 7th element", 15, heapO.getHeap()[6]);
		assertEquals("fetching 8th element", 72, heapO.getHeap()[7]);
		assertEquals("fetching 9th element", 33, heapO.getHeap()[8]);
		assertEquals("fetching 10th element", 44, heapO.getHeap()[9]);
		assertEquals("fetching last (12) element", 12, heapO.getHeap()[11]);
		assertEquals("size should eq to 12", 12, heapO.getSize());
		
		/* testing heap is full situation */
		addArrayValuesFactory();
		assertEquals("fetching last (12) element", 12, heapO.getHeap()[11]);
		assertEquals("size should eq to 12", 12, heapO.getSize());
		
		/* testing null array situation */
		heapO.add(null);
		assertEquals("fetching last (12) element", 12, heapO.getHeap()[11]);
		assertEquals("size should eq to 12", 12, heapO.getSize());
	}
	
	private void addArrayValuesFactory(){
		byte[] array = { 72, 12, 9, 14, -3, 0, 15, -2, 33, 44, 19, 7 };
		heapO.add(array);
	}
	
	/* tests removeMin */
	@Test
	public void testRemoveMin() {
		heapO.removeMin();
		assertEquals("heap is empty, size should be unchanged", 0, heapO.getSize());
		
		heapO.allocTinyHeap(15);
		addArrayValuesFactory();
		heapO.removeMin();
		
		/* after remove root should be rebuild */
		assertEquals("fetching 1st element", -2, heapO.getHeap()[0]);
		assertEquals("fetching 2nd element", 9, heapO.getHeap()[1]);
		assertEquals("fetching 3d element", 0, heapO.getHeap()[2]);
	}
	
	/* tests removeMax */
	@Test
	public void testRemoveMax() {
		heapO.allocTinyHeap(15);
		addArrayValuesFactory();
		heapO.removeMax();
		
		assertEquals("last element was 12, not it should be removed", 0, heapO.getHeap()[11]);
	}
	
	/* tests tinyFree */
	@Test
	public void testTinyFree() {
		heapO.allocTinyHeap(16);
		addArrayValuesFactory();
		heapO.tinyFree(3);
		heapO.tinyFree(7);
		assertEquals("fetching 4th element", 14, heapO.getHeap()[3]);
		assertEquals("fetching 8th element", 44, heapO.getHeap()[7]);
		assertEquals("capacity of heap should not change", 16, heapO.getHeap().length);
	}
	
	/* complex test of few operations */
	@Test
	public void testUseCases() {
		heapO.allocTinyHeap(16);
		addArrayValuesFactory();
		addValueFactory();
		assertEquals("fetching 1st element", -3, heapO.getHeap()[0]);
		assertEquals("fetching 4th element", 1, heapO.getHeap()[3]);
		assertEquals("fetching 8th element", 9, heapO.getHeap()[7]);
		assertEquals("fetching 16th element", 72, heapO.getHeap()[15]);
		heapO.removeMin();
		heapO.removeMax();
		assertEquals("fetching 1st element", -2, heapO.getHeap()[0]);
		assertEquals("fetching 4th element", 9, heapO.getHeap()[3]);
		assertEquals("fetching 8th element", 72, heapO.getHeap()[7]);
		assertEquals("fetching 15th element", 0, heapO.getHeap()[14]);
		assertEquals("fetching 15th element", 0, heapO.getHeap()[14]);
		heapO.tinyFree(3);
		heapO.tinyFree(7);
		assertEquals("fetching 4th element", 14, heapO.getHeap()[3]);
		assertEquals("fetching 8th element", 44, heapO.getHeap()[7]);
		assertEquals("capacity of heap should not change", 16, heapO.getHeap().length);
	}
}
