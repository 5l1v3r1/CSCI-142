/**
 * Created by ptnega on 30/01/2017.
 */
public class SpaceShip {
    public final static long SPEED_OF_LIGHT = 299111111L;
    public final static int MAX_WARP_FACTOR = 10;

    private static int nextID = 1;
    private int id;
    private String name;
    private int warpFactor;
    private double velocity;
    private double distanceTravelled;

    public SpaceShip(String name) {
        this.id = nextID++;
        this.name = name;
        this.warpFactor = 0;
        this.velocity = 0.0;
        this.distanceTravelled = 0.0;
    }

    public static long getSpeedOfLight() {
        return SPEED_OF_LIGHT;
    }

    public static int getMaxWarpFactor() {
        return MAX_WARP_FACTOR;
    }

    public static int getNextID() {
        return nextID;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWarpFactor() {
        return warpFactor;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getDistanceTravelled() {
        return distanceTravelled;
    }

    public static void setNextID(int nextID) {
        SpaceShip.nextID = nextID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWarpFactor(int warpFactor) {
        this.warpFactor = warpFactor;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public void setDistanceTravelled(double distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }


}
