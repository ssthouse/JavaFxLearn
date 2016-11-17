package supermarket.controller;


import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import supermarket.model.Person;
import supermarket.model.PersonDataManager;
import supermarket.util.Log;
import supermarket.util.PreferUtil;

import java.io.File;
import java.util.List;

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
    private void initialize() {
        menuNew.setOnAction(event -> {
            //clear all current file
            PreferUtil.setPersonFilePath(null);
            mainApp.getPersonData().clear();
        });

        menuOpen.setOnAction(event -> {
            showOpenFileDialog();
        });

        menuSave.setOnAction(event -> {
            File lastOpenFile = PreferUtil.getPersonFilePath();
            if (lastOpenFile == null) {
                showSaveFileDialog();
                return;
            }
            PersonDataManager.savePersonsDataToFile(lastOpenFile, mainApp.getPersonData());
        });

        menuSaveAs.setOnAction(event -> {
            showSaveFileDialog();
        });

        menuExit.setOnAction(event -> {
            System.exit(0);
        });
    }

    private void showOpenFileDialog() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File openFile = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        if(openFile == null)
            return;
        List<Person> personList = PersonDataManager.loadPersonsFromFile(openFile);
        mainApp.setPersonData(personList);
        //保存当前打开文件路径
        PreferUtil.setPersonFilePath(openFile);
    }

    private void showSaveFileDialog() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File chooseFile = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
        if (chooseFile == null) {
            return;
        }
        PersonDataManager.savePersonsDataToFile(chooseFile, mainApp.getPersonData());
    }

}
