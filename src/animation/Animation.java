package animation;

import biuoop.DrawSurface;

/**
 * @author Dekel Eli 313162422
 * Animation - the interface for different animations.
 */
public interface Animation {
    /**
     * places the sprites on the gui for one frame.
     * @param d the DrawSurface to draw the sprites on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return if the animation should stop.
     */
    boolean shouldStop();
}