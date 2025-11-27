# Task Assignment Guide - Restaurant Booking App

## Overview
This document breaks down all the work that needs to be done into independent tasks. Each task can be worked on separately, so multiple teammates can work in parallel without conflicts.

---

## Task 1: Implement DateFormatter Utility
**File**: `app/src/main/java/com/miun/restaurantbooking/util/DateFormatter.java`
**Difficulty**: Easy
**Estimated Time**: 30-45 minutes
**Prerequisites**: None

### What to do:
1. Implement all four methods in `DateFormatter.java`
2. Use `LocalDateTime` and `DateTimeFormatter` to parse and format dates
3. Handle null/empty strings gracefully

### Key methods to implement:
- `formatTime()` - Extract time from ISO string and format as "HH:mm"
- `formatDate()` - Format date as "MMM dd, yyyy"
- `getDateOnly()` - Return just the date portion "YYYY-MM-DD"
- `toIsoString()` - Convert LocalDateTime to ISO format

### Testing:
Create a simple test in `onCreate()` of MainActivity:
```java
String testDate = "2025-11-27T18:30:00";
Log.d("DateFormatter", "Time: " + DateFormatter.formatTime(testDate));
Log.d("DateFormatter", "Date: " + DateFormatter.formatDate(testDate));
```

---

## Task 2: Implement ApiClient Singleton
**File**: `app/src/main/java/com/miun/restaurantbooking/api/ApiClient.java`
**Difficulty**: Easy
**Estimated Time**: 30 minutes
**Prerequisites**: None

### What to do:
1. Implement the singleton pattern in `getClient()`
2. Create Retrofit instance with base URL and Gson converter
3. Implement `getBookingApiService()`

### Implementation hints:
```java
if (retrofit == null) {
    retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
}
return retrofit;
```

### Testing:
The API won't work yet (no backend), but you can test that it creates without errors:
```java
BookingApiService service = ApiClient.getBookingApiService();
Log.d("ApiClient", "Service created: " + (service != null));
```

---

## Task 3: Implement BookingRepository
**File**: `app/src/main/java/com/miun/restaurantbooking/repository/BookingRepository.java`
**Difficulty**: Medium
**Estimated Time**: 1 hour
**Prerequisites**: Task 2 (ApiClient) should be done first

### What to do:
1. Implement `getTodaysBookings()` method
   - Check `USE_DUMMY_DATA` flag
   - If true: use `DummyBookingProvider`, call `callback.onSuccess()`
   - If false: use Retrofit's `enqueue()` to make API call
2. Implement `getBookingsForDate()` similarly

### Implementation hints for dummy mode:
```java
List<Booking> bookings = DummyBookingProvider.getTodaysBookings();
callback.onSuccess(bookings);
```

### Implementation hints for API mode:
```java
apiService.getTodaysBookings().enqueue(new Callback<List<Booking>>() {
    @Override
    public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
        if (response.isSuccessful() && response.body() != null) {
            callback.onSuccess(response.body());
        } else {
            callback.onError("Failed to load bookings");
        }
    }

    @Override
    public void onFailure(Call<List<Booking>> call, Throwable t) {
        callback.onError(t.getMessage());
    }
});
```

### Testing:
Test with dummy data first. You can test in MainActivity temporarily.

---

## Task 4: Design Booking Card Layout
**File**: `app/src/main/res/layout/item_booking.xml`
**Difficulty**: Easy
**Estimated Time**: 45 minutes
**Prerequisites**: None

### What to do:
1. Design a CardView layout for a single booking
2. Must include these TextViews with exact IDs:
   - `@+id/textTableNumber` (large, prominent)
   - `@+id/textCustomerName`
   - `@+id/textBookingTime`
   - `@+id/textNumberOfGuests`

### Design suggestions:
- Use LinearLayout inside CardView
- Table number on the left (large, colored background)
- Booking details on the right
- Add proper padding and margins

### Reference:
Look at Material Design card examples online for inspiration.

### Testing:
You can preview the layout in Android Studio's Layout Editor.

---

## Task 5: Design Main Activity Layout
**File**: `app/src/main/res/layout/activity_main.xml`
**Difficulty**: Easy
**Estimated Time**: 45 minutes
**Prerequisites**: None

### What to do:
1. Design the main screen layout
2. Must include these views with exact IDs:
   - `@+id/recyclerViewBookings` (RecyclerView)
   - `@+id/textEmptyState` (TextView for "No bookings")
3. Add a title at the top
4. Optional: Add a button for viewing other dates

### Layout structure:
```
- Title TextView (top)
- RecyclerView (fills remaining space)
- Empty state TextView (centered, initially hidden)
```

### Testing:
Preview in Layout Editor.

---

## Task 6: Implement BookingViewHolder
**File**: `app/src/main/java/com/miun/restaurantbooking/ui/BookingViewHolder.java`
**Difficulty**: Easy
**Estimated Time**: 30 minutes
**Prerequisites**: Task 1 (DateFormatter), Task 4 (item_booking.xml)

### What to do:
1. Declare TextView variables for all views from `item_booking.xml`
2. Initialize them in the constructor using `findViewById()`
3. Implement the `bind()` method to display booking data

