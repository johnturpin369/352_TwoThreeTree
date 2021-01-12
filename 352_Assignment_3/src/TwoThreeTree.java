
public class TwoThreeTree extends Tree {

	//private Node root;
	//private Node curr;
	private static int comparisons;
	private static int numThreeNodesCreated;
	protected static int numAddOperations;
	protected static int numFindOperations;
	private static int numRemoveOperations;
	
	public TwoThreeTree(int key1) {
		this.root = new TwoNode(key1, null);
		this.curr = this.root;
	}
	
	public void Insert(int val) {
		
		if(curr.IsLeaf()) {
			if(!curr.IsThreeNode()) {
				
				// if inserting into a two Node leaf, 
				// create a new three node leaf with val and key assigned as the new keys in ascending order
				if (curr.IsRoot()) {
					numThreeNodesCreated++;
					this.root = (curr.NewValIsLessThanKeys(val)) ? 
								new ThreeNode(val, curr.GetKey()[0], curr.parent) : 
								new ThreeNode(curr.GetKey()[0], val, curr.parent);
				}
				else {
					numThreeNodesCreated++;
					curr = curr.NewValIsLessThanKeys(val) ? 
								new ThreeNode(val,  curr.GetKey()[0], curr.parent) :
								new ThreeNode(curr.GetKey()[0], val, curr.parent);
					
					if(curr.IsLeftChild()) {
						curr.parent.SetLeftChild(curr, curr.parent);
					}
					else if(curr.parent.IsThreeNode()) {
						
						if(((ThreeNode) curr).IsMiddleChild()) {
							((ThreeNode) curr.parent).SetMiddleChild(curr, curr.parent);
						}
					}
					else {
						curr.parent.SetRightChild(curr,  curr.parent);
					}
				}
			}
			else {
				// if inserting into a three node leaf, promote middle value and split children
				Promote(val);	
			}
		}
		else {
			
			// if current node is not a leaf, move to appropriate child and call on Insert again
			if(curr.IsThreeNode()) {
				if(val < curr.GetKey()[0]) {
					curr = curr.GetLeftChild();
					comparisons = comparisons+2;
					Insert(val);
				}
				else if(val == curr.GetKey()[0]) {
					comparisons = comparisons + 3;
				}
				else if(val < curr.GetKey()[1]) {
					curr = ((ThreeNode) curr).GetMiddleChild();
					comparisons = comparisons + 4;
					Insert(val);
				}
				else if(val == curr.GetKey()[0]){
					comparisons = comparisons + 5;
				}
				else {
					curr = curr.GetRightChild();
					comparisons = comparisons + 6;
					Insert(val);
				}
			}
			else {
				if(val < curr.GetKey()[0]) {
					curr = curr.GetLeftChild();
					comparisons = comparisons + 2;
					Insert(val);
				}
				else if(val == curr.GetKey()[0]) {
					comparisons = comparisons + 3;
				}
				else {
					curr = curr.GetRightChild();
					comparisons = comparisons + 4;
					Insert(val);
				}
			}
		}
		
		this.curr = this.root;
	}
	
	public boolean Find(int val) {
		
		if(curr == null) {
			return false;
		}
		
		if(!curr.IsThreeNode()) {
			if(val < curr.GetKey()[0]) {
				curr = curr.GetLeftChild();
			}
			else if(val == curr.GetKey()[0]) {
				curr = root;
				return true;
			}
			else {
				curr = curr.GetRightChild();
			}
		}
		else {
			if(val < curr.GetKey()[0]) {
				curr = curr.GetLeftChild();
			}
			else if(val == curr.GetKey()[0]) {
				curr = root;
				return true;
			}
			else if(val < curr.GetKey()[1]) {
				curr = ((ThreeNode) curr).GetMiddleChild();
			}
			else if(val == curr.GetKey()[1]) {
				curr = root;
				return true;
			}
			else {
				curr = curr.GetRightChild();
			}
		}
		curr = root;
		return false;
	}
	
	// arguments must be placed correctly, as method uses the fact that key1 and key2 are already ordered
	public int GetMedianOfThree(int key1, int key2, int val) {
		
		return (val < key1) ? key1 : ((val > key2) ? key2 : val);
	}
	
	public int[] GetOrderedTriplet(int key1, int key2, int val) {
		
		if(val < key1) {
			comparisons++;
			int[] triplet = new int[]{val, key1, key2};
			return triplet;
		}
		if(val > key2) {
			comparisons++;
			int[] triplet = new int[]{key1, key2, val};
			return triplet;
		}
		else {
			comparisons++;
			int[] triplet = new int[]{key1, val, key2};
			return triplet;
		}
	}
	
