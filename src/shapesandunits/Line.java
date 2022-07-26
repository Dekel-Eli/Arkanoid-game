package shapesandunits;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Dekel Eli 313162422
 * Task 1.2: A class for a line segment.
 * includes methods for finding an calculating
 * the intersection of 2 line segments.
 */
public class Line {
    public static final int COLLINEAR = 0;
    public static final int CLOCKWISE = 1;
    public static final int COUNTER_CLOCKWISE = 2;
    private static final double EPSILON = 0.001;

    private Point start;
    private Point end;

    /**
     * Constructor.
     * @param start the start of a line segment.
     * @param end the end of a line segment.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    /**
     * Constructor for receiving coordinates.
     * @param x1 x coordinate of the start of a line segment.
     * @param y1 y coordinate of the start of a line segment.
     * @param x2 x coordinate of the end of a line segment.
     * @param y2 y coordinate of the end of a line segment.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * @return the length of a line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * @return the middle point of a line.
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * @return the start point of a line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point of a line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Calculates the orientation of three point according to their slope.
     * @param p a third point for orientation check.
     * @return the orientation of a point with a line.
     */
    private int orientation(Point p) {
        // the calculation's sign indicates the orientation
        double val = (this.end.getY() - this.start.getY()) * (p.getX() - this.end.getX())
                - (this.end.getX() - this.start.getX()) * (p.getY() - this.end.getY());
        if (val == 0) {
            return COLLINEAR;
        }
        if (val > 0) {
            return CLOCKWISE;
        }
        return COUNTER_CLOCKWISE;
    }

    /**
     * Checks if a point is in a line segment.
     * @param p a third point for check.
     * @return true if p is on the line,
     *         false otherwise.
     */
    public boolean onLine(Point p) {
        // in case we get a very close point to line we need an epsilon to return true
        return p.getX() <= Math.max(this.start.getX(), this.end.getX()) + EPSILON
                && p.getX() >= Math.min(this.start.getX(), this.end.getX()) - EPSILON
                && p.getY() <= Math.max(this.start.getY(), this.end.getY()) + EPSILON
                && p.getY() >= Math.min(this.start.getY(), this.end.getY()) - EPSILON;
    }

    /**
     * Checks if a two lines intersect.
     * @param other the second line.
     * @return true if the lines intersect,
     *         false otherwise.
     */
    public boolean isIntersecting(Line other) {
        // checks if one of the points are equal
        if (this.start.equals(other.start) || this.start.equals(other.end)
                || this.end.equals(other.start) || this.end.equals(other.end)) {
            return true;
        }
        // gather the orientations of each line with a point from the other line
        int or1 = this.orientation(other.start);
        int or2 = this.orientation(other.end);
        int or3 = other.orientation(this.start);
        int or4 = other.orientation(this.end);
        // both pairs of possible orientation needs to be different for an intersection to occur
        if (or1 != or2 && or3 != or4) {
            return true;
        }
        // in case of collinear checks if the forth point is in the range of the first line segment
        if (or1 == COLLINEAR) {
            return this.onLine(other.end);
        }
        if (or2 == COLLINEAR) {
            return this.onLine(other.start);
        }
        if (or3 == COLLINEAR) {
            return other.onLine(this.end);
        }
        if (or4 == COLLINEAR) {
            return other.onLine(this.start);
        }
        // if none of the above happened the lines don't intersect
        return false;
    }

    /**
     * @return the slope of a line.
     */
    private double calSlope() {
        return (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
    }

    /**
     * @return the intersection of a line with the Y axis.
     */
    private double calIntersect() {
        return this.start.getY() - (this.calSlope() * this.start.getX());
    }

    /**
     * Calculates the intersection point if the line is vertical.
     * @param other the non-vertical line.
     * @return the point of intersection.
     */
    private Point calPointWithVertical(Line other) {
        double x = this.start.getX();
        double y = other.calSlope() * this.start.getX() + other.calIntersect();
        return new Point(x, y);
    }

    /**
     * Checks if two lines continue each other,
     * if they do return the common point.
     * @param other the second line.
     * @return the common point,
     *         null if the lines don't continue each other.
     */
    private Point lineContinueOther(Line other) {
        if (this.start.equals(other.start) || this.start.equals(other.end)) {
            return this.start;
        }
        if (this.end.equals(other.start) || this.end.equals(other.end)) {
            return this.end;
        }
        return null;
    }

    /**
     * Checks if two lines intersect,
     * if they do return the intersection point.
     * @param other the second line.
     * @return the point of intersection,
     *         null if the line doesn't intersect.
     */
    public Point intersectionWith(Line other) {
        // in case the lines doesn't intersect or if two vertical lines collide
        if (!isIntersecting(other)
                || (this.start.getX() == this.end.getX() && other.start.getX() == other.end.getX())) {
            return null;
        }
        // if one is vertical
        if (this.start.getX() == this.end.getX()) {
            return this.calPointWithVertical(other);
        }
        // if the other one is vertical
        if (other.start.getX() == other.end.getX()) {
            return other.calPointWithVertical(this);
        }
        // calculates the components of each linear equation
        double m1 = this.calSlope();
        double m2 = other.calSlope();
        if (m1 == m2) {
            return lineContinueOther(other);
        }
        double n1 = this.calIntersect();
        double n2 = other.calIntersect();
        // finding the x coordinate by comparing the equations
        double x = (n2 - n1) / (m1 - m2);
        // inserting the x coordinate in the first linear equation to find the y coordinate
        double y = m1 * x + n1;
        // the coordinates on the frame are integers. round the numbers for easier comparison.
        //x = Math.round(x);
        //y = Math.round(y);
        return new Point(x, y);
    }

    /**
     * equals -- return true is the lines are equal, false otherwise.
     * @param other the second line for comparison.
     * @return true if two line are equal,
     *         false otherwise.
     */
    public boolean equals(Line other) {
        return this.start == other.start && this.end == other.end
                || this.start == other.end && this.end == other.start;
    }

    // Otherwise, return the closest intersection point to the
    // start of the line.
    /**
     * checks the intersection points of a line with a rectangle.
     * @param rect the tested rectangle.
     * @return the closest point of intersection to the start of the line,
     *         null if the line doesn't intersect.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // gather all of the intersection points
        List<Point> points = rect.intersectionPoints(this);
        // If this line does not intersect with the rectangle, return null.
        if (points.size() == 0) {
            return null;
        }
        // make a list of the points' distance from the tested line
        List<Double> distances = new ArrayList<>();
        for (Point point : points) {
            distances.add(this.start.distance(point));
        }
        // find the minimal distance
        int closest = distances.indexOf(Collections.min(distances));
        // return the closest point
        return points.get(closest);
    }
}
