import javafx.application.Application;
import javafx.stage.Stage;
import model.CalendarModel;
import views.CalendarView;


/**
 * Main Class for the Application.
 */
public class Main extends Application {
    CalendarView view; // the calendar view to render for the application.

    /**
     * The Main function of the class.
     * <p>
     * The launcher for JavaFx application.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        launch(args);
    }


    /**
     * To Start the application.
     *
     * @param primaryStage the stage for the JavaFx application.
     * @throws Exception any exception that is to be thrown
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.view = CalendarView.getView(new CalendarModel(), primaryStage);
    }
}
