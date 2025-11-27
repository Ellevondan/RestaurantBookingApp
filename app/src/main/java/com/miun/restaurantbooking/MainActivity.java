package com.miun.restaurantbooking;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miun.restaurantbooking.model.Booking;
import com.miun.restaurantbooking.repository.BookingRepository;
import com.miun.restaurantbooking.ui.BookingAdapter;

import java.util.List;

/**
 * Main activity that displays today's bookings.
 */
public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private BookingAdapter adapter;
    private TextView emptyStateText;
    private BookingRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // TODO: Initialize views

        // TODO: Initialize repository

        // TODO: Setup RecyclerView

        // TODO: Load today's bookings

    }

    /**
     * Load and display today's bookings.
     */
    private void loadTodaysBookings() {
        // TODO: Call repository.getTodaysBookings() with a callback
        // In onSuccess: Update adapter with bookings, show/hide empty state
        // In onError: Show error message with Toast
    }

    /**
     * Show or hide the empty state message.
     *
     * @param show true to show empty state, false to hide
     */
    private void showEmptyState(boolean show) {
        // TODO: Set visibility of emptyStateText and recyclerView accordingly
    }
}