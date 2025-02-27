package com.example.calculator.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CalculationServiceTest {

    private final CalculationService calculationService = new CalculationService();

    @Test
    void testExtractIntegerPart() {
        String expected = "100";
        String actual = calculationService.extractIntegerPart("100.1234");
        assertEquals(expected, actual);
    }

    @Test
    void testExtractIntegerPart2() {
        String expected = "0";
        String actual = calculationService.extractIntegerPart("0.100");
        assertEquals(expected, actual);
    }

    @Test
    void testGetLastArithmetic() {
        String expected = "×3";
        String actual = calculationService.getLastArithmetic("3×3");
        assertEquals(expected, actual);
    }

    @Test
    void testGetLastArithmetic2() {
        String expected = "";
        String actual = calculationService.getLastArithmetic("33");
        assertEquals(expected, actual);
    }

    @Test
    void testGetLastArithmetic3() {
        String expected = "×-4";
        String actual = calculationService.getLastArithmetic("-4×-4×-4");
        assertEquals(expected, actual);
    }

    @Test
    void testCalculationProcessing() {
        String expected = "9";
        String actual = calculationService.calculationProcessing("3×3");
        assertEquals(expected, actual);
    }

    @Test
    void testCalculationProcessing2() {
        String expected = "64";
        String actual = calculationService.calculationProcessing("4×-4×-4");
        assertEquals(expected, actual);
    }

    @Test
    void testCalculationProcessing3() {
        String expected = "1";
        String actual = calculationService.calculationProcessing("1");
        assertEquals(expected, actual);
    }

    @Test
    void testCalculationProcessing4() {
        String expected = "4×-4×-";
        String actual = calculationService.calculationProcessing("4×-4×-");
        assertEquals(expected, actual);
    }

    @Test
    void testCalculationProcessing5() {
        String expected = "0";
        String actual = calculationService.calculationProcessing("123456789102-123456789102");
        assertEquals(expected, actual);
    }

    @Test
    void testCalculationProcessing6() {
        String expected = "111111111111";
        String actual = calculationService.calculationProcessing("666666666666-555555555555");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("計算の実行足し算：正常系")
    void testCalculate() {
        String expected = "6.0";
        String[] str = {"3.0", "＋", "3.0"};
        String actual = calculationService.calculate(str);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("計算の実行足し算2：正常系")
    void testCalculate2() {
        String expected = "9.0";
        String[] str = {"3.0", "＋", "3.0", "＋", "3.0"};
        String actual = calculationService.calculate(str);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("計算の実行足し算3：正常系")
    void testCalculate3() {
        String expected = "6.0";
        String[] str = {"-", "3.0", "＋", "3.0", "＋", "6.0"};
        String actual = calculationService.calculate(str);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("計算の実行引き算：正常系")
    void testCalculate4() {
        String expected = "78";
        String[] str = {"81", "-", "3"};
        String actual = calculationService.calculate(str);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("計算の実行引き算：正常系")
    void testCalculate5() {
        String expected = "-81";
        String[] str = {"-", "81"};
        String actual = calculationService.calculate(str);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("計算の実行引き算：正常系")
    void testCalculate6() {
        String expected = "0";
        String[] str = {"0"};
        String actual = calculationService.calculate(str);
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
        String actual = calculationService.calculate(str);
        assertEquals(expected, actual);
    }

    @Test
    void testCalculateSwitchBD7() {
        BigDecimal expected = new BigDecimal("0");
        BigDecimal actual = calculationService.calculateSwitch(new BigDecimal("2"), 7, new BigDecimal("2"));
        assertEquals(expected, actual);
    }

    @Test
    void testCalculateSwitchBD4() {
        BigDecimal expected = new BigDecimal("1");
        BigDecimal actual = calculationService.calculateSwitch(new BigDecimal("3"), 4, new BigDecimal("3"));
        assertEquals(expected, actual);
    }

    @Test
    void testCalculateSwitchBD6() {
        BigDecimal expected = new BigDecimal("-2");
        BigDecimal actual = calculationService.calculateSwitch(new BigDecimal("6"), 6, new BigDecimal("3"));
        assertEquals(expected, actual);
    }

    @Test
    void testCalculateSwitchBD62() {
        BigDecimal expected = new BigDecimal("-3.33333333333");
        BigDecimal actual = calculationService.calculateSwitch(new BigDecimal("10"), 6, new BigDecimal("3"));
        assertEquals(expected, actual);
    }

    @Test
    void testCalculateSwitchBD63() {
        BigDecimal expected = new BigDecimal("1234567891023");
        BigDecimal actual = calculationService.calculateSwitch(new BigDecimal("10"), 6, new BigDecimal("0"));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("無限小数")
    void testDivision2() {
        BigDecimal expected = new BigDecimal("3.33333333333");
        BigDecimal actual = calculationService.division(new BigDecimal("10"), new BigDecimal("3"));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("ゼロ除算")
    void testDivision3() {
        BigDecimal expected = new BigDecimal("1234567891023");
        BigDecimal actual = calculationService.division(new BigDecimal("10"), new BigDecimal("0"));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("マイナス無限小数で上側に数字ある時")
    void testDivision4() {
        BigDecimal expected = new BigDecimal("-333.333333333");
        BigDecimal actual = calculationService.division(new BigDecimal("1000"), new BigDecimal("-3"));
        assertEquals(expected, actual);
    }

    @Test
    void testSplitFormula() {
        String[] expected = {"3", "×", "3"};
        String[] actual = calculationService.splitFormula("3×3");
        assertArrayEquals(expected, actual);
    }

    @Test
    void testSplitFormula2() {
        String[] expected = {"81", "-", "3"};
        String[] actual = calculationService.splitFormula("81-3");
        assertArrayEquals(expected, actual);
    }

    @Test
    void testSplitFormula3() {
        String[] expected = {"3", "×-", "3"};
        String[] actual = calculationService.splitFormula("3×-3");
        assertArrayEquals(expected, actual);
    }

    @Test
    void testGetOperatorType() {
        int expected = 1;
        int actual = calculationService.getOperatorType("＋");
        assertEquals(expected, actual);
    }

    @Test
    void testGetOperatorType4() {
        int expected = 4;
        int actual = calculationService.getOperatorType("÷");
        assertEquals(expected, actual);
    }

    @Test
    void testGetOperatorType5() {
        int expected = 5;
        int actual = calculationService.getOperatorType("×-");
        assertEquals(expected, actual);
    }

    @Test
    void testGetOperatorType6() {
        int expected = 6;
        int actual = calculationService.getOperatorType("÷-");
        assertEquals(expected, actual);
    }

    @Test
    void testGetOperatorType7() {
        int expected = 0;
        int actual = calculationService.getOperatorType("1");
        assertEquals(expected, actual);
    }
}
