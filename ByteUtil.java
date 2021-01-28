/*
 * 作成日：2005/05/24
 * 
 * (C) COPYRIGHT Open Future System Corp. 2005
 * All Rights Reserved.
 */
package jp.co.profitcube.eacris.web.bridge.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * バイト配列を操作するユーティリティメソッド郡。
 * <p>
 * @author daisaku
 * @version 1.0
 */
public class ByteUtil {
    
    /**
     * 新規のファイルにバイト配列の内容を書き込む。
     * 既存のファイルに上書きしようとした場合は実行時例外が発生する。
     * <p>
     * @param b バイト配列
     * @param newFile 新規作成ファイル
     */
    public static synchronized void toFile(byte[] b, File newFile){
        if(FileUtil.isReadFile(newFile)){
            throw new ByteUtilRuntimeException("already exist file: file-path["+newFile.getPath()+"]");
        }
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(newFile);
            stream.write(b);
            stream.flush();
            stream.close();
        } catch (Exception e) {
            throw new ByteUtilRuntimeException(e);
        }finally{
            if(stream != null){
                try {
                    stream.close();
                } catch (IOException e) {
                    throw new ByteUtilRuntimeException(e);
                }
            }
        }
    }

}

class ByteUtilRuntimeException extends RuntimeException{
    public ByteUtilRuntimeException(String message){
        super(message);
    }
    public ByteUtilRuntimeException(Exception e){
        super(e);
    }
}
