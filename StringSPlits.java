/**
 * 
 */
package co.jp.console.sample;

/**
 * @author h_tozawa
 *
 */
public class StringSPlits {

	/**
	 * 
	 */
	public StringSPlits() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String str = "リンゴ,|/パイナップル,|/すいか,|/ぶどう";
		String[] fruit = str.split("/", 3);
		//String[] fruit = str.split("\\|");
		System.out.println("Size: " + fruit.length);
		
		int count = 0;
		for(String item : fruit){
			
			System.out.println(count + "番目の要素 = :" + item);
			count++;
			
		}

		
		
		String xmlstr = "<?xml version=1.0 encoding="+"UTF-8" + "standalone="+"no" + "?>" + "<HEAD></HEAD>" + "<BODY></BODY>"
		
+"<?xml version=1.0 encoding="+"UTF-8" + "standalone="+"no" + "?>" + "<BHEAD></BHEAD>" + "<BBODY></BBODY>" + "<BFOOTER></BSFOOTER>";
				
				;
		
		
		
		String[] xmlstrArray = xmlstr.split("\\<\\?xml");
		
		
		System.out.println("Size: " + xmlstrArray.length);
		// 文字の分割
		xmlstrArray[0] = "<?xml";
		
		
		
//		count = 0;
//		for(String item : xmlstrArray){
//			
//			System.out.println(count + "番目の要素 = :" + item);
//			if(count > 0){
//				System.out.println(count + "文字の結合 = :" + (xmlstrArray[0] +item));
//			}
//			count++;
//			
//		}
		
		for(int i = 1 ; i < xmlstrArray.length ; i++){
			
			System.out.println(i + "文字の結合 = :" + (xmlstrArray[0] +xmlstrArray[i]));
		}
		
		

	}

}
