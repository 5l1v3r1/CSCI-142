package poly.stu;

import java.util.ArrayList;

/**
 * Created by ptnega on 26/01/2017.
 * This class can compute the derivative of a polynomial.
 *
 * @author Duc Phan - ddp3945
 */
public class PolyDerive {

    /**
     * Computes the derivative for a polynomial.
     *
     * @param poly A list representing the polynomial, in reverse order.
     * @return A polynomial as a list in reverse order.
     * @rit.pre poly is not an empty list. Minimally it will contain a constant term.
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
