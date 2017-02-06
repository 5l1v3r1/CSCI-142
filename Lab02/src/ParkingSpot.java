import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * Created by ptnega on 05/02/2017.
 *
 * @author Duc Phan - ddp3945@rit.edu
 */
public class ParkingSpot {

    private static String OCCUPIED_STR;
    private int spot;
    private Permit.Type type;
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
     * @pre.rit there is no vehicle in the spot
     */
    public void occupySpot(Vehicle vehicle) {
        vehicle.setParked(true);
        this.vehicle = vehicle;
    }

    /**
     * Have a vehicle leave a spot and become unparked.
     *
     * @pre.rit there is a vehicle in the spot
     */
    public void vacateSpot() {
        this.vehicle.setParked(false);
        this.vehicle = null;
    }

    /**
     * ##:?
     * Where ## is the spot number, and ? is the spot type (* if occupied, otherwise if unoccupied it is either H for handicapped, R for reserved or G for general.
     *
     * @return the formatted string
     */
    @Override
    public String toString() {
        return "" + getSpot() / 10 + getSpot() % 10 + ":" + ( this.vehicle != null ? "*" : this.type.toString().charAt(0));
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
     * The main test function for the ParkingSpot class.
     *
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        ParkingSpot s1 = new ParkingSpot(56, Permit.Type.HANDICAPPED);
        verifySpot("s1", s1, 56, Permit.Type.HANDICAPPED, null);

        Vehicle v1 = new Vehicle(69);
        s1.occupySpot(v1);
        verifySpot("s1", s1, 56, Permit.Type.HANDICAPPED, v1);

        s1.vacateSpot();
        verifySpot("s1", s1, 56, Permit.Type.HANDICAPPED, null);
    }

}
