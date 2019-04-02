package com.EasyRestaurantClient.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.text.ParseException;

/**
 * Unit test for simple Reservation.
 */
public class ReservationTest {

    /**
     * Tries to create a test reservation
     * @throws ParseException
     */
    @Test
    public void testReservation() throws ParseException {
        Reservation reservation = new Reservation();
        String date = "2019-05-18 20:45";
        assertTrue(reservation.make_reservation("carlos", "Txacoli", date, 4, "Gluten allergic"));
    }
}
