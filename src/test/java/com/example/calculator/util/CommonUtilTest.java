package com.example.calculator.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonUtilTest {

    @Test
    @DisplayName("文字列をchar配列にする：正常系")
    void testCheckNumberOfDigits() {
        int expected = 3;
        int actual = CommonUtil.countDigits("-3.55");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("文字列をchar配列にする：正常系")
    void testCheckNumberOfDigits2() {
        int expected = 0;
        int actual = CommonUtil.countDigits("");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("文字列をchar配列にする：正常系")
    void testCheckNumberOfDigits3() {
        int expected = 4;
        int actual = CommonUtil.countDigits("0.004");
        assertEquals(expected, actual);
    }

//	@Test
//	@DisplayName("文字列をchar配列にする：異常系")
//	void testSplitFalse() {
//		char[] expected = {'1','2','3'};
//		char[] actual = Input.split("123");
//		assertFalse(Arrays.equals(expected, actual));
//	}

    @Test
    @DisplayName("入力継続できるか桁数確認：正常系")
    void testCheckNumber() {
        boolean actual = CommonUtil.checkNumber("0.004-12345678910");
        assertTrue(actual);
    }

    @Test
    @DisplayName("入力継続できるか桁数確認：異常系")
    void testCheckNumber2() {
        boolean actual = CommonUtil.checkNumber("0.004-123456789102");
        assertFalse(actual);
    }

    @Test
    @DisplayName("数字グループかどうか：異常系")
    void testIsNumeric() {
        boolean actual = CommonUtil.isNumeric("0.004-1234567891023");
        assertFalse(actual);
    }

    @Test
    @DisplayName("数字グループかどうか：正常系")
    void testIsNumeric2() {
        boolean actual = CommonUtil.isNumeric("0.004");
        assertTrue(actual);
    }

    @Test
    @DisplayName("数字グループかどうか：異常系")
    void testIsNumeric3() {
        boolean actual = CommonUtil.isNumeric(null);
        assertFalse(actual);
    }

}
