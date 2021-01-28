package jp.co.core.ddm.pack.sbti.localMain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jp.co.core.ddm.pack.sbti.excel.poi.data.DetailsData;
import jp.co.core.ddm.pack.sbti.excel.poi.data.HeaderData;
import jp.co.core.ddm.pack.sbti.excel.poi.data.ReportData;
import jp.co.core.ddm.pack.sbti.excel.poi.dto.Scr0005Dto;
import jp.co.core.ddm.pack.sbti.excel.poi.dto.Scr0005ItemDto;
import jp.co.core.ddm.pack.sbti.logic.StaticsOPILogic;


public class OpiSample {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {


		StaticsOPILogic poiLogic = new StaticsOPILogic();

		//String input = "C:/local/inout/inputSalesList.xlsx";
		//String otput = "C:/local/inout/outputSalesList.xlsx";

		String input = "C:/local/inout/○○組_テンプレート.xlsx";


		StringBuffer sb = new StringBuffer("C:/local/inout/○○組_");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");

		sb.append(sdf.format(new Date()));
		sb.append(".xlsx");
		String otput = sb.toString();

		// Excel形式チェック（必須ではない）
		if (!poiLogic.isExistExcelFile(input)) {
			System.out.println("error of inputFile");
			System.exit(1);
		}

		// Excel形式チェック（必須ではない）
		if (!poiLogic.isExcelFile(otput)) {
			System.out.println("error of outputFile");
			System.exit(1);
		}

		// ワークブック生成
		XSSFWorkbook wb = poiLogic.getWorkbook(input);
		// データ生成
		List<ReportData> dataList = new ArrayList<ReportData>();

		// データベースから直接取得したり、加工したり、
		Scr0005Dto dto = new Scr0005Dto();

		// ヘッダ部
		dto.organizationName = "○○組";


		//  明細部分の取得
		List<Scr0005ItemDto> listData = new ArrayList<Scr0005ItemDto>();

		//
		dto.itemList = listData;

		dataList.add(setData(dto));
		// create controller
		XSSFWorkbook newWorkBook = poiLogic.create(wb, dataList);
		// データを設定してExcelファイルの出力
		poiLogic.write(newWorkBook, otput);
	}

