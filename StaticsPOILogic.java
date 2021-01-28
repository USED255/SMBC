package jp.co.core.ddm.pack.sbti.logic;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jp.co.core.ddm.pack.sbti.framework.exception.DevelopRuntimeException;
import jp.co.core.ddm.pack.sbti.poi.data.ReportData;


/**
 * POI利用サブLogicクラス
 *
 * @author TOZAWA
 */
@SuppressWarnings( "deprecation" )
public class StaticsPOILogic implements Serializable {


    /** 暴力団組織分布図凡例・判断文字列 */
    protected static final String EXPLANATORY_NOTE = "ExplanatoryNote";

    /** 県内団体組織系統表・判断文字列 */
    protected static final String PREFECTURE_ORGANIZATION_SYSTEM = "prefectureOrganizationSystem";

    /** データなし判断文字列 */
    protected static final String NO_DATA = "NoData";

    /** 色なし判断文字列 */
    protected static final String NO_COLAR = "NoColar";

    /** 判断文字列 */
    protected static final String NO_FILL = "NoFill";

    /** メッセージ */
    private static final String POI_ERR_MSG = "予期せぬエラーが発生いたしました。";

    /** 印刷ヘッダ領域リスト */
    private List<CellRangeAddress> cellRangeRowList = new ArrayList<CellRangeAddress>();


    /** 印刷ヘッダ領域リスト */
    private List<CellRangeAddress> cellRangeColList = new ArrayList<CellRangeAddress>();


    /**
     * Excelファイルの取得
     *
     * @param fileName Excel2007形式以降のExcelファイル
     * @return Excel2007形式以降のwbオブジェクトデータ
     * @throws Exception
     */
    public XSSFWorkbook createWorkbook( String fileName ) {

        InputStream inp;
        XSSFWorkbook wb = null;
        try {
            inp = new FileInputStream( fileName );
            wb = (XSSFWorkbook) WorkbookFactory.create( inp );
            inp.close();
        } catch ( Exception e ) {
            DevelopRuntimeException err = new DevelopRuntimeException( POI_ERR_MSG );
            throw err;

        }
        return wb;
    }


    /**
     * 拡張子（Excel2007形式以降か）確認
     *
     * @param inputFile Excel2007形式以降のExcelファイル名
     * @return true/false
     */
    public boolean isExcelFile( String inputFile ) {

        if ( inputFile != null ) {
            if ( inputFile.endsWith( "xlsx" ) || inputFile.endsWith( "xlsm" ) ) {
                return true;
            }
        }
        return false;
    }


    /**
     * Excelファイルが存在するか確認
     *
     * @param inputFile Excel2007形式以降のExcelファイル
     * @return true/false
     */
    public boolean isExistExcelFile( String inputFile ) {

        if ( isExcelFile( inputFile ) ) {
            File file = new File( inputFile );
            if ( file.exists() && file.isFile() ) {
                return true;
            }
        }
        return false;
    }


    /**
     * Excelファイルの出力
     *
     * @param wb 編集済みワークブックオブジェクト
     * @param outFile 出力ファイル名(Excel2007形式)
     * @param bindingList セル連結指定アドレスリスト
     */
    public void write( XSSFWorkbook wb, String outFile, List<Integer[]> bindingList ) {

        // 計算式を再計算する。
        wb.getCreationHelper().createFormulaEvaluator().evaluateAll();

        if ( bindingList != null ) {
            XSSFSheet sheet0 = wb.getSheetAt( 0 );
            for ( int index = 0; index < bindingList.size(); index++ ) {
                cellBind( sheet0, bindingList.get( index ) );
            }
        }

        try {
            Path dstPath = Paths.get( outFile );
            Path parentPath = dstPath.getParent();
            // ディレクトリの存在チェックをし、なければ作成
            if ( ( parentPath != null ) && ( !new File( parentPath.toString() ).exists() ) ) {
                Files.createDirectories( parentPath );
            }
            try ( OutputStream out = new FileOutputStream( outFile ) ) {
                // 書き込み
                wb.write( out );
            }
        } catch ( Exception e ) {
            DevelopRuntimeException err = new DevelopRuntimeException( POI_ERR_MSG );
            throw err;
        }

    }


    /**
     * Excelファイルの出力
     *
     * @param wb 編集済みワークブックオブジェクト
     * @param outFile 出力ファイル名(Excel2007形式)
     * @param bindingList セル連結指定アドレスリスト
     */
    public void writeIo028( XSSFWorkbook wb, String outFile, List<Integer[]> bindingList ) {

        if ( bindingList != null ) {
            XSSFSheet sheet0 = wb.getSheetAt( 0 );
            for ( int index = 0; index < bindingList.size(); index++ ) {
                cellBind( sheet0, bindingList.get( index ) );
            }
        }

        OutputStream out = null;
        try {
            out = new FileOutputStream( outFile );
            wb.write( out );
        } catch ( Exception e ) {
            DevelopRuntimeException err = new DevelopRuntimeException( POI_ERR_MSG );
            throw err;
        } finally {
            try {
                if ( out != null ) {
                    out.close();
                }

            } catch ( IOException e ) {
                out = null;
                DevelopRuntimeException err = new DevelopRuntimeException( POI_ERR_MSG );
                throw err;
            }
        }

    }


    /**
     * セルの結合処理
     *
     * @param sheet セルオブジェクト
     * @param integers 結合領域の配列
     */
    private void cellBind( XSSFSheet sheet, Integer[] integers ) {

        for ( int index = integers[0]; index < integers[1]; index++ ) {
            sheet.addMergedRegion( new CellRangeAddress( index, index, integers[2], integers[3] ) );
            if ( integers.length > 5 ) {
                sheet.addMergedRegion( new CellRangeAddress( index, index, integers[4], integers[5] ) );
            }
        }
    }


