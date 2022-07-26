package gamesense;

import animation.AnimationRunner;
import animation.Animation;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.LinkedList;
import levels.LevelInformation;
import shapesandunits.Block;
import shapesandunits.Rectangle;
import shapesandunits.Point;
import shapesandunits.Collidable;
import shapesandunits.Sprite;
import shapesandunits.Ball;
import shapesandunits.Paddle;
import listenersandremovers.HitListener;
import listenersandremovers.ScoreTrackingListener;
import listenersandremovers.BallRemover;
import listenersandremovers.BlockRemover;

/**
 * @author Dekel Eli 313162422
 * GameLevel - a class for initializing and running a level.
 * adds all of the collidables and sprites to the game.
 */
public class GameLevel implements Animation {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int DEFAULT_BLOCK_SIZE = 20;
    private static final int BALL_SIZE = 5;
    private static final int FINISHED_LVL = 100;
    private static final Point PADDLE_CENTER = new Point(400, 580);
    public static final Block TOP = new Block(new Rectangle(new Point(), WIDTH, DEFAULT_BLOCK_SIZE), Color.GRAY);
    public static final Block LEFT = new Block(new Rectangle(new Point(0, DEFAULT_BLOCK_SIZE),
                                                DEFAULT_BLOCK_SIZE , HEIGHT - DEFAULT_BLOCK_SIZE), Color.GRAY);
    public static final Block RIGHT = new Block(new Rectangle(new Point(WIDTH - DEFAULT_BLOCK_SIZE, DEFAULT_BLOCK_SIZE),
                                                 DEFAULT_BLOCK_SIZE, HEIGHT - DEFAULT_BLOCK_SIZE), Color.GRAY);
    private static final Block DEATH_REGION = new Block(new Rectangle(new Point(0,
                                               HEIGHT), WIDTH - 2 * DEFAULT_BLOCK_SIZE,
                                                  DEFAULT_BLOCK_SIZE), Color.GRAY);

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter points;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;

    /**
     * Constructor.
     * initialize the fields for the game.
     * @param animationRunner the runner for the animations.
     * @param keyboard the keyboard sensor.
     * @param levelInformation the level that's being played.
     * @param score a counter for the score.
     */
    public GameLevel(LevelInformation levelInformation,
                     KeyboardSensor keyboard,
                     AnimationRunner animationRunner, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter();
        this.remainingBlocks.increase(levelInformation.numberOfBlocksToRemove());
        this.remainingBalls = new Counter();
        this.points = score;
        this.runner = animationRunner;
        this.running = false;
        this.keyboard = keyboard;
        this.levelInformation = levelInformation;
    }

    /**
     * adding a collidable to the environment.
     * @param c the new collidable.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * adding a sprite to the game.
     * @param s the new sprite.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * removing a colliadable from the game.
     * @param c the collidable for removal.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /**
     * removing a sprite from the game.
     * @param s the sprite for removal.
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }

    /**
     * @return the counter of remaining balls.
     */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

    /**
     * @return the counter of remaining blocks.
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * addBalls - creates and adds the ball to the game.
     */
    private void addBalls() {
        Point center = new Point((double) WIDTH / 2, (double) HEIGHT / 2);
        // copy the list to a linked list for it to be more comfortable
        LinkedList<Velocity> velocities = new LinkedList<>(this.levelInformation.initialBallVelocities());
        // if the number of balls is odd we will place one ball on top
        if (this.levelInformation.numberOfBalls() % 2 == 1) {
            Ball top = new Ball(new Point(center.getX(), center.getY() - 10),
                                BALL_SIZE, Color.WHITE, this.environment);
            top.setVelocity(velocities.pollFirst());
            top.addToGame(this);
        }
        int placementX = 30;
        int placementY = 10;
        while (!velocities.isEmpty()) {
            Ball ball1 = new Ball(new Point(center.getX() + placementX, center.getY() + placementY),
                                               BALL_SIZE, Color.white, this.environment);
            ball1.setVelocity(velocities.pollFirst());
            ball1.addToGame(this);
            Ball ball2 = new Ball(new Point(center.getX() - placementX,
                               center.getY() + placementY), BALL_SIZE, Color.white, this.environment);
            ball2.setVelocity(velocities.pollFirst());
            ball2.addToGame(this);
            placementX += 30;
            placementY += 10;
        }
    }

    /**
     * addBlocks - creates and adds the blocks to the game.
     */
    private void addBlocks() {
        addSprite(this.levelInformation.getBackground());
        HitListener blockListener = new BlockRemover(this, this.remainingBlocks);
        HitListener point = new ScoreTrackingListener(points);
        for (Block block : this.levelInformation.blocks()) {
            block.addToGame(this);
            block.addHitListener(blockListener);
            block.addHitListener(point);
        }
    }

    /**
     * initialize - Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        addBlocks();
        addBalls();
        Paddle player = new Paddle(keyboard,
                                   new Rectangle(new Point(PADDLE_CENTER.getX()
                                           - ((double) this.levelInformation.paddleWidth() / 2),
                                           PADDLE_CENTER.getY()), this.levelInformation.paddleWidth(), 20));
        player.addToGame(this);
        TOP.addToGame(this);
        LEFT.addToGame(this);
        RIGHT.addToGame(this);
        addCollidable(DEATH_REGION);
        // listener for remaining balls
        HitListener ballListener = new BallRemover(this, remainingBalls);
        DEATH_REGION.addHitListener(ballListener);
        // counting and printing the score
        ScoreIndicator score = new ScoreIndicator(points);
        score.addToGame(this);
        LevelName levelName = new LevelName(this.levelInformation.levelName());
        levelName.addToGame(this);
    }

    /**
     * run - Run the game -- start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // if for pausing
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", new PauseScreen()));
            this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        }
        // if for finishing a level
        if (this.remainingBlocks.getValue() == 0) {
            this.points.increase(FINISHED_LVL);
            this.running = false;
        }
        // if for losing
        if (this.remainingBalls.getValue() == 0) {
            this.running = false;
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}