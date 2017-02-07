import java.util.ArrayList;

/**
 * Created by ptnega on 06/02/2017.
 *
 * @author Duc Phan - ddp3945@rit.edu
 */
public class ParkingOfficer {

    /**
     * the amount of time the officer will pause for after issuing a ticket
     */
    private static final int PAUSE_TIME = 1000;

    /**
     * the parking lot
     */
    private ParkingLot lot;

    /**
     * the collection of tickets that have been issued to vehicles
     */
    private ArrayList<Ticket> tickets;

    /**
     * Create the parking officer. Initially, there is no lot, and no tickets have been issued yet.
     */
    public ParkingOfficer() {
        this.lot = null;
        this.tickets = new ArrayList<>();
    }

    /**
     * Determine the type of fine a vehicle *would* get if they parked in a spot. If a vehicle is in a handicapped spot
     * and either doesn't have a permit, or a handicapped permit, the fine is Fine.PARKING_HANDICAPPED. If a vehicle is
     * in a reserved spot and either doesn't have a permit, or a handicapped or reserved permit, the fine is
     * Fine.PARKING_RESERVED. If a vehicle is parked in a general spot and does not have a permit, the fine is
     * NO_PERMIT. Otherwise there is no fine, Fine.NO_FINE.
     *
     * @param vehicle the vehicle (with or without a permit)
     * @param spot    the spot
     * @return the fine for parking in that spot, Fine.NO_FINE if there is none
     * @rit.pre the parking lot has been created, the vehicle has been created, the parking spot is in range
     */
    public static Fine getFineVehicleSpot(Vehicle vehicle, ParkingSpot spot) {
        Permit vehiclePermit = vehicle.getPermit();
        Permit.Type spotPermit = spot.getType();

        if (spotPermit == Permit.Type.HANDICAPPED) {
            if (!vehicle.hasPermit() || vehiclePermit.getType() != spotPermit) {
                return Fine.PARKING_HANDICAPPED;
            }
        } else if (spotPermit == Permit.Type.RESERVED) {
            if (!vehicle.hasPermit() || ((vehiclePermit.getType() != spotPermit) || (vehiclePermit.getType() != Permit.Type.HANDICAPPED))) {
                return Fine.PARKING_RESERVED;
            }
        } else if (spotPermit == Permit.Type.GENERAL) {
            if (!vehicle.hasPermit()) {
                return Fine.NO_PERMIT;
            }
        }

        return Fine.NO_FINE;
    }

    /**
     * Connect the parking officer to the parking lot they will be responsible for patrolling.
     *
     * @param lot the parking lot
     * @rit.pre the lot has already been created (is not null)
     */
    public void setParkingLot(ParkingLot lot) {
        this.lot = lot;
    }

    /**
     * Get all the tickets the officer has issued.
     *
     * @return the collection of tickets
     */
    public ArrayList<Ticket> getTickets() {
        return this.tickets;
    }

    /**
     * Issue a ticket to a vehicle with a fine. If a ticket is issued it should print:
     * Issuing ticket to: {vehicle} in spot {spot} for {fine}
     * Where {vehicle} is the Vehicle, {spot} is the spot number and {fine} is the Fine type.
     *
     * @param vehicle the vehicle to get the ticket
     * @param spot    the spot number
     * @param fine    the fine
     * @rit.pre the vehicle exists, there is a fine (not Fine.NO_FINE)
     */
    private void issueTicket(Vehicle vehicle, int spot, Fine fine) {
        Ticket t = new Ticket(vehicle.getPlate(), fine);
        this.tickets.add(t);
        vehicle.giveTicket(t);
        System.out.println("Issuing ticket to: " + vehicle + " in spot " + spot + " for " + fine);
    }

    /**
     * When the officer patrols the lot, they go to each spot (starting at the first until last). If there is a vehicle
     * in the spot, they check whether a fine should be assessed (hint, see the helper method getFineVehicleSpot()). If
     * there is a fine (not Fine.NO_FINE), then a ticket should be issued (see issueTicket()), and then the officer
     * should pause for a second (see RITParking.pause()).
     */
    public void patrolLot() {
        for (int i = 0; i < this.lot.getCapacity(); i++) {
            if (this.lot.isSpotVacant(i)) {
                continue;
            }
            ParkingSpot s = this.lot.getSpot(i);
            Vehicle v = s.getVehicle();
            Fine fine = getFineVehicleSpot(v, s);
            if (fine != Fine.NO_FINE) {
                issueTicket(v, i, fine);
                RITParking.pause(PAUSE_TIME);
            }
        }
    }

    /**
     * A test method for the parking officer.
     *
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {

        // Create new ParkingOfficier o1
        ParkingOfficer o1 = new ParkingOfficer();

        // Create new ParkingLot l1 and assign it to ParkingOfficier o1
        ParkingLot l1 = new ParkingLot(20, 30, 40);
        o1.setParkingLot(l1);

        // Create new Vehicle v1 and illegally park it in spot #55 of ParkingLot l1
        Vehicle v1 = new Vehicle(1);
        l1.parkVehicle(v1, 55);
        System.out.println("Ticket for v1 at spot 55 is NO_PERMIT?: " + (
                getFineVehicleSpot(v1, l1.getSpot(55)) == Fine.NO_PERMIT ? "OK" :
                        "FAIL, got " + getFineVehicleSpot(v1, l1.getSpot(55))));

        // Create new Vehicle v2 and illegally park it in spot #10 of ParkingLot l1
        Vehicle v2 = new Vehicle(2);
        l1.parkVehicle(v2, 10);
        System.out.println("Ticket for v2 at spot 10 is PARKING_HANDICAPPED?: " + (
                getFineVehicleSpot(v2, l1.getSpot(10)) == Fine.PARKING_HANDICAPPED ? "OK" :
                        "FAIL, got " + getFineVehicleSpot(v2, l1.getSpot(10))));

        // Create new Vehicle v3 and illegally park it in spot #25 of ParkingLot l1
        Vehicle v3 = new Vehicle(3);
        l1.parkVehicle(v3, 25);
        System.out.println("Ticket for v3 at spot 25 is PARKING_RESERVED?: " + (
                getFineVehicleSpot(v3, l1.getSpot(25)) == Fine.PARKING_RESERVED ? "OK" :
                        "FAIL, got " + getFineVehicleSpot(v3, l1.getSpot(25))));

        // Create new Vehicle v4 and give HANDICAPPED Permit to it
        // Park v4 legally at spot #00 in ParkingLot l1
        Vehicle v4 = new Vehicle(4);
        Permit p1 = new Permit(1, Permit.Type.HANDICAPPED);
        v4.setPermit(p1);
        l1.parkVehicle(v4, 0);
        System.out.println("Ticket for v4 at spot 25 is NO_FINE?: " + (
                getFineVehicleSpot(v4, l1.getSpot(0)) == Fine.NO_FINE ? "OK" :
                        "FAIL, got " + getFineVehicleSpot(v4, l1.getSpot(0))));

        // Let ParkingOfficier o1 patrol ParkingLot l1
        o1.patrolLot();

        // ParkingOfficier o1 has issued 3 tickets
        System.out.println("o1 has issued 3 tickets?: " + (o1.getTickets().size() == 3 ? "OK" :
                "FAIL, got: " + o1.getTickets().size()));
        System.out.println("o1's issued ticket list:" + o1.getTickets());
    }
}
