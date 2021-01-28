package jp.co.core.ddm.pack.sbti.localMain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jp.co.core.ddm.pack.sbti.logic.StaticsPOILogic;
import jp.co.core.ddm.pack.sbti.poi.data.DetailsData;
import jp.co.core.ddm.pack.sbti.poi.data.HeaderData;
import jp.co.core.ddm.pack.sbti.poi.data.ReportData;
import jp.co.core.ddm.pack.sbti.poi.dto.Scr0005Dto;


public class OpiSample {

	/** テンプレートExcelファイル配置場所 */
	private static final String TEMPLATE_FILE_ROOT = "C:/local/inout/";

	/** テンプレートExcelファイル名 */
	private static final String SCR0005_TEMPLATE_FILE_NAME = "○○組_テンプレート";

	/** 出力Excelファイル配置場所 */
	private static final String SCR0005_FILE_ROOT = "C:/local/inout/";

	/** 出力Excelファイル名 */
	private static final String SCR0005_FILE_NAME = "○○組";

	/** ファイル拡張子  */
	private static final String FILE_EXTENSION = ".xlsx";

	/** 連結用下線  */
	private static final String UNDER_BAR = "_";

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {


		StaticsPOILogic poiLogic = new StaticsPOILogic();

		// テンプレートファイル
		String input = TEMPLATE_FILE_ROOT + SCR0005_TEMPLATE_FILE_NAME + FILE_EXTENSION;

		// 出力ファイル
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String otput = SCR0005_FILE_ROOT + SCR0005_FILE_NAME + UNDER_BAR + sdf.format(new Date()) +  FILE_EXTENSION;

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

		// データ取得
		Scr0005Dto dto = getReportData();

		// データ生成
		List<ReportData> dataList = new ArrayList<ReportData>();
		dataList.add(setData(dto));

		// create controller
		XSSFWorkbook newWorkBook = poiLogic.create(wb, dataList);

		// データを設定してExcelファイルの出力
		poiLogic.write(newWorkBook, otput);
	}

