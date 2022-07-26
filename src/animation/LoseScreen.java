package animation;

import biuoop.DrawSurface;

/**
 * @author Dekel Eli 313162422
 * LoseScreen - shows a lose screen animation.
 */
public class LoseScreen implements Animation {
    private int score;

    /**
     * Constructor.
     * @param score the final score.
     */
    public LoseScreen(int score) {
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(190, d.getHeight() / 2, "Game Over. Your score is " + score, 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
