package com.miun.restaurantbooking.api;

import com.miun.restaurantbooking.model.Booking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Provides dummy booking data for testing purposes.
 * This class generates fake bookings.
 */
public class DummyBookingProvider {

    private static final String[] CUSTOMER_NAMES = {
            "Anna Andersson",
            "Erik Eriksson",
            "Maria Johansson",
            "Lars Larsson",
            "Karin Nilsson",
            "Johan Karlsson",
            "Emma Svensson",
            "Mikael Gustafsson",
            "Sofia Pettersson",
            "Anders Jonsson",
            "Lisa Persson",
            "Peter Olsson",
            "Sara Lindberg",
            "Magnus Berg",
            "Helena Lundgren"
    };

    private static final String[] PHONE_NUMBERS = {
            "070-123 45 67",
            "073-987 65 43",
            "076-234 56 78",
            "070-456 78 90",
            "073-678 90 12",
            "076-890 12 34",
            "070-345 67 89",
            "073-567 89 01",
            "076-789 01 23",
            "070-901 23 45",
            "073-012 34 56",
            "076-234 45 67",
            "070-456 67 89",
            "073-678 89 01",
            "076-890 01 23"
    };

    private static final int[] TABLE_NUMBERS = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    private static final int[] GUEST_COUNTS = {2, 2, 3, 4, 4, 5, 6};
    private static final String[] BOOKING_TIMES = {
            "11:30:00",
            "12:00:00",
            "12:30:00",
            "13:00:00",
            "17:00:00",
            "17:30:00",
            "18:00:00",
            "18:30:00",
            "19:00:00",
            "19:30:00",
            "20:00:00",
            "20:30:00"
    };

    private static final Random random = new Random();
    private static long bookingIdCounter = 1;

    /**
     * Get all bookings for today.
     * Generates 8-12 random bookings for the current date.
     *
     * @return List of bookings for today
     */
    public static List<Booking> getTodaysBookings() {
        LocalDate today = LocalDate.now();
        return getBookingsForDate(today.toString());
    }

    /**
     * Get bookings for a specific date.
     * Generates 5-12 random bookings for the given date.
     *
     * @param date Date string in format YYYY-MM-DD
     * @return List of bookings for the specified date
     */
    public static List<Booking> getBookingsForDate(String date) {
        List<Booking> bookings = new ArrayList<>();

        // Generate between 5 and 12 bookings
        int numberOfBookings = 5 + random.nextInt(8);

        // Keep track of used tables to avoid duplicates at the same time
        List<String> usedTableTimes = new ArrayList<>();

        for (int i = 0; i < numberOfBookings; i++) {
            String customerName = CUSTOMER_NAMES[random.nextInt(CUSTOMER_NAMES.length)];
            String phoneNumber = PHONE_NUMBERS[random.nextInt(PHONE_NUMBERS.length)];
            int tableNumber = TABLE_NUMBERS[random.nextInt(TABLE_NUMBERS.length)];
            String time = BOOKING_TIMES[random.nextInt(BOOKING_TIMES.length)];
            int numberOfGuests = GUEST_COUNTS[random.nextInt(GUEST_COUNTS.length)];

            // Ensure we don't book the same table at the same time
            String tableTimeKey = tableNumber + "-" + time;
            if (usedTableTimes.contains(tableTimeKey)) {
                i--; // Try again
                continue;
            }
            usedTableTimes.add(tableTimeKey);

            // Create datetime string in ISO 8601 format
            String dateTime = date + "T" + time;

            Booking booking = new Booking(
                    bookingIdCounter++,
                    customerName,
                    phoneNumber,
                    tableNumber,
                    dateTime,
                    numberOfGuests
            );

            bookings.add(booking);
        }

        // Sort bookings by time
        bookings.sort((b1, b2) -> b1.getDateTime().compareTo(b2.getDateTime()));

        return bookings;
    }

    /**
     * Get a single booking by ID.
     *
     * @param id Booking ID
     * @return A dummy booking with the given ID
     */
    public static Booking getBookingById(Long id) {
        LocalDate today = LocalDate.now();
        return new Booking(
                id,
                CUSTOMER_NAMES[0],
                PHONE_NUMBERS[0],
                TABLE_NUMBERS[0],
                today.toString() + "T18:00:00",
                4
        );
    }

    /**
     * Reset the booking ID counter.
     */
    public static void resetCounter() {
        bookingIdCounter = 1;
    }
}
