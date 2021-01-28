/**
 * 
 */
package jp.local.src.main;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author t-hitozawa
 *
 */
public class Factorization {

	/**
	 * 
	 */
	public Factorization() {
		// TODO Auto-generated constructor stub
	}
	public HashMap<Integer,Integer> doAnswer(int radix){
		
		ArrayList<Integer> ret = new ArrayList<Integer>();
		
		
		// 2 で割れるだけ割り算する
		while(radix % 2 == 0){
			ret.add(2);
			radix /= 2;
		}
		// 奇数で割り算していく
		for(int intOdd = 3; intOdd * intOdd <= radix; intOdd += 2){
			while(radix % intOdd == 0){
				radix /= intOdd;
				ret.add(intOdd);
			}
		}
		if(radix > 1) {
			ret.add(radix);
		}
		
		
		HashMap<Integer,Integer> map = new HashMap<>();
		
		int count = 1;
		for(int i = 0 ; i < ret.size()  ; i++){
			if(i + 1 < ret.size()){
				if(ret.get(i) != ret.get(i+1)){
					map.put(ret.get(i), count);
					count = 1;
				} else {
					count++;
				}
			} else {
				map.put(ret.get(i), count);
			}
		}
//		for(Integer key : map.keySet()){
//			System.out.println("Key  :  " + key + " val : " + map.get(key));
//		}
		
		
		return map;
		
	}

}
