package levels;

import biuoop.DrawSurface;
import gamesense.GameLevel;
import shapesandunits.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dekel Eli 313162422
 * Background - a sprite representing the background.
 */
public class Background implements Sprite {
    private List<Sprite> pixels;

    /**
     * Constructor.
     * creates a new list of sprites.
     */
    public Background() {
        this.pixels = new ArrayList<>();
    }

    @Override
    public void drawOn(DrawSurface d) {
        for (Sprite pixel : this.pixels) {
            pixel.drawOn(d);
        }
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * adding a shape to draw on the background.
     * @param pixel the shape it should add.
     */
    public void addPixel(Sprite pixel) {
        this.pixels.add(pixel);
    }
}
