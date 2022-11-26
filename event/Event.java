package event;
import observer.*;
import timeBehaviour.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Event{

    private String name; //Title that indicates what the event is. This shows on the calendar.
    private String description; //More detailed description of the event.
    private int pointValue; //How many points are awarded upon completion?
    private TimeBehaviour timeBehaviour;
    private static ArrayList<EventObserver> observerList;

    /**
     * Constructor for a new Event. A new event requires a name and timeBehaviour, and other attributes can be set later.
     *
     * @param name the name of the new Event
     * @param timeBehaviour the Event's time behaviour. Contains the Event's time or start/end times
     */
    public Event(String name, String description, int points, TimeBehaviour timeBehaviour) {
        this.name = name;
        this.description = description;
        this.pointValue = points;
        this.timeBehaviour = timeBehaviour;
        this.observerList = new ArrayList<EventObserver>();
    }

    /**
     * Change this Event's time with a new time behaviour.
     *
     * @param t the new timeBehaviour
     */
    public  void performSetTime(TimeBehaviour t)
    {
        this.timeBehaviour = t;
    }

    // REMINDER: Complete this once we have a working Calendar & view
    /**
     * Set this Event as "completed", and notify observers.
     */
    public void complete() {
        for (EventObserver o : this.observerList) {
            if (o.addPoints(this.pointValue)) {
                // Tell the view to display a message!
                // Add o to the calendar's list of completed goals!
                this.observerList.remove(o);
            }
        }
    }

    /**
     * Add a new goal associated with this Event to the observerList.
     *
     * @param o the observer to be added
     */
    public void addGoal(EventObserver o) {
        if (!this.observerList.contains(o)) {
            this.observerList.add(o);
        }
    }


}
