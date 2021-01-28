/*
 * 作成日：2005/05/15
 * 
 * (C) COPYRIGHT Open Future System Corp. 2005
 * All Rights Reserved.
 */
package jp.co.profitcube.eacris.web.bridge.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * XML形式のプロパティーファイルを解析する。
 * <p>
 * @author daisaku
 * @version 1.0
 */
public class XMLPropertyParser {
    
    //コンスト
    /** propertyタグ */
    private final static String TAG_PROPERTY = "property";
    /** propertyタグのname属性 */
    private final static String ATTR_NAME = "name";
    /** propertyタグのvalue属性 */
    private final static String ATTR_VALUE = "value";
    
    //初期化
    /** XMLドキュメントを格納 */
    private Document doc;
    /** key:name, value:valueを格納 */
    private Map map = new HashMap();
    
    /**
     * 指定のファイルを読込み、DOMオブジェクトに変換する
     * <p>
     * @param file XMLファイル
     * @throws FileNotFoundException 
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public XMLPropertyParser(File file) throws FileNotFoundException, IOException, ParserConfigurationException, SAXException{
        //読み込み
        InputStream stream = new FileInputStream(file);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		this.doc = db.parse(stream);
    }
    
    /**
     * DOMオブジェクトを走査し、keyとvalueに分解しマップに格納する。
     * <p>
     */
    public void parse(){
		//プロパティを取得
		NodeList nList = this.doc.getElementsByTagName(TAG_PROPERTY);
		int length = nList.getLength();
		//プロパティをマップにセット
		for(int i = 0; i < length; i++){
		   Node propertyNode = nList.item(i);
		   String name = getPropertyName(propertyNode);
		   String value = getPropertyValue(propertyNode);
		   map.put(name, value);
		}
    }
    
    /**
     * ノードからプロパティー名を取得する。
     * <p>
     * @param node ノード
     * @return 名前
     */
    private String getPropertyName(Node node){
        NamedNodeMap map = node.getAttributes();
        Node pNode = map.getNamedItem(ATTR_NAME);
        return pNode.getNodeValue();
    }
    
    /**
     * ノードからプロパティーの値を取得する。
     * <p>
     * @param node ノード
     * @return 値
     */
    private String getPropertyValue(Node node){
        NamedNodeMap map = node.getAttributes();
        Node pNode = map.getNamedItem(ATTR_VALUE);
        return pNode.getNodeValue();
    }
    
    /**
     * nameに対応したvalueをStringで取得する。
     * <p>
     * @param name 名前
     * @return 値
     */
    public String getPropertyAsString(String name){
        Object object = map.get(name);
        if(object == null){
            throw new PropertyValueNotFoundRuntimeException("name["+name+"]");
        }
        return (String) map.get(name);
    }
    
    /**
     * nameに対応したvalueをintで取得する
     * <p>
     * @param name 名前
     * @return 値
     */
    public int getPropertyAsInt(String name){
        String p = getPropertyAsString(name);
        return Integer.parseInt(p);
    }
    
// 2010/04/22 option tsuda_t ADD START long追加
    /**
     * nameに対応したvalueをlongで取得する
     * <p>
     * @param name 名前
     * @return 値
     */
    public long getPropertyAsLong(String name){
        String p = getPropertyAsString(name);
        return Long.parseLong(p);
    }
//  2010/04/22 option tsuda_t ADD END long追加
    
    /**
     * nameに対応したvalueをintで取得する
     * <p>
     * @param name 名前
     * @return 値
     */
    public boolean getPropertyAsBoolean(String name){
        String p = getPropertyAsString(name);
        Boolean bool = new Boolean(p);
        return bool.booleanValue();
    }
    
    /**
     * key=name、value=value形式でマップに格納する。
     * <p>
     * @param name 名前
     * @param value 値
     */
    public void setProperty(String name, String value){
        map.put(name, value);
    }
    
    /**
     * 登録済みプロパティー名を取得する。
     * <p>
     * @return プロパティ名
     */
    public String[] getNameList(){
        int size = map.size();
        String[] names = new String[size];
        Set set = map.entrySet();
        Iterator i = set.iterator();
        int index = 0;
        while(i.hasNext()){
            names[index] = (String)i.next();
            index++;
        }
        return names;
    }
    
    /**
     * 現在のプロパティをファイルに書き出す。
     * <p>
     * @param file ファイル
     */
    public void write(File file){
        //TODO 書出しコード(暇な時)
    }
}

/**
 * 本クラス実行時例外。
 * <p>
 * @author daisaku
 * @version 1.0
 */
class PropertyValueNotFoundRuntimeException extends RuntimeException {
    public PropertyValueNotFoundRuntimeException(String message){
        super(message);
    }
}
