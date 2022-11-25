package model;

import event.Event;

public class CalendarModel {
    Event[] events;

    public CalendarModel(){
        this.events = new Event[]{};

    }
    public CalendarModel(Event[] events){
        this.events = events;
    }
}
