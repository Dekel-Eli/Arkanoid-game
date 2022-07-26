package shapesandunits;

import biuoop.DrawSurface;
import gamesense.Velocity;

/**
 * @author Dekel Eli 313162422
 * Collidable - an interface for the collidables in the program.
 */
public interface Collidable {
    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * hit - a method for changing the velocity of a moving collidable.
     * according to the collsion with another object.
     * @param hitter the ball that hit the block.
     * @param collisionPoint the point of collision.
     * @param currentVelocity the velocity of the moving object.
     * @return the new velocity after collision.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * drawOn - a method for drawing the collidable on a DrawSurface.
     * @param d the DrawSurface to draw on.
     */
    void drawOn(DrawSurface d);
}