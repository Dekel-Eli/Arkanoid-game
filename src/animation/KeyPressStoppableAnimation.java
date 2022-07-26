package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Dekel Eli 313162422
 * KeyPressStoppableAnimation - a class that wraps an animation
 * that needs a key press to stop.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private String key;
    private Animation animation;
    private boolean running;
    private boolean isAlreadyPressed;

    /**
     * Constructor.
     * @param sensor the keyboard.
     * @param key the key that will stop the animation.
     * @param animation the animation that needs to be played.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.key = key;
        this.animation = animation;
        this.running = true;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (!this.keyboard.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
        if (this.isAlreadyPressed) {
            return;
        }
        if (this.keyboard.isPressed(this.key)) {
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
