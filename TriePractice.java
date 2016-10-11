import java.util.*;

/**
* Q : https://leetcode.com/problems/implement-trie-prefix-tree/
* Inspiration for algorithm taken from 
* http://www.geeksforgeeks.org/trie-insert-and-search/

* NOTE : Trie keys removal is implemented using a backlink to the parent's node.
* The idea is to remove the link to the node, if there are no more keys under this node, 
* and traverse upwards till it is true.
**/
class TrieNode {
	int alphabet_size = 26;
	int wordCount = 0 ;
	public boolean isLeaf;
	public TrieNode children[];
	public TrieNode parent;
	TrieNode() {
		children = new TrieNode[alphabet_size];
		isLeaf = false;
		for(int i=0; i < alphabet_size; i++)  {
			children[i] = null;
		}
		parent = null;
		wordCount  = 0;
	}
	TrieNode(TrieNode parent) {
		this.parent = parent;
	}
}

class Trie{ 
	TrieNode root = null;
	Trie() {
		root = getNode();
		root.parent = null;
	}
	TrieNode getNode() {
		TrieNode pNode = new TrieNode();
		if(root == null) {
			root = pNode;
		}
		return pNode;
	}
	void insert(String key) {
		int level, index;
		int length = key.length();
		TrieNode pCrawl = root;
		for(level=0; level < length; level++) {
			index = (int)key.charAt(level) - (int)'a';
			if (pCrawl.children[index] == null) {
				pCrawl.children[index] = new TrieNode();
				pCrawl.children[index].parent = pCrawl;
			}
			
			pCrawl = pCrawl.children[index];
		}

		pCrawl.isLeaf = true;
	}
	boolean search(String key) {
		int level, index, length = key.length();
		TrieNode pCrawl = root;
		for(level = 0; level < length; level++) {
			index = (int)key.charAt(level) - (int)'a';
			if (pCrawl.children[index] == null) {
				return false;
			}
			//System.out.println("wordCount =" + pCrawl.wordCount);
			pCrawl = pCrawl.children[index];
		}
		return (pCrawl != null && pCrawl.isLeaf == true);
	}

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    boolean startsWith(String prefix) {
    	int length = prefix.length();
    	int index;
    	TrieNode pCrawl = root;
    	for(int level = 0; level < length; level++) {
    		index = (int)prefix.charAt(level) - (int)'a';
    		if(pCrawl.children[index] == null) {
    			return false; //not present
    		}
    		pCrawl = pCrawl.children[index];
    	}
    	return true; //if it reaches, all the characters in prefix were present
	}
	boolean isMoreKeysPresentUnderNode(TrieNode node) {
		for (int i=0; i < node.children.length; i++ ) {
			if(node.children[i] != null) {
				return true;
			}
		}
		return false;
	}
	boolean remove(String text) {
		int index , length = text.length();
		TrieNode pCrawl = root;
		for (int i=0; i < length; i++ ) {
			index = (int)text.charAt(i) - (int)'a';
			if(pCrawl.children[index] == null) {
				return false;
			}
			pCrawl = pCrawl.children[index];
		}
		if(!pCrawl.isLeaf) {
			return false;
		}
		pCrawl.isLeaf = false;
		int counter = text.length()-1;

		TrieNode parent = pCrawl.parent;
		while(!isMoreKeysPresentUnderNode(pCrawl) && !pCrawl.isLeaf) { //there must NOT be any
			//more keys under this node, also this node itself must NOT be a leaf node
			index = (int)text.charAt(counter)-(int)'a';
			parent.children[index] = null;
			pCrawl = parent;
			parent = pCrawl.parent;
			counter--;
		}
		return true;
	}
}

public class TriePractice {
	public static void main(String []args) {
		Trie trie = new Trie();
		String[] data = new String[]{"the","there","their","any","answer","bye"};
		for(String text:data) {
			trie.insert(text);
		}
		// for(String text:data) {
		// 	System.out.println(trie.search(text));
		// }
		System.out.println("Before remove :" + trie.search("their"));
		System.out.println(trie.remove("their"));		
		System.out.println("After remove :" + trie.search("their"));
	
		System.out.println("Before remove :" + trie.search("there"));
		System.out.println(trie.remove("there"));		
		System.out.println("After remove :" + trie.search("there"));

		System.out.println("Before remove :" + trie.search("the"));

		System.out.println("Before remove :" + trie.search("bye"));
		System.out.println(trie.remove("bye"));		
		System.out.println("After remove :" + trie.search("bye"));

	}

}