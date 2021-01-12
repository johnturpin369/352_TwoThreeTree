import java.io.*;

public class TwoThree {

	private static TwoThreeTree tree = null;
	
	public static void main(String[] args) {

		String file = System.getProperty("user.dir") + "\\" + args[0];
		String line = null;
		boolean treeExists = false;
		
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null) {
				if(line.charAt(0) == 'a' && !treeExists) {
					tree = new TwoThreeTree(Integer.parseInt(line.substring(1, line.length())));
					TwoThreeTree.numAddOperations++;
					treeExists = true;
				}
				else if(line.charAt(0) == 'a') {
					TwoThreeTree.numAddOperations++;
					tree.Insert(Integer.parseInt(line.substring(1, line.length())));
					tree.curr = tree.root;
				}
				else if(line.charAt(0) == 'f') {
					TwoThreeTree.numFindOperations++;
					tree.Find(Integer.parseInt(line.substring(1,  line.length())));
				}
			}
			
			tree.DisplayStatistics();
			System.out.print("Pre-order traversal after step " + args[1] + ": ");
			Tree.Traversal(tree.root, Integer.parseInt(args[1]));
			bufferedReader.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("File: \"" + file + "\" not found");
			ex.printStackTrace();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
