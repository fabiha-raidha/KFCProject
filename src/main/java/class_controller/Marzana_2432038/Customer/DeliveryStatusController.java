package class_controller.Marzana_2432038.Customer;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class DeliveryStatusController
{
    @javafx.fxml.FXML
    private TableView<DeliveryStatus> statusTV;
    @javafx.fxml.FXML
    private TableColumn<DeliveryStatus, String> orderIdTC;
    @javafx.fxml.FXML
    private TableColumn<DeliveryStatus, String> statusTC;
    @javafx.fxml.FXML
    private TableColumn<DeliveryStatus, String> estimatedTimeTC;
    @javafx.fxml.FXML
    private TableColumn<DeliveryStatus, String> driverInfoTC;
    @javafx.fxml.FXML
    private TextField orderIdTF;
    @javafx.fxml.FXML
    private Label messageLabel;

    @javafx.fxml.FXML
    public void initialize() {

        orderIdTC.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("status"));
        estimatedTimeTC.setCellValueFactory(new PropertyValueFactory<>("estimatedTime"));
        driverInfoTC.setCellValueFactory(new PropertyValueFactory<>("driverInfo"));

        loadAllStatuses();
    }

    @javafx.fxml.FXML
    public void handleTrackOrderOA(ActionEvent actionEvent) {

        statusTV.getItems().clear();
        String id = orderIdTF.getText() == null ? "" : orderIdTF.getText().trim();

        if (id.isEmpty()) {
            loadAllStatuses();
            messageLabel.setText("Showing all delivery statuses.");
            return;
        }

        boolean found = false;

        try {
            FileInputStream fis = new FileInputStream("data/DeliveryStatus.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    DeliveryStatus ds = (DeliveryStatus) ois.readObject();
                    if (ds.getOrderId().equals(id)) {
                        statusTV.getItems().add(ds);
                        found = true;
                    }
                } catch (EOFException eof) {
                    break;
                }
            }
            ois.close();

        } catch (FileNotFoundException e) {
            messageLabel.setText("No delivery data found.");
            return;
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Error loading delivery data.");
            return;
        }

        if (found) {
            messageLabel.setText("Delivery status found for Order ID: " + id);
        } else {
            messageLabel.setText("No delivery information for Order ID: " + id);
        }
    }

    private void loadAllStatuses() {
        statusTV.getItems().clear();

        try {
            FileInputStream fis = new FileInputStream("data/DeliveryStatus.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    DeliveryStatus ds = (DeliveryStatus) ois.readObject();
                    statusTV.getItems().add(ds);
                } catch (EOFException eof) {
                    break;
                }
            }
            ois.close();

        } catch (FileNotFoundException e) {
            messageLabel.setText("No delivery data available.");
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Error loading delivery data.");
        }
    }
}
