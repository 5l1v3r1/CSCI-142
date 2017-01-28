package poly.stu;

import java.util.ArrayList;

/**
 * Created by ptnega on 26/01/2017.
 */
public class PolyDerive {
    /**
     * @param poly
     * @return
     */
    public static ArrayList<Integer> computeDerivative(ArrayList<Integer> poly) {
        ArrayList<Integer> derivatives = new ArrayList<Integer>();
        for (int i = 1; i < poly.size(); i++) {
            derivatives.add(poly.get(i) * i);
        }
        if (derivatives.size() == 0) {
            derivatives.add(0);
        }

        return derivatives;
    }
}
