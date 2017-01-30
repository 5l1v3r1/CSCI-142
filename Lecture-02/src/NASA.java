/**
 * Created by ptnega on 30/01/2017.
 */
public class NASA {
    public static void main(String[] args) {

        System.out.println(SpaceShip.MAX_WARP_FACTOR);
        System.out.println(SpaceShip.SPEED_OF_LIGHT);

        SpaceShip s1 = new SpaceShip("lol");
        System.out.println(s1.getName());
    }
}
