/**
 * 
 */
package jp.console.main;

/**
 * @author h_tozawa
 *
 */
public class StringUtils {
	
	
	
	
	private String strData = null;
	
	
	

	/**
	 * 
	 */
	public StringUtils() {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	public StringUtils(String msg) {
		this.setStrData(msg);
	}
	
	public static char[] getOldDictionary(String _strData) {


		String strData = new String(_strData);

		char[] charData = strData.toCharArray();
		
		char[] newCharaData = new char[charData.length];
		
		int j =0;
		for(int i = charData.length -1 ; i > -1 ; i-- ){
			newCharaData[j] = charData[i];
			j++;
		}
		return newCharaData;
	}

	
	public static String getOldDictionaryToString(String _strData) {

		return String.valueOf(getOldDictionary(_strData));
	}

	public String getStrData() {
		return strData;
	}

	public void setStrData(String strData) {
		this.strData = strData;
	}
	

    /**
     * LPADを行います。 文字列[str]の左に指定した文字列[addStr]を[len]に 満たすまで挿入します。
     * 
     * @param str
     *            対象文字列
     * @param len
     *            補充するまでの桁数（LPADを行った後のサイズを指定します。）
     * @param addStr
     *            挿入する文字列
     * @return 変換後の文字列。
     */
    public static String lpad(String str, int len, String addStr) {
        return fillString(str, "L", len, addStr);
    }

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

	
	
	
}
