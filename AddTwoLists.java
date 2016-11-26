import java.util.*;

class Entry {
	public Node node;
	public int carry;
	Entry(Node n, int carry) {
		this.node = n;
		this.carry= carry;
	}
}
class Node {
	public int data;
	Node next;
	Node(int data) {
		this.data = data;
		next = null;
	}
}
public class AddTwoLists {
	public static Node getDupList(Node node) {
		Node root = null;
		Node r = root;
		Node t = node;
		while(t!= null) {
			if(root == null) {
				root = new Node(t.data); 
				r = root;
			} else {
				r.next = new Node(t.data);
				r = r.next;
			}
			t = t.next;
		}
		return root;
	}
	public static int getSize(Node root) {
		Node x= root;
		int count = 0;
		while(x != null) {
			x = x.next;
			count++:
		}
		return count;
	}
	public static Node travel(Node bigger,int delta) {
		Node t = bigger;
		while(delta > 0 ) {
			t = t.next;
			delta--;
		}
		return t;
	}
	private static Entry addSameSize(Node node1, Node node2, int carry) {
		if(node1 == null	) {
			return new Entry(null,0);
		}
		Node result = new Node(0);
		Entry e = addSameSize(node1.next,node2.next,carry);
		result.next = e.node;
		int sum = node1.data + node2.data + carry;
		result.data = sum % 10;
		carry = carry / 10;
		return new Entry(result,carry);
	}
	// add left out nodes to the bigger node
	private static Node addLeftNodes(Node head,Node curr, int carry) {

		if(head != cur) {
			return addLeftNodes(head.next,curr,carry);
			int sum = head.data + carry;
			Node x = new Node()
		}
		return 
				
	}
	public static Node addNumbers(Node node1, Node node2) {
		if(node1 == null && node2 == null) {
			return null;
		if (node1 = null) {
			return getDupList(node2);
		} 
		if (node2 == null) {
			return getDupList(node1);
		}
		int size1 = getSize(node1);
		int size2 = getSize(node2);
		int delta = size1 - size2;
		Node bigger = delta > 0 ? node1 : node2;
		Node smaller= delta > 0 ? node2 : node1;
		delta = Math.abs(delta);

		biggerNode = travel(bigger,delta); //biggerNode travels by distance delta
		Entry e    = addSameSize(node1,node2,0);
		if(e.carry == 0) {
			return e.node;
		}
		e = addLeftNodes(bigger,biggerNode,carry);
		if(e.carry > 0 ) {

		}
		return e.node;
	}
	public static void main(String[] args) {
		Node root = new Node(1);
		root.next = new Node(2);
		root.next.next = new Node(3);

		Node root2= new Node(3);
		root2.next= new Node(9);
		Node result= addNumbers(root,root2);
		Node t= result;
		while(t != null) {
			System.out.print(t.data + " ");
			t = t.next;
		}
	}


}