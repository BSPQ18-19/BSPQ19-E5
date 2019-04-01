package com.EasyRestaurantClient.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple UserAuthentication.
 */
public class UserAuthenticationTest
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
        UserAuthentication userAuthentication = new UserAuthentication();
        String result = userAuthentication.login("as","as");
        assertEquals("Incorrect", result);

        String result2 = userAuthentication.login("carlos","numero1234");
        assertEquals("Correct", result2);
    }

    @Test
    public void registerRest(){
        UserAuthentication userAuthentication = new UserAuthentication();

        String result1 = userAuthentication.register("test", "test", "test", "test@test.es");
        assertEquals("User created", result1);

        String result2 = userAuthentication.register("test", "dasds", "test", "test@test.es");
        assertEquals("User already exists", result2);
    }
}