### Implementation hints:
```java
textTableNumber.setText(String.valueOf(booking.getTableNumber()));
textCustomerName.setText(booking.getCustomerName());
textBookingTime.setText(DateFormatter.formatTime(booking.getDateTime()));
textNumberOfGuests.setText(booking.getNumberOfGuests() + " guests");
```

### Testing:
Can't fully test until adapter is implemented.

---

## Task 7: Implement BookingAdapter
**File**: `app/src/main/java/com/miun/restaurantbooking/ui/BookingAdapter.java`
**Difficulty**: Medium
**Estimated Time**: 45 minutes
**Prerequisites**: Task 4 (item_booking.xml), Task 6 (BookingViewHolder)

### What to do:
1. Implement `onCreateViewHolder()` - inflate layout and create ViewHolder
2. Implement `onBindViewHolder()` - get booking and call `holder.bind()`
3. Implement `getItemCount()` - return bookings list size
4. Implement `setBookings()` - update list and call `notifyDataSetChanged()`
5. Implement `clearBookings()` - clear list and notify

### Implementation hints:
```java
// onCreateViewHolder
View view = LayoutInflater.from(parent.getContext())
    .inflate(R.layout.item_booking, parent, false);
return new BookingViewHolder(view);

// onBindViewHolder
Booking booking = bookings.get(position);
holder.bind(booking);
```

### Testing:
Will be tested once integrated into MainActivity.

---

## Task 8: Implement MainActivity Logic
**File**: `app/src/main/java/com/miun/restaurantbooking/MainActivity.java`
**Difficulty**: Medium
**Estimated Time**: 1 hour
**Prerequisites**: Task 3 (Repository), Task 5 (activity_main.xml), Task 7 (Adapter)

### What to do:
1. Uncomment and initialize all instance variables
2. Setup RecyclerView with adapter and layout manager
3. Implement `loadTodaysBookings()` method
4. Implement `showEmptyState()` method

### Implementation hints:
```java
// In onCreate
repository = new BookingRepository();
adapter = new BookingAdapter();
recyclerView.setLayoutManager(new LinearLayoutManager(this));
recyclerView.setAdapter(adapter);
loadTodaysBookings();

// loadTodaysBookings
repository.getTodaysBookings(new BookingRepository.BookingCallback() {
    @Override
    public void onSuccess(List<Booking> bookings) {
        adapter.setBookings(bookings);
        showEmptyState(bookings.isEmpty());
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
});

// showEmptyState
if (show) {
    emptyStateText.setVisibility(View.VISIBLE);
    recyclerView.setVisibility(View.GONE);
} else {
    emptyStateText.setVisibility(View.GONE);
    recyclerView.setVisibility(View.VISIBLE);
}
```

### Testing:
Run the app! You should see dummy bookings displayed.

---

## Task 9: Add Internet Permission (When Backend is Ready)
**File**: `app/src/main/AndroidManifest.xml`
**Difficulty**: Very Easy
**Estimated Time**: 5 minutes
**Prerequisites**: None (do this when switching to real API)

### What to do:
Add this line before the `<application>` tag:
```xml
<uses-permission android:name="android.permission.INTERNET" />
```

---

## Task 10: Add Pull-to-Refresh (Optional Enhancement)
**Files**: `activity_main.xml`, `MainActivity.java`
**Difficulty**: Medium
**Estimated Time**: 45 minutes
**Prerequisites**: Task 8 (MainActivity completed)

### What to do:
1. Wrap RecyclerView in SwipeRefreshLayout in XML
2. Add refresh listener in MainActivity
3. Call `loadTodaysBookings()` on refresh

### Reference:
https://developer.android.com/training/swipe/add-swipe-interface

---

## Task Distribution Suggestions

### For 3 Teammates:
- **Person A**: Tasks 1, 2, 3 (Backend/Data layer)
- **Person B**: Tasks 4, 5 (UI Design)
- **Person C**: Tasks 6, 7, 8 (UI Logic)

### For 4 Teammates:
- **Person A**: Tasks 1, 2 (Utilities & API)
- **Person B**: Tasks 3 (Repository)
- **Person C**: Tasks 4, 5 (Layouts)
- **Person D**: Tasks 6, 7, 8 (RecyclerView & MainActivity)

### For 5+ Teammates:
Split tasks individually, assign optional enhancements.

---

## Testing the Complete App

Once all tasks are complete:

1. **Sync Gradle** in Android Studio
2. **Run the app** on an emulator or device
3. **You should see** dummy booking data displayed in cards
4. **Test scrolling** through the list
5. **Check** that table numbers, names, times, and guest counts display correctly

---

## When Backend is Ready

1. Update `BASE_URL` in `ApiClient.java` with actual server URL
2. Change `USE_DUMMY_DATA` to `false` in `BookingRepository.java`
3. Add internet permission (Task 9)
4. Test with real data!

---

## Getting Help

- Check the `IMPLEMENTATION_PLAN.md` for detailed architecture
- Android documentation: https://developer.android.com
- Retrofit guide: https://square.github.io/retrofit/
- Ask your team lead if stuck!
