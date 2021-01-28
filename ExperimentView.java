
/**
 *
 */
package jp.co.profitcube.eacris.report.attribute.judgement.normalization;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jp.util.CaptionExpression;
import jp.util.DateProcessingUtil;
import jp.util.LoopClasses;
import jp.util.ReadCSV;
import jp.util.StringUtil;
import jp.util.SystemDateUtil;

import org.apache.log4j.Logger;

/**
 * @author さぶれ(*´艸｀)
 *
 */
public class ExperimentView {


	public static Logger logger;

	SystemDateUtil systemDate = new SystemDateUtil();




	public static void systemView(String str) {

		SystemDateUtil systemDate = new SystemDateUtil();

		System.out.println(systemDate.setSystemDate() + str);
	}

	public static void systemView(int intStr) {
		SystemDateUtil systemDate = new SystemDateUtil();
		System.out.println(systemDate.setSystemDate() + intStr);
	}

	//	private static void systemView(long longStr) {
	//		SystemDateUtil systemDate = new SystemDateUtil();
	//		System.out.println(systemDate.setSystemDate() + longStr);
	//	}


	/**
	 *
	 *
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		if(args.length <= 0){
			doView_08();
			System.exit(0);
		}

		try {

			int intCase = Integer.valueOf(args[0]);
			switch (intCase) {
			case 1:
				doView_01();
				break;
			case 2:
				doView_02();
				break;
			case 3:
				doView_03();
				break;
			case 4:
				doView_04();
				break;
			case 5:
				doView_05();
				break;
			case 6:
				doView_06();
				break;
			case 7:
				doView_07();
				break;
			case 8:
				doView_08();
				break;
				//			case 9:
				//				doView_09();
				//				break;
				//			case 10:
				//				doView_10();
				//				break;
			case 99:
				doView_01();
				doView_02();
				doView_03();
				doView_04();
				doView_05();
				doView_06();
				//				doView_07();
				//				doView_08();
				//				doView_09();
				break;

			default:
				break;
			}
		}catch(NumberFormatException e){

			System.err.println("No name");
			System.exit(1);
		}
	}



	private static void doView_08() {
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		CaptionExpression.setStackTraceElements(stackTraceElements);
		CaptionExpression.start(1);

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp.getTime());


		//int hour =  cal.get(Calendar.HOUR);
		//int minute =  0;
		//int second = 0;


		cal.set(Calendar.HOUR, 9);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 0);


		DateProcessingUtil.getInstance();
		DateProcessingUtil.setCalenders(cal);


		LoopClasses loops = new LoopClasses();


		int maxLoop = 800 * 10;

		loops.doMain(maxLoop);


		CaptionExpression.end(1);

	}

	private static void doView_07() {
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		CaptionExpression.setStackTraceElements(stackTraceElements);
		CaptionExpression.start(1);

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		// STEP1:DBから取得したデータ(Timestamp型)をCalendar型に変換する
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp.getTime());


		int hour =  cal.get(Calendar.HOUR);
		int minute =  0;
		int second = 0;


		cal.set(Calendar.HOUR, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);

		int year= cal.get(Calendar.YEAR);
		int date = cal.get(Calendar.DAY_OF_MONTH);

		for(int index = 0 ; index < 900000 ; index++){

			if(index > 0){
				second = cal.get(Calendar.SECOND);
				date= cal.get(Calendar.DAY_OF_MONTH);
				year= cal.get(Calendar.YEAR);

			}

			if(index % 800 == 0 && index / 800 > 0){

				date++;
				cal.set(Calendar.DATE, cal.get(Calendar.DATE)+1);

			}else {
				second++;
			}
			cal.set(Calendar.SECOND, second);

			if(date < cal.get(Calendar.DATE)){
				cal.set(Calendar.DATE, date);
			} else {
//				if(year < cal.get(Calendar.YEAR)){
//					cal.set(Calendar.YEAR, year);
//				}
			}



			// Step3:導出した月初日をTimestamp型に変換する
			Timestamp fromTm = new Timestamp(cal.getTimeInMillis());
			System.out.println(StringUtil.digitNumber(index + 1, 8) + " : " +  fromTm.toString());
			//Thread.sleep(500);
		}



		CaptionExpression.end(1);
	}

	private static void doView_06() {

		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		CaptionExpression.setStackTraceElements(stackTraceElements);
		CaptionExpression.start(1);

		//String target = "㈱日㈲産㈹自動車㈱";
		String target = "㍾㍽㍼";


		char[] charArray = target.toCharArray();

		systemView("[in     ]: " + target);

		//
		List<Character> charList = new ArrayList<Character>();


		for(char character : charArray){
			NormalizeUtil.placeCharacterList(charList, character);
		}

		try {
			String outPutStr = NormalizeUtil.stringToCharacterList(charList);
			systemView("[out    ]: " + outPutStr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CaptionExpression.end(1);
		}

	}



	private static void doView_05() {

		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		CaptionExpression.setStackTraceElements(stackTraceElements);
		CaptionExpression.start(1);


		String nameKana = "株株式会社㈱";
		byte[] bNameKana;
		try {
			bNameKana = nameKana.getBytes("Shift_JIS");

			String strHex = StringUtil.asHex(bNameKana);

			System.out.println("hex:"+strHex);

			String newStr = new String(StringUtil.asByteArray(strHex), "Shift_JIS");

			System.out.println("str:"+newStr);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			CaptionExpression.end(1);
		}
	}

	private static void doView_04() {

		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		CaptionExpression.setStackTraceElements(stackTraceElements);
		CaptionExpression.start(1);

		String strBuf = "一二三四五六七八九〇";



		char[] charArray = strBuf.toCharArray();
		for (char charBuf : charArray) {

			int intValues = (int)charBuf;

			System.out.println(charBuf + "　：　" + intValues);
		}


		System.out.println(strBuf.replaceAll("ﾞ", ""));

		strBuf = "ﾄﾞﾄﾞｳﾞﾉﾐｽｽ";
		System.out.println(strBuf);
		System.out.println(strBuf.replaceAll("ｳﾞ", "ﾌ"));
		CaptionExpression.end(1);


	}

	private static void doView_03() {
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		CaptionExpression.setStackTraceElements(stackTraceElements);
		CaptionExpression.start(1);

		String str = ConverterUtil.chinaNumToArabicNum("百十");
		System.out.println(str);

		str = ConverterUtil.chinaNumToArabicNum("五百十二");
		System.out.println(str);
		CaptionExpression.end(1);
	}

	private static void doView_02() {

		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		CaptionExpression.setStackTraceElements(stackTraceElements);
		CaptionExpression.start(1);

		String str = "あ";
		byte[] bytes;
		try {
			bytes = str.getBytes("JIS");
			int loop = 0;
			for(byte code : bytes){
				systemView(code);
				systemView(StringUtil.digitNumber(loop, 5) + "");
				loop++;

			}
			// 文字「あ」です。
			char c = 'あ';

			// 文字に対応するUnicodeコードを取得します。 (UTF-8)
			int code = (int)c;

			// 文字コードを表示します。
			System.out.println(code);
			System.out.printf("%02x\n", code);
			systemView(code);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			CaptionExpression.end(1);
		}


	}

	private static void doView_01() {

		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		CaptionExpression.setStackTraceElements(stackTraceElements);

		CaptionExpression.start(1);

		ReadCSV csvRead = new ReadCSV();

		ArrayList<String> list = csvRead.doMain("E:/Users/CSV/Input/writers.csv");

		for (String str : list) {
			systemView(str);
		}

		CaptionExpression.end(1);

	}
}
