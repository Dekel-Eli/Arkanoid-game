package gamesense;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;
import shapesandunits.Sprite;

/**
 * @author Dekel Eli 313162422
 * SpriteCollection - holds a list of sprites in the game.
 * can make the sprites in the game do certain methods.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Default constructor.
     * initialize a list for the sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Constructor.
     * @param sprites the list of sprites in a game.
     */
    public SpriteCollection(List<Sprite> sprites) {
        this.sprites = sprites;
    }

    /**
     * adding a sprite to the list.
     * @param s the new sprite.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<>(this.sprites);
        for (Sprite sprite : spritesCopy) {
            sprite.timePassed();
        }
    }

    /**
     * calls drawOn(d) on all sprites.
     * @param d the draw surface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }

    /**
     * Getter.
     * @return the list of sprites.
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }
}