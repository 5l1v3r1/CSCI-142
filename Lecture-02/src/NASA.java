/**
 * Created by ptnega on 30/01/2017.
 */
public class NASA {
    public static void main(String[] args) {

        System.out.println(SpaceShip.MAX_WARP_FACTOR);
        System.out.println(SpaceShip.SPEED_OF_LIGHT);

        SpaceShip s1 = new SpaceShip("lol");
        System.out.println("Name: " + s1.getName());
        System.out.println("Distance: " + s1.getDistanceTravelled());
        System.out.println(s1);

        for (int i = 0; i < 5; i++) {
            s1.punchItChewy();
        }


        System.out.println(s1);
        double distance = s1.fly(120);
        System.out.println("Flew: " + distance + "m");
        System.out.println(s1);

        distance = s1.fly(100000000);
        System.out.println("Flew: " + distance + "m");
        System.out.println(s1);

        SpaceShip s2 = new SpaceShip("ggwp");
        System.out.println(s2);

        SpaceShip s3 = new SpaceShip("xss");
        SpaceShip s4 = new SpaceShip("xss");

        System.out.println(s3.equals(s4));
        System.out.println(s3 == s4);
        System.out.println(s3 == s3);
    }
}
