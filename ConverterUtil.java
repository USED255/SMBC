/**
 *
 */
package jp.util;

import java.util.ArrayList;

/**
 * @author PC User
 *
 */
public class ConverterUtil {

	/**
	 *
	 * Š¿”š‚ğ”¼Šp”š‚É•ÏŠ·‚µ‚Ü‚·B
	 *
	 * @param chinaNum
	 *            •ÏŠ·‚µ‚½‚¢Š¿”š
	 * @return long •ÏŠ·‚µ‚½’l
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

		list.add("•S");
		list.add("\");

		for (int i = 0; i < 2; i++) {
			if (calValue.indexOf(list.get(i)) > -1) {

				spritChinaNum[i] = calValue.substring(0,
						calValue.indexOf(list.get(i)) + 1);
				if (spritChinaNum[i].equals(list.get(i))) {
					spritChinaNum[i] = "ˆê" + spritChinaNum[i];
				}
				calValue = calValue.substring(calValue.indexOf(list.get(i)) + 1, calValue.length());
			} else {
				spritChinaNum[i] = "—ë";
			}
		}

		spritChinaNum[2] = calValue;

		long hundredsPlaceArray[] = { 0, 0 };
		long tensPlaceArray[] = { 0, 0 };

		for (String num : spritChinaNum) {

			if (num.matches(".*" + list.get(0) + ".*")) {
				for (int i = 0; i < num.length(); i++) {
					// hundredsPlaceArray.add(getNum(num.substring(i,i+1)));
					hundredsPlaceArray[i] = getNum(num.substring(i, i + 1));

				}
			}

			if (num.matches(".*" + list.get(1) + ".*")) {
				for (int i = 0; i < num.length(); i++) {
					// tensPlaceArray.add(getNum(num.substring(i,i+1)));
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
		// •S‚ÌˆÊA\‚ÌˆÊ‚Æˆê‚ÌˆÊ‚ğ‰ÁZ
		retNum = hundredsPlace + tensPlace + onesPlace;

		//”¼Šp”š‚ğ‘SŠp”š‚É‚·‚éB
		String ret =  convertMultiByteNum(retNum);


		return ret;

	}

	/**
	 * ”¼Šp”š‚ğ‘SŠp”š‚É‚·‚éB
	 * @param inNum
	 * @return
	 */
	public static String convertMultiByteNum(long inNum) {

		String strInNum = Long.toString(inNum);
		 StringBuffer sb = new StringBuffer(strInNum);
	    for (int i = 0; i < sb.length(); i++) {
	        char c = sb.charAt(i);
	        if (c >= '0' && c <= '9') {
	            sb.setCharAt(i, (char) (c - '0' + '‚O'));
	          }
	        }

		return sb.toString();
	}

	/**
	 * Š¿”š‚ğ”¼Šp”š‚É‚·‚éÚ×
	 * @param strNum
	 * @return
	 */
	private static long getNum(String strNum) {

		long num = 0L;
		// Š¿”š‚ğ”’l‰»‚·‚éB
		for (int i = 0; i < strNum.length(); i++) {
			char cBuf = strNum.charAt(i);
			switch (cBuf) {
			case '—ë':
			case 'Z':
				num = 0;
				break;
			case 'ˆë':
			case 'ˆê':
				num = 1;
				break;
			case '“ó':
			case '“ñ':
				num = 2;
				break;
			case 'Q':
			case 'O':
				num = 3;
				break;
			case 'l':
				num = 4;
				break;
			case 'ŒÜ':
				num = 5;
				break;
			case '˜Z':
				num = 6;
				break;
			case 'µ':
				num = 7;
				break;
			case '”ª':
				num = 8;
				break;
			case '‹ã':
				num = 9;
				break;
			case '\':
				num = 10;
				break;

			case '•S':
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
	 * •¶š‚Ì‘SŠp‚Æ”¼Šp‚ğ”»’è‚µ‚Ä”¼Šp‚Å‚ ‚ê‚Î³‚ğ•Ô‚·
	 *
	 * @param targetString
	 * @return@boolean
	 */
	public static boolean isSingleByte(String targetString) {
		// æ“¾‚µ‚½•¶šƒTƒCƒY‚ÌŒvZ
		int strSize = targetString.length();
		int byteSize = targetString.getBytes().length;

		// •¶š”‚ÆƒoƒCƒg”‚ª“¯‚¶¨”¼Šp
		if (strSize == byteSize) {
			return true;
		}
		return false;
	}

}




