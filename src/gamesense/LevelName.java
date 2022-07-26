package gamesense;

import biuoop.DrawSurface;
import shapesandunits.Point;
import shapesandunits.Sprite;
import java.awt.Color;

/**
 * @author Dekel Eli 313162422
 * LevelName - prints the level's name.
 */
public class LevelName implements Sprite {
    private static final Point NAME_PLACEMENT = new Point(550, 15);
    private static final int NAME_FONT_SIZE = 15;

    private String levelName;

    /**
     * Constructor.
     * @param name the level's name.
     */
    public LevelName(String name) {
        this.levelName = name;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText((int) NAME_PLACEMENT.getX(), (int) NAME_PLACEMENT.getY(),
                "Level Name: " + this.levelName, NAME_FONT_SIZE);

    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
