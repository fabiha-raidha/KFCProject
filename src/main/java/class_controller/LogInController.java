package class_controller;

import class_controller.users.CustomerUser;
import class_controller.users.StaffUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class LogInController {

    @FXML private TextField usernameTF;
    @FXML private PasswordField passwordTF;

    private List<Authenticatable> userTypes;

    public LogInController() {
        userTypes = new ArrayList<>();
        userTypes.add(new StaffUser("Head Chef", "chef", "123", "/class_controller/Fabiha_2211214/HeadChefFXML/HeadChefDashBoardView.fxml"));
        userTypes.add(new StaffUser("CMO", "cmo", "123", "/class_controller/Fabiha_2211214/CMOFXML/CMODashboard.fxml"));
        userTypes.add(new StaffUser("Manager", "manager", "123", "/class_controller/Marzana_2432038/StoreManagerFxml/StoreManagerDashboard.fxml"));
        userTypes.add(new StaffUser("Supply", "supply", "123", "/class_controller/Ahnaf_2320401/SupplyFXML/supplyDashboard.fxml"));
        userTypes.add(new StaffUser("Quality", "quality", "123", "/class_controller/Ahnaf_2320401/QualityFXML/qualityDashboard.fxml"));
        userTypes.add(new StaffUser("Customer", "customer", "123", "/class_controller/Marzana_2432038/CustomerFxml/customerDasboard.fxml"));
        userTypes.add(new StaffUser("Store Manager", "storemanager", "123", "/class_controller/Marzana_2432038/StoreManagerFxml/StoreManagerDashboard.fxml"));
        userTypes.add(new CustomerUser());
    }

    @FXML
    void onLogInButtonClicked(MouseEvent event) {
        String user = usernameTF.getText();
        String pass = passwordTF.getText();

        for (Authenticatable auth : userTypes) {
            String path = auth.authenticate(user, pass);
            if (path != null) {
                loadDashboard(event, path, auth.getRoleName() + " Dashboard");
                return;
            }
        }

        showAlert("Login Failed", "Invalid credentials.");
    }

    private void loadDashboard(MouseEvent event, String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load dashboard.");
        }
    }

    private void showAlert(String title, String message) {
        new Alert(Alert.AlertType.ERROR, message).showAndWait();
    }
}