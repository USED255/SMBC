package jp.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class FileUtilitys {


	private  ArrayList<String> list ;


	public FileUtilitys(String fileName) {
		list = new ArrayList<String>();
		String newName = new String(fileName);

		try {
			getLineDetail(newName);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}


	private void getLineDetail(String file) throws Exception
	{
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		while ((line = br.readLine()) != null) {
			list.add(line);
		}
		br.close();
		fr.close();

	}

	public ArrayList<String> getList() {
		return list;
	}

}
