package views;

import event.Event;
import timeBehaviour.TimeBehaviour;
import timeBehaviour.TimePoint;
import timeBehaviour.TimeRange;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A EventCreatorView class for handling the creation of events.
 */
public class EventCreatorView {
    private final CalendarView calendarView; // the calendar view
    private final Button changeTimeButton = new Button("Choose start/end time"); // Button for changing the type of time of the event
    private final DatePicker pointDatePicker = new DatePicker(LocalDate.now()); // DatePicker for TimePoint event
    private final TextField pointTimePicker = new TextField("Time (hh:mm)"); // Deadline time for TimePoint event
    private final DatePicker startDatePicker = new DatePicker(LocalDate.now()); // Start time DatePicker for TimeRange event
    private final TextField startTimePicker = new TextField("Start time (hh:mm)"); // Start time for TimeRange event
    private final DatePicker endDatePicker = new DatePicker(LocalDate.now()); // End time DatePicker for TimeRange event
    private final TextField endTimePicker = new TextField("End time (hh:mm)"); // End time for TimeRange event
    private final Button saveButton = new Button("Save Changes"); // Button for Saving the edited event
    private final Label errorLabel = new Label(""); // the error message to display

    private final TextField nameTextField = new TextField("Name"); // the name of the event
    private final TextField pointsTextField = new TextField("Points"); // the points associated with the event
    private final TextField descTextField = new TextField("Description"); // the description of the event

