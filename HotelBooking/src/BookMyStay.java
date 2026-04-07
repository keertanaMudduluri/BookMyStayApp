import java.util.*;

// AddOnService class
class AddOnService {

    private String serviceName;
    private double cost;

    // Constructor
    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    // Getter for service name
    public String getServiceName() {
        return serviceName;
    }

    // Getter for cost
    public double getCost() {
        return cost;
    }
}


// AddOnServiceManager class
class AddOnServiceManager {

    private Map<String, List<AddOnService>> servicesByReservation;

    // Constructor
    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    // Add service to reservation
    public void addService(String reservationId, AddOnService service) {
        servicesByReservation.putIfAbsent(reservationId, new ArrayList<>());
        servicesByReservation.get(reservationId).add(service);
    }

    // Calculate total cost
    public double calculateTotalServiceCost(String reservationId) {
        double total = 0;

        List<AddOnService> services = servicesByReservation.get(reservationId);

        if (services != null) {
            for (AddOnService s : services) {
                total += s.getCost();
            }
        }

        return total;
    }
}


// Main class
public class BookMyStay {

    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        // Create services
        AddOnService s1 = new AddOnService("Breakfast", 500);
        AddOnService s2 = new AddOnService("Airport Pickup", 1000);

        String reservationId = "Single-1";

        // Add services
        manager.addService(reservationId, s1);
        manager.addService(reservationId, s2);

        // Calculate total cost
        double total = manager.calculateTotalServiceCost(reservationId);

        // Output
        System.out.println("Add-On Service Selection");
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + total);
    }
}