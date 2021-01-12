
public class TwoNode extends Node {
		
	public TwoNode(int key, Node parent) {
		this.key1 = key;
		this.parent = parent;
	}
	
	public int[] GetKey() {
		int[] arr = {key1};
		
		return arr;
	}
	
	public boolean IsLeaf(){
		return (leftChild == null && rightChild == null);
	}
	
	public boolean NewValIsLessThanKeys(int val) {
		return val < key1;
	}
	
	public boolean NewValIsGreaterThanKeys(int val) {
		return val > key1;
	}
	
	public boolean NewValIsBetweenKeys(int val) {
		return false;
	}
	
	public boolean IsThreeNode() {
		return false;
	}
	
	public boolean IsLeftChild() {
		return this.key1 < this.parent.key1;
	}
}
