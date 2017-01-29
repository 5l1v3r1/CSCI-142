package poly.stu;

import java.util.ArrayList;

/**
 * Created by ptnega on 26/01/2017.
 * This class can evaluate a polynomial, and determine whether the polynomial is zero or not.
 *
 * @author Duc Phan - ddp3945
 */

public class PolyEval {

    /**
     * Evaluates a polynomial for a supplied value for x.
     *
     * @param poly A list representing the polynomial, in reverse order.
     * @param x    The value for x.
     * @return The result of the evaluation.
     * @rit.pre poly is not an empty list. Minimally it will contain a constant term.
     */
    public static double evaluate(ArrayList<Integer> poly, double x) {
        double sum = 0.0;
        for (int i = 0; i < poly.size(); i++) {
            sum += Math.pow(x, i) * poly.get(i);
        }
        return sum;
    }

    /**
     * Tells whether a polynomial is zero or not.
     *
     * @param poly A list representing the polynomial, in reverse order.
     * @return True if it is zero, False otherwise.
     * @rit.pre poly is not an empty list. Minimally it will contain a constant term.
     */
    public static boolean isZero(ArrayList<Integer> poly) {
        boolean flag = false;
        for (int i = 0; i < poly.size(); i++) {
            flag = flag || (poly.get(i) != 0);
        }
        return !flag;
    }
}
