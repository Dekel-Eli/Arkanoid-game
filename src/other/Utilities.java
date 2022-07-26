package other;

import java.awt.Color;
import java.util.Random;

/**
 * @author Dekel Eli 313162422
 * Utilities - has general static methods.
 */
public class Utilities {
    /**
     * Generate a random color.
     * @return the new generated color.
     */
    public static Color generateRandomColor() {
        Random rand = new Random();
        // each float is randomized to receive a random basic color.
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        // create a color with 3 basic colors.
        return new Color(r, g, b);
    }
}
