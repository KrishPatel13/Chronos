package Model;

import observer.EventObserver;

import java.util.ArrayList;

public class CalendarModel {
    private static ArrayList<EventObserver> completedGoals = new ArrayList<>();

    public static ArrayList<EventObserver> getCompletedGoals() {
        return completedGoals;
    }
}
