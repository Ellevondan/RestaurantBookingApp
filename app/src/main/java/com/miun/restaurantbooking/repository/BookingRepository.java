package com.miun.restaurantbooking.repository;

import com.miun.restaurantbooking.api.ApiClient;
import com.miun.restaurantbooking.api.BookingApiService;
import com.miun.restaurantbooking.api.DummyBookingProvider;
import com.miun.restaurantbooking.model.Booking;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository class that handles data operations for bookings.
 * Acts as a single source of truth for booking data.
 * Can switch between dummy data and real API calls.
 */
public class BookingRepository {

    /**
     * Toggle this to switch between dummy data and real API.
     */
    private static final boolean USE_DUMMY_DATA = true;

    private final BookingApiService apiService;

    /**
     * Constructor
     */
    public BookingRepository() {
        this.apiService = ApiClient.getBookingApiService();
    }

    /**
     * Get all bookings for today.
     * Uses dummy data if USE_DUMMY_DATA is true, otherwise calls the real API.
     *
     * @param callback Callback to handle the result
     */
    public void getTodaysBookings(BookingCallback callback) {
        if (USE_DUMMY_DATA) {
            List<Booking> dummyBookings = DummyBookingProvider.getTodaysBookings();
            callback.onSuccess(dummyBookings);
        } else {
            // API
            Call<List<Booking>> call = apiService.getTodaysBookings();
            call.enqueue(new Callback<List<Booking>>() {
                @Override
                public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onError("Failed to fetch bookings: " + response.code());
                    }
                }
                @Override
                public void onFailure(Call<List<Booking>> call, Throwable t) {
                    callback.onError("Network error: " + t.getMessage());
                }
            });
        }
    }

    /**
     * Get bookings for a specific date.
     * Uses dummy data if USE_DUMMY_DATA is true, otherwise calls the real API.
     *
     * @param date     Date string in format YYYY-MM-DD
     * @param callback Callback to handle the result
     */
    public void getBookingsForDate(String date, BookingCallback callback) {
        if (USE_DUMMY_DATA) {
            List<Booking> dummyBookings = DummyBookingProvider.getBookingsForDate(date);
            callback.onSuccess(dummyBookings);
        } else {
            // API
            Call<List<Booking>> call = apiService.getBookingsForDate(date);
            call.enqueue(new Callback<List<Booking>>() {
                @Override
                public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onError("Failed to fetch bookings for date: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<Booking>> call, Throwable t) {
                    callback.onError("Network error: " + t.getMessage());
                }
            });
        }
    }

    /**
     * Callback interface for handling asynchronous booking requests
     */
    public interface BookingCallback {
        void onSuccess(List<Booking> bookings);
        void onError(String errorMessage);
    }
}
