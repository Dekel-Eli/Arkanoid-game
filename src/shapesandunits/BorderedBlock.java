package shapesandunits;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Dekel Eli 313162422
 * BorderedBlock - a block that being drawn with a border.
 */
public class BorderedBlock extends Block {
    /**
     * Constructor.
     * @param rect the dimensions of the block.
     * @param color the color of the block.
     */
    public BorderedBlock(Rectangle rect, Color color) {
        super(rect, color);
    }

    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                        (int) this.getCollisionRectangle().getUpperLeft().getY(),
                        (int) (this.getCollisionRectangle().getBottom().end().getX()
                                - this.getCollisionRectangle().getUpperLeft().getX()),
                        (int) (this.getCollisionRectangle().getBottom().end().getY()
                                - this.getCollisionRectangle().getUpperLeft().getY()));
    }
}
