package jp.co.core.ddm.pack.sbti.excel.poi.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Excelファイルと、出力用データを結びつける(明細・縦方向連続データ用)
 * @author TOZAWA
 *
 */
public class DetailsData {

	private int numOfDtails = 0;

	private Map<String, Object[]> dataListMap = new HashMap<String, Object[]>();

	public Map<String, Object[]> getDataListMap() {
		return dataListMap;
	}

	public void setDataListMap(Map<String, Object[]> dataListMap) {
		this.dataListMap = dataListMap;
	}

	public int getNumOfDetails() {
		return numOfDtails;
	}

	public void setNumOfDetails(int numOfDtails) {
		this.numOfDtails = numOfDtails;
	}


}
