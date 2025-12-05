package class_controller.Marzana_2432038.StoreManager;

import class_controller.AppendableObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;

public class SalesManagementController {

    @FXML private TableView<Sale> salesTableView;
    @FXML private TableColumn<Sale, LocalDate> dateTC;
    @FXML private TableColumn<Sale, String> productTC;
    @FXML private TableColumn<Sale, Integer> quantityTC;
    @FXML private TableColumn<Sale, Float> unitPriceTC;
    @FXML private TableColumn<Sale, Float> totalAmountTC;
    @FXML private TableColumn<Sale, String> paymentMethodTC;
    @FXML private TableColumn<Sale, String> statusTC;
    @FXML private DatePicker dateDP;
    @FXML private TextField productTF;
    @FXML private TextField quantityTF;
    @FXML private TextField unitPriceTF;
    @FXML private ComboBox<String> paymentMethodCB;
    @FXML private ComboBox<String> statusCB;
    @FXML private TextField totalAmountTF;
    @FXML private Label messageLabel;

    @FXML
    public void initialize() {

        dateTC.setCellValueFactory(new PropertyValueFactory<>("date"));
        productTC.setCellValueFactory(new PropertyValueFactory<>("product"));
        quantityTC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitPriceTC.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        totalAmountTC.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        paymentMethodTC.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("status"));

        paymentMethodCB.getItems().addAll("Cash", "Card", "Bkash", "Nagad");
        statusCB.getItems().addAll("Completed", "Pending", "Refunded");

        loadSalesFromFile();
    }

    @FXML
    public void handleAddSaleOA(ActionEvent actionEvent) {

        messageLabel.setText("");

        if (productTF.getText().isEmpty()
                || quantityTF.getText().isEmpty()
                || unitPriceTF.getText().isEmpty()
                || paymentMethodCB.getValue() == null) {

            messageLabel.setText("Fill Product, Quantity, Unit Price and Payment.");
            return;
        }

        int quantity;
        float unitPrice;

        try {
            quantity = Integer.parseInt(quantityTF.getText());
            unitPrice = Float.parseFloat(unitPriceTF.getText());
        } catch (NumberFormatException e) {
            messageLabel.setText("Qty must be integer, Unit Price must be number.");
            return;
        }

        float totalAmount = quantity * unitPrice;
        totalAmountTF.setText(String.valueOf(totalAmount));

        LocalDate date = (dateDP.getValue() != null) ? dateDP.getValue() : LocalDate.now();
        String status = (statusCB.getValue() != null) ? statusCB.getValue() : "Completed";

        Sale sale = new Sale(
                date,
                productTF.getText(),
                quantity,
                unitPrice,
                totalAmount,
                paymentMethodCB.getValue(),
                status
        );

        salesTableView.getItems().add(sale);

        saveSaleToFile(sale);

        messageLabel.setText("Sale added.");
        clearForm();
    }

    private void saveSaleToFile(Sale sale) {
        try {
            File f = new File("data/Sales.bin");

            if (f.exists()) {
                FileOutputStream fos = new FileOutputStream(f, true);
                ObjectOutputStream oos = new AppendableObjectOutputStream(fos);
                oos.writeObject(sale);
                oos.close();
            } else {
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(sale);
                oos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Error saving sale.");
        }
    }

    private void loadSalesFromFile() {
        try {
            File f = new File("data/Sales.bin");
            if (!f.exists()) return;

            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    Sale s = (Sale) ois.readObject();
                    salesTableView.getItems().add(s);
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
        dateDP.setValue(null);
        productTF.clear();
        quantityTF.clear();
        unitPriceTF.clear();
        paymentMethodCB.setValue(null);
        statusCB.setValue(null);
        totalAmountTF.clear();
    }
}
