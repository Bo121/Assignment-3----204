
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class SortedDoubleLinkedList_STUDENT_Test {
	SortedDoubleLinkedList<Double> sortedLinkedDouble;
	DoubleComparator comparatorD;
	
	
	@Before
	public void setUp() throws Exception {
		comparatorD = new DoubleComparator();
		sortedLinkedDouble = new SortedDoubleLinkedList<Double>(comparatorD);
	}
	
	@After
	public void tearDown() throws Exception {
		comparatorD = null;
		sortedLinkedDouble = null;
	}
	
	@Test
	public void testAddToEnd() {
		try {
			sortedLinkedDouble.addToEnd(10.0);
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}
	
	@Test
	public void testAddToFront() {
		try {
			sortedLinkedDouble.addToFront(10.0);
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}
	
	@Test
	public void testIteratorSuccessfulDoubleNext() {
		sortedLinkedDouble.add(10.0);
		sortedLinkedDouble.add(40.0);
		sortedLinkedDouble.add(20.0);
		sortedLinkedDouble.add(100.0);
		ListIterator<Double> iterator = sortedLinkedDouble.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(Double.valueOf(10.0), iterator.next());
		assertEquals(Double.valueOf(20.0), iterator.next());
		assertEquals(Double.valueOf(40.0), iterator.next());
		assertEquals(true, iterator.hasNext());
	}
	
	@Test
	public void testIteratorSuccessfulDoublePrevious() {
		sortedLinkedDouble.add(10.0);
		sortedLinkedDouble.add(40.0);
		sortedLinkedDouble.add(20.0);
		sortedLinkedDouble.add(100.0);
		ListIterator<Double> iterator = sortedLinkedDouble.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(Double.valueOf(10.0), iterator.next());
		assertEquals(Double.valueOf(20.0), iterator.next());
		assertEquals(Double.valueOf(40.0), iterator.next());
		assertEquals(Double.valueOf(100.0), iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals(Double.valueOf(100.0), iterator.previous());
		assertEquals(Double.valueOf(40.0), iterator.previous());
		assertEquals(Double.valueOf(20.0), iterator.previous());
	}
	
	@Test
	public void testIteratorNoSuchElementException() {
		sortedLinkedDouble.add(10.0);
		sortedLinkedDouble.add(40.0);
		sortedLinkedDouble.add(20.0);
		sortedLinkedDouble.add(100.0);
		//ArrayList<Car> carList = sortedLinkedCar.toArrayList();
		//alphabetic order: e f a c b d
		ListIterator<Double> iterator = sortedLinkedDouble.iterator();
		
		assertEquals(true, iterator.hasNext());
		assertEquals(Double.valueOf(10.0), iterator.next());
		assertEquals(Double.valueOf(20.0), iterator.next());
		assertEquals(Double.valueOf(40.0), iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals(Double.valueOf(100.0), iterator.next());
		try{
			//no more elements in list
			iterator.next();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
	}
	
	@Test
	public void testIteratorUnsupportedOperationExceptionString() {
		sortedLinkedDouble.add(10.0);
		sortedLinkedDouble.add(40.0);
		sortedLinkedDouble.add(20.0);
		sortedLinkedDouble.add(100.0);
		//ArrayList<Car> carList = sortedLinkedCar.toArrayList();
		//alphabetic order: e f a c b d
		ListIterator<Double> iterator = sortedLinkedDouble.iterator();
		
		try{
			//remove is not supported for the iterator
			iterator.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}
	
	@Test
	public void testRemoveFirestDouble() {
		sortedLinkedDouble.add(10.0);
		sortedLinkedDouble.add(40.0);
		assertEquals(Double.valueOf(10.0), sortedLinkedDouble.getFirst());
		assertEquals(Double.valueOf(40.0), sortedLinkedDouble.getLast());
		sortedLinkedDouble.add(5.0);
		assertEquals(Double.valueOf(5.0), sortedLinkedDouble.getFirst());
		// remove the first
		sortedLinkedDouble.remove(5.0, comparatorD);
		assertEquals(Double.valueOf(10.0), sortedLinkedDouble.getFirst());
	}
	
	@Test
	public void testRemoveLastDouble() {
		sortedLinkedDouble.add(10.0);
		sortedLinkedDouble.add(40.0);
		assertEquals(Double.valueOf(10.0), sortedLinkedDouble.getFirst());
		assertEquals(Double.valueOf(40.0), sortedLinkedDouble.getLast());
		sortedLinkedDouble.add(50.0);
		assertEquals(Double.valueOf(50.0), sortedLinkedDouble.getLast());
		// remove the first
		sortedLinkedDouble.remove(50.0, comparatorD);
		assertEquals(Double.valueOf(40.0), sortedLinkedDouble.getLast());
	}
	
	@Test
	public void testRemoveMiddleDouble() {
		sortedLinkedDouble.add(10.0);
		sortedLinkedDouble.add(40.0);
		assertEquals(Double.valueOf(10.0), sortedLinkedDouble.getFirst());
		assertEquals(Double.valueOf(40.0), sortedLinkedDouble.getLast());
		sortedLinkedDouble.add(30.0);
		assertEquals(Double.valueOf(10.0), sortedLinkedDouble.getFirst());
		assertEquals(Double.valueOf(40.0), sortedLinkedDouble.getLast());
		assertEquals(3,sortedLinkedDouble.getSize());
		//remove from middle of list
		sortedLinkedDouble.remove(30.0, comparatorD);
		assertEquals(Double.valueOf(10.0), sortedLinkedDouble.getFirst());
		assertEquals(Double.valueOf(40.0), sortedLinkedDouble.getLast());
		assertEquals(2,sortedLinkedDouble.getSize());
	}
	
	private class DoubleComparator implements Comparator<Double>
	{
		@Override
		public int compare(Double arg0, Double arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
	
	
}
