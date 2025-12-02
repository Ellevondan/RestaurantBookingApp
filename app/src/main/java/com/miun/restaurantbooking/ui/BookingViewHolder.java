package com.miun.restaurantbooking.ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miun.restaurantbooking.R;
import com.miun.restaurantbooking.model.Booking;
import com.miun.restaurantbooking.util.DateFormatter;

/**
 * ViewHolder for displaying a single booking item.
 * TODO: Implement binding logic to display booking data in the views
 */
public class BookingViewHolder extends RecyclerView.ViewHolder {

    private TextView textCustomerName;
    private TextView textBookingTime;
    private TextView textNumberOfGuests;

    public BookingViewHolder(@NonNull View itemView) {
        super(itemView);
        // TODO: Initialize all TextViews using itemView.findViewById()
        textCustomerName = itemView.findViewById(R.id.textCustomerName);
        textBookingTime = itemView.findViewById(R.id.textBookingTime);
        textNumberOfGuests = itemView.findViewById(R.id.textNumberOfGuests);
    }

    /**
     * Bind booking data to the views.
     *
     * @param booking The booking to display
     */
    public void bind(Booking booking) {
        textCustomerName.setText(booking.getCustomerName());
        String formattedTime = DateFormatter.formatTime(booking.getDateTime());
        textBookingTime.setText(formattedTime);
        textNumberOfGuests.setText(booking.getNumberOfGuests() + "guests");
        // TODO: Set the data from the booking object to each TextView
        // Use DateFormatter to format the time nicely
        // For number of guests: textNumberOfGuests.setText(booking.getNumberOfGuests() + " guests");
    }
}
