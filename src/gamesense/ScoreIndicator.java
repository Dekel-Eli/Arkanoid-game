package gamesense;

import biuoop.DrawSurface;
import java.awt.Color;
import shapesandunits.Sprite;
import shapesandunits.Point;

/**
 * @author Dekel Eli 313162422
 * ScoreIndicator - keeps up with the score of the game.
 */
public class ScoreIndicator implements Sprite {
    private static final Point SCORE_PLACEMENT = new Point(200, 15);
    private static final int SCORE_FONT_SIZE = 15;
    private Counter score;

    /**
     * Constructor.
     * @param score the player's score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText((int) SCORE_PLACEMENT.getX(), (int) SCORE_PLACEMENT.getY(),
                "Score: " + this.score.getValue(), SCORE_FONT_SIZE);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
