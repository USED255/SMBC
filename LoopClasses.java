/**
 *
 */
package jp.util;

/**
 * @author PC User
 *
 */
public class LoopClasses {



	public LoopClasses(){

	}


	public void doMain(int maxLoop){


		for(int index = 0 ; index < maxLoop ; index++){

			String strTimeStanp = DateProcessingUtil.setSystemDate(DateProcessingUtil.getIncrementDate(index));
			System.out.println(StringUtil.digitNumber(index + 1, 8) + " : " + strTimeStanp );


		}
	}







}
