package views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;

public class EventCreatorView {

    CalendarView calendarView;
    private Label createEventLabel = new Label("Create a new event!");
    private TextField nameTextField = new TextField("Name");
    private TextField pointsTextField = new TextField("Points");
    private TextField descTextField = new TextField("Description");
    private TextField timePointTextField = new TextField("Time: ");
    private Button changeTimeButton = new Button("Choose start/end time");
    private DatePicker pointDatePicker = new DatePicker(LocalDate.now());
    private DatePicker startDatePicker = new DatePicker(LocalDate.now());
    private DatePicker endDatePicker = new DatePicker(LocalDate.now());


    /**
     * Constructor
     */
    public EventCreatorView(CalendarView calendarView) {
        this.calendarView = calendarView;

        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(calendarView.stage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setPadding(new Insets(20, 20, 20, 20));



    }
}