	private static Scr0005Dto getReportData() {

		// 基本はDBから取得する。DBにつなげるまでは直接定義 TODO
		Scr0005Dto retDto = new Scr0005Dto();

		// ヘッダ部
		retDto.organizationName = "金剛組";
		retDto.orgCode = "00023";
		retDto.officeLocateAdd = "埼玉県さいたま区";
		retDto.jurisdictionName = "さいたま署";

		//  明細部分の取得
		List<Scr0005Dto> items = new ArrayList<Scr0005Dto>();

		// 一行目
		Scr0005Dto item01 = new Scr0005Dto();
		item01.orgPosition1Name = "ああああ（ああああ）";
		item01.orgPosition2Name = "いあああ（あいああ）";
		item01.orgPosition3Name = "うあああ（あうああ）";
		item01.orgPosition4Name = "えあああ（あえああ）";
		item01.orgPosition5Name = "おあああ（あおああ）";
		item01.executive = "○";
		item01.personalNumber = "90-99-99990";
		item01.name = "ああああああああ金剛";
		item01.ruby = "アアアアアアアアコンゴウ";
		item01.birthday = "昭和63年12月31日";
		item01.responsibility = "埼玉県";
		item01.materialPositive = "○";
		item01.materialNegative = "○";
		item01.crossFinger = "";
		item01.tattoo = "";
		item01.socialPosition = "";
		item01.domicile   = "埼玉県さいたま市浦和区高砂3-15-1";
		item01.address    = "埼玉県さいたま市浦和区高砂4-25-1";
		item01.residence  = "埼玉県さいたま市浦和区高砂2-4-11";
		item01.offense  = "道路交通法違反";
		item01.fixedDate = "平成26年12月31日";
		item01.disposalResult = "処分ああああああああ結果";
		item01.validityPeriod = "平成27年2月25日";
		item01.arrest = 99;
		item01.instruction = 100;
		item01.recurrence = 200;
		item01.remarks = "鬼嫁";

		// 二行目
		Scr0005Dto item02 = new Scr0005Dto();
		item02.orgPosition1Name = "あいああ（あああい）";
		item02.orgPosition2Name = "いいああ（あいあい）";
		item02.orgPosition3Name = "ういああ（あうあい）";
		item02.orgPosition4Name = "えいああ（あえあい）";
		item02.orgPosition5Name = "おいああ（あおあい）";
		item02.executive = "";
		item02.personalNumber = "90-99-99991";
		item02.name = "あいあああああい比叡";
		item02.ruby = "アイアアアアアイヒエイ";
		item02.birthday = "平成12年12月31日";
		item02.responsibility = "埼玉県";
		item02.materialPositive = "○";
		item02.materialNegative = "○";
		item02.crossFinger = "○";
		item02.tattoo = "○";
		item02.socialPosition = "";
		item02.domicile   = "埼玉県さいたま市浦和区高砂3-15-2";
		item02.address    = "埼玉県さいたま市浦和区高砂4-25-2";
		item02.residence  = "埼玉県さいたま市浦和区高砂2-4-12";
		item02.offense  = "殺人未遂";
		item02.fixedDate = "平成26年12月31日";
		item02.disposalResult = "処分あいあああああい結果";
		item02.validityPeriod = "平成27年2月25日";
		item02.arrest = 120;
		item02.instruction = 220;
		item02.recurrence = 320;
		item02.remarks = "鬼神";

		// 三行目
		Scr0005Dto item03 = new Scr0005Dto();
		item03.orgPosition1Name = "あうああ（あああう）";
		item03.orgPosition2Name = "いうああ（あいあう）";
		item03.orgPosition3Name = "ううああ（あうあう）";
		item03.orgPosition4Name = "えうああ（あえあう）";
		item03.orgPosition5Name = "おうああ（あおあう）";
		item03.executive = "○";
		item03.personalNumber = "90-99-99992";
		item03.name = "あうあああああう榛名";
		item03.ruby = "アウアアアアアウハルナ";
		item03.birthday = "平成24年12月31日";
		item03.responsibility = "埼玉県";
		item03.materialPositive = "";
		item03.materialNegative = "";
		item03.crossFinger = "";
		item03.tattoo = "";
		item03.socialPosition = "";
		item03.domicile   = "埼玉県さいたま市浦和区高砂3-15-3";
		item03.address    = "埼玉県さいたま市浦和区高砂4-25-3";
		item03.residence  = "埼玉県さいたま市浦和区高砂2-4-13";
		item03.offense  = "薬物取り扱い法違反";
		item03.fixedDate = "平成26年12月31日";
		item03.disposalResult = "処分あうあああああう結果";
		item03.validityPeriod = "平成27年2月25日";
		item03.arrest = 1;
		item03.instruction = 2;
		item03.recurrence = 3;
		item03.remarks = "鬼才";

		// 四行目
		Scr0005Dto item04 = new Scr0005Dto();
		item04.orgPosition1Name = "あえああ（あああえ）";
		item04.orgPosition2Name = "いえああ（あいあえ）";
		item04.orgPosition3Name = "うえああ（あうあえ）";
		item04.orgPosition4Name = "ええああ（あえあえ）";
		item04.orgPosition5Name = "おえああ（あおあえ）";
		item04.executive = "○";
		item04.personalNumber = "90-99-99990";
		item04.name = "あえあああああえ霧島";
		item04.ruby = "アエアアアエクロキリシマ";
		item04.birthday = "平成36年12月31日";
		item04.responsibility = "埼玉県";
		item04.materialPositive = "○";
		item04.materialNegative = "○";
		item04.crossFinger = "○";
		item04.tattoo = "○";
		item04.socialPosition = "○";
		item04.domicile   = "埼玉県さいたま市浦和区高砂3-15-4";
		item04.address    = "埼玉県さいたま市浦和区高砂4-25-4";
		item04.residence  = "埼玉県さいたま市浦和区高砂2-4-14";
		item04.offense  = "銃刀法違反";
		item04.fixedDate = "平成26年12月31日";
		item04.disposalResult = "処分あえあああああえ結果";
		item04.validityPeriod = "平成27年2月25日";
		item04.arrest = 999;
		item04.instruction = 999;
		item04.recurrence = 999;
		item04.remarks = "鬼怒";

		items.add(item01);
		items.add(item02);
		items.add(item03);
		items.add(item04);

		retDto.scr0005Dtoitems = items;

		return retDto;

	}

