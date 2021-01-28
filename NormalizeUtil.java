/**
 *
 */
package jp.co.profitcube.eacris.report.attribute.judgement.normalization;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author tozawa_h01
 *
 */
public class NormalizeUtil {


	/**
	 * 法人名略称の略称の16進数文字列配列
	 */
	private final static String[] corporateNameAbbreviationArray = new String[] {
		"3231","3232","3233","3234","3235","3236","3237","3238","3239","323a","323b","323c","323d","323e","323f","3240",
		"3241","3242","3243",

		"3291","3292","3293","3294","3295","3296","3297","3298","3299","329A","329B","329C","329D","329E","329F","32A0",
		"32A1","32A2","32A3","32A4","32A5","32A6","32A7","32A8","32A9","32AA","32AB","32AC","32AD","32AE","32AF",
		"337D"
	};


	/**
	 *
	 */
	public NormalizeUtil() {
	}

    public void normalize(InputData inputData) {
    }
    
    
    
	/**
	 * 文字を文字リストに配置します。この時文字が規定のUTF-8コードリストに存在すれば、配置されない。
	 * @param charList   配置される
	 * @param character
	 * @param hexCode
	 */
	public static void placeCharacterList(List<Character> charList, char character, String hex) {

		int code = (int)character;
		String hexCode = Integer.toHexString(code);
		
		if(hex.equals(hexCode.toUpperCase())) {
			charList.add(character);
		}

	}

    
    
    
    
    

	/**
	 * 文字を文字リストに再配置します。この時文字が規定のUTF-8コードリストに存在すれば、配置されない。
	 * @param charList   再配置される
	 * @param character
	 * @param hexCode
	 */
	public static void placeCharacterList(List<Character> charList, char character) {

		int code = (int)character;
		String hexCode = Integer.toHexString(code);


		boolean flag = false;
		for(String hex : corporateNameAbbreviationArray){
			if(hex.equals(hexCode.toUpperCase())) {
				flag = true;
			}
		}
		if(!flag){
			charList.add(character);
		}
	}

    /**
     * char Listを文字列に変換します。
     * @param  charList 対象のリスト
     * @return String   変換後の文字列
     * @throws Exception
     */
    public static String stringToCharacterList(ArrayList<Character> charList) throws Exception{

		char[] newCharaArray = new char[charList.size()];

		for(int index = 0 ; index < charList.size() ; index++){
			newCharaArray[index] = charList.get(index);
		}
    	return String.valueOf(newCharaArray);
    }


    /**
     * char Listを文字列に変換します。
     * @param  charList 対象のリスト
     * @return String   変換後の文字列
     * @throws Exception
     */
    public static String stringToCharacterList(List<Character> charList) throws Exception{

		char[] newCharaArray = new char[charList.size()];

		for(int index = 0 ; index < charList.size() ; index++){
			newCharaArray[index] = charList.get(index);
		}
    	return String.valueOf(newCharaArray);
    }

    // 正規化部品
    /**
     * 半角・全角トリム
     * @param targetStr
     * @return
     */
    public static String trimSpace(String targetStr) {
        char[] value = targetStr.toCharArray();
        int len = value.length;
        int st = 0;
        char[] val = value;

        while ((st < len) && (val[st] <= ' ' || val[st] == '　')) {
            st++;
        }
        while ((st < len) && (val[len - 1] <= ' ' || val[len - 1] == '　')) {
            len--;
        }

        return ((st>0) || (len<value.length)) ? targetStr.substring(st,len):targetStr;
    }


	/**
	 * 数値に変換可能な文字を変換する。
	 * 数値に変換できない文字列は0に変換される
	 *
	 * @param  str    数値変換したい文字列
	 * @return long   変換された数値
	 */
	public static long getNumerical(String str) {
		long ret = -1;
		try {
			ret = Long.parseLong(str);
		} catch (NumberFormatException e) {
			ret = 0;
		}
		return ret;
	}


	/**
	 * 文字の全角と半角を判定して半角であれば正を返す
	 *
	 * @param     targetString             調べたい文字列
	 * @return    boolean                  結果
	 */
	public static boolean isSingleByte(String targetString) {
		// 取得した文字サイズの計算
		int strSize = targetString.length();
		int byteSize = targetString.getBytes().length;

		// 文字数とバイト数が同じ→半角
		if (strSize == byteSize) {
			return true;
		}
		return false;
	}

	/**
	 * 文字列のバイト数を取得
	 * @param str        調べたい文字列
	 * @param encode     文字タイプ
	 * @return           Byte数
	 */
	public static int getTextByteLenght(String str, String encode) {
		int nLen;
		try {
			if (encode != null) {
				nLen = str.getBytes(encode).length;

			} else {
				nLen = str.getBytes().length;
			}

		} catch (UnsupportedEncodingException e) {
			nLen = str.getBytes().length;
		}
		return nLen;

	}