    /**
     * 増減ワークブックの生成
     *
     * @param wb ワークブックオブジェクト
     * @param dataList 帳票データ
     * @return XSSFWorkbook
     */
    public XSSFWorkbook createIncDec( XSSFWorkbook wb, List<ReportData> dataList ) {

        // 変数初期化
        int numOfDetails = 0;
        ReportData reportData = null;
        Map<String, String> headerMap = null;
        Map<String, Object[]> detailsMap = null;

        initialize( wb, dataList );

        // ワークシート単位の繰返し処理
        for ( int sheetIndex = 0; sheetIndex < dataList.size(); sheetIndex++ ) {
            XSSFSheet sheet = wb.getSheetAt( sheetIndex );
            // ワークシートに対応するデータを取得
            reportData = dataList.get( sheetIndex );
            headerMap = reportData.getHeader().getDataMap();
            detailsMap = reportData.getDetails().getDataListMap();
            if ( reportData.getDetails().getNumOfDetails() != 0 ) {
                numOfDetails = reportData.getDetails().getNumOfDetails();
            }
            // 行単位の繰返し処理
            int lastRow = sheet.getLastRowNum();
            for ( int rowIndex = 0; rowIndex <= lastRow; rowIndex++ ) {
                XSSFRow row = sheet.getRow( rowIndex );
                if ( row == null ) {
                    continue;
                }
                // セル単位の繰返し処理
                int lastColumn = row.getLastCellNum();
                for ( int columnIndex = 0; columnIndex < lastColumn; columnIndex++ ) {
                    XSSFCell cell = row.getCell( columnIndex );
                    if ( cell == null ) {
                        continue;
                    }
                    if ( cell.getCellType() == XSSFCell.CELL_TYPE_STRING ) {
                        // 値を置換える為に準備したセルであればデータを設定
                        String key = cell.getStringCellValue();
                        if ( headerMap.containsKey( key ) ) {
                            setCellValue( cell, headerMap.get( key ) );
                        } else if ( detailsMap.containsKey( key ) ) {
                            setCellValues( cell, sheet, detailsMap.get( key ), numOfDetails );
                        }
                    }
                }
            }
        }
        // 完成したワークブックオブジェクトを戻す
        return wb;
    }


    /**
     * ワークブックの生成
     *
     * @param wb ワークブックオブジェクト
     * @param dataList 帳票データ
     * @return XSSFWorkbook
     */
    public XSSFWorkbook create( XSSFWorkbook wb, List<ReportData> dataList ) {

        // 変数初期化
        int numOfDetails = 0;
        ReportData reportData = null;
        Map<String, String> headerMap = null;
        Map<String, Object[]> detailsMap = null;
        // 印刷範囲初期化
        initialize( wb, dataList );


        // ワークシート単位の繰返し処理
        int numOfSheet = wb.getNumberOfSheets();
        for ( int sheetIndex = 0; sheetIndex < numOfSheet; sheetIndex++ ) {
            XSSFSheet sheet = wb.getSheetAt( sheetIndex );


            // ワークシートに対応するデータを取得
            reportData = dataList.get( sheetIndex );
            headerMap = reportData.getHeader().getDataMap();
            detailsMap = reportData.getDetails().getDataListMap();
            if ( reportData.getDetails().getNumOfDetails() != 0 ) {
                numOfDetails = reportData.getDetails().getNumOfDetails();
            }
            // 行単位の繰返し処理
            int lastRow = sheet.getLastRowNum();
            for ( int rowIndex = 0; rowIndex <= lastRow; rowIndex++ ) {
                XSSFRow row = sheet.getRow( rowIndex );
                if ( row == null ) {
                    continue;
                }
                // セル単位の繰返し処理
                int lastColumn = row.getLastCellNum();
                for ( int columnIndex = 0; columnIndex < lastColumn; columnIndex++ ) {
                    XSSFCell cell = row.getCell( columnIndex );
                    if ( cell == null ) {
                        continue;
                    }
                    if ( cell.getCellType() == XSSFCell.CELL_TYPE_STRING ) {
                        // 値を置換える為に準備したセルであればデータを設定
                        String key = cell.getStringCellValue();
                        if ( headerMap.containsKey( key ) ) {
                            setCellValue( cell, headerMap.get( key ) );
                        } else if ( detailsMap.containsKey( key ) ) {
                            setCellValues( cell, sheet, detailsMap.get( key ), numOfDetails );
                        }
                    }
                }
            }
        }
        return wb;
    }


    /**
     * ワークブックの生成
     *
     * @param wb ワークブックオブジェクト
     * @param dataList 帳票データ
     * @return XSSFWorkbook
     */
    public XSSFWorkbook createIo028( XSSFWorkbook wb, List<ReportData> dataList ) {

        // 変数初期化
        int numOfDetails = 0;
        ReportData reportData = null;
        Map<String, String> headerMap = null;
        Map<String, Object[]> detailsMap = null;
        // 印刷範囲初期化
        initialize( wb, dataList );
        // ワークシート単位の繰返し処理
        int numOfSheet = wb.getNumberOfSheets();
        for ( int sheetIndex = 0; sheetIndex < numOfSheet; sheetIndex++ ) {
            XSSFSheet sheet = wb.getSheetAt( sheetIndex );
            // ワークシートに対応するデータを取得
            reportData = dataList.get( sheetIndex );
            headerMap = reportData.getHeader().getDataMap();
            detailsMap = reportData.getDetails().getDataListMap();
            if ( reportData.getDetails().getNumOfDetails() != 0 ) {
                numOfDetails = reportData.getDetails().getNumOfDetails();
            }
            // 行単位の繰返し処理
            int lastRow = sheet.getLastRowNum();
            for ( int rowIndex = 0; rowIndex <= lastRow; rowIndex++ ) {
                XSSFRow row = sheet.getRow( rowIndex );
                if ( row == null ) {
                    continue;
                }
                // セル単位の繰返し処理
                int lastColumn = row.getLastCellNum();
                for ( int columnIndex = 0; columnIndex < lastColumn; columnIndex++ ) {
                    XSSFCell cell = row.getCell( columnIndex );
                    if ( cell == null ) {
                        continue;
                    }
                    if ( cell.getCellType() == XSSFCell.CELL_TYPE_STRING ) {
                        // 値を置換える為に準備したセルであればデータを設定
                        String key = cell.getStringCellValue();
                        if ( headerMap.containsKey( key ) ) {
                            setCellValue( cell, headerMap.get( key ) );
                        } else if ( detailsMap.containsKey( key ) ) {
                            setCellValuesIo028( cell, sheet, detailsMap.get( key ), numOfDetails, wb );
                        }
                    }
                }
            }
        }
        // 完成したワークブックオブジェクトを戻す
        return wb;
    }


    /**
     * 余計なセルの削除
     *
     * @param wb ワークブックオブジェクト
     * @return XSSFWorkbook
     */
    public XSSFWorkbook deleteCellIo028( XSSFWorkbook wb ) {

        XSSFSheet sheet = wb.getSheetAt( 0 );
        XSSFRow fromRow = sheet.getRow( 5 );
        XSSFCell fromCell = fromRow.getCell( 5 );

        for ( int i = 2; i < 4; i++ ) {
            XSSFRow toRow = sheet.getRow( i );
            for ( int j = 5; j < 9; j++ ) {
                XSSFCell toCell = toRow.getCell( j );
                toCell.setCellValue( "" );
                copyCell( fromCell, toCell );
            }
        }
        // 完成したワークブックオブジェクトを戻す
        return wb;
    }


