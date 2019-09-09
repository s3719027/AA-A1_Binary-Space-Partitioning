import java.io.FileNotFoundException;

class Main {
	
	private static DataGenerator dg;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		dg = new DataGenerator();
		dg.generateData();
	}
}