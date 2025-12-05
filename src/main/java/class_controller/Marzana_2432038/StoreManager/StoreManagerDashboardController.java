package class_controller.Marzana_2432038.StoreManager;

import class_controller.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StoreManagerDashboardController
{
    @javafx.fxml.FXML
    private Button signOutBtn;
    @javafx.fxml.FXML
    private Button supplierManagementButtonOA;
    @javafx.fxml.FXML
    private Label todaySalesL;
    @javafx.fxml.FXML
    private Label greetingLabel;
    @javafx.fxml.FXML
    private Button salesManagementButtonOA;
    @javafx.fxml.FXML
    private Button SalesReportButtonOA;
    @javafx.fxml.FXML
    private Button employeeManagementButtonOA;
    @javafx.fxml.FXML
    private Button cashReconciliationButtonOA;
    @javafx.fxml.FXML
    private BorderPane storeManagerDashboardBP;
    @javafx.fxml.FXML
    private Button inventoryManagementButtonOA;
    @javafx.fxml.FXML
    private Label lowStockL;
    @javafx.fxml.FXML
    private Button staffSchedulingButtonOA;
    @javafx.fxml.FXML
    private Button purchaseOrderButtonOA;
    @javafx.fxml.FXML
    private Button profileButtonOA;
    @javafx.fxml.FXML
    private Label pendingReqL;

    @javafx.fxml.FXML
    public void initialize() {
    }

    // Scene switch utility method
    public static void sceneSwitch(ActionEvent actionEvent, String fxmlFile, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void cashReconciliationOA(ActionEvent actionEvent) {
        try {
            sceneSwitch(actionEvent, "/class_controller/Marzana_2432038/StoreManagerFxml/G7AddExpense.fxml", "Cash Reconciliation");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void purchaseOrderOA(ActionEvent actionEvent) {
        try {
            sceneSwitch(actionEvent, "/class_controller/Marzana_2432038/StoreManagerFxml/G8MaintenanceRequest.fxml", "Purchase Orders");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void viewRefreshOA(ActionEvent actionEvent) {
        // Refresh the current view - reload data
        initialize();
    }

    @javafx.fxml.FXML
    public void signOutOA(ActionEvent actionEvent) {
        try {
            sceneSwitch(actionEvent, "/class_controller/LogInView.fxml", "Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void salesManagementOA(ActionEvent actionEvent) {
        try {
            sceneSwitch(actionEvent, "/class_controller/Marzana_2432038/StoreManagerFxml/salesManager.fxml", "Sales Management");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void staffSchedulingOA(ActionEvent actionEvent) {
        try {
            sceneSwitch(actionEvent, "/class_controller/Marzana_2432038/StoreManagerFxml/staffSchedule.fxml", "Staff Scheduling");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void salesReportOA(ActionEvent actionEvent) {
        try {
            sceneSwitch(actionEvent, "/class_controller/Marzana_2432038/StoreManagerFxml/SalesReportView.fxml", "Sales Report");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void helpAboutOA(ActionEvent actionEvent) {
        System.out.println("Help/About clicked");
    }

    @javafx.fxml.FXML
    public void profileOA(ActionEvent actionEvent) {
        try {
            sceneSwitch(actionEvent, "/class_controller/Marzana_2432038/StoreManagerFxml/performanceSummary.fxml", "Profile");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void inventoryManagementOA(ActionEvent actionEvent) {
        try {
            sceneSwitch(actionEvent, "/class_controller/Marzana_2432038/StoreManagerFxml/InventoryRquest.fxml", "Inventory Management");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void fileCloseOA(ActionEvent actionEvent) {
        Stage stage = (Stage) storeManagerDashboardBP.getScene().getWindow();
        stage.close();
    }

    @javafx.fxml.FXML
    public void supplierManagementOA(ActionEvent actionEvent) {
        try {
            sceneSwitch(actionEvent, "/class_controller/Marzana_2432038/StoreManagerFxml/customerFeedback.fxml", "Supplier Management");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void employeeManagementOA(ActionEvent actionEvent) {
        try {
            sceneSwitch(actionEvent, "/class_controller/Marzana_2432038/StoreManagerFxml/staffEvaluation.fxml", "Employee Management");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}