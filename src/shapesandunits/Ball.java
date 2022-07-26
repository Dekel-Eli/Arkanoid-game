package shapesandunits;

import biuoop.DrawSurface;
import gamesense.Velocity;
import gamesense.GameEnvironment;
import gamesense.GameLevel;
import gamesense.CollisionInfo;
import other.Utilities;

import java.awt.Color;

/**
 * @author Dekel Eli 313162422
 * Task 3.1: A class for a ball. includes
 * basic info and method to implement
 * velocity in an animation.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * Constructor.
     * @param center the center of the ball.
     * @param r the radius of the ball.
     * @param color the color of the ball.
     * @param gameEnvironment the list of collidables in the game.
     * A new ball has velocity 0.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity();
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Constructor without a color. randomize a color.
     * @param center the center of the ball.
     * @param r the radius of the ball.
     * @param gameEnvironment the list of collidables in the game.
     */
    public Ball(Point center, int r, GameEnvironment gameEnvironment) {
        this(center, r, Utilities.generateRandomColor(), gameEnvironment);
    }

    /**
     * Constructor with two coordinates instead of a point.
     * @param x x coordinate of the center of the ball.
     * @param y y coordinate of the center of the ball.
     * @param r the radius of the ball.
     * @param color the color of the ball.
     * @param gameEnvironment the list of collidables in the game.
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this(new Point(x, y), r, color, gameEnvironment);
    }

    /**
     * Accessor.
     * @return x coordinate of the center of the ball.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Accessor.
     * @return y coordinate of the center of the ball.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Accessor.
     * @return the radius of the ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Accessor.
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Get the velocity of the ball.
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * set a new center for the ball.
     * @param newCenter the new center for input.
     */
    public void setCenter(Point newCenter) {
        this.center = newCenter;
    }

    /**
     * set a game environment for the ball.
     * @param env the new environment for input.
     */
    public void setGameEnvironment(GameEnvironment env) {
        this.gameEnvironment = env;
    }

    /**
     * set a velocity.
     * @param v the surface to draw on.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * set a velocity by dx and dy.
     * @param dx movement in x axis.
     * @param dy movement in y axis.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * draw the ball on a given draw surface.
     * @param surface the surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    /**
     * Changes the direction of the ball if it hits an object.
     * Moves the ball according to its velocity.
     */
    public void moveOneStep() {
        Line trajectory = new Line(this.center, this.velocity.applyToPoint(this.center));
        // checks if a collision is going to occur and returns the closest collision
        CollisionInfo check = gameEnvironment.getClosestCollision(trajectory);
        if (check != null) {
            /* if the velocity needs to be changed the method will move
               the ball to the collision and initiate his velocity angle for a bit */
            this.setVelocity(check.collisionObject().hit(this, check.collisionPoint(), this.velocity));
            this.setCenter(new Point(check.collisionPoint().getX() + (0.1 * this.velocity.getDx()),
                                        check.collisionPoint().getY() + (0.1 * this.velocity.getDy())));
            return;
        }
        this.setCenter(this.getVelocity().applyToPoint(this.center));
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.getRemainingBalls().increase(1);
    }

    /**
     * removeFromGame - removes the ball from the game.
     * @param g the game that the ball needs to be removed from.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}

