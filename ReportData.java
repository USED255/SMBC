package jp.co.core.ddm.pack.sbti.excel.poi.data;

/**
 * 帳票データクラス
 * @author TOZAWA
 *
 */
public class ReportData {

	private HeaderData header;

	private DetailsData details;

	public DetailsData getDetails() {
		return details;
	}

	public void setDetails(DetailsData details) {
		this.details = details;
	}

	public HeaderData getHeader() {
		return header;
	}

	public void setHeader(HeaderData header) {
		this.header = header;
	}

}
