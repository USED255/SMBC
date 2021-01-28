package jp.co.core.ddm.pack.sbti.excel.poi.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Excelファイルと、出力用データを結びつける(見出し用)
 * @author TOZAWA
 *
 */
public class HeaderData {

	private String reportName = null;

	private Map<String, String> dataMap = new HashMap<String, String>();

	public Map<String, String> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, String> dataMap) {
		this.dataMap = dataMap;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

}
