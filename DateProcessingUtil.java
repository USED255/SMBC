/**
 *
 */
package jp.util;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author PC User
 *
 */
public final class DateProcessingUtil  implements Serializable {





	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	//private static int hour =  0;
	//private static int minute =  0;
	private static int second = 0;

	private static int year= 1900;
	private static int date = 1;
	private static Calendar cal;



	/**
	 *
	 */
	private static final class DateProcessingUtilHolder{
		private static final  DateProcessingUtil instance = new DateProcessingUtil();
	 }


	private  DateProcessingUtil() {
	}

	public static DateProcessingUtil getInstance(){
		return DateProcessingUtilHolder.instance;
	}


	public static String setSystemDate() {
		return setSystemDate(new Date());
	}

	public static String setSystemDate(Date date) {
		SimpleDateFormat fmt = new SimpleDateFormat("[yyyy/MM/dd HH:mm:ss.SSS] ");
		return fmt.format(date);
	}


	public static String setSystemDate(Timestamp date) {
		SimpleDateFormat fmt = new SimpleDateFormat("[yyyy/MM/dd HH:mm:ss.SSS] ");
		return fmt.format(date);
	}


	public static Timestamp getIncrementDate(int index){

		if(index > 0){
			second = cal.get(Calendar.SECOND);
			date= cal.get(Calendar.DAY_OF_MONTH);
			year= cal.get(Calendar.YEAR);

		}

		if(index % 800 == 0 && index / 800 > 0){

			date++;
			second++;
			cal.set(Calendar.DATE, cal.get(Calendar.DATE)+1);

		}else {
			second++;
		}
		cal.set(Calendar.SECOND, second);

		if(date < cal.get(Calendar.DATE)){
			cal.set(Calendar.DATE, date);
		}

		// Step3:“±o‚µ‚½ŒŽ‰“ú‚ðTimestampŒ^‚É•ÏŠ·‚·‚é
		Timestamp fromTm = new Timestamp(cal.getTimeInMillis());
		return fromTm;
	}

	public static void setCalenders(Calendar cal_){
		cal  = cal_;
		year = cal.get(Calendar.YEAR);
		date = cal.get(Calendar.DAY_OF_MONTH);
	}

}