	/**
	 *
	 * @return  出力データ設定
	 */
	private static ReportData setData(Scr0005Dto reportItem) {

		ReportData dataContainer = new ReportData();

		HeaderData header = new HeaderData();
		header.setReportName("OrganizationList");
		Map<String, String> dataMap = header.getDataMap();

		// ヘッダー部編集
		dataMap.put("$ORGANIZATION_NAME", reportItem.organizationName);                  /* 団体名                    */
		dataMap.put("$ORG_CODE", reportItem.orgCode);                                    /* 団体番号                  */
		dataMap.put("$OFFICE_LOCATE_ADD", reportItem.officeLocateAdd);                   /* 事務所所在地              */
		dataMap.put("$JURISDICTION_NAME", reportItem.jurisdictionName);                  /* 所轄                      */
		dataContainer.setHeader(header);

		DetailsData details = new DetailsData();
		Map<String, Object[]> dataListMap = details.getDataListMap();

		// 明細行（縦方向にデータを設定するように初期化）
		List<Scr0005Dto>  itemList = reportItem.scr0005Dtoitems;

		Integer[] numArray             = new Integer[itemList.size()];                /* 番号                         */
		String[] orgPosition1NameArray = new String[itemList.size()];                 /* 所属団体の地位・肩書_一次    */
		String[] orgPosition2NameArray = new String[itemList.size()];                 /* 所属団体の地位・肩書_二次    */
		String[] orgPosition3NameArray = new String[itemList.size()];                 /* 所属団体の地位・肩書_二次    */
		String[] orgPosition4NameArray = new String[itemList.size()];                 /* 所属団体の地位・肩書_四次    */
		String[] orgPosition5NameArray = new String[itemList.size()];                 /* 所属団体の地位・肩書_五次    */
		String[] executiveArray        = new String[itemList.size()];                 /* 法上幹部                     */
		String[] personalNumberArray   = new String[itemList.size()];                 /* 個人番号                     */
		String[] nameArray             = new String[itemList.size()];                 /* 氏名                         */
		String[] rubyArray             = new String[itemList.size()];                 /* フリガナ                     */
		String[] birthdayArray         = new String[itemList.size()];                 /* 生年月日(和暦)               */
		String[] responsibilityArray   = new String[itemList.size()];                 /* 登録責任                     */
		String[] materialPositiveArray = new String[itemList.size()];                 /* 資料肯定                     */
		String[] materialNegativeArray = new String[itemList.size()];                 /* 資料否定                     */
		String[] crossFingerArray      = new String[itemList.size()];                 /* 断指有無                     */
		String[] tattooArray           = new String[itemList.size()];                 /* 入墨有無                     */
		String[] socialPositionArray   = new String[itemList.size()];                 /* 身上有無                     */
		String[] domicileArray         = new String[itemList.size()];                 /* 本籍                         */
		String[] addressArray          = new String[itemList.size()];                 /* 住所                         */
		String[] residenceArray        = new String[itemList.size()];                 /* 住居                         */
		String[] offenseArray          = new String[itemList.size()];                 /* 犯罪保有状況・罪名           */
		String[] fixedDateArray        = new String[itemList.size()];                 /* 犯罪保有状況・確定日(和暦)   */
		String[] disposalResultArray   = new String[itemList.size()];                 /* 犯罪保有状況・処分結果       */
		String[] validityPeriodArray   = new String[itemList.size()];                 /* 犯罪保有状況・有効期間(和暦) */
		Integer[] arrestArray          = new Integer[itemList.size()];                /* 威力事件・検拳               */
		Integer[] instructionArray     = new Integer[itemList.size()];                /* 威力事件・命令               */
		Integer[] recurrenceArray      = new Integer[itemList.size()];                /* 威力事件・再発               */
		String[] remarksArray          = new String[itemList.size()];                 /* 威力事件・備考               */

		// 縦方向にデータ加工し配列へ設定
		for(int index = 0 ; index < itemList.size() ; index++){
			numArray[index] = index + 1;
			orgPosition1NameArray[index] = itemList.get(index).orgPosition1Name;
			orgPosition2NameArray[index] = itemList.get(index).orgPosition2Name;
			orgPosition3NameArray[index] = itemList.get(index).orgPosition3Name;
			orgPosition4NameArray[index] = itemList.get(index).orgPosition4Name;
			orgPosition5NameArray[index] = itemList.get(index).orgPosition5Name;
			executiveArray[index]        = itemList.get(index).executive;
			personalNumberArray[index]   = itemList.get(index).personalNumber;
			nameArray[index]             = itemList.get(index).name;
			rubyArray[index]             = itemList.get(index).ruby;
			birthdayArray[index]         = itemList.get(index).birthday;
			responsibilityArray[index]   = itemList.get(index).responsibility;
			materialPositiveArray[index] = itemList.get(index).materialPositive;
			materialNegativeArray[index] = itemList.get(index).materialNegative;
			crossFingerArray[index]      = itemList.get(index).crossFinger;
			tattooArray[index]           = itemList.get(index).tattoo;
			socialPositionArray[index]   = itemList.get(index).socialPosition;
			domicileArray[index]         = itemList.get(index).domicile;
			addressArray[index]          = itemList.get(index).address;
			residenceArray[index]        = itemList.get(index).residence;
			offenseArray[index]          = itemList.get(index).offense;
			fixedDateArray[index]        = itemList.get(index).fixedDate;
			disposalResultArray[index]   = itemList.get(index).disposalResult;
			validityPeriodArray[index]   = itemList.get(index).validityPeriod;
			arrestArray[index]           = itemList.get(index).arrest;
			instructionArray[index]      = itemList.get(index).instruction;
			recurrenceArray[index]       = itemList.get(index).recurrence;
			remarksArray[index]          = itemList.get(index).remarks;
		}

		// データマッピング（明細行）
		dataListMap.put("$NUMBER[]", numArray);
		dataListMap.put("$ORG_POSITION1_NAME[]", orgPosition1NameArray);
		dataListMap.put("$ORG_POSITION2_NAME[]", orgPosition2NameArray);
		dataListMap.put("$ORG_POSITION3_NAME[]", orgPosition3NameArray);
		dataListMap.put("$ORG_POSITION4_NAME[]", orgPosition4NameArray);
		dataListMap.put("$ORG_POSITION5_NAME[]", orgPosition5NameArray);
		dataListMap.put("$EXECUTIVE[]",  executiveArray);
		dataListMap.put("$PERSONAL_NUMBER[]",  personalNumberArray);
		dataListMap.put("$NAME[]",  nameArray);
		dataListMap.put("$RUBY[]",  rubyArray);
		dataListMap.put("$BIRTHDAY[]",  birthdayArray);
		dataListMap.put("$RESPONSIBILITY[]", responsibilityArray);
		dataListMap.put("$MATERIAL_POSITIVE[]",  materialPositiveArray);
		dataListMap.put("$MATERIAL_NEGATIVE[]",  materialNegativeArray);
		dataListMap.put("$CROSS_FINGER[]",       crossFingerArray);
		dataListMap.put("$TATTOO[]",             tattooArray);
		dataListMap.put("$SOCIAL_POSITION[]",    socialPositionArray);
		dataListMap.put("$DOMICILE[]", domicileArray);
		dataListMap.put("$ADDRESS[]", addressArray);
		dataListMap.put("$RESIDENCE[]", residenceArray);
		dataListMap.put("$OFFENSE[]", offenseArray);
		dataListMap.put("$FIXED_DATE[]", fixedDateArray);
		dataListMap.put("$DISPOSAL_RESULT[]", disposalResultArray);
		dataListMap.put("$VALIDITY_PERIOD[]", validityPeriodArray);
		dataListMap.put("$ARREST[]",  arrestArray);
		dataListMap.put("$INSTRUCTION[]",  instructionArray);
		dataListMap.put("$RECURRENCE[]",  recurrenceArray);
		dataListMap.put("$REMARKS[]",  remarksArray);

// TODO
//		dataListMap.put("$NUMBER[]", new Integer[] { 1, 2, 3, 4 });
//		dataListMap.put("$ORG_POSITION1_NAME[]",  new String[] { "ああああ（ああああ）", "あああい（ああああ）",
//														"あああう（ああああ）", "あああえ（ああああ）" });
//		dataListMap.put("$ORG_POSITION2_NAME[]",  new String[] { "いあああ（ああああ）", "いああい（ああああ）",
//														"いああう（ああああ）", "いああえ（ああああ）" });
//		dataListMap.put("$ORG_POSITION3_NAME[]",  new String[] { "うあああ（ああああ）", "うああい（ああああ）",
//														"うああう（ああああ）", "うああえ（ああああ）" });
//		dataListMap.put("$ORG_POSITION4_NAME[]",  new String[] { "えあああ（ああああ）", "えああい（ああああ）",
//														"えああう（ああああ）", "えああえ（ああああ）" });
//		dataListMap.put("$ORG_POSITION5_NAME[]",  new String[] { "おあああ（ああああ）", "おああい（ああああ）",
//														"おああう（ああああ）", "おああえ（ああああ）" });
//		dataListMap.put("$EXECUTIVE[]",  new String[] { "○", "","", "" });
//		dataListMap.put("$PERSONAL_NUMBER[]",  new String[] { "99-99-99990", "99-99-99991","99-99-99992", "99-99-99993" });
//		dataListMap.put("$NAME[]",  new String[] { "ああああああああああ金剛", "ああああああああああ比叡",
//														"ああああああああああ榛名", "ああああああああああ霧島" });
//		dataListMap.put("$RUBY[]",  new String[] { "アアアアアアアアアアアア", "イアアアアアアアアアアア",
//														"ウアアアアアアアアアアア", "エアアアアアアアアアアア" });
//		dataListMap.put("$BIRTHDAY[]",  new String[] { "2000/12/31", "2005/12/31","2010/12/31", "2015/12/31" });
//		dataListMap.put("$RESPONSIBILITY[]",  new String[] { "埼玉県", "埼玉県","埼玉県", "埼玉県" });
//		dataListMap.put("$MATERIAL_POSITIVE[]",  new String[] { "○", "○","○", "○" });
//		dataListMap.put("$MATERIAL_NEGATIVE[]",  new String[] { "○", "○","○", "○" });
//		dataListMap.put("$CROSS_FINGER[]",       new String[] { "○", "○","○", "○" });
//		dataListMap.put("$TATTOO[]",             new String[] { "○", "○","○", "○" });
//		dataListMap.put("$SOCIAL_POSITION[]",    new String[] { "○", "○","○", "○" });
//		dataListMap.put("$DOMICILE[]", new String[] { "埼玉県さいたま市浦和区高砂3-15-1",
//													"埼玉県さいたま市浦和区高砂3-15-2",
//													"埼玉県さいたま市浦和区高砂3-15-3",
//													"埼玉県さいたま市浦和区高砂3-15-4" });
//		dataListMap.put("$ADDRESS[]", new String[] { "埼玉県さいたま市浦和区高砂3-25-1",
//													"埼玉県さいたま市浦和区高砂3-25-2",
//													"埼玉県さいたま市浦和区高砂3-25-3",
//													"埼玉県さいたま市浦和区高砂3-25-4" });
//		dataListMap.put("$RESIDENCE[]", new String[] { "埼玉県さいたま市浦和区高砂3-35-1",
//														"埼玉県さいたま市浦和区高砂3-35-2",
//														"埼玉県さいたま市浦和区高砂3-35-3",
//														"埼玉県さいたま市浦和区高砂3-35-4" });
//		dataListMap.put("$OFFENSE[]", new String[] { "道路交通法違反", "恫喝","薬物取り扱い法違反", "銃刀法違反" });
//		dataListMap.put("$FIXED_DATE[]",  new String[] { "2000/12/31", "2005/12/31","2010/12/31", "2015/12/31" });
//		dataListMap.put("$DISPOSAL_RESULT[]", new String[] { "ああああああああああああああああああああああああああああ",
//				"いあああああああああああああああああああああああああああ",
//				"うあああああああああああああああああああああああああああ",
//				"えあああああああああああああああああああああああああああ" });
//
//		dataListMap.put("$VALIDITY_PERIOD[]",  new String[] { "2000/12/31", "2005/12/31","2010/12/31", "2015/12/31" });
//		dataListMap.put("$ARREST[]",  new Integer[] { 900, 800,700,999 });
//		dataListMap.put("$INSTRUCTION[]",  new Integer[] { 600, 500,400,99 });
//		dataListMap.put("$RECURRENCE[]",  new Integer[] { 30, 20,10,0 });
//
//		dataListMap.put("$REMARKS[]",  new String[] { "鬼嫁", "鬼神","鬼才", "鬼怒" });
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
// TODO

		details.setNumOfDetails(dataListMap.get("$NUMBER[]").length);

		dataContainer.setDetails(details);

		return dataContainer;
	}
}
