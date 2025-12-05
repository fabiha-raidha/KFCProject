package class_controller.Ahnaf_2320401.Supply;

import class_controller.AppendableObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class purchaseOrderController
{
    @javafx.fxml.FXML
    private DatePicker deliveryDatePicker;
    @javafx.fxml.FXML
    private TableView<purchaseOrderClass> purchaseOrderTableView;
    @javafx.fxml.FXML
    private TableColumn<purchaseOrderClass, LocalDate> deliveryDateTableColumn;
    @javafx.fxml.FXML
    private TextField deliveryLocationTextField;
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private TableColumn<purchaseOrderClass, String> supplierTableColumn;
    @javafx.fxml.FXML
    private TableColumn<purchaseOrderClass, String> statusTableColumn;
    @javafx.fxml.FXML
    private TextField priceTextField;
    @javafx.fxml.FXML
    private TextField ingredientTextField;
    @javafx.fxml.FXML
    private TableColumn<purchaseOrderClass, Double> totalTableColumn;
    @javafx.fxml.FXML
    private TableColumn<purchaseOrderClass, String> poNumberTableColumn;
    @javafx.fxml.FXML
    private TableColumn<purchaseOrderClass, String> deliveryLocationTableColumn;
    @javafx.fxml.FXML
    private ComboBox<String> supplierComboBox;
    @javafx.fxml.FXML
    private TextField quantityTextField;

    private purchaseOrderClass currentPO;
    private List<purchaseOrderClass.LineItem> currentLineItems;
    private int poCounter = 1000;

    @javafx.fxml.FXML
    public void initialize() {
        poNumberTableColumn.setCellValueFactory(new PropertyValueFactory<>("poNumber"));
        supplierTableColumn.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        deliveryLocationTableColumn.setCellValueFactory(new PropertyValueFactory<>("deliveryLocation"));
        deliveryDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
        totalTableColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        statusTableColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        supplierComboBox.getItems().addAll("Dhaka", "Chittagong", "Sylhet");

        currentLineItems = new ArrayList<>();

        loadPurchaseOrdersFromFile();

        loadSuppliersIntoComboBox();

        updatePOCounter();
    }

    @javafx.fxml.FXML
    public void addLineItemONA(ActionEvent actionEvent) {
        statusLabel.setText("");

        if (ingredientTextField.getText().isEmpty() || quantityTextField.getText().isEmpty() || priceTextField.getText().isEmpty()) {
            statusLabel.setText("Please fill in all line item fields.");
            return;
        }

        String ingredient = ingredientTextField.getText();
        int quantity = Integer.parseInt(quantityTextField.getText());
        double price = Double.parseDouble(priceTextField.getText());

        purchaseOrderClass.LineItem lineItem = new purchaseOrderClass.LineItem(ingredient, quantity, price);
        currentLineItems.add(lineItem);
        ingredientTextField.clear();
        quantityTextField.clear();
        priceTextField.clear();

        statusLabel.setText("Line item added successfully.");
    }

    @javafx.fxml.FXML
    public void createPurchaseOrderONA(ActionEvent actionEvent) {
        statusLabel.setText("");

        if (supplierComboBox.getValue() == null || deliveryLocationTextField.getText().isEmpty() || deliveryDatePicker.getValue() == null || currentLineItems.isEmpty()) {
            statusLabel.setText("Please fill in all fields.");
            return;
        }

        String poNumber = "PO-" + (poCounter++);
        String supplier = supplierComboBox.getValue();
        String deliveryLocation = deliveryLocationTextField.getText();
        LocalDate deliveryDate = deliveryDatePicker.getValue();
        String status = "Pending";

        purchaseOrderClass po = new purchaseOrderClass(poNumber, supplier, deliveryLocation, deliveryDate, status);

        for (purchaseOrderClass.LineItem item : currentLineItems) {
            po.addLineItem(item);
        }

        purchaseOrderTableView.getItems().add(po);
        savePurchaseOrderToFile(po);
        clearForm();

        statusLabel.setText("Purchase order added successfully.");
    }

    private void savePurchaseOrderToFile(purchaseOrderClass po) {
        try {
            File f = new File("data/PurchaseOrder.bin");

            if (f.exists()) {
                FileOutputStream fos = new FileOutputStream(f, true);
                ObjectOutputStream oos = new AppendableObjectOutputStream(fos);
                oos.writeObject(po);
                oos.close();
            } else {
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(po);
                oos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadPurchaseOrdersFromFile() {
        purchaseOrderTableView.getItems().clear();

        try {
            File f = new File("data/PurchaseOrder.bin");
            if (!f.exists()) {
                return;
            }

            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    purchaseOrderClass po = (purchaseOrderClass) ois.readObject();
                    purchaseOrderTableView.getItems().add(po);
                } catch (EOFException eof) {
                    break;
                }
            }

            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadSuppliersIntoComboBox() {
        try {
            File f = new File("data/Supplier.bin");
            if (!f.exists()) {
                return;
            }

            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    supplierClass supplier = (supplierClass) ois.readObject();
                    supplierComboBox.getItems().add(supplier.getName());
                } catch (EOFException eof) {
                    break;
                }
            }

            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updatePOCounter() {
        if (!purchaseOrderTableView.getItems().isEmpty()) {
            for (purchaseOrderClass po : purchaseOrderTableView.getItems()) {
                String poNum = po.getPoNumber().replace("PO-", "");
                try {
                    int num = Integer.parseInt(poNum);
                    if (num >= poCounter) {
                        poCounter = num + 1;
                    }
                } catch (NumberFormatException e) {
                }
            }
        }
    }

    private void clearForm() {
        supplierComboBox.setValue(null);
        deliveryLocationTextField.clear();
        deliveryDatePicker.setValue(null);
        ingredientTextField.clear();
        quantityTextField.clear();
        priceTextField.clear();
        currentLineItems.clear();
    }
}