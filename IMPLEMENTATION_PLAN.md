# Restaurant Booking Android App - Complete Implementation Plan

## Project Overview
A simple Android app for restaurant staff to view table bookings. The app will display today's bookings and allow viewing bookings for specific dates.

## Architecture Design

```
┌─────────────────────────────────────────────┐
│           Android App (Java)                 │
├─────────────────────────────────────────────┤
│                                              │
│  UI Layer (Activities/Fragments)            │
│  ├─ MainActivity (Today's Bookings)         │
│  └─ DatePickerActivity (Bookings by Date)   │
│                    ↓                         │
│  Repository Layer                            │
│  └─ BookingRepository                        │
│                    ↓                         │
│  API Layer (Retrofit/HTTP)                   │
│  ├─ BookingApiService (interface)           │
│  └─ ApiClient (dummy implementation)         │
│                    ↓                         │
│  Model Layer                                 │
│  └─ Booking (data class)                     │
│                                              │
└──────────────────┬───────────────────────────┘
                   │
                   │ REST API (Future)
                   ↓
        ┌──────────────────────┐
        │   Payara Server      │
        │   (Web Team)         │
        └──────────────────────┘
```

## Package Structure

```
com.miun.restaurantbooking/
├── model/
│   └── Booking.java
├── api/
│   ├── BookingApiService.java
│   ├── ApiClient.java
│   └── DummyBookingProvider.java
├── repository/
│   └── BookingRepository.java
├── ui/
│   ├── MainActivity.java
│   ├── BookingAdapter.java
│   └── BookingViewHolder.java
└── util/
    └── DateFormatter.java
```

## Data Model

Based on your requirements, here's the `Booking` entity structure:

```java
public class Booking {
    private Long bookingId;
    private String customerName;
    private int tableNumber;
    private String dateTime;  // ISO 8601 format: "2025-11-27T18:30:00"
    private int numberOfGuests;

    // Constructors, getters, setters
}
```

## Team Task Breakdown

### Phase 1: Core Setup (Easy tasks for beginners)
**Priority: HIGH | Estimated time: 2-3 hours**

#### Task 1.1: Create Model Class
**Assignee**: 1 person
**Difficulty**: Easy
**File**: `app/src/main/java/com/miun/restaurantbooking/model/Booking.java`

Create a simple POJO (Plain Old Java Object) with:
- Private fields: `bookingId`, `customerName`, `tableNumber`, `dateTime`, `numberOfGuests`
- Constructor (empty and full)
- Getters and setters for all fields
- `toString()` method for debugging

#### Task 1.2: Add Required Dependencies
**Assignee**: 1 person
**Difficulty**: Easy
**File**: `app/build.gradle.kts`

Add to dependencies section:
```kotlin
// Retrofit for API calls
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")

// RecyclerView for displaying lists
implementation("androidx.recyclerview:recyclerview:1.3.2")

// CardView for booking cards
implementation("androidx.cardview:cardview:1.0.0")
```

### Phase 2: Dummy Data Provider (For testing without backend)
**Priority: HIGH | Estimated time: 1-2 hours**

#### Task 2.1: Create Dummy Data Provider
**Assignee**: 1 person
**Difficulty**: Easy
**File**: `app/src/main/java/com/miun/restaurantbooking/api/DummyBookingProvider.java`

Create a class that generates fake bookings for testing:
```java
public class DummyBookingProvider {
    public static List<Booking> getTodaysBookings() {
        // Return 5-10 fake bookings for today
    }

    public static List<Booking> getBookingsForDate(String date) {
        // Return fake bookings for any date
    }
}
```

### Phase 3: API Layer (Prepared for future backend)
**Priority: MEDIUM | Estimated time: 2-3 hours**

#### Task 3.1: Create API Service Interface
**Assignee**: 1 person
**Difficulty**: Medium
**File**: `app/src/main/java/com/miun/restaurantbooking/api/BookingApiService.java`

Retrofit interface with methods:
```java
public interface BookingApiService {
    @GET("bookings/today")
    Call<List<Booking>> getTodaysBookings();

    @GET("bookings/date/{date}")
    Call<List<Booking>> getBookingsForDate(@Path("date") String date);
}
```

#### Task 3.2: Create API Client
**Assignee**: 1 person
**Difficulty**: Medium
**File**: `app/src/main/java/com/miun/restaurantbooking/api/ApiClient.java`

Singleton class for Retrofit instance:
- Base URL placeholder (will be updated when backend is ready)
- Method to get `BookingApiService` instance

### Phase 4: Repository Layer
**Priority: HIGH | Estimated time: 2 hours**

#### Task 4.1: Create Booking Repository
**Assignee**: 1 person
**Difficulty**: Medium
**File**: `app/src/main/java/com/miun/restaurantbooking/repository/BookingRepository.java`

Central class that:
- Has a toggle: `USE_DUMMY_DATA = true` (switch to false when backend is ready)
- If dummy mode: uses `DummyBookingProvider`
- If real mode: uses `BookingApiService`
- Provides methods: `getTodaysBookings()`, `getBookingsForDate(String date)`

### Phase 5: UI Layer
**Priority: HIGH | Estimated time: 4-5 hours**

#### Task 5.1: Create Booking List Item Layout
**Assignee**: 1 person
**Difficulty**: Easy
**File**: `app/src/main/res/layout/item_booking.xml`

XML layout for a single booking card showing:
- Table number (large, prominent)
- Customer name
- Time
- Number of guests

