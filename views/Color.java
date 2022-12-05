package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class Color implements Initializable {
    public AnchorPane myPane2;
    public Circle circle2;
    public Text text1;
    public Text text2;
    public AnchorPane myPane;
    @FXML
    private Circle circle;
    @FXML
    private ColorPicker cp;
    @FXML
    private ColorPicker cp2;
    @FXML
    static CalendarView cv;


    public void handleColorPicker(ActionEvent actionEvent) {

        circle.setFill(cp.getValue());
        cv.calendarLayout.setBackground(new Background(new BackgroundFill(cp.getValue(),null,null)));
        cv.realLayout.setBackground(new Background(new BackgroundFill(cp.getValue(),null,null)));

        CalendarView.colour = cp.getValue();
        myPane.setBackground(new Background(new BackgroundFill(CalendarView.colour,null,null)));
        cv.saveModel();
    }
    public void handleFontColor(ActionEvent actionEvent) {
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cp.getStyleClass().add(ColorPicker.STYLE_CLASS_SPLIT_BUTTON);
        myPane.setBackground(new Background(new BackgroundFill(CalendarView.colour,null,null)));

    }



}
