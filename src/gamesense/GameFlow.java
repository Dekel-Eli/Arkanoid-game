package gamesense;

import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import animation.LoseScreen;
import animation.WinScreen;
import biuoop.KeyboardSensor;
import levels.LevelInformation;

import java.util.List;

/**
 * @author Dekel Eli 313162422
 * GameFlow - runs the levels.
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter points;

    /**
     * Constructor.
     * @param ar the runner of animation.
     * @param ks the keyboard sensor.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.points = new Counter();
    }

    /**
     * runs a list of levels.
     * @param levels a list of levels to run.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo,
                    this.keyboardSensor,
                    this.animationRunner, this.points);
            level.initialize();
            while (level.getRemainingBlocks().getValue() != 0
                   && level.getRemainingBalls().getValue() != 0) {
                level.run();
            }
            if (level.getRemainingBalls().getValue() == 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                                    "space", new LoseScreen(this.points.getValue())));
                return;
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                                                            "space", new WinScreen(this.points.getValue())));
    }
}