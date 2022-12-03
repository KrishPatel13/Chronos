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
import java.net.URL;
import java.util.ResourceBundle;

public class Color implements Initializable {
    public AnchorPane myPane2;
    public Circle circle2;
    @FXML
    private Circle circle;
    @FXML
    private ColorPicker cp;
    @FXML
    private Pane myPane;
    static CalendarView cv;

    public void handleColorPicker(ActionEvent actionEvent) {

        circle.setFill(cp.getValue());
        cv.calendarLayout.setBackground(new Background(new BackgroundFill(cp.getValue(),null,null)));
        cv.realLayout.setBackground(new Background(new BackgroundFill(cp.getValue(),null,null)));

        CalendarView.colour = cp.getValue();
        myPane.setBackground(new Background(new BackgroundFill(CalendarView.colour,null,null)));
    }
    public void handleFontColor(ActionEvent actionEvent) {
        circle.setFill(cp.getValue());
        CalendarView.colour_font = cp.getValue();

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cp.getStyleClass().add(ColorPicker.STYLE_CLASS_SPLIT_BUTTON);
        myPane.setBackground(new Background(new BackgroundFill(CalendarView.colour,null,null)));

    }


}
