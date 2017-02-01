package poly.stu;

import java.util.ArrayList;

/**
 * Created by ptnega on 26/01/2017.
 * This class can compute the root of a polynomial (whose derivative is non-zero), using Newton's method of successive approximation.
 *
 * @author Duc Phan - ddp3945
 */
public class PolyRoot {

    /**
     * The degree of acceptable error for the root
     */
    public static final double EPSILON = 0.0001;

    /**
     * The initial guess for the root
     */
    public static final double INITIAL_GUESS = 0.1;

    /**
     * The maximum number of iterations to perform for root finding
     */
    public static final int MAX_ITERATIONS = 100;

    /**
     * Compute an estimate for a root of the polynomial, using Newton's method.
     *
     * @param poly A list representing the polynomial, in reverse order.
     * @return An estimated root for the polynomial.
     * @rit.pre poly is not an empty list. Minimally it will contain a constant term., The derivative of poly is non-zero, The evaluation of the derivative of the polynomial at a guessed root is non-zero.
     */
    public static double computeRoot(ArrayList<Integer> poly) {
        return newtonsMethod(poly, INITIAL_GUESS, 1);
    }

    /**
     * Compute an estimate for a root of the polynomial, using Newton's method.
     *
     * @param poly A list representing the polynomial, in reverse order.
     * @param x0   The current guess for the root.
     * @param iter The current iteration being performed
     * @return An estimated root for the polynomial.
     * @rit.pre poly is not an empty list. Minimally it will contain a constant term., The derivative of poly is non-zero, The evaluation of the derivative of the polynomial at a guessed root is non-zero.
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
