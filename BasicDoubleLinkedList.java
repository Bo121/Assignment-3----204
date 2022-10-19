import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> {

	/**
	 * Node<T> head The first node in the list
	 */
	protected Node<T> head;
	/**
	 * Node<T> tail The last node in the list
	 */
	protected Node<T> tail;
	/**
	 * int numOfEntries the number of elements in the list
	 */
	protected int numOfEntries;
	
	/**
	 * This methods sets the head, tail, and number of entries of the linked list
	 */
	public BasicDoubleLinkedList(){
		head = null;
		tail = null;
		numOfEntries = 0;
	}
	
	/**
	 * This method creates the Linked List
	 * @param <T> Generic type
	 */
	@SuppressWarnings("hiding") 
	public class Node<T> {
		protected T data;
		protected Node<T> nextNode;
		protected Node<T> prevNode;

		protected Node(T dataNode) {
			this(null, dataNode, null);
		}
		
		protected Node(Node<T> prevNode, T dataNode, Node<T> nextNode) {
			this.prevNode = prevNode;
			data = dataNode;
			this.nextNode = nextNode;
		}
	
		protected T getData() {
			return data;
		}
		
		protected void setData(T newData) {
			data = newData;
		}

		protected Node<T> getPrevNode() {
			return prevNode;
		}
		protected Node<T> getNextNode() {
			return nextNode;
		}
	}
	
	/**
	 * Returns an object of the DoubleLinkedListIterator.
	 */	
	public DoubleLinkedListIterator iterator() {
		return new DoubleLinkedListIterator();
	}
	
	
	/**
	 * This methods create an iterator
	 *
	 */
	protected class DoubleLinkedListIterator implements ListIterator<T>{

		private Node<T> prevNode;
		private Node<T> currentNode;
		
		public DoubleLinkedListIterator() {
			currentNode = head;
			prevNode = null;
		}
		
		@Override
		public boolean hasNext() {
			return currentNode != null;
		}

		@Override
		public T next() {
			
			if(!hasNext()) {
				throw new NoSuchElementException();
			}else{
				T temp = currentNode.getData();
				prevNode = currentNode;
				currentNode = currentNode.getNextNode();
				if(currentNode != null) {
					currentNode.prevNode = prevNode;
				}
				return temp;
			}
		}

		@Override
		public boolean hasPrevious() {
			return prevNode != null ;		
		}

		@Override
		public T previous() {
			
			if(!hasPrevious()) {
				throw new NoSuchElementException();
			}else {
				currentNode = prevNode;
				T temp = currentNode.getData();
				prevNode = currentNode.prevNode;
				return temp;
			}	
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(T e) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void add(T e) {
			throw new UnsupportedOperationException();
		}
		
	}

	/**
	 * This method adds the elements to the end of the list
	 * @param e the element to be added
	 */
	public void addToEnd(T e) {
		if(head == null) {
			head = new Node<T>(null, e, null);
		}
		else {
			Node<T> currentNode = head;
			while(currentNode.getNextNode() != null) {
				currentNode = currentNode.nextNode;
			}
			
			Node<T> newNode = new Node<T>(currentNode, e, null);
			currentNode.nextNode = newNode;
			tail = currentNode.nextNode;
		}
		
		numOfEntries++;
	}

	/**
	 * Return the number of nodes in the list
	 * @return integer the number of nodes
	 */
	public int getSize() {
		
		return numOfEntries;
	}
	
	/**
	 * This method returns the last element in the list but does not remove it from the list
	 * @return T the last element in the list
	 */
	public T getLast() {
		return tail.getData();
	}
	
	/**
	 * This method returns the first element in the list but does not remove it from the list
	 * @return T the first element in the list
	 */
	public T getFirst() {
		if(head == null) {
			throw new NoSuchElementException();
		}else {
			return head.getData();
		}
	}

	/**
	 * This method adds a element to the front of the list
	 * @param e a generic element to be added to the front of the list
	 */
	public void addToFront(T e) {
		if (head == null) {
			head = new Node<T>(null, e, null);
		}
		else {
			Node<T> newNode = new Node<T>(null, e, head);
			head.prevNode = newNode;
			head = newNode;
		}

		numOfEntries++;
	}

	public Node<T> prev;
	public Node<T> prev2;
	
	/**
	 * This method removes and returns the last element from the list.
	 * @return the last element in the list
	 */
	public T retrieveLastElement(){
		
		if (head  == null) {
			throw new IllegalStateException();
		}
		else if(numOfEntries == 1) {
			T data= head.getData();
			head = null;
			numOfEntries--;
			return data;
		}else {
			T data = tail.getData();	
			Node<T> temp = tail.getPrevNode();
			tail = temp;
			numOfEntries--;
			
			return data;
		}
	}
	
	/**
	 * This method gets the first element of the list
	 * @return the first element in the list
	 */
	public T getFront() {
		if(head  == null) {
			throw new NoSuchElementException();
		}else {
			return head.getData();
		}
	}

	/**
	 * Removes and returns the first element from the list
	 * @return the first element in the list
	 */
	//Removes and returns the first element from the list
	public T retrieveFirstElement() {
		T front = getFront();
		assert head != null;
		head.setData(null);
		head = head.getNextNode();
		
		if(head == null) {
			tail = null;
		}
		
		numOfEntries--;
		return front;
	}
	
	/**
	 * This method returns an Array list of all the items in the Double Linked list
	 * @return an Array list of all the items in the Double Linked list
	 */
	public ArrayList<T> toArrayList() {
		ArrayList<T> temp = new ArrayList<T>();
		ListIterator<T> itr = new DoubleLinkedListIterator();

		while (itr.hasNext()) {
			temp.add(itr.next());
		}
		 return temp;
	}
	
	/**
	 * This method remove a element from a list by comparing to other elements
	 * @param elem the elements needed to be deleted
	 * @param comparator compare the elements with another element
	 * @return the elements being deleted
	 */
	public BasicDoubleLinkedList<T> remove(T elem, java.util.Comparator<T> comparator) {
		Node<T> prevNode = null;
		Node<T> currentNode = head;
		
		while(currentNode != null) {
			if (comparator.compare(currentNode.getData(), elem) == 0) {
				if(currentNode == head) {
					head = head.getNextNode();
					currentNode = head;
				}else if(currentNode == tail) {
					tail = prevNode;
					currentNode = null;
					prevNode.nextNode= null;
				}else {
					prevNode.nextNode = currentNode.getNextNode();
					currentNode = currentNode.getNextNode();
				}
				numOfEntries--;	
			}else{
				prevNode = currentNode;
				currentNode = currentNode.getNextNode();
			}
		}
		return this;
	}

}

