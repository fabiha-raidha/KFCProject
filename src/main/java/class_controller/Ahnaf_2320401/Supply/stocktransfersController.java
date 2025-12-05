package class_controller.Ahnaf_2320401.Supply;

import class_controller.AppendableObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class stocktransfersController
{
    @javafx.fxml.FXML
    private TableColumn<stockTransfersClass, String> fromOutletTableColumn;
    @javafx.fxml.FXML
    private TableColumn<stockTransfersClass, String> statusTableColumn;
    @javafx.fxml.FXML
    private ComboBox<String> fromOutletComboBox;
    @javafx.fxml.FXML
    private TableView<stockTransfersClass> transferTableView;
    @javafx.fxml.FXML
    private TableColumn<stockTransfersClass, String> itemsTableColumn;
    @javafx.fxml.FXML
    private TableColumn<stockTransfersClass, String> transferIdTableColumn;
    @javafx.fxml.FXML
    private ComboBox<String> toOutletComboBox;
    @javafx.fxml.FXML
    private TextField ingredientTextField;
    @javafx.fxml.FXML
    private TableColumn<stockTransfersClass, String> toOutletTableColumn;
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private TextField quantityTextField;

    private List<stockTransfersClass.TransferItem> currentTransferItems;
    private int transferCounter = 1000;

    @javafx.fxml.FXML
    public void initialize() {
        transferIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("transferId"));
        fromOutletTableColumn.setCellValueFactory(new PropertyValueFactory<>("fromOutlet"));
        toOutletTableColumn.setCellValueFactory(new PropertyValueFactory<>("toOutlet"));
        itemsTableColumn.setCellValueFactory(new PropertyValueFactory<>("items"));
        statusTableColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        fromOutletComboBox.getItems().addAll("Dhaka", "Chittagong", "Sylhet");
        toOutletComboBox.getItems().addAll("Dhaka", "Chittagong", "Sylhet");
        currentTransferItems = new ArrayList<>();

        loadTransfersFromFile();
        updateTransferCounter();
    }

    @javafx.fxml.FXML
    public void addTransferItemONA(ActionEvent actionEvent) {
        statusLabel.setText("");

        if (ingredientTextField.getText().isEmpty() || quantityTextField.getText().isEmpty()) {
            statusLabel.setText("Please fill in all item fields.");
            return;
        }

        try {
            String ingredient = ingredientTextField.getText();
            int quantity = Integer.parseInt(quantityTextField.getText());
            stockTransfersClass.TransferItem transferItem = new stockTransfersClass.TransferItem(ingredient, quantity);
            currentTransferItems.add(transferItem);
            ingredientTextField.clear();
            quantityTextField.clear();
            statusLabel.setText("Transfer item added successfully.");
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid quantity. Please enter a valid number.");
        }
    }

    @javafx.fxml.FXML
    public void createTransferONA(ActionEvent actionEvent) {
        statusLabel.setText("");

        if (fromOutletComboBox.getValue() == null || toOutletComboBox.getValue() == null || currentTransferItems.isEmpty()) {
            statusLabel.setText("Please fill in all fields and add at least one item.");
            return;
        }

        if (fromOutletComboBox.getValue().equals(toOutletComboBox.getValue())) {
            statusLabel.setText("From and To outlets must be different.");
            return;
        }

        String transferId = "TRF-" + (transferCounter++);
        String fromOutlet = fromOutletComboBox.getValue();
        String toOutlet = toOutletComboBox.getValue();
        String status = "Pending";

        stockTransfersClass transfer = new stockTransfersClass(transferId, fromOutlet, toOutlet, status);

        for (stockTransfersClass.TransferItem item : currentTransferItems) {
            transfer.addTransferItem(item);
        }

        transferTableView.getItems().add(transfer);

        saveTransferToFile(transfer);

        clearForm();

        statusLabel.setText("Transfer created successfully.");
    }

    private void saveTransferToFile(stockTransfersClass transfer) {
        try {
            File f = new File("data/StockTransfer.bin");

            if (f.exists()) {
                FileOutputStream fos = new FileOutputStream(f, true);
                ObjectOutputStream oos = new AppendableObjectOutputStream(fos);
                oos.writeObject(transfer);
                oos.close();
            } else {
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(transfer);
                oos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error saving transfer to file.");
        }
    }

    private void loadTransfersFromFile() {
        transferTableView.getItems().clear();

        try {
            File f = new File("data/StockTransfer.bin");
            if (!f.exists()) {
                return;
            }

            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    stockTransfersClass transfer = (stockTransfersClass) ois.readObject();
                    transferTableView.getItems().add(transfer);
                } catch (EOFException eof) {
                    break;
                }
            }

            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateTransferCounter() {
        if (!transferTableView.getItems().isEmpty()) {
            for (stockTransfersClass transfer : transferTableView.getItems()) {
                String transferNum = transfer.getTransferId().replace("TRF-", "");
                try {
                    int num = Integer.parseInt(transferNum);
                    if (num >= transferCounter) {
                        transferCounter = num + 1;
                    }
                } catch (NumberFormatException e) {
                }
            }
        }
    }

    private void clearForm() {
        fromOutletComboBox.setValue(null);
        toOutletComboBox.setValue(null);
        ingredientTextField.clear();
        quantityTextField.clear();
        currentTransferItems.clear();
    }
}