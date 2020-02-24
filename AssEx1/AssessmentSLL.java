//2460681S, Wesley Scott
//do not include package statement 
//do not import any classes
//just complete the methods indicated,
//namely:
//insertTail
//deleteAlternate and
//merge

public class AssessmentSLL<E extends Comparable<E>> {

	// Each SLL object is the header of
	// a singly-linked-list

	private Node<E> first;

	/**
	 * constructor
	 */
	public AssessmentSLL() {
		// Construct an empty SLL.
		first = null;
	}

	// //////// Inner class //////////
	private static class Node<E> {
		// Each Node object is a node of a
		// singly-linked-list.
		protected E element;
		protected Node<E> next;

		public Node(E elem, Node<E> n) {
			element = elem;
			next = n;
		}
	}

	// ////////////////////////////////

	/**
	 * print all elements starting from first node
	 */
	public void printFirstToLast() {
		// Print all elements in this SLL, in first-to-last order.
		Node<E> curr = first;
		while (curr != null) {
			System.out.println(curr.element);
			curr = curr.next;
		}
	}

	/**
	 * insert new node containing elem at front of list
	 * 
	 */
	public void insert(E elem) {
		Node<E> newNode = new Node<E>(elem, first);
		first = newNode;
	}

	/**
	 * add new node to end of list
	 */
	public void insertTail(E elem) {
		Node<E> newNode = new Node<E>(elem, first);

		if(first == null){
			first = new Node<E>(elem, first);
			return;
		}
		newNode.next = null;

		Node<E> last = first;
		while(last.next != null){
			last = last.next;
		}
		last.next = newNode;

		return;
	}

	/** delete every alternate element
	 *  imagine the elements are indexed 0, 1, 2, ... , n-1
	 *  delete all the odd indexed elements
	 *  so if list had values ant, badger, cat, dog
	 *  the method would delete nodes containing badger and dog
	 */
	public void deleteAlternate() {
		if(first == null) return;

		Node<E> pred = first;
		Node<E> curr = first.next;

		while(pred != null && curr !=null){
			// Change next link of previous node
			pred.next = curr.next;

			// Clear current node
			curr = null;

			// Update pred and curr
			pred = pred.next;
			if (pred != null) curr = pred.next;
		}
	}

	public Node<E> getNextUnique(Node<E> curr){ // ensures no repeated entries in the same list
		Node<E> dummy = curr;
		curr = curr.next;
		while(compare(dummy, curr) == 0){
			curr = curr.next;
		}
		return curr;
	}

	public int compare(Node<E> elem1, Node<E> elem2){ // wrapper method to check for null or compare
		if (elem1 == null && elem2 == null) { // null + equal
			return 0;
		} else if (elem1 == null) { // this null
			return 1;
		} else if (elem2 == null) { // arg null
			return -1;
		} else{ 
			return elem1.element.compareTo(elem2.element); // zero if the strings are equal, positive if lex >, negative if lex <
		}
	}

	/**
	 * method to merge two ordered lists with this one whilst removing duplicates
	 * order should be preserved
	 * you need to replace the type list1, list2 and list3 and the return type
	 * with the new name of this class
	 */
	public  AssessmentSLL<E> merge(AssessmentSLL<E> list1, AssessmentSLL<E> list2){
		
		AssessmentSLL<E> mergedList = new AssessmentSLL<E>();
		Node<E> a = this.first;
		Node<E> b = list1.first;
		Node<E> c = list2.first;

		while(a != null || b != null || c != null){
			if ((this.compare(a, b) <= -1) && (this.compare(a, c) <= -1)){ // b and c are larger than a or null
				mergedList.insert(a.element);
				a = getNextUnique(a);
			} else if((this.compare(b, a) <= -1) && (this.compare(b, c) <= -1)){ // a and c are larger than b or null
				mergedList.insert(b.element);
				b = getNextUnique(b);
			} else if((this.compare(c, a) <= -1) && (this.compare(c, b) <= -1)){ // a and b are larger than c or null 
				mergedList.insert(c.element);
				c = getNextUnique(c);
			} else if ((this.compare(a, b) == 0) && (this.compare(a, c) <= -1)){ // ab equal, a < c
				mergedList.insert(a.element);
				a = getNextUnique(a);
				b = getNextUnique(b);
			} else if ((this.compare(a, c) == 0) && (this.compare(a, b) <= -1)){ // ac equal, a < b
				mergedList.insert(a.element);
				a = getNextUnique(a);
				c = getNextUnique(c);
			}  else if ((this.compare(b, c) == 0) && (this.compare(b, a) <= -1)){ // bc equal, b < a
				mergedList.insert(b.element);
				b = getNextUnique(b);
				c = getNextUnique(c);
			} else if ((this.compare(a, b) == 0) && (this.compare(b, c) == 0)){ // abc equal
				mergedList.insert(b.element);
				a = getNextUnique(a);
				b = getNextUnique(b);
				c = getNextUnique(c);
			}
		}
		this.first = mergedList.first; // this list becomes the merged list
		return mergedList; // return mergedlist
	}
}