import java.util.Objects;

/**
 * Created by ptnega on 30/01/2017.
 */
public class SpaceShip {
    public final static long SPEED_OF_LIGHT = 299792458L;
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

    public void punchItChewy() {
        if (this.warpFactor < MAX_WARP_FACTOR) {
            ++this.warpFactor;
        }
    }

    public void fullStop() {
        setWarpFactor(0);
        setVelocity(0);
    }

    public double fly(int time) {
        // velocity V = C * W^3;
        // distance D = V * T;

        double distance = 0;
        setVelocity(SPEED_OF_LIGHT * Math.pow(this.warpFactor, 3));
        distance = this.velocity * time;
        this.distanceTravelled += distance;
        return distance;
    }

    @Override
    public String toString() {
        return "SpaceShip{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", warpFactor=" + this.warpFactor +
                ", velocity=" + this.velocity +
                ", distanceTravelled=" + this.distanceTravelled +
                '}';
    }

    @Override
    public boolean equals(Object other) {
        SpaceShip s = (SpaceShip) other;
        return this.name.equals(s.name);
    }
}
