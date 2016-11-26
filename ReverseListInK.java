import java.util.*;

/**
* https://leetcode.com/problems/reverse-linked-list-ii/
**/
class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}

class Entry {
    public ListNode first, last, nextNode;
    Entry(ListNode first, ListNode last,ListNode nextNode) {
        this.first = first;
        this.last  = last;
        this.nextNode = nextNode;
    }
} 

public class ReverseListInK {
    private static Entry reverseBetween(ListNode begin, int count) {
        ListNode prev = null, curr = begin, nextNode = null;
        int k=1;
        while(k <= count) {
            nextNode = curr.next;
            curr.next= prev;
            prev = curr;
            curr = nextNode;
            k++;
        }
        return new Entry(prev,begin, nextNode);
    }


    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null) {
            return null;
        }
        int k=1; 
        ListNode start=null, end, curr = head, prev1=null;
        if (m == 1) {
            start = head;
            prev1 = null;
            
        } else {
            while(k+1 <= m) { // find the first nodes
                start = curr.next;
                prev1 = curr;
                curr  = curr.next;
                k++;
            }
            
            
        }
        int count = n-m+1;
        Entry e = reverseBetween(start,count);
        if(prev1 != null)
        	prev1.next = e.first;
        e.last.next = e.nextNode;
        if(m== 1) {
            head = e.first;
        }
        return head;
    }
    private static Entry reverse(ListNode start,ListNode end ) {
        ListNode begin = start, prev = null, curr  = start, nextNode= null;
        ListNode endNode = end.next;
        while(curr != endNode) {
            nextNode = curr.next;
            curr.next= prev;
            prev = curr;
            curr = nextNode;
        }
        return new Entry(prev,begin,nextNode);
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
       if (head== null || k <= 0 ) {
           return null;
       }   
       ListNode start = head, end , prev = null;
       while(start != null) {
           end = start;
           for(int i=1; i < k ; i++) {
               end = end.next;
               if(end == null) {
                   break;
               }
           }
           if(end == null) {
               break;
           }
           Entry e = reverse(start,end);
           if(prev == null) {
               head = e.first;
           } else {
               prev.next = e.first;
           }
           e.last.next = e.nextNode;
           start = e.last.next;
           prev  = e.last;
       }
       return head;
    }    
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		ListNode x = new ListNode(2);
		head.next = x;
		x = new ListNode(3);
		head.next.next = x;
		x = new ListNode(4);
		head.next.next.next = x;
		x = new ListNode(5);
		head.next.next.next.next = x;
		head.next.next.next.next.next = new ListNode(6);

		ListNode root = reverseKGroup(head,7);
		x = root;
		while(x != null) {
			System.out.println(x.val);
			x = x.next;
		}
	}

}