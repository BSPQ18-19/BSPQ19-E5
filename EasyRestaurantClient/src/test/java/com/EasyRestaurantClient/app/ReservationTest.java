package com.EasyRestaurantClient.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;

import java.text.ParseException;

/**
 * Unit test for simple Reservation.
 */
public class ReservationTest {

    /**
     * Tries to create a test reservation
     * @throws ParseException
     */
    @Rule
    public ContiPerfRule i = new ContiPerfRule();
    @Test
    @Required(max=500)
    public void testReservation() throws ParseException {
        String date = "2019-05-18 20:45";
        assertTrue(Reservation.make_reservation("carlos", "Txacoli", date, 4, "Gluten allergic"));
    }
}
