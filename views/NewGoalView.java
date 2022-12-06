package views;

import event.Event;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import observer.Goal;

import static views.CalendarView.colour;

/**
 * GUI for the user to create new Goals. Allows the user to choose a name and amount of points required to complete the
 * goal.
 */
public class NewGoalView {

    CalendarView calendarView; // The application's CalendarView
    private Paint colour; // The background colour
    private Label createGoalLabel = new Label("Create a new goal! Enter a name and point value."); // Text label at the top of the window
    private TextField goalNameTextField = new TextField("Name"); // Text field to enter the new goal's name
    private TextField goalPointsTextField = new TextField("Points"); // Text field to enter a number of points
    private Label errorLabel = new Label(""); // Label to display whether the goal was created successfully or not
    private Button saveButton = new Button("Save Goal"); // Button to create and save the new goal

    /**
     * Constructor
     *
     * @param calendarView the application's CalendarView
     */
    public NewGoalView(CalendarView calendarView) {
        this.calendarView = calendarView;
        this.colour = CalendarView.colour;

        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(calendarView.stage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setPadding(new Insets(20, 20, 20, 20));

        createGoalLabel.setId("createGoalLabel");
        createGoalLabel.setTextFill(CalendarView.colour_font);
        createGoalLabel.setFont(new Font(16));
        createGoalLabel.setTextFill(CalendarView.colour_font);
        goalNameTextField.setId("goalNameTextField");
        goalNameTextField.setFont(new Font(16));
        goalPointsTextField.setId("goalPointsTextField");
        goalPointsTextField.setFont(new Font(16));
        errorLabel.setId("errorLabel");
        errorLabel.setTextFill(CalendarView.colour_font);
        errorLabel.setFont(new Font(16));
        saveButton.setId("saveButton");
        saveButton.setTextFill(CalendarView.colour_font);
        saveButton.setFont(new Font(16));
        dialogVbox.setBackground(new Background(new BackgroundFill(colour,null,null)));

        saveButton.setOnAction(e -> createGoal());
        errorLabel.setTextFill(CalendarView.colour_font);

        VBox createGoalBox = new VBox(10, createGoalLabel, goalNameTextField, goalPointsTextField, errorLabel, saveButton);
        createGoalBox.setBackground(new Background(new BackgroundFill(CalendarView.colour,null,null)));


        dialogVbox.getChildren().add(createGoalBox);
        dialogVbox.setBackground(new Background(new BackgroundFill(CalendarView.colour,null,null)));
        Scene dialogScene = new Scene(dialogVbox, 400, 400);
        dialog.setScene(dialogScene);
        dialog.show();
        dialog.setAlwaysOnTop(true);
    }


    /**
     * Create a new Goal using the configurations given in goalNameTextField and goalPointsTextField. Add the new Goal
     * to the observerLists of all Events.
     */
    private void createGoal() {
        try {
            int points = Integer.parseInt(goalPointsTextField.getText());

            String name;
            if (!goalNameTextField.getText().isEmpty()) {
                name = goalNameTextField.getText();
            }
            else {
                errorLabel.setText("Enter a name for the goal!");
                return;
            }

            Goal goal = new Goal(name, points);
            Event.getObserverList().add(goal);
            this.calendarView.saveModel();
            errorLabel.setText("Goal " + "\"" + goal.getName() + "\"" + " created!");

        }
        catch (NumberFormatException e) {
            errorLabel.setText("Invalid number of points!");
        }

    }
}