package observer;


public interface EventObserver {

    /**
     * Add points to this observer's current point total.
     *
     * @param points the amount of points to be added
     */
    boolean addPoints(int points);
}
