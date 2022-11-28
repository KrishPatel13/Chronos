package views;


import event.Event;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.*;
import javafx.stage.Stage;
//import Main;
import model.CalendarModel;

import java.io.IOException;
import java.time.LocalDate;


public class CalendarView {

    Stage stage;
    CalendarModel model;

    String[] months = {"January", "February", "March", "April",
            "May", "June", "July", "August",
            "September", "October", "November", "December"};

    public CalendarView(CalendarModel model, Stage stage){
        this.model = model;
        this.stage = stage;
        initUI();
    }

    private void initUI(){
        this.stage.setTitle("Chronos");

        //Make core screen
        //HBox screen = new HBox(8);
        AnchorPane calendarLayout = new AnchorPane();
        //AnchorPane goalDisplay = new AnchorPane();
        BorderPane realLayout = new BorderPane();

        //make a DatePicker for our calendar, and then set up a display that keeps
        // the calendar always active
        DatePicker calendar = new DatePicker(LocalDate.now());
        DatePickerSkin calendarSkin = new DatePickerSkin(calendar);
        Node calendarDisplay = calendarSkin.getPopupContent();
        calendarDisplay.setScaleX(1.5);
        calendarDisplay.setScaleY(1.5);
        calendarDisplay.setLayoutX(100);
        calendarDisplay.setLayoutY(100);
        calendarLayout.getChildren().add(calendarDisplay);
        calendarLayout.setPrefSize(400, 400);

        //Create the label to display the date
        Label dateDisplay = new Label(calendar.getValue().toString());

        //When a date is selected, update our list of events in the below
        calendar.setOnAction(e ->{
            dateDisplay.setText(calendar.getValue().toString());
        });


        //Create the button to make events
        Button makeEventButton = new Button("Make Event");
        makeEventButton.setScaleX(1.15);
        makeEventButton.setScaleY(1.15);
        makeEventButton.setOnAction(e -> {
            EventCreatorView ecv = new EventCreatorView(this);
        });

        //Create the button to make goals
        Button makeGoalButton = new Button("Make Goal");
        makeGoalButton.setScaleX(1.15);
        makeGoalButton.setScaleY(1.15);
        makeGoalButton.setOnAction(e -> {
            NewGoalView ngv = new NewGoalView(this);
        });

        //Create the button to view
        Button viewGoalButton = new Button("View Goal");
        viewGoalButton.setScaleX(1.15);
        viewGoalButton.setScaleY(1.15);
        viewGoalButton.setOnAction(e -> {
            GoalListView glv = new GoalListView(this);
        });

        //Create Button for changing the theme
        Button changeThemeButton = new Button("Change Theme");
        changeThemeButton.setScaleX(1.15);
        changeThemeButton.setScaleY(1.15);
        changeThemeButton.setOnAction(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(CalendarView.class.getResource("ColorPick.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            stage.setTitle("Chronos");
            stage.setScene(scene);
            stage.show();
        });

        //Create button bar
        HBox buttons = new HBox(10);
        buttons.setPadding(new Insets(30));
        buttons.getChildren().addAll(makeEventButton, makeGoalButton, viewGoalButton, changeThemeButton);
        buttons.setPadding(new Insets(20));

        //Create view for goals
        VBox goalDisplay = new VBox();
        goalDisplay.setPadding(new Insets(20));
        ListView<String> goals = new ListView<>();
        goalDisplay.getChildren().addAll(dateDisplay, goals);

        //put everything together
        realLayout.setCenter(calendarLayout);
        realLayout.setBottom(buttons);
        realLayout.setRight(goalDisplay);


        //Finally, display everything
        Scene scene = new Scene(realLayout);

        this.stage.setScene(scene);
        this.stage.show();

    }

}
