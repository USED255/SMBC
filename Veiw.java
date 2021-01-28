/**
 * 
 */
package jp.console.main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import jp.util.src.main.AttributeCommonTMPItem;
import jp.util.src.main.StringUtil;

/**
 * @author tozawa_h01
 *
 */
public class Veiw {


	public static  AttributeCommonTMPItem notNameClass = null;

	public enum Section {FIRST_PART_, MIDDLE_PART_, FINAL_PART_, MAX_SIZE_};


	private static final String[] addressSpecialCharArray = {
		"ﾁﾖｳﾒ","ﾁﾖｳ","ﾊﾞﾝﾁ","ﾊﾞﾝ","ｺﾞｳｼﾂ","ｺﾞｳ","ﾁﾜﾘ","ｾﾝ","ﾉ","ｵｵｱｻﾞ","ｱｻﾞ","ｺｳ","ｵﾂ","ｰ"

	};

	private static final char[] cChinaNumberArray ={
		'一','二','三','四','五','六','七','八','九','十','百'
	};


//	private static final String[] hexCodeArray = {
//	"3231","3232","3233","3234"
//	};

	/**
	 * 法人名略称の略称の16進数文字列配列
	 */
	private final static String[] hexCodeArray = new String[] {
		"3231","3232","3233","3234","3235","3236","3237","3238","3239","323a","323b","323c","323d","323e","323f","3240",
		"3241","3242","3243",
		"3291","3292","3293","3294","3295","3296","3297","3298","3299","329A","329B","329C","329D","329E","329F","32A0",
		"32A1","32A2","32A3","32A4","32A5","32A6","32A7","32A8","32A9","32AA","32AB","32AC","32AD","32AE","32AF"
	};
	
	private final static String[] addressStateNameArrayMultiByte = {
			"北海道","青森県","岩手県","宮城県","秋田県","山形県","福島県",
			"茨城県","栃木県","群馬県","埼玉県","千葉県","東京都","神奈川県",
			"新潟県","富山県","石川県","福井県","山梨県","長野県","岐阜県",
			"静岡県","愛知県","三重県","滋賀県","京都府","大阪府","兵庫県",
			"奈良県","和歌山県","鳥取県","島根県","岡山県","広島県","山口県",
			"徳島県","香川県","愛媛県","高知県","福岡県","佐賀県","長崎県",
			"熊本県","大分県","宮崎県","鹿児島県","沖縄県"

	};
	
	
	

	private static int maxSize = -1;



	/**
	 * 
	 */
	public Veiw() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		if(args.length < 1 ){
			doMain021();
			System.exit(0);
		}

