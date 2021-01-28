package jp.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConverterUtilTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * 漢数字の変換　001
	 */
	@Test
	public void chinaNumToArabicNumTestCase001() {

		String rValue = new String();
		String pValue = "２５６";
		String iValue = "弐百五十六";


		System.out.println("**********Case001 Test START**********");


		rValue = StringUtil.chinaNumToArabicNum(iValue);

		System.out.println("入力値：　" + iValue);
		System.out.println("想定値：　" + pValue);
		System.out.println("戻り値：　" + rValue);

		assertEquals(pValue,rValue);

		System.out.println("**********Case001 Test   END**********");

	}

	/**
	 * 漢数字の変換　002
	 */
	@Test
	public void chinaNumToArabicNumTestCase002() {

		String rValue = new String();
		String pValue = "９９";
		String iValue = "九十九";

		System.out.println();
		System.out.println("**********Case002 Test START**********");

		System.out.println("入力値：　" + iValue);
		System.out.println("想定値：　" + pValue);
		rValue = StringUtil.chinaNumToArabicNum(iValue);
		System.out.println("戻り値：　" + rValue);

		assertEquals(pValue,rValue);

		System.out.println("**********Case002 Test   END**********");

	}

	/**
	 * 漢数字の変換　003
	 */
	@Test
	public void chinaNumToArabicNumTestCase003() {

		String iValue = "百十九";
		String rValue = new String();
		String pValue = "１１９";


		System.out.println();
		System.out.println("**********Case003 Test START**********");




		System.out.println("入力値：　" + iValue);
		System.out.println("想定値：　" + pValue);
		rValue = StringUtil.chinaNumToArabicNum(iValue);
		System.out.println("戻り値：　" + rValue);
		assertEquals(pValue,rValue);

		System.out.println("**********Case003 Test   END**********");

	}


	/**
	 * 漢数字の変換　004
	 */
	@Test
	public void chinaNumToArabicNumTestCase004() {

		String iValue = "二";
		String pValue = "２";
		String rValue = new String();


		System.out.println();
		System.out.println("**********Case004 Test START**********");




		System.out.println("入力値：　" + iValue);
		System.out.println("想定値：　" + pValue);
		rValue = StringUtil.chinaNumToArabicNum(iValue);
		System.out.println("戻り値：　" + rValue);
		assertEquals(pValue,rValue);

		System.out.println("**********Case004 Test   END**********");

	}


	/**
	 * 漢数字の変換　005
	 */
	@Test
	public void chinaNumToArabicNumTestCase005() {

		String iValue = "弐五六";
		String pValue = "２５６";
		String rValue = new String();


		System.out.println();
		System.out.println("**********Case005 Test START**********");




		System.out.println("入力値：　" + iValue);
		System.out.println("想定値：　" + pValue);
		rValue = StringUtil.chinaNumToArabicNum(iValue);
		System.out.println("戻り値：　" + rValue);
		assertEquals(pValue,rValue);

		System.out.println("**********Case005 Test   END**********");

	}

	/**
	 * 漢数字の変換　006
	 */
	@Test
	public void chinaNumToArabicNumTestCase006() {

		String iValue = "千五十九";
		String pValue = "５９";
		String rValue = new String();


		System.out.println();
		System.out.println("**********Case006 Test START**********");


		System.out.println("入力値：　" + iValue);
		System.out.println("想定値：　" + pValue);
		rValue = StringUtil.chinaNumToArabicNum(iValue);
		System.out.println("戻り値：　" + rValue);
		assertEquals(pValue,rValue);

		System.out.println("**********Case006 Test   END**********");

	}


}
