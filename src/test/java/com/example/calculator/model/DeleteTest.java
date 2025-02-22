package com.example.calculator.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteTest {

    @Test
    @DisplayName("インスタンス生成：正常系")
    void testCreateTrue() {
        Delete test = new Delete();
    }
    /* まだ書いていないメソッド
     *
     *
     * */

    @Test
    @DisplayName("入力に応じてdisplay文字を編集して出力：正常系")
    void testDeleteProcessingTrue() {
        String expected = "５";
        String actual = Delete.deleteProcessing("５４", "Ｃ");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("入力に応じてdisplay文字を編集して出力：正常系")
    void testDeleteProcessingTrue2() {
        String expected = "0";
        String actual = Delete.deleteProcessing("54", "ＡＣ");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("入力に応じてdisplay文字を編集して出力：正常系")
    void testDeleteProcessingTrue3() {
        String expected = "0";
        String actual = Delete.deleteProcessing("5", "Ｃ");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("入力に応じてdisplay文字を編集して出力")
    void testDeleteProcessing4() {
        String expected = "5";
        String actual = Delete.deleteProcessing("5", "test");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("入力に応じてdisplay文字を編集して出力：異常系")
    void testDeleteProcessingNull() {
        String expected = null;
        String actual = Delete.deleteProcessing(null, null);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("入力に応じてdisplay文字を編集して出力：異常系")
    void testDeleteProcessingNull2() {
        String expected = null;
        String actual = Delete.deleteProcessing(null, "");
        assertEquals(expected, actual);
    }


    //*****************************************
    @Test
    @DisplayName("Delete処理であるＣ：正常系")
    void testCheckDeleteTrue() {
        boolean actual = Delete.checkDelete("Ｃ");
        assertTrue(actual);
    }

    @Test
    @DisplayName("Delete処理であるＡＣ：正常系")
    void testCheckDeleteTrue2() {
        boolean actual = Delete.checkDelete("ＡＣ");
        assertTrue(actual);
    }

    @Test
    @DisplayName("Delete処理である：異常系")
    void testCheckDeleteFalse() {
        boolean actual = Delete.checkDelete("＝");
        assertFalse(actual);
    }

    @Test
    @DisplayName("Delete処理である：異常系")
    void testCheckDeleteNull() {
        //assertThrows(NullPointerException.class, ()-> Delete.checkDelete(null));
        boolean actual = Delete.checkDelete(null);
        assertFalse(actual);
    }

    //****************************************

    @Test
    @DisplayName("ＣであるString：正常系")
    void testIsSingleCStringTrue() {
        boolean actual = Delete.isSingleC("Ｃ");
        assertTrue(actual);
    }

    @Test
    @DisplayName("ＣであるString：異常系")
    void testIsSingleCStringFalse() {
        boolean actual = Delete.isSingleC("＝");
        assertFalse(actual);
    }

    @Test
    @DisplayName("ＣであるString：異常系")
    void testIsSingleCStringNull() {
        assertThrows(NullPointerException.class, () -> Delete.isSingleC(null));
    }

    //****************************************
    @Test
    @DisplayName("ＡＣであるString：正常系")
    void testIsExactAcStringTrue() {
        boolean actual = Delete.isExactAC("ＡＣ");
        assertTrue(actual);
    }

    @Test
    @DisplayName("ＡＣであるString：異常系")
    void testIsExactAcStringFalse() {
        boolean actual = Delete.isExactAC("＝");
        assertFalse(actual);
    }

    @Test
    @DisplayName("ＡＣであるString：異常系")
    void testIsExactAcStringNull() {
        assertThrows(NullPointerException.class, () -> Delete.isExactAC(null));
    }

    //****************************************
    //nullチェックしていない
    @Test
    @DisplayName("2文字以上かどうか：正常系")
    void testIsLongerThanOneTrue() {
        boolean actual = Delete.isLongerThanOne("22");
        assertTrue(actual);
    }

    @Test
    @DisplayName("2文字以上かどうか：異常系")
    void testIsLongerThanOneFalse() {
        boolean actual = Delete.isLongerThanOne("2");
        assertFalse(actual);
    }

    @Test
    @DisplayName("最後尾の文字削除：正常系")
    void testDeleteLastChar() {
        String expected = "123";
        String str = "1234";
        String actual = Delete.deleteLastChar(str);
        assertEquals(expected, actual);
    }


}
