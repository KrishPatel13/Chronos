package model;

import event.Event;
import observer.EventObserver;

import java.util.ArrayList;


public class CalendarModel {
    ArrayList<Event> events;

    public CalendarModel(){
        this.events = new ArrayList<Event>();

    }
    public CalendarModel(ArrayList<Event> events){
        this.events = events;
    }

    public void addEvent(Event e)
    {
        this.events.add(e);
    }
}

