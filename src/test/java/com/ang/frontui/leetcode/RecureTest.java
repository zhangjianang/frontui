package com.ang.frontui.leetcode;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class RecureTest {
    Recure recure;

    @BeforeEach
    void setUp() {
        recure = new Recure();
    }

    @Test
    void testNumDecodings() {
        assertEquals(2, recure.numDecodings("12"));
    }

    @Test
    void testNumDecodings1() {
        assertEquals(3, recure.numDecodings("226"));
    }

    @Test
    void testNumDecodings2() {
        assertEquals(0, recure.numDecodings("0"));
    }

    @Test
    void testNumDecodings3() {
        assertEquals(1, recure.numDecodings("1"));
    }

    @Test
    void testNumDecodings4() {
//        assertEquals(1, recure.numDecodings("2"));
        assertEquals(1, recure.numDecodings("111111111111111111111111111111111111111111111"));
    }


    @Test
    void trimeTest(){
//        assertEquals("",recure.trimeZero("0"));
//        assertEquals("11",recure.trimeZero("00011"));
//        assertEquals("110",recure.trimeZero("110"));
    }

}
