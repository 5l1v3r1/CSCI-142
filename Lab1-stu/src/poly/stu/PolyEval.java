package poly.stu;
import java.util.ArrayList;

/**
 * Created by ptnega on 26/01/2017.
 */

public class PolyEval {

    public static double evaluate(ArrayList<Integer> poly, double x) {
        double sum = 0.0;
        for (int i = 0; i < poly.size(); i++) {
            sum += Math.pow(x, i) * poly.get(i);
        }
        return sum;
    }

    public static boolean isZero(ArrayList<Integer> poly) {
        boolean flag = true;
        for (int i = 0; i < poly.size(); i++) {
            flag = flag && (poly.get(i) != 0);
        }
        return flag;
    }
}
