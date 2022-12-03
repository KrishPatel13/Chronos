package event;
import model.CalendarModel;
import observer.*;
import timeBehaviour.*;
import views.GoalCompleteView;

import java.io.Serializable;
import java.util.ArrayList;

public class Event implements Serializable {

    private String name; //Title that indicates what the event is. This shows on the calendar.
    private String description; //More detailed description of the event.
    private int pointValue; //How many points are awarded upon completion?
    private TimeBehaviour timeBehaviour;
    
    private static ArrayList<EventObserver> observerList = new ArrayList<>();

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
        for (EventObserver o : observerList) {
            if (o.addPoints(this.pointValue)) {
                GoalCompleteView gcv = new GoalCompleteView((Goal) o);
                CalendarModel.getCompletedGoals().add(o);
                observerList.remove(o);
            }
        }
    }

    /**
     * Add a new goal associated with this Event to the observerList.
     *
     * @param o the observer to be added
     */
    public void addGoal(EventObserver o) {
        if (!observerList.contains(o)) {
            observerList.add(o);
        }
    }

    /**
     * Get the list of observers for events.
     *
     * @return observerList
     */
    public static ArrayList<EventObserver> getObserverList() {
        return observerList;
    }


    public TimeBehaviour getTime(){return this.timeBehaviour;}


    /**
     * Set the list of observers for events. Useful for loading files.
     *
     * @param oList the list of observers.
     */
    public static void setObserverList(ArrayList<EventObserver> oList) { observerList = oList; }



    public String getName(){
        return this.name;
    }
}
