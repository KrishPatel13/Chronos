package views;
import event.Event;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class CalendarView {

    Stage stage;

    String[] months = {"January", "February", "March", "April",
            "May", "June", "July", "August",
            "September", "October", "November", "December"};

    public CalendarView(Stage stage){
        this.stage = stage;
        initUI();
    }

    private void initUI(){
        this.stage.setTitle("Chronos");

        //Make core screen
        AnchorPane layout = new AnchorPane();

        /*
        //Make grid representing calendar
        GridPane calendar = new GridPane();
        calendar.setGridLinesVisible(true);
        for (int i = 0; i < 7; i++) {
            ColumnConstraints column = new ColumnConstraints(100);
            calendar.getColumnConstraints().add(column);
        }
        for (int i = 0; i < 5; i++) {
            RowConstraints row = new RowConstraints(75);
            calendar.getRowConstraints().add(row);
        }

        //Instantiate the calendar
        calendar.setLayoutX(200);
        calendar.setLayoutY(150);
        layout.getChildren().add(calendar);

        */

        DatePickerSkin calendar2 = new DatePickerSkin(new DatePicker(LocalDate.now()));
        Node calendarDisplay = calendar2.getPopupContent();
        calendarDisplay.setScaleX(1.5);
        calendarDisplay.setScaleY(1.5);
        calendarDisplay.setLayoutX(450);
        calendarDisplay.setLayoutY(200);
        layout.getChildren().add(calendarDisplay);

        //Create the button to make events
        Button makeEventButton = new Button("Make Event");
        makeEventButton.setLayoutX(200);
        makeEventButton.setLayoutY(600);

        makeEventButton.setOnAction(e -> {

        });

        //Create the button to make goals
        Button makeGoalButton = new Button("Make Goal");
        makeGoalButton.setLayoutX(500);
        makeGoalButton.setLayoutY(600);

        makeGoalButton.setOnAction(e -> {

        });

        //Create the button to view
        Button viewGoalButton = new Button("View Goal");
        viewGoalButton.setLayoutX(825);
        viewGoalButton.setLayoutY(600);

        viewGoalButton.setOnAction(e -> {

        });

        //Create the label to display the month and year
        //Date
        Label monthAndYear = new Label(LocalDate.now().toString());
        monthAndYear.setLayoutX(825);
        monthAndYear.setLayoutY(200);

        //Add all components to the screen
        layout.getChildren().add(makeEventButton);
        layout.getChildren().add(makeGoalButton);
        layout.getChildren().add(viewGoalButton);
        layout.getChildren().add(monthAndYear);



        //Finally, display everything
        Scene scene = new Scene(layout, 1100, 800);

        this.stage.setScene(scene);
        this.stage.show();

    }

}