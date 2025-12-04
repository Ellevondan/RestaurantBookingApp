package com.miun.restaurantbooking.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Singleton class for creating and managing Retrofit API client.
 * TODO: Update BASE_URL when backend server is deployed
 */
public class ApiClient {

    // TODO: Replace this placeholder URL with the actual backend server URL
    private static final String BASE_URL = "http://10.0.2.2:8080/demo-1.0-SNAPSHOT/"; // Android emulator localhost



    private static Retrofit retrofit = null;

    /**
     * Get Retrofit instance (singleton pattern).
     * Creates a new instance if it doesn't exist.
     * TODO: Implement the singleton pattern to return a Retrofit instance
     *
     * @return Retrofit instance
     */
    public static Retrofit getClient() {
        // TODO: Implement singleton pattern
        // If retrofit is null, create new Retrofit.Builder()
        // Set base URL, add GsonConverterFactory, and build
        // Return the retrofit instance

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        // returnera samma instans varje gång
        return retrofit;
    }

    /**
     * Get BookingApiService instance.
     *
     * @return BookingApiService for making API calls
     */
    public static BookingApiService getBookingApiService() {
        // TODO: Use getClient().create(BookingApiService.class) to return the service
        //Done : skapar en implementation av interface BookingApiSerice
        return getClient().create(BookingApiService.class);
    }
}
