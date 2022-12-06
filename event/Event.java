package event;

import observer.*;
import timeBehaviour.*;
import model.CalendarModel;
import views.GoalCompleteView;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * An event class that stores the data of an Event.
 */
public class Event implements Serializable
{
    private String name; // name of the event, this shows on the calendar.
    private String description; // detailed description of the event.
    private int pointValue; // the points are awarded upon completion.
    private TimeBehaviour timeBehaviour; // the TimeBehaviour of the event.
    private static ArrayList<EventObserver> observerList = new ArrayList<>(); // the list of EventObservers for a particular event.


    /**
     * Constructor for a new Event. A new event requires a name, description, points and a timeBehaviour.
     *
     * @param name the name of the new Event
     * @param timeBehaviour the Event's time behaviour. Contains the Event's time or start/end times
     * @param description the description of the new Event
     * @param points the points associated with the new Event
     */
    public Event(String name, String description, int points, TimeBehaviour timeBehaviour)
    {
        this.name = name;
        this.description = description;
        this.pointValue = points;
        this.timeBehaviour = timeBehaviour;
    }


    /**
     * Set this Event as "completed", and notify observers.
     */
    public void complete()
    {
        ArrayList<EventObserver> completed = new ArrayList<>();
        for (EventObserver o : observerList)
            if (o.addPoints(this.pointValue))
            {
                GoalCompleteView gcv = new GoalCompleteView((Goal) o);
                CalendarModel.getCompletedGoals().add(o);
                completed.add(o);
            }
        for (EventObserver o : completed)
            observerList.remove(o);
    }


    /**
     * Get the list of observers for events.
     *
     * @return observerList
     */
    public static ArrayList<EventObserver> getObserverList()
    {
        return observerList;
    }

    /**
     * Get the Event's name.
     *
     * @return name
     */
    public String getName()
    {
        return this.name;
    }


    /**
     * Get the Event's description.
     *
     * @return description
     */
    public String getDescription()
    {
        return this.description;
    }


    /**
     * Get the Event's associated points.
     *
     * @return pointValue
     */
    public int getPointValue()
    {
        return this.pointValue;
    }


    /**
     * Get the Event's timebehaviour.
     *
     * @return timeBehaviour
     */
    public TimeBehaviour getTimeBehaviour()
    {
        return this.timeBehaviour;
    }


    /**
     * Set the list of observers for events. Useful for loading files.
     *
     * @param oList the list of observers
     */
    public static void setObserverList(ArrayList<EventObserver> oList)
    {
        observerList = oList;
    }


    /**
     * Set the Event's name to a new name.
     *
     * @param name the new Event's name
     */
    public void setName(String name)
    {
        this.name = name;
    }


    /**
     * Set the Event's description to a new description.
     *
     * @param description the new Event's description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }


    public void setPointValue(int pointValue)
    {
        this.pointValue = pointValue;
    }


    /**
     * Change this Event's time with a new time-behaviour.
     *
     * @param timeBehaviour the new timeBehaviour to set for the event.
     */
    public void setTimeBehaviour(TimeBehaviour timeBehaviour)
    {
        this.timeBehaviour = timeBehaviour;
    }
}
