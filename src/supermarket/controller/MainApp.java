package supermarket.controller;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import supermarket.model.Person;
import supermarket.model.PersonDataManager;
import supermarket.util.PreferUtil;
import supermarket.view.MainController;
import supermarket.view.PersonOverviewController;
import supermarket.view.RootLayoutController;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    /**
     * The data as an observable list of Persons.
     */
    private ObservableList<Person> personData = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("JavaFxText");
        openMainLayout();
        AVOSCloud.initialize("v8C4cGcNIgHoPlxhxO4CBfco-gzGzoHsz", "KvQzo5VGUpjcrkd4XdN5IgHO", "uuuf1RAzOwqLAVqjrWnCE9Iw");
        AVObject testObject = new AVObject("TestObject");
        testObject.put("words", "Hello World!");
        testObject.save();
//        initSomeData();
//        initRootLayout();
//        showPersonOverview();
//        initLocalData();
    }

    private MainController mainController;

    private void openMainLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/Main.fxml"));
            TabPane tabPane = loader.load();
            // Show the scene containing the root layout.
            Scene scene = new Scene(tabPane);
            primaryStage.setScene(scene);
            primaryStage.show();

            mainController = loader.getController();
            mainController.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initLocalData() {
        File lastOpenFile = PreferUtil.getPersonFilePath();
        if (lastOpenFile != null) {
            List<Person> personList = PersonDataManager.loadPersonsFromFile(lastOpenFile);
            personData.clear();
            personData.addAll(personList);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void initSomeData() {
        // Add some sample data
        personData.add(new Person("Hans", "Muster"));
        personData.add(new Person("Ruth", "Mueller"));
        personData.add(new Person("Heinz", "Kurz"));
        personData.add(new Person("Cornelia", "Meier"));
        personData.add(new Person("Werner", "Meyer"));
        personData.add(new Person("Lydia", "Kunz"));
        personData.add(new Person("Anna", "Best"));
        personData.add(new Person("Stefan", "Meier"));
        personData.add(new Person("Martin", "Mueller"));
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/RootLayout.fxml"));
            rootLayout = loader.load();
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            RootLayoutController rootLayoutController = loader.getController();
            rootLayoutController.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/PersonOverview.fxml"));
            AnchorPane personOverview = loader.load();
            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);
            // Give the controller access to the main app.
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }


    /**
     * Returns the data as an observable list of Persons.
     *
     * @return
     */
    public ObservableList<Person> getPersonData() {
        return personData;
    }


    public void setPersonData(List<Person> personList) {
        personData.clear();
        personData.addAll(personList);
    }
}