    /**
     * 印刷範囲初期化
     *
     * @param wb ワークブックオブジェクト
     * @param dataList 帳票データ
     */
    private void initialize( XSSFWorkbook wb, List<ReportData> dataList ) {

        // 印刷範囲を取得
        int numOfSheet = wb.getNumberOfSheets();
        for ( int sheetIndex = 0; sheetIndex < numOfSheet; sheetIndex++ ) {
            XSSFSheet sheet = wb.getSheetAt( sheetIndex );
            // 値を退避
            cellRangeRowList.add( sheet.getRepeatingRows() );
            cellRangeColList.add( sheet.getRepeatingColumns() );
        }
    }


    /**
     * ワークブックの生成
     *
     * @param wb ワークブックオブジェクト
     * @param cellStyleFlg
     * @param dataList 帳票データ
     * @return XSSFWorkbook
     */
    public XSSFWorkbook createConditonSetting( XSSFWorkbook wb, List<ReportData> dataList ) {

        // 印刷範囲初期化
        ReportData reportData = null;
        Map<String, String> headerMap = null;
        Map<String, Object[]> detailsMap = null;
        // 印刷範囲初期化
        initialize( wb, dataList );
        // ワークシート単位の繰返し処理
        int numOfSheet = wb.getNumberOfSheets();
        for ( int sheetIndex = 0; sheetIndex < numOfSheet; sheetIndex++ ) {
            XSSFSheet sheet = wb.getSheetAt( sheetIndex );
            // ワークシートに対応するデータを取得
            reportData = dataList.get( sheetIndex );
            headerMap = reportData.getHeader().getDataMap();
            detailsMap = reportData.getDetails().getDataListMap();
            // 行単位の繰返し処理
            int lastRow = sheet.getLastRowNum();
            for ( int rowIndex = 0; rowIndex <= lastRow; rowIndex++ ) {
                XSSFRow row = sheet.getRow( rowIndex );
                if ( row == null ) {
                    continue;
                }
                // セル単位の繰返し処理
                int lastColumn = row.getLastCellNum();
                for ( int columnIndex = 0; columnIndex < lastColumn; columnIndex++ ) {
                    XSSFCell cell = row.getCell( columnIndex );
                    if ( cell == null ) {
                        continue;
                    }
                    // セルの型が文字列型の場合
                    if ( cell.getCellType() == XSSFCell.CELL_TYPE_STRING ) {
                        // 値を置換える為に準備したセルであればデータを設定
                        String key = cell.getStringCellValue();
                        if ( headerMap.containsKey( key ) ) {
                            setCellValue( cell, headerMap.get( key ) );
                        } else if ( detailsMap.containsKey( key ) ) {
                            setCellValuesWithSetStyle( wb, cell, sheet, detailsMap.get( key ) );

                        }
                    }
                }
            }
        }
        // 完成したワークブックオブジェクトを戻す
        return wb;
    }


    /**
     * ワークブックの生成・セルのスタイル加工処理あり
     *
     * @param wb ワークブックオブジェクト
     * @param dataList 帳票データ
     * @return XSSFWorkbook
     */
    public XSSFWorkbook createWithChangeCellStyle( XSSFWorkbook wb, List<ReportData> dataList ) {

        // 印刷範囲初期化
        ReportData reportData = null;
        Map<String, String> headerMap = null;
        Map<String, Object[]> detailsMap = null;
        // 印刷範囲初期化
        initialize( wb, dataList );
        // ワークシート単位の繰返し処理
        int numOfSheet = wb.getNumberOfSheets();
        for ( int sheetIndex = 0; sheetIndex < numOfSheet; sheetIndex++ ) {
            XSSFSheet sheet = wb.getSheetAt( sheetIndex );
            // ワークシートに対応するデータを取得
            reportData = dataList.get( sheetIndex );
            headerMap = reportData.getHeader().getDataMap();
            detailsMap = reportData.getDetails().getDataListMap();
            // 行単位の繰返し処理
            int lastRow = sheet.getLastRowNum();
            for ( int rowIndex = 0; rowIndex <= lastRow; rowIndex++ ) {
                XSSFRow row = sheet.getRow( rowIndex );
                if ( row == null ) {
                    continue;
                }
                // セル単位の繰返し処理
                int lastColumn = row.getLastCellNum();
                for ( int columnIndex = 0; columnIndex < lastColumn; columnIndex++ ) {
                    XSSFCell cell = row.getCell( columnIndex );
                    if ( cell == null ) {
                        continue;
                    }
                    // セルの型が文字列型の場合
                    if ( cell.getCellType() == XSSFCell.CELL_TYPE_STRING ) {
                        // 値を置換える為に準備したセルであればデータを設定
                        String key = cell.getStringCellValue();
                        if ( headerMap.containsKey( key ) ) {
                            setCellValue( cell, headerMap.get( key ) );
                        } else if ( detailsMap.containsKey( key ) ) {
                            setCellValuesWithChangeStyle( wb, cell, sheet, detailsMap.get( key ) );
                        }
                    }
                }
            }
        }
        // 完成したワークブックオブジェクトを戻す
        return wb;
    }


