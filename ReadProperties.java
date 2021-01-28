package jp.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author Administrator
 *
 */
public class ReadProperties {



	public static HashMap<String, String> getPropMap(String path) {


		Properties propUTF8 = null;



		HashMap<String, String> propMap = new HashMap<String, String>();


		try {
			propUTF8 = loadUTF8Properties(path);


			if (propUTF8 == null) {
				throw new NullPointerException();
			} else {
				for (String propertyName : propUTF8.stringPropertyNames()) {
					propMap.put(propertyName, propUTF8.getProperty(propertyName));
				}

			}


		} catch (IOException e) {

			e.printStackTrace();
		}




		return propMap;

	}

	public static HashMap<String, String> getSjisPropMap(String path) {


		Properties prop = null;



		HashMap<String, String> propMap = new HashMap<String, String>();


		try {
			prop = loadSJISProperties(path);


			if (prop == null) {
				throw new NullPointerException();
			} else {
				for (String propertyName : prop.stringPropertyNames()) {
					propMap.put(propertyName, prop.getProperty(propertyName));
				}

			}


		} catch (IOException e) {

			e.printStackTrace();
		}




		return propMap;

	}


	public static void setPropMap(HashMap<String, String> map, String path){

		try {
			Properties myConf = new Properties();

			InputStreamReader myConfFileIn = new InputStreamReader(new FileInputStream(path),"SJIS");

			myConf.load(myConfFileIn); // ← ① ファイルから読み込む。




			for (String key : map.keySet()) {
//				System.out.println(SystemDateUtil.getSystemDate() + key + " : " + map.get(key));
				myConf.setProperty(key, map.get(key));
			}

			OutputStreamWriter myConfFileOut = new OutputStreamWriter(new FileOutputStream(path),"SJIS");
			myConf.store(myConfFileOut, "CostPropertyFile"); // ← ④
																// 変更した内容をファイルに書き込む。
		} catch (Exception e) {
			e.printStackTrace();
		}


	}













	private static Properties loadUTF8Properties(String path) throws IOException {
		Properties retProp = new Properties();
		retProp.load(new InputStreamReader(new FileInputStream(path), "UTF-8"));
		return retProp;
	}

	private static Properties loadSJISProperties(String path)
			throws IOException {
		Properties retProp = new Properties();
		retProp.load(new InputStreamReader(new FileInputStream(path), "SJIS"));
		return retProp;
	}

}
