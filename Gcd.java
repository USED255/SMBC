package jp.local.src.main;

public class Gcd {

	public int gcd(int radixA, int radixB){
		
		return radixB == 0 ? radixA : gcd(radixB, radixA % radixB);
		
	}
	
	
	
}
