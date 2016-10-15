import java.util.*;

/*
* Original question: https://leetcode.com/problems/lru-cache/
*/
class Node {
	Node next,prev;
	int val, key;
	Node(int key,int val) {
		this.key = key;
		this.val = val;
		this.next= null;
		this.prev= null;
	}
}

class LRUCacheStructure {
	HashMap<Integer,Node> map = new HashMap<>();
	Node head, end;
	int capacity;
	LRUCacheStructure(int capacity) {
		this.capacity = capacity;
	}
	public void remove(Node node) {
		if(node.prev != null) {
			node.prev.next = node.next;
		} else {
			head = node.next;
		}
		if(node.next != null) {
			node.next.prev = node.prev;
		} else {
			end = node.prev;
		}
		
	}
	public void setHead(Node node) {
		node.next = head;
		node.prev = null;
		if(head != null) {
			head.prev = node;
			
		}
		head = node;
		if(end == null)
			end = head;
		
	}
	public int get(int key) {
		if (map.containsKey(key)) {
			Node node = map.get(key);
			int val   = node.val;
			remove(node);
			setHead(node);
			return val;
		}
		return -1;
	}	
	public void set(int key,int val) {
		if(map.containsKey(key)) {
			Node node = map.get(key);
			node.val  = val;
			remove(node);
			setHead(node);
		} else {
			Node created = new Node(key,val);
			if(map.size() >= capacity) {
				map.remove(end.key);
				remove(end);
				
			}
			setHead(created);
			map.put(key,created);
		}
	}
}

public class LRUCache {
	public static void main(String[] args) {
		LRUCacheStructure lru = new LRUCacheStructure(2);
		lru.set(2,1);
		lru.set(3,2);
		//lru.set(3,3);


//
		//[set(2,1),set(3,2),get(3),get(2),set(4,3),get(2),get(3),get(4)]


		//lru.set(4,4);
		System.out.println("val = " + lru.get(3));
		System.out.println("val = " + lru.get(2));
		lru.set(4,3);
		System.out.println("val = " + lru.get(2));
		System.out.println("val = " + lru.get(3));


		System.out.println("val = " + lru.get(4));
		
		//System.out.println("val = " + lru.get(3));

	}
}