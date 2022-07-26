package gamesense;

import shapesandunits.Point;

/**
 * @author Dekel Eli 313162422
 * Velocity specifies the change in
 * position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private  double dy;

    /**
     * Constructor.
     * @param dx the movement in x axis.
     * @param dy the movement in y axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Default Constructor, initialize with no speed.
     */
    public Velocity() {
        this(0, 0);
    }

    /**
     * Calculating the velocity using a vector.
     * @param angle the direction of the velocity.
     * @param speed the speed of the velocity.
     * @return the dx, dy velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // calculating dx and dy using trigonometric equations
        double dy = -speed * Math.cos(Math.toRadians(angle));
        double dx = speed * Math.sin(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * @return the movement in x axis.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return the movement in y axis.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * sets a new dx.
     * @param newDx the new dx;
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**
     * sets a new dy.
     * @param newDy the new dy;
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    /**
     * Move the center of the ball according to its velocity.
     * @param p center that needs to be moved.
     * @return the moved center.
     */
    public Point applyToPoint(Point p) {
        return new Point(this.dx + p.getX(), this.dy + p.getY());
    }

    /**
     * @return the vector size of velocity according to dx and dy.
     */
    public double velocityToVectorSize() {
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }
}