		int pattarn = Integer.valueOf(args[0]);
		switch(pattarn){
		case 1:
			doMain01();
			break;
		case 2:
			doMain02();
			break;
		case 3:
			doMain03();
			break;
		case 4:
			doMain04();
			break;
		case 5:
			doMain05();
			break;
		case 6:
			doMain06();
			break;
		case 7:
			doMain07();
		case 8:
			doMain08();
			break;
		case 9:
			doMain09();
			break;

		case 10:
			doMain010();
			break;

		case 11:
			doMain011();
			break;		

		case 12:
			doMain012();
			break;	

		case 13:
			doMain013();
			break;

		case 14:
			doMain014();
			break;

		case 15:
			doMain015();
			break;


		case 16:
			doMain016();
			break;

		case 17:
			doMain017();
			break;

		case 18:
			doMain018();
			break;


		case 19:
			doMain019();
			break;


		case 20:
			doMain020();
			break;

		default:
			doMain000();
		break;
		}
	}



	
	
	/**
	 * リストシャッフルとキュー 
	 */
	private static void doMain021() {
		Queue<String> queue = new LinkedList<String>(); 
		
		
		
		List<String> addressStateNameList = new ArrayList<String>(Arrays.asList(addressStateNameArrayMultiByte));
		
		Collections.shuffle(addressStateNameList);
		
		for(String value : addressStateNameList ){
			queue.add(value);
			
		}
		
		while(queue.size() > 0) {
			System.out.printf( "%3d : ", queue.size());
			System.out.printf( "%s\n", queue.remove());
			//System.out.println();
		}
		
		
		
		
	}

	private static void doMain020() {
		
		int insertCount = 1999999;
		
		
		System.out.println(String.format("%06d", insertCount));
		
		
		String str ="060000";
		
		
		System.out.println(Integer.valueOf(str));
		
	}

	private static void doMain019() {
		
		
		
    	// 1.文字列長の長い方を基準にする。
		
		
		String pItemInfoAddressFrontPart_ = "津市";
		String oItemInfoAddressFrontPart_ = "君津市";
		
		
		String basisString = new String();
		String shortString = new String();
		
		
		int pInfoCodeSize = pItemInfoAddressFrontPart_.length();
		int oInfoCodeSize = oItemInfoAddressFrontPart_.length();
	
        if (pInfoCodeSize > oInfoCodeSize){
        	basisString = pItemInfoAddressFrontPart_;
        	shortString = oItemInfoAddressFrontPart_;
        	// 基準文字列がもう一方に含まれていれば、都道府県省略
        	
        	// 基準文字列に短い方の文字列が含まれていれば、都道府県省略
        	System.out.println("本人情報が基準文字列:" + basisString.matches(".*" + shortString + ".*"));
	
        }
        if (pInfoCodeSize < oInfoCodeSize){
        	basisString = oItemInfoAddressFrontPart_;
        	shortString = pItemInfoAddressFrontPart_;
        	
        	// 基準文字列に短い方の文字列が含まれていれば、都道府県省略
        	System.out.println("他人情報が基準文字列:" + basisString.matches(".*" + shortString + ".*"));
	
        	
        }
	
		
	}

	private static void doMain018() {
		
		String pItemInfoAddressLearsPart = "ﾊﾔｼ1ﾉ3ﾉ7";
		String oItemInfoAddressLearsPart = "1-3-7";
		
        String pInfoAddressZipCode = pItemInfoAddressLearsPart.replaceAll("[^0-9]", "");    // 本人情報
        String oInfoAddressZipCode = oItemInfoAddressLearsPart.replaceAll("[^0-9]", "");    // 他人情報

		
		

    	
    	System.out.println("本人：　" + pInfoAddressZipCode);
    	System.out.println("他人：　" + oInfoAddressZipCode);
		
		
	}

	private static void doMain017() {


		String[] strArrays = {"099-99-9999  ","09031457843","099999999","   090-3145-7843"};



		for(String str : strArrays) {

			String trimTel = trimSpace(str);
			System.out.print(trimSpace(str));

			if( !trimTel.equals("099-99-9999") && !trimTel.equals("099999999")) {

				System.out.println(" :有効項目");

			} else {

				System.out.println(" :無効項目");
			}


		}
	}

	/**
	 * 文字列を左側から検索して行き、最初にヒットした数値から、前後で文字列分割を行う
	 * <p>
	 * 
	 * @author tozawa_h
	 * @version 1.0
	 * @param strValue
	 * @param fastNumPosition
	 * @return
	 */
	private static String[] getSplitStrings(String strValue) {
		int fastNumPosition = -1;
		for (int i = 0; i < strValue.length(); i++) {
			// 文字列か数値か判定する
			if (isNumeric(strValue.substring(i, i + 1))) {
				fastNumPosition = i;
				break;
			}
		}

		String itemInfoArray[] = new String[2];

		// 取得した文字列を返却
		if (fastNumPosition > 0) {
			itemInfoArray[0] = strValue.substring(0, fastNumPosition);
			itemInfoArray[1] = strValue.substring(fastNumPosition, strValue
					.length());
		} else {
			itemInfoArray[0] = strValue;
			itemInfoArray[1] = "";
		}

		return itemInfoArray;
	}


	/**
	 * 文字列が数値かどうか判断する
	 * <p>
	 * 
	 * @author tozawa_h
	 * @version 1.0
	 * @param num
	 *            対象の文字列
	 * @return boolean
	 */
	private static boolean isNumeric(String num) {
		try {
			Integer.parseInt(num);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}

	}


	/**
	 * @param pItemInfo
	 * @param oItemInfo
	 * @return
	 */
	private static boolean detailedJudgment(String pItemInfo, String oItemInfo) {
		boolean retFlag = false;

		// 文字数を取得
		int pInfoItemLengs = pItemInfo.length();
		int oInfoItemLengs = oItemInfo.length();

		if(pInfoItemLengs != oInfoItemLengs){

			if(pInfoItemLengs > oInfoItemLengs ){
				maxSize = oInfoItemLengs - 1;
			}else if(pInfoItemLengs < oInfoItemLengs ) {
				maxSize = pInfoItemLengs - 1;
			}
			// 文字配列の準備
			char[] pInfoCharArray = pItemInfo.toCharArray();
			char[] oInfoCharArray = oItemInfo.toCharArray();

			// 文字単位で一致確認
			int pInfoItemSize = pInfoItemLengs - 1;
			int oInfoItemSize = oInfoItemLengs - 1;

			System.out.println("pInfoCharArray ：" + pItemInfo );
			System.out.println("oInfoCharArray ：" + oItemInfo );


			for(int index = maxSize ; index >= 0 ; index--){
				if(pInfoCharArray[pInfoItemSize] == oInfoCharArray[oInfoItemSize]){

					System.out.println("pInfoCharArray[" + pInfoItemSize + "] : " + pInfoCharArray[pInfoItemSize]);
					System.out.println("oInfoCharArray[" + oInfoItemSize + "] : " + oInfoCharArray[oInfoItemSize]);

					retFlag = true;
					System.out.println(retFlag);


				}else{
					System.out.println("pInfoCharArray[" + pInfoItemSize + "] : " + pInfoCharArray[pInfoItemSize]);
					System.out.println("oInfoCharArray[" + oInfoItemSize + "] : " + oInfoCharArray[oInfoItemSize]);

					retFlag = false;
					System.out.println(retFlag);
					System.out.println("一致しないのでループを抜けます。");
					break;
				}
				pInfoItemSize--;
				oInfoItemSize--;
			}
		}
		return retFlag;
	}






	/**
	 * 半角・全角トリム
	 * @param targetStr
	 * @return
	 */
	public static String trimSpace(String targetStr) {
		char[] value = targetStr.toCharArray();
		int len = value.length;
		int index = 0;
		char[] val = value;

		while ((index < len) && (val[index] <= ' ' || val[index] == '　')) {
			index++;
		}
		while ((index < len) && (val[len - 1] <= ' ' || val[len - 1] == '　')) {
			len--;
		}
		return ((index>0) || (len<value.length)) ? targetStr.substring(index,len):targetStr;
	}

	private static void doMain016() {



		ArrayList<String> list = new ArrayList<String>();

		list.add("名前１");
		list.add("名前２");
		list.add("名前３");
		list.add("名前４");
		list.add("名前５");

		System.out.println(getArrayListElements(list));

		String target = "999 ";

		if(target.matches("9+")){
			// 上記以外は true 「有効」
			System.out.println(target + "は特別文字列");
		} else {
			System.out.println(target + "は一般文字列");
		}		

	}

	private static String getArrayListElements(ArrayList<String> itemInfoArray) {

		StringBuilder buf = new StringBuilder("[");

		for(int index = 0 ; index < itemInfoArray.size() ; index++ ){

			buf.append(itemInfoArray.get(index));

			if(index < itemInfoArray.size()-1){
				buf.append(",");
			}
		}

		buf.append("]");


		return buf.toString();
	}



	private static void doMain015() {

		String pItemInfoAddressLearsPart = "０１３２１６５０２０１";
		String oItemInfoAddressLearsPart = "４５３２１６５０";


		String strShort = new String();
		String strLong = new String();

		if(pItemInfoAddressLearsPart.length() > oItemInfoAddressLearsPart.length()){


			strShort = oItemInfoAddressLearsPart;
			strLong = pItemInfoAddressLearsPart.substring(0,oItemInfoAddressLearsPart.length());

			System.out.println("[A:strShort]  " + strShort);
			System.out.println("[A:strLong ]  " + strLong);



		}else{
			strShort = pItemInfoAddressLearsPart;
			strLong = oItemInfoAddressLearsPart.substring(0,pItemInfoAddressLearsPart.length());

			System.out.println("[B:strShort]  " + strShort);
			System.out.println("[B:strLong ]  " + strLong);


		}
	}

	private static void doMain014() {


		String pItemInfoAddressLearsPart = "４５３-２１-６５０-２０１";
		String oItemInfoAddressLearsPart = "４５３-２１-６５０-２０１";


		String pTemp1 =pItemInfoAddressLearsPart.replaceAll("[^０-９]", "");
		String oTemp1 =oItemInfoAddressLearsPart.replaceAll("[^０-９]", "");

		System.out.println(pTemp1);
		System.out.println(oTemp1);


		if(pTemp1.equals(oTemp1)){
			System.out.println("一致した");
		} else {
			System.out.println("一致しなかった");
		}














		//String pInfoAddressZipCode = Integer.toString(Integer.parseInt((pItemInfoAddressLearsPart.replaceAll("[^０-９]", "")))); // 本人情報
		//String oInfoAddressZipCode = Integer.toString(Integer.parseInt((oItemInfoAddressLearsPart.replaceAll("[^０-９]", "")))); // 他人情報



		//System.out.println(pInfoAddressZipCode);

		//System.out.println(oInfoAddressZipCode);


		String str = "４５３２１６５０２０１";



		String temp = changeSingleByteNumericToMultiByteNumeric(str);



		System.out.println(temp);


		//int intStr = Integer.valueOf(temp);


		//System.out.println(intStr);


	}
	private static String changeSingleByteNumericToMultiByteNumeric(String s) {
		StringBuffer sb = new StringBuffer(s);
		for (int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			if (c >= '０' && c <= '９') {
				sb.setCharAt(i, (char)(c - '０' + '0'));
			}
		}
		return sb.toString();
	}
	private static void doMain013() {





		boolean[] spaceflg = new boolean[]{false,false}; 
		// 分ち書きが存在したら、名前のみの完全一致検索をする。
		boolean retFlag = false;

		if(spaceflg[0] && spaceflg[1]){
			System.out.println("本人にも他人にも分かち書きがない");
			retFlag =  false;

		} else if(spaceflg[0]) {

			System.out.println("本人に分かち書きがない");
			retFlag =  true;

		} else if(spaceflg[1]) {
			System.out.println("他人に分かち書きがない");

			retFlag =  true;

		}

		System.out.println("[flag    ]: " + retFlag);




	}

	private static void doMain012()  {

		ArrayList<String> list  = new ArrayList<String>();
		FileInputStream fr = null;
		InputStreamReader in = null;
		BufferedReader br = null;

		try {




//			FileReader fr = new FileReader("D:/work/unicode_test.txt");
//			BufferedReader br = new BufferedReader(fr);

			fr = new FileInputStream("D:/work/unicode_test.txt"); 
			in = new InputStreamReader(fr,"UTF-16"); 
			br = new BufferedReader(in);

			String line;
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
			br.close();
			fr.close();
			in.close();

			int index = 0;
			for(String target : list){
				System.out.println("[" + index + "] : " + target);

				char[] charArray = target.toCharArray();

				//System.out.println(charArray.length);


				List<Character> charList = new ArrayList<Character>();

				for(char character : charArray){
					int code = (int)character;
					String hexCode = Integer.toHexString(code);
					//System.out.println("[Decimal]: " + code);
					System.out.println("[Hexa   ]: " + hexCode);

					boolean flag = false;
					for(String hex : hexCodeArray){
						if(hex.equals(hexCode)){
							flag =true;
							break;
						}
					}

					if(!flag){
						charList.add(character);
					}
				}

				char[] newCharaArray = new char[charList.size()];

				for(int indexI = 0 ; indexI < charList.size() ; indexI++){
					newCharaArray[indexI] = charList.get(indexI);
				}

				//String output =  String.valueOf(newCharaArray);
				System.out.println("[out    ]: " + String.valueOf(newCharaArray));


				index++;
			}







		}catch(FileNotFoundException e){
			System.out.println(e);
		}catch(IOException e){
			System.out.println(e);
		}



	}

	private static void doMain011() {





		String target = "日産㈲自動車㈱"; // 文字コードは0x878f

		try {

//			Stringからコードへ
//			char[] buf = new String(target.getBytes("MS932"), "8859_1").toCharArray();
//			String hexCode = Integer.toHexString(buf[0]) + Integer.toHexString(buf[1]);
//			System.out.println(hexCode);
//			→878f

//			コードからStringへ
//			System.out.println(hexCode.substring(0,2));
//			System.out.println(hexCode.substring(2,4));
//			buf = new char[]{(char)Integer.parseInt(hexCode.substring(0,2), 16), (char)Integer.parseInt(hexCode.substring(2,4), 16)};
//			System.out.println(new String(new String(buf).getBytes("8859_1"), "MS932"));
//			→㍼


			System.out.println("[in     ]: " + target);

			char[] charArray = target.toCharArray();

			//System.out.println(charArray.length);


			List<Character> charList = new ArrayList<Character>();

			for(char character : charArray){
				int code = (int)character;
				String hexCode = Integer.toHexString(code);
				//System.out.println("[Decimal]: " + code);
				System.out.println("[Hexa   ]: " + hexCode);

				boolean flag = false;
				for(String hex : hexCodeArray){
					if(hex.equals(hexCode)){
						flag =true;
						break;
					}
				}

				if(!flag){
					charList.add(character);
				}
			}


			char[] newCharaArray = new char[charList.size()];

			for(int index = 0 ; index < charList.size() ; index++){
				newCharaArray[index] = charList.get(index);
			}

			String output =  String.valueOf(newCharaArray);
			System.out.println("[out    ]: " + output);

		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void doMain010() throws Exception {

		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		start(stackTraceElements);

		for(int i = 0 ; i < 10 ; i++){

			String str = null;
			//String str = "count";


			try {

				int idex = str.length();

				System.out.println(idex * (i + 1));



			} catch (Exception e) {
				//e.printStackTrace();
				System.err.print("Exception01 ");
				System.err.println(str);
				throw e;
			}

			//str = null;
			str ="count";
			try {

				int idex = str.length();

				System.out.println(idex * (i + 1));



			} catch (Exception e) {
				//e.printStackTrace();
				System.err.print("Exception02 ");
				System.err.println(str);
				throw e;
			}

		}
		end(stackTraceElements);
	}

	private static void doMain09() {
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		start(stackTraceElements);


		ArrayList<String> pItemInfoList = new ArrayList<String>();
		ArrayList<String> oItemInfoList = new ArrayList<String>();

		//pItemInfoList.add(null);
		//pItemInfoList.add("      ");
		//pItemInfoList.add("Name00");
		//pItemInfoList.add("Name01");
		//pItemInfoList.add("Name02");

		pItemInfoList.add("札幌　みく");
		pItemInfoList.add("大阪　来未");
		pItemInfoList.add("東　はるみ");



		//oItemInfoList.add(null);
		//oItemInfoList.add("Name01");
		//oItemInfoList.add("Name02");
		//oItemInfoList.add("Name03");

		//oItemInfoList.add(null);
		//oItemInfoList.add("　　　　　");
		oItemInfoList.add("東京　一朗");
		oItemInfoList.add("札幌　みく");
		oItemInfoList.add("札幌　みく");


		boolean flag = false;


		int count = 0;
		for(int i = 0 ; i < pItemInfoList.size() ; i++){

			if(pItemInfoList.get(i) != null && 	StringUtil.trimSpace(pItemInfoList.get(i)).length() > 0){

				for(int j = 0 ; j < oItemInfoList.size() ; j++){

					if(oItemInfoList.get(j) != null && StringUtil.trimSpace(oItemInfoList.get(j)).length() > 0){
						// 配列に存在するだけの値を全て判定
						String pItemInfo = pItemInfoList.get(i);
						String oItemInfo = oItemInfoList.get(j);

						//System.out.print("[" + count + "]");

						if(pItemInfo.equals(oItemInfo)){
							System.out.println(pItemInfo + "[" + i + "]と" + oItemInfo + "[" + j + "]は同じだよ");
							flag = true;
							break;
						}else{
							System.out.println(pItemInfo + "[" + i + "]と" + oItemInfo + "[" + j + "]は違うよ");
						}
						count++;

//						} else {
//						System.out.println(oItemInfoList.get(j) + "はコンテニューするよ");
//						continue;
					}
				}

//				} else {
//				System.out.println(pItemInfoList.get(i) + "はコンテニューするよ");
//				continue;
			}
			if(flag){
				System.out.println("ループを抜けるよ");
				break;
			}
		}






		end(stackTraceElements);
	}







	private static void doMain08() {

		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		start(stackTraceElements);


		String oldStr = "今度の九月十九日に九十九里浜へ行こう";
		//String oldStr = "神奈川県横須賀市　林一丁目三番地七号";

		char[] cArrayBuf = convArrays(oldStr);
		System.out.println("convArrays");
		String newStr = String.valueOf(cArrayBuf);
		System.out.println("置換前　" + oldStr );
		System.out.println("置換後　" + newStr );
		System.out.println("covStrngArrays");

		int count;

		String[] splitStr = covStrngArrays(newStr);
		System.out.println("removeNullList");

		String[] newChinaNumArray = removeNullList(splitStr);


		count = 0;
		for (String str : newChinaNumArray) {
			System.out.println("[" + count + "]" + str );
			count++;
		}
		System.out.println("sizeSort");
		sizeSort(newChinaNumArray);


		count = 0;
		for (String str : newChinaNumArray) {
			System.out.println("[" + count + "]" + str );
			count++;
		}

		end(stackTraceElements);

	}

	//--------------------------------------------------------------------------------------------------//

	/**
	 * String 配列を要素の文字列長が長い順に昇順ソートします。
	 * @param newChinaNumArray
	 */
	private static void sizeSort(String[] newChinaNumArray) {
		for(int i = 0 ; i < newChinaNumArray.length -1 ; i++){
			for(int j = i + 1 ; j < newChinaNumArray.length ; j++){
				if(newChinaNumArray[i].length() < newChinaNumArray[j].length()){
					String tempStr = newChinaNumArray[i];
					newChinaNumArray[i] = newChinaNumArray[j];
					newChinaNumArray[j] = tempStr;
				}
			}
		}
	}

	/**
	 * String 配列の要素が無い物だけリストから除外します。
	 * @param splitStr　
	 * @return
	 */
	private static String[] removeNullList(String[] splitStr) {
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(splitStr)); 

		for(int index = list.size() - 1; index > -1 ; index--){
			if(list.get(index).length() == 0){
				list.remove(index);
			}
		}

		String[] newChinaNumArray = list.toArray(new String[list.size()]);
		return newChinaNumArray;
	}

	/**
	 * カンマ区切りの文字列を文字配列に設定します。
	 * @param newStr
	 * @return
	 */
	private static String[] covStrngArrays(String newStr) {
		String[] splitStr = newStr.split(",", 0);

		int count = 0;
		for(String str : splitStr){
			System.out.println("[" + count + "]" + str );
			count++;
		}
		return splitStr;
	}

	/**
	 * 指定したた漢数字以外の全角文字を','に置き換えます。
	 * @param oldStr
	 * @return
	 */
	private static char[] convArrays(String oldStr) {
		char[] cArrayBuf = oldStr.toCharArray();

		for(int index = 0 ; index < cArrayBuf.length ; index++ ){

			boolean flag = false;
			for(char cChinaNum : cChinaNumberArray){

				if(cArrayBuf[index] == cChinaNum){

					flag = true;
					break;
				}
			}
			if(!flag){
				cArrayBuf[index] = ',';
			}
		}
		return cArrayBuf;
	}

	//---------------------------------------------------------------------------------------------------//

	private static void doMain07() {

		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		start(stackTraceElements);


		for(String str : addressSpecialCharArray){
			System.out.println(str);
		}

		end(stackTraceElements);


	}

	private static void doMain06() {

		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		start(stackTraceElements);

		String strBuf = "ｯｬｭｮ";



		char[] charArray = strBuf.toCharArray();
		for (char charBuf : charArray) {

			int intValues = (int)charBuf;

			System.out.println(charBuf + "　:　" + intValues);
		}


		//StringBuilder#replac

		String str = "ﾁﾞﾁﾞｲ";


		StringBuilder stBuf = builderReplaceAll(str,"ﾁﾞ","ｼ");

		stBuf = builderReplaceAll(stBuf.toString(),"ﾞ","");

//		stBuf = new StringBuilder(stBuf.toString());
//		count = stBuf.toString().length();

//		for(int index = 0 ; index < count ; index++){

//		int start = stBuf.indexOf("ﾞ");
//		int end = start+1;

//		if(end >= count - 1){
//		end = count - 1;
//		}
//		if(start >= 0){
//		stBuf.replace(start,end,"");
//		}
//		stBuf = new StringBuilder(stBuf.toString());

//		}
		System.out.println(stBuf.toString());

		end(stackTraceElements);


	}

	/**
	 * 
	 * @param str
	 * @param strTarget
	 * @param strConvert
	 * @return
	 */
	private static StringBuilder builderReplaceAll(String str, String strTarget,String  strConvert) {
		StringBuilder stBuf = new StringBuilder(str);

		int count = str.length();

		for(int index = 0 ; index < count ; index++){

			int start = stBuf.indexOf(strTarget);
			int end = start+1;

			if(end >= count - 1){
				end = count - 1;
			}
			if(start >= 0){
				stBuf.replace(start,end,strConvert);
			}
			stBuf = new StringBuilder(stBuf.toString());

		}
		return stBuf;
	}

	private static void doMain05() {

		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		start(stackTraceElements);

//		ArrayList<String> list = new ArrayList<String>();
//		list.add("09031457843");
//		System.out.println(list.toString() + " is " + StringUtil.tellNumCheckCommon(list, "JIC"));

//		list = new ArrayList<String>();
//		list.add("00000000000");
//		System.out.println(list.toString() + " is " + StringUtil.tellNumCheckCommon(list, "JIC"));

//		list = new ArrayList<String>();
//		list.add("09031457843");
//		list.add("00000000000");
//		System.out.println(list.toString() + " is " + StringUtil.tellNumCheckCommon(list, "JIC"));

//		list = new ArrayList<String>();
//		list.add("00000000000");
//		list.add("99999999999");
//		list.add("00000000000");
//		list.add("99999999999");
//		list.add("99999999999");
//		list.add("00000000000");
//		System.out.println(list.toString() + " is " + StringUtil.tellNumCheckCommon(list, "JIC"));

//		list = new ArrayList<String>();
//		list.add("00000000000");
//		list.add("00000000000");
//		list.add("00000000000");
//		System.out.println(list.toString() + " is " + StringUtil.tellNumCheckCommon(list, "JIC"));

//		list = new ArrayList<String>();
//		list.add("000-0000-0000");
//		list.add("000-0000-0000");
//		list.add("(000)00000000");
//		System.out.println(list.toString() + " is " + StringUtil.tellNumCheckCommon(list, "JIC"));

		end(stackTraceElements);
	}

	private static void doMain04() {

		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		start(stackTraceElements);

//		String target = new String();


//		target = "Target";
//		System.out.println(target + " is " + StringUtils.isEmpty(target));


//		target = null;
//		System.out.println(target + " is " + StringUtils.isEmpty(target));

//		target = "";
//		System.out.println(target + " is " + StringUtils.isEmpty(target));


//		ArrayList<String> list = new ArrayList<String>();
//		list.add("SKE48");
//		list.add("AKB48");
//		list.add("HKT48");

//		System.out.println(list.toString() + " is " + StringUtils.is();

//		list = new ArrayList<String>();
//		list.add(null);
//		list.add("");
//		list.add(null);
//		list.add("");

//		System.out.println(list.toString() + " is " + StringUtil.isNULL(list));



//		list = new ArrayList<String>();


//		list.add(null);
//		list.add("SKE48");
//		list.add("AKB48");
//		list.add("HKT48");
//		list.add("");

//		System.out.println(list.toString() + " is " + StringUtil.isNULL(list));
//		System.out.println("allCheckCommon");

//		list = new ArrayList<String>();
//		list.add(null);

//		System.out.println(list.toString() + " is " + StringUtil.allCheckCommon(list));


//		list = new ArrayList<String>();
//		list.add("SKE48");
//		list.add("AKB48");
//		list.add("HKT48");

//		System.out.println(list.toString() + " is " + StringUtil.allCheckCommon(list));

//		list = new ArrayList<String>();
//		list.add(null);
//		list.add("    ");
//		list.add(null);
//		list.add("　　　　");

//		System.out.println(list.toString() + " is " + StringUtil.allCheckCommon(list));

//		list = new ArrayList<String>();
//		list.add(null);
//		list.add("SKE48");
//		list.add("AKB48");
//		list.add("HKT48");
//		list.add("");

//		System.out.println(list.toString() + " is " + StringUtil.allCheckCommon(list));

//		list = new ArrayList<String>();
//		list.add("NAB48");
//		list.add("");
//		list.add("   ");
//		list.add("　　　　");

//		System.out.println(list.toString() + " is " + StringUtil.allCheckCommon(list));


		String str ="99999999999";
		System.out.println( str.matches("9+"));

		end(stackTraceElements);

	}

	private static void doMain03() {

		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		start(stackTraceElements);


		try {

			String strDivs = new String();
			strDivs ="1";
			System.out.println("文字列：　"+ strDivs);
			System.out.println("数値　：　"+ Integer.valueOf(strDivs));

			strDivs ="A";
			System.out.println("文字列：　"+ strDivs);
			System.out.println("数値　：　"+ Integer.valueOf(strDivs));

		} catch(NumberFormatException e){
			String strDivs ="-1";
			System.out.println("例外文字列：　"+ strDivs);
			System.out.println("例外数値　：　"+ Integer.valueOf(strDivs));
		} finally {
			System.out.println("終了");

		}

		end(stackTraceElements);


	}

	private static void doMain02() {

		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		start(stackTraceElements);


		InputData data = new InputData();

		InputDataList dataList = new InputDataList();

		ArrayList<InputData> list = new ArrayList<InputData>();


		data = new InputData();
		data.setNameKanjiEx1("あいうえお");
		dataList.setDataList(data);

		data = new InputData();
		data.setNameKanjiEx1("かきくけこ");
		dataList.setDataList(data);

		data = new InputData();
		data.setNameKanjiEx1("さしすせそ");
		dataList.setDataList(data);




		list = dataList.getDataList();

		System.out.println(list.size());
		for(InputData inData : list ){
			System.out.println(inData.getNameKanjiEx1());
		}

		end(stackTraceElements);


	}

	private static void doMain01() {

		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		start(stackTraceElements);


		InputData data = new InputData();

		ArrayList<String> inItemInfo = new ArrayList<String>();

		inItemInfo.add("処理開始");
		data.setNameKana(inItemInfo);

		for(String str : data.getNameKana()){
			System.out.println(str);
		}


		MainSrc obj = new MainSrc();

		obj.setData(data);


		for(String str : data.getNameKana()){
			System.out.println(str);
		}


		notNameClass = new AttributeCommonTMPItem(){

			int honninShikibetsuKekka_ = 0;

			String standardPatternNo_ = null;

			String matchItemStatus_ = null;

			public void setHonninShikibetsuKekka(int honninShikibetsuKekka){
				this.honninShikibetsuKekka_ = honninShikibetsuKekka;  
			}


			public  void setStandardPatternNo(String standardPatternNo){
				this.standardPatternNo_ = standardPatternNo;

			}

			public void setMatchItemStatus(String matchItemStatus){
				this.matchItemStatus_ = matchItemStatus;
			}

			public int getHonninShikibetsuKekka(){
				return this.honninShikibetsuKekka_;
			}

			public String getStandardPatternNo(){
				return this.standardPatternNo_;
			}

			public String getMatchItemStatus(){
				return this.matchItemStatus_;
			}

		};

		notNameClass.setHonninShikibetsuKekka(100);
		data.setAttrybuteCommon(notNameClass);


		AttributeCommonTMPItem noName = data.getAttrybuteCommon();
		System.out.println(noName.getHonninShikibetsuKekka());

		end(stackTraceElements);

	}


	private static void doMain000() {
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		start(stackTraceElements);

		for(int index = 0 ; index < stackTraceElements.length ; index++){
			System.out.println("-----------------------------");
			System.out.println("[" + index + "]ClassName  : " + stackTraceElements[index].getClassName());
			System.out.println("[" + index + "]FileName   : " + stackTraceElements[index].getFileName());
			System.out.println("[" + index + "]MethodName : " + stackTraceElements[index].getMethodName());
			System.out.println("[" + index + "]LineNumber : " + stackTraceElements[index].getLineNumber());
			System.out.println("-----------------------------");

			String[] classNameArray = stackTraceElements[index].getClassName().split("\\.");
			String[] faileNameArray = stackTraceElements[index].getFileName().split("\\.");

			if(classNameArray[classNameArray.length -1].equals(faileNameArray[0])){
				if(!stackTraceElements[index].getMethodName().equals("main")){

					System.out.println("[" + index + "]" + stackTraceElements[index].getMethodName());
				}
			}
		}

		end(stackTraceElements);

	}

	private static void end(StackTraceElement[] stackTraceElements) {
		System.out.println();
		System.out.println("********** " + stackTraceElements[2].getMethodName() +"   END **********");
		System.out.println();
	}

	private static void start(StackTraceElement[] stackTraceElements) {
		System.out.println();
		System.out.println("********** " + stackTraceElements[2].getMethodName() +" START **********");
		System.out.println();
	}

}
