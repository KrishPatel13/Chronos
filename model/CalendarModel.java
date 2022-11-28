package model;

import event.Event;
import observer.EventObserver;

import java.util.ArrayList;
import java.util.Calendar;


public class CalendarModel {
    ArrayList<Event> events;
    private static ArrayList<EventObserver> completedGoals = new ArrayList<>();

    public CalendarModel(){
        this.events = new ArrayList<Event>();

    }
    public CalendarModel(ArrayList<Event> events){
        this.events = events;
    }

    public static ArrayList<EventObserver> getCompletedGoals() {
        return completedGoals;
    }

    public void addEvent(Event e)
    {
        this.events.add(e);
    }
}

