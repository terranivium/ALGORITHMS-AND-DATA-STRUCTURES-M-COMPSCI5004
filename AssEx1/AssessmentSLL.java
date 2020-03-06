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
		// if the current list is empty just insert the element
		// at the head O(1)
		if(first == null){
			this.insert(elem); // reuse available code
			return;
		}

		// prepare new node which points to null
		// as its next element
		// because the node will be the last element
		// once it is inserted O(1)
		Node<E> newNode = new Node<E>(elem, null);

		// select the first node O(1)
		Node<E> currentNode = first;

		// move to each next node using pointers
		// until we find the last one O(n)
		// for n=length of list/number of nodes
		while(currentNode.next != null) {
			currentNode = currentNode.next;
		}

		// now currentNode is the last node in the list
		// so we will just change its pointer to point
		// to the new node in order to insert it O(1)
		currentNode.next = newNode;

		// O(1) + O(1) + O(n) + O(1) = O(n) overall
		return;

	}

	/** delete every alternate element
	 *  imagine the elements are indexed 0, 1, 2, ... , n-1
	 *  delete all the odd indexed elements
	 *  so if list had values ant, badger, cat, dog
	 *  the method would delete nodes containing badger and dog
	 */
	public void deleteAlternate() {
		// if the list is empty
		// we return bc we can't delete nothing
		if(first == null) return;

		// set the predecessor pointer to first node
		// and the current pointer to the next node O(1)
		Node<E> pred = first;
		Node<E> curr = first.next;

		// move pointers along nodes
		// until we have traversed the whole list
		// (in which case both will be null) 
		// since we are "hopping over" every other node
		// this is actually O(n/2) steps
		while(pred != null && curr !=null){
			// Change next link of previous node
			// to point to the next node of the current node
			// i.e. we skip the current node  O(1)
			pred.next = curr.next;

			// Clear current node O(1)
			curr = null;

			// Advance pred node to its next O(1)
			pred = pred.next;
			// if it's not the last node in the list
			// set the current pointer 
			// to the pred node's next node O(1)
			if (pred != null) curr = pred.next;
		}
		// O(n) overall
	}

	public Node<E> getNextUnique(Node<E> curr){ // ensures no repeated entries in the same list
		// set the predecessor node to the current node O(1)
		Node<E> pred = curr;
		// set the current node to its next node O(1)
		curr = curr.next;
		// now pred and curr are two neighbour nodes
		// compare them 
		// worst case scenario all the nodes after curr
		// are equal to curr so O(n) where n is the length of
		// a list in which curr is the first node
		while(compare(pred, curr) == 0){
			// set the current node to its next node O(1)
			curr = curr.next;
		}
		// when we exit the while loop it will return the first node
		// after curr that is not the same as curr's original value O(1)
		return curr;
		// O(1) + O(1) + O(n) * O(1) + O(1) = O(n) overall
	}

	public int compare(Node<E> elem1, Node<E> elem2){ // wrapper method to check for null or compare
		// all possible cases are
		// comparisons between 2 elements
		// which is 1 step O(1)
		if (elem1 == null && elem2 == null) { // null + equal
			return 0;
		} else if (elem1 == null) { // this null
			return 1;
		} else if (elem2 == null) { // arg null
			return -1;
		} else{ 
			return elem1.element.compareTo(elem2.element); // zero if the strings are equal, positive if lex >, negative if lex <
		}
		// O(1) overall
	}

	/**
	 * method to merge two ordered lists with this one whilst removing duplicates
	 * order should be preserved
	 * you need to replace the type list1, list2 and list3 and the return type
	 * with the new name of this class
	 */
	public AssessmentSLL<E> merge(AssessmentSLL<E> list1, AssessmentSLL<E> list2){
		// create a new list to hold the merged values O(1)
		AssessmentSLL<E> mergedList = new AssessmentSLL<E>();
		// select the first node from each list O(1)
		Node<E> a = this.first;
		Node<E> b = list1.first;
		Node<E> c = list2.first;

		// traverse all lists until the end O(n+m+l)
		// where n=length of list 1
		// m=length of list 2
		// l=length of list 3
		while(a != null || b != null || c != null){
			// each comparison is O(1)
			if ((this.compare(a, b) <= -1) && (this.compare(a, c) <= -1)){ // b and c are larger than a or null
				mergedList.insert(a.element);
				a = getNextUnique(a); // worst case this is O(n) if all elements in list are the same 
				// but it means on next step you dont have to traverse the list again
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
		this.first = mergedList.first; // this list becomes the merged list O(1)
		// overall O(n+m+l) for n,m,l - length of each of the 3 lists
		return mergedList; // return mergedlist
	}
}