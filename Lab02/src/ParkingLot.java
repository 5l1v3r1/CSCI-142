import java.util.ArrayList;

/**
 * Created by ptnega on 05/02/2017.
 *
 * @author Duc Phan - ddp3945@rit.edu
 */
public class ParkingLot {

    /**
     * an illegal spot number
     */
    public static final int ILLEGAL_SPOT = -1;

    /**
     * when printing the lot, the number of spots to display per line
     */
    private static final int SPOTS_PER_LINE = 10;

    /**
     * the number of reserved spots
     */
    private int handicappedSpots;

    /**
     * the number of reserved spots
     */
    private int reservedSpots;

    /**
     * the number of general spots
     */
    private int generalSpots;

    /**
     * the total number of spots
     */
    private int capacity;

    /**
     * the number of vehicles currently parked in the lot
     */
    private int parkedVehicles;

    /**
     * the collection of parking spots
     */
    private ArrayList<ParkingSpot> lot;

    /**
     * Create a new parking lot.
     *
     * @param handicappedSpots number of handicapped spots
     * @param reservedSpots    number of reserved spots
     * @param generalSpots     number of general spots
     * @pre.rit the number of spots for each kind is non-negative
     */
    public ParkingLot(int handicappedSpots, int reservedSpots, int generalSpots) {
        this.handicappedSpots = handicappedSpots;
        this.reservedSpots = reservedSpots;
        this.generalSpots = generalSpots;
        this.capacity = handicappedSpots + reservedSpots + generalSpots;
        this.parkedVehicles = 0;
        this.lot = new ArrayList<>();
        initializeSpots();
    }

    /**
     * Create the parking spots for the lot. This is a helper method that is called by the constructor, after the lot
     * has been created, to initialize and add each new spot to the lot.
     */
    private void initializeSpots() {
        int i = 0;
        for (; i < this.handicappedSpots; i++) {
            ParkingSpot s = new ParkingSpot(i, Permit.Type.HANDICAPPED);
            this.lot.add(s);
        }
        for (int j = 0; j < this.reservedSpots; i++, j++) {
            ParkingSpot s = new ParkingSpot(i, Permit.Type.RESERVED);
            this.lot.add(s);
        }
        for (int j = 0; j < this.generalSpots; i++, j++) {
            ParkingSpot s = new ParkingSpot(i, Permit.Type.GENERAL);
            this.lot.add(s);
        }
    }

    /**
     * Get the total number of spots in the lot.
     *
     * @return the lot's capacity
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Get the number of spots that are occupied by vehicles.
     *
     * @return the number of parked vehicles
     */
    public int getNumParkedVehicles() {
        return this.parkedVehicles;
    }

    /**
     * Tells whether a spot is in a valid range within the parking lot or not.
     *
     * @param spot the spot to check
     * @return whether the spot is in range or not
     */
    public boolean isSpotValid(int spot) {
        return (0 <= spot && spot < this.lot.size());
    }

    /**
     * Get a parking spot.
     *
     * @param spot the spot number
     * @return the spot
     * @pre.rit spot is within the range of the lot
     */
    public ParkingSpot getSpot(int spot) {
        return this.lot.get(spot);
    }

    /**
     * Is a parking spot open at the moment (not occupied by a vehicle)?
     *
     * @param spot the spot to check
     * @return true if the spot is open, false otherwise
     * @pre.rit the spot is within the range of the lot
     */
    public boolean isSpotVacant(int spot) {
        return (getSpot(spot).getVehicle() == null);
    }

    /**
     * Park a vehicle in a spot. If the spot is already occupied by another vehicle, it cannot be parked in this spot.
     *
     * @param vehicle the vehicle to park
     * @param spot    the spot to park in
     * @return whether the vehicle was parked in this spot or not
     * @pre.rit the spot is within the range of the lot, the vehicle exists and is not already parked
     */
    public boolean parkVehicle(Vehicle vehicle, int spot) {
        if (!isSpotVacant(spot)) {
            return false;
        }
        this.lot.get(spot).occupySpot(vehicle);
        this.parkedVehicles++;
        return true;
    }

