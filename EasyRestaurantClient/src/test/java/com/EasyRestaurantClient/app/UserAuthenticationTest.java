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
     * Test the login with a user that doesn't exist and with an existing one
     */
    @Test
    public void loginTest(){
        UserAuthentication userAuthentication = new UserAuthentication();
        String result = userAuthentication.login("as","as");
        assertEquals("Incorrect", result);

        String result2 = userAuthentication.login("carlos","numero1234");
        assertEquals("Correct", result2);
    }

    /**
     * Tries to register the same client twice, first to check it is done correctly and
     * then to test that it is incorrect if it already exists
     */
    @Test
    public void registerRest(){
        UserAuthentication userAuthentication = new UserAuthentication();

        String result1 = userAuthentication.register("test", "test", "test", "test@test.es");
        assertEquals("User created", result1);

        String result2 = userAuthentication.register("test", "dasds", "test", "test@test.es");
        assertEquals("User already exists", result2);
    }
}
