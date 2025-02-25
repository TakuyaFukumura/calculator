package com.example.calculator.model;

import com.example.calculator.service.InputService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputTest {

    private final InputService inputService = new InputService();

    @Test
    @DisplayName("式を塊に分ける。最後を取り出してピリオドチェック：正常系")
    void testCheckPeriodTrue() {
        boolean actual = inputService.checkPeriod("1.23×12.345");
        assertTrue(actual);
    }

    @Test
    @DisplayName("式を塊に分ける。最後を取り出してピリオドチェック：異常系")
    void testCheckPeriodFalse() {
        boolean actual = inputService.checkPeriod("12345÷1234");
        assertFalse(actual);
    }

    @Test
    @DisplayName("式を塊に分ける。最後を取り出してピリオドチェック：異常系")
    void testCheckPeriodNull() {
        boolean actual = inputService.checkPeriod(null);
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
    void testInputProcessingTrue() {
        String expected = "5";
        String actual = inputService.inputProcessing("0", "5");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("最初に0.1にする：正常系")
    void testInputProcessingTrue2() {
        String expected = "0.1";
        String actual = inputService.inputProcessing("0", ".");
        actual = inputService.inputProcessing(actual, "1");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("最初に0.1にする：正常系")
    void testInputProcessingTrue3() {
        String expected = "0.1";
        String actual = inputService.inputProcessing("0", ".");
        actual = inputService.inputProcessing(actual, "1");
        actual = inputService.inputProcessing(actual, ".");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("最初に0.1にする：正常系")
    void testInputProcessingTrue4() {
        String expected = "0.11";
        String actual = inputService.inputProcessing("0", ".");
        actual = inputService.inputProcessing(actual, "1");
        actual = inputService.inputProcessing(actual, "1");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("最初に0.1にする：正常系")
    void testInputProcessingTrue5() {
        String expected = "0.1-";
        String actual = inputService.inputProcessing("0", ".");
        actual = inputService.inputProcessing(actual, "1");
        actual = inputService.inputProcessing(actual, "＋");
        actual = inputService.inputProcessing(actual, "-");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("最初に0.1にする：正常系")
    void testInputProcessingTrue6() {
        String expected = "0.1＋";
        String actual = inputService.inputProcessing("0", ".");
        actual = inputService.inputProcessing(actual, "1");
        actual = inputService.inputProcessing(actual, "-");
        actual = inputService.inputProcessing(actual, "-");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("最初に0.1にする：正常系")
    void testInputProcessingTrue7() {
        String expected = "0.1÷-";
        String actual = inputService.inputProcessing("0", ".");
        actual = inputService.inputProcessing(actual, "1");
        actual = inputService.inputProcessing(actual, "÷");
        actual = inputService.inputProcessing(actual, "-");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("入力に応じてdisplay文字を編集して出力：異常系")
    void testInputProcessingNull() {
        assertThrows(NullPointerException.class, () -> inputService.inputProcessing(null, null));
    }

    //******************************************

    @Test
    @DisplayName("入力系文字であるString：正常系")
    void testCheckInputStringTrue() {
        boolean actual = inputService.checkInput("×");
        assertTrue(actual);
    }

    @Test
    @DisplayName("入力系文字であるString：異常系")
    void testCheckInputStringFalse() {
        boolean actual = inputService.checkInput("＝");
        assertFalse(actual);
    }

    @Test
    @DisplayName("入力系文字であるString：異常系")
    void testCheckInputStringNull() {
        assertThrows(NullPointerException.class, () -> inputService.checkInput(null));
    }

    //********************************************
    @Test
    @DisplayName("×または÷であるchar：正常系")
    void testSymbolIsMuDTrue() {
        boolean actual = inputService.isMuD('÷');
        assertTrue(actual);
    }

    @Test
    @DisplayName("×または÷であるchar：異常系")
    void testSymbolIsMuDFalse() {
        boolean actual = inputService.isMuD('-');
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

    @Test
    @DisplayName("マイナスであるString：異常系")
    void testSymbolIsMinusStringNull() {
        assertThrows(NullPointerException.class, () -> inputService.isMinus(null));
    }

    //*****************************************
    @Test
    @DisplayName("記号ではないchar：正常系")
    void testNotSymbolTrue() {
        boolean actual = inputService.isNumeric('1');
        assertTrue(actual);
    }

    @Test
    @DisplayName("記号ではないchar：異常系")
    void testNotSymbolFalse() {
        boolean actual = inputService.isNumeric('＋');
        assertFalse(actual);
    }

    @Test
    @DisplayName("記号ではないString：正常系")
    void testNotSymbolStringTrue() {
        boolean actual = inputService.isNumeric("1");
        assertTrue(actual);
    }

    @Test
    @DisplayName("記号ではないString：異常系")
    void testNotSymbolStringFalse() {
        boolean actual = inputService.isNumeric("＋");
        assertFalse(actual);
    }

    @Test
    @DisplayName("記号であるString：異常系")
    void testNotSymbolStringNull() {
        assertThrows(NullPointerException.class, () -> inputService.isNumeric(null));
    }

    //*************************************

    @Test
    @DisplayName("配列後尾の文字取得：正常系")
    void testGetLastChar() {
        char expected = '3';
        char[] charList = {'1', '2', '3'};
        char actual = inputService.getLastChar(charList);
        assertEquals(expected, actual);
    }

}
