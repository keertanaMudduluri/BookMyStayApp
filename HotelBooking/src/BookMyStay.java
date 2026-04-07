import java.util.*;

/**
 * Represents a reservation request.
 */
class Reservation {
    private String guestName;
    private String roomType;
    private String assignedRoomId;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setAssignedRoomId(String roomId) {
        this.assignedRoomId = roomId;
    }

    public String getAssignedRoomId() {
        return assignedRoomId;
    }
}

/**
 * Represents centralized room inventory.
 */
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public void addInventory(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public boolean isAvailable(String roomType) {
        return inventory.getOrDefault(roomType, 0) > 0;
    }

    public void decrement(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }
}

/**
 * Handles room allocation logic.
 */
class RoomAllocationService {

    /**
     * Stores all allocated room IDs to prevent duplicates.
     */
    private Set<String> allocatedRoomIds;

    /**
     * Stores assigned room IDs by room type.
     * Key   -> Room type
     * Value -> Set of assigned room IDs
     */
    private Map<String, Set<String>> assignedRoomsByType;

    /**
     * Initializes allocation tracking structures.
     */
    public RoomAllocationService() {
        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
    }

    /**
     * Confirms a booking request by assigning
     * a unique room ID and updating inventory.
     */
    public void allocateRoom(Reservation reservation, RoomInventory inventory) {
        String roomType = reservation.getRoomType();

        if (!inventory.isAvailable(roomType)) {
            System.out.println("No rooms available for type: " + roomType);
            return;
        }

        String roomId = generateRoomId(roomType);

        reservation.setAssignedRoomId(roomId);
        allocatedRoomIds.add(roomId);

        assignedRoomsByType
                .computeIfAbsent(roomType, k -> new HashSet<>())
                .add(roomId);

        inventory.decrement(roomType);

        System.out.println("Booking confirmed for Guest: "
                + reservation.getGuestName()
                + ", Room ID: " + roomId);
    }

    /**
     * Generates a unique room ID for the given room type.
     * Example: Single-1, Single-2, Suite-1
     */
    private String generateRoomId(String roomType) {
        int counter = 1;
        String roomId;

        do {
            roomId = roomType + "-" + counter++;
        } while (allocatedRoomIds.contains(roomId));

        return roomId;
    }
}

/**
 * MAIN CLASS - UseCaseRoomAllocation
 */
public class BookMyStay {

    public static void main(String[] args) {

        System.out.println("Room Allocation Processing");

        RoomInventory inventory = new RoomInventory();
        inventory.addInventory("Single", 2);
        inventory.addInventory("Suite", 1);

        RoomAllocationService service = new RoomAllocationService();

        // FIFO booking requests
        List<Reservation> reservations = Arrays.asList(
                new Reservation("Abhi", "Single"),
                new Reservation("Subha", "Single"),
                new Reservation("Vanmathi", "Suite")
        );

        for (Reservation r : reservations) {
            service.allocateRoom(r, inventory);
        }
    }
}