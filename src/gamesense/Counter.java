package gamesense;

/**
 * @author Dekel Eli 313162422
 * Counter - a class for counting accourences.
 */
public class Counter {
    private int val;

    /**
     * Constructor.
     * sets the counter to zero.
     */
    public Counter() {
        this.val = 0;
    }

    /**
     * adds a number to the current count.
     * @param number the amount to subtract.
     */
    public void increase(int number) {
        this.val += number;
    }

    /**
     * subtracts a number from the current count.
     * @param number the amount to subtract.
     */
    public void decrease(int number) {
        this.val -= number;
    }

    /**
     * @return current count.
     */
    public int getValue() {
        return this.val;
    }
}