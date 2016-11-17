package supermarket.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import supermarket.controller.MainApp;

import java.io.IOException;

/**
 * Created by ssthouse on 17/11/2016.
 */
public class MainController {

    @FXML
    Tab tabSalesTendency;

    @FXML
    Tab tabSalesStrategy;

    @FXML
    Tab tabPersonalizedAds;

    @FXML
    Tab tabWarehouseManagement;

    @FXML
    Tab tabSmartReport;

    private MainApp mainApp;

    @FXML
    private void initialize() {
        try {
            String[] tabFxmlStrs = {"SalesTendency.fxml", "SalesStrategy.fxml", "PersonalizedAds.fxml",
                    "WarehouseManagement.fxml", "SmartReport.fxml"};
            Tab[] tabs = {tabSalesTendency, tabSalesStrategy, tabPersonalizedAds, tabWarehouseManagement, tabSmartReport};
            for (int i = 0; i < tabFxmlStrs.length; i++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("../view/" + tabFxmlStrs[i]));
                tabs[i].setContent(loader.load());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
