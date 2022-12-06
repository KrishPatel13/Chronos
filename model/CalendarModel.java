package model;

import event.Event;
import observer.EventObserver;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.ArrayList;

// Class to store app information behind the scene
public class CalendarModel implements Serializable {

    // list of events
    ArrayList<Event> events;

    // color settings
    public String colour;
    public String colour_font;

    // list of goals that remain the same
    private static ArrayList<EventObserver> completedGoals = new ArrayList<>();


    // Constructor for no events
    public CalendarModel()
    {
        this.events = new ArrayList<Event>();
    }

    // Constructor if some events are there
    public CalendarModel(ArrayList<Event> events){
        this.events = events;
    }

    // get list of completed goals
    public static ArrayList<EventObserver> getCompletedGoals() {
        return completedGoals;
    }

    // set completed goals
    public static void setCompletedGoals(ArrayList<EventObserver> oList) {completedGoals = oList;}

    // add an event to the calendar
    public void addEvent(Event e)
    {
        this.events.add(e);
    }

    // get the list of all events
    public ArrayList<Event> getAllEvents(){
        return this.events;
    }

    /**
     * get events filtered by a date
     */
    public ArrayList<Event> getEventsInTime(LocalDateTime time)
    {
        ArrayList<Event> filteredEvents = new ArrayList<>();
        for (Event e: this.events)
            if (e.getTimeBehaviour().inTime(time))
            {
                System.out.println(e.getName());
                filteredEvents.add(e);
            }
        return filteredEvents;
    }
}
