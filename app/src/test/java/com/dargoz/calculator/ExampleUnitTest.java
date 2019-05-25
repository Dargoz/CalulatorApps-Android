package com.dargoz.calculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void cleanDot_isCorrect() {
        String res = CheckAmountText.cleanDotFromString("5.000.000");
        assertEquals( "5000000",res);
        res = CheckAmountText.cleanDotFromString("5.780.000");
        assertEquals( "5780000",res);
        res = CheckAmountText.cleanDotFromString("5.484.400");
        assertEquals( "5484400",res);
    }
}