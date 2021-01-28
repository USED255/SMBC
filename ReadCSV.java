package jp.util;

import java.util.ArrayList;

public class ReadCSV {

	public ReadCSV() {
	}


	public ArrayList<String> doMain(String path){
		ArrayList<String> list = new ArrayList<String>();

		FileUtilitys files = new FileUtilitys(path);
		list = files.getList();

		return list;
	}
}
