/* ---------------------------------------------------------------- */
/*   e-Acris3 development project.                                  */
/*                                                                  */
/*      Utilities library belongs REPORT application.               */
/*        PROGRAM : XMLUtil.java                                    */
/*         AUTHOR : S&F)M.Sasaki                                    */
/*          SINCE : 2008/09/01                                      */
/*                                                                  */
/*   (C)2008 Profit Cube Inc. All rights reserved.                  */
/* ---------------------------------------------------------------- */

package jp.co.profitcube.eacris.report.util;

import java.util.List;
import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * XML操作ユーティリティ・クラス
 *
 * @author M.Sasaki
 * @version 1.0
 */

public class XMLUtil {

    /**
     * コンストラクタ（外部インスタンス生成不可）
     */
    private XMLUtil() {}

    /**
     * 指定の親Nodeへ子Nodeを追加する。
     * @param parent 親Node
     * @param child 追加する子Node
     */
    public static void appendNode(Node parent, Node child)
    {
        Document owner = parent.getOwnerDocument();
        Node addNode = owner.importNode(child, true);
        parent.appendChild(addNode);
    }

    /**
     * 指定したエレメントNodeを指定した親Nodeから抽出する。
     * @param parent 親Node
     * @param elementName エレメントNode名
     * @return エレメントNode<br>
     * 指定したエレメントNodeが存在しない場合はnullを返す。
     */
    public static Node getNode(Node parent, String elementName)
    {
        NodeList list = parent.getChildNodes();
        Node child = null;

        for (int i=0; i<list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                if (node.getNodeName().equals(elementName)) {
                    child = node;
                    break;
                }
            }
        }
        return child;
    }

    /**
     * 指定したエレメントNode一覧を指定した親Nodeから抽出する。
     * @param parent 親Node
     * @param elementName エレメントNode名
     * @return エレメントNode一覧<br>
     * 指定したエレメントNodeが存在しない場合はnullを返す。
     */
    public static List getElementNodeList(Node parent, String elementName)
    {
        List elementList = null;

        NodeList list = parent.getChildNodes();

        for (int i=0; i<list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                if (node.getNodeName().equals(elementName)) {
                    if (elementList == null) {
                        elementList = new ArrayList();
                    }
                    elementList.add(node);
                }
            }
        }
        return elementList;
    }

    /**
     * 指定したNodeの値を抽出する。
     * @param node Node
     * @return Nodeの値
     * 指定したNodeがテキスト子ノードを持たない場合、空文字を返す。
     */
    public static String getNodeValue(Node node)
    {
        NodeList list = node.getChildNodes();
        String value = "";

        Node tmp = list.item(0);
        try {
            if (tmp.getNodeType() == Node.TEXT_NODE) {
                value = tmp.getNodeValue();
            }
        } catch (Exception e) {
            value = "";
        }
        return value;
    }

    /**
     * 指定した親NodeへNodeを挿入する。
     * @param parent 挿入先のNode
     * @param ins 挿入Node
     * @param ref 参照Node このNodeの前に挿入する。
     */
    public static void insertNode(Node parent, Node ins, Node ref)
    {
        Document owner = parent.getOwnerDocument();
        Node insNode = owner.importNode(ins, true);
        parent.insertBefore(insNode, ref);
    }

    /**
     * 指定した親NodeからNodeを削除する。
     * @param parent 親Node
     * @param child 削除Node
     */
    public static void removeNode(Node parent, Node child)
    {
        parent.removeChild(child);
    }

    /**
     * 指定した親Nodeの子Nodeを置換する。
     * @param parent 親Node
     * @param newNode 置換後のNode
     * @param oldNode 置換前のNode
     */
    public static void replaceNode(Node parent, Node newNode, Node oldNode)
    {
        Document owner = parent.getOwnerDocument();
        Node replace = owner.importNode(newNode, true);
        parent.replaceChild(replace, oldNode);
    }

    /**
     * <pre>
     * 指定したNodeへ値を設定する。
     * 指定したNodeが空タグの場合、値を設定しない。（何もしない。）
     * @param node Node
     * @param value 設定値
     * </pre>
     */
    public static void setNodeValue(Node node, String value)
    {
        NodeList list = node.getChildNodes();

        Node tmp = list.item(0);
        try {
            if (tmp.getNodeType() == Node.TEXT_NODE) {
                tmp.setNodeValue(value);
            }
        } catch (Exception e) {
            //No ope
        }
    }

    /**
     * <pre>
     * 指定したNodeへ値を設定する。
     * @param node Node
     * @param value 設定値
     * @param flgEmpTag 空タグ設定フラグ
     * <ui>
     * <li>true  : 空タグの場合、指定した値を設定する。</li>
     * <li>false : 空タグの場合、指定した値を設定しない。（何もしない。）</li>
     * </ui>
     * </pre>
     */
    public static void setNodeValue(Node node, String value, boolean flgEmpTag)
    {
        if (getNodeValue(node).equals("")) {    // 空タグの場合
            if (flgEmpTag) {
                // 空タグ設定フラグが設定されていれば、テキストを設定
                setText(node, value);
            }
        } else {                                // 空タグでない場合
            setNodeValue(node, value);
        }
    }

    /**
     * 空タグにテキストを設定する。
     * @param node 空タグのNode
     * @param value 設定文字列
     */
    public static void setText(Node node, String value)
    {
        Document document = node.getOwnerDocument();
        node.appendChild(document.createTextNode(value));
    }

    /**
     * エレメント Node に属性を設定する。
     * @param elementNode エレメント Node
     * @param attrName 属性名
     * @param attrValue 属性値
     */
    public static void setAttribute(Node elementNode, String attrName, String attrValue)
    {
        try {
            ((Element)elementNode).setAttribute(attrName, attrValue);
        } catch (Exception e) {
            // 何もしない
        }
    }

    /**
     * エレメント Node の属性値を変更する。
     * @param elementNode エレメント Node
     * @param attrName 属性名
     * @param attrValue 属性値
     */
    public static void changeAttribute(Node elementNode, String attrName, String attrValue)
    {
        try {
            Element element = (Element)elementNode;
            element.removeAttribute(attrName);          // 属性を一旦削除
            element.setAttribute(attrName, attrValue);  // 新規追加
        } catch (Exception e) {
            // 何もしない
        }
    }

    /**
     * <pre>
     * エレメント属性値を取得する。
     * @param elementNode エレメントNode
     * @param attrName 属性名
     * @return エレメント属性値
     * 　指定した属性名が存在しない場合、空文字を返す。
     * </pre>
     */
    public static String getNodeAttrValue(Node elementNode, String attrName)
    {
        String value = "";
        if (elementNode.hasAttributes()) {
            NamedNodeMap map = elementNode.getAttributes();
            for (int i=0; i<map.getLength(); i++) {
                Node node = map.item(i);
                if ((node.getNodeName()).equals(attrName)) {
                    value = node.getNodeValue();
                    break;
                }
            }
        }
        return value;
    }

    /**
     * XMLファイルを読込み、Nodeへ変換する。
     * @param xmlFilePath クラスパスからの相対パス
     * @return XMLファイルのNode
     */
    public static Node xml2Node(String xmlFilePath)
    {
        DocumentBuilder builder = null;
        Document document = null;
        Node node = null;

        if (!xmlFilePath.substring(0, 1).equals("/")) {
            xmlFilePath = "/" + xmlFilePath;
        }

        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = builder.parse(new XMLUtil().getClass().getResourceAsStream(xmlFilePath));
            node = (Node)document.getDocumentElement();
        } catch (Exception e) {
            node = null;
        }

        return node;
    }

     /**
      * Node値の取得(XMLファイル名を指定)する。
      * @param xmlFilePath XMLファイルの設定ファイルからの相対パス
      * @param nodeName 親Nodeからたどっていく子Node名
      * @return nodeName配列の最後の要素で指定されたテキストNodeの値
      */
     public static String getConfigValue(String xmlFilePath, String[] nodeName)
     {
        Node sg = xml2Node(xmlFilePath);
        Node node = sg;

        for (int i=0; i<nodeName.length; i++) {
            node = getNode(node, nodeName[i]);
        }
        return getNodeValue(node);
     }

    /**
     * Node値の取得
     * @param config XML Nodeツリー
     * @param nodeName 親Nodeからたどっていく子Node名
     * @return nodeName配列の最後の要素で指定されたテキストNodeの値
     */
    public static String getConfigValue(Node config, String[] nodeName)
    {
        Node node = config;

        for (int i=0; i<nodeName.length; i++) {
            node = getNode(node, nodeName[i]);
        }
        return getNodeValue(node);
     }
    
	// 2014/01/24 SBIM 自動再照会・別姓/補正 対応 ADD START suzuki_h 別姓消込
    /**
     * 指定した子Node群の要素をList型で返す。
     * @param node
     * @return
     */
	public static List getConfigValueList(Node node) {
		
        NodeList gchildren  = node.getChildNodes();
		
        List<String> list = new ArrayList();
        List childList = new ArrayList();
        for(int i = 0;i<gchildren.getLength();i++){
        	Node gchild = gchildren.item(i);
        	list.add((XMLUtil.getNodeValue(gchild)));
        }
        if(list!= null){
        	for(int i=0;i<list.size();i++){
        		if(list.get(i).trim().length() != 0){
        			childList.add(list.get(i));
        		}
        	}
        }

		return childList;
	}
	// 2014/01/24 SBIM 自動再照会・別姓/補正 対応 ADD END   suzuki_h 別姓消込
	    
}
