package listenersandremovers;

import shapesandunits.Block;
import shapesandunits.Ball;
import gamesense.Counter;

/**
 * @author Dekel Eli 313162422
 * ScoreTrackingListener - updates the score according to block hits.
 */
public class ScoreTrackingListener implements HitListener {
    private static final int BLOCK_HIT = 5;
    private Counter currentScore;

    /**
     * Constructor.
     * @param scoreCounter the counter for the score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(BLOCK_HIT);
    }
}