
public class ThreeNode extends Node {
	
	protected int key2;
	private Node middleChild;
	
	public ThreeNode(int key1, int key2, Node parent) {
		this.key1 = key1;
		this.key2 = key2;
		this.parent = parent;
	}
	
	public int[] GetKey() {
		int[] arr = {key1, key2};
		
		return arr;
	}
	
	public Node GetMiddleChild() {
		return middleChild;
	}
	
	public void SetMiddleChild(Node middleChild, Node parent) {
		this.middleChild = middleChild;
		
		if(parent != null) {
			this.middleChild.parent = parent;
		}
	}
	
	public boolean IsMiddleChild() {
		return key1 > ((ThreeNode) parent).key1 && key2 < ((ThreeNode) parent).key2;
	}
	
	public boolean IsLeaf() {
		return (leftChild == null && middleChild == null && rightChild == null); // shouldn't have to check all
	}
	
	public boolean NewValIsLessThanKeys(int val) {
		return val < key1;
	}
	
	public boolean NewValIsGreaterThanKeys(int val) {
		return val > key2;
	}
	
	public boolean NewValIsBetweenKeys(int val) {
		return val > key1 && val < key2;
	}
	
	public boolean IsThreeNode() {
		return true;
	}

}
