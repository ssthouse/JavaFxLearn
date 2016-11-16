package supermarket.controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.Pair;
import supermarket.model.Person;
import supermarket.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssthouse on 15/11/2016.
 */
public class PersonOverviewController {

    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    @FXML
    private Button btnDelete;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnAdd;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    PersonOverviewController.this.showPersonDetails(newValue);
                });

        btnDelete.setOnAction(event -> {
            int index = personTable.getSelectionModel().getSelectedIndex();
            if (index < 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("this is header");
                alert.setContentText("this is content");
                alert.showAndWait();
                return;
            }
            personTable.getItems().remove(index);
            System.out.println(mainApp.getPersonData().size() + "*************");
        });

        btnEdit.setOnAction(event -> {
            Person person = personTable.getSelectionModel().getSelectedItem();
            showEditDialog(person);
        });

        btnAdd.setOnAction(event -> {
            showNewDialog();
        });
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }

    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     *
     * @param person the person or null
     */
    private void showPersonDetails(Person person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }

    private void showEditDialog(Person person) {
        if (person == null) {
            return;
        }
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("编辑");
        dialog.setHeaderText("this is the content");

        //set the button type
        ButtonType sureBtnType = new ButtonType("Sure", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(sureBtnType, ButtonType.CANCEL);


        //create custom view
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));


        String[] labelStrs = {"Fist Name", "Lase Name", "Street", "City", "Post Code", "Birthday"};
        List<TextField> textFieldList = new ArrayList<>();
        for (int i = 0; i < labelStrs.length; i++) {
            gridPane.add(new Label(labelStrs[i]), 0, i);
            textFieldList.add(new TextField());
            gridPane.add(textFieldList.get(i), 1, i);
        }

        //fill int data
        textFieldList.get(0).setText(person.getFirstName());
        textFieldList.get(1).setText(person.getLastName());
        textFieldList.get(2).setText(person.getStreet());
        textFieldList.get(3).setText(person.getCity());
        textFieldList.get(4).setText(person.getPostalCode() + "");
        textFieldList.get(5).setText(person.getBirthday().toString());
        dialog.getDialogPane().setContent(gridPane);

        dialog.setResultConverter(param -> {
            if (param == sureBtnType)
                return new Pair<String, String>("hahaha", "hihihi");
            return null;
        });

        if (dialog.showAndWait().isPresent()) {
            person.setFirstName(textFieldList.get(0).getText());
            person.setLastName(textFieldList.get(1).getText());
        }
    }

    private void showNewDialog() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("编辑");
        dialog.setHeaderText("this is the content");

        //set the button type
        ButtonType sureBtnType = new ButtonType("Sure", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(sureBtnType, ButtonType.CANCEL);


        //create custom view
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));


        String[] labelStrs = {"Fist Name", "Lase Name", "Street", "City", "Post Code", "Birthday"};
        List<TextField> textFieldList = new ArrayList<>();
        for (int i = 0; i < labelStrs.length; i++) {
            gridPane.add(new Label(labelStrs[i]), 0, i);
            textFieldList.add(new TextField());
            gridPane.add(textFieldList.get(i), 1, i);
        }

        dialog.getDialogPane().setContent(gridPane);

        dialog.setResultConverter(param -> {
            if (param == sureBtnType)
                return new Pair<String, String>("hahaha", "hihihi");
            return null;
        });

        if (dialog.showAndWait().isPresent()) {
            mainApp.getPersonData().add(new Person(textFieldList.get(0).getText(), textFieldList.get(1).getText()));
        }
    }
}
