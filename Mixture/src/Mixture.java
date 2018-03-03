import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Mixture {

	public static void main(String args[]) {

		
		
		ArrayList<String> tempList = new ArrayList<String>();
		File fileName = new File("solutions.txt");

		String[] desiredSolutionRatio = null;
		
		/*
		 * Read every line from the solution file
		 */
		try {
			Scanner fileReader = new Scanner(fileName);
			String line = fileReader.nextLine();
			desiredSolutionRatio = line.split(":");
			do {
				line = fileReader.nextLine();
				tempList.add(line);
			} while (fileReader.hasNextLine());
		} catch (FileNotFoundException fnfe) {
			System.err.println("File not found");
		}

		/*
		 * Convert the arraylist to two dimensional array
		 */
		int rowSize = tempList.size();
		int columnSize = desiredSolutionRatio.length;
		int size = rowSize;
		long[][] tempMatrix = new long[rowSize][columnSize];
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < columnSize; j++) {
				String[] tempLine = tempList.get(i).split(":");
				tempMatrix[i][j] = Long.parseLong(tempLine[j]);
			}
		}
		
		/*
		 * Transpose the matrix
		 */
		String[][] finalMatrix = new String[columnSize][rowSize];
		for (int i = 0; i < columnSize; i++) {
			for (int j = 0; j < rowSize; j++) {
				finalMatrix[i][j] = tempMatrix[j][i]+"/1";
			}

		}
		
		//Convert desiredSolutionRatio into a matrix
		String yMatrix[] = new String[desiredSolutionRatio.length];
		for(int i=0;i<yMatrix.length;i++){
			yMatrix[i]=desiredSolutionRatio[i];
		}
		
		
		Utility utility = new Utility(size);
		String[] desiredRatio = new String[size];
		
		utility.createLUDecomposition(finalMatrix,size);
		utility.initializeY(yMatrix,size);
		utility.performLYB(size);
		desiredRatio= utility.performUXY(size);
		
		/*
		 * Writing the answer to a txt file
		 */
		try {
			//FileWriter writer = new FileWriter("answer.txt");
			//BufferedWriter bufferWriter = new BufferedWriter(writer);
			PrintWriter writer = new PrintWriter("answer.txt");
			for (int i = 0; i < desiredRatio.length; i++) {
				//bufferWriter.write(desiredRatio[i].substring(0, desiredRatio[i].indexOf("/")));
				//bufferWriter.newLine();
				writer.write(Integer.parseInt(desiredRatio[i].substring(0, desiredRatio[i].indexOf("/")))+"\n");
			}
			writer.close();
			//bufferWriter.close();
		} catch (IOException ioe) {
			System.out.println("Error processing file");
		}		
	}

}
