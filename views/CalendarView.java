package views;

import event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.CalendarModel;
import observer.EventObserver;
import java.io.IOException;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class CalendarView {

    Stage stage;
    CalendarModel model;

    AnchorPane calendarLayout;
    BorderPane realLayout;

    String[] months = {"January", "February", "March", "April",
            "May", "June", "July", "August",
            "September", "October", "November", "December"};
    ListView<String> eventsView = new ListView<>();
    ArrayList<Event> events = new ArrayList<>();

    Button makeEventButton;
    Button makeGoalButton;
    Button changeThemeButton;
    Button viewGoalButton;
    static Paint colour;
    static Paint colour_font = javafx.scene.paint.Color.valueOf("#000000") ;

    public CalendarView(CalendarModel model, Stage stage){
        this.model = model;
        loadModel();
        this.stage = stage;
        this.calendarLayout = new AnchorPane();
        this.realLayout = new  BorderPane();
        initUI();
    }

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

    public void saveModel() {
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
        this.stage.setTitle("Chronos");

        //Make core screen
        //HBox screen = new HBox(8);
        AnchorPane calendarLayout = new AnchorPane();
        //AnchorPane goalDisplay = new AnchorPane();
        BorderPane realLayout = new BorderPane();

        //make a DatePicker for our calendar, and then set up a display that keeps
        // the calendar always active
        DatePicker calendar = new DatePicker(LocalDate.now());
        DatePickerSkin calendarSkin = new DatePickerSkin(calendar);
        Node calendarDisplay = calendarSkin.getPopupContent();
        calendarDisplay.setScaleX(1.5);
        calendarDisplay.setScaleY(1.5);
        calendarDisplay.setLayoutX(100);
        calendarDisplay.setLayoutY(100);
        calendarLayout.getChildren().add(calendarDisplay);
        calendarLayout.setPrefSize(400, 400);

        //Create the label to display the date
        Label dateDisplay = new Label(calendar.getValue().toString());

        //When a date is selected, update our list of events in the below
        calendar.setOnAction(e ->{
            dateDisplay.setText(calendar.getValue().toString());
            this.displayEvents(calendar.getValue().atStartOfDay());
        });



        //Create the button to make events
        this.makeEventButton = new Button("Make Event");
        makeEventButton.setScaleX(1.15);
        makeEventButton.setScaleY(1.15);
        makeEventButton.setOnAction(e -> {
            EventCreatorView ecv = new EventCreatorView(this);
        });

        //Create the button to make goals
        this.makeGoalButton = new Button("Make Goal");
        makeGoalButton.setScaleX(1.15);
        makeGoalButton.setScaleY(1.15);
        makeGoalButton.setOnAction(e -> {
            NewGoalView ngv = new NewGoalView(this);
        });

        //Create the button to view
        this.viewGoalButton = new Button("View Goal");
        viewGoalButton.setScaleX(1.15);
        viewGoalButton.setScaleY(1.15);
        viewGoalButton.setOnAction(e -> {
            GoalListView glv = new GoalListView(this);
        });

        //Create Button for changing the theme
        this.changeThemeButton = new Button("Change Theme");
        changeThemeButton.setTextFill(colour_font);
        changeThemeButton.setScaleX(1.15);
        changeThemeButton.setScaleY(1.15);
        changeThemeButton.setOnAction(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(CalendarView.class.getResource("ColorPick.fxml"));
            Scene scene = null;
            try {
//                scene = new Scene(fxmlLoader.load());
                Parent root1  = (Parent) fxmlLoader.load(); //error source
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
        Button editButton = new Button("Edit Event");
//        editButton.setScaleY(1.15);
//        editButton.setScaleX(1.15);
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

        Button completeEventButton = new Button("Complete Event");
        //completeEventButton.setScaleX(1.15);
        //completeEventButton.setScaleY(1.15);
        completeEventButton.setOnAction(e -> {
            String eventName = this.eventsView.getSelectionModel().getSelectedItem();
            if (eventName == null){
                return;
            }
            //int index = 0;
            for (Event event: this.events){
                if (event.getName() == eventName){
                    event.complete();
                    //this.events.remove(event);
                }
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

        //Create view for goals
        VBox goalDisplay = new VBox();
        goalDisplay.setPadding(new Insets(20));
        ListView<String> goals = new ListView<>();
        goalDisplay.getChildren().addAll(dateDisplay,goals);

        //put everything together
        realLayout.setCenter(calendarLayout);
        realLayout.setBottom(buttons);
        realLayout.setRight(eventDisplay);


        //Finally, display everything
        Scene scene = new Scene(realLayout);

        this.stage.setScene(scene);
        this.stage.show();

    }

    private void displayEvents(LocalDateTime time)
    {
        this.events = this.model.getEventsInTime(time);
        ArrayList<String> eventNames = new ArrayList<>();
        for (Event e: this.events){
            eventNames.add(e.getName());
        }
        ObservableList<String> namesToDisplay = FXCollections.observableArrayList(eventNames);
        this.eventsView.setItems(namesToDisplay);
    }

}
