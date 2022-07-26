package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import gamesense.SpriteCollection;
import java.awt.Color;

/**
 * @author Dekel Eli 313162422
 * CountdownAnimation - plays a countdown before the levels starts
 * or resumes.
 */
public class CountdownAnimation implements Animation {
    private boolean running;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Sleeper sleeper;
    private boolean firstTime;
    private double wait;

    /**
     * Constructor.
     * @param numOfSeconds the duration of the countdown.
     * @param countFrom the first number that will appear on screen.
     * @param gameScreen the sprites on the screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen) {
        this.running = true;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.sleeper = new Sleeper();
        this.firstTime = true;
        this.wait = (numOfSeconds /  this.countFrom);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        // draw only if there is something to count
        if (this.countFrom != 0) {
            d.setColor(Color.YELLOW);
            d.drawText(390, d.getHeight() / 2, String.valueOf(countFrom), 32);
        } else {
            this.running = false;
        }
        // the method should not sleep for the first doOneFrame of the countdown
        if (!this.firstTime) {
            /* the sleeper in AnimationRunner also sleeps so
               here the sleeper sleeps for less than a second */
            this.sleeper.sleepFor((long) (this.wait * 1000));
        }
        // we passed one frame so the next one won't be the first time
        this.firstTime = false;
        this.countFrom--;
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