    /**
     * セルの設定（スタイルの変更あり）
     *
     * @param wb ワークブックオブジェクト
     * @param baseCell テンプレートセル
     * @param sheet シートオブジェクト
     * @param values 明細部データ
     */
    private void setCellValuesWithChangeStyle( XSSFWorkbook wb, XSSFCell baseCell, XSSFSheet sheet, Object[] values ) {

        // 繰返し処理開始セルの位置情報を保持
        int startRowPosition = baseCell.getRowIndex();
        int columnPosition = baseCell.getColumnIndex();
        int maxIndex = values.length;
        String strOld = new String();

        // 明細の数分繰返し処理をする
        for ( int index = 0; index < maxIndex; index++ ) {
            // 行を取得または生成
            XSSFRow row = sheet.getRow( startRowPosition + index );
            if ( row == null ) {
                row = sheet.createRow( startRowPosition + index );
                // 繰返し処理開始行と同じ高さを設定
                row.setHeight( sheet.getRow( startRowPosition ).getHeight() );
            }
            // セルを取得または生成
            XSSFCell cell = row.getCell( columnPosition );
            if ( cell == null ) {
                cell = row.createCell( columnPosition );
                // 繰返し処理開始セルの情報をコピー
                copyCell( baseCell, cell );
            }

            // 文字列型である場合に値を分割
            String tmp = null;
            String[] splitValue = null;
            if ( values[index] != null ) {
                tmp = (String) values[index];
                splitValue = tmp.split( "_" );
                // 凡例を出力対象にしている場合
                if ( EXPLANATORY_NOTE.equals( splitValue[splitValue.length - 1] ) ) {
                    setCellValueExplanatoryStyle( wb, cell, splitValue[0], strOld, splitValue[1], index, maxIndex );

                    // 県内団体組織系統表を出力する場合
                } else if ( PREFECTURE_ORGANIZATION_SYSTEM.equals( splitValue[splitValue.length - 1] ) ) {
                    setCellValueOrganizationOrganizationStyle( wb, cell, splitValue[0], strOld, splitValue[1], splitValue[2], index, maxIndex );
                } else {
                    switch ( splitValue.length ) {
                        case 1:
                            setCellValueJurisdictionName( wb, cell, splitValue[0], strOld, index, maxIndex );
                            break;
                        case 2:
                            changeCellStyleWithSetValue( wb, cell, splitValue[0], strOld, splitValue[1], "", index, maxIndex );
                            break;
                        case 3:
                            changeCellStyleWithSetValue( wb, cell, splitValue[0], strOld, splitValue[1], splitValue[2], index, maxIndex );
                            break;
                        default:
                            break;
                    }
                }
            }
            // 出力値がnull以外
            if ( values[index] != null ) {
                strOld = splitValue[0];
            } else {
                cell.setCellType( XSSFCell.CELL_TYPE_BLANK );
                cell.setCellStyle( cell.getCellStyle() );
            }
        }
    }


    /**
     * 名前
     *
     * @param wb
     * @param cell
     * @param value
     * @param strOld
     * @param index
     * @param maxIndex
     */
    private void setCellValueJurisdictionName( XSSFWorkbook wb, XSSFCell cell, Object value, String strOld, int index, int maxIndex ) {

        XSSFCellStyle baseStyle = cell.getCellStyle();
        XSSFCellStyle copyStyle = wb.createCellStyle();

        if ( value != null ) {
            // 値の型が文字列
            if ( value instanceof String ) {
                // セルの設定変更
                changeCellStyles( cell, value, strOld, index, maxIndex, baseStyle, copyStyle );
                // 値の型が数値(実質通らない)
            } else if ( value instanceof Number ) {
                Number numValue = (Number) value;
                if ( numValue instanceof Float ) {
                    Float floatValue = (Float) numValue;
                    numValue = new Double( String.valueOf( floatValue ) );
                }
                cell.setCellValue( numValue.doubleValue() );
                // 値の型が日付(実質通らない)
            } else if ( value instanceof Date ) {
                Date dateValue = (Date) value;
                cell.setCellValue( dateValue );
                // 値の型が成否(実質通らない)
            } else if ( value instanceof Boolean ) {
                Boolean boolValue = (Boolean) value;
                cell.setCellValue( boolValue );
            }
        } else {
            // どこにも属さない場合は空白
            cell.setCellType( XSSFCell.CELL_TYPE_BLANK );
            cell.setCellStyle( baseStyle );
        }
    }


    /**
     * セルのスタイル変更
     *
     * @param cell セルオブジェクト
     * @param value 設定値
     * @param strOld 比較元設定値
     * @param index データ数
     * @param maxIndex 最大データ数
     * @param baseStyle 基セルのスタイル
     * @param copyStyle 真セルのスタイル
     */
    private void changeCellStyles( XSSFCell cell, Object value, String strOld, int index, int maxIndex, XSSFCellStyle baseStyle, XSSFCellStyle copyStyle ) {

        // セル設定の取得
        selectCellTypes( cell );

        if ( index >= 0 ) {
            if ( strOld.equals( value ) ) {
                // 色はぬらない
                copyStyle.setFillForegroundColor( new XSSFColor( new java.awt.Color( 0, 0, 0 ) ) );
                copyStyle.setFillPattern( CellStyle.NO_FILL );
                copyStyle.setBorderTop( CellStyle.BORDER_NONE );
                copyStyle.setBorderLeft( CellStyle.BORDER_THIN );
                copyStyle.setBorderRight( CellStyle.BORDER_THIN );
                if ( index >= maxIndex - 1 ) {
                    copyStyle.setBorderBottom( CellStyle.BORDER_THIN );
                } else {
                    copyStyle.setBorderBottom( CellStyle.BORDER_NONE );
                }
                copyStyle.setAlignment( baseStyle.getAlignment() );
                copyStyle.setVerticalAlignment( baseStyle.getVerticalAlignment() );
                cell.setCellStyle( copyStyle );

                if ( index == 0 ) {
                    cell.setCellValue( (String) value );
                } else {
                    cell.setCellValue( "" );
                }

            } else {

                // 色はぬらない
                copyStyle.setFillForegroundColor( new XSSFColor( new java.awt.Color( 0, 0, 0 ) ) );
                copyStyle.setFillPattern( CellStyle.NO_FILL );

                copyStyle.setBorderTop( CellStyle.BORDER_THIN );
                copyStyle.setBorderLeft( CellStyle.BORDER_THIN );
                copyStyle.setBorderRight( CellStyle.BORDER_THIN );

                if ( index >= maxIndex - 1 ) {
                    copyStyle.setBorderBottom( CellStyle.BORDER_THIN );
                } else {
                    copyStyle.setBorderBottom( CellStyle.BORDER_NONE );
                }

                copyStyle.setAlignment( baseStyle.getAlignment() );
                copyStyle.setVerticalAlignment( baseStyle.getVerticalAlignment() );
                copyStyle.setShrinkToFit( true );
                cell.setCellStyle( copyStyle );
                cell.setCellValue( (String) value );
            }
        }
    }


