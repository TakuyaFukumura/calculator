package com.example.calculator.model;

import com.example.calculator.service.InputService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputServiceTest {

    private final InputService inputService = new InputService();

    @Test
    @DisplayName("式を塊に分ける。最後を取り出してピリオドチェック：正常系")
    void testHasPeriodTrue() {
        boolean actual = inputService.hasPeriod("1.23×12.345");
        assertTrue(actual);
    }

    @Test
    @DisplayName("式を塊に分ける。最後を取り出してピリオドチェック：異常系")
    void testHasPeriodFalse() {
        boolean actual = inputService.hasPeriod("12345÷1234");
        assertFalse(actual);
    }

    @Test
    @DisplayName("式を塊に分ける。最後を取り出してピリオドチェック：異常系")
    void testHasPeriodNull() {
        boolean actual = inputService.hasPeriod(null);
        assertFalse(actual);
    }

    //*********************************************
    @Test
    @DisplayName("ピリオドが含まれているかチェック：正常系")
    void testIsPeriodTrue() {
        boolean actual = inputService.isPeriod("1.234");
        assertTrue(actual);
    }

    @Test
    @DisplayName("ピリオドが含まれているかチェック：異常系")
    void testIsPeriodFalse() {
        boolean actual = inputService.isPeriod("12345");
        assertFalse(actual);
    }

    @Test
    @DisplayName("ピリオドが含まれているかチェック：異常系")
    void testIsPeriodNull() {
        boolean actual = inputService.isPeriod(null);
        assertFalse(actual);
    }

    //*********************************************
    @Test
    @DisplayName("入力に応じてdisplay文字を編集して出力：正常系")
    void testBuildFormulaTrue() {
        String expected = "5";
        String actual = inputService.buildFormula("0", "5");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("最初に0.1にする：正常系")
    void testBuildFormulaTrue2() {
        String expected = "0.1";
        String actual = inputService.buildFormula("0", ".");
        actual = inputService.buildFormula(actual, "1");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("最初に0.1にする：正常系")
    void testBuildFormulaTrue3() {
        String expected = "0.1";
        String actual = inputService.buildFormula("0", ".");
        actual = inputService.buildFormula(actual, "1");
        actual = inputService.buildFormula(actual, ".");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("最初に0.1にする：正常系")
    void testBuildFormulaTrue4() {
        String expected = "0.11";
        String actual = inputService.buildFormula("0", ".");
        actual = inputService.buildFormula(actual, "1");
        actual = inputService.buildFormula(actual, "1");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("最初に0.1にする：正常系")
    void testBuildFormulaTrue5() {
        String expected = "0.1-";
        String actual = inputService.buildFormula("0", ".");
        actual = inputService.buildFormula(actual, "1");
        actual = inputService.buildFormula(actual, "＋");
        actual = inputService.buildFormula(actual, "-");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("最初に0.1にする：正常系")
    void testBuildFormulaTrue6() {
        String expected = "0.1＋";
        String actual = inputService.buildFormula("0", ".");
        actual = inputService.buildFormula(actual, "1");
        actual = inputService.buildFormula(actual, "-");
        actual = inputService.buildFormula(actual, "-");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("最初に0.1にする：正常系")
    void testBuildFormulaTrue7() {
        String expected = "0.1÷-";
        String actual = inputService.buildFormula("0", ".");
        actual = inputService.buildFormula(actual, "1");
        actual = inputService.buildFormula(actual, "÷");
        actual = inputService.buildFormula(actual, "-");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("入力に応じてdisplay文字を編集して出力：異常系")
    void testBuildFormulaNull() {
        assertThrows(NullPointerException.class, () -> inputService.buildFormula(null, null));
    }

    //******************************************

    @Test
    @DisplayName("入力系文字であるString：正常系")
    void testCheckInputStringTrue() {
        boolean actual = inputService.isInput("×");
        assertTrue(actual);
    }

    @Test
    @DisplayName("入力系文字であるString：異常系")
    void testCheckInputStringFalse() {
        boolean actual = inputService.isInput("＝");
        assertFalse(actual);
    }

    @Test
    @DisplayName("入力系文字であるString：異常系")
    void testCheckInputStringNull() {
        assertThrows(NullPointerException.class, () -> inputService.isInput(null));
    }

    //********************************************
    @Test
    @DisplayName("×または÷であるchar：正常系")
    void testSymbolIsMuDTrue() {
        boolean actual = inputService.isMultiplicationOrDivision('÷');
        assertTrue(actual);
    }

    @Test
    @DisplayName("×または÷であるchar：異常系")
    void testSymbolIsMuDFalse() {
        boolean actual = inputService.isMultiplicationOrDivision('-');
        assertFalse(actual);
    }

    //********************************************
    @Test
    @DisplayName("プラスであるchar：正常系")
    void testSymbolIsPlusTrue() {
        boolean actual = inputService.isPlus('＋');
        assertTrue(actual);
    }

    @Test
    @DisplayName("プラスであるchar：異常系")
    void testSymbolIsPlusFalse() {
        boolean actual = inputService.isPlus('-');
        assertFalse(actual);
    }
    //****************************************
    @Test
    @DisplayName("マイナスであるchar：正常系")
    void testSymbolIsMinusTrue() {
        boolean actual = inputService.isMinus('-');
        assertTrue(actual);
    }

    @Test
    @DisplayName("マイナスであるchar：異常系")
    void testSymbolIsMinusFalse() {
        boolean actual = inputService.isMinus('＋');
        assertFalse(actual);
    }

    @Test
    @DisplayName("マイナスであるString：正常系")
    void testSymbolIsMinusStringTrue() {
        boolean actual = inputService.isMinus("-");
        assertTrue(actual);
    }

    @Test
    @DisplayName("マイナスであるString：異常系")
    void testSymbolIsMinusStringFalse() {
        boolean actual = inputService.isMinus("＋");
        assertFalse(actual);
    }

    //*************************************

    @Test
    @DisplayName("配列後尾の文字取得：正常系")
    void testGetLastChar() {
        char expected = '3';
        String str = "123";
        char actual = inputService.getLastChar(str);
        assertEquals(expected, actual);
    }
}
