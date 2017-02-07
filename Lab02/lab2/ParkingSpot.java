/**
 * Created by ptnega on 05/02/2017.
 *
 * @author Duc Phan - ddp3945@rit.edu
 */
public class ParkingSpot {

    /**
     * used in the string representation of the lot to show an occupied spot
     */
    private static final String OCCUPIED_STR = "*";

    private int spot;

    private Permit.Type type;

    /**
     * the Vehicle object parked in the spot (null if no vehicle)
     */
    private Vehicle vehicle;

    /**
     * Create a new parking spot.
     *
     * @param spot the unique spot number
     * @param type the type of spot
     */
    public ParkingSpot(int spot, Permit.Type type) {
        this.spot = spot;
        this.type = type;
        this.vehicle = null;
    }

    /**
     * Verify a parking spot has the correct spot id, type and vehicle.
     *
     * @param spotVar the name of the variable
     * @param s       the ParkingSpot object to check
     * @param spot    the expected spot id
     * @param type    the expected permit type
     * @param vehicle the expected vehicle (null if none)
     */
    private static void verifySpot(String spotVar, ParkingSpot s, int spot, Permit.Type type, Vehicle vehicle) {
        System.out.println(spotVar + ": spot=" + spot + "? " + (spot == s.getSpot() ? "OK" :
                "FAIL, got: " + s.getSpot() + " expected: " + spot));

        System.out.println(spotVar + ": permit=" + type + "? " + (type == s.getType() ? "OK" :
                "FAIL, got: " + s.getType() + " expected: " + type));

        if (s.getVehicle() == null) {
            System.out.println(spotVar + ": vehicle=null? " + (vehicle == null ? "OK" : "FAIL"));
        } else {
            System.out.println(spotVar + ": vehicle=" + vehicle + "? " + (s.getVehicle().equals(vehicle) ? "OK" :
                    "FAIL"));
            System.out.println(spotVar + " is parked? " + (s.getVehicle().isParked() ? "OK" :
                    "FAIL, got: " + s.getVehicle() + " expected: true"));
        }

        System.out.println("visually verify " + spotVar + "'s toString: " + s);
    }

    /**
     * Get the spot number.
     *
     * @return spot number
     */
    public int getSpot() {
        return this.spot;
    }

    /**
     * Get the spot type.
     *
     * @return spot type
     */
    public Permit.Type getType() {
        return this.type;
    }

    /**
     * Get the vehicle in the spot.
     *
     * @return the vehicle (null if none).
     */
    public Vehicle getVehicle() {
        return this.vehicle;
    }

    /**
     * Have a vehicle take this spot and become parked.
     *
     * @param vehicle - the vehicle to occupy the spot
     * @rit.pre there is no vehicle in the spot
     */
    public void occupySpot(Vehicle vehicle) {
        vehicle.setParked(true);
        this.vehicle = vehicle;
    }

    /**
     * Have a vehicle leave a spot and become unparked.
     *
     * @rit.pre there is a vehicle in the spot
     */
    public void vacateSpot() {
        this.vehicle.setParked(false);
        this.vehicle = null;
    }

    /**
     * ##:? Where ## is the spot number, and ? is the spot type (* if occupied, otherwise if unoccupied it is either H
     * for handicapped, R for reserved or G for general.
     *
     * @return the formatted string
     */
    @Override
    public String toString() {
        return "" + getSpot() / 10 + getSpot() % 10 + ":" + (this.vehicle != null ? OCCUPIED_STR :
                this.type.toString().charAt(0));
    }

    /**
     * The main test function for the ParkingSpot class.
     *
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {

        // Create a new ParkingSpot s1, no vehicle is parked
        ParkingSpot s1 = new ParkingSpot(0, Permit.Type.HANDICAPPED);
        verifySpot("s1", s1, 0, Permit.Type.HANDICAPPED, null);

        // Create a new Vehicle with plate number 69 and park it at s1
        Vehicle v1 = new Vehicle(69);
        s1.occupySpot(v1);
        verifySpot("s1", s1, 0, Permit.Type.HANDICAPPED, v1);

        // Free the ParkingSpot s1
        s1.vacateSpot();
        verifySpot("s1", s1, 0, Permit.Type.HANDICAPPED, null);

        // Create a new Vehicle with plate number 1337 and park it at s1
        Vehicle v2 = new Vehicle(1337);
        s1.occupySpot(v2);
        verifySpot("s1", s1, 0, Permit.Type.HANDICAPPED, v2);

        // Create a new ParkingSpot s2, no vehicle is parked
        ParkingSpot s2 = new ParkingSpot(69, Permit.Type.RESERVED);
        verifySpot("s2", s2, 69, Permit.Type.RESERVED, null);

        // Park Vehicle v1 at ParkingSpot s2
        s2.occupySpot(v1);
        verifySpot("s2", s2, 69, Permit.Type.RESERVED, v1);

        // Free the ParkingSpot s2
        s2.vacateSpot();
        verifySpot("s2", s2, 69, Permit.Type.RESERVED, null);
    }
}