    /**
     * セルデータ設定
     *
     * @param wb ワークブックオブジェクト
     * @param cell セルオブジェクト
     * @param value 出力対象データ
     * @param strOld 比較データ
     * @param collarBgCode 背景色コード
     * @param index データ数
     * @param maxIndex 最大データ数
     */
    private void setCellValueExplanatoryStyle( XSSFWorkbook wb, XSSFCell cell, Object value, String strOld, String collarBgCode, int index, int maxIndex ) {

        HashMap<String, Integer> bgCollerMap = new HashMap<String, Integer>();

        // 色コードをRGBに変換してマップ化
        setPatternRGB( collarBgCode, bgCollerMap );

        XSSFCellStyle baseStyle = cell.getCellStyle();
        XSSFCellStyle copyStyle = wb.createCellStyle();

        if ( value != null ) {
            // 値の型が文字列
            if ( value instanceof String ) {

                // セルの型を選択して、設定を最適化
                selectCellTypes( cell );
                // 色は塗る。
                if ( bgCollerMap.size() == 3 ) {
                    copyStyle.setFillForegroundColor( new XSSFColor( new java.awt.Color( bgCollerMap.get( "R" ), bgCollerMap.get( "G" ), bgCollerMap.get( "B" ) ) ) );
                    copyStyle.setFillPattern( CellStyle.SOLID_FOREGROUND );
                } else {
                    copyStyle.setFillForegroundColor( new XSSFColor( new java.awt.Color( 0, 0, 0 ) ) );
                    copyStyle.setFillPattern( CellStyle.NO_FILL );
                }

                copyStyle.setShrinkToFit( true );
                copyStyle.setBorderLeft( CellStyle.BORDER_THIN );
                copyStyle.setBorderRight( CellStyle.BORDER_THIN );

                if ( index > 0 ) {
                    copyStyle.setBorderTop( CellStyle.BORDER_NONE );
                } else {
                    copyStyle.setBorderTop( CellStyle.BORDER_THIN );
                }
                if ( index >= maxIndex - 1 ) {
                    copyStyle.setBorderBottom( CellStyle.BORDER_THIN );
                } else {
                    copyStyle.setBorderBottom( CellStyle.BORDER_NONE );
                }

                cell.setCellStyle( copyStyle );
                cell.setCellValue( (String) value );


                // 値の型が数値
            } else if ( value instanceof Number ) {
                Number numValue = (Number) value;
                if ( numValue instanceof Float ) {
                    Float floatValue = (Float) numValue;
                    numValue = new Double( String.valueOf( floatValue ) );
                }
                cell.setCellValue( numValue.doubleValue() );
                // 値の型が日付
            } else if ( value instanceof Date ) {
                Date dateValue = (Date) value;
                cell.setCellValue( dateValue );
                // 値の型が成否
            } else if ( value instanceof Boolean ) {
                Boolean boolValue = (Boolean) value;
                cell.setCellValue( boolValue );
            }
        } else {
            // どこにも属さない場合は空白
            cell.setCellType( XSSFCell.CELL_TYPE_BLANK );
            cell.setCellStyle( baseStyle );
        }
    }


    /**
     * セルデータ設定
     *
     * @param wb ワークブックオブジェクト
     * @param cell セルオブジェクト
     * @param value 出力対象データ
     * @param strOld 比較データ
     * @param bgColorCode 背景色コード
     * @param fontColorCode 文字色コード
     * @param index データ数
     * @param maxIndex 最大データ数
     */
    private void setCellValueOrganizationOrganizationStyle( XSSFWorkbook wb, XSSFCell cell, Object value, String strOld, String bgColorCode, String fontColorCode, int index, int maxIndex ) {

        HashMap<String, Integer> bgColorMap = new HashMap<String, Integer>();

        HashMap<String, Integer> fontColorMap = new HashMap<String, Integer>();

        // 背景色
        setPatternRGB( bgColorCode, bgColorMap );
        // フォント色
        setPatternRGB( fontColorCode, fontColorMap );

        XSSFCellStyle baseStyle = cell.getCellStyle();
        XSSFCellStyle copyStyle = wb.createCellStyle();
        XSSFFont font = wb.createFont();

        // フォント色の指定
        if ( fontColorMap.size() == 3 ) {
            font.setColor( new XSSFColor( new java.awt.Color( fontColorMap.get( "R" ), fontColorMap.get( "G" ), fontColorMap.get( "B" ) ) ) );
        } else {
            font.setColor( new XSSFColor( new java.awt.Color( 255, 255, 255 ) ) );
        }
        if ( value != null ) {
            // 値の型が文字列
            if ( value instanceof String ) {
                // セル設定の取得
                selectCellTypes( cell );

                if ( index >= 0 ) {
                    if ( strOld.equals( value ) ) {
                        // 背景色を設定する。
                        setBackGrandColers( bgColorMap, copyStyle );
                        // 線を引く
                        copyStyle.setBorderTop( CellStyle.BORDER_NONE );
                        if ( NO_DATA.equals( value ) ) {
                            copyStyle.setBorderLeft( CellStyle.BORDER_NONE );
                            copyStyle.setBorderRight( CellStyle.BORDER_NONE );
                        } else {
                            copyStyle.setBorderLeft( CellStyle.BORDER_THIN );
                            copyStyle.setBorderRight( CellStyle.BORDER_THIN );
                        }

                        if ( index >= maxIndex - 1 ) {
                            if ( NO_DATA.equals( value ) ) {
                                copyStyle.setBorderBottom( CellStyle.BORDER_NONE );
                            } else {
                                copyStyle.setBorderBottom( CellStyle.BORDER_THIN );
                            }
                        } else {
                            copyStyle.setBorderBottom( CellStyle.BORDER_NONE );
                        }
                        copyStyle.setAlignment( baseStyle.getAlignment() );
                        copyStyle.setVerticalAlignment( baseStyle.getVerticalAlignment() );
                        copyStyle.setFont( font );
                        copyStyle.setShrinkToFit( true );
                        cell.setCellStyle( copyStyle );

                        if ( index == 0 ) {
                            if ( NO_DATA.equals( value ) ) {
                                cell.setCellValue( "" );
                            }
                            // 出力情報を分解して表示する
                            String outValue = (String) value;
                            String[] splitValue = outValue.split( "#" );
                            if ( splitValue.length > 1 ) {
                                cell.setCellValue( splitValue[0] );
                            } else {
                                cell.setCellValue( (String) value );
                            }
                        } else {
                            cell.setCellValue( "" );
                        }

                    } else {
                        // 色を塗る
                        setBackGrandColers( bgColorMap, copyStyle );

                        // 線を引く
                        copyStyle.setBorderTop( CellStyle.BORDER_THIN );

                        if ( NO_DATA.equals( value ) ) {
                            copyStyle.setBorderLeft( CellStyle.BORDER_NONE );
                            copyStyle.setBorderRight( CellStyle.BORDER_NONE );
                        } else {
                            copyStyle.setBorderLeft( CellStyle.BORDER_THIN );
                            copyStyle.setBorderRight( CellStyle.BORDER_THIN );
                        }

                        if ( index >= maxIndex - 1 ) {
                            if ( NO_DATA.equals( value ) ) {
                                copyStyle.setBorderBottom( CellStyle.BORDER_NONE );
                            } else {
                                copyStyle.setBorderBottom( CellStyle.BORDER_THIN );
                            }
                        } else {
                            copyStyle.setBorderBottom( CellStyle.BORDER_NONE );
                        }
                        copyStyle.setAlignment( baseStyle.getAlignment() );
                        copyStyle.setVerticalAlignment( baseStyle.getVerticalAlignment() );
                        copyStyle.setFont( font );
                        copyStyle.setShrinkToFit( true );
                        cell.setCellStyle( copyStyle );
                        // 値の設定
                        if ( NO_DATA.equals( value ) ) {
                            cell.setCellValue( "" );
                        } else {
                            // 出力情報を分解して表示する
                            String outValue = (String) value;
                            String[] splitValue = outValue.split( "#" );
                            if ( splitValue.length > 1 ) {
                                cell.setCellValue( splitValue[0] );
                            } else {
                                cell.setCellValue( (String) value );
                            }
                        }
                    }
                }
                // 値の型が数値(実質通らない)
            } else if ( value instanceof Number ) {
                Number numValue = (Number) value;
                if ( numValue instanceof Float ) {
                    Float floatValue = (Float) numValue;
                    numValue = new Double( String.valueOf( floatValue ) );
                }
                cell.setCellValue( numValue.doubleValue() );
                // 値の型が日付(実質通らない)
            } else if ( value instanceof Date ) {
                Date dateValue = (Date) value;
                cell.setCellValue( dateValue );
                // 値の型が成否(実質通らない)
            } else if ( value instanceof Boolean ) {
                Boolean boolValue = (Boolean) value;
                cell.setCellValue( boolValue );
            }
        } else {
            // どこにも属さない場合は空白
            cell.setCellType( XSSFCell.CELL_TYPE_BLANK );
            cell.setCellStyle( baseStyle );
        }
    }


