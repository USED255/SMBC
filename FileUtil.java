/*
 * 作成日：2005/05/24
 * 
 * (C) COPYRIGHT Open Future System Corp. 2005
 * All Rights Reserved.
 */
package jp.co.profitcube.eacris.web.bridge.util;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * コメント
 * <p>
 * @author daisaku
 * @version 1.0
 */
public class FileUtil {
    
    /**
     * 読み込み可能なファイルか否か。
     * <p>
     * @param file 対象ファイル
     * @return true:読み込み可能、false:それ以外
     */
    public static boolean isReadFile(File file){
        //ファイルが存在するか否か
        if(file.exists()){
            //ファイルがファイルか否か
            if(file.isFile()){
                //ファイルが読込み可能か否か
                if(file.canRead()){
                   return true; 
                }
            }
        }
        return false;
    }
    
    /**
     * ファイルから指定のエンコード方式で文字列を読込み、
     * 改行単位で格納したString配列を返す。
     * 改行コードはOSに準拠したものを利用する。
     * <p>
     * @param file ファイル
     * @param encode エンコード方式
     * @return ファイルから読込んだ文字列の配列
     */
    public static String[] toLines(File file, String encode){
        InputStream stream = null;
        InputStreamReader reader = null;
        BufferedReader bReader = null;
        try{
            stream = new FileInputStream(file);
            reader = new InputStreamReader(stream, encode);
            bReader = new BufferedReader(reader);
            String line = null;
            List list = new ArrayList();
            while(bReader.ready()){
                line = bReader.readLine();
                list.add(line);
            }
            String[] lines = new String[list.size()];
            Iterator i = list.iterator();
            for(int j = 0; i.hasNext(); j++){
                lines[j] = (String)i.next();
            }
            return lines;
        }catch(Exception e){
            throw new FileUtilRuntimeException(e);
        }finally{
            if(stream != null){
	            try {
	                stream.close();
	            } catch (IOException e) {
	                throw new FileUtilRuntimeException(e);
	            }
            }
            if(reader != null){
	            try {
	                reader.close();
	            } catch (IOException e) {
	                throw new FileUtilRuntimeException(e);
	            }
            }
            if(bReader != null){
	            try {
	                bReader.close();
	            } catch (IOException e) {
	                throw new FileUtilRuntimeException(e);
	            }
            }
        }
    }
    
    /**
     * ファイルの内容をバイト配列で取得する。
     * <p>
     * @param file 読込むファイル
     * @return ファイルから読込んだバイト配列
     */
    public static byte[] toBytes(File file){
        FileInputStream stream = null;
        try{
            stream = new FileInputStream(file);
            byte[] b = new byte[(int) file.length()];
            stream.read(b);
            /*
            fChannel = stream.getChannel();
            ByteBuffer bb = ByteBuffer.allocate((int)fChannel.size());
            fChannel.read(bb);
            System.out.println(Dumper.dump(bb.array()));
        	return bb.array();
        	*/
            return b;
        }catch(Exception e){
            throw new FileUtilRuntimeException(e);
        }finally{
            if(stream != null){
	            try {
	                stream.close();
	            } catch (IOException e) {
	                throw new FileUtilRuntimeException(e);
	            }
            }
        }
    }
    
    // 2010/04/27 option tsuda_t MOV from 2009/04/30 FH_0020 ADD M.Sasaki start.
    /**
     * ファイルのコピーを行う。
     * @param source コピー元ファイル
     * @param target コピー先ファイル
     */
    public static void copy(File source, File target) throws IOException
    {
        FileChannel sChannel = new FileInputStream(source).getChannel();
        FileChannel tChannel = new FileOutputStream(target).getChannel();

        sChannel.transferTo(0, sChannel.size(), tChannel);

        sChannel.close();
        tChannel.close();
    }

    /**
     * オブジェクト直列化書出しを行う。
     * @param obj 直列化可能オブジェクト
     * @param outFile 出力先ファイル名
     */
    public static void writeSerFile(Object obj, String outFile) throws IOException
    {
        FileOutputStream fos = new FileOutputStream(outFile);
        ObjectOutputStream oStream = new ObjectOutputStream(fos);
        oStream.writeObject(obj);
        oStream.close();
    }

