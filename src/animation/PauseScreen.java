package animation;

import biuoop.DrawSurface;

/**
 * @author Dekel Eli 313162422
 * PauseScreen - an animation for pausing the game.
 */
public class PauseScreen implements Animation {
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(155, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}