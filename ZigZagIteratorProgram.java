import java.util.*;

/* https://leetcode.com/problems/zigzag-iterator/ */
class ZigZag {
	Queue<List<Integer>> q;
	ZigZag(List<List<Integer>> list) {
		q = new LinkedList<>();
		List<Integer> l;
		for(int i=0; i < list.size(); i++) {
			l = list.get(i);
			q.offer(l);
		}
	}
	public boolean hasNext()  {
		return (!q.isEmpty());
	}
	public int next() {
		List<Integer> l = q.poll();
		int result = l.remove(0);
		if(!l.isEmpty()) {
			q.add(l);	
		}		
		return result;
	}
}
public class ZigZagIteratorProgram {
	public static List<Integer> returnList(int []A) {
		List<Integer> list = new LinkedList<>();
		for(int a:A) {
			list.add(a);
		}
		return list;
	}
	public static void main(String[] args) {
		List<List<Integer>> list = new LinkedList<>();
		int []A = new int[]{1,2,3};
		int []B = new int[]{5,10,18,3};
		int []C = new int[]{3,2,1,5,6};
		int []D = new int[]{10,11,12};
		List<Integer> one = returnList(A);
		List<Integer> two = returnList(B);
		List<Integer> three = returnList(C);
		List<Integer> four = returnList(D);
		list.add(one);
		list.add(two);
		list.add(three);
		list.add(four);
		
		ZigZag z = new ZigZag(list);
		while(z.hasNext()) {
			System.out.println(z.next());
		}
	}

}