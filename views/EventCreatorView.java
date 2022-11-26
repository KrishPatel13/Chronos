package views;

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

import java.time.LocalDate;

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
    private void createEvent() {

    }
}
