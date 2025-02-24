package com.example.calculator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteServiceTest {

    private DeleteService deleteService;

    @BeforeEach
    void setUp() {
        deleteService = new DeleteService();
    }

    @Test
    void testIsAllClear() {
        assertTrue(deleteService.isAllClear("ＡＣ"));
        assertFalse(deleteService.isAllClear("Ｃ"));
        assertFalse(deleteService.isAllClear("123"));
    }

    @Test
    void testIsClear() {
        assertTrue(deleteService.isClear("Ｃ"));
        assertFalse(deleteService.isClear("ＡＣ"));
        assertFalse(deleteService.isClear("123"));
    }

    @Test
    void testAllClear() {
        assertEquals("0", deleteService.allClear());
    }

    @Test
    void testClearWithNullFormula() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> deleteService.clear(null));
        assertEquals("引数が不正です。formula=null", exception.getMessage());
    }

    @Test
    void testClearWithEmptyFormula() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> deleteService.clear(""));
        assertEquals("引数が不正です。formula=", exception.getMessage());
    }

    @Test
    void testClearWithSingleCharacterFormula() {
        assertEquals("0", deleteService.clear("1"));
    }

    @Test
    void testClearWithMultipleCharacterFormula() {
        assertEquals("12", deleteService.clear("123"));
    }
}
