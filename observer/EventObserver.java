package observer;

/**
 * Interface for Event Observers. Event Observers must be able to add an Event's points to themselves when they are
 * notified that and Event is complete.
 */
public interface EventObserver {

    /**
     * Add points to this observer's current point total.
     *
     * @param points the amount of points to be added
     */
    boolean addPoints(int points);
}
