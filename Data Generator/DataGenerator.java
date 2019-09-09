import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {
		
	private static int NUM_NODES = 100000;

		public void generateData() throws FileNotFoundException{
			
			Random random = new Random(System.currentTimeMillis());
			List<String> rNumbers = new ArrayList<String>();
			PrintWriter pw = new PrintWriter("bsptree.txt");
			
			int rootValue = random.nextInt(NUM_NODES + 1);
			int childValue;
			String line = String.valueOf(rootValue);
			
			rNumbers.add(String.valueOf(rootValue));
			pw.println(line);
			
			int i = 0;
			
			while (rNumbers.size() < NUM_NODES + 1) {
				
				line = String.valueOf(rNumbers.get(i));
				
				while (true) {
					childValue = random.nextInt(NUM_NODES + 1);
					if (!rNumbers.contains(String.valueOf(childValue))) {
						break;
					}
				}
				rNumbers.add(String.valueOf(childValue));
				line = line + " " + String.valueOf(childValue);
				
				while (true) {
					childValue = random.nextInt(NUM_NODES + 1);
					if (!rNumbers.contains(String.valueOf(childValue))) {
						break;
					}
				}
				rNumbers.add(String.valueOf(childValue));
				line = line + " " + String.valueOf(childValue);
				
				pw.println(line);
				i++;				
			}
			pw.close();
		}
	}