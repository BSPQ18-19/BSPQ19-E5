package com.EasyRestaurantClient.app;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RestaurantTest {

    /**
     * Tries to retrive existing restaurants
     */
    @Test
    public void testRestaurants() {
        Restaurants restaurants = new Restaurants();
        JSONObject filters = new JSONObject();
        JSONArray jsonArray = restaurants.restaurant_list(filters);
        assertNotNull(jsonArray);
    }
}
