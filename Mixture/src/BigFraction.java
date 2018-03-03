import java.math.BigInteger;

public class BigFraction {

	public BigFraction() {

	}

	public String add(String A, String B) {
		BigInteger numeratorOfA = new BigInteger(A.substring(0, A.indexOf("/")));
		BigInteger denominatorOfA = new BigInteger(A.substring(A.indexOf("/") + 1, A.length()));
		BigInteger numeratorOfB = new BigInteger(B.substring(0, B.indexOf("/")));
		BigInteger denominatorOfB = new BigInteger(B.substring(B.indexOf("/") + 1, B.length()));
		//long numeratorOfA = Long.parseLong(A.substring(0, A.indexOf("/")));
		//long denominatorOfA = Long.parseLong(A.substring(A.indexOf("/") + 1, A.length()));
		//long numeratorOfB = Long.parseLong(B.substring(0, B.indexOf("/")));
		//long denominatorOfB = Long.parseLong(B.substring(B.indexOf("/") + 1, B.length()));
		BigInteger lcm = new BigInteger(findLCM(denominatorOfA, denominatorOfB));
		//long lcm = findLCM(denominatorOfA, denominatorOfB);
		BigInteger finalNumerator =numeratorOfA.multiply(lcm.divide(denominatorOfA)).add(numeratorOfB.multiply(lcm.divide(denominatorOfB)));
		//long finalNumerator = numeratorOfA * (lcm / denominatorOfA) + numeratorOfB * (lcm / denominatorOfB);
		String finalFraction = findSimplestForm(finalNumerator, lcm);
		return finalFraction;

	}

	public String subtract(String A, String B) {

		BigInteger numeratorOfA = new BigInteger(A.substring(0, A.indexOf("/")));
		BigInteger denominatorOfA = new BigInteger(A.substring(A.indexOf("/") + 1, A.length()));
		BigInteger numeratorOfB = new BigInteger(B.substring(0, B.indexOf("/")));
		BigInteger denominatorOfB = new BigInteger(B.substring(B.indexOf("/") + 1, B.length()));
		//long numeratorOfA = Long.parseLong(A.substring(0, A.indexOf("/")));
		//long denominatorOfA = Long.parseLong(A.substring(A.indexOf("/") + 1, A.length()));
		//long numeratorOfB = Long.parseLong(B.substring(0, B.indexOf("/")));
		//long denominatorOfB = Long.parseLong(B.substring(B.indexOf("/") + 1, B.length()));
		BigInteger lcm = new BigInteger(findLCM(denominatorOfA, denominatorOfB));
		//long lcm = findLCM(denominatorOfA, denominatorOfB);
		BigInteger finalNumerator =numeratorOfA.multiply(lcm.divide(denominatorOfA)).subtract(numeratorOfB.multiply(lcm.divide(denominatorOfB)));
		//long finalNumerator = numeratorOfA * (lcm / denominatorOfA) - numeratorOfB * (lcm / denominatorOfB);
		String finalFraction = findSimplestForm(finalNumerator, lcm);
		return finalFraction;

	}
	
	public String multiply(String A, String B) {
		BigInteger numeratorOfA = new BigInteger(A.substring(0, A.indexOf("/")));
		BigInteger denominatorOfA = new BigInteger(A.substring(A.indexOf("/") + 1, A.length()));
		BigInteger numeratorOfB = new BigInteger(B.substring(0, B.indexOf("/")));
		BigInteger denominatorOfB = new BigInteger(B.substring(B.indexOf("/") + 1, B.length()));
		//long numeratorOfA = Long.parseLong(A.substring(0, A.indexOf("/")));
		//long denominatorOfA = Long.parseLong(A.substring(A.indexOf("/") + 1, A.length()));
		//long numeratorOfB = Long.parseLong(B.substring(0, B.indexOf("/")));
		//long denominatorOfB = Long.parseLong(B.substring(B.indexOf("/") + 1, B.length()));
		BigInteger finalNumerator = numeratorOfA.multiply(numeratorOfB);
		BigInteger finalDenominator= denominatorOfA.multiply(denominatorOfB);
		//long finalNumerator = numeratorOfA * numeratorOfB;
		//long finalDenominator = denominatorOfA * denominatorOfB;
		String finalFraction = findSimplestForm(finalNumerator, finalDenominator);
		return finalFraction;
	}
	
	public String divide(String A, String B) {
		BigInteger numeratorOfA = new BigInteger(A.substring(0, A.indexOf("/")));
		BigInteger denominatorOfA = new BigInteger(A.substring(A.indexOf("/") + 1, A.length()));
		BigInteger denominatorOfB = new BigInteger(B.substring(0, B.indexOf("/")));
		BigInteger numeratorOfB = new BigInteger(B.substring(B.indexOf("/") + 1, B.length()));
		
		//long numeratorOfA = Long.parseLong(A.substring(0, A.indexOf("/")));
		//long denominatorOfA = Long.parseLong(A.substring(A.indexOf("/") + 1, A.length()));
		//long denominatorOfB = Long.parseLong(B.substring(0, B.indexOf("/")));
		//long numeratorOfB = Long.parseLong(B.substring(B.indexOf("/") + 1, B.length()));
		BigInteger zero = new BigInteger("0");
		if(denominatorOfB.compareTo(zero)==-1){
			denominatorOfB = denominatorOfB.negate();
			numeratorOfB = numeratorOfB.negate();
		}
		BigInteger finalNumerator = numeratorOfA.multiply(numeratorOfB);
		BigInteger finalDenominator= denominatorOfA.multiply(denominatorOfB);
		
		//long finalNumerator = numeratorOfA * numeratorOfB;
		//long finalDenominator = denominatorOfA * denominatorOfB;
		
		String finalFraction = findSimplestForm(finalNumerator, finalDenominator);
		return finalFraction;
	}

	private String findLCM(BigInteger a, BigInteger b) {
		BigInteger max = null;
		BigInteger min = null;
		if (a.compareTo(b)==1) {
			max = a;
			min = b;
		} else {
			max = b;
			min = a;
		}

		BigInteger x = null;
		BigInteger temp = min;
		BigInteger zero = new BigInteger("0");
		BigInteger one = new BigInteger("1");
		BigInteger count=one;
		while(!temp.equals(zero)){
			
			x = max.multiply(count);
			if(x.mod(min).equals(zero)){
				return x.toString();
			}
			temp=temp.subtract(one);
			count=count.add(one);
			
		}
		return null;
	}

	public String findSimplestForm(BigInteger a, BigInteger b) {
		BigInteger numerator = a;
		BigInteger denominator = b;
		BigInteger zero = new BigInteger("0");
		BigInteger one = new BigInteger("1");
		if(!a.equals(zero)){
		if (a.mod(b).equals(zero)) {
			numerator = a.divide(b);
			denominator = new BigInteger("1");
		} else {
			BigInteger factor = GCF(numerator, denominator);
			while (!factor.equals(one)) {
				numerator = numerator.divide(factor);
				denominator = denominator.divide(factor);
				factor = GCF(numerator, denominator);
			}

		}
		}
		String finalForm = numerator.toString() + "/" + denominator.toString();
		return finalForm;
	}

	private BigInteger GCF(BigInteger a, BigInteger b) {
		BigInteger zero = new BigInteger("0");
		if (b.equals(zero)) {
			return a.abs();
		} else {
			return GCF(b, a.mod(b));
		}
	}
}
