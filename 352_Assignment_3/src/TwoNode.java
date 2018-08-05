
public class TwoNode extends Node {
	
	private int key;
	private Node leftChild;
	private Node rightChild;
	
	public TwoNode(int key) {
		this.key = key;
	}
	
	// kind of weird i know
	public int[] GetKey() {
		int[] arr = {key};
		
		return arr;
	}
	
	public boolean IsLeaf(){
		return (leftChild == null && rightChild == null);
	}
	
	public boolean IsLessThan(int val) {
		return val < key;
	}
	
	public boolean IsGreaterThan(int val) {
		return val > key;
	}
	
	public boolean IsEqualTo(int val) {
		return val == key;
	}
	
	public boolean IsBetween(int val) {
		return false;
	}
	
	public boolean IsThreeNode() {
		return false;
	}
}
