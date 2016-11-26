import java.util.*;

public class Verify {
    public static boolean isValidSerialization(String preorder) {
        if (preorder == null || preorder.length() == 0) {
            return false;
        }
        LinkedList<String> stack = new LinkedList<>();
        String []arr = preorder.split(",");
        for(int i=0; i < arr.length; i++) {
            stack.add(arr[i]);
            while(stack.size() >= 3
            && stack.get(stack.size()-1).equals("#")
            && stack.get(stack.size()-2).equals("#")    
            && !stack.get(stack.size()-3).equals("#")) {
                stack.removeLast();
                stack.removeLast();
                stack.removeLast();
                stack.add("#");
            }
            
        }
        if(stack.size() == 1 && stack.get(0).equals("#")) {
            return true;
        }
        return false;
    }
	
	public static void main(String[] args) {
		String preorder = "9,3,#,#,4,1,#,#,6,#";
		System.out.println(isValidSerialization(preorder));
	}
}