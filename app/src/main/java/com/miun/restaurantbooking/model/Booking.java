package com.miun.restaurantbooking.model;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a table booking at the restaurant.
 */
public class Booking {
    @SerializedName("id")
    private Long bookingId;

    @SerializedName("name")
    private String customerName;

    @SerializedName("phoneNumber")
    private String phoneNumber;

    @SerializedName("tableNum")
    private int tableNumber;

    @SerializedName("dateTime")
    private String dateTime;

    @SerializedName("numberOfPeople")
    private int numberOfGuests;

    /**
     * Default constructor
     */
    public Booking() {
    }

    /**
     * Full constructor
     *
     * @param bookingId      Unique identifier for the booking
     * @param customerName   Name of the customer who made the booking
     * @param phoneNumber    Phone number of the customer
     * @param tableNumber    Table number assigned to this booking
     * @param dateTime       Date and time of the booking in ISO 8601 format
     * @param numberOfGuests Number of guests for this booking
     */
    public Booking(Long bookingId, String customerName, String phoneNumber, int tableNumber, String dateTime, int numberOfGuests) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.tableNumber = tableNumber;
        this.dateTime = dateTime;
        this.numberOfGuests = numberOfGuests;
    }

    // Getters and Setters

    /**
     * Get the booking ID
     *
     * @return the unique booking identifier
     */
    public Long getBookingId() {
        return bookingId;
    }

    /**
     * Set the booking ID
     *
     * @param bookingId the unique booking identifier
     */
    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    /**
     * Get the customer name
     *
     * @return the name of the customer
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Set the customer name
     *
     * @param customerName the name of the customer
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Get the phone number
     *
     * @return the customer's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set the phone number
     *
     * @param phoneNumber the customer's phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the table number
     *
     * @return the assigned table number
     */
    public int getTableNumber() {
        return tableNumber;
    }

    /**
     * Set the table number
     *
     * @param tableNumber the assigned table number
     */
    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    /**
     * Get the date and time of the booking
     *
     * @return date and time in ISO 8601 format
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Set the date and time of the booking
     *
     * @param dateTime date and time in ISO 8601 format
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Get the number of guests
     *
     * @return number of guests for this booking
     */
    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    /**
     * Set the number of guests
     *
     * @param numberOfGuests number of guests for this booking
     */
    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    /**
     * Returns a string representation of the booking
     *
     * @return string containing all booking details
     */
    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", customerName='" + customerName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", tableNumber=" + tableNumber +
                ", dateTime='" + dateTime + '\'' +
                ", numberOfGuests=" + numberOfGuests +
                '}';
    }
}
