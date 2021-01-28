/**
 *
 */
package jp.co.core.ddm.pack.sbti.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jp.co.core.ddm.pack.sbti.poi.data.ReportData;



/**
 * 統計情報Excel出力（POI)ロジッククラス
 *
 */
public class StaticsOPILogic implements Serializable {

	public StaticsOPILogic() {
	}
	/**
	 * Excelファイルの取得
	 * @param fileName   Excel2007形式以降のExcelファイル
	 * @return           Excel2007形式以降のwbオブジェクトデータ
	 * @throws Exception
	 */
	public XSSFWorkbook getWorkbook(String fileName) throws Exception {
		InputStream inp = new FileInputStream(fileName);
		XSSFWorkbook wb = (XSSFWorkbook) WorkbookFactory.create(inp);
		System.out.println("ファイル名「" + fileName + "」を読込ました");
		return wb;
	}

	/**
	 * Excel2007形式以降か確認
	 *
	 * @param inputFile  Excel2007形式以降のExcelファイル
	 * @return           true/false
	 */
	public boolean isExcelFile(String inputFile) {
		if (inputFile != null) {
			if (inputFile.endsWith("xlsx")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 存在するExcelファイルか確認
	 * @param inputFile Excel2007形式以降のExcelファイル
	 * @return          true/false
	 */
	public boolean isExistExcelFile(String inputFile) {
		if (isExcelFile(inputFile)) {
			File file = new File(inputFile);
			if (file.exists() && file.isFile()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Excelファイルの出力
	 * @param wb               編集済みワークブックオブジェクト
	 * @param outFile          出力ファイル名(Excel2007形式)
	 * @throws Exception
	 */
	public void write(XSSFWorkbook wb, String outFile) throws Exception {
		OutputStream out = new FileOutputStream(outFile);
		wb.write(out);
		System.out.println("ファイル名「" + outFile + "」が出力されました");
		out.close();
	}


	// レポート用のワークブックを作成
	public XSSFWorkbook create(XSSFWorkbook wb, List<ReportData> dataList) {

		//List<ReportData> reportList = dataList;

		int numOfDetails = 0;

		// 印刷範囲を取得
		String printArea = wb.getPrintArea(wb.getSheetIndex("TEMPLATE"));
		if (printArea != null) {
			int sheetPosition = printArea.indexOf("!");
			if (sheetPosition != -1) {
				printArea = printArea.substring(sheetPosition + 1);
			} else {
				printArea = null;
			}
		}

		if (dataList.size() > 1) {
			for (int reportIndex = 0; reportIndex < dataList.size(); reportIndex++) {
				// テンプレートシートをデータ数分シートコピーする
				Sheet cloneSheet = wb.cloneSheet(wb.getSheetIndex("TEMPLATE"));
				// ワークシート名を設定
				wb.setSheetName(wb.getSheetIndex(cloneSheet), dataList.get(
						reportIndex).getHeader().getReportName());
				// 印刷範囲を設定
				if (printArea != null) {
					wb.setPrintArea(wb.getSheetIndex(cloneSheet), printArea);
				}
			}
			// テンプレートシートの削除
			wb.removeSheetAt(wb.getSheetIndex("TEMPLATE"));
		}

		ReportData reportData = null;
		Map<String, String> headerMap = null;
		Map<String, Object[]> detailsMap = null;

		// ワークシート単位の繰返し処理
		int numOfSheet = wb.getNumberOfSheets();
		for (int sheetIndex = 0; sheetIndex < numOfSheet; sheetIndex++) {
			XSSFSheet sheet = wb.getSheetAt(sheetIndex);

			// ワークシートに対応するデータを取得
			reportData = dataList.get(sheetIndex);
			headerMap = reportData.getHeader().getDataMap();
			detailsMap = reportData.getDetails().getDataListMap();
			if (reportData.getDetails().getNumOfDetails() != 0) {
				numOfDetails = reportData.getDetails().getNumOfDetails();
			}

			// 行単位の繰返し処理
			int lastRow = sheet.getLastRowNum();
			for (int rowIndex = 0; rowIndex <= lastRow; rowIndex++) {
				XSSFRow row = sheet.getRow(rowIndex);
				if (row == null) {
					continue;
				}

				// セル単位の繰返し処理
				int lastColumn = row.getLastCellNum();
				for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
					XSSFCell cell = row.getCell(columnIndex);
					if (cell == null) {
						continue;
					}

					if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
						// 値を置換える為に準備したセルであればデータを設定
						String key = cell.getStringCellValue();
						if (headerMap.containsKey(key)) {
							setCellValue(cell, headerMap.get(key));
						} else if (detailsMap.containsKey(key)) {
							setCellValues(cell, sheet, detailsMap.get(key),numOfDetails);
						}
					}
				}
			}
		}
		// 完成したワークブックオブジェクトを戻す
		return wb;
	}

	// セルに値を設定
	private void setCellValue(XSSFCell cell, Object value) {

		CellStyle style = cell.getCellStyle();

		if (value != null) {
			if (value instanceof String) {
				cell.setCellValue((String) value);
			} else if (value instanceof Number) {
				Number numValue = (Number) value;
				if (numValue instanceof Float) {
					Float floatValue = (Float) numValue;
					numValue = new Double(String.valueOf(floatValue));
				}
				cell.setCellValue(numValue.doubleValue());
			} else if (value instanceof Date) {
				Date dateValue = (Date) value;
				cell.setCellValue(dateValue);
			} else if (value instanceof Boolean) {
				Boolean boolValue = (Boolean) value;
				cell.setCellValue(boolValue);
			}
		} else {
			cell.setCellType(Cell.CELL_TYPE_BLANK);
			cell.setCellStyle(style);
		}

	}

	private void setCellValues(XSSFCell baseCell, XSSFSheet sheet, Object[] values, int numOfDetails) {

		// 繰返し処理開始セルの位置情報を保持
		int startRowPosition = baseCell.getRowIndex();
		int columnPosition = baseCell.getColumnIndex();

		// 明細の数分繰返し処理をする
		for (int i = 0; i < numOfDetails; i++) {

			// 行を取得または生成
			XSSFRow row = sheet.getRow(startRowPosition + i);
			if (row == null) {
				row = sheet.createRow(startRowPosition + i);
				// 繰返し処理開始行と同じ高さを設定
				row.setHeight(sheet.getRow(startRowPosition).getHeight());
			}
			// セルを取得または生成
			XSSFCell cell = row.getCell(columnPosition);
			if (cell == null) {
				cell = row.createCell(columnPosition);
				// 繰返し処理開始セルの情報をコピー
				copyCell(baseCell, cell);
			}
			// セルに値を設定
			setCellValue(cell, values[i]);
		}

	}

	// セルの値を別セルにコピーする
	public static void copyCell(XSSFCell fromCell, XSSFCell toCell) {

		if (fromCell != null) {

			int cellType = fromCell.getCellType();
			switch (cellType) {
			case XSSFCell.CELL_TYPE_BLANK:
				break;
			case XSSFCell.CELL_TYPE_FORMULA:
				toCell.setCellFormula(fromCell.getCellFormula());
				break;
			case XSSFCell.CELL_TYPE_BOOLEAN:
				toCell.setCellValue(fromCell.getBooleanCellValue());
				break;
			case XSSFCell.CELL_TYPE_ERROR:
				toCell.setCellErrorValue(fromCell.getErrorCellValue());
				break;
			case XSSFCell.CELL_TYPE_NUMERIC:
				toCell.setCellValue(fromCell.getNumericCellValue());
				break;
			case XSSFCell.CELL_TYPE_STRING:
				toCell.setCellValue(fromCell.getRichStringCellValue());
				break;
			default:
			}

			if (fromCell.getCellStyle() != null) {
				toCell.setCellStyle(fromCell.getCellStyle());
			}

			if (fromCell.getCellComment() != null) {
				toCell.setCellComment(fromCell.getCellComment());
			}
		}
	}



}
