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
 * Color class for handling color picker to choose background and font color as per user's choice
 *
 */
public class Color implements Initializable
{
    public AnchorPane myPane2; // color picker for font change pane
    public Circle circle2; // chosen color reflected shape for font
    public Text text1; // text in color picker window
    public Text text2; // text in color picker window
    public AnchorPane myPane; //  color picker for background change pane
    @FXML
    private Circle circle; // chosen color reflected shape for background
    @FXML
    private ColorPicker cp; // chosen color for background
    @FXML
    private ColorPicker cp2; // chosen color for font
    @FXML
    static CalendarView cv; // calendar view access


    /**
     * Choose a background theme for the application as per choice of user.
     *
     * @param actionEvent application's response if the user chooses his desired background color.
     */
    public void handleColorPicker(ActionEvent actionEvent)
    {
        // Change the fill color for the circle when you pick a new color and get color value from user to change
        // the background color for calendar's background and border layout.
        circle.setFill(cp.getValue());
        cv.calendarLayout.setBackground(new Background(new BackgroundFill(cp.getValue(),null,null)));
        cv.realLayout.setBackground(new Background(new BackgroundFill(cp.getValue(),null,null)));

        //Change the fill color for the circle when you pick a new color and get color value from the user to
        // change background color for color picker window.
        CalendarView.colour = cp.getValue();
        myPane.setBackground(new Background(new BackgroundFill(CalendarView.colour,null,null)));
        cv.saveModel();
    }


    /**
     * Choose a font color for the application as per choice of user.
     *
     * @param actionEvent application's response if user chooses his desired font color.
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
     * Change the style of color picker
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object,or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        cp.getStyleClass().add(ColorPicker.STYLE_CLASS_SPLIT_BUTTON);
        myPane.setBackground(new Background(new BackgroundFill(CalendarView.colour,null,null)));
    }
}
