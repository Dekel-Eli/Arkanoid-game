package shapesandunits;

import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import gamesense.GameLevel;
import gamesense.Velocity;
import listenersandremovers.HitNotifier;
import listenersandremovers.HitListener;
import other.Utilities;

/**
 * @author Dekel Eli 313162422
 * the Block class - holds information about a block on the screen
 * and can change a velocity of a collidable it interacts with
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private java.awt.Color color;
    private List<HitListener> hitListeners;

    /**
     * Constructor.
     * @param rect the size of the block as a rectangle object.
     * @param color the color of the block.
     */
    public Block(Rectangle rect, java.awt.Color color) {
        this.rectangle = rect;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructor.
     * @param rect the size of the block as a rectangle object.
     * randomizes the color of a block.
     */
    public Block(Rectangle rect) {
        this (rect, Utilities.generateRandomColor());
        this.hitListeners = new ArrayList<>();
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * @return the color of the block.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * notifies the listeners that the block got hit.
     * @param hitter the ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        // if the collision in on top or bottom change dy
        if (this.rectangle.getTop().onLine(collisionPoint) || this.rectangle.getBottom().onLine(collisionPoint)) {
            newVelocity.setDy(-currentVelocity.getDy());
        }
        // if the collision in on right or left change dx
        if (this.rectangle.getRight().onLine(collisionPoint) || this.rectangle.getLeft().onLine(collisionPoint)) {
            newVelocity.setDx(-currentVelocity.getDx());
        }
        this.notifyHit(hitter);
        // on a corner both dx and dy will change
        return newVelocity;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                     (int) (this.rectangle.getBottom().end().getX() - this.rectangle.getUpperLeft().getX()),
                     (int) (this.rectangle.getBottom().end().getY() - this.rectangle.getUpperLeft().getY()));
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * @param game the game to remove the block from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * removes all listeners.
     */
    public void removeAllListeners() {
        this.hitListeners.clear();
    }
}
