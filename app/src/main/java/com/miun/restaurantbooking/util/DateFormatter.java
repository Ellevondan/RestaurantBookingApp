package com.miun.restaurantbooking.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for formatting dates and times for display.
 */
public class DateFormatter {

    private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DISPLAY_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd, yyyy");

    /**
     * Extract and format the time from an ISO 8601 datetime string.
     *
     * @param isoDateTime ISO 8601 datetime string (e.g., "2025-11-27T18:30:00")
     * @return Formatted time string (e.g., "18:30")
     */
    public static String formatTime(String isoDateTime) {
        if (isoDateTime == null || isoDateTime.isEmpty()) {
            return "";
        }
        try {
            LocalDateTime dateTime = LocalDateTime.parse(isoDateTime, ISO_FORMATTER);
            return dateTime.format(TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return isoDateTime;
        }
    }

    /**
     * Extract and format the date from an ISO 8601 datetime string.
     *
     * @param isoDateTime ISO 8601 datetime string (e.g., "2025-11-27T18:30:00")
     * @return Formatted date string (e.g., "Nov 27, 2025")
     */
    public static String formatDate(String isoDateTime) {
        if (isoDateTime == null || isoDateTime.isEmpty()) {
            return "";
        }
        try {
            LocalDateTime dateTime = LocalDateTime.parse(isoDateTime, ISO_FORMATTER);
            return dateTime.format(DISPLAY_DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return isoDateTime;
        }
    }

    /**
     * Get the date portion of an ISO 8601 datetime string in YYYY-MM-DD format.
     *
     * @param isoDateTime ISO 8601 datetime string (e.g., "2025-11-27T18:30:00")
     * @return Date string in YYYY-MM-DD format
     */
    public static String getDateOnly(String isoDateTime) {
        if (isoDateTime == null || isoDateTime.isEmpty()) {
            return "";
        }
        try {
            LocalDateTime dateTime = LocalDateTime.parse(isoDateTime, ISO_FORMATTER);
            return dateTime.format(DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return isoDateTime.split("T")[0];
        }
    }

    /**
     * Format a LocalDateTime to ISO 8601 string.
     *
     * @param dateTime LocalDateTime object
     * @return ISO 8601 formatted string
     */
    public static String toIsoString(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        return dateTime.format(ISO_FORMATTER);
    }
}
