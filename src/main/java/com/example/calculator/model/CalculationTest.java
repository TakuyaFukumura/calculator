package com.example.calculator.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CalculationTest {


    @Test
    void testFigureLengthUpPoint() {
        String expected = "100";
        String actual = Calculation.figureLengthUpPoint("100.1234");
        assertEquals(expected, actual);
    }

    @Test
    void testFigureLengthUpPoint2() {
        String expected = "0";
        String actual = Calculation.figureLengthUpPoint("0.100");
        assertEquals(expected, actual);
    }

    @Test
    void testCreate() {
        Calculation calculation = new Calculation();
    }

    @Test
    void testGetLastArithmetic() {
        String expected = "×3";
        String actual = Calculation.getLastArithmetic("3×3");
        assertEquals(expected, actual);
    }

    @Test
    void testGetLastArithmetic2() {
        String expected = "";
        String actual = Calculation.getLastArithmetic("33");
        assertEquals(expected, actual);
    }

    @Test
    void testGetLastArithmetic3() {
        String expected = "×-4";
        String actual = Calculation.getLastArithmetic("-4×-4×-4");
        assertEquals(expected, actual);
    }

    @Test
    void testConversions() {
        BigDecimal expected = new BigDecimal("-1.3");
        BigDecimal actual = Calculation.conversions("-1.3");
        assertEquals(expected, actual);
    }

    @Test
    void testCalculationProcessing() {
        String expected = "9";
        String actual = Calculation.calculationProcessing("3×3");
        assertEquals(expected, actual);
    }

    @Test
    void testCalculationProcessing2() {
        String expected = "64";
        String actual = Calculation.calculationProcessing("4×-4×-4");
        assertEquals(expected, actual);
    }

    @Test
    void testCalculationProcessing3() {
        String expected = "1";
        String actual = Calculation.calculationProcessing("1");
        assertEquals(expected, actual);
    }

    @Test
    void testCalculationProcessing4() {
        String expected = "4×-4×-";
        String actual = Calculation.calculationProcessing("4×-4×-");
        assertEquals(expected, actual);
    }

    @Test
    void testCalculationProcessing5() {
        String expected = "0";
        String actual = Calculation.calculationProcessing("123456789102-123456789102");
        assertEquals(expected, actual);
    }

    @Test
    void testCalculationProcessing6() {
        String expected = "111111111111";
        String actual = Calculation.calculationProcessing("666666666666-555555555555");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("計算の実行足し算：正常系")
    void testCalculate() {
        String expected = "6.0";
        String[] str = {"3.0", "＋", "3.0"};
        String actual = Calculation.calculate(str);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("計算の実行足し算2：正常系")
    void testCalculate2() {
        String expected = "9.0";
        String[] str = {"3.0", "＋", "3.0", "＋", "3.0"};
        String actual = Calculation.calculate(str);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("計算の実行足し算3：正常系")
    void testCalculate3() {
        String expected = "6.0";
        String[] str = {"-", "3.0", "＋", "3.0", "＋", "6.0"};
        String actual = Calculation.calculate(str);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("計算の実行引き算：正常系")
    void testCalculate4() {
        String expected = "78";
        String[] str = {"81", "-", "3"};
        String actual = Calculation.calculate(str);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("計算の実行引き算：正常系")
    void testCalculate5() {
        String expected = "-81";
        String[] str = {"-", "81"};
        String actual = Calculation.calculate(str);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("計算の実行引き算：正常系")
    void testCalculate6() {
        String expected = "0";
        String[] str = {"0"};
        String actual = Calculation.calculate(str);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "0,1,-,1",
            "0,1,-,1"
    })
    @DisplayName("まとまりを３つづつ計算する")
    void testCalculate7(String expected, String num1, String formula, String num2) {
        String[] str = {num1, formula, num2};
        String actual = Calculation.calculate(str);
        assertEquals(expected, actual);
    }

    @Test
    void testCalculateSwitchBD7() {
        BigDecimal expected = new BigDecimal("0");
        BigDecimal actual = Calculation.calculateSwitch(new BigDecimal("2"), 7, new BigDecimal("2"));
        assertEquals(expected, actual);
    }

    @Test
    void testCalculateSwitchBD4() {
        BigDecimal expected = new BigDecimal("1");
        BigDecimal actual = Calculation.calculateSwitch(new BigDecimal("3"), 4, new BigDecimal("3"));
        assertEquals(expected, actual);
    }

    @Test
    void testCalculateSwitchBD6() {
        BigDecimal expected = new BigDecimal("-2");
        BigDecimal actual = Calculation.calculateSwitch(new BigDecimal("6"), 6, new BigDecimal("3"));
        assertEquals(expected, actual);
    }

    @Test
    void testCalculateSwitchBD62() {
        BigDecimal expected = new BigDecimal("-3.33333333333");
        BigDecimal actual = Calculation.calculateSwitch(new BigDecimal("10"), 6, new BigDecimal("3"));
        assertEquals(expected, actual);
    }

    @Test
    void testCalculateSwitchBD63() {
        BigDecimal expected = new BigDecimal("1234567891023");
        BigDecimal actual = Calculation.calculateSwitch(new BigDecimal("10"), 6, new BigDecimal("0"));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("無限小数")
    void testDivision2() {
        BigDecimal expected = new BigDecimal("3.33333333333");
        BigDecimal actual = Calculation.division(new BigDecimal("10"), new BigDecimal("3"));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("ゼロ除算")
    void testDivision3() {
        BigDecimal expected = new BigDecimal("1234567891023");
        BigDecimal actual = Calculation.division(new BigDecimal("10"), new BigDecimal("0"));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("マイナス無限小数で上側に数字ある時")
    void testDivision4() {
        BigDecimal expected = new BigDecimal("-333.333333333");
        BigDecimal actual = Calculation.division(new BigDecimal("1000"), new BigDecimal("-3"));
        assertEquals(expected, actual);
    }

    @Test
    void testSplitFormula() {
        String[] expected = {"3", "×", "3"};
        String[] actual = Calculation.splitFormula("3×3");
        assertArrayEquals(expected, actual);
    }

    @Test
    void testSplitFormula2() {
        String[] expected = {"81", "-", "3"};
        String[] actual = Calculation.splitFormula("81-3");
        assertArrayEquals(expected, actual);
    }

    @Test
    void testSplitFormula3() {
        String[] expected = {"3", "×-", "3"};
        String[] actual = Calculation.splitFormula("3×-3");
        assertArrayEquals(expected, actual);
    }

    @Test
    void testJudgmentOperator() {
        int expected = 1;
        int actual = Calculation.judgmentOperator("＋");
        assertEquals(expected, actual);
    }

    @Test
    void testJudgmentOperator4() {
        int expected = 4;
        int actual = Calculation.judgmentOperator("÷");
        assertEquals(expected, actual);
    }

    @Test
    void testJudgmentOperator5() {
        int expected = 5;
        int actual = Calculation.judgmentOperator("×-");
        assertEquals(expected, actual);
    }

    @Test
    void testJudgmentOperator6() {
        int expected = 6;
        int actual = Calculation.judgmentOperator("÷-");
        assertEquals(expected, actual);
    }

    @Test
    void testJudgmentOperator7() {
        int expected = 0;
        int actual = Calculation.judgmentOperator("1");
        assertEquals(expected, actual);
    }

    @Test
    void testCheckCalculation() {
        boolean actual = Calculation.checkCalculation("＝");
        assertTrue(actual);
    }

    @Test
    void testCheckCalculationNull() {
        boolean actual = Calculation.checkCalculation(null);
        assertFalse(actual);
    }

    @Test
    void testCheckCalculation2() {
        boolean actual = Calculation.checkCalculation("2");
        assertFalse(actual);
    }

    @Test
    void testSymbolIsEqual() {
        boolean actual = Calculation.symbolIsEqual("＝");
        assertTrue(actual);
    }

    @Test
    void testSymbolIsEqual2() {
        boolean actual = Calculation.symbolIsEqual("1");
        assertFalse(actual);
    }

    @Test
    void testSymbolIsEqualNull() {
        boolean actual = Calculation.symbolIsEqual(null);
        assertFalse(actual);
    }

    @Test
    void testJudgmentNumGString() {
        boolean actual = Calculation.judgmentNumG("9");
        assertTrue(actual);
    }

    @Test
    void testJudgmentNumGStringNull() {
        boolean actual = Calculation.judgmentNumG(null);
        assertFalse(actual);
    }

    @Test
    void testJudgmentNumGChar() {
        boolean actual = Calculation.judgmentNumG('9');
        assertTrue(actual);
    }

    @Test
    void testJudgmentSymbolGString() {
        boolean actual = Calculation.judgmentSymbolG("＋");
        assertTrue(actual);
    }

    @Test
    void testJudgmentSymbolGStringNull() {
        boolean actual = Calculation.judgmentSymbolG(null);
        assertFalse(actual);
    }

    @Test
    void testJudgmentSymbolGChar() {
        boolean actual = Calculation.judgmentSymbolG('＋');
        assertTrue(actual);
    }
}