    /**
     * @param bgColorMap
     * @param copyStyle
     */
    private void setBackGrandColers( HashMap<String, Integer> bgColorMap, XSSFCellStyle copyStyle ) {

        // 色を塗る
        if ( bgColorMap.size() == 3 ) {
            copyStyle.setFillForegroundColor( new XSSFColor( new java.awt.Color( bgColorMap.get( "R" ), bgColorMap.get( "G" ), bgColorMap.get( "B" ) ) ) );
            copyStyle.setFillPattern( CellStyle.SOLID_FOREGROUND );
        } else {
            copyStyle.setFillForegroundColor( new XSSFColor( new java.awt.Color( 255, 255, 255 ) ) );
            copyStyle.setFillPattern( CellStyle.NO_FILL );
        }
    }


    /**
     * カラーコードのRGB化
     *
     * @param colorCode カラーコード実態
     * @param colorMap RGB変換したカラーマップ
     */
    private void setPatternRGB( String colorCode, HashMap<String, Integer> colorMap ) {

        if ( colorCode != null && !"".equals( colorCode ) && !NO_COLAR.equals( colorCode ) ) {
            int collerR = Integer.parseInt( colorCode.substring( 1, 3 ), 16 );
            int collerG = Integer.parseInt( colorCode.substring( 4, 6 ), 16 );
            int collerB = Integer.parseInt( colorCode.substring( 5, 7 ), 16 );
            colorMap.put( "R", collerR );
            colorMap.put( "G", collerG );
            colorMap.put( "B", collerB );
        }
    }


    /**
     * セル設定の選択
     *
     * @param cell セルオブジェクト
     */
    private void selectCellTypes( XSSFCell cell ) {

        int cellType = cell.getCellType();
        switch ( cellType ) {
            case XSSFCell.CELL_TYPE_BLANK:
                break;
            case XSSFCell.CELL_TYPE_FORMULA:
                cell.setCellFormula( cell.getCellFormula() );
                break;
            case XSSFCell.CELL_TYPE_BOOLEAN:
                cell.setCellValue( cell.getBooleanCellValue() );
                break;
            case XSSFCell.CELL_TYPE_ERROR:
                cell.setCellErrorValue( cell.getErrorCellValue() );
                break;
            case XSSFCell.CELL_TYPE_NUMERIC:
                cell.setCellValue( cell.getNumericCellValue() );
                break;
            case XSSFCell.CELL_TYPE_STRING:
                cell.setCellValue( cell.getRichStringCellValue() );
                break;
            default:
                break;
        }
    }


    /**
     * セルの設定（スタイルの変更あり）
     *
     * @param wb ワークブックオブジェクト
     * @param baseCell テンプレートセル
     * @param sheet シートオブジェクト
     * @param values 明細部データ
     */
    private void setCellValuesWithSetStyle( XSSFWorkbook wb, XSSFCell baseCell, XSSFSheet sheet, Object[] values ) {

        // 繰返し処理開始セルの位置情報を保持
        int startRowPosition = baseCell.getRowIndex();
        int columnPosition = baseCell.getColumnIndex();
        int maxIndex = values.length;
        String strOld = new String();

        // 明細の数分繰返し処理をする
        for ( int index = 0; index < maxIndex; index++ ) {
            // 行を取得または生成
            XSSFRow row = sheet.getRow( startRowPosition + index );
            if ( row == null ) {
                row = sheet.createRow( startRowPosition + index );
                // 繰返し処理開始行と同じ高さを設定
                row.setHeight( sheet.getRow( startRowPosition ).getHeight() );
            }
            // セルを取得または生成
            XSSFCell cell = row.getCell( columnPosition );
            if ( cell == null ) {
                cell = row.createCell( columnPosition );
                // 繰返し処理開始セルの情報をコピー
                copyCell( baseCell, cell );
            }

            // 文字列型である場合に値を分割
            String tmp = null;
            String[] splitValue = null;
            if ( values[index] != null ) {
                tmp = (String) values[index];
                splitValue = tmp.split( "_" );
                setCellStyleAndValues( wb, cell, splitValue[0], splitValue[1], index, maxIndex );
            }
            // 出力値がnull以外
            if ( values[index] != null ) {
                strOld = splitValue[0];
            } else {
                cell.setCellType( XSSFCell.CELL_TYPE_BLANK );
                cell.setCellStyle( cell.getCellStyle() );
            }
        }
    }


