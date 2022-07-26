import animation.AnimationRunner;
import biuoop.KeyboardSensor;
import gamesense.GameFlow;
import levels.DirectHit;
import levels.HotTopic;
import levels.LevelInformation;
import levels.BallsAllOver;
import levels.EliteFour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Dekel Eli 313162422
 * Ass6Game - initializes the game and runs it
 *            according to the arguments.
 */
public class Ass6Game {
    /**
     * The main method for initializing a game.
     * @param args receives the levels it should play.
     *             if none entered play the 4 levels.
     */
    public static void main(String[] args) {
        Map<String, LevelInformation> levels = new HashMap<>();
        levels.put("1", new DirectHit());
        levels.put("2", new BallsAllOver());
        levels.put("3", new HotTopic());
        levels.put("4", new EliteFour());
        List<LevelInformation> play = new ArrayList<>();
        for (String level : args) {
            if (level.equals("1") || level.equals("2")
                    || level.equals("3") || level.equals("4")) {
                play.add(levels.get(level));
            }
        }
        AnimationRunner animationRunner = new AnimationRunner();
        KeyboardSensor keyboardSensor = animationRunner.getGui().getKeyboardSensor();
        GameFlow game = new GameFlow(animationRunner, keyboardSensor);
        if (play.isEmpty()) {
            String[] defaultLevels = {"1", "2", "3", "4"};
            for (String level : defaultLevels) {
                play.add(levels.get(level));
            }
        }
        game.runLevels(play);
        animationRunner.getGui().close();
    }
}
