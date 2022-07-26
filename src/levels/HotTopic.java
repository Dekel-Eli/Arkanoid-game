package levels;

import gamesense.Velocity;
import other.Utilities;
import shapesandunits.Block;
import shapesandunits.Sprite;
import shapesandunits.Rectangle;
import shapesandunits.Point;
import shapesandunits.BorderedBlock;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dekel Eli 313162422
 * BallsAllOver - the 3rd level.
 */
public class HotTopic implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocity = new ArrayList<>();
        ballsVelocity.add(Velocity.fromAngleAndSpeed(40, 6));
        ballsVelocity.add(Velocity.fromAngleAndSpeed(320, 6));
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
        return "Hot Topic";
    }

    @Override
    public Sprite getBackground() {
        Background background = new Background();
        background.addPixel(new Block(new Rectangle(new Point(), 800, 600), Color.BLACK));
        for (int i = 20; i <= 580; i += 20) {
            for (int j = 20; j <= 760; j += 40) {
                background.addPixel(new Block(new Rectangle(new Point(j, i), 20, 20), Color.pink));
            }
            i += 20;
            for (int j = 40; j <= 770; j += 40) {
                background.addPixel(new Block(new Rectangle(new Point(j, i), 20, 20), Color.pink));
            }
        }
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        // a point to start the building of the blocks on the screen
        Point blockPoint = new Point((800 - 20 - 50), 5 * 20);
        // 2 loops to create lines that decrease the number of block on each row
        for (int i = 12; i > 12 / 2; i--) {
            Color color = Utilities.generateRandomColor();
            for (int j = 1; j <= i; j++) {
                blocks.add(new BorderedBlock(new Rectangle(new Point(blockPoint.getX(), blockPoint.getY()),
                        50, 20), color));
                blockPoint.setX(blockPoint.getX() - 50);
            }
            // move the point to the next row
            blockPoint = new Point((800 - 20 - 50),
                    blockPoint.getY() + 20);
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}