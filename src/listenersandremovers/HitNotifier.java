package listenersandremovers;

/**
 * @author Dekel Eli 313162422
 * HitNotifier - the interface for listeners that notify about hits.
 */
public interface HitNotifier {
    /**
     * @param hl Add hl as a listener to hit events.
     */
    void addHitListener(HitListener hl);

    /**
     * @param hl remove hl as a listener to hit events.
     */
    void removeHitListener(HitListener hl);
}
