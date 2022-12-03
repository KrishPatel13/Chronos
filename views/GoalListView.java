package views;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import model.CalendarModel;
import event.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import observer.EventObserver;

import java.util.ArrayList;

public class GoalListView {

    CalendarView calendarView;

    /**
     * Constructor
     *
     * @param calendarView the application's CalendarView
     */
    public GoalListView(CalendarView calendarView) {
        this.calendarView = calendarView;

        final Stage dialog = new Stage();
        Label labelOngoing = new Label("Ongoing goals:");
        labelOngoing.setFont(new Font(16));
        Label labelComplete = new Label("Completed goals:");
        labelComplete.setFont(new Font(16));

        ArrayList<String> goalsOngoingList = new ArrayList<>();
        for (EventObserver o : Event.getObserverList()) {
            goalsOngoingList.add(o.toString());
        }
        ObservableList<String> goalsOngoing = FXCollections.observableArrayList(goalsOngoingList);
        ListView<String> listViewOngoing = new ListView<>(goalsOngoing);
        listViewOngoing.setMaxSize(200, 200);

        ArrayList<String> goalsCompleteList = new ArrayList<>();
        for (EventObserver o : CalendarModel.getCompletedGoals()) {
            goalsCompleteList.add(o.toString());
        }
        ObservableList<String> goalsComplete = FXCollections.observableArrayList(goalsCompleteList);
        ListView<String> listViewComplete = new ListView<>(goalsComplete);
        listViewComplete.setMaxSize(200, 200);

        VBox vboxOngoing = new VBox(20);
        vboxOngoing.setPadding(new Insets(20, 20, 20, 20));
        vboxOngoing.getChildren().addAll(labelOngoing, listViewOngoing);
        vboxOngoing.setBackground(new Background(new BackgroundFill(CalendarView.colour,null,null)));

        VBox vboxComplete = new VBox(20);
        vboxComplete.setPadding(new Insets(20, 20, 20, 20));
        vboxComplete.getChildren().addAll(labelComplete, listViewComplete);
        vboxComplete.setBackground(new Background(new BackgroundFill(CalendarView.colour,null,null)));

        HBox hbox = new HBox(20);
        hbox.setPadding(new Insets(20, 20, 20, 20));
        hbox.getChildren().addAll(vboxOngoing, vboxComplete);
        hbox.setBackground(new Background(new BackgroundFill(CalendarView.colour,null,null)));

        Scene scene = new Scene(hbox, 400, 400);
        dialog.setScene(scene);
        dialog.show();
        dialog.setAlwaysOnTop(true);

    }
}
