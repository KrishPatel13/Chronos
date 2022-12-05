package model;

import event.Event;
import javafx.scene.paint.Paint;
import observer.EventObserver;


import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;


public class CalendarModel implements Serializable {
    ArrayList<Event> events;
    public String colour;
    public String colour_font;
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


    public static void setCompletedGoals(ArrayList<EventObserver> oList) {completedGoals = oList;}

    public void addEvent(Event e)
    {
        this.events.add(e);
    }

    public ArrayList<Event> getAllEvents(){
        return this.events;
    }

    public ArrayList<Event> getEventsInTime(LocalDateTime time) {
        ArrayList<Event> filteredEvents = new ArrayList<>();
        for (Event e: this.events){
            if (e.getTime().inTime(time)){
                System.out.println(e.getName());
                filteredEvents.add(e);
            }
        }
        return filteredEvents;
    }
}

