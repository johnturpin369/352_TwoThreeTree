
public class ThreeNode extends Node {
	
	private int key1;
	private int key2;
	private Node parent;
	private Node leftChild;
	private Node middleChild;
	private Node rightChild;
	
	public ThreeNode(int key1, int key2) {
		this.key1 = key1;
		this.key2 = key2;
	}
	
	public int[] GetKey() {
		int[] arr = {key1, key2};
		
		return arr;
	}
	
	public Node GetMiddleChild() {
		return middleChild;
	}
	
	public void SetMiddleChild(Node middleChild) {
		this.middleChild = middleChild;
	}
	
	public boolean IsLeaf() {
		return (leftChild == null && middleChild == null && rightChild == null); // shouldn't have to check all
	}
	
	public boolean IsFull() {
		return (leftChild.IsThreeNode() && middleChild.IsThreeNode() && rightChild.IsThreeNode());
	}
	
	public boolean IsLessThan(int val) {
		return val < key1;
	}
	
	public boolean IsGreaterThan(int val) {
		return val > key2;
	}
	
	public boolean IsBetween(int val) {
		return val > key1 && val < key2;
	}
	
	public boolean IsEqualTo(int val) {
		return val == key1 || val == key2;
	}
	
	public boolean IsThreeNode() {
		return true;
	}
}
