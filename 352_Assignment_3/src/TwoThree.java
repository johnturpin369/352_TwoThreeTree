import java.io.LineNumberReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TwoThree {

	public static void main(String[] args) {
	
		FileReader file;
		try {
			
			file = new FileReader(args[0]);
			LineNumberReader readLines = new LineNumberReader(file);
			
			for(int i = 0; i < Integer.parseInt(args[1]); i++) {
				
				
				
				// read lines from file to give to tree
				// something like: Integer.parseInt(readLines.readLine());
			}
			
			TwoThreeTree tree = new TwoThreeTree(12);
			
			if(tree.Find(21) == null) {
				System.out.println("Value was not found in tree");
			}
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}

	}

}
