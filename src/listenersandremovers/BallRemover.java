package listenersandremovers;

import shapesandunits.Block;
import shapesandunits.Ball;
import gamesense.GameLevel;
import gamesense.Counter;


/**
 * @author Dekel Eli 313162422
 * BallRemover - a listener for removing balls.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Constructor.
     * @param game the running game.
     * @param remainingBalls counts the removed balls.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}
