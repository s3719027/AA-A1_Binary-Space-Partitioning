import java.io.FileReader;
import java.io.BufferedReader;
import java.io.*;


/*
@author Daniel Bound, 2019
*/

class TaskB_S1 {
	public static void main(String[] args) throws FileNotFoundException, IOException{
		
		
		BSPTree<String> seqtree = new SequentialRepresentation<String>();
		BSPTree<String> linktree = new LinkedRepresentation<String>();
		
		BufferedReader bReader = new BufferedReader(new FileReader(args[0]));
		String line = "";
		String[] lineParts;
		
		//start time
		long startTime = System.nanoTime();
		
		//add root node
		seqtree.setRootNode(bReader.readLine());
		
		//run splits for seqtree
		while ((line = bReader.readLine()) != null) {
			
			lineParts = line.split("\\s");
			seqtree.splitNode(lineParts[0], lineParts[1], lineParts[2]);
		}
		
		//end time
		long endTime = System.nanoTime();

		//time in secs
		double estimatedTimeSeq = ((double)(endTime - startTime)) / Math.pow(10, 9);
		
		
		
		bReader = new BufferedReader(new FileReader(args[0]));
		line = "";
		
		//start time
		startTime = System.nanoTime();
		
		//add root node
		linktree.setRootNode(bReader.readLine());
		
		//run splits for seqtree
		while ((line = bReader.readLine()) != null) {
			
			lineParts = line.split("\\s");
			linktree.splitNode(lineParts[0], lineParts[1], lineParts[2]);
		}
		
		//end time
		endTime = System.nanoTime();
		

		//time in secs
		double estimatedTimeLink = ((double)(endTime - startTime)) / Math.pow(10, 9);
		
		System.out.println("Time taken sequential:\t" + estimatedTimeSeq + " seconds");
		System.out.println("Time taken linked:\t" + estimatedTimeLink + " seconds");
	
	}
}
