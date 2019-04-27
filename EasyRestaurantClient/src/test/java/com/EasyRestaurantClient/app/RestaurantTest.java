package com.EasyRestaurantClient.app;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;

public class RestaurantTest {

    /**
     * Tries to retrive existing restaurants
     */
    @Rule
    public ContiPerfRule i = new ContiPerfRule();
    @Test
    @PerfTest(invocations = 20, threads = 5)
    @Required(max=500, median = 250)
    public void testRestaurants() {
        Restaurants restaurants = new Restaurants();
        JSONObject filters = new JSONObject();
        JSONArray jsonArray = restaurants.restaurant_list(filters);
        assertNotNull(jsonArray);
    }
}
