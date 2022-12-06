package views;

import event.Event;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.CalendarModel;
import observer.EventObserver;
import javafx.scene.layout.*;
import javafx.stage.StageStyle;


import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class CalendarView {

    //Below defines all the components we'll need

    //The main root
    Stage stage;

    //The model that stores event information
    CalendarModel model;

    //The sublayout that contains our calendar
    AnchorPane calendarLayout;

    //The overall layout that contains everything
    BorderPane realLayout;

    //Buttons used for functionality
    Button makeEventButton;
    Button makeGoalButton;
    Button changeThemeButton;
    Button viewGoalButton;

    //The calendar used to access events
    DatePicker calendar;

    //A wrapper class used to help display the calendar in a better way
    DatePickerSkin calendarSkin;

    // A node that takes the calendar and makes it always visible
    Node calendarDisplay;
    Button editButton;

    //Label showing the selected date
    Label dateDisplay;
    Button completeEventButton;

    //Variables for getting the background and text color to change window theme
    static Paint colour = javafx.scene.paint.Color.valueOf("#FFFFFF") ;;
    static Paint colour_font = javafx.scene.paint.Color.valueOf("#000000") ;

    // ListView to display event names for a specific date
    ListView<String> eventsView = new ListView<>();

    // List to store events for a given date
    ArrayList<Event> events = new ArrayList<>();

    // static instance
    static CalendarView instance;

    //Method to implement singleton design pattern
    public static CalendarView getView(CalendarModel model, Stage stage){
        if (instance == null){
            instance = new CalendarView(model, stage);
        }
        return instance;
    }

    // private constructor to enforce Singleton
    private CalendarView(CalendarModel model, Stage stage){
        // Get a model
        this.model = model;

        // load stored model info
        loadModel();
        this.stage = stage;

        // Set up the layouts
        this.calendarLayout = new AnchorPane();
        this.realLayout = new BorderPane();

        // Create the UI
        initUI();
    }

    // load stored info
    public void loadModel() {
        File folder = new File("save/");
        if (!folder.exists()) {
            return;
        }
        File[] fileList = folder.listFiles();
        assert fileList != null;
        for (File f : fileList) {
            if (f.isFile() && f.getName().equals("model.ser")) {
                try {
                    FileInputStream file = new FileInputStream("save/model.ser");
                    ObjectInputStream in = new ObjectInputStream(file);
                    ArrayList<Object> loadList = (ArrayList<Object>) in.readObject();
                    this.model = (CalendarModel) loadList.get(0);
                    Event.setObserverList((ArrayList<EventObserver>) loadList.get(1));
                    CalendarModel.setCompletedGoals((ArrayList<EventObserver>) loadList.get(2));
                    CalendarView.colour = javafx.scene.paint.Color.valueOf(this.model.colour);
                    CalendarView.colour_font = javafx.scene.paint.Color.valueOf(this.model.colour_font);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    // Save our model
    public void saveModel() {
        this.model.colour = colour.toString();
        this.model.colour_font = colour_font.toString();
        File folder = new File("save/");
        if (!folder.exists()) {
            folder.mkdir();
        }
        File fModel = new File("save/model.ser");
        ArrayList<Object> saveList = new ArrayList<>();
        try {
            saveList.add(this.model);
            saveList.add(Event.getObserverList());
            saveList.add(CalendarModel.getCompletedGoals());
            FileOutputStream fout = new FileOutputStream(fModel);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(saveList);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void initUI(){

        // set the title of the screen
        this.stage.setTitle("Chronos");


        //Make core screen
        this.calendarLayout = new AnchorPane();
        this.realLayout = new BorderPane();

        //make a DatePicker for our calendar, and then set up a display that keeps
        // the calendar always active
        calendar = new DatePicker(LocalDate.now());
        calendarSkin = new DatePickerSkin(calendar);
        calendarDisplay = calendarSkin.getPopupContent();
        calendarDisplay.setScaleX(1.5);
        calendarDisplay.setScaleY(1.5);
        calendarDisplay.setLayoutX(100);
        calendarDisplay.setLayoutY(100);
        calendarLayout.getChildren().add(calendarDisplay);
        calendarLayout.setPrefSize(400, 400);
        calendarLayout.setBackground(new Background(new BackgroundFill(colour,null,null)));

        //Create the label to display the date
        this.dateDisplay = new Label(calendar.getValue().toString());
        dateDisplay.setTextFill(colour_font);
        dateDisplay.setFont(new Font(20));
        dateDisplay.setAlignment(Pos.TOP_CENTER);
        //When a date is selected, update our list of events in the below
        calendar.setOnAction(e ->{
            dateDisplay.setText(calendar.getValue().toString());
            this.displayEvents(calendar.getValue().atStartOfDay());
        });

        //Create the button to make events
        this.makeEventButton = new Button("Make Event");
        makeEventButton.setScaleX(1.15);
        makeEventButton.setScaleY(1.15);
        makeEventButton.setTextFill(colour_font);
        makeEventButton.setOnAction(e -> {
            EventCreatorView ecv = new EventCreatorView(this);
            this.displayEvents(calendar.getValue().atStartOfDay());
        });

        //Create the button to make goals
        this.makeGoalButton = new Button("Make Goal");
        makeGoalButton.setScaleX(1.15);
        makeGoalButton.setScaleY(1.15);
        makeGoalButton.setTextFill(colour_font);
        makeGoalButton.setOnAction(e -> {
            NewGoalView ngv = new NewGoalView(this);
        });

        //Create the button to view
        this.viewGoalButton = new Button("View Goal");
        viewGoalButton.setScaleX(1.15);
        viewGoalButton.setScaleY(1.15);
        viewGoalButton.setTextFill(colour_font);
        viewGoalButton.setOnAction(e -> {
            GoalListView glv = new GoalListView(this);
        });

        //Create Button for changing the theme
        this.changeThemeButton = new Button("Change Theme");
        changeThemeButton.setTextFill(colour_font);
        changeThemeButton.setScaleX(1.15);
        changeThemeButton.setScaleY(1.15);
        changeThemeButton.setOnAction(e -> {
            // Configure the Color
            FXMLLoader fxmlLoader = new FXMLLoader(CalendarView.class.getResource("ColorPick.fxml"));
            Scene scene = null;
            try {
//                scene = new Scene(fxmlLoader.load());

                Parent root1  = (Parent) fxmlLoader.load(); // TODO: Error Source
                Stage stage = new Stage();
//                stage.initModality(Modality.APPLICATION_MODAL);
//                stage.initStyle(StageStyle.UNDECORATED);
                stage.setTitle("Chronos");
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Color.cv = this;
        });

        //Create button bar
        HBox buttons = new HBox(10);
        buttons.setPadding(new Insets(30));
        buttons.getChildren().addAll(makeEventButton, makeGoalButton, viewGoalButton, changeThemeButton);
        buttons.setPadding(new Insets(20));

        //Create buttons for editing and completing events
        this.editButton = new Button("Edit Event");
        this.editButton.setTextFill(colour_font);
        editButton.setOnAction(e -> {
            if (this.eventsView.getSelectionModel().getSelectedItem() != null){

                for(Event i: this.events)
                {
                    if(i.getName().equals(this.eventsView.getSelectionModel().getSelectedItem()))
                    {
                        EventEditorView editorView = new EventEditorView(this, i);
                        break;
                    }
                }
            }
            else{
                System.out.println("Need to select an item");
            }
        });

        this.completeEventButton = new Button("Complete Event");
        this.completeEventButton.setTextFill(colour_font);
        completeEventButton.setOnAction(e -> {
            String eventName = this.eventsView.getSelectionModel().getSelectedItem();
            if (eventName == null){
                return;
            }
            //int index = 0;
            Event completed = null;
            for (Event event: this.events){
                if (event.getName() == eventName){
                    completed = event;
                    completed.complete();
                    break;
                }
            }
            if (!(completed == null)){
                this.events.remove(completed);
                this.model.getAllEvents().remove(completed);
                this.saveModel();
            }
        });

        //Create bar for editing and completing events
        HBox eventsManaging = new HBox();
        eventsManaging.getChildren().addAll(editButton, completeEventButton);
        eventsManaging.setPadding(new Insets(20));

        //Create view for events
        VBox eventDisplay = new VBox();
        eventDisplay.setPadding(new Insets(20));
        this.displayEvents(LocalDateTime.now());
        eventDisplay.getChildren().addAll(dateDisplay, eventsView, eventsManaging);


        //put everything together
        realLayout.setCenter(calendarLayout);
        realLayout.setBottom(buttons);
        realLayout.setRight(eventDisplay);
        realLayout.setBackground(new Background(new BackgroundFill(colour,null,null)));



        //Finally, display everything
        Scene scene = new Scene(realLayout);
        this.stage.setScene(scene);
        this.stage.show();

    }

    //Method to display events in the event list
    private void displayEvents(LocalDateTime time)
    {
        //Get events that match the date
        this.events = this.model.getEventsInTime(time);

        //Get their names
        ArrayList<String> eventNames = new ArrayList<>();
        for (Event e: this.events){
            eventNames.add(e.getName());
        }

        //Set the ListView to contain those names
        ObservableList<String> namesToDisplay = FXCollections.observableArrayList(eventNames);
        this.eventsView.setItems(namesToDisplay);
    }

}
