package event;

import timeBehaviour.TimeBehaviour;

import java.time.LocalDateTime;

public class Deadline extends Event{

    private LocalDateTime deadlineTime;


    /**
     * Constructor for a new Event. A new event requires a name and timeBehaviour, and other attributes can be set later.
     *
     * @param name          the name of the new Event
     * @param timeBehaviour the Event's time behaviour. Contains the Event's time or start/end times
     */
    public Deadline(String name, String description, int points, TimeBehaviour t) {
        super(name, description, points, t);
        this.deadlineTime = t.getDeadlineTime();
    }

    /**
     * Change this Event's time with a new time behaviour.
     *
     * @param t the new timeBehaviour
     */
    @Override
    public void performSetTime(TimeBehaviour t) {

    }
}
