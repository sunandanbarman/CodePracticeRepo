import java.util.*;

/**
* https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
**/
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public  class TreeTraversal {    

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null ) {
            return  result;
        }
        
        boolean flagDirection = false; // L To R ; false : R to L
        TreeNode x;
        LinkedList<TreeNode> list = new LinkedList<>();
        LinkedList<TreeNode> newList = new LinkedList<>();
        list.add(root);
        LinkedList<Integer> l1 = new LinkedList<>();
        l1.add(root.val);
        result.add(l1);
        while(!list.isEmpty()) {
            x = list.removeLast();
            if(!flagDirection) {
                if(x.right != null)
                    newList.add(x.right);
                if(x.left != null)
                    newList.add(x.left);
            }
            else {
                if(x.left != null)
                    newList.add(x.left);
                if(x.right != null)
                    newList.add(x.right);
            }
            
            if(list.isEmpty()) {
                flagDirection = !flagDirection;
                if(!newList.isEmpty()) {
                    list.addAll(newList);
                    l1 = new LinkedList<>();
                    while(!newList.isEmpty()) {
                        l1.add(newList.removeFirst().val);
                        
                    }
                    result.add(l1);
                    newList.clear();
                }
            }
            
        }
        return result;
    }
    public static void main(String[] args) {
        
    }
}