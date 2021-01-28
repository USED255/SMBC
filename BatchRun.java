/**
 *
 */
package jp.co.profitcube.eacris.report.attribute.judgement.normalization;

import java.io.IOException;

/**
 * @author PC User
 *
 */
public class BatchRun {

	/**
	 *
	 */
	public BatchRun() {
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String cmd = "cmd.exe /c start E:\\view\\renFile.bat";
		Runtime.getRuntime().exec(cmd);

	}

}
