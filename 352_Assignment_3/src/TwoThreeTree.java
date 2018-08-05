
public class TwoThreeTree extends Tree {

	private Node root;
	private Node curr = root;
	
	public TwoThreeTree(int val) {
		root = new TwoNode(val);
	}
	
	public boolean IsEmpty() {
		return root == null;
	}
	
	public void Insert(int val) {
		
		if(curr.IsLessThan(val)) {
			if(curr.IsLeaf()) {
				if(!curr.IsThreeNode()) {
					curr = new ThreeNode(val, curr.GetKey()[0]);
				}
				else {
					
				}
			}
		}
	}
	
	public Node Find(int val) {
		if (curr.IsLessThan(val)) {
			if(curr.IsLeaf()) {
				return null;
			}
			else {
				curr = curr.GetLeftChild();
				Find(val);
			}
		}
		else if (curr.IsGreaterThan(val)) {
			if(curr.IsLeaf()) {
				return null;
			}
			else {
				curr = curr.GetRightChild();
				Find(val);
			}
		}
		else if(curr.IsThreeNode()){
			if(curr.IsBetween(val)) {
				if(curr.IsLeaf()) {
					return null;
				}
				else {
					curr = ((ThreeNode) curr).GetMiddleChild();
				}
			}
				
		}
		
		return curr;
	}
	
	// arguments must be placed correctly, as method uses the fact that key1 and key2 are already ordered
	public int GetMedianOfThree(int key1, int key2, int val) {
		
		return (val < key1) ? key1 : ((val > key2) ? key2 : val);
	}
	
	

}
