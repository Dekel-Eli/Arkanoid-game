package levels;

import gamesense.Velocity;
import other.Utilities;
import shapesandunits.Block;
import shapesandunits.Point;
import shapesandunits.Ball;
import shapesandunits.Rectangle;
import shapesandunits.Sprite;
import shapesandunits.BorderedBlock;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * @author Dekel Eli 313162422
 * BallsAllOver - the 2nd level.
 */
public class BallsAllOver implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocity = new ArrayList<>();
        for (int i = 12; i <= 60; i += 12) {
            ballsVelocity.add(Velocity.fromAngleAndSpeed(i, 6));
            ballsVelocity.add(Velocity.fromAngleAndSpeed(-i, 6));
        }
        return ballsVelocity;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 680;
    }

    @Override
    public String levelName() {
        return "Balls All Over";
    }

    @Override
    public Sprite getBackground() {
        Background background = new Background();
        Random rand = new Random();
        for (int i = 0; i < 30; i++) {
            Point p = new Point();
            background.addPixel(new Ball(p.generateRandomPoint(p, new Point(800, 600)),
                    rand.nextInt(40 - 10) + 10, null));
        }
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        for (int i = 20; i < 780; i += 40) {
            blocks.add(new BorderedBlock(new Rectangle(new Point(i, 280), 40, 20), Utilities.generateRandomColor()));
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}