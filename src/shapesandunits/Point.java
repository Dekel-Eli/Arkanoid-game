package shapesandunits;

import java.util.Random;

/**
 * @author Dekel Eli 313162422
 * Task 1.1: A class for a two
 * dimensional point.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructor.
     * @param x x coordinate of the point.
     * @param y y coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Default constructor, initialize on (0,0).
     */
    public Point() {
        this(0, 0);
    }

    /**
     * generates a random point.
     * @param startP top left corner of the border.
     * @param endP bottom right corner of the border.
     * @return a random point.
     */
    public Point generateRandomPoint(Point startP, Point endP) {
        Random rand = new Random();
        return new Point(rand.nextInt((int) endP.x) + startP.x,
                         rand.nextInt((int) endP.y) + startP.y);
    }

    /**
     * the distance of two points.
     * @param other the second point.
     * @return the distance of this point to the other point.
     */
    public double distance(Point other) {
        double d = Math.pow((this.x - other.x), 2) + Math.pow((this.y - other.y), 2);
        return Math.sqrt(d);
    }

    /**
     * Equals.
     * @param other the second point.
     * @return true if its the same point,
     *         false otherwise.
     */
    public boolean equals(Point other) {
        return this.x == other.x && this.y == other.y;
    }

    /**
     * @return the x value of a point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y value of a point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * set for x.
     * @param newX new x value of a point.
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * set for y.
     * @param newY new y value of a point.
     */
    public void setY(double newY) {
        this.y = newY;
    }


    /**
     * checks where is the point compare to a rectangle.
     * @param rect the tested rectangle.
     * @return true if the point is above or below.
     *         false otherwise
     */
    public boolean pointCompareToRectangle(Rectangle rect) {
        // in case the ball is to the left
        if (this.getX() < rect.getUpperLeft().getX()) {
            return false;
        }
        // if the ball is not to the left or the right return true
        return !(this.getX() > rect.getTop().end().getX());
    }
}
