package supermarket.controller;


import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import sun.applet.Main;
import supermarket.util.Log;

/**
 * Created by ssthouse on 17/11/2016.
 */
public class RootLayoutController {

    @FXML
    private MenuItem menuNew;

    @FXML
    private MenuItem menuOpen;

    @FXML
    private MenuItem menuSave;

    @FXML
    private MenuItem menuSaveAs;

    @FXML
    private MenuItem menuExit;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize(){
        menuNew.setOnAction(event -> {
            Log.println("new");
        });

        menuOpen.setOnAction(event -> {
            Log.println("open");

        });

        menuSave.setOnAction(event -> {

        });

        menuSaveAs.setOnAction(event -> {

        });

        menuExit.setOnAction(event -> {

        });
    }


}
