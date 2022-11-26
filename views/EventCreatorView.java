package views;

import event.Deadline;
import event.Event;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import timeBehaviour.TimeBehaviour;
import timeBehaviour.TimePoint;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class EventCreatorView {

    CalendarView calendarView;
    private Label createEventLabel = new Label("Create a new event!");
    private TextField nameTextField = new TextField("Name");
    private TextField pointsTextField = new TextField("Points");
    private TextField descTextField = new TextField("Description");
    private Button changeTimeButton = new Button("Choose start/end time");
    private DatePicker pointDatePicker = new DatePicker(LocalDate.now());
    private TextField pointTimePicker = new TextField("Time (hh:mm)");
    private DatePicker startDatePicker = new DatePicker(LocalDate.now());
    private TextField startTimePicker = new TextField("Start time (hh:mm)");
    private DatePicker endDatePicker = new DatePicker(LocalDate.now());
    private TextField endTimePicker = new TextField("End time (hh:mm)");
    private Button saveButton = new Button("Save Event");
    private Label errorLabel = new Label("");


    /**
     * Constructor
     *
     * @param calendarView the application's CalendarView
     */
    public EventCreatorView(CalendarView calendarView) {
        this.calendarView = calendarView;

        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(calendarView.stage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setPadding(new Insets(20, 20, 20, 20));

        Font f = new Font(16);
        createEventLabel.setFont(f);
        nameTextField.setFont(f);
        pointsTextField.setFont(f);
        descTextField.setFont(f);
        changeTimeButton.setFont(f);
        pointTimePicker.setFont(f);
        startTimePicker.setFont(f);
        endTimePicker.setFont(f);
        saveButton.setFont(f);

        saveButton.setOnAction(e -> createEvent());

        VBox vbox = new VBox(10, createEventLabel, nameTextField, pointsTextField, descTextField, changeTimeButton, pointDatePicker, pointTimePicker, saveButton, errorLabel);
        dialogVbox.getChildren().add(vbox);
        Scene scene = new Scene(dialogVbox, 400, 500);
        dialog.setScene(scene);
        dialog.show();
        dialog.setAlwaysOnTop(true);

        changeTimeButton.setOnAction(e -> {
            if (vbox.getChildren().contains(pointDatePicker)) {
                vbox.getChildren().remove(pointDatePicker);
                vbox.getChildren().remove(pointTimePicker);
                vbox.getChildren().remove(saveButton);
                vbox.getChildren().remove(errorLabel);
                pointDatePicker.setValue(LocalDate.now());
                pointTimePicker.setText("Time (hh:mm)");
                changeTimeButton.setText("Choose single time");
                vbox.getChildren().add(startDatePicker);
                vbox.getChildren().add(startTimePicker);
                vbox.getChildren().add(endDatePicker);
                vbox.getChildren().add(endTimePicker);
                vbox.getChildren().add(saveButton);
                vbox.getChildren().add(errorLabel);
            }
            else {
                vbox.getChildren().remove(startDatePicker);
                vbox.getChildren().remove(startTimePicker);
                vbox.getChildren().remove(endDatePicker);
                vbox.getChildren().remove(endTimePicker);
                vbox.getChildren().remove(saveButton);
                vbox.getChildren().remove(errorLabel);
                startDatePicker.setValue(LocalDate.now());
                startTimePicker.setText("Start time (hh:mm)");
                endDatePicker.setValue(LocalDate.now());
                endTimePicker.setText("End time (hh:mm)");
                changeTimeButton.setText("Choose start/end time");
                vbox.getChildren().add(pointDatePicker);
                vbox.getChildren().add(pointTimePicker);
                vbox.getChildren().add(saveButton);
                vbox.getChildren().add(errorLabel);
            }

        });

    }

    /**
     * Create a new Event using the parameters given in the text fields and date pickers.
     * Store this new Event in the CalendarModel's list of Events.
     */
    private void createEvent() throws NumberFormatException{

        // Check if the Event's Name is not empty.
        if(this.nameTextField.getText().trim().isEmpty() || this.nameTextField.getText().trim().isBlank())
        {
            this.errorLabel.setText("Please enter the Event Name. It can NOT be Blank!");
            return;
        }
        String event_name = this.nameTextField.getText().trim();


        // Check if the Event's Description is not empty.
        if(this.descTextField.getText().trim().isEmpty() || this.descTextField.getText().trim().isBlank())
        {
            this.errorLabel.setText("Please enter the Event's Description. It can NOT be Blank!");
            return;
        }
        String event_description = this.descTextField.getText().trim();



        String points = String.valueOf(this.pointsTextField).trim();
        int event_points = 0;
        // Check if the Event's associated Points are of integer type.
        try
        {
            event_points += Integer.parseInt(points);
        }
        catch(NumberFormatException e)
        {
            this.errorLabel.setText("Please enter a Integer value for the points associated with the event.");
            return;
        }




        //Check the type of the time and create time range or time point based on that.
        if(this.endTimePicker.getText().trim().isBlank() || this.endTimePicker.getText().trim().isEmpty())
        {
            // Create a new Time Point object based on the deadline of the user.

            String deadline_hours = this.pointTimePicker.getText().trim();

            Pattern pattern = Pattern.compile("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$");
            Matcher matcher = pattern.matcher(deadline_hours);
            if(matcher.find())
            {
                // match found, valid deadline HH:mm.
                String yyyy_mm_dd = this.pointDatePicker.toString();

                // TODO: Consider the case of  the where the deadline is a past date.


                String yyyy_mm_dd_HH_mm = yyyy_mm_dd+deadline_hours;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime pointTime = LocalDateTime.parse(yyyy_mm_dd_HH_mm, formatter);


                // Create a TimePoint object.
                TimeBehaviour tp = new TimePoint(pointTime);

                // Finally, create a new Event with the new TimePoint deadline time of the event.
                Event e = new Deadline(event_name, event_description, event_points, tp);
                this.calendarView.model.addEvent(e);
            }
            else
            {
                // Invalid Format of HH:mm
                this.errorLabel.setText("Please Re-enter the time of the event. Enter in HH:mm format.");
            }
            return;
        }
        else
        {
            // Create a new Time Range object based on the start-time and end-time of the user.


            String start_time = this.startTimePicker.getText().trim();
            String end_time = this.endTimePicker.getText().trim();



        }

    }
}
