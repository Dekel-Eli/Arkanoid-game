package listenersandremovers;

import gamesense.GameLevel;
import gamesense.Counter;
import shapesandunits.Block;
import shapesandunits.Ball;

/**
 * @author Dekel Eli 313162422
 * BlockRemover - BlockRemover is in charge of removing blocks from the game,
 * as well as keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructor.
     * @param game the running game.
     * @param remainingBlocks counts the removed block.
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeAllListeners();
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
    }
}