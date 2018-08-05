
public abstract class Tree {

	/*private Node root;
	private Node[] BST; // binary search tree full of Nodes
	
	public Tree(Node rtNode, int key) {
		root = new Node(key);
		BST[0] = root;
	}*/
	
	public abstract void Insert(int val);
	public abstract Node Find(int val);
	public abstract boolean IsEmpty();
	
	public static TwoNode[] Traversal() {
		// TODO implement pre or post-order traversal
		return null;
	}
}