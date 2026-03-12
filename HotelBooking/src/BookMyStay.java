import java.util.*;

// Room class
class Room {

    private String type;
    private int beds;
    private int size;
    private double price;

    public Room(String type, int beds, int size, double price) {
        this.type = type;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public void displayRoom(int available) {
        System.out.println(type + " Room:");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
        System.out.println("Available: " + available);
        System.out.println();
    }
}


// RoomInventory class
class RoomInventory {

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    private void initializeInventory() {
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }
}


// Room search service
class RoomSearchService {

    public void searchAvailableRooms(
            RoomInventory inventory,
            Room singleRoom,
            Room doubleRoom,
            Room suiteRoom) {

        Map<String, Integer> availability = inventory.getRoomAvailability();

        if (availability.get("Single") > 0) {
            singleRoom.displayRoom(availability.get("Single"));
        }

        if (availability.get("Double") > 0) {
            doubleRoom.displayRoom(availability.get("Double"));
        }

        if (availability.get("Suite") > 0) {
            suiteRoom.displayRoom(availability.get("Suite"));
        }
    }
}


// Main class
public class BookMyStay {

    public static void main(String[] args) {

        System.out.println("Room Search\n");

        Room singleRoom = new Room("Single", 1, 250, 1500.0);
        Room doubleRoom = new Room("Double", 2, 400, 2500.0);
        Room suiteRoom = new Room("Suite", 3, 750, 5000.0);

        RoomInventory inventory = new RoomInventory();

        RoomSearchService service = new RoomSearchService();

        service.searchAvailableRooms(inventory, singleRoom, doubleRoom, suiteRoom);
    }
}