package com.EasyRestaurantClient.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;


/**
 * Unit test for simple UserAuthentication_API.
 */
public class UserAuthenticationAPITest
{
    /**
     * Test the login with a user that doesn't exist and with an existing one
     */
    @Rule
    public ContiPerfRule i = new ContiPerfRule();
    @Test
    @PerfTest(invocations = 50, threads = 2)
    @Required(max=11000, median = 4500)
    public void loginTest(){
        UserAuthentication_API userAuthenticationAPI = new UserAuthentication_API();
        String result = userAuthenticationAPI.login("as","as");
        assertEquals("Incorrect", result);

        String result2 = userAuthenticationAPI.login("carlos","numero1234");
        assertEquals("Correct", result2);
    }

    /**
     * Tries to register the same client twice, first to check it is done correctly and
     * then to test that it is incorrect if it already exists
     */
    @Test
    @PerfTest(invocations = 10, threads = 10)
    @Required(max = 11000, median=4500)
    public void registerRest(){
        UserAuthentication_API userAuthenticationAPI = new UserAuthentication_API();

        String result2 = userAuthenticationAPI.register("test", "dasds", "test", "test@test.es");
        if (result2.equals("User created")){
            result2 = userAuthenticationAPI.register("test", "dasds", "test", "test@test.es");
            assertEquals("User already exists", result2);
        } else {
            assertEquals("User already exists", result2);
        }

    }
}
