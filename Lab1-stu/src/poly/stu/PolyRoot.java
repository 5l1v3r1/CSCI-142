package poly.stu;

import java.util.ArrayList;

/**
 * Created by ptnega on 26/01/2017.
 */
public class PolyRoot {

    public static final double EPSILON = 0.0001;
    public static final double INITIAL_GUESS = 0.1;
    public static final int MAX_ITERATIONS = 100;

    /**
     * @param poly
     * @return
     */
    public static double computeRoot(ArrayList<Integer> poly) {
        return newtonsMethod(poly, INITIAL_GUESS, 1);
    }

    /**
     * @param poly
     * @param x0
     * @param iter
     * @return
     */
    private static double newtonsMethod(ArrayList<Integer> poly, double x0, int iter) {
        double result = PolyEval.evaluate(poly, x0);

        if (iter >= MAX_ITERATIONS || Math.abs(result) <= EPSILON) {
            return x0;
        }

        double x1 = x0 - PolyEval.evaluate(poly, x0) / PolyEval.evaluate(PolyDerive.computeDerivative(poly), x0);
        return newtonsMethod(poly, x1, iter + 1);
    }
}
