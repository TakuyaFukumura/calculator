package com.example.calculator.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteServiceTest {

    private final DeleteService deleteService = new DeleteService();

    @Test
    @DisplayName("入力に応じてdisplay文字を編集して出力：正常系")
    void testDeleteProcessingTrue() {
        String expected = "５";
        String actual = deleteService.handleFormulaResetOrDelete("５４", "Ｃ");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("入力に応じてdisplay文字を編集して出力：正常系")
    void testDeleteProcessingTrue2() {
        String expected = "0";
        String actual = deleteService.handleFormulaResetOrDelete("54", "ＡＣ");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("入力に応じてdisplay文字を編集して出力：正常系")
    void testDeleteProcessingTrue3() {
        String expected = "0";
        String actual = deleteService.handleFormulaResetOrDelete("5", "Ｃ");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("入力に応じてdisplay文字を編集して出力")
    void testDeleteProcessing4() {
        String expected = "5";
        String actual = deleteService.handleFormulaResetOrDelete("5", "test");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("入力に応じてdisplay文字を編集して出力：異常系")
    void testDeleteProcessingNull() {
        String expected = null;
        String actual = deleteService.handleFormulaResetOrDelete(null, null);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("入力に応じてdisplay文字を編集して出力：異常系")
    void testDeleteProcessingNull2() {
        String expected = null;
        String actual = deleteService.handleFormulaResetOrDelete(null, "");
        assertEquals(expected, actual);
    }


    //*****************************************
    @Test
    @DisplayName("Delete処理であるＣ：正常系")
    void testCheckDeleteTrue() {
        boolean actual = deleteService.isDeleteOperation("Ｃ");
        assertTrue(actual);
    }

    @Test
    @DisplayName("Delete処理であるＡＣ：正常系")
    void testCheckDeleteTrue2() {
        boolean actual = deleteService.isDeleteOperation("ＡＣ");
        assertTrue(actual);
    }

    @Test
    @DisplayName("Delete処理である：異常系")
    void testCheckDeleteFalse() {
        boolean actual = deleteService.isDeleteOperation("＝");
        assertFalse(actual);
    }

    @Test
    @DisplayName("Delete処理である：異常系")
    void testCheckDeleteNull() {
        boolean actual = deleteService.isDeleteOperation(null);
        assertFalse(actual);
    }
}
