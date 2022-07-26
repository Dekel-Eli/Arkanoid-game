package levels;

import gamesense.Velocity;
import shapesandunits.Block;
import shapesandunits.Sprite;
import java.util.List;

/**
 * @author Dekel Eli 313162422
 * LevelInformation - holds info about a single level.
 */
public interface LevelInformation {
    /**
     * @return the number of balls in the game.
     */
    int numberOfBalls();

    /**
     * @return The initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return The paddle's speed.
     */
    int paddleSpeed();

    /**
     * @return the paddle's width.
     */
    int paddleWidth();

    /**
     * @return The level's name.
     */
    String levelName();

    /**
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * @return The Blocks that make up this level, each block contains
     *         its size, color and location.
     */
    List<Block> blocks();

    /**
     * @return Number of blocks that should be removed
     *         before the level is considered to be "cleared".
     */
    int numberOfBlocksToRemove();
}