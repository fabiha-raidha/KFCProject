package class_controller.Marzana_2432038.StoreManager;

import class_controller.AppendableObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class InventoryRequestController {

    @FXML
    private TableView<InventoryRequest> requestTableView;
    @FXML
    private TableColumn<InventoryRequest, String> itemNameTC;
    @FXML
    private TableColumn<InventoryRequest, Integer> requiredQtyTC;
    @FXML
    private TableColumn<InventoryRequest, String> statusTC;

    @FXML
    private TextField itemNameTF;
    @FXML
    private TextField requiredQtyTF;
    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {
        itemNameTC.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        requiredQtyTC.setCellValueFactory(new PropertyValueFactory<>("requiredQuantity"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadRequestsFromFile();
    }

    @FXML
    public void handleRequestItemOA(ActionEvent actionEvent) {

        messageLabel.setText("");

        if (itemNameTF.getText().isEmpty() || requiredQtyTF.getText().isEmpty()) {
            messageLabel.setText("Please enter item name and required quantity.");
            return;
        }

        int qty;
        try {
            qty = Integer.parseInt(requiredQtyTF.getText());
        } catch (NumberFormatException e) {
            messageLabel.setText("Required quantity must be an integer.");
            return;
        }

        if (qty <= 0) {
            messageLabel.setText("Required quantity must be greater than 0.");
            return;
        }

        boolean available = verifyAvailability(itemNameTF.getText(), qty);

        if (!available) {
            messageLabel.setText("Requested quantity is not available in central store.");
            return;
        }

        InventoryRequest request = new InventoryRequest(
                itemNameTF.getText(),
                qty,
                "Pending"
        );

        requestTableView.getItems().add(request);
        saveRequestToFile(request);
        messageLabel.setText("Request saved with status 'Pending'.");
        clearForm();
    }

    private boolean verifyAvailability(String itemName, int requiredQty) {
        return requiredQty <= 100;
    }

    private void saveRequestToFile(InventoryRequest request) {
        try {
            File f = new File("data/InventoryRequests.bin");

            if (f.exists()) {
                FileOutputStream fos = new FileOutputStream(f, true);
                ObjectOutputStream oos = new AppendableObjectOutputStream(fos);
                oos.writeObject(request);
                oos.close();
            } else {
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(request);
                oos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Error saving request.");
        }
    }

    private void loadRequestsFromFile() {
        try {
            File f = new File("data/InventoryRequests.bin");
            if (!f.exists()) return;

            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    InventoryRequest r = (InventoryRequest) ois.readObject();
                    requestTableView.getItems().add(r);
                } catch (EOFException eof) {
                    break;
                }
            }
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearForm() {
        itemNameTF.clear();
        requiredQtyTF.clear();
    }
}
