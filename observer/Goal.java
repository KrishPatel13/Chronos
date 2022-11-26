package observer;

public class Goal implements EventObserver {

    private String name;
    private int currentPoints;
    private int pointsToBadge;

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
