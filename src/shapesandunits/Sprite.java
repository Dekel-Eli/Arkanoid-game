package shapesandunits;

import biuoop.DrawSurface;
import gamesense.GameLevel;

/**
 * @author Dekel Eli 313162422
 * Sprite - an interface for the sprites in the program.
 */
public interface Sprite {
    /**
     * drawOn - a method for drawing the sprite on a DrawSurface.
     * @param d the DrawSurface to draw on.
     */
    void drawOn(DrawSurface d);
    /**
     * timePassed - notify the sprite that time has passed.
     */
    void timePassed();
    /**
     * addToGame - adds the sprite to the game.
     * @param g the game that the sprite should be added to.
     */
    void addToGame(GameLevel g);
}