package com.example.calculator.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculationHistoryTest {

    @Test
    void testOldCalculatedData() {
        String expected = "0";
        CalculationHistory calculationHistory = new CalculationHistory();
        calculationHistory.setOldResult("0");
        String actual = calculationHistory.getOldResult();
        assertEquals(expected, actual);
    }

    @Test
    void testOldCalculatedData2() {
        String expected = "-3";
        CalculationHistory calculationHistory = new CalculationHistory("0", "-3");
        String actual = calculationHistory.getOldOperator();
        assertEquals(expected, actual);
    }

}
