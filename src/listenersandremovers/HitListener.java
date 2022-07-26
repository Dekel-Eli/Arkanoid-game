package listenersandremovers;

import shapesandunits.Block;
import shapesandunits.Ball;

/**
 * @author Dekel Eli 313162422
 * HitListener - the interface for listeners that preform commands for a hit.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit the block for removal.
     * @param hitter the ball which hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}