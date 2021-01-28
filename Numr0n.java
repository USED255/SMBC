/**
 * 
 */
package jp.console.main;

import java.util.ArrayList;

/**
 * @author tozawa_h01
 *
 */
public class Numr0n {


	private int countEAT_ = 0;
	
	private int countBITE_ = 0;

	/**
	 * 
	 */
	public Numr0n() {
	}
	public Numr0n(String myself, String opponent) {

		judge(myself, opponent);
	}


	private void judge(String myself, String opponent){

		char[] myCharArray = myself.toCharArray();
		char[] opCharArray = opponent.toCharArray();

		ArrayList<Character> myTempList = new ArrayList<Character>();
		ArrayList<Character> opTempList = new ArrayList<Character>();
		
		
		int countEAT = 0;
		int countBITE = 0;
		if(myCharArray.length == opCharArray.length){

			// EAT Check
			for(int index = 0 ; index < myCharArray.length ; index++ ) {
				if(myCharArray[index] == opCharArray[index]){
					countEAT++;
				} else {
					myTempList.add(myCharArray[index]);
					opTempList.add(opCharArray[index]);
				}
			}
			countEAT_ = countEAT;
			
			// BITE Check
			for(int indexA = 0 ; indexA < myTempList.size() ; indexA++) {
				
				for(int indexB = 0 ; indexB < opTempList.size() ; indexB++) {
				
					if(myTempList.get(indexA) == opTempList.get(indexB)){
						countBITE++;
					}
				}
			}
			countBITE_ = countBITE;
		}
	}


	/**
	 * @return countEAT_
	 */
	public int getEAT() {
		return countEAT_;
	}
	/**
	 * @return countBITE_
	 */
	public int getBITE() {
		return countBITE_;
	}



}
