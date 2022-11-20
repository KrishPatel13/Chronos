package event;
import observer.*;
import timeBehaviour.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Event{

    private String name; //Title that indicates what the event is. This shows on the calendar.
    private String description; //More detailed description of the event.
    private int pointValue; //How many points are awarded upon completion?
    private TimeBehaviour timeBehaviour;
    private ArrayList<EventObserver> observerList;

    /**
     * Constructor for a new Event. A new event requires a name and timeBehaviour, and other attributes can be set later.
     *
     * @param name the name of the new Event
     * @param timeBehaviour the Event's time behaviour. Contains the Event's time or start/end times
     */
    public Event(String name, TimeBehaviour timeBehaviour) {
        this.name = name;
        this.description = "";
        this.pointValue = 0;
        this.timeBehaviour = timeBehaviour;
        this.observerList = new ArrayList<EventObserver>();
    }

    /**
     * Change this Event's time with a new time behaviour.
     *
     * @param t the new timeBehaviour
     */
    public abstract void performSetTime(TimeBehaviour t);

    /**
     * Set this Event as "completed", and notify observers.
     */
    public abstract void complete();

    /**
     * Add a new goal associated with this Event to the observerList.
     *
     * @param o the observer to be added
     */
    public abstract void addGoal(EventObserver o);


}
