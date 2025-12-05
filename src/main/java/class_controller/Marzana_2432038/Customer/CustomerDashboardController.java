package class_controller.Marzana_2432038.Customer;

import class_controller.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerDashboardController {

    @FXML private BorderPane customerDashboardBP;
    @FXML private Label greetL;
    @FXML private Label accountL;
    @FXML private Label infoL;
    @FXML private Label cartCountL;
    @FXML private Label pendingOrdersL;
    @FXML private Label loyaltyL;

    @FXML
    public void initialize() {
    }

    private void sceneSwitch(ActionEvent actionEvent, String fxmlFile, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle(title);
        stage.show();
    }

    @FXML
    public void browseMenuOA(ActionEvent actionEvent) throws IOException {
        sceneSwitch(actionEvent, "Marzana_2432038/CustomerFxml/G1BrowseAndViewResturantMenu.fxml", "Browse Menu");
    }

    @FXML
    public void placeOrderOA(ActionEvent actionEvent) throws IOException {
        sceneSwitch(actionEvent, "Marzana_2432038/CustomerFxml/G2PlaceFoodOrderOnline.fxml", "Place Order");
    }

    @FXML
    public void trackDeliveryOA(ActionEvent actionEvent) throws IOException {
        sceneSwitch(actionEvent, "Marzana_2432038/CustomerFxml/G3TrackDelivaryStatus.fxml", "Track Delivery");
    }

    @FXML
    public void feedbackOA(ActionEvent actionEvent) throws IOException {
        sceneSwitch(actionEvent, "Marzana_2432038/CustomerFxml/G4FeedBackAndRating.fxml", "Feedback");
    }

    @FXML
    public void createAccountOA(ActionEvent actionEvent) throws IOException {
        sceneSwitch(actionEvent, "Marzana_2432038/CustomerFxml/G5createCustomerAccount.fxml", "Create Account");
    }

    @FXML
    public void loginOA(ActionEvent actionEvent) throws IOException {
        sceneSwitch(actionEvent, "/class_controller/LogInView.fxml", "Login");
    }

    @FXML
    public void addToCartOA(ActionEvent actionEvent) {
        showAlert("Add to Cart", "Please browse the menu and select items to add to cart.");
    }

    @FXML
    public void viewCartOA(ActionEvent actionEvent) {
        showAlert("View Cart", "Your cart is empty.");
    }

    @FXML
    public void profileOA(ActionEvent actionEvent) {
        showAlert("Profile", "Profile feature coming soon.");
    }

    @FXML
    public void signOutOA(ActionEvent actionEvent) throws IOException {
        sceneSwitch(actionEvent, "/class_controller/LogInView.fxml", "Login");
    }

    @FXML
    public void fileCloseOA(ActionEvent actionEvent) {
        Stage stage = (Stage) customerDashboardBP.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void viewRefreshOA(ActionEvent actionEvent) {
        showAlert("Refresh", "Dashboard refreshed.");
    }

    @FXML
    public void helpAboutOA(ActionEvent actionEvent) {
        showAlert("About", "KFC Customer Portal v1.0");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

