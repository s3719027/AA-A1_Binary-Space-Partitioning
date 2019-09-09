import java.io.FileReader;
import java.io.BufferedReader;
import java.io.*;

class TaskB_S3 {
	public static void main(String[] args) throws FileNotFoundException, IOException{
		
		
		BSPTree<String> seqtree = new SequentialRepresentation<String>();
		BSPTree<String> linktree = new LinkedRepresentation<String>();
		
		BufferedReader bReader = new BufferedReader(new FileReader(args[0]));
		String line = "";
		String[] lineParts;
		
		
		//add root node
		seqtree.setRootNode(bReader.readLine());
		
		//run splits for seqtree
		while ((line = bReader.readLine()) != null) {
			
			lineParts = line.split("\\s");
			seqtree.splitNode(lineParts[0], lineParts[1], lineParts[2]);
		}
		
		bReader = new BufferedReader(new FileReader(args[0]));
		line = "";
		
		//add root node
		linktree.setRootNode(bReader.readLine());
		
		//run splits for seqtree
		while ((line = bReader.readLine()) != null) {
			
			lineParts = line.split("\\s");
			linktree.splitNode(lineParts[0], lineParts[1], lineParts[2]);
		}
		
		long startTime;
		long endTime;

		
		PrintWriter pw = new PrintWriter(new File("PartB_S3.txt"));
		
		//start time
		startTime = System.nanoTime();
		
		seqtree.printInPreorder(pw);
		
		//end time
		endTime = System.nanoTime();

		//time in secs
		double estimatedTimePre = ((double)(endTime - startTime)) / Math.pow(10, 9);
		
		
		//start time
		startTime = System.nanoTime();
		
		seqtree.printInInorder(pw);
		
		//end time
		endTime = System.nanoTime();
	
		//time in secs
		double estimatedTimeIn = ((double)(endTime - startTime)) / Math.pow(10, 9);
		
		
		//start time
		startTime = System.nanoTime();
		
		seqtree.printInPostorder(pw);
		
		//end time
		endTime = System.nanoTime();
	
		//time in secs
		double estimatedTimePost = ((double)(endTime - startTime)) / Math.pow(10, 9);
		
		System.out.println("Time taken sequential;\nPreorder:\t" + estimatedTimePre + " seconds"
		+ "\nInorder:\t" + estimatedTimeIn + " seconds" + "\nPostorder:\t" + estimatedTimePost + " seconds");
		
		
		
		//start time
		startTime = System.nanoTime();
		
		linktree.printInPreorder(pw);
		
		//end time
		endTime = System.nanoTime();

		//time in secs
		estimatedTimePre = ((double)(endTime - startTime)) / Math.pow(10, 9);
		
		
		//start time
		startTime = System.nanoTime();
		
		linktree.printInInorder(pw);
		
		//end time
		endTime = System.nanoTime();
	
		//time in secs
		estimatedTimeIn = ((double)(endTime - startTime)) / Math.pow(10, 9);
		
		
		//start time
		startTime = System.nanoTime();
		
		linktree.printInPostorder(pw);
		
		//end time
		endTime = System.nanoTime();
	
		//time in secs
		estimatedTimePost = ((double)(endTime - startTime)) / Math.pow(10, 9);
		
		System.out.println("Time taken linked;\nPreorder:\t" + estimatedTimePre + " seconds"
		+ "\nInorder:\t" + estimatedTimeIn + " seconds" + "\nPostorder:\t" + estimatedTimePost + " seconds");
	
	}
}