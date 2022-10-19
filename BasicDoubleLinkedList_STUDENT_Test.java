
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BasicDoubleLinkedList_STUDENT_Test {
	BasicDoubleLinkedList<String> linkedString;
	StringComparator comparator;


	@Before
	public void setUp() throws Exception {
		linkedString = new BasicDoubleLinkedList<String>();
		linkedString.addToEnd("Hello");
		linkedString.addToEnd("Hel");
		linkedString.addToFront("H");
		linkedString.addToEnd("Helllllll");
		comparator = new StringComparator();
	}

	@After
	public void tearDown() throws Exception {
		linkedString = null;
	}

	@Test
	public void testGetSize() {
		assertEquals(4,linkedString.getSize());
		
	}	
	
	@Test
	public void testAddToEnd() {
		assertEquals("Helllllll", linkedString.getLast());
		linkedString.addToEnd("Hueell");
		assertEquals("Hueell", linkedString.getLast());
		
		assertEquals("Hueell",linkedString.getLast());
		linkedString.addToEnd("boo");
		assertEquals("boo",linkedString.getLast());
	}
	
	@Test
	public void testAddToFront() {
		assertEquals("H", linkedString.getFirst());
		linkedString.addToFront("bbb");
		assertEquals("bbb", linkedString.getFirst());
		
	}
	
	@Test
	public void testGetFirst() {
		assertEquals("H", linkedString.getFirst());
		linkedString.addToFront("boo");
		assertEquals("boo", linkedString.getFirst());
		
		assertEquals("boo",linkedString.getFirst());
		linkedString.addToFront("zzz");
		assertEquals("zzz",linkedString.getFirst());
	}
	
	@Test
	public void testGetLast() {
		assertEquals("Helllllll", linkedString.getLast());
		linkedString.addToEnd("boo");
		assertEquals("boo", linkedString.getLast());
		
		assertEquals("boo",linkedString.getLast());
		linkedString.addToEnd("bbb");
		assertEquals("bbb",linkedString.getLast());
	}
	
	@Test
	public void testToArrayList()
	{
		ArrayList<String> list;
		linkedString.addToFront("bbb");
		linkedString.addToEnd("boo");
		list = linkedString.toArrayList();
		assertEquals("bbb",list.get(0));
		assertEquals("H",list.get(1));
		assertEquals("Hello",list.get(2));
		assertEquals("Hel",list.get(3));
		assertEquals("Helllllll",list.get(4));
		assertEquals("boo",list.get(5));
	}
	
	@Test
	public void testIteratorSuccessfulNext() {
		linkedString.addToFront("bbb");
		linkedString.addToEnd("boo");
		ListIterator<String> iterator = linkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("bbb", iterator.next());
		assertEquals("H", iterator.next());
		assertEquals("Hello", iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals("Hel", iterator.next());
		assertEquals("Helllllll", iterator.next());
		assertEquals("boo", iterator.next());

	}
	
	@Test
	public void testIteratorSuccessfulPrevious() {
		linkedString.addToFront("bbb");
		linkedString.addToEnd("boo");
		ListIterator<String> iterator = linkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("bbb", iterator.next());
		assertEquals("H", iterator.next());
		assertEquals("Hello", iterator.next());
		assertEquals("Hel", iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals("Hel", iterator.previous());
		assertEquals("Hello", iterator.previous());
		assertEquals("H", iterator.previous());
		assertEquals("bbb", iterator.previous());
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionNext() {
		linkedString.addToFront("bbb");
		linkedString.addToEnd("boo");
		ListIterator<String> iterator = linkedString.iterator();	
		assertEquals(true, iterator.hasNext());
		assertEquals("bbb", iterator.next());
		assertEquals("H", iterator.next());
		assertEquals("Hello", iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals("Hel", iterator.next());
		
		try{
			//no more elements in list
			iterator.next();
			assertTrue("Did not throw a NoSuchElementException",true);
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
	public void testIteratorNoSuchElementExceptionPrevious() {
		linkedString.addToFront("bbb");
		linkedString.addToEnd("boo");
		ListIterator<String> iterator = linkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("bbb", iterator.next());
		assertEquals("H", iterator.next());
		assertEquals("Hello", iterator.next());
		assertEquals("Hel", iterator.next());
		assertEquals(true, iterator.hasPrevious());
		
		assertEquals("Hel", iterator.previous());
		assertEquals("Hello", iterator.previous());
		assertEquals("H", iterator.previous());
		assertEquals("bbb", iterator.previous());
		
		try{
			//no more elements in list
			iterator.previous();
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
	public void testIteratorUnsupportedOperationException() {
		linkedString.addToFront("bbb");
		linkedString.addToEnd("boo");
		ListIterator<String> iterator = linkedString.iterator();	
		assertEquals(true, iterator.hasNext());
		assertEquals("bbb", iterator.next());
		assertEquals("H", iterator.next());
		assertEquals("Hello", iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals("Hel", iterator.next());
		
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
	
	private class StringComparator implements Comparator<String>
	{

		@Override
		public int compare(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
	
	@Test
	public void testRemove() {
		// remove the first
		assertEquals("H", linkedString.getFirst());
		assertEquals("Helllllll", linkedString.getLast());
		linkedString.addToFront("bbb");
		assertEquals("bbb", linkedString.getFirst());
		linkedString.remove("bbb", comparator);
		assertEquals("H", linkedString.getFirst());
		//remove from the end of the list
		linkedString.addToEnd("boo");
		assertEquals("boo", linkedString.getLast());
		linkedString.remove("boo", comparator);
		assertEquals("Helllllll", linkedString.getLast());
		//remove from middle of list
		linkedString.addToFront("bbb");
		assertEquals("bbb", linkedString.getFirst());
		assertEquals("Helllllll", linkedString.getLast());
		linkedString.remove("bbb", comparator);
		assertEquals("H", linkedString.getFirst());
		assertEquals("Helllllll", linkedString.getLast());
		
	}
	
	@Test
	public void testRetrieveFirstElement() {
		assertEquals("H", linkedString.getFirst());
		linkedString.addToFront("bbb");
		assertEquals("bbb", linkedString.getFirst());
		assertEquals("bbb", linkedString.retrieveFirstElement());
		assertEquals("H",linkedString.getFirst());
		assertEquals("H", linkedString.retrieveFirstElement());
		assertEquals("Hello",linkedString.getFirst());
		
	}
	
	@Test
	public void testRetrieveLastElement() {
		assertEquals("Helllllll", linkedString.getLast());
		linkedString.addToEnd("boo");
		assertEquals("boo", linkedString.getLast());
		assertEquals("boo", linkedString.retrieveLastElement());
		assertEquals("Helllllll",linkedString.getLast());
	}
	
}
