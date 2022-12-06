package observer;

import java.io.Serializable;

/**
 * Class for the user's goals, which act as concrete Event Observers. A goal contains a name, amount of points currently
 * gained, and amount of points needed.
 */
public class Goal implements EventObserver, Serializable {

    private String name; // The goal's name
    private int currentPoints; // The amount of points the user has currently earned toward this goal
    private int pointsToBadge; // The amount of points that are required to complete this goal

    /**
     * Constructor for the Goal class. Takes a name and a point value.
     *
     * @param name the name of the Goal
     * @param p the amount of points required for completion
     */
    public Goal(String name, int p) {
        this.name = name;
        this.pointsToBadge = p;
        this.currentPoints = 0;
    }


    /**
     * Add points to this observer's current point total.
     *
     * @param points the amount of points to be added
     * @return true if the goal is complete, or false otherwise
     */
    @Override
    public boolean addPoints(int points) {
        this.currentPoints += points;
        if (this.currentPoints >= this.pointsToBadge) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Get this Goal's name
     *
     * @return this.name
     */
    public String getName() {return this.name;}

    /**
     * Change the name of this Goal.
     *
     * @param n the Goal's new name.
     */
    public void setName(String n) { this.name = n;}

    /**
     * Get the string representation of this goal. If the goal is incomplete, its string representation looks like this:
     * name: currentPoints/pointsToBadge
     * If the goal is complete, its string representation is only the name of the goal.
     *
     * @return the string representation of this goal
     */
    @Override
    public String toString() {
        if (this.currentPoints >= this.pointsToBadge) {
            return this.name;
        }
        else {
            return this.name + ": " + this.currentPoints + "/" + this.pointsToBadge;
        }
    }
}
