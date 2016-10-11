import java.util.*;

class Node {
	public int data;
	public ArrayList<Node> children;
	Node(int data) {
		this.data = data;
		this.children = new ArrayList<>();
	}
}
class Graph {
	public ArrayList<Node> nodes;
	private HashMap<Integer,Node> nodeSet;
	Graph() {
		nodes = new ArrayList<>();
		nodeSet = new HashMap<>();
	}
	public void addNode(int data) {
		Node node= new Node(data);
		nodes.add(node);
	}
	public Node getNode(Integer data) {
		
	} 
	
}

public class GraphProgram {
	public static void main(String []args) {
		Graph g= new Graph();
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);
	}
}