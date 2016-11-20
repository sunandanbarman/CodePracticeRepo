import java.util.*;

/**
* http://www.geeksforgeeks.org/boggle-find-possible-words-board-characters/
**/
class Entry {
	public String str;
	public int posRow, posCol;
	Entry(String str,int i,int j) {
		this.str    = str;
		this.posRow = i;
		this.posCol = j;
	}
}
public class WordFinder {
    	private static Set<String> findWords(char [][]arr, String []dict) {
    		String wordSoFar = "";
    		Set<String> list = new HashSet<>();
    		for(int i=0; i < arr.length; i++) {
    			for (int j=0; j < arr[0].length ; j++) {
    				wordSoFar = String.valueOf(arr[i][j]);
    				formWords(wordSoFar,arr,list,dict,i,j);
    			}
    		}
    		return list;
    	}
    	private static boolean isValid(int i,int j,int M,int N) {
    		if ((i < 0) || (i >= M)) {
    			return false;
    		}
    		if((j < 0) || (j >= N)) {
    			return false;
    		}
    		return true;
    	}
    	private static List<Entry> getEightNeighbors(char [][]arr,int posRow, int posCol) {
    		List<Entry> neighbors = new ArrayList<>();
    		int M = arr.length; 
    		int N = arr[0].length;
    		int []row = new int[]{-1,0,1};
    		int []col = new int[]{-1,0,1};
    		for (int i=0; i < row.length; i++) {
    			for (int j=0; j < col.length; j++) {
    				if (row[i] == 0 && col[j] == 0) {
    					continue;
    				}
    				if(isValid(posRow+row[i],posCol+col[j],M,N)) {
    					
    					neighbors.add(new Entry(
    						String.valueOf(arr[posRow+row[i]][posCol+col[j]]),
    						posRow+row[i],posCol+col[j]));
    				}
    			}
    		}
    		return neighbors;
    	}
    	private static boolean wordPresentAsSubStringInDict(String newWord,String []dict,Set<String> list) {
    		int idx = -1;
    		newWord = newWord.toLowerCase();
    		for(String str:dict) {
    			str = str.toLowerCase();
    			idx = str.indexOf(newWord);
    			if(idx < 0) {
    				continue;
    			}
    			if (idx == 0) {
    				if(newWord.equals(str) ) {
    					list.add(newWord);
    				}
    				return true;
    			}
    		}		
    		return false;
    	}
    	private static void formWords(String wordSoFar,char [][]arr,Set<String> list,
    								String []dict, int posRow, int posCol) {
    		Stack<Entry> stack = new Stack<>();
    		stack.push(new Entry(wordSoFar,posRow,posCol));
    		List<Entry> neighbors;
    		String newWord;
    		while(!stack.isEmpty()) {
    			Entry eWord= stack.pop();
	    		neighbors  = getEightNeighbors(arr,eWord.posRow,eWord.posCol);
	    		wordSoFar  = eWord.str;	    		
	    		for(Entry e:neighbors) {
	    			newWord = new StringBuilder(wordSoFar).append(e.str).toString();
	    			if(!wordPresentAsSubStringInDict(newWord,dict,list)) {
	    				continue;
	    			}
	    			stack.push(new Entry(newWord,e.posRow,e.posCol));
	    			//formWords(newWord,arr,list,dict,e.posRow,e.posCol);

	    		}

    		}
    	}

	public static void main(String[] args) {	
			String []dict = new String[]{"Geeks","Quiz","For","Go","Amazon","ekzi"};
			char [][]arr  = new char[][]{{'G','I','Z'},{'U','E','K'},{'Q','S','E'}};
			Set<String> list = findWords(arr,dict);
			for(String s:list ) {
				System.out.println(s);
			}
		}	
}