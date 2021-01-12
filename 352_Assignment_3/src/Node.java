
public abstract class Node {
	
	protected int key1;
	protected Node parent;
	protected Node leftChild;
	protected Node rightChild;

	public Node GetParent() {
		return parent;
	}
	
	public void SetParent(Node parent) {
		this.parent = parent;
	}
	
	public Node GetLeftChild() {
		return leftChild;
	}
	
	public void SetLeftChild(Node leftChild, Node parent) {
		this.leftChild = leftChild;
		
		if(parent != null) {
			this.leftChild.parent = parent;
		}
	}
	
	public Node GetRightChild() {
		return rightChild;
	}
	
	public void SetRightChild(Node rightChild, Node parent) {
		this.rightChild = rightChild;
		
		if(parent != null) {
			this.rightChild.parent = parent;
		}
	}
	
	public boolean IsRoot() {
		return this.parent == null;
	}
	
	public boolean IsLeftChild() {
		return key1 < parent.key1;
	}
	
	public boolean IsRightChild() {
		if(this.parent.IsThreeNode()) {
			return this.key1 > ((ThreeNode) parent).key2;
		}
		else {
			return this.key1 > ((ThreeNode) this.parent).key2;
		}
	}
	
	// returns the key value(s) of a node.
	public abstract int[]	GetKey();
	
	// returns true if val is less than smallest key value. 
	public abstract boolean NewValIsLessThanKeys(int val);
	
	// return true if val is greater than greatest key value.
	public abstract boolean NewValIsGreaterThanKeys(int val);
	
	// returns true if val is in between key values of a ThreeNode.
	public abstract boolean NewValIsBetweenKeys(int val);
	
	// returns true if current node is a ThreeNode. 
	public abstract boolean IsThreeNode();
	
	// returns true if current node is a leaf.
	public abstract boolean IsLeaf();
	
	
	
}
