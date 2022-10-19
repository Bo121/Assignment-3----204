import java.util.Comparator;


public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T>{

	Comparator<T> compare = null;
	
	/**
	 * This method set the elements that will be compared with to null
	 * @param compareableObject the elements to be compared with 
	 */
	public SortedDoubleLinkedList(Comparator<T> compareableObject) {
		compare = compareableObject;
	}
	
	/**
	 * Inserts the specified element at the correct position in the sorted list.
	 * @param data the specific element to be added to the sorted linked list
	 * @return the elements to be added to the linked list
	 */
	public SortedDoubleLinkedList<T> add(T data) {
		
		 if (data == null) {
			 return this;
		 }
		
		 Node<T> newNode = new Node<T>(null, data, null);
		
		if(head==null) {
			head = new Node<T>(null, data, null);
			tail = head;
		}else {
			if (compare.compare(data, tail.getData()) >= 0){
				tail.nextNode = newNode;
				tail = newNode;
			}else if(compare.compare(data, head.getData()) <= 0){
				newNode.nextNode = head;
				head = newNode;
			}else {
				Node<T> nextNode = head.nextNode;
				Node<T> prevNode = head;
				
				while(compare.compare(data, nextNode.getData()) > 0) {
					prevNode = nextNode;
					nextNode = nextNode.nextNode;
				}
				
				prevNode.nextNode = newNode;
				newNode.nextNode = nextNode;
			}
		}
		numOfEntries++;
		return this;
	}
	
	/**
	 * This method remove a element from a list by comparing to other elements
	 * @param elem the elements needed to be deleted
	 * @param comparator compare the elements with another element
	 * @return the elements being deleted
	 */
	@Override
	public BasicDoubleLinkedList<T> remove(T elem, java.util.Comparator<T> comparator) {
		Node<T> prev = null;
		Node<T> current = head;
		while(current != null) {
			if (comparator.compare(current.data, elem) == 0) {
				if(current == head) {
					head = head.getNextNode();
					current = head;
				}else if(current == tail) {
					current = null;
					tail = prev;
					prev.nextNode= null;
				}else {
					prev.nextNode = current.getNextNode();
					current = current.getNextNode();
				}
				numOfEntries--;	
			}else{
				prev = current;
				current = current.getNextNode();
			}
		}
		return this;
	}

	/**
	 * Implements the iterator by calling the super class iterator method.
	 */
	@Override
	public DoubleLinkedListIterator iterator(){
		return new DoubleLinkedListIterator();
	}
	
	/**
	 * This operation is invalid for a sorted list.
	 */
	@Override
	public void addToEnd(T data){
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	/**
	 * This operation is invalid for a sorted list.
	 */
	
	@Override
	public void addToFront(T data){
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
}
