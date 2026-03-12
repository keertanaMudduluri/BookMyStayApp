import java.util.HashMap;
import java.util.Map;

public class BookMyStay {

    /**
     * Stores available room count for each room type.
     * Key   -> Room type name
     * Value -> Available room count
     */
    private Map<String, Integer> roomAvailability;

    /**
     * Constructor initializes the inventory
     * with default availability values.
     */
    public RoomInventory() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    /**
     * Initializes room availability data.
     */
    private void initializeInventory() {

        roomAvailability.put("Single Room", 5);
        roomAvailability.put("Double Room", 3);
        roomAvailability.put("Suite Room", 2);

    }

    /**
     * Returns the current availability map.
     */
    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    /**
     * Updates availability for a specific room type.
     */
    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }
}


public class BookMyStay{

    /**
     * Application entry point.
     */
    public static void main(String[] args) {

        BookMyStay inventory = new BookMyStay();

        System.out.println("Hotel Room Inventory Status\n");

        Map<String, Integer> rooms = inventory.getRoomAvailability();

        for (String roomType : rooms.keySet()) {

            if (roomType.equals("Single Room")) {

                System.out.println("Single Room:");
                System.out.println("Beds: 1");
                System.out.println("Size: 250 sqft");
                System.out.println("Price per night: 1500.0");
                System.out.println("Available Rooms: " + rooms.get(roomType));

            } else if (roomType.equals("Double Room")) {

                System.out.println("\nDouble Room:");
                System.out.println("Beds: 2");
                System.out.println("Size: 400 sqft");
                System.out.println("Price per night: 2500.0");
                System.out.println("Available Rooms: " + rooms.get(roomType));

            } else if (roomType.equals("Suite Room")) {

                System.out.println("\nSuite Room:");
                System.out.println("Beds: 3");
                System.out.println("Size: 750 sqft");
                System.out.println("Price per night: 5000.0");
                System.out.println("Available Rooms: " + rooms.get(roomType));

            }
        }
    }
}