    /**
     * セルに値を設定
     *
     * @param wb ワークブックオブジェクト
     * @param cell セルオブジェクト
     * @param value 設定値
     * @param strOld 比較元
     * @param bgColorCode 背景カラーCODE
     * @param fontColorCode 文字カラーCODE
     * @param index データ数
     * @param maxIndex 最大データ数
     */
    private void changeCellStyleWithSetValue( XSSFWorkbook wb, XSSFCell cell, Object value, String strOld, String bgColorCode, String fontColorCode, int index, int maxIndex ) {

        HashMap<String, Integer> bgColorMap = new HashMap<String, Integer>();

        HashMap<String, Integer> fontColorMap = new HashMap<String, Integer>();

        // 背景色
        setPatternRGB( bgColorCode, bgColorMap );
        // フォント色
        setPatternRGB( fontColorCode, fontColorMap );

        XSSFCellStyle baseStyle = cell.getCellStyle();
        XSSFCellStyle copyStyle = wb.createCellStyle();
        XSSFFont font = wb.createFont();

        // フォント色の指定
        if ( fontColorMap.size() == 3 ) {
            font.setColor( new XSSFColor( new java.awt.Color( fontColorMap.get( "R" ), fontColorMap.get( "G" ), fontColorMap.get( "B" ) ) ) );
        } else {
            font.setColor( new XSSFColor( new java.awt.Color( 255, 255, 255 ) ) );
        }


        if ( value != null ) {
            // 値の型が文字列
            if ( value instanceof String ) {
                selectCellTypes( cell );
                // 背景色を設定する。
                setBackGrandColers( bgColorMap, copyStyle );
                // いつも線を引く
                copyStyle.setBorderTop( CellStyle.BORDER_THIN );
                copyStyle.setBorderLeft( CellStyle.BORDER_THIN );
                copyStyle.setBorderRight( CellStyle.BORDER_THIN );
                copyStyle.setBorderBottom( CellStyle.BORDER_THIN );

                copyStyle.setAlignment( baseStyle.getAlignment() );
                copyStyle.setVerticalAlignment( baseStyle.getVerticalAlignment() );
                copyStyle.setFont( font );
                copyStyle.setShrinkToFit( true );
                cell.setCellStyle( copyStyle );
                cell.setCellValue( (String) value );


                // 値の型が数値
            } else if ( value instanceof Number ) {
                Number numValue = (Number) value;
                if ( numValue instanceof Float ) {
                    Float floatValue = (Float) numValue;
                    numValue = new Double( String.valueOf( floatValue ) );
                }
                cell.setCellValue( numValue.doubleValue() );
                // 値の型が日付
            } else if ( value instanceof Date ) {
                Date dateValue = (Date) value;
                cell.setCellValue( dateValue );
                // 値の型が成否
            } else if ( value instanceof Boolean ) {
                Boolean boolValue = (Boolean) value;
                cell.setCellValue( boolValue );
            }
        } else {
            // どこにも属さない場合は空白
            cell.setCellType( XSSFCell.CELL_TYPE_BLANK );
            cell.setCellStyle( baseStyle );
        }
    }


    /**
     * セルに値を設定
     *
     * @param cell セルオブジェクト
     * @param value 設定値
     */
    private void setCellValue( XSSFCell cell, Object value ) {

        XSSFCellStyle style = cell.getCellStyle();

        if ( value != null ) {
            // 値の型が数値
            if ( value instanceof String ) {
                cell.setCellValue( (String) value );
                // 値の型が数値
            } else if ( value instanceof Number ) {
                Number numValue = (Number) value;
                if ( numValue instanceof Float ) {
                    Float floatValue = (Float) numValue;
                    numValue = new Double( String.valueOf( floatValue ) );
                }
                cell.setCellValue( numValue.doubleValue() );
                // 値の型が日付
            } else if ( value instanceof Date ) {
                Date dateValue = (Date) value;
                cell.setCellValue( dateValue );
                // 値の型が成否
            } else if ( value instanceof Boolean ) {
                Boolean boolValue = (Boolean) value;
                cell.setCellValue( boolValue );
            }
        } else {
            // どこにも属さない場合は空白
            cell.setCellType( XSSFCell.CELL_TYPE_BLANK );
            cell.setCellStyle( style );
        }

    }


    /**
     * セルに値を設定
     *
     * @param cell セルオブジェクト
     * @param value 設定値
     * @param wb ワークブック
     */
    private void setCellValueIo028( XSSFCell cell, Object value, XSSFWorkbook wb ) {

        XSSFCellStyle style = cell.getCellStyle();

        if ( value != null ) {
            // 値の型が数式
            if ( value.toString().startsWith( "formula=" ) ) {
                String strTemp = value.toString().replace( "formula=", "" );
                cell.setCellFormula( strTemp );
                // 値の型が数値
            } else if ( value.toString().startsWith( "Integer=" ) ) {
                // カンマ区切りの数値型に変更
                DataFormat format = wb.createDataFormat();
                style.setDataFormat( format.getFormat( "#,##0" ) );
                // 数値にして値を設定
                String strTemp = value.toString().replace( "Integer=", "" );
                Number numValue = Long.valueOf( strTemp );
                cell.setCellValue( numValue.doubleValue() );
                // 値の型が文字列
            } else if ( value instanceof String ) {
                cell.setCellValue( (String) value );
                // 値の型が数値
            } else if ( value instanceof Number ) {
                Number numValue = (Number) value;
                if ( numValue instanceof Float ) {
                    Float floatValue = (Float) numValue;
                    numValue = new Double( String.valueOf( floatValue ) );
                }
                cell.setCellValue( numValue.doubleValue() );
                // 値の型が日付
            } else if ( value instanceof Date ) {
                Date dateValue = (Date) value;
                cell.setCellValue( dateValue );
                // 値の型が成否
            } else if ( value instanceof Boolean ) {
                Boolean boolValue = (Boolean) value;
                cell.setCellValue( boolValue );
            }
            style.setBorderTop( CellStyle.BORDER_THIN );
            style.setBorderBottom( CellStyle.BORDER_THIN );
            style.setBorderLeft( CellStyle.BORDER_THIN );
            style.setBorderRight( CellStyle.BORDER_THIN );
        } else {
            // どこにも属さない場合は空白
            cell.setCellType( XSSFCell.CELL_TYPE_BLANK );
            cell.setCellStyle( style );
        }

    }


