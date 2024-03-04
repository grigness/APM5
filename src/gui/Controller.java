package gui;

import Domain.MealPlanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import service.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private TextField TextFieldNume;

    @FXML
    private Button addButton;

    @FXML
    private ListView<MealPlanner> listViewPrincipal;

    @FXML
    private Button sortButton;

    @FXML
    private TextField textFieldIngredients;

    @FXML
    private TextField textFieldTimp;

    private Service service;

    void populateList()
    {
        List<MealPlanner> documents = service.getActivities();
        ObservableList<MealPlanner> obsList = FXCollections.observableList(documents);
        listViewPrincipal.setItems(obsList);
    }

    @FXML
    private Button filterButton;
    @FXML
    private TextField filterTextFieldString;

    @FXML
    private TextField filterTextField;
    @FXML
    void filter(ActionEvent event) {
        int aux =Integer.parseInt(filterTextField.getText());
        ArrayList<MealPlanner> sorted=service.getFilteredActivitiesByValue(aux);
        ObservableList<MealPlanner>sortedActivity=FXCollections.observableArrayList(sorted);
        listViewPrincipal.setItems(sortedActivity);
    }

    @FXML
    void sort(ActionEvent event) {
        List<MealPlanner> sortedActivities=service.getSorted();
        ObservableList<MealPlanner>observableActivities=FXCollections.observableArrayList(sortedActivities);
        listViewPrincipal.setItems(observableActivities);
    }

    @FXML
    void add(ActionEvent event) throws SQLException {
        String dateAdded=TextFieldNume.getText();
        int nbOfStepsAdded= Integer.parseInt(textFieldTimp.getText());
        String hoursOfSleepAdded=(textFieldIngredients.getText());
        MealPlanner act=new MealPlanner(dateAdded,nbOfStepsAdded,hoursOfSleepAdded);
        service.add(act);
        populateList();
    }

    public void initialize() {
        populateList();
    }

    public Controller(Service service) {
        this.service = service;
    }
}
