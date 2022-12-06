package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Short class description here
 *
 */
public class Color implements Initializable
{
    public AnchorPane myPane2; //
    public Circle circle2; //
    public Text text1; // //
    public Text text2; //
    public AnchorPane myPane; //
    @FXML
    private Circle circle; //
    @FXML
    private ColorPicker cp; //
    @FXML
    private ColorPicker cp2; //
    @FXML
    static CalendarView cv; //


    /**
     * Write ehre
     *
     * @param actionEvent write here
     */
    public void handleColorPicker(ActionEvent actionEvent)
    {
        // write hree
        circle.setFill(cp.getValue());
        cv.calendarLayout.setBackground(new Background(new BackgroundFill(cp.getValue(),null,null)));
        cv.realLayout.setBackground(new Background(new BackgroundFill(cp.getValue(),null,null)));

        // write here
        CalendarView.colour = cp.getValue();
        myPane.setBackground(new Background(new BackgroundFill(CalendarView.colour,null,null)));
        cv.saveModel();
    }


    /**
     * write here
     *
     * @param actionEvent write ehre
     */
    public void handleFontColor(ActionEvent actionEvent) {
        // Colour Picker UI
        circle2.setFill(cp2.getValue());
        CalendarView.colour_font = cp2.getValue();
        cv.makeEventButton.setTextFill(cp2.getValue());
        cv.makeGoalButton.setTextFill(cp2.getValue());
        cv.viewGoalButton.setTextFill(cp2.getValue());
        cv.changeThemeButton.setTextFill(cp2.getValue());
        cv.editButton.setTextFill(cp2.getValue());
        cv.completeEventButton.setTextFill(cp2.getValue());
        cv.dateDisplay.setTextFill(cp2.getValue());
        text1.setFill(cp2.getValue());
        text2.setFill(cp2.getValue());
        cv.saveModel();
    }


    /**
     * write here
     *
     * @param url write here
     * @param resourceBundle write here
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        cp.getStyleClass().add(ColorPicker.STYLE_CLASS_SPLIT_BUTTON);
        myPane.setBackground(new Background(new BackgroundFill(CalendarView.colour,null,null)));
    }
}
