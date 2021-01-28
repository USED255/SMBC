/*
 *　@(#)ByteArrayUtil.java
 *
 */
package jp.console.main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * バイト配列操作のヘルパークラス
 */
public class ByteArrayUtil {

    /**
     * 可変長配列からバイトの配列を生成
     * 
     * @param list
     *            可変長配列
     * @return バイトの配列
     */
    public static byte[] toArray(List<Byte> list) {
        byte[] array = new byte[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i).byteValue();
        }
        return array;
    }

    /**
     * バイトの配列から可変長配列を生成
     * 
     * @param array
     *            バイトの配列
     * @return 可変長配列
     */
    public static List<Byte> asList(byte[] array) {
        List<Byte> list = new ArrayList<Byte>(array.length);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    /**
     * 可変長配列に指定された範囲の配列を追加
     * 
     * @param list
     *            可変長配列
     * @param array
     *            コピーされる配列
     * @param from
     *            コピーされる範囲の最初のインデックス
     * @param to
     *            コピーされる範囲の最後の次のインデックス(範囲外)
     */
    public static void appendOfRange(List<Byte> list, byte[] array, int from,
            int to) {
        for (int i = from; i < to; i++) {
            list.add(array[i]);
        }
    }

    /**
     * 可変長配列に指定されたファイルを追加
     * 
     * @param list
     *            可変長配列
     * @param file
     *            追加するファイル
     * @throws IOException
     *             ファイルI/Oエラー
     */
    public static void applendFile(List<Byte> list, File file)
            throws IOException {
        InputStream stream = new BufferedInputStream(new FileInputStream(file));
        try {
            int ch;
            while ((ch = stream.read()) >= 0) {
                list.add((byte) ch);
            }
        } finally {
            stream.close();
        }
    }

    /**
     * バイナリファイルの読み込み
     * 
     * @param file
     *            ファイル
     * @return バイトの配列
     * @throws IOException
     *             ファイルI/Oエラー
     */
    public static byte[] readFile(File file) throws IOException {
        byte[] array = new byte[(int) file.length()];
        InputStream stream = new BufferedInputStream(new FileInputStream(file));
        try {
            stream.read(array);
        } finally {
            stream.close();
        }
        return array;
    }
}
