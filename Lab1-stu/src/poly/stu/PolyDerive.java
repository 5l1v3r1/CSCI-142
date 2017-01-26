package poly.stu;
import java.util.ArrayList;

/**
 * Created by ptnega on 26/01/2017.
 */
public class PolyDerive {

    public static ArrayList<Integer> computeDerivative(ArrayList<Integer> poly) {
        ArrayList<Integer> derivatives = new ArrayList<Integer>();
        for (int i = 0; i < poly.size(); i++ ) {
            derivatives.add(poly.get(i) * i);
        }
        return  derivatives;
    }
}
