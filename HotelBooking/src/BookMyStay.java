import java.util.*;

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}


// RoomInventory class (basic validation support)
class RoomInventory {

    private Set<String> availableRoomTypes;

    public RoomInventory() {
        availableRoomTypes = new HashSet<>();
        availableRoomTypes.add("Single");
        availableRoomTypes.add("Double");
        availableRoomTypes.add("Suite");
    }

    public boolean isValidRoomType(String roomType) {
        return availableRoomTypes.contains(roomType);
    }
}


// BookingRequestQueue (dummy class for completeness)
class BookingRequestQueue {
    // In real system, this would store requests
}


// ReservationValidator class
class ReservationValidator {

    public void validate(
            String guestName,
            String roomType,
            RoomInventory inventory
    ) throws InvalidBookingException {

        // Validate guest name
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        // Validate room type
        if (!inventory.isValidRoomType(roomType)) {
            throw new InvalidBookingException("Invalid room type selected.");
        }
    }
}


// Main Class
public class BookMyStay {

    public static void main(String[] args) {

        System.out.println("Booking Validation");

        Scanner scanner = new Scanner(System.in);

        // Initialize components
        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        try {
            // Take input
            System.out.print("Enter guest name: ");
            String guestName = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();

            // Validate
            validator.validate(guestName, roomType, inventory);

            // If valid
            System.out.println("Booking successful!");

        } catch (InvalidBookingException e) {

            // Handle validation error
            System.out.println("Booking failed: " + e.getMessage());

        } finally {
            scanner.close();
        }
    }
}