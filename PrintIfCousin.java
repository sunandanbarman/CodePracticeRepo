import java.util.*;

/*
* http://www.geeksforgeeks.org/check-two-nodes-cousins-binary-tree/
*/
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
class Entry {
	int level;
	TreeNode parent;
	TreeNode node;
	Entry(TreeNode node,TreeNode parent,int level) {
		this.node=node;
		this.parent = parent;
		this.level  = level;
	}
}
public class PrintIfCousin {
	public static Entry getLevelAndParent(TreeNode root,TreeNode node) {
		Queue<Entry> q = new LinkedList<>();
		q.add(new Entry(root,null,1));
		Entry x,result=null;
		while(!q.isEmpty()) {
			x = q.remove();
			if(x.node == node) {
				result = x;
				break;
			}
			if(x.node.left != null) {
				q.add(new Entry(x.node.left,x.node,x.level +1 ));
			}
			if(x.node.right != null) {
				q.add(new Entry(x.node.right,x.node,x.level +1 ));
			}

		}
		return result;
	}
	public static boolean printIfCousin(TreeNode root, TreeNode node1, TreeNode node2) {
		boolean result = false;
		Entry one = getLevelAndParent(root,node1);
		Entry two = getLevelAndParent(root,node2);
		if(one.level == two.level && (one.parent != two.parent)) {
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		TreeNode root = new TreeNode(6);
		root.left = new TreeNode(3);		
		root.right = new TreeNode(5);

		root.left.left = new TreeNode(7);
		root.left.right= new TreeNode(8);

		root.right.left = new TreeNode(1);
		root.right.right= new TreeNode(3);
		System.out.println(printIfCousin(root,root.left,root.right));
	}
}