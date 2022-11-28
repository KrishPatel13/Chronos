import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import model.CalendarModel;
import views.CalendarView;


public class Main extends Application
{

    CalendarView view;


    public static void main(String[] args)
    {
        launch(args);
    }
    
    
    @Override
    public void start(Stage stage) throws Exception
    {
        this.view = new CalendarView(new CalendarModel(),stage);
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ColorPick.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("Chronos");
//        stage.setScene(scene);
//        stage.show();
    }
    
}

