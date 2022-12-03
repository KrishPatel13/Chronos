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
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import observer.Goal;

public class NewGoalView {

    CalendarView calendarView;
    private Label createGoalLabel = new Label("Create a new goal! Enter a name and point value.");
    private TextField goalNameTextField = new TextField("Name");
    private TextField goalPointsTextField = new TextField("Points");
    private Label errorLabel = new Label("");
    private Button saveButton = new Button("Save Goal");

    /**
     * Constructor
     *
     * @param calendarView the application's CalendarView
     */
    public NewGoalView(CalendarView calendarView) {
        this.calendarView = calendarView;

        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(calendarView.stage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setPadding(new Insets(20, 20, 20, 20));

        createGoalLabel.setId("createGoalLabel");
        createGoalLabel.setFont(new Font(16));
        goalNameTextField.setId("goalNameTextField");
        goalNameTextField.setFont(new Font(16));
        goalPointsTextField.setId("goalPointsTextField");
        goalPointsTextField.setFont(new Font(16));
        errorLabel.setId("errorLabel");
        errorLabel.setFont(new Font(16));
        saveButton.setId("saveButton");
        saveButton.setFont(new Font(16));

        saveButton.setOnAction(e -> createGoal());

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
            errorLabel.setText("Goal " + "\"" + goal.getName() + "\"" + " created!");

        }
        catch (NumberFormatException e) {
            errorLabel.setText("Invalid number of points!");
        }

    }
}
