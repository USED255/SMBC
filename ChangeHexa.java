/**
 * 
 */
package jp.local.src.main;

import java.io.UnsupportedEncodingException;

/**
 *
 */
public class ChangeHexa {


	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		String target = args[0];
		
		int intHex = 0;
		
			for(int index = 0 ; index < target.length() ; index++){
				String strChar = String.valueOf(target.charAt(index));
				String hexString = getHexa(strChar);
				intHex = intHex + Integer.parseInt(hexString, 16);
			}
			
			System.out.println(target +"\n");
			
			System.out.println("１６進数:  " + Integer.toHexString(intHex).toUpperCase());
			System.out.println("１０進数:  " + intHex);
			System.out.println("０８進数:  " +  Integer.toOctalString(intHex));
			System.out.println("０２進数:  " +  Integer.toBinaryString(intHex));
	}

	/**
	 * 
	 * @param target
	 * @throws UnsupportedEncodingException
	 */
	public static String getHexa(String target) throws UnsupportedEncodingException {
		char[] buf = new String(target.getBytes("MS932"), "8859_1").toCharArray();
		return Integer.toHexString(buf[0]) + Integer.toHexString(buf[1]);
	}
}