	/**
	 *
	 * 漢数字を全角数字に変換します。
	 *
	 * @param chinaNum      変換したい漢数字
	 * @return long         変換した値
	 */
	public static String chinaNumToArabicNum(String chinaNum) {

		long retNum = 0;

		int maxSize = chinaNum.length();

		String calValue = new String(chinaNum);
		int count = 0;
		for (int i = 0; i < maxSize; i++) {

			if (getNum(chinaNum.substring(i, i + 1)) != -1) {

				calValue = chinaNum.substring(count, maxSize);
				break;
			} else {
				count++;
			}
		}

		String spritChinaNum[] = new String[3];

		ArrayList<String> list = new ArrayList<>();

		list.add("百");
		list.add("十");

		for (int i = 0; i < 2; i++) {
			if (calValue.indexOf(list.get(i)) > -1) {

				spritChinaNum[i] = calValue.substring(0,
						calValue.indexOf(list.get(i)) + 1);
				if (spritChinaNum[i].equals(list.get(i))) {
					spritChinaNum[i] = "一" + spritChinaNum[i];
				}
				calValue = calValue.substring(calValue.indexOf(list.get(i)) + 1, calValue.length());
			} else {
				spritChinaNum[i] = "零";
			}
		}

		spritChinaNum[2] = calValue;

		long hundredsPlaceArray[] = { 0, 0 };
		long tensPlaceArray[] = { 0, 0 };

		for (String num : spritChinaNum) {

			if (num.matches(".*" + list.get(0) + ".*")) {
				for (int i = 0; i < num.length(); i++) {
					// 100の位
					hundredsPlaceArray[i] = getNum(num.substring(i, i + 1));
				}
			}

			if (num.matches(".*" + list.get(1) + ".*")) {
				for (int i = 0; i < num.length(); i++) {
					// 10の位
					tensPlaceArray[i] = getNum(num.substring(i, i + 1));
				}
			}
		}

		long hundredsPlace = 0;
		long tensPlace = 0;
		long onesPlace = 0;

		hundredsPlace = Math.abs(hundredsPlaceArray[0] * hundredsPlaceArray[1]);
		tensPlace = Math.abs(tensPlaceArray[0] * tensPlaceArray[1]);

		for (int i = 0; i < calValue.length(); i++) {
			if (i == 0) {
				if (calValue.length() >= 3) {
					onesPlace += getNum(calValue.substring(i, i + 1)) * 100;
				} else if (calValue.length() == 2) {
					onesPlace += getNum(calValue.substring(i, i + 1)) * 10;
				} else {
					onesPlace += getNum(calValue);
				}
			} else if (i == 1) {
				if (calValue.length() >= 3) {
					onesPlace += getNum(calValue.substring(i, i + 1)) * 10;
				} else {
					onesPlace += getNum(calValue);
				}
			} else if (i == 2) {
				onesPlace += getNum(calValue);
			}
		}
		retNum = hundredsPlace + tensPlace + onesPlace;

		//半角数字を全角数字にする。
		String ret =  convertMultiByteNum(retNum);
		return ret;
	}


	/**
	 * 漢数字を数値に変換する。
	 * @param     targetNum     対象とする漢数字
	 * @return    long          変換した数値
	 */
	public static long getNum(String targetNum) {

		long num = 0L;
		// 漢数字を数値化する。
		for (int i = 0; i < targetNum.length(); i++) {
			char cBuf = targetNum.charAt(i);
			switch (cBuf) {
			case '零':
				num = 0;
				break;

			case '壱':
			case '一':
				num = 1;
				break;

			case '弐':
			case '二':
				num = 2;
				break;

			case '参':
			case '三':
				num = 3;
				break;

			case '四':
				num = 4;
				break;

			case '五':
				num = 5;
				break;

			case '六':
				num = 6;
				break;

			case '七':
				num = 7;
				break;

			case '八':
				num = 8;
				break;

			case '九':
				num = 9;
				break;

			case '十':
				num = 10;
				break;

			case '百':
				num = 100;
				break;

			default:
				num = -1;
				break;
			}

		}
		return num;
	}

	/**
	 * 半角数字を全角数字にする。
	 * @param     longTergetNum      対象とする数字
	 * @return    String             変換した全角数字
	 */
	public static String convertMultiByteNum(long longTergetNum) {

		String strInNum = Long.toString(longTergetNum);
		 StringBuffer strBuf = new StringBuffer(strInNum);
	    for (int i = 0; i < strBuf.length(); i++) {
	        char cBuf = strBuf.charAt(i);
	        if ( cBuf >= '0' && cBuf <= '9' ) {
	            strBuf.setCharAt(i, (char) (cBuf - '0' + '０'));
	          }
	        }
		return strBuf.toString();
	}

}
