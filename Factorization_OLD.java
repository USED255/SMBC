package jp.local.src.main;

import java.util.HashSet;

public class Factorization_OLD {
	public static void main(String[] args){
		HashSet<Integer> set = new HashSet<>();
		
		int intRadix = 21;
	    // 2 で割れるだけ割り算する
	    while(intRadix % 2 == 0){
	      System.out.print(2);
	      System.out.print(" ");
	      intRadix /= 2;
	    }
	    // 奇数で割り算していく
	    for(int intOdd = 3; intOdd * intOdd <= intRadix; intOdd += 2){
	      while(intRadix % intOdd == 0){
	        System.out.print(intOdd + " ");
	        intRadix /= intOdd;
	    	set.add(intOdd);
	      }
	    }
	    if(intRadix > 1) {
	    	System.out.println(intRadix);
	    }
	}
}
