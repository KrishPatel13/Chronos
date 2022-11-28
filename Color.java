import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Circle;
import java.net.URL;
import java.util.ResourceBundle;

public class Color implements Initializable {
    @FXML
    private Circle circle;
    @FXML
    private ColorPicker cp;

    public void handleColorPicker(ActionEvent actionEvent) {

        circle.setFill(cp.getValue());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cp.getStyleClass().add(ColorPicker.STYLE_CLASS_SPLIT_BUTTON);
    }
}
