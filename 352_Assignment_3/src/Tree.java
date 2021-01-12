
public abstract class Tree {
	
	protected Node root;
	protected Node curr;
	private static int size = 0;
	
	public abstract void Insert(int val);
	public abstract boolean Find(int val);
	
	public static void Traversal(Node curr, int steps) {
		if(curr == null || size >= steps){
			return;
		}
		if(curr.IsThreeNode()) {
			System.out.print(" " + curr.GetKey()[0] + " " + curr.GetKey()[1]);
			size = size + 2;
			Traversal(curr.GetLeftChild(), steps);
			Traversal(((ThreeNode) curr).GetMiddleChild(), steps);
			Traversal(curr.GetRightChild(), steps);
		}
		else {
			System.out.print(" " + curr.GetKey()[0]);
			size++;
			Traversal(curr.GetLeftChild(), steps);
			Traversal(curr.GetRightChild(), steps);
		}	
	}
}