	// creates a sub BST, containing values of leaf and val being inserted
	public TwoNode GetMiniBST(int key1, int key2, int val) {
		
		TwoNode miniRoot;
		
		if(val < key1) {
			comparisons++;
			miniRoot = new TwoNode(key1, null);
			miniRoot.SetLeftChild(new TwoNode(val, miniRoot), null);
			miniRoot.SetRightChild(new TwoNode(key2, miniRoot), null);
		}
		else if (val > key2) {
			comparisons = comparisons+2;
			miniRoot = new TwoNode(key2, null);
			miniRoot.SetLeftChild(new TwoNode(key1, miniRoot), null);
			miniRoot.SetRightChild(new TwoNode(val, miniRoot), null);
		}
		else {
			comparisons = comparisons+2;
			miniRoot = new TwoNode(val, null);
			miniRoot.SetLeftChild(new TwoNode(key1, miniRoot), null);
			miniRoot.SetRightChild(new TwoNode(key2, miniRoot), null);
		}
		
		return miniRoot;	
	}

	public void Promote(int val) {
		
		Node parent = curr.GetParent();
		
		if(curr.IsRoot()) {
			if(curr.IsLeaf()) {
				comparisons = comparisons+2;
				this.root =  GetMiniBST(curr.GetKey()[0], curr.GetKey()[1], val);
			}
			else if (curr.IsThreeNode()) {
				comparisons = comparisons+3;
				this.root =  GetMiniBST(curr.GetKey()[0], curr.GetKey()[1], val);
				root.leftChild.SetLeftChild(curr.GetLeftChild(), root);
				root.leftChild.SetRightChild(curr.GetRightChild(), parent);
			}
			else {
				comparisons = comparisons+3;
				this.root = this.curr;
			}
		}
		else if(!parent.IsThreeNode()) {
			
			// if promoting into two node and curr is two node
			ThreeNode newCurr;
			
			if(!curr.IsThreeNode()) {
				if(curr.IsLeftChild()) {
					comparisons = comparisons+4;
					numThreeNodesCreated++;
					newCurr = new ThreeNode(curr.GetKey()[0], curr.parent.GetKey()[0], curr.parent.parent);
					newCurr.SetLeftChild(curr.GetLeftChild(), newCurr);
					newCurr.SetMiddleChild(curr.GetRightChild(), newCurr);
					newCurr.SetRightChild(curr.parent.GetRightChild(), newCurr);
				}
				else {
					comparisons = comparisons+4;
					numThreeNodesCreated++;
					newCurr = new ThreeNode(curr.parent.GetKey()[0], curr.GetKey()[0], curr.parent.parent);
					newCurr.SetLeftChild(curr.parent.GetLeftChild(), newCurr);
					newCurr.SetMiddleChild(curr.GetLeftChild(), newCurr);
					newCurr.SetRightChild(curr.GetRightChild(), newCurr);
				}
			}
			else {
				// if promoting into a two node, and curr is three node
				TwoNode bst = GetMiniBST(curr.GetKey()[0], curr.GetKey()[1], val);
				
				if(bst.GetKey()[0] < parent.GetKey()[0]) {
					comparisons = comparisons+3;
					numThreeNodesCreated++;
					newCurr = new ThreeNode(bst.GetKey()[0], parent.GetKey()[0], parent.GetParent());
					newCurr.SetLeftChild(bst.GetLeftChild(), newCurr);
					newCurr.SetMiddleChild(bst.GetRightChild(), newCurr);
					newCurr.SetRightChild(parent.GetRightChild(), newCurr);
				}
				else{
					comparisons = comparisons+3;
					numThreeNodesCreated++;
					newCurr = new ThreeNode(parent.GetKey()[0], bst.GetKey()[0], parent.GetParent());
					newCurr.SetLeftChild(parent.GetLeftChild(), newCurr);
					newCurr.SetMiddleChild(bst.GetLeftChild(), newCurr);
					newCurr.SetRightChild(bst.GetRightChild(), newCurr);
				
				}
			}
				
			if(newCurr.GetParent() == null) {
				this.root = newCurr; 
			}
			else if(!newCurr.GetParent().IsThreeNode()) {
				if(newCurr.IsLeftChild()){
					newCurr.GetParent().SetLeftChild(newCurr, newCurr.GetParent());
				}
				else {
					newCurr.GetParent().SetRightChild(newCurr, newCurr.GetParent());
				}
			}
			else {
				if((newCurr.GetKey()[1] < newCurr.GetParent().GetKey()[0])){
					newCurr.GetParent().SetLeftChild(newCurr, newCurr.GetParent());
				}
				else if (newCurr.GetKey()[0] > newCurr.GetParent().GetKey()[1]) {
					newCurr.GetParent().SetRightChild(newCurr, newCurr.GetParent());
				}
				else {
					((ThreeNode) newCurr.GetParent()).SetMiddleChild(newCurr, newCurr.GetParent());
				}
			}
		}
		else {
			// if parent is three node
			if(curr.IsThreeNode()) {
				if(curr.IsLeftChild()) {
					 TwoNode lBst = GetMiniBST(curr.GetKey()[0], curr.GetKey()[1], val);
					 lBst.SetParent(curr.parent.parent);
					 TwoNode rBst = new TwoNode(curr.GetParent().GetKey()[1], curr.parent.parent);
					 rBst.SetLeftChild(((ThreeNode) curr.parent).GetMiddleChild(), rBst);
					 rBst.SetRightChild(curr.parent.GetRightChild(), rBst);
					 
					 curr = new TwoNode(curr.parent.GetKey()[0], curr.parent.parent);
					 curr.SetLeftChild(lBst, curr);
					 curr.SetRightChild(rBst, curr);
					 
					 Promote(curr.GetKey()[0]);
				 }
				 else if(curr.IsRightChild()) {
					 TwoNode rBst = GetMiniBST(curr.GetKey()[0], curr.GetKey()[1], val);
					 rBst.SetParent(curr.parent.parent);
					 TwoNode lBst = new TwoNode(curr.parent.GetKey()[0], curr.parent.parent);
					 lBst.SetLeftChild(curr.parent.GetLeftChild(), lBst);
					 lBst.SetRightChild(((ThreeNode) curr.parent).GetMiddleChild(), lBst);
					 
					 curr = new TwoNode(curr.parent.GetKey()[1], curr.parent.parent);
					 curr.SetLeftChild(lBst, curr);
					 curr.SetRightChild(rBst, curr);
					 
					 Promote(curr.GetKey()[0]);
				 }
				 else {
					 int[] triplet = GetOrderedTriplet(curr.GetKey()[0], curr.GetKey()[1], val);
					 numThreeNodesCreated++;
					 curr = new ThreeNode(triplet[0], triplet[2], curr.parent);
					 
					 TwoNode lBst = new TwoNode(curr.parent.GetKey()[0], curr.parent.parent);
					 lBst.SetLeftChild(curr.parent.GetLeftChild(), lBst);
					 lBst.SetRightChild(new TwoNode(curr.GetKey()[0], lBst), lBst);
					 
					 TwoNode rBst = new TwoNode(curr.parent.GetKey()[1], curr.parent.parent);
					 rBst.SetLeftChild(new TwoNode(curr.GetKey()[1],  rBst), rBst);
					 rBst.SetRightChild(curr.parent.GetRightChild(), rBst);
					 
					 curr = new TwoNode(triplet[1], curr.parent.parent);
					 curr.SetLeftChild(lBst, curr);
					 curr.SetRightChild(rBst, curr);
					 
					 Promote(triplet[1]);
				 }
			}
			else {
				// if promoting into threeNode parent and curr is two node (and not a leaf)
				if(curr.IsLeftChild()) {
					TwoNode newParent = new TwoNode(curr.parent.GetKey()[0], curr.parent.parent);
					
					TwoNode rBst = new TwoNode(curr.parent.GetKey()[1], newParent);
					rBst.SetLeftChild(((ThreeNode) curr.parent).GetMiddleChild(), rBst);
					rBst.SetRightChild(curr.parent.GetRightChild(), rBst);
					
					newParent.SetLeftChild(curr, newParent);
					newParent.SetRightChild(rBst, newParent);
					
					curr = newParent;
					
					Promote(curr.GetKey()[0]);
				}
				else if(curr.IsRightChild()) {
					TwoNode newParent = new TwoNode(curr.parent.GetKey()[1], curr.parent.parent);
					
					TwoNode lBst = new TwoNode(curr.parent.GetKey()[0], newParent);
					lBst.SetLeftChild(curr.parent.GetLeftChild(), lBst);
					lBst.SetRightChild(((ThreeNode) curr.parent).GetMiddleChild(), lBst);
					
					newParent.SetLeftChild(lBst, newParent);
					newParent.SetRightChild(curr, newParent);
					
					curr = newParent;
					
					Promote(curr.GetKey()[0]);
					
				}
				else {
				
					TwoNode newParent = new TwoNode(val, curr.parent.parent);
					
					TwoNode lBst = new TwoNode(curr.parent.GetKey()[0], newParent);
					lBst.SetLeftChild(curr.parent.GetLeftChild(), lBst);
					lBst.SetRightChild(curr.GetLeftChild(), lBst);
					
					TwoNode rBst = new TwoNode(curr.parent.GetKey()[1], newParent);
					rBst.SetLeftChild(curr.GetRightChild(), root);
					rBst.SetRightChild(curr.parent.GetRightChild(), rBst);
					
					newParent.SetLeftChild(lBst, newParent);
					newParent.SetRightChild(rBst, newParent);
					
					curr = newParent;
					
					Promote(curr.GetKey()[0]);
				}
			}
		}
	}
	
	public void DisplayStatistics() {
		System.out.println(comparisons + " Comparisons Made");
		System.out.println(numThreeNodesCreated + " Three Nodes Created");
		System.out.println(numAddOperations + " Add Operations");
		System.out.println(numFindOperations + " Find Operations");
		System.out.println(numRemoveOperations + " Remove Operations");
	}
}
