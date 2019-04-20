package com.EasyRestaurantClient.app;

import org.json.JSONArray;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RestaurantTest {

    /**
     * Tries to retrive existing restaurants
     */
    @Test
    public void testRestaurants() {
        Restaurants restaurants = new Restaurants();
        JSONArray jsonArray = restaurants.restaurant_list();
        assertNotNull(jsonArray);
    }
}