	/**
	 *
	 * @return  出力データ設定
	 */
	private static ReportData setData(Scr0005Dto reportDto) {

		ReportData dataContainer = new ReportData();

		HeaderData header = new HeaderData();
//		header.setReportName("SalesList");
		header.setReportName("OrganizationList");
		Map<String, String> dataMap = header.getDataMap();

		// ヘッダー部編集
//		dataMap.put("$OUTPUT_DATE", sdf.format(new Date()));
//		dataMap.put("$PERIOD", "2010年4月");
//		dataMap.put("$TOTAL_SALES", "780,000");

		dataMap.put("$ORGANIZATION_NAME", reportDto.organizationName);
		dataMap.put("$ORG_CODE", "00023");
		dataMap.put("$OFFICE_LOCATE_ADD", "滋賀県大津市坂本本町");
		dataMap.put("$JURISDICTION_NAME", "浦和署");
		dataContainer.setHeader(header);

		DetailsData details = new DetailsData();
		Map<String, Object[]> dataListMap = details.getDataListMap();

// TODO
		// 明細行（縦方向にデータを設定）
		// 明細部分取得
		List<Scr0005ItemDto>  itemList = reportDto.itemList;

		Integer[] numArray = new Integer[itemList.size()];
		String[] orgPosition1NameArry = new String[itemList.size()];
		String[] orgPosition2NameArry = new String[itemList.size()];

		String[] orgPosition3NameArry = new String[itemList.size()];
		String[] orgPosition4NameArry = new String[itemList.size()];
		String[] orgPosition5NameArry = new String[itemList.size()];




		for(int index = 0 ; index < itemList.size() ; index++){
			numArray[index] = index + 1;
			orgPosition1NameArry[index] = itemList.get(index).orgPosition1Name;
			orgPosition2NameArry[index] = itemList.get(index).orgPosition2Name;
			orgPosition3NameArry[index] = itemList.get(index).orgPosition3Name;
			orgPosition4NameArry[index] = itemList.get(index).orgPosition4Name;
			orgPosition5NameArry[index] = itemList.get(index).orgPosition5Name;














		}
// TODO



		dataListMap.put("$NUMBER[]", new Integer[] { 1, 2, 3, 4 });
		dataListMap.put("$NUMBER[]", new Integer[] { 1, 2, 3, 4 });



		dataListMap.put("$ORG_POSITION1_NAME[]",  new String[] { "ああああ（ああああ）", "あああい（ああああ）",
														"あああう（ああああ）", "あああえ（ああああ）" });
		dataListMap.put("$ORG_POSITION2_NAME[]",  new String[] { "いあああ（ああああ）", "いああい（ああああ）",
														"いああう（ああああ）", "いああえ（ああああ）" });
		dataListMap.put("$ORG_POSITION3_NAME[]",  new String[] { "うあああ（ああああ）", "うああい（ああああ）",
														"うああう（ああああ）", "うああえ（ああああ）" });
		dataListMap.put("$ORG_POSITION4_NAME[]",  new String[] { "えあああ（ああああ）", "えああい（ああああ）",
														"えああう（ああああ）", "えああえ（ああああ）" });
		dataListMap.put("$ORG_POSITION5_NAME[]",  new String[] { "おあああ（ああああ）", "おああい（ああああ）",
														"おああう（ああああ）", "おああえ（ああああ）" });
		dataListMap.put("$EXECUTIVE[]",  new String[] { "○", "","", "" });
		dataListMap.put("$PERSONAL_NUMBER[]",  new String[] { "99-99-99990", "99-99-99991","99-99-99992", "99-99-99993" });
		dataListMap.put("$NAME[]",  new String[] { "ああああああああああ金剛", "ああああああああああ比叡",
														"ああああああああああ榛名", "ああああああああああ霧島" });
		dataListMap.put("$RUBY[]",  new String[] { "アアアアアアアアアアアア", "イアアアアアアアアアアア",
														"ウアアアアアアアアアアア", "エアアアアアアアアアアア" });
		dataListMap.put("$BIRTHDAY[]",  new String[] { "2000/12/31", "2005/12/31","2010/12/31", "2015/12/31" });
		dataListMap.put("$RESPONSIBILITY[]",  new String[] { "埼玉県", "埼玉県","埼玉県", "埼玉県" });
		dataListMap.put("$MATERIAL_POSITIVE[]",  new String[] { "○", "○","○", "○" });
		dataListMap.put("$MATERIAL_NEGATIVE[]",  new String[] { "○", "○","○", "○" });
		dataListMap.put("$CROSS_FINGER[]",       new String[] { "○", "○","○", "○" });
		dataListMap.put("$TATTOO[]",             new String[] { "○", "○","○", "○" });
		dataListMap.put("$SOCIAL_POSITION[]",    new String[] { "○", "○","○", "○" });
		dataListMap.put("$DOMICILE[]", new String[] { "埼玉県さいたま市浦和区高砂3-15-1",
													"埼玉県さいたま市浦和区高砂3-15-2",
													"埼玉県さいたま市浦和区高砂3-15-3",
													"埼玉県さいたま市浦和区高砂3-15-4" });
		dataListMap.put("$ADDRESS[]", new String[] { "埼玉県さいたま市浦和区高砂3-25-1",
													"埼玉県さいたま市浦和区高砂3-25-2",
													"埼玉県さいたま市浦和区高砂3-25-3",
													"埼玉県さいたま市浦和区高砂3-25-4" });
		dataListMap.put("$RESIDENCE[]", new String[] { "埼玉県さいたま市浦和区高砂3-35-1",
														"埼玉県さいたま市浦和区高砂3-35-2",
														"埼玉県さいたま市浦和区高砂3-35-3",
														"埼玉県さいたま市浦和区高砂3-35-4" });
		dataListMap.put("$OFFENSE[]", new String[] { "道路交通法違反", "恫喝","薬物取り扱い法違反", "銃刀法違反" });
		dataListMap.put("$FIXED_DATE[]",  new String[] { "2000/12/31", "2005/12/31","2010/12/31", "2015/12/31" });
		dataListMap.put("$DISPOSAL_RESULT[]", new String[] { "ああああああああああああああああああああああああああああ",
				"いあああああああああああああああああああああああああああ",
				"うあああああああああああああああああああああああああああ",
				"えあああああああああああああああああああああああああああ" });

		dataListMap.put("$VALIDITY_PERIOD[]",  new String[] { "2000/12/31", "2005/12/31","2010/12/31", "2015/12/31" });
		dataListMap.put("$ARREST[]",  new Integer[] { 900, 800,700,999 });
		dataListMap.put("$INSTRUCTION[]",  new Integer[] { 600, 500,400,99 });
		dataListMap.put("$RECURRENCE[]",  new Integer[] { 30, 20,10,0 });

		dataListMap.put("$REMARKS[]",  new String[] { "鬼嫁", "鬼神","鬼才", "鬼怒" });




//		dataListMap.put("$SALES_NO[]", new String[] { "S0000001", "S0000002",
//				"S0000003", "S0000004", "S0000005", "S0000006", "S0000007",
//				"S0000008", "S0000009", "S0000010", "S0000011", "S0000012",
//				"S0000013", "S0000014", "S0000015", "S0000016", "S0000017",
//				"S0000018", "S0000019", "S0000020", "S0000021", "S0000022",
//				"S0000023", "S0000024", "S0000025", "S0000026", "S0000027" });
//		dataListMap.put("$SALES_DATE[]", new String[] { "2010/04/01",
//				"2010/04/01", "2010/04/01", "2010/04/01", "2010/04/01",
//				"2010/04/01", "2010/04/01", "2010/04/01", "2010/04/01",
//				"2010/04/01", "2010/04/01", "2010/04/01", "2010/04/01",
//				"2010/04/01", "2010/04/01", "2010/04/01", "2010/04/01",
//				"2010/04/01", "2010/04/01", "2010/04/02", "2010/04/02",
//				"2010/04/02", "2010/04/02", "2010/04/02", "2010/04/02",
//				"2010/04/02", "2010/04/02" });
//		dataListMap.put("$CUSTOMER_CODE[]", new String[] { "C11111", "C11111",
//				"C11111", "C11111", "C11111", "C11111", "C11112", "C11112",
//				"C11112", "C11112", "C11112", "C11113", "C11113", "C11113",
//				"C11113", "C11113", "C11117", "C11117", "C11117", "C11117",
//				"C11117", "C11115", "C11115", "C11115", "C11119", "C11119",
//				"C11119" });
//		dataListMap.put("$EMPLOYEE_CODE[]", new String[] { "E0001", "E0001",
//				"E0001", "E0001", "E0001", "E0001", "E0005", "E0005", "E0005",
//				"E0005", "E0005", "E0003", "E0003", "E0003", "E0003", "E0003",
//				"E0012", "E0012", "E0012", "E0012", "E0012", "E0007", "E0007",
//				"E0007", "E0015", "E0015", "E0015" });
//		dataListMap.put("$PRODUCE_CODE[]", new String[] { "P00001", "P00002",
//				"P00003", "P00004", "P00005", "P00006", "P00001", "P00002",
//				"P00003", "P00004", "P00005", "P00001", "P00002", "P00003",
//				"P00004", "P00005", "P00001", "P00002", "P00003", "P00004",
//				"P00005", "P00001", "P00002", "P00003", "P00001", "P00002",
//				"P00003" });
//		dataListMap.put("$AMOUNT[]", new Integer[] { 1, 2, 3, 4, 5, 6, 1, 2, 3,
//				4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 1, 2, 3, });
//		dataListMap.put("$SALES_AMOUNT[]", new Integer[] { 10000, 20000, 30000,
//				40000, 50000, 60000, 10000, 20000, 30000, 40000, 50000, 10000,
//				20000, 30000, 40000, 50000, 10000, 20000, 30000, 40000, 50000,
//				10000, 20000, 30000, 10000, 20000, 30000, });

		details.setNumOfDetails(dataListMap.get("$NUMBER[]").length);

		dataContainer.setDetails(details);

		return dataContainer;
	}
}