    /**
     * Remove a vehicle from a parked spot. If the vehicle is found in a parked spot in the lot, it is removed from the
     * spot.
     *
     * @param vehicle the vehicle to remove
     * @return the spot the vehicle was removed from. if the vehicle was not parked in the lot it returns ILLEGAL_SPOT.
     * @pre.rit the vehicle exists
     */
    public int removeVehicle(Vehicle vehicle) {
        int spot = ILLEGAL_SPOT;
        for (ParkingSpot s : this.lot) {
            if (vehicle.equals(s.getVehicle())) {
                spot = s.getSpot();
                s.vacateSpot();
                this.parkedVehicles--;
            }
        }
        return spot;
    }

    /**
     * Return a string representation of the parking lot. The format is 10 spots per line, with a space between each
     * spot.
     *
     * @return the formatted string of the lot
     */
    @Override
    public String toString() {

        String lotString = "";
        for (ParkingSpot s : this.lot) {
            lotString += (s + (s.getSpot() % SPOTS_PER_LINE == SPOTS_PER_LINE - 1 ? "\n" : " "));
        }

        lotString += (this.capacity % SPOTS_PER_LINE == 0 ? "" : "\n");
        lotString += "Vacant Spots: " + (getCapacity() - getNumParkedVehicles());
        return lotString;
    }

    public static void main(String[] args) {
        // Create a new ParkingLot with 10 handicappedSpots, 20 reservedSpots and 32 generalSpots
        ParkingLot l1 = new ParkingLot(10, 20, 32);
        System.out.println("visually verify l1's toString: \n" + l1);

        // Create a new Vehicle v1 and park it at spot #10 of ParkingLot l1
        Vehicle v1 = new Vehicle(1337);
        System.out.println("Park v1 at spot #10 of l1?: " + (l1.parkVehicle(v1, 10) ? "OK" :
                "FAIL, got: " + l1.parkVehicle(v1, 10)));

        // Create a new Vehicle v2 and park it at spot #10 of v1
        Vehicle v2 = new Vehicle(69);
        System.out.println("Illegally park v2 at spot #10 of l1?: " + (!l1.parkVehicle(v2, 10) ? "OK" :
                "FAIL, got: " + l1.parkVehicle(v2, 10)));

        // Remove Vehicle v1 from spot #10 of ParkingLot l1
        System.out.println("Remove v1 from spot #10 of l1?: " + (l1.removeVehicle(v1) == 10 ? "OK" :
                "FAIL, got: " + l1.removeVehicle(v1)));

        // Try to remove v1 again
        System.out.println("Illegally remove v1 from spot #10 of l1?: " + (l1.removeVehicle(v1) == ILLEGAL_SPOT ? "OK" :
                "FAIL, got: " + l1.removeVehicle(v1)));

        // Test isSpotValid
        System.out.println("Spot 61 is valid in l1?: " + (l1.isSpotValid(61) ? "OK" :
                "FAIL, got: " + l1.isSpotValid(61)));
        System.out.println("Spot 62 is not valid in l1?: " + (!l1.isSpotValid(62) ? "OK" :
                "FAIL, got: " + l1.isSpotValid(62)));
        System.out.println("Spot 31337 is not valid in l1?: " + (!l1.isSpotValid(31337) ? "OK" :
                "FAIL, got: " + l1.isSpotValid(31337)));
        System.out.println("Spot 42 is valid in l1?: " + (l1.isSpotValid(42) ? "OK" :
                "FAIL, got: " + l1.isSpotValid(42)));

        // Create 50 Vehicle and park it in l1
        for (int i = 100; i < 100 + 50; i++) {
            Vehicle v = new Vehicle(i);
            l1.parkVehicle(v, i - 100);
        }
        System.out.println("Number of parked vehicles is 50?: " + (l1.getNumParkedVehicles() == 50 ? "OK" :
                "FAIL, got " + l1.getNumParkedVehicles()));
        System.out.println("visually verify l1's toString: \n" + l1);
    }
}
