package com.example.calculator.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OldCalculatedDataTest {

    @Test
    void testOldCalculatedData() {
        String expected = "0";
        OldCalculatedData oldCalculatedData = new OldCalculatedData();
        oldCalculatedData.setOldResult("0");
        String actual = oldCalculatedData.getOldResult();
        assertEquals(expected, actual);
    }

    @Test
    void testOldCalculatedData2() {
        String expected = "-3";
        OldCalculatedData oldCalculatedData = new OldCalculatedData("0", "-3");
        String actual = oldCalculatedData.getOldOperator();
        assertEquals(expected, actual);
    }

}
