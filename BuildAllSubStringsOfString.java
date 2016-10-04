import java.util.*;
/**
Create all possible (not necessary contiguous ) substrings of given string
E.g. : Given s ="abc", output : "a,ab,abc,ac,b,bc,c" (Output must be in lexicographic sorted order)
**/
public class BuildAllSubStringsOfString {    
	private static void constructSeq(String sNew,String s,int sIdx, Set<String> list) {
    	if (s.length() == 0) {
    		return;
    	}
    	if (sIdx >= s.length()) {
    		return;
    	}
    	char temp;
    	for(int i=sIdx; i < s.length();i++) {
    		temp = s.charAt(i);
    		String next = new StringBuilder(sNew).append(Character.toString(temp)).toString();
    		list.add(next);
    		constructSeq(next, s, ++sIdx, list);
    	}
    }
    public static String[] buildSeq(String s) {
    	Set<String> list = new TreeSet<>();
    	for(int i=0; i < s.length();i++) {
    		list.add(Character.toString(s.charAt(i)));
    		constructSeq(Character.toString(s.charAt(i)),s,i+1,  list);
    	}
    	
    	String[] res = new String[list.size()];
    	int i=0;
    	for(String sTemp : list) {
    		res[i] = sTemp;
    		i++;
    	}
    	return res;
    }
	public static void main(String []args) {
		String s = "abcd";
		String[] sRes = buildSeq(s);
		for(String sTemp : sRes) {
			System.out.println(sTemp);
		}		
	}
}