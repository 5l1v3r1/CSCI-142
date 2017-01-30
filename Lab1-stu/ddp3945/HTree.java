import turtle.Turtle;

/**
 * Created by ptnega on 26/01/2017.
 * HTree class generates an "h-tree" using recursion and Turtle graphics.
 *
 * @author Duc Phan - ddp3945
 */
public class HTree {
    /**
     * The length of the largest segment
     */
    private static int MAX_SEGMENT_LENGTH = 1024;

    /**
     * Initialize the graphics.
     *
     * @param length The length of one segment of the HTree.
     * @param depth  The depth of recursion.
     * @return A turtle object to draw with.
     */
    public static Turtle init(int length, int depth) {
        Turtle t = new Turtle(0.0, 0.0, 0.0);
        t.setWorldCoordinates(-length * 2, -length * 2, length * 2, length * 2);
        t.setCanvasTitle("H-Tree, depth: " + depth);
        return t;
    }

    /**
     * Recursively draw the H-Tree.
     *
     * @param t      the turtle object
     * @param length the length of the current h-tree segments
     * @param depth  the current depth of recursion
     * @pre.rit depth is greater than or equal to 0, turtle is at center of current h-tree, down, facing east
     * @post.rit turtle is at center of current h-tree, down, facing east
     */
    public static void drawHTree(Turtle t, int length, int depth) {
        if (depth > 0) {
            // start in center of H, move to upper right
            t.goForward(length / 2);
            t.turnLeft(90.0);
            t.goForward(length / 2);
            t.turnRight(90.0);

            // recurse
            drawHTree(t, length / 2, depth - 1);

            // move to lower right of H
            t.turnRight(90.0);
            t.goForward(length);
            t.turnLeft(90.0);

            // recurse
            drawHTree(t, length / 2, depth - 1);

            // move to upper left of H
            t.turnLeft(90.0);
            t.goForward(length / 2);
            t.turnLeft(90.0);
            t.goForward(length);
            t.turnRight(90.0);
            t.goForward(length / 2);
            t.turnRight(90.0);

            // recurse
            drawHTree(t, length / 2, depth - 1);

            // move to lower left of H
            t.turnRight(90.0);
            t.goForward(length);
            t.turnLeft(90.0);

            // recurse
            drawHTree(t, length / 2, depth - 1);

            // return to the center of H
            t.turnLeft(90.0);
            t.goForward(length / 2);
            t.turnRight(90.0);
            t.goForward(length / 2);
        }
    }

    /**
     * The main method reads the command line argument for the depth and draws the h-tree.
     *
     * @param args The depth of recursion (an integer)
     */
    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("Usage: java HTree #");
            return;
        }

        int depth = Integer.parseInt(args[0]);
        if (depth < 0) {
            System.out.println("The depth must be greater than or equal to 0");
            return;
        }

        Turtle t = init(MAX_SEGMENT_LENGTH, depth);
        drawHTree(t, MAX_SEGMENT_LENGTH, depth);

        System.out.println("Close the window to exit program...");
    }
}
