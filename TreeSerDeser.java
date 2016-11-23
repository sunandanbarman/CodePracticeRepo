import java.util.*;

class TreeNode {
	TreeNode left,right;
	int val;
	TreeNode (int val) {
		this.val = val;
		this.left = null;
		this.right= null;
	}
}

class SerDeser {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
    	if (root == null) {
    		return null;
    	}
     	StringBuilder sb = new StringBuilder();   
     	Queue<TreeNode> q = new LinkedList<>();
     	q.add(root);
     	TreeNode x;
     	sb.append(root.val).append(",");
     	while(!q.isEmpty()) {
     		x = q.poll();
     		
     		if (x.left != null) {
     			q.add(x.left);
     			sb.append(x.left.val).append(",");
     		} else {
     			sb.append("null").append(",");
     		}
     		if (x.right != null) {
     			q.add(x.right);
     			sb.append(x.right.val).append(",");
     		} else {
     			sb.append("null").append(",");

     		}
     	}
     	String result;
     	if (sb.charAt(sb.length()-1)  == ',' ) {
     		result = sb.substring(0,sb.length()-1);
     	} else {
     		result = sb.toString();
     	}
     	return result;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
    	if (data == null) {
			return null;
    	}
    	TreeNode root;
    	String []list = data.trim().split(",");
    	String dt;
    	root = null;
    	TreeNode x;
    	int nullSeen = 0;
    	String leftTreeNode, rightTreeNode;
    	Queue<TreeNode> q= new LinkedList<>();
    	for(int i=0; i < list.length; i++) {
    		dt = list[i];
    		if(dt.equals("null")) {    			
    			nullSeen += (1*2); // 2 index lost
    		} else {
	    		if(root == null) {
	    			root = new TreeNode(Integer.valueOf(dt));
	    			q.add(root);
	    		}    			
    			x = q.poll();
    			int left = 2*i + 1 - nullSeen;
    			int right= 2*i + 2 - nullSeen;
    			leftTreeNode = list[left];
    			rightTreeNode= list[right];
    			if(!leftTreeNode.equals("null")) {
    				x.left = new TreeNode(Integer.valueOf(list[left]));
    				q.add(x.left);    				
    			}
    			if (!rightTreeNode.equals("null")) {
	    			x.right= new TreeNode(Integer.valueOf(list[right])); 
	    			q.add(x.right);    				
    			}
    		}
    	}
    	return root;
    }	
}
public class TreeSerDeser {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right= new TreeNode(3);

		root.left.left = new TreeNode(3);
		root.left.right= new TreeNode(4);

		root.left.left.left= new TreeNode(5);
		root.left.left.right= new TreeNode(6);
		// root.right.left = new TreeNode(4);
		root.right.right= new TreeNode(7);

		// root.right.right.right = new TreeNode(7);

		SerDeser sd = new SerDeser();
		String result = sd.serialize(root);
		System.out.println(result);

		TreeNode root2 = sd.deserialize(result);
		String result2 = sd.serialize(root2);
		System.out.println(result2);		
	}

}