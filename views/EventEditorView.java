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
import timeBehaviour.TimePoint;
import timeBehaviour.TimeRange;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventEditorView {

    private CalendarView calendarView;
    private Event event;
    private Label editEventLabel = new Label("");
    private Button completeButton = new Button("Complete!");
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

        VBox vbox = new VBox(10, editEventLabel, completeButton, nameTextField, pointsTextField, descTextField, changeTimeButton, pointDatePicker, pointTimePicker, saveButton, errorLabel);
        dialogVbox.getChildren().add(vbox);
        Scene scene = new Scene(dialogVbox, 400, 500);
        dialog.setScene(scene);
        dialog.show();
        dialog.setAlwaysOnTop(true);

        saveButton.setOnAction(e -> editEvent());

//        completeButton.setOnAction(e -> completeEvent());


        changeTimeButton.setOnAction(e -> changeTime(vbox));

        editEventLabel.setText("Edit/Complete Event: " + event.getName());
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



//    //TODO: Implement completeEvent()
//    private void completeEvent() {
//    }


    //TODO: Implement editEvent()
    private void editEvent() {


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
                vbox.getChildren().add(saveButton);
                vbox.getChildren().add(errorLabel);
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
                vbox.getChildren().add(saveButton);
                vbox.getChildren().add(errorLabel);
            }
        }
    }
}
