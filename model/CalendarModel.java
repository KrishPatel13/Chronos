package model;

import event.Event;
import observer.EventObserver;

import java.util.ArrayList;

public class CalendarModel {
    Event[] events;

    static ArrayList<EventObserver> completedGoals = new ArrayList<>();

    public CalendarModel(){
        this.events = new Event[]{};

    }
    public CalendarModel(Event[] events){
        this.events = events;
    }

    public static ArrayList<EventObserver> getCompletedGoals() {return completedGoals;}
}
