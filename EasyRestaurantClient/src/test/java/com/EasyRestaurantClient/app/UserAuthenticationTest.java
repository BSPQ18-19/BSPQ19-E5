package com.EasyRestaurantClient.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;


/**
 * Unit test for simple UserAuthentication.
 */
public class UserAuthenticationTest
{
    /**
     * Test the login with a user that doesn't exist and with an existing one
     */
    @Rule
    public ContiPerfRule i = new ContiPerfRule();
    @Test
    @PerfTest(invocations = 100, threads = 5)
    @Required(max=500, median = 250)
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
    @PerfTest(invocations = 10, threads = 10)
    @Required(max = 200, median=150)
    public void registerRest(){
        UserAuthentication userAuthentication = new UserAuthentication();

        String result2 = userAuthentication.register("test", "dasds", "test", "test@test.es");
        if (result2.equals("User created")){
            result2 = userAuthentication.register("test", "dasds", "test", "test@test.es");
            assertEquals("User already exists", result2);
        } else {
            assertEquals("User already exists", result2);
        }

    }
}
