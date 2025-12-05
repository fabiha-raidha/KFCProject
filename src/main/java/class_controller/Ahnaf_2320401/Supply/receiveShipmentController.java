package class_controller.Ahnaf_2320401.Supply;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class receiveShipmentController
{
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private TableView<receiveShipmentClass> poDetailsTableView;
    @javafx.fxml.FXML
    private TextField poNumberTextField;
    @javafx.fxml.FXML
    private TableColumn<receiveShipmentClass, Integer> orderedQtyTableColumn;
    @javafx.fxml.FXML
    private TableColumn<receiveShipmentClass, String> itemTableColumn;
    @javafx.fxml.FXML
    private TableColumn<receiveShipmentClass, Integer> receivedQtyTableColumn;

    private purchaseOrderClass currentPO;

    @javafx.fxml.FXML
    public void initialize() {
        itemTableColumn.setCellValueFactory(new PropertyValueFactory<>("item"));
        orderedQtyTableColumn.setCellValueFactory(new PropertyValueFactory<>("orderedQty"));
        receivedQtyTableColumn.setCellValueFactory(new PropertyValueFactory<>("receivedQty"));

        statusLabel.setText("Enter PO Number to fetch details.");
    }

    @javafx.fxml.FXML
    public void fetchPODetailsONA(ActionEvent actionEvent) {
        statusLabel.setText("");
        poDetailsTableView.getItems().clear();

        if (poNumberTextField.getText().isEmpty()) {
            statusLabel.setText("Please enter a PO Number.");
            return;
        }

        String poNumber = poNumberTextField.getText();
        currentPO = loadPurchaseOrderByNumber(poNumber);

        if (currentPO == null) {
            statusLabel.setText("PO not found.");
            return;
        }

        if ("Received".equalsIgnoreCase(currentPO.getStatus())) {
            statusLabel.setText("PO already received.");
            return;
        }

        for (purchaseOrderClass.LineItem lineItem : currentPO.getLineItems()) {
            poDetailsTableView.getItems().add(new receiveShipmentClass(
                    lineItem.getIngredient(), lineItem.getQuantity(), lineItem.getQuantity()));
        }

        statusLabel.setText("PO loaded. Click 'Confirm Receipt' to complete.");
    }

    @javafx.fxml.FXML
    public void confirmReceiptONA(ActionEvent actionEvent) {
        statusLabel.setText("");

        if (currentPO == null) {
            statusLabel.setText("Please fetch a PO first.");
            return;
        }

        if (poDetailsTableView.getItems().isEmpty()) {
            statusLabel.setText("No items to confirm.");
            return;
        }

        currentPO.setStatus("Received");
        updatePurchaseOrderInFile(currentPO);

        statusLabel.setText("Shipment received successfully!");
        clearForm();
    }

    @javafx.fxml.FXML
    public void refreshListONA(ActionEvent actionEvent) {
        clearForm();
        statusLabel.setText("Form cleared. Enter PO Number to fetch.");
    }

    private purchaseOrderClass loadPurchaseOrderByNumber(String poNumber) {
        try {
            File f = new File("data/PurchaseOrder.bin");
            if (!f.exists()) {
                return null;
            }

            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    purchaseOrderClass po = (purchaseOrderClass) ois.readObject();
                    if (po.getPoNumber().equalsIgnoreCase(poNumber)) {
                        ois.close();
                        return po;
                    }
                } catch (EOFException eof) {
                    break;
                }
            }

            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void updatePurchaseOrderInFile(purchaseOrderClass updatedPO) {
        try {
            File f = new File("data/PurchaseOrder.bin");
            if (!f.exists()) {
                return;
            }

            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            java.util.List<purchaseOrderClass> allPOs = new java.util.ArrayList<>();

            while (true) {
                try {
                    purchaseOrderClass po = (purchaseOrderClass) ois.readObject();

                    if (po.getPoNumber().equals(updatedPO.getPoNumber())) {
                        allPOs.add(updatedPO);
                    } else {
                        allPOs.add(po);
                    }
                } catch (EOFException eof) {
                    break;
                }
            }
            ois.close();

            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (purchaseOrderClass po : allPOs) {
                oos.writeObject(po);
            }

            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error updating PO file.");
        }
    }

    private void clearForm() {
        poNumberTextField.clear();
        poDetailsTableView.getItems().clear();
        currentPO = null;
    }
}