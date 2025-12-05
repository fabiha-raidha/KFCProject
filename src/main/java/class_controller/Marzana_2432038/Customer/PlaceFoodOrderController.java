package class_controller.Marzana_2432038.Customer;

import class_controller.AppendableObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class PlaceFoodOrderController
{
    @javafx.fxml.FXML
    private TableColumn<CustomerOrder, String> itemNameTC;
    @javafx.fxml.FXML
    private TextField itemNameTF;
    @javafx.fxml.FXML
    private TextField quantityTF;
    @javafx.fxml.FXML
    private TableColumn<CustomerOrder, Integer> quantityTC;
    @javafx.fxml.FXML
    private TableColumn<CustomerOrder, Float> unitPriceTC;
    @javafx.fxml.FXML
    private TextField unitPriceTF;
    @javafx.fxml.FXML
    private TableColumn<CustomerOrder, String> addressTC;
    @javafx.fxml.FXML
    private TextField addressTF;
    @javafx.fxml.FXML
    private TableColumn<CustomerOrder, String> paymentMethodTC;
    @javafx.fxml.FXML
    private ComboBox<String> paymentMethodCB;
    @javafx.fxml.FXML
    private TableView<CustomerOrder> orderTV;
    @javafx.fxml.FXML
    private Label messageLabel;

    @javafx.fxml.FXML
    public void initialize() {

        itemNameTC.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        quantityTC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitPriceTC.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        addressTC.setCellValueFactory(new PropertyValueFactory<>("deliveryAddress"));
        paymentMethodTC.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));

        paymentMethodCB.getItems().addAll("Cash", "Card", "Bkash", "Nagad");

        loadOrdersFromFile();
    }

    @javafx.fxml.FXML
    public void onPlaceOrderOA(ActionEvent actionEvent) {

        orderTV.getItems().clear();
        messageLabel.setText("");

        if (itemNameTF.getText().isEmpty()
                || quantityTF.getText().isEmpty()
                || unitPriceTF.getText().isEmpty()
                || addressTF.getText().isEmpty()
                || paymentMethodCB.getValue() == null) {

            messageLabel.setText("Please fill food item, quantity, price, address and payment.");
            return;
        }

        int qty;
        float unitPrice;

        try {
            qty = Integer.parseInt(quantityTF.getText());
            unitPrice = Float.parseFloat(unitPriceTF.getText());
        } catch (NumberFormatException e) {
            messageLabel.setText("Quantity must be integer and price must be a number.");
            return;
        }

        float subtotal = qty * unitPrice;
        float taxRate = 0.15f;
        float totalWithTax = subtotal + (subtotal * taxRate);

        try {
            File f = new File("data/CustomerOrder.bin");

            if (f.exists()) {
                FileOutputStream fos = new FileOutputStream(f, true);
                ObjectOutputStream oos = new AppendableObjectOutputStream(fos);
                CustomerOrder o = new CustomerOrder(
                        itemNameTF.getText(),
                        qty,
                        unitPrice,
                        addressTF.getText(),
                        paymentMethodCB.getValue()
                );
                oos.writeObject(o);
                oos.close();
            } else {
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                CustomerOrder o = new CustomerOrder(
                        itemNameTF.getText(),
                        qty,
                        unitPrice,
                        addressTF.getText(),
                        paymentMethodCB.getValue()
                );
                oos.writeObject(o);
                oos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Error saving order.");
        }

        loadOrdersFromFile();

        messageLabel.setText("Order placed. Total (with tax): " + totalWithTax + " BDT");
        clearForm();
    }

    private void loadOrdersFromFile() {
        orderTV.getItems().clear();

        try {
            FileInputStream fis = new FileInputStream("data/CustomerOrder.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                try {
                    CustomerOrder o = (CustomerOrder) ois.readObject();
                    orderTV.getItems().add(o);
                } catch (EOFException e1) {
                    break;
                }
            }
            ois.close();
        } catch (FileNotFoundException e) {
  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearForm() {
        itemNameTF.clear();
        quantityTF.clear();
        unitPriceTF.clear();
        addressTF.clear();
        paymentMethodCB.setValue(null);
    }
}
