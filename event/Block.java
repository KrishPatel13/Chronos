package event;

import timeBehaviour.TimeBehaviour;
import timeBehaviour.TimeRange;

import java.time.LocalDateTime;

public class Block extends Event{

    private LocalDateTime startTime; // The Start time of the event.

    private LocalDateTime endTime; // The End Time of the event.


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
        this.startTime = t.getStartTime();
        this.endTime = t.getEndTime();
    }


    /**
     * Change this Event's time with a new time behaviour.
     *
     * @param t the new timeBehaviour
     */
    @Override
    public void performSetTime(TimeBehaviour t)
    {
        this.setTimeBehaviour(t);

        this.startTime = t.getStartTime();

        this.endTime = t.getEndTime();
    }
}
