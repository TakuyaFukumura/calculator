package com.example.calculator.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OldCalculatedDataTest {

	@Test
	void testOldCalculatedData() {
		String expected = "0";
		OldCalculatedData oldCalculatedData = new OldCalculatedData();
		oldCalculatedData.setOldResult("0");
		String actual = oldCalculatedData.getOldResult();
		assertEquals(expected,actual);
	}
	@Test
	void testOldCalculatedData2() {
		String expected = "-3";
		OldCalculatedData oldCalculatedData = new OldCalculatedData("0", "-3");
		String actual = oldCalculatedData.getOldOperator();
		assertEquals(expected,actual);
	}

}
