package class_controller.Fabiha_2211214.HeadChef;

import class_controller.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

public class HeadChefDashboardController {

    @FXML
    private BorderPane headChefBorderPane;

    @FXML
    public void initialize() {
        overviewONA(null);
    }

    @FXML
    public void overviewONA(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/class_controller/Fabiha_2211214/HeadChefFXML/HeadChefOverview.fxml"));
            headChefBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void recipeMenuONA(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/class_controller/Fabiha_2211214/HeadChefFXML/G1RecipeManagement.fxml"));
            headChefBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void inventoryONA(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/class_controller/Fabiha_2211214/HeadChefFXML/G2InventoryManagement.fxml"));
            headChefBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void qualityControlONA(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/class_controller/Fabiha_2211214/HeadChefFXML/G3QualityControl.fxml"));
            headChefBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void attendanceONA(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/class_controller/Fabiha_2211214/HeadChefFXML/G4AttendanceSheet.fxml"));
            headChefBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void salesReportONA(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/class_controller/Fabiha_2211214/HeadChefFXML/G5SalesReport.fxml"));
            headChefBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void staffONA(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/class_controller/Fabiha_2211214/HeadChefFXML/G6StaffTrainingManagement.fxml"));
            headChefBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void lineManagementONA(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/class_controller/Fabiha_2211214/HeadChefFXML/G7KitchenOperation.fxml"));
            headChefBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
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