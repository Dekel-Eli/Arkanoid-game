package levels;

import gamesense.Velocity;
import shapesandunits.Block;
import shapesandunits.Sprite;
import shapesandunits.Rectangle;
import shapesandunits.Point;
import shapesandunits.Ball;
import shapesandunits.BorderedBlock;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dekel Eli 313162422
 * DirectHit - the 1st level.
 */
public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocity = new ArrayList<>();
        ballsVelocity.add(Velocity.fromAngleAndSpeed(0, 6));
        return ballsVelocity;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        Background background = new Background();
        background.addPixel(new Block(new Rectangle(new Point(), 800, 600), Color.BLACK));
        for (int i = 88; i >= 28; i -= 10) {
            background.addPixel(new Ball(400, 170, i, Color.RED, null));
            i -= 10;
            background.addPixel(new Ball(400, 170, i, Color.WHITE, null));
        }
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new BorderedBlock(new Rectangle(new Point(390, 160), 20, 20), Color.RED));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
