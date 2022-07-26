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
 * EliteFour - the 4th level.
 */
public class EliteFour implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocity = new ArrayList<>();
        ballsVelocity.add(Velocity.fromAngleAndSpeed(0, 6));
        ballsVelocity.add(Velocity.fromAngleAndSpeed(45, 6));
        ballsVelocity.add(Velocity.fromAngleAndSpeed(315, 6));
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
        return "Elite Four";
    }

    @Override
    public Sprite getBackground() {
        Background background = new Background();
        background.addPixel(new Block(new Rectangle(new Point(), 800, 600), Color.cyan));
        for (int y = 250; y <= 380; y += 130) {
            background.addPixel(new Block(new Rectangle(new Point(380, y), 40, 10), Color.BLACK));
        }
        for (int y = 260; y <= 370; y += 110) {
            background.addPixel(new Block(new Rectangle(new Point(360, y), 80, 10), Color.BLACK));
            if (y == 260) {
                background.addPixel(new Block(new Rectangle(new Point(380, y), 40, 10), Color.RED));
            } else {
                background.addPixel(new Block(new Rectangle(new Point(380, y), 40, 10), Color.WHITE));
            }
        }
        for (int y = 270; y <= 360; y += 90) {
            background.addPixel(new Block(new Rectangle(new Point(350, y), 100, 10), Color.BLACK));
            if (y == 270) {
                background.addPixel(new Block(new Rectangle(new Point(360, y), 80, 10), Color.RED));
            } else {
                background.addPixel(new Block(new Rectangle(new Point(360, y), 80, 10), Color.WHITE));
            }
        }
        for (int y = 280; y <= 340; y += 60) {
            for (int i = 0; i < 2; i++) {
                background.addPixel(new Block(new Rectangle(new Point(340, y + (10 * i)), 120, 10), Color.BLACK));
                if (y == 280) {
                    background.addPixel(new Block(new Rectangle(new Point(350, y + (10 * i)), 100, 10), Color.RED));
                } else {
                    background.addPixel(new Block(new Rectangle(new Point(350, y + (10 * i)), 100, 10), Color.WHITE));
                }
            }
        }
        for (int y = 300; y <= 330; y += 30) {
            background.addPixel(new Block(new Rectangle(new Point(330, y), 140, 10), Color.BLACK));
            for (int x = 340; x <= 410; x += 70) {
                if (y == 300) {
                    background.addPixel(new Block(new Rectangle(new Point(x, y), 50, 10), Color.RED));
                } else {
                    background.addPixel(new Block(new Rectangle(new Point(x, y), 50, 10), Color.WHITE));
                }
            }
        }
        int y = 310;
        background.addPixel(new Block(new Rectangle(new Point(330, y), 140, 10), Color.BLACK));
        background.addPixel(new Block(new Rectangle(new Point(330, 320), 140, 10), Color.BLACK));
        background.addPixel(new Block(new Rectangle(new Point(340, y), 40, 10), Color.RED));
        background.addPixel(new Block(new Rectangle(new Point(420, y), 40, 10), Color.RED));
        background.addPixel(new Block(new Rectangle(new Point(390, y), 20, 20), Color.WHITE));
        background.addPixel(new Block(new Rectangle(new Point(370, 280), 10, 10), Color.WHITE));
        background.addPixel(new Block(new Rectangle(new Point(360, 290), 10, 10), Color.WHITE));
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int height = 60;
        for (int j = 0; j < 7; j++) {
            for (int i = 20; i < 780; i += 40) {
                blocks.add(new BorderedBlock(new Rectangle(new Point(i, height + (j * 20)),
                        40, 20), Utilities.generateRandomColor()));
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}