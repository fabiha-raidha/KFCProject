package class_controller.Fabiha_2211214.CMO;

import class_controller.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

public class CMODashboardController {

    @FXML
    private BorderPane cmoBorderPane;

    @FXML
    public void initialize() {
        overviewONA(null);
    }

    @FXML
    public void overviewONA(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/class_controller/Fabiha_2211214/CMOFXML/CMOOverview.fxml"));
            cmoBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void campaignManagementONA(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/class_controller/Fabiha_2211214/CMOFXML/G2MarketingCampaigns.fxml"));

            cmoBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void marketTrendsONA(ActionEvent actionEvent) {
        System.out.println("Market Trends clicked");
    }

    @FXML
    public void customerFeedbackONA(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/class_controller/Fabiha_2211214/CMOFXML/G3CustomerFeedback.fxml"));
            cmoBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void promotionsONA(ActionEvent actionEvent) {
    }

    @FXML
    public void budgetManagementONA(ActionEvent actionEvent) {
    }

    @FXML
    public void salesAnalyticsONA(ActionEvent actionEvent) {
    }

    @FXML
    public void digitalMediaONA(ActionEvent actionEvent) {
    }

    @FXML
    public void logoutONA(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/class_controller/LogInView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}