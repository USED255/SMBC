/**
 *
 */
package jp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

/**
 * @author PC User
 *
 */
public class FileToByte {

	/**
	 *
	 */
	public FileToByte() {
	}

	public void doMain() throws Exception{


		File file = new File("e:/tmp/xml/test.xml");
		BufferedReader br = new BufferedReader(new FileReader(file));

		String strData = br.readLine();
		String fileData = null;
		while(strData != null){
			// System.out.println(strData);
			if(fileData != null){

				fileData += strData;
			} else {

				fileData = strData;
			}

			strData = br.readLine();
		}


		DelimiterChecker delimiter = new DelimiterChecker();

		byte[]  byteData = fileData.getBytes("UTF8");

		int index = 1;
		for(byte byteItem : byteData){
			if(index % 256 == 0){
				System.out.println();
			}else{

				System.out.printf("%5s",Byte.toString(byteItem));

			}
			index++;

		}
		System.out.println();





		List<String> result = delimiter.delimiterCheck(new String(byteData, "UTF8"));

		for(String item : result){


			System.out.println(item);
		}

		br.close();

	}

}
