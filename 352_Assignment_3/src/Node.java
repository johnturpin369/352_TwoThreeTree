
public abstract class Node {
	
	private Node parent;
	private Node leftChild;
	private Node rightChild;
	
	public Node GetParent() {
		return parent;
	}
	
	public void SetParent(Node parent) {
		this.parent = parent;
	}
	
	public Node GetLeftChild() {
		return leftChild;
	}
	
	public void SetLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}
	
	public Node GetRightChild() {
		return rightChild;
	}
	
	public void SetRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}
	
	// returns the key value(s) of a node.
	public abstract int[]	GetKey();
	
	// returns true if val is less than smallest key value. 
	public abstract boolean IsLessThan(int val);
	
	// return true if val is greater than greatest key value.
	public abstract boolean IsGreaterThan(int val);
	
	// returns true if val is equal to a key value.
	public abstract boolean IsEqualTo(int val);
	
	// returns true if val is in between key values of a ThreeNode.
	public abstract boolean IsBetween(int val);
	
	// returns true if current node is a ThreeNode. 
	public abstract boolean IsThreeNode();
	
	// returns true if current node is a leaf.
	public abstract boolean IsLeaf();
}
