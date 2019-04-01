package com.EasyRestaurantClient.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void loginTest(){
        App app = new App();
        String result = app.login("as","as");
        assertEquals("Incorrect", result);

        String result2 = app.login("carlos","numero1234");
        assertEquals("Correct", result2);
    }
}
