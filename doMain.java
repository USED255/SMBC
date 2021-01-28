/**
 * 
 */
package jp.local.src.main;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author t-hitozawa
 */
public class doMain {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Factorization fat = new Factorization();

		ArrayList<Integer> ret = new ArrayList<>();
		
		// 最小公倍数
		// 2 と10の最少公倍数は 10となる
		
		int radixA = 27;
		int radixB = 81;
		
		HashMap<Integer,Integer> factorA = fat.doAnswer(radixA);
		HashMap<Integer,Integer> factorB = fat.doAnswer(radixB);
		
		
		
		int ans = 1;
		
		for(Integer key : factorA.keySet()){
			
			if(factorB.containsKey(key)){
				
				if(factorA.get(key) > factorB.get(key)){
					
					for(int i = 0 ; i < factorB.get(key) ; i++){
						ans *= key;
					}
					
				} else  {
					for(int i = 0 ; i < factorA.get(key) ; i++){
						ans *= key;
					}
					
				}
			}
		}
		
		Gcd obj = new Gcd();
		
		System.out.println(obj.gcd(radixA, radixB));
		
		System.out.println("最小公倍数 : " + ans);
		System.out.println("最大公約数 : " + radixA * radixB / ans);
		
		
		
		
	}
}
