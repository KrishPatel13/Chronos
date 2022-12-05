package views;

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
import timeBehaviour.TimeRange;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventEditorView {

    private CalendarView calendarView;
    private Event event;

    private Label editEventLabel = new Label("");

//    private Button completeButton = new Button("Complete!");
    private TextField nameTextField = new TextField("");
    private TextField pointsTextField = new TextField("");
    private TextField descTextField = new TextField("");
    private Button changeTimeButton = new Button("Choose start/end time");
    private DatePicker pointDatePicker = new DatePicker(LocalDate.now());
    private TextField pointTimePicker = new TextField("Time (hh:mm)");
    private DatePicker startDatePicker = new DatePicker(LocalDate.now());
    private TextField startTimePicker = new TextField("Start time (hh:mm)");
    private DatePicker endDatePicker = new DatePicker(LocalDate.now());
    private TextField endTimePicker = new TextField("End time (hh:mm)");
    private Button saveButton = new Button("Save Changes");
    private Label errorLabel = new Label("");

    /**
     * Constructor
     *
     * @param calendarView the application's CalendarView
     */
    public EventEditorView(CalendarView calendarView, Event event) {
        this.calendarView = calendarView;
        this.event = event;

        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(calendarView.stage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setPadding(new Insets(20, 20, 20, 20));

        Font f = new Font(16);
        editEventLabel.setFont(f);
        nameTextField.setFont(f);
        pointsTextField.setFont(f);
        descTextField.setFont(f);
        changeTimeButton.setFont(f);
        pointTimePicker.setFont(f);
        startTimePicker.setFont(f);
        endTimePicker.setFont(f);
        saveButton.setFont(f);
        errorLabel.setFont(f);

        VBox vbox = new VBox(10, editEventLabel, nameTextField, pointsTextField, descTextField, changeTimeButton, pointDatePicker, pointTimePicker, saveButton, errorLabel);
        dialogVbox.getChildren().add(vbox);
        Scene scene = new Scene(dialogVbox, 400, 500);
        dialog.setScene(scene);
        dialog.show();
        dialog.setAlwaysOnTop(true);

        saveButton.setOnAction(e -> editEvent());

        changeTimeButton.setOnAction(e -> changeTime(vbox));

        editEventLabel.setText("Edit Event: " + event.getName());
        nameTextField.setText(event.getName());
        pointsTextField.setText(String.valueOf(event.getPointValue()));
        descTextField.setText(event.getDescription());


        //If event has a time point:
        if(event.getTimeBehaviour() instanceof TimePoint)
        {
            TimePoint tp = (TimePoint) event.getTimeBehaviour();

            //set values of pointDatePicker and pointTimePicker to event's time
            this.pointDatePicker.setValue(tp.getTime().toLocalDate());
            this.pointTimePicker.setText(tp.getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).substring(11,16));
        }


        //If event has a time range:
        else {
            changeTime(vbox);

            //set values of startDatePicker, startTimePicker, endDatePicker and endTimePicker
            TimeRange tr = (TimeRange) event.getTimeBehaviour();

            this.startDatePicker.setValue(tr.getStartTime().toLocalDate());
            this.endDatePicker.setValue(tr.getEndTime().toLocalDate());

            this.startTimePicker.setText(tr.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).substring(11,16));
            this.endTimePicker.setText(tr.getEndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).substring(11,16));
        }
    }


    /**
     * Edit the details of the event and save accordingly.
     */
    private void editEvent() {

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
        this.event.setName(this.nameTextField.getText().trim());


        // Check if the Event's Description is not empty.
        if (this.descTextField.getText().trim().isEmpty() || this.descTextField.getText().trim().isBlank()) {
            this.errorLabel.setText("Please enter the Event's Description. It can NOT be Blank!");
            return;
        }
       this.event.setDescription(this.descTextField.getText().trim());


        String points = String.valueOf(this.pointsTextField.getText()).trim();
        int event_points = 0;
        // Check if the Event's associated Points are of integer type.
        try {
            event_points += Integer.parseInt(points);
        } catch (NumberFormatException e) {
            this.errorLabel.setText("Please enter a Integer value for the points associated with the event.");
            return;
        }
        this.event.setPointValue(event_points);




        if (this.endTimePicker.getText().trim().isBlank() || this.endTimePicker.getText().trim().isEmpty() || this.startTimePicker.getText().trim().equals("Start time (hh:mm)") || this.endTimePicker.getText().trim().equals("End time (hh:mm)")) {
            // Create a new Time Point object based on the deadline of the user.

            String deadline_hours = this.pointTimePicker.getText().trim();

            Pattern pattern = Pattern.compile("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$");
            Matcher matcher = pattern.matcher(deadline_hours);
            if (matcher.find()) {
                // match found, valid deadline HH:mm.
                String yyyy_mm_dd = this.pointDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                // TODO: Consider the case of  the where the deadline is a past date.

                String yyyy_mm_dd_HH_mm = yyyy_mm_dd + " " + deadline_hours;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime pointTime = LocalDateTime.parse(yyyy_mm_dd_HH_mm, formatter);


                // Create a TimePoint object.
                TimeBehaviour tp = new TimePoint(pointTime);

                this.event.setTimeBehaviour(tp);

//                this.calendarView.saveModel();

                //Success Message!
                this.errorLabel.setText("Event Added to the Calendar!");
                this.calendarView.saveModel();
            } else {
                // Invalid Format of HH:mm
                this.errorLabel.setText("Please Re-enter the time of the event. Enter in HH:mm format.");
                return;
            }
        } else {
            // Create a new Time Range object based on the start-time and end-time of the user.

            String start_time = this.startTimePicker.getText().trim();
            String end_time = this.endTimePicker.getText().trim();


            Pattern pattern = Pattern.compile("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$");

            Matcher matcher = pattern.matcher(start_time);
            Matcher matcher1 = pattern.matcher(end_time);

            if (matcher1.find() && matcher.find()) {
                // match found, valid deadline HH:mm.
                String yyyy_mm_dd = this.startDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String yyyy_mm_dd2 = this.endDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                // TODO: Consider the case of  the where the deadline is a past date.

                String yyyy_mm_dd_HH_mm = yyyy_mm_dd + " " + start_time;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime start_time_block = LocalDateTime.parse(yyyy_mm_dd_HH_mm, formatter);

                String yyyy_mm_dd_HH_mm2 = yyyy_mm_dd2 + " " + end_time;
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime end_time_block = LocalDateTime.parse(yyyy_mm_dd_HH_mm2, formatter2);

                System.out.println(start_time_block + " " + end_time_block);


                // Create a TimeRange object.
                TimeBehaviour tr = new TimeRange(start_time_block, end_time_block);

                this.event.setTimeBehaviour(tr);
//                this.calendarView.saveModel();

                //Success Message!
                this.errorLabel.setText("Event Added to the Calendar!");

                this.calendarView.saveModel();
            } else {
                // Invalid Format of HH:mm
                this.errorLabel.setText("Invalid Start/End time of the event. Enter in HH:mm format.");
                return;
            }

        }
    }

    /**
     * Switches between displaying input for time points/time ranges
     *
     * @param vbox
     */
    private void changeTime(VBox vbox) {
        {
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
            } else {
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
            }
            vbox.getChildren().add(saveButton);
            vbox.getChildren().add(errorLabel);
        }
    }
}