#### Task 5.2: Create RecyclerView Adapter
**Assignee**: 1 person
**Difficulty**: Medium
**Files**:
- `app/src/main/java/com/miun/restaurantbooking/ui/BookingAdapter.java`
- `app/src/main/java/com/miun/restaurantbooking/ui/BookingViewHolder.java`

Adapter to display list of bookings in RecyclerView

#### Task 5.3: Update MainActivity Layout
**Assignee**: 1 person
**Difficulty**: Easy
**File**: `app/src/main/res/layout/activity_main.xml`

Add:
- Title "Today's Bookings"
- RecyclerView for bookings list
- Button "View Other Dates"
- Empty state message ("No bookings today")

#### Task 5.4: Implement MainActivity Logic
**Assignee**: 1-2 people
**Difficulty**: Medium
**File**: `app/src/main/java/com/miun/restaurantbooking/MainActivity.java`

- Initialize RecyclerView
- Load bookings from repository
- Display in adapter
- Handle empty state
- Handle loading state
- Handle errors

### Phase 6: Additional Features (Optional for v1)
**Priority: LOW | Estimated time: 3-4 hours**

#### Task 6.1: Date Picker Feature
**Assignee**: 1 person
**Difficulty**: Medium

Allow staff to select a specific date and view those bookings

#### Task 6.2: Refresh Functionality
**Assignee**: 1 person
**Difficulty**: Easy

Add pull-to-refresh to update bookings list

#### Task 6.3: Sorting/Filtering
**Assignee**: 1 person
**Difficulty**: Medium

Sort bookings by time or table number

## Code Style Guide

Following the patterns from `MenuService.java`:

1. **Documentation**: Use JavaDoc comments for all public methods
2. **Naming**: Use clear, descriptive names (e.g., `getTodaysBookings()`, `getBookingsForDate()`)
3. **Error Handling**: Check for null values before operations
4. **Format**: Use consistent indentation and spacing

## Setup Instructions for Teammates

### Prerequisites
1. Android Studio (latest version)
2. JDK 11 or higher
3. Android SDK with minimum API 24

### Getting Started
1. Clone the repository
2. Open project in Android Studio
3. Wait for Gradle sync to complete
4. Run the app on an emulator or physical device (API 24+)

### First Tasks
1. Pick a task from Phase 1 or Phase 2
2. Create a new branch: `feature/your-task-name`
3. Implement your task
4. Test on emulator
5. Create pull request for review

## Integration Checklist (For when backend is ready)

- [ ] Get backend API base URL from web team
- [ ] Update `ApiClient.java` with correct base URL
- [ ] Verify endpoint paths match backend (e.g., `/api/bookings/today`)
- [ ] Test with real backend
- [ ] Switch `BookingRepository.USE_DUMMY_DATA` to `false`
- [ ] Add internet permission to AndroidManifest.xml
- [ ] Handle network errors properly

## Testing Strategy

### Unit Testing (Optional but recommended)
- Test `Booking` model getters/setters
- Test `DummyBookingProvider` data generation
- Test date formatting utilities

### Manual Testing Checklist
- [ ] App launches successfully
- [ ] Dummy data displays correctly
- [ ] Scrolling through bookings works smoothly
- [ ] App handles empty bookings list
- [ ] Date formats are readable
- [ ] UI looks good on different screen sizes

## Notes for Web Team

When implementing the backend REST API, please provide:

### Required Endpoints

```java
// GET /api/bookings/today
// Returns: List<Booking> for today's date
// Example response:
[
  {
    "bookingId": 1,
    "customerName": "John Doe",
    "tableNumber": 5,
    "dateTime": "2025-11-27T18:30:00",
    "numberOfGuests": 4
  }
]

// GET /api/bookings/date/{date}
// Path parameter: date in format YYYY-MM-DD
// Returns: List<Booking> for specified date
```

### Booking Entity (Backend)
```java
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    private String customerName;
    private int tableNumber;
    private LocalDateTime dateTime;
    private int numberOfGuests;

    // Getters and setters
}
```

### Example BookingService (Backend)
```java
@Stateless
public class BookingService {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    /**
     * Get all bookings for today
     */
    public List<Booking> getTodaysBookings() {
        LocalDate today = LocalDate.now();
        return em.createQuery(
            "SELECT b FROM Booking b WHERE DATE(b.dateTime) = :date ORDER BY b.dateTime",
            Booking.class)
            .setParameter("date", today)
            .getResultList();
    }

    /**
     * Get bookings for a specific date
     */
    public List<Booking> getBookingsForDate(LocalDate date) {
        return em.createQuery(
            "SELECT b FROM Booking b WHERE DATE(b.dateTime) = :date ORDER BY b.dateTime",
            Booking.class)
            .setParameter("date", date)
            .getResultList();
    }
}
```

### Example REST Resource (Backend)
```java
@Path("/bookings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingResource {

    @EJB
    private BookingService bookingService;

    @GET
    @Path("/today")
    public Response getTodaysBookings() {
        List<Booking> bookings = bookingService.getTodaysBookings();
        return Response.ok(bookings).build();
    }

    @GET
    @Path("/date/{date}")
    public Response getBookingsForDate(@PathParam("date") String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);
        List<Booking> bookings = bookingService.getBookingsForDate(date);
        return Response.ok(bookings).build();
    }
}
```

## Questions or Issues?

Contact the team lead or create an issue in the project repository.
