package jp.console.main;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class StringUtil {

	/**
	 * 標準表示桁数
	 */
	private static int DEFAULT_DIGIT = 3;

	/**
	 * 半角スペース
	 */
	private static String SINGLE_BYTE_SPACE = " ";

	/**
	 * 全角スペース
	 */
	private static String MULTI_BYTE_SPACE = "　";
	
	
	

    /**
     * RPADを行います。 文字列[str]の右に指定した文字列[addStr]を[len]に 満たすまで挿入します。
     * 
     * @param str
     *            対象文字列
     * @param len
     *            補充するまでの桁数（RPADを行った後のサイズを指定します。）
     * @param addStr
     *            挿入する文字列
     * @return 変換後の文字列。
     */
    public static String rpad(String str, int len, String addStr) {
        return fillString(str, "R", len, addStr);
    }

    /**
     * 文字列[str]に対して、補充する文字列[addStr]を [position]の位置に[len]に満たすまで挿入します。
     * 
     * ※[str]がnullや空リテラルの場合でも[addStr]を [len]に満たすまで挿入した結果を返します。
     * 
     * @param str
     *            対象文字列
     * @param position
     *            前に挿入 ⇒ L or l 後に挿入 ⇒ R or r
     * @param len
     *            補充するまでの桁数
     * @param addStr
     *            挿入する文字列
     * @return 変換後の文字列。
     */
    public static String fillString(String str, String position, int len,
            String addStr) {
        if (addStr == null || addStr.length() == 0) {
            throw new IllegalArgumentException("挿入する文字列の値が不正です。addStr="
                    + addStr);
        }
        if (str == null) {
            str = "";
        }
        StringBuffer buffer = new StringBuffer(str);
        while (len > buffer.length()) {
            if (position.equalsIgnoreCase("l")) {
                int sum = buffer.length() + addStr.length();
                if (sum > len) {
                    addStr = addStr.substring(0, addStr.length() - (sum - len));
                    buffer.insert(0, addStr);
                } else {
                    buffer.insert(0, addStr);
                }
            } else {
                buffer.append(addStr);
            }
        }
        if (buffer.length() == len) {
            return buffer.toString();
        }
        return buffer.toString().substring(0, len);
    }

	
	
	
	
	

	/**
	 * targetIntegerが３桁未満の場合、３桁を満たすだけ、'0'を先頭に積める
	 *
	 * @param targetInteger
	 *            補正対象の数値
	 * @return　補正後の 文字列
	 */
	public static String digitNumber(int targetInteger) {
		return digitNumber(targetInteger, DEFAULT_DIGIT);
	}

	/**
	 * targetIntegerがlen未満の場合、lenを満たすだけ、'0'を先頭に積める
	 *
	 * @param targetInteger
	 *            補正対象の数値
	 * @param len
	 *            補正値
	 * @return　補正後の 文字列
	 */
	public static String digitNumber(int targetInteger, int len) {
		// 入力した補正後の桁数が標準桁数より小さい場合は文字列に変換して戻す
		String diffLen = Integer.toString(targetInteger);
		if (diffLen.length() >= len) {
			return diffLen;
		} else {
			// 初期化
			int compenstionDigit = len - diffLen.length();
			StringBuilder ret = new StringBuilder();
			// 補正の実施
			for (int loop = 0; loop < compenstionDigit; loop++) {
				ret.append("0");
			}
			ret.append(targetInteger);
			return ret.toString();
		}
	}

	/**
	 * 指定された文字数になるようにスペースを補填
	 *
	 * @param targetString
	 *            補填対象の文字列
	 * @param maxSize
	 *            最大桁数
	 * @return　補填後の 文字列
	 */
	public static String setCoverSpace(String targetString, int maxSize,
			boolean flagTop) {

		if (flagTop) {
			// 前詰します
			return leftSpaceJustified(targetString, maxSize);
		} else {
			// 後詰します
			return rightSpaceJustified(targetString, maxSize);
		}
	}

	/**
	 * 対象文字の左側にスペースを規定数結合する
	 *
	 * @param targetString
	 *            補填対象の文字列
	 * @param maxSize
	 *            最大桁数
	 * @return 補填後の文字列
	 */
	private static String leftSpaceJustified(String targetString, int maxSize) {
		// 取得した文字サイズの計算
		int strSize = targetString.length();
		// 初期化
		StringBuilder buf = new StringBuilder();
		int compenstionDigit = maxSize - strSize;

		//
		for (int loop = 0; loop < compenstionDigit; loop++) {
			setSpace(targetString, buf);
		}
		buf.append(targetString);
		return buf.toString();
	}

	private static void setSpace(String targetString, StringBuilder buf) {
		if (isSingleByte(targetString)) {
			buf.append(SINGLE_BYTE_SPACE);
		} else {
			buf.append(MULTI_BYTE_SPACE);
		}
	}

	private static String rightSpaceJustified(String targetString, int maxSize) {
		// 取得した文字サイズの計算
		int strSize = targetString.length();
		StringBuilder buf = new StringBuilder(targetString);
		if (maxSize > strSize) {
			int count = maxSize - strSize;
			for (int i = 0; i < count; i++) {
				setSpace(targetString, buf);
			}
		}
		return buf.toString();
	}

	/**
	 * 文字の全角と半角を判定して半角であれば正を返す
	 *
	 * @param targetString
	 * @return　boolean
	 */
	private static boolean isSingleByte(String targetString) {
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
	 *
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
	 * 数値に変換可能な文字を変換する。 数値に変換できない文字列は0に変換される
	 *
	 * @param str
	 *            数値変換したい文字列
	 * @return long 変換された数理
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
	 *
	 * 漢数字を半角数字に変換します。
	 *
	 * @param chinaNum
	 *            変換したい漢数字
	 * @return long 変換した値
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

		retNum = hundredsPlace + tensPlace + onesPlace;



		//半角数字を全角数字にする。
		String ret =  convertMultiByteNum(retNum);


		return ret;

	}

	/**
	 * 半角数字を全角数字にする。
	 * @param inNum
	 * @return
	 */
	private static String convertMultiByteNum(long inNum) {

		String strInNum = Long.toString(inNum);
		 StringBuffer sb = new StringBuffer(strInNum);
	    for (int i = 0; i < sb.length(); i++) {
	        char c = sb.charAt(i);
	        if (c >= '0' && c <= '9') {
	            sb.setCharAt(i, (char) (c - '0' + '０'));
	          }
	        }

		return sb.toString();
	}

	/**
	 * 漢数字を半角数字にする詳細
	 * @param strNum
	 * @return
	 */
	private static long getNum(String strNum) {

		long num = 0L;
		// 漢数字を数値化する。
		for (int i = 0; i < strNum.length(); i++) {
			switch (strNum.charAt(i)) {
			case '零':
			case '〇':
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
	 * バイト配列を16進数の文字列に変換する。
	 *
	 * @param bytes バイト配列
	 * @return 16進数の文字列
	 */
	public static String asHex(byte bytes[]) {
		// バイト配列の２倍の長さの文字列バッファを生成。
		StringBuffer strbuf = new StringBuffer(bytes.length * 2);

		// バイト配列の要素数分、処理を繰り返す。
		for (int index = 0; index < bytes.length; index++) {
			// バイト値を自然数に変換。
			int bt = bytes[index] & 0xff;

			// バイト値が0x10以下か判定。
			if (bt < 0x10) {
				// 0x10以下の場合、文字列バッファに0を追加。
				strbuf.append("0");
			}

			// バイト値を16進数の文字列に変換して、文字列バッファに追加。
			strbuf.append(Integer.toHexString(bt));
		}

		/// 16進数の文字列を返す。
		return strbuf.toString();
	}

	/**
	 * 16進数の文字列をバイト配列に変換する。
	 *
	 * @param hex 16進数の文字列
	 * @return バイト配列
	 */
	public static byte[] asByteArray(String hex) {
		// 文字列長の1/2の長さのバイト配列を生成。
		byte[] bytes = new byte[hex.length() / 2];

		// バイト配列の要素数分、処理を繰り返す。
		for (int index = 0; index < bytes.length; index++) {
			// 16進数文字列をバイトに変換して配列に格納。
			bytes[index] = (byte) Integer.parseInt( hex.substring(index * 2, (index + 1) * 2), 16);
		}

		// バイト配列を返す。
		return bytes;
	}

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
}
