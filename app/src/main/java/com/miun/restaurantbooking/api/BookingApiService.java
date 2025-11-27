package com.miun.restaurantbooking.api;

import com.miun.restaurantbooking.model.Booking;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Retrofit API interface for booking-related endpoints.
 * This interface will be used once the backend REST API is ready.
 * The actual endpoints will be provided by the web team.
 */
public interface BookingApiService {

    /**
     * Get all bookings for today.
     * TODO: Update endpoint path when backend is ready
     *
     * @return Call containing list of today's bookings
     */
    @GET("api/bookings/today")
    Call<List<Booking>> getTodaysBookings();

    /**
     * Get bookings for a specific date.
     * TODO: Update endpoint path when backend is ready
     *
     * @param date Date string in format YYYY-MM-DD
     * @return Call containing list of bookings for the specified date
     */
    @GET("api/bookings/date/{date}")
    Call<List<Booking>> getBookingsForDate(@Path("date") String date);
}
