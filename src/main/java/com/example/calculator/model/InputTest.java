package com.example.calculator.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputTest {

	@Test
	@DisplayName("インスタンス生成：正常系")
	void testCreateTrue() {
		Input test = new Input();
		String expected = "5";
		String actual = Input.inputProcessing("0","5");
		assertEquals(expected,actual);
	}
	/* 未テストメソッド
	 *checkPeriod
	 *
	 * */




	@Test
	@DisplayName("式を塊に分ける。最後を取り出してピリオドチェック：正常系")
	void testCheckPeriodTrue() {
		boolean actual = Input.checkPeriod("1.23×12.345");
		assertTrue(actual);
	}
	@Test
	@DisplayName("式を塊に分ける。最後を取り出してピリオドチェック：異常系")
	void testCheckPeriodFalse() {
		boolean actual = Input.checkPeriod("12345÷1234");
		assertFalse(actual);
	}
	@Test
	@DisplayName("式を塊に分ける。最後を取り出してピリオドチェック：異常系")
	void testCheckPeriodNull() {
		boolean actual = Input.checkPeriod(null);
		assertFalse(actual);
	}
	//*********************************************
	@Test
	@DisplayName("ピリオドが含まれているかチェック：正常系")
	void testIsPeriodTrue() {
		boolean actual = Input.IsPeriod("1.234");
		assertTrue(actual);
	}
	@Test
	@DisplayName("ピリオドが含まれているかチェック：異常系")
	void testIsPeriodFalse() {
		boolean actual = Input.IsPeriod("12345");
		assertFalse(actual);
	}
	@Test
	@DisplayName("ピリオドが含まれているかチェック：異常系")
	void testIsPeriodNull() {
		boolean actual = Input.IsPeriod(null);
		assertFalse(actual);
	}
	//*********************************************
	@Test
	@DisplayName("入力に応じてdisplay文字を編集して出力：正常系")
	void testInputProcessingTrue() {
		String expected = "5";
		String actual = Input.inputProcessing("0","5");
		assertEquals(expected,actual);
	}
	@Test
	@DisplayName("最初に0.1にする：正常系")
	void testInputProcessingTrue2() {
		String expected = "0.1";
		String actual = Input.inputProcessing("0",".");
		actual = Input.inputProcessing(actual,"1");
		assertEquals(expected,actual);
	}
	@Test
	@DisplayName("最初に0.1にする：正常系")
	void testInputProcessingTrue3() {
		String expected = "0.1";
		String actual = Input.inputProcessing("0",".");
		actual = Input.inputProcessing(actual,"1");
		actual = Input.inputProcessing(actual,".");
		assertEquals(expected,actual);
	}
	@Test
	@DisplayName("最初に0.1にする：正常系")
	void testInputProcessingTrue4() {
		String expected = "0.11";
		String actual = Input.inputProcessing("0",".");
		actual = Input.inputProcessing(actual,"1");
		actual = Input.inputProcessing(actual,"1");
		assertEquals(expected,actual);
	}
	@Test
	@DisplayName("最初に0.1にする：正常系")
	void testInputProcessingTrue5() {
		String expected = "0.1-";
		String actual = Input.inputProcessing("0",".");
		actual = Input.inputProcessing(actual,"1");
		actual = Input.inputProcessing(actual,"＋");
		actual = Input.inputProcessing(actual,"-");
		assertEquals(expected,actual);
	}
	@Test
	@DisplayName("最初に0.1にする：正常系")
	void testInputProcessingTrue6() {
		String expected = "0.1＋";
		String actual = Input.inputProcessing("0",".");
		actual = Input.inputProcessing(actual,"1");
		actual = Input.inputProcessing(actual,"-");
		actual = Input.inputProcessing(actual,"-");
		assertEquals(expected,actual);
	}
	@Test
	@DisplayName("最初に0.1にする：正常系")
	void testInputProcessingTrue7() {
		String expected = "0.1÷-";
		String actual = Input.inputProcessing("0",".");
		actual = Input.inputProcessing(actual,"1");
		actual = Input.inputProcessing(actual,"÷");
		actual = Input.inputProcessing(actual,"-");
		assertEquals(expected,actual);
	}
	@Test
	@DisplayName("入力に応じてdisplay文字を編集して出力：異常系")
	void testInputProcessingNull() {
		assertThrows(NullPointerException.class, ()-> Input.inputProcessing(null,null));
	}
	//******************************************
	@Test
	@DisplayName("入力系文字であるchar：正常系")
	void testCheckInputTrue() {
		boolean actual = Input.checkInput('÷');
		assertTrue(actual);
	}
	@Test
	@DisplayName("入力系文字であるchar：異常系")
	void testCheckInputFalse() {
		boolean actual = Input.checkInput('＝');
		assertFalse(actual);
	}
	@Test
	@DisplayName("入力系文字であるString：正常系")
	void testCheckInputStringTrue() {
		boolean actual = Input.checkInput("×");
		assertTrue(actual);
	}
	@Test
	@DisplayName("入力系文字であるString：異常系")
	void testCheckInputStringFalse() {
		boolean actual = Input.checkInput("＝");
		assertFalse(actual);
	}
	@Test
	@DisplayName("入力系文字であるString：異常系")
	void testCheckInputStringNull() {
		assertThrows(NullPointerException.class, ()-> Input.checkInput(null));
	}
	//********************************************
	@Test
	@DisplayName("．であるchar：正常系")
	void testSymbolIsPeriodTrue() {
		boolean actual = Input.symbolIsPeriod('.');
		assertTrue(actual);
	}
	@Test
	@DisplayName("．であるchar：異常系")
	void testSymbolIsPeriodFalse() {
		boolean actual = Input.symbolIsPeriod('-');
		assertFalse(actual);
	}
	@Test
	@DisplayName("．であるString：正常系")
	void testSymbolIsPeriodStringTrue() {
		boolean actual = Input.symbolIsPeriod(".");
		assertTrue(actual);
	}
	@Test
	@DisplayName("．であるString：異常系")
	void testSymbolIsPeriodStringFalse() {
		boolean actual = Input.symbolIsPeriod("-");
		assertFalse(actual);
	}
	@Test
	@DisplayName("．であるString：異常系")
	void testSymbolIsPeriodStringNull() {
		assertThrows(NullPointerException.class, ()-> Input.symbolIsPeriod(null));
	}
	//********************************************
	@Test
	@DisplayName("×または÷であるchar：正常系")
	void testSymbolIsMuDTrue() {
		boolean actual = Input.symbolIsMuD('÷');
		assertTrue(actual);
	}
	@Test
	@DisplayName("×または÷であるchar：異常系")
	void testSymbolIsMuDFalse() {
		boolean actual = Input.symbolIsMuD('-');
		assertFalse(actual);
	}
	@Test
	@DisplayName("×または÷であるString：正常系")
	void testSymbolIsMuDStringTrue() {
		boolean actual = Input.symbolIsMuD("×");
		assertTrue(actual);
	}
	@Test
	@DisplayName("×または÷であるString：異常系")
	void testSymbolIsMuDStringFalse() {
		boolean actual = Input.symbolIsMuD("-");
		assertFalse(actual);
	}
	@Test
	@DisplayName("×または÷であるString：異常系")
	void testSymbolIsMuDStringNull() {
		assertThrows(NullPointerException.class, ()-> Input.symbolIsMuD(null));
	}
	//********************************************
	@Test
	@DisplayName("プラスであるchar：正常系")
	void testSymbolIsPlusTrue() {
		boolean actual = Input.symbolIsPlus('＋');
		assertTrue(actual);
	}
	@Test
	@DisplayName("プラスであるchar：異常系")
	void testSymbolIsPlusFalse() {
		boolean actual = Input.symbolIsPlus('-');
		assertFalse(actual);
	}
	@Test
	@DisplayName("プラスであるString：正常系")
	void testSymbolIsPlusStringTrue() {
		boolean actual = Input.symbolIsPlus("＋");
		assertTrue(actual);
	}
	@Test
	@DisplayName("プラスであるString：異常系")
	void testSymbolIsPlusStringFalse() {
		boolean actual = Input.symbolIsPlus("-");
		assertFalse(actual);
	}
	@Test
	@DisplayName("プラスであるString：異常系")
	void testSymbolIsPlusStringNull() {
		assertThrows(NullPointerException.class, ()-> Input.symbolIsPlus(null));
	}
	//****************************************
	@Test
	@DisplayName("マイナスであるchar：正常系")
	void testSymbolIsMinusTrue() {
		boolean actual = Input.symbolIsMinus('-');
		assertTrue(actual);
	}
	@Test
	@DisplayName("マイナスであるchar：異常系")
	void testSymbolIsMinusFalse() {
		boolean actual = Input.symbolIsMinus('＋');
		assertFalse(actual);
	}
	@Test
	@DisplayName("マイナスであるString：正常系")
	void testSymbolIsMinusStringTrue() {
		boolean actual = Input.symbolIsMinus("-");
		assertTrue(actual);
	}
	@Test
	@DisplayName("マイナスであるString：異常系")
	void testSymbolIsMinusStringFalse() {
		boolean actual = Input.symbolIsMinus("＋");
		assertFalse(actual);
	}
	@Test
	@DisplayName("マイナスであるString：異常系")
	void testSymbolIsMinusStringNull() {
		assertThrows(NullPointerException.class, ()-> Input.symbolIsMinus(null));
	}
	//*****************************************
	@Test
	@DisplayName("記号ではないchar：正常系")
	void testNotSymbolTrue() {
		boolean actual = Input.notSymbol('1');
		assertTrue(actual);
	}
	@Test
	@DisplayName("記号ではないchar：異常系")
	void testNotSymbolFalse() {
		boolean actual = Input.notSymbol('＋');
		assertFalse(actual);
	}
	@Test
	@DisplayName("記号ではないString：正常系")
	void testNotSymbolStringTrue() {
		boolean actual = Input.notSymbol("1");
		assertTrue(actual);
	}
	@Test
	@DisplayName("記号ではないString：異常系")
	void testNotSymbolStringFalse() {
		boolean actual = Input.notSymbol("＋");
		assertFalse(actual);
	}
	@Test
	@DisplayName("記号であるString：異常系")
	void testNotSymbolStringNull() {
		assertThrows(NullPointerException.class, ()-> Input.notSymbol(null));
	}
	//*******************************
	@Test
	@DisplayName("文字列がゼロか確認：正常系")
	void testJudgmentTrue() {
		boolean actual = Input.judgment("0");
		assertTrue(actual);
	}
	@Test
	@DisplayName("文字列がゼロか確認：異常系")
	void testJudgmentFalse() {
		boolean actual = Input.judgment("-");
		assertFalse(actual);
	}
	@Test
	@DisplayName("文字列がゼロか確認：異常系")
	void testJudgmentNull() {
		assertThrows(NullPointerException.class, ()-> Input.judgment(null));
	}
	//*************************************

	@Test
	@DisplayName("配列後尾の文字取得：正常系")
	void testGetLastChar() {
		char expected = '3';
		char[] charList = {'1','2','3'};
		char actual = Input.getLastChar(charList);
		assertEquals(expected,actual);
	}

}
