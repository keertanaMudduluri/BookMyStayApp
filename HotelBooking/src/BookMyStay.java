import java.util.*;

// RoomInventory class
class RoomInventory {
    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    public void restoreRoom(String roomType) {
        roomAvailability.put(roomType, roomAvailability.getOrDefault(roomType, 0) + 1);
    }

    public int getAvailableRooms(String roomType) {
        return roomAvailability.getOrDefault(roomType, 0);
    }
}

// CancellationService class
class CancellationService {

    // Stack that stores recently released room IDs
    private Stack<String> releasedRoomIds;

    // Maps reservation ID to room type
    private Map<String, String> reservationRoomTypeMap;

    // Initializes cancellation tracking structures
    public CancellationService() {
        releasedRoomIds = new Stack<>();
        reservationRoomTypeMap = new HashMap<>();
    }

    // Registers a confirmed booking
    public void registerBooking(String reservationId, String roomType) {
        reservationRoomTypeMap.put(reservationId, roomType);
    }

    // Cancels a confirmed booking and restores inventory safely
    public void cancelBooking(String reservationId, RoomInventory inventory) {
        if (reservationRoomTypeMap.containsKey(reservationId)) {
            String roomType = reservationRoomTypeMap.get(reservationId);

            inventory.restoreRoom(roomType);
            releasedRoomIds.push(reservationId);
            reservationRoomTypeMap.remove(reservationId);

            System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
        } else {
            System.out.println("Reservation ID not found.");
        }
    }

    // Displays recently cancelled reservations
    public void showRollbackHistory() {
        System.out.println("\nRollback History (Most Recent First):");
        while (!releasedRoomIds.isEmpty()) {
            System.out.println("Released Reservation ID: " + releasedRoomIds.pop());
        }
    }
}

// Main class
public class BookMyStay {
    public static void main(String[] args) {

        System.out.println("Booking Cancellation");

        RoomInventory inventory = new RoomInventory();
        CancellationService cancellationService = new CancellationService();

        // Register a confirmed booking
        cancellationService.registerBooking("Single-1", "Single");

        // Cancel the booking
        cancellationService.cancelBooking("Single-1", inventory);

        // Show rollback history
        cancellationService.showRollbackHistory();

        // Display updated availability
        System.out.println("\nUpdated Single Room Availability: " + inventory.getAvailableRooms("Single"));
    }
}