    /**
     * Constructor for the EventCreatorView Class.
     *
     * @param calendarView the application's CalendarView
     */
    public EventCreatorView(CalendarView calendarView) {
        this.calendarView = calendarView;


        // Set up the stage
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(calendarView.stage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setBackground(new Background(new BackgroundFill(CalendarView.colour,null,null)));
        dialogVbox.setPadding(new Insets(20, 20, 20, 20));



        // Font UI
        Font f = new Font(16);
        Label createEventLabel = new Label("Create a new event!");
        createEventLabel.setFont(f);
        nameTextField.setFont(f);
        pointsTextField.setFont(f);
        descTextField.setFont(f);
        changeTimeButton.setFont(f);
        pointTimePicker.setFont(f);
        pointDatePicker.setValue(this.calendarView.calendar.getValue());
        startTimePicker.setFont(f);
        startDatePicker.setValue(this.calendarView.calendar.getValue());
        endDatePicker.setValue(this.calendarView.calendar.getValue());
        endTimePicker.setFont(f);
        saveButton.setFont(f);



        // Buttons, TextFields, and Labels UI.
        createEventLabel.setTextFill(CalendarView.colour_font);
        changeTimeButton.setTextFill(CalendarView.colour_font);
        saveButton.setTextFill(CalendarView.colour_font);
        VBox vbox = new VBox(10, createEventLabel, nameTextField, pointsTextField, descTextField, changeTimeButton, pointDatePicker, pointTimePicker, saveButton, errorLabel);
        dialogVbox.getChildren().add(vbox);
        Scene scene = new Scene(dialogVbox, 400, 500);
        dialog.setScene(scene);
        dialog.show();
        dialog.setAlwaysOnTop(true);



        // On Action Function Calls.
        saveButton.setOnAction(e -> createEvent());
        changeTimeButton.setOnAction(e -> {
            if (vbox.getChildren().contains(pointDatePicker)) {
                vbox.getChildren().remove(pointDatePicker);
                vbox.getChildren().remove(pointTimePicker);
                vbox.getChildren().remove(saveButton);
                vbox.getChildren().remove(errorLabel);
                pointDatePicker.setValue(this.calendarView.calendar.getValue());
                pointTimePicker.setText("Time (hh:mm)");
                changeTimeButton.setText("Choose single time");
                vbox.getChildren().add(startDatePicker);
                vbox.getChildren().add(startTimePicker);
                vbox.getChildren().add(endDatePicker);
                vbox.getChildren().add(endTimePicker);
            } else {
                vbox.getChildren().remove(startDatePicker);
                vbox.getChildren().remove(startTimePicker);
                vbox.getChildren().remove(endDatePicker);
                vbox.getChildren().remove(endTimePicker);
                vbox.getChildren().remove(saveButton);
                vbox.getChildren().remove(errorLabel);
                startDatePicker.setValue(this.calendarView.calendar.getValue());
                startTimePicker.setText("Start time (hh:mm)");
                endDatePicker.setValue(this.calendarView.calendar.getValue());
                endTimePicker.setText("End time (hh:mm)");
                changeTimeButton.setText("Choose start/end time");
                vbox.getChildren().add(pointDatePicker);
                vbox.getChildren().add(pointTimePicker);
            }
            vbox.getChildren().add(saveButton);
            vbox.getChildren().add(errorLabel);

        });

    }

    /**
     * Create a new Event using the parameters given in the text fields and date pickers.
     * Store this new Event in the CalendarModel's list of Events.
     *
     * @throws NumberFormatException for invalid input of integer in points field.
     */
    private void createEvent() throws NumberFormatException {

        // Check if the Event's Name is not empty.
        if (this.nameTextField.getText().trim().isEmpty() || this.nameTextField.getText().trim().isBlank()) {
            this.errorLabel.setText("Please enter the Event Name. It can NOT be Blank!");
            return;
        }
        // Check if the event name is already in the events list.
        else
        {
            String temp = this.nameTextField.getText().trim();
            for(Event i: this.calendarView.model.getAllEvents())
            {
                if (temp.equals(i.getName()))
                {
                    this.errorLabel.setText("Event Name already taken!");
                    return;
                }
            }
        }
        // store the valid name
        String event_name = this.nameTextField.getText().trim();


        // Check if the Event's Description is not empty.
        if (this.descTextField.getText().trim().isEmpty() || this.descTextField.getText().trim().isBlank()) {
            this.errorLabel.setText("Please enter the Event's Description. It can NOT be Blank!");
            return;
        }
        //Store the valid description
        String event_description = this.descTextField.getText().trim();


        String points = String.valueOf(this.pointsTextField.getText()).trim();
        int event_points = 0;
        // Check if the Event's associated Points are of integer type.
        try {
            event_points += Integer.parseInt(points); // store the valid points associated with the event.
        }
        catch (NumberFormatException e)
        {
            this.errorLabel.setText("Please enter a Integer value for the points associated with the event.");
            return;
        }


        //Check the type of the time and create time range or time point based on that.
        if (this.endTimePicker.getText().trim().isBlank() || this.endTimePicker.getText().trim().isEmpty() || this.startTimePicker.getText().trim().equals("Start time (hh:mm)") || this.endTimePicker.getText().trim().equals("End time (hh:mm)")) {
            // Create a new Time Point object based on the deadline of the user.

            String deadline_hours = this.pointTimePicker.getText().trim();

            Pattern pattern = Pattern.compile("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$");
            Matcher matcher = pattern.matcher(deadline_hours);
            if (matcher.find()) {
                // match found, valid deadline HH:mm.
                String yyyy_mm_dd = this.pointDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String yyyy_mm_dd_HH_mm = yyyy_mm_dd + " " + deadline_hours;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime pointTime = LocalDateTime.parse(yyyy_mm_dd_HH_mm, formatter);


                // Create a TimePoint object.
                TimeBehaviour tp = new TimePoint(pointTime);

                // Finally, create a new Event with the new TimePoint deadline time of the event.
                Event e = new Event(event_name, event_description, event_points, tp);


                // Add the event to the events list of CalendarView.model.events
                this.calendarView.model.addEvent(e);

                // Save the event
                this.calendarView.saveModel();


                //Success Message!
                this.errorLabel.setText("Event Added to the Calendar!");
            }
            else // Invalid Format of HH:mm
                this.errorLabel.setText("Please Re-enter the time of the event. Enter in HH:mm format.");
        }
        else
        {
            // Create a new Time Range object based on the start-time and end-time of the user.

            String start_time = this.startTimePicker.getText().trim();
            String end_time = this.endTimePicker.getText().trim();


            Pattern pattern = Pattern.compile("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$");
            Matcher matcher = pattern.matcher(start_time);
            Matcher matcher1 = pattern.matcher(end_time);

            if (matcher1.find() && matcher.find())
            {
                // match found, valid deadline HH:mm.
                String yyyy_mm_dd = this.startDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String yyyy_mm_dd2 = this.endDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String yyyy_mm_dd_HH_mm = yyyy_mm_dd + " " + start_time;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime start_time_block = LocalDateTime.parse(yyyy_mm_dd_HH_mm, formatter);

                String yyyy_mm_dd_HH_mm2 = yyyy_mm_dd2 + " " + end_time;
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime end_time_block = LocalDateTime.parse(yyyy_mm_dd_HH_mm2, formatter2);

                System.out.println(start_time_block + " " + end_time_block);


                // Create a TimeRange object.
                TimeBehaviour tr = new TimeRange(start_time_block, end_time_block);

                // Finally, create a new Event with the new TimeRange start and end time of the event block.
                Event e = new Event(event_name, event_description, event_points, tr);


                // Add the event to the events list of CalendarView.model.events
                this.calendarView.model.addEvent(e);

                // Save the event
                this.calendarView.saveModel();

                //Success Message!
                this.errorLabel.setText("Event Added to the Calendar!");
            }
            else // Invalid Format of HH:mm
                this.errorLabel.setText("Invalid Start/End time of the event. Enter in HH:mm format.");
        }
    }
}
