package com.example.calculator.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonUtilTest {

    /*
     * checkNumberOfDigits
     * */
    @Test
    @DisplayName("インスタンス生成：正常系")
    void testCreateTrue() {
        CommonUtil test = new CommonUtil();
    }

    @Test
    @DisplayName("文字列をchar配列にする：正常系")
    void testSplitTrue() {
        char[] expected = {'1', '2', '3'};
        char[] actual = CommonUtil.split("123");
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("文字列をchar配列にする：正常系")
    void testCheckNumberOfDigits() {
        int expected = 3;
        int actual = CommonUtil.checkNumberOfDigits("-3.55");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("文字列をchar配列にする：正常系")
    void testCheckNumberOfDigits2() {
        int expected = 0;
        int actual = CommonUtil.checkNumberOfDigits("");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("文字列をchar配列にする：正常系")
    void testCheckNumberOfDigits3() {
        int expected = 4;
        int actual = CommonUtil.checkNumberOfDigits("0.004");
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
    void testJudgmentNumG() {
        boolean actual = CommonUtil.judgmentNumG("0.004-1234567891023");
        assertFalse(actual);
    }

    @Test
    @DisplayName("数字グループかどうか：正常系")
    void testJudgmentNumG2() {
        boolean actual = CommonUtil.judgmentNumG("0.004");
        assertTrue(actual);
    }

    @Test
    @DisplayName("数字グループかどうか：異常系")
    void testJudgmentNumG3() {
        boolean actual = CommonUtil.judgmentNumG(null);
        assertFalse(actual);
    }

}
