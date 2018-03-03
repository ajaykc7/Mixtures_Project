import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class Utility {

	public String[][] lowerMatrix;
	public String[][] upperMatrix;
	public String[] yMatrix;
	public String[] xMatrix;
	public String[] desiredMatrix;
	public BigFraction fraction;
	public int size;
	public Utility(int size) {
	
		fraction = new BigFraction();
		lowerMatrix = new String[size][size];
		upperMatrix = new String[size][size];
		yMatrix = new String[size];
		xMatrix = new String[size];
		desiredMatrix = new String[size];
		this.size=size;
		// Initialize lowerMatrix such that its a lower triangular matrix
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == j) {
					lowerMatrix[i][j] = 1+"/1";
				} else if (i < j) {
					lowerMatrix[i][j] = 0+"/1";
				} else {
					lowerMatrix[i][j] = -1+"/1";
				}
			}
		}
		
		//Initialize an empy xMatrix
		for(int i=0;i<size;i++){
			xMatrix[i]="0/1";
		}
		
		//Initialize an empy desiredMatrix
				for(int i=0;i<size;i++){
					desiredMatrix[i]="0/1";
				}
	}

	public void initializeY(String yMatrix[],int size){
		for(int i=0;i<size;i++){
			this.yMatrix[i]=yMatrix[i]+"/1";
		}
	}
	public void createLUDecomposition(String matrix[][], int size) {

		// Create a upper triangular matrix from the given matrix

		/*Divide every element in first row by the element in matrix[0][0]
		String firstRowFirstColumnElement = matrix[0][0];
		for (int j = 0; j < size; j++) {
			if (Long.parseLong(matrix[0][0].substring(0, matrix[0][0].indexOf("/"))) < 0) {
				matrix[0][j] = fraction.divide(matrix[0][j] , ("-"+firstRowFirstColumnElement));
			} else {
				matrix[0][j] = fraction.divide(matrix[0][j],firstRowFirstColumnElement);
			}
		}*/
		
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i > j) {
					String factor = fraction.divide(matrix[i][j] , matrix[j][j]);
					reduceRow(matrix, j, i, factor);
					createLMatrix(factor, i, j);
				} else {
				}

			}
		}
		
		//Store the elements of the matrix into the upper triangular matrix
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				upperMatrix[i][j]=matrix[i][j];
			}
		}
		
		
	}

	private void createLMatrix(String factor, int row, int column) {
		//String finalFactor = Long.toString(Integer.parseInt(factor.substring(0,factor.indexOf("/")))*-1);
		//finalFactor = finalFactor+factor.substring(factor.indexOf("/"),factor.length());
		//lowerMatrix[row][column] = finalFactor;
		lowerMatrix[row][column]=factor;
	}

	private void reduceRow(String matrix[][], int factoringRow, int row, String factor) {
		for (int column = 0; column < size; column++) {
			matrix[row][column] = fraction.subtract(matrix[row][column],(fraction.multiply(matrix[factoringRow][column], factor)));
			//matrix[row][column] = matrix[factoringRow][column] * factor - matrix[row][column];
		}
	}
	
	public void performLYB(int size){
		
		for(int i=0;i<size;i++){
			String tempSum="0/1";
			for(int j=0;j<size;j++){
				if(i>=j){
					BigInteger bigInt = new BigInteger(xMatrix[j].substring(0, xMatrix[j].indexOf("/")));
					BigInteger zero = new BigInteger("0");
						if(!bigInt.equals(zero)){
							tempSum = fraction.add(tempSum, fraction.multiply(xMatrix[j], lowerMatrix[i][j]));
							//tempSum= tempSum+(xMatrix[j]*lowerMatrix[i][j]);
						//}
					}
				}
			}
			xMatrix[i] =fraction.divide(fraction.subtract(yMatrix[i], tempSum), lowerMatrix[i][i]);
			//xMatrix[i]=(yMatrix[i]-tempSum)/lowerMatrix[i][i];
			tempSum="0/1";
		}
	}
	
	public String[] performUXY(int size){
		for(int i=size-1;i>=0;i--){
			String tempSum="0/1";
			
			for(int j=size-1;j>=0;j--){
				if(i<=j){
					BigInteger bigInt = new BigInteger(desiredMatrix[j].substring(0, desiredMatrix[j].indexOf("/")));
					BigInteger zero = new BigInteger("0");	
					if(!bigInt.equals(zero)){
							tempSum = fraction.add(tempSum, fraction.multiply(desiredMatrix[j], upperMatrix[i][j]));
						
						
					}
				}
			}
			desiredMatrix[i]=fraction.divide(fraction.subtract(xMatrix[i], tempSum), upperMatrix[i][i]);
			//desiredMatrix[i]=(xMatrix[i]-tempSum)/upperMatrix[i][i];
			tempSum="0/1";
		}
		return desiredMatrix;
	}
	
}