    /**
     * @param baseCell テンプレートセル
     * @param sheet シートオブジェクト
     * @param values 明細部データ
     * @param numOfDetails 最大出力件数（縦方向）
     */
    private void setCellStyleAndValues( XSSFWorkbook wb, XSSFCell cell, Object value, String bgFlg, int index, int maxIndex ) {

        HashMap<String, Integer> bgColorMap = new HashMap<String, Integer>();


        XSSFCellStyle baseStyle = cell.getCellStyle();
        XSSFCellStyle copyStyle = wb.createCellStyle();
        XSSFFont font = wb.createFont();


        if ( value != null ) {
            // 値の型が文字列
            if ( value instanceof String ) {
                selectCellTypes( cell );

                if ( bgFlg.equals( "true" ) ) {
                    bgColorMap.put( "R", 255 );
                    bgColorMap.put( "G", 255 );
                    bgColorMap.put( "B", 0 );
                    // 背景色を設定する。
                    setBackGrandColers( bgColorMap, copyStyle );
                }
                // // いつも線を引く
                copyStyle.setBorderTop( CellStyle.BORDER_THIN );
                copyStyle.setBorderLeft( CellStyle.BORDER_THIN );
                copyStyle.setBorderRight( CellStyle.BORDER_THIN );
                copyStyle.setBorderBottom( CellStyle.BORDER_THIN );

                copyStyle.setAlignment( baseStyle.getAlignment() );
                copyStyle.setVerticalAlignment( baseStyle.getVerticalAlignment() );
                copyStyle.setFont( font );
                copyStyle.setShrinkToFit( true );
                cell.setCellStyle( copyStyle );
                if ( value.equals( "null" ) ) {
                    value = "";
                }
                cell.setCellValue( (String) value );


                // 値の型が数値
            } else if ( value instanceof Number ) {
                Number numValue = (Number) value;
                if ( numValue instanceof Float ) {
                    Float floatValue = (Float) numValue;
                    numValue = new Double( String.valueOf( floatValue ) );
                }
                cell.setCellValue( numValue.doubleValue() );
                // 値の型が日付
            } else if ( value instanceof Date ) {
                Date dateValue = (Date) value;
                cell.setCellValue( dateValue );
                // 値の型が成否
            } else if ( value instanceof Boolean ) {
                Boolean boolValue = (Boolean) value;
                cell.setCellValue( boolValue );
            }
        } else {
            // どこにも属さない場合は空白
            cell.setCellType( XSSFCell.CELL_TYPE_BLANK );
            cell.setCellStyle( baseStyle );
        }

    }


    /**
     * @param baseCell テンプレートセル
     * @param sheet シートオブジェクト
     * @param values 明細部データ
     * @param numOfDetails 最大出力件数（縦方向）
     */
    private void setCellValues( XSSFCell baseCell, XSSFSheet sheet, Object[] values, int numOfDetails ) {

        // 繰返し処理開始セルの位置情報を保持
        int startRowPosition = baseCell.getRowIndex();
        int columnPosition = baseCell.getColumnIndex();

        // 明細の数分繰返し処理をする
        for ( int i = 0; i < numOfDetails; i++ ) {

            // 行を取得または生成
            XSSFRow row = sheet.getRow( startRowPosition + i );
            if ( row == null ) {
                row = sheet.createRow( startRowPosition + i );
                // 繰返し処理開始行と同じ高さを設定
                row.setHeight( sheet.getRow( startRowPosition ).getHeight() );
            }
            // セルを取得または生成
            XSSFCell cell = row.getCell( columnPosition );
            if ( cell == null ) {
                cell = row.createCell( columnPosition );
                // 繰返し処理開始セルの情報をコピー
                copyCell( baseCell, cell );
            }
            // セルに値を設定
            setCellValue( cell, values[i] );
        }

    }


    /**
     * @param baseCell テンプレートセル
     * @param sheet シートオブジェクト
     * @param values 明細部データ
     * @param numOfDetails 最大出力件数（縦方向）
     * @param wb
     */
    private void setCellValuesIo028( XSSFCell baseCell, XSSFSheet sheet, Object[] values, int numOfDetails, XSSFWorkbook wb ) {

        // 繰返し処理開始セルの位置情報を保持
        int startRowPosition = baseCell.getRowIndex();
        int columnPosition = baseCell.getColumnIndex();

        // 明細の数分繰返し処理をする
        for ( int i = 0; i < numOfDetails; i++ ) {

            // 行を取得または生成
            XSSFRow row = sheet.getRow( startRowPosition + i );
            if ( row == null ) {
                row = sheet.createRow( startRowPosition + i );
                // 繰返し処理開始行と同じ高さを設定
                row.setHeight( sheet.getRow( startRowPosition ).getHeight() );
            }
            // セルを取得または生成
            XSSFCell cell = row.getCell( columnPosition );
            cell = null;
            if ( cell == null ) {
                cell = row.createCell( columnPosition );
                // 繰返し処理開始セルの情報をコピー
                copyCell( baseCell, cell );
            }
            // セルに値を設定
            setCellValueIo028( cell, values[i], wb );
        }

    }


    // セルの値を別セルにコピーする
    /**
     * @param fromCell コピー基セル
     * @param toCell コピー先セル
     */
    public void copyCell( XSSFCell fromCell, XSSFCell toCell ) {

        if ( fromCell != null ) {

            int cellType = fromCell.getCellType();
            switch ( cellType ) {
                case XSSFCell.CELL_TYPE_BLANK:
                    break;
                case XSSFCell.CELL_TYPE_FORMULA:
                    toCell.setCellFormula( fromCell.getCellFormula() );
                    break;
                case XSSFCell.CELL_TYPE_BOOLEAN:
                    toCell.setCellValue( fromCell.getBooleanCellValue() );
                    break;
                case XSSFCell.CELL_TYPE_ERROR:
                    toCell.setCellErrorValue( fromCell.getErrorCellValue() );
                    break;
                case XSSFCell.CELL_TYPE_NUMERIC:
                    toCell.setCellValue( fromCell.getNumericCellValue() );
                    break;
                case XSSFCell.CELL_TYPE_STRING:
                    toCell.setCellValue( fromCell.getRichStringCellValue() );
                    break;
                default:
            }

            if ( fromCell.getCellStyle() != null ) {
                toCell.setCellStyle( fromCell.getCellStyle() );
            }

            if ( fromCell.getCellComment() != null ) {
                toCell.setCellComment( fromCell.getCellComment() );
            }
        }
    }


    /**
     * タイトル行の参照値を返却する
     *
     * @param sheetIndex シートインデックス
     * @return 参照値の文字列
     */
    public String getRowRangeRef( int sheetIndex ) {

        String retRowRangeRef = null;
        if ( cellRangeRowList.get( sheetIndex ) != null ) {
            retRowRangeRef = cellRangeRowList.get( sheetIndex ).formatAsString();
        }
        return retRowRangeRef;
    }


    /**
     * タイトル列の参照値を返却する
     *
     * @param sheetIndex シートインデックス
     * @return 参照値の文字列
     */
    public String getColRangeRef( int sheetIndex ) {


        String retColRangeRef = null;
        if ( cellRangeColList.get( sheetIndex ) != null ) {
            retColRangeRef = cellRangeColList.get( sheetIndex ).formatAsString();
        }
        return retColRangeRef;

    }
}
