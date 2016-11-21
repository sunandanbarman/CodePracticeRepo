import java.util.*;

class Entry {
	public String word;
	int count;
	Entry(String word, int count ) {
		this.word = word;
		this.count= count;
	}
}
public class TopKWords {
	private static List<String> findTopKWords(List<String> dict,int k) {
		if( dict == null || dict.size() == 0) {
			return null;
		}
		if(k <=0 || k > dict.size()) {
			return null;
		}
		HashMap<String,Integer> map = new HashMap<>();
		for(String word:dict) {
			if(!map.containsKey(word)) {
				map.put(word,1);
			} else {
				map.put(word,map.get(word)+1);
			}
		}
		PriorityQueue<Entry> q = new PriorityQueue<>(new Comparator<Entry>() {
			@Override
			public int compare(Entry o1,Entry o2) {
				Integer c1 = o1.count;
				Integer c2 = o2.count;
				return c1.compareTo(c2);
			}
		}) ;
		for (Map.Entry<String,Integer> entry : map.entrySet() ) {
			q.offer(new Entry(entry.getKey(),entry.getValue()));
			if(q.size() > k) {
				q.poll();
			}
		}
		List<String> result = new ArrayList<>();
		while(!q.isEmpty()) {
			result.add(q.poll().word);
		}
		return result;
	}
	public static void main(String[] args) {
		String []words = new String[]{"ABC","abc","bed","bath","beyond","cat","cat","abc"};
		List<String> dict = new ArrayList<>();
		for(String word:words) {
			dict.add(word);
		}	
		List<String> result = findTopKWords(dict,2);
		for(String word:result ) {
			System.out.println(word);
		}
	}
}