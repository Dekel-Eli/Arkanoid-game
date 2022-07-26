package animation;

import biuoop.DrawSurface;

/**
 * @author Dekel Eli 313162422
 * WinScreen - shows a win screen animation.
 */
public class WinScreen implements Animation {
    private int score;

    /**
     * Constructor.
     * @param score the final score.
     */
    public WinScreen(int score) {
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(200, d.getHeight() / 2, "You Win! Your score is " + score, 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
