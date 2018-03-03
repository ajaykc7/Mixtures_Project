
public class DeterminantStorage {

	private long matrix[][];
	private long determinant;
	
	public DeterminantStorage(long matrix[][], long determinant){
		this.matrix = matrix;
		this.determinant = determinant;
	}
	
	public long[][] getMatrix(){
		return matrix;
	}
	
	public long getDeterminant(){
		return determinant;
	}
	
}
