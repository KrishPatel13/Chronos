import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import model.CalendarModel;
import views.CalendarView;

public class Main extends Application{

    CalendarView view;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.view = new CalendarView(new CalendarModel(),primaryStage);
    }
    
}
