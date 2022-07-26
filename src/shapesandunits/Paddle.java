package shapesandunits;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import gamesense.Velocity;
import gamesense.GameLevel;


/**
 * @author Dekel Eli 313162422
 * the Paddle class - the player in the game. can move
 * and interact with moving collidables.
 */
public class Paddle implements Sprite, Collidable {
    private static final int DEFAULT_SPEED = 5;
    private static final int REGIONS = 5;
    private static final int STARTING_ANGLE = 300;
    private static final int NEXT_REGION_ADDITION = 30;
    private static final Color DEFAULT_COLOR = Color.yellow;
    private static final Rectangle DEFAULT_RECTANGLE = new Rectangle(new Point(350, 580), 100, 20);

    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Block block;
    private java.awt.Color color;

    /**
     * Constructor.
     * @param keyboard the object for controlling the paddle.
     * @param rect the dimension and location of the paddle.
     * block is initialized by the other fields.
     */
    public Paddle(KeyboardSensor keyboard, Rectangle rect) {
        this.keyboard = keyboard;
        this.rectangle = rect;
        this.color = DEFAULT_COLOR;
        this.block = new BorderedBlock(rect, color);
    }

    /**
     * Default Constructor.
     * @param keyboard the object for controlling the paddle.
     * The fields color and rectangle are the default data in this class.
     * block is initialized by the other fields.
     */
    public Paddle(KeyboardSensor keyboard) {
        this.keyboard = keyboard;
        this.rectangle = DEFAULT_RECTANGLE;
        this.color = DEFAULT_COLOR;
        this.block = new Block(rectangle, color);
    }

    /**
     * moveLeft - moves the paddle left by changing the rectangle location.
     */
    public void moveLeft() {
        // first we need to check if by moving the paddle is going to go out of borders
        Rectangle rectCheck = new Rectangle(new Point(this.rectangle.getUpperLeft().getX() - DEFAULT_SPEED,
                                            this.rectangle.getUpperLeft().getY()), this.rectangle.getWidth(),
                                            this.rectangle.getHeight());
        Line padCheck = new Line(rectCheck.getTop().end().getX() + 1, rectCheck.getTop().end().getY(),
                                 rectCheck.getTop().start().getX() + 1, rectCheck.getTop().start().getY());
        if (padCheck.closestIntersectionToStartOfLine(GameLevel.LEFT.getCollisionRectangle()) != null) {
            return;
        }
        // updates the paddle location
        this.rectangle = rectCheck;
        this.block = new BorderedBlock(this.rectangle, color);
    }

    /**
     * moveRight - moves the paddle right by changing the rectangle location.
     */
    public void moveRight() {
        // first we need to check if by moving the paddle is going to go out of borders
        Rectangle rectCheck = new Rectangle(new Point(this.rectangle.getUpperLeft().getX() + DEFAULT_SPEED,
                                            this.rectangle.getUpperLeft().getY()), this.rectangle.getWidth(),
                                            this.rectangle.getHeight());
        Line padCheck = new Line(rectCheck.getTop().start().getX() - 1, rectCheck.getTop().start().getY(),
                                 rectCheck.getTop().end().getX() - 1, rectCheck.getTop().end().getY());
        if (padCheck.closestIntersectionToStartOfLine(GameLevel.RIGHT.getCollisionRectangle()) != null) {
            return;
        }
        // updates the paddle location
        this.rectangle = rectCheck;
        this.block = new BorderedBlock(this.rectangle, color);
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.block.drawOn(d);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double velocitySize = currentVelocity.velocityToVectorSize();
        List<Block> segments = new ArrayList<>(REGIONS);
        // the making of the list of sections
        for (int i = 0; i < this.rectangle.getWidth(); i += this.rectangle.getWidth() / REGIONS) {
            Rectangle segment = new Rectangle(new Point(rectangle.getUpperLeft().getX() + i,
                                rectangle.getUpperLeft().getY()),
                          this.rectangle.getWidth() / REGIONS, this.rectangle.getHeight());
            segments.add(new Block(segment, this.color));
        }
        for (int i = 0; i < REGIONS; i++) {
            // hit from top or bottom
            if (collisionPoint.pointCompareToRectangle(segments.get(i).getCollisionRectangle())) {
                /* the middle of the paddle needs to be calculated regularly
                   break the loops so the method will continue to the method hit from block */
                if (i == 2) {
                    break;
                }
                // adds 30 to the degrees for the number of the region to the starting angle
                return Velocity.fromAngleAndSpeed(STARTING_ANGLE + (i * NEXT_REGION_ADDITION), velocitySize);
            }
        }
        // when the ball is going to hit the sides we can use the hit method from block
        return this.block.hit(hitter, collisionPoint, currentVelocity);
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}