    /**
     * オブジェクト直列化XML書出しを行う。
     * @param obj 直列化可能オブジェクト
     * @param outFile 出力先ファイル名
     */
    public static void writeXmlSerFile(Object obj, String outFile) throws IOException
    {
        FileOutputStream fos = new FileOutputStream(outFile);
        XMLEncoder oStream = new XMLEncoder(new BufferedOutputStream(fos));
        oStream.writeObject(obj);
        oStream.close();
    }

    /**
     * 直列化ファイルの読出しを行う。
     * @param inFile 読出し元ファイル
     * @return オブジェクト
     */
    public static Object readSerFile(String inFile) throws IOException, ClassNotFoundException
    {
        Object retVal = null;

        FileInputStream fis = new FileInputStream(inFile);
        ObjectInputStream iStream = new ObjectInputStream(fis);
        retVal = iStream.readObject();
        iStream.close();

        return retVal;
    }

    /**
     * 直列化XMLファイルの読出しを行う。
     * @param inFile 読出し元ファイル
     * @return オブジェクト
     */
    public static Object readXmlSerFile(String inFile) throws IOException, ClassNotFoundException
    {
        Object retVal = null;

        FileInputStream fis = new FileInputStream(inFile);
        XMLDecoder iStream = new XMLDecoder(new BufferedInputStream(fis));
        retVal = iStream.readObject();
        iStream.close();

        return retVal;
    }

    /**
     * ファイルの書込を行う。
     * @param filePath 出力先ファイル
     * @param outString 出力文字列
     * @param encoding エンコーディング
     */
    public static void writeFile(String filePath, String outString, String encoding) throws Exception
    {
        BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), encoding));
        bWriter.write(outString);
        bWriter.close();
    }

    /**
     * バイト列の書込を行う。
     * @param outByte 出力バイト列
     * @param outFile 出力先ファイル名
     */
    public static void writeByteFile(byte[] outByte, String outFile) throws IOException
    {
        FileOutputStream fos = new FileOutputStream(outFile);
        fos.write(outByte);
        fos.close();
    }
    // 2010/04/27 option tsuda_t MOV from 2009/04/30 FH_0020 ADD M.Sasaki end.
    
    // 2014/01/22 SBIM 自動再照会・別姓/補正 対応 ADD START hishinuma_d 回答補正
    /**
     * 指定ディレクトリ以下のファイル・ディレクトリを削除する。
     * @param dirPath 削除対象ディレクトリ
     */
    public static void deleteFile(String dirPath) {
    	File dir = new File(dirPath);
    	File[] files = dir.listFiles(getFileRegexFilter("*"));
    	
    	for (File file : files) {
    		deleteDirectory(file);
    	}
    }
    
    /**
     * 再帰的にファイル・ディレクトリを削除する。
     * @param file
     */
    public static void deleteDirectory(File file) {
    	if (file.exists() == false){
    		return;
    	}
    	
    	if (file.isFile()) {
    		file.delete();
    		
    	} else if (file.isDirectory()) {
    		File[] files = file.listFiles();
    		for (int i = 0; i < files.length; i++) {
    			deleteDirectory(files[i]);
    		}
    		file.delete();
    	}
    }
    
    /**
     * 正規表現を使うためのフィルタークラス
     * @param regex
     * @return
     */
    public static FilenameFilter getFileRegexFilter(String regex) {  
        final String regex_ = regex;  
        return new FilenameFilter() {
            public boolean accept(File file, String name) {
                boolean ret = name.matches(regex_);
                return ret;  
            }
        };  
    }
    // 2014/01/22 SBIM 自動再照会・別姓/補正 対応 ADD END hishinuma_d 回答補正
}

class FileUtilRuntimeException extends RuntimeException{
    public FileUtilRuntimeException(String message){
        super(message);
    }
    public FileUtilRuntimeException(Exception e){
        super(e);
    }
}