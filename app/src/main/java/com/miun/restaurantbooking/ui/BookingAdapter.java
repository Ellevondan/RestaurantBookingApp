package com.miun.restaurantbooking.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miun.restaurantbooking.model.Booking;
import com.miun.restaurantbooking.R;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView Adapter for displaying a list of bookings.
 * TODO: Implement the adapter to bind booking data to ViewHolders
 */
public class BookingAdapter extends RecyclerView.Adapter<BookingViewHolder> {

    private List<Booking> bookings;

    /**
     * Constructor
     */
    public BookingAdapter() {
        this.bookings = new ArrayList<>();
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // TODO: Inflate the item_booking.xml layout
        // Use LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking, parent, false)
        // Return new BookingViewHolder(view)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        // TODO: Get the booking at this position
        // Call holder.bind(booking) to display the data
        Booking booking = bookings.get(position);
        holder.bind(booking);
    }

    @Override
    public int getItemCount() {
        // TODO: Return the size of the bookings list
        return bookings.size();
    }

    /**
     * Update the list of bookings and refresh the RecyclerView.
     *
     * @param newBookings New list of bookings to display
     */
    public void setBookings(List<Booking> newBookings) {
        // TODO: Update the bookings list
        // Call notifyDataSetChanged() to refresh the RecyclerView
        bookings.clear();

        if (newBookings != null) {
            bookings.addAll(newBookings);
        }
        notifyDataSetChanged();
    }

    /**
     * Clear all bookings from the adapter.
     */
    public void clearBookings() {
        // TODO: Clear the bookings list
        // Call notifyDataSetChanged() to refresh the RecyclerView
        bookings.clear();
        notifyDataSetChanged();
    }
}
