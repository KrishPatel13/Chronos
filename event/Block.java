package event;

import timeBehaviour.TimeBehaviour;
import timeBehaviour.TimeRange;

import java.time.LocalDateTime;

public class Block extends Event{

    private TimeBehaviour event_time;


    /**
     * Constructor for a new Event. A new event requires a name and timeBehaviour, and other attributes can be set later.
     *
     * @param name the name of the new Event
     * @param description the event's description
     * @param points the event's assigned points
     * @param t the Event's time behaviour. Contains the Event's time or start/end times
     */
    public Block(String name, String description, int points, TimeBehaviour t) {
        super(name, description, points, t);
        this.event_time = t;
    }


    /**
     * Change this Event's time with a new time behaviour.
     *
     * @param t the new timeBehaviour
     */
    @Override
    public void performSetTime(TimeBehaviour t)
    {
        this.event_time = t;
    }
}
