package shapesandunits;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dekel Eli 313162422
 * the Rectangle class - has information about the rectangle
 * dimensions and its upperleft corner.
 * The class can return information about
 * the intersection point of a line with the rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Line top;
    private Line bottom;
    private Line right;
    private Line left;

    /**
     * Constructor.
     * @param upperLeft the upper left corner of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     * the fields for the sides of the rectangle are calculated.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        // sets variables for the three other corners to define the sides
        Point upperRight = new Point(upperLeft.getX() + width,
                upperLeft.getY());
        Point bottomLeft = new Point(upperLeft.getX(),
                upperLeft.getY() + height);
        Point bottomRight = new Point(upperLeft.getX() + width,
                upperLeft.getY() + height);
        this.top = new Line(upperLeft, upperRight);
        this.bottom = new Line(bottomLeft, bottomRight);
        this.right = new Line(upperRight, bottomRight);
        this.left = new Line(upperLeft, bottomLeft);
    }

    /**
     * checks where does a line intersect with the rectangle.
     * @param line the tested line for intersections.
     * @return a (possibly empty) List of intersection points
     *         with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> points = new ArrayList<>();
        Point check;
        /* checks which sides of the rectangle intersect
           with the line. adds the intersection point to the list*/
        check = line.intersectionWith(top);
        if (check != null) {
            points.add(check);
        }
        check = line.intersectionWith(bottom);
        if (check != null) {
            points.add(check);
        }
        check = line.intersectionWith(right);
        if (check != null) {
            points.add(check);
        }
        check = line.intersectionWith(left);
        if (check != null) {
            points.add(check);
        }
        return points;
    }

    /**
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return the top of the rectangle.
     */
    public Line getTop() {
        return this.top;
    }

    /**
     * @return the bottom of the rectangle.
     */
    public Line getBottom() {
        return this.bottom;
    }

    /**
     * @return the right side of the rectangle.
     */
    public Line getRight() {
        return this.right;
    }

    /**
     * @return the left side of the rectangle.
     */
    public Line getLeft() {
        return this.left;
    }
}