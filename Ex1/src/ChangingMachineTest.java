import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ChangingMachineTest {
	
	private ChangingMachine CM;
	
	/* creates instance for ChangingMachine */ 
	@Before
	public void setUp() throws Exception {
		CM = new ChangingMachine();
	}

	/* test for simple numbers */
	@Test
	public void changeForSimpleNumbersTest() {
		assertEquals("From 1 must be 1", 1, CM.makeChange(1));
		assertEquals("From 5 must be 1", 1, CM.makeChange(5));
		assertEquals("From 10 must be 1", 1, CM.makeChange(10));
		assertEquals("From 20 must be 1", 1, CM.makeChange(20));
	    assertEquals("From 50 must be 1", 1, CM.makeChange(50));
	    assertEquals("From 100 must be 1", 1, CM.makeChange(100));
	}
	
	/* test for complex numbers */
	@Test	
	public void changeForComplexNumbersTest() {
	    assertEquals("From 135 must be 4", 4, CM.makeChange(135));
	    assertEquals("From 227 must be 4", 6, CM.makeChange(227));
	    assertEquals("From 44 must be 6", 6, CM.makeChange(44));
	    assertEquals("From 98 must be 7", 7, CM.makeChange(98));
	}
	
	/* tests for bad data on input */
	@Test	
	public void changeForBadDataTest() {
	    assertEquals("From 0 must be 0", 0, CM.makeChange(0));
	    assertEquals("From -12 must be 0", 0, CM.makeChange(0));
	}
}