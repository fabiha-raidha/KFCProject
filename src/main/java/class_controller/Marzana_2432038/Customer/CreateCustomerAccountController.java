package class_controller.Marzana_2432038.Customer;

import class_controller.AppendableObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class CreateCustomerAccountController {

    @FXML
    private TextField custNameTF;
    @FXML
    private TextField custPhoneTF;
    @FXML
    private TextField custEmailTF;
    @FXML
    private PasswordField custPassPF;
    @FXML
    private ComboBox<String> custTypeCB;

    @FXML
    private TableView<CustomerAccount> customerTV;
    @FXML
    private TableColumn<CustomerAccount, String> custNameTC;
    @FXML
    private TableColumn<CustomerAccount, String> custPhoneTC;
    @FXML
    private TableColumn<CustomerAccount, String> custEmailTC;
    @FXML
    private TableColumn<CustomerAccount, String> custTypeTC;

    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {
        custTypeCB.getItems().addAll("Regular", "Premium", "VIP");

        custNameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
        custPhoneTC.setCellValueFactory(new PropertyValueFactory<>("phone"));
        custEmailTC.setCellValueFactory(new PropertyValueFactory<>("email"));
        custTypeTC.setCellValueFactory(new PropertyValueFactory<>("type"));
    }

    @FXML
    public void onSignUp(ActionEvent actionEvent) {

        customerTV.getItems().clear();

        try {
            File f = new File("data/CustomerAccount.bin");

            CustomerAccount c = new CustomerAccount(
                    custNameTF.getText(),
                    custPhoneTF.getText(),
                    custEmailTF.getText(),
                    custTypeCB.getValue(),
                    custPassPF.getText()
            );

            if (f.exists()) {
                FileOutputStream fos = new FileOutputStream(f, true);
                ObjectOutputStream oos = new AppendableObjectOutputStream(fos);
                oos.writeObject(c);
                oos.close();
            } else {
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(c);
                oos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fis = new FileInputStream("data/CustomerAccount.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                try {
                    CustomerAccount c = (CustomerAccount) ois.readObject();
                    customerTV.getItems().add(c);
                    messageLabel.setText("Account Created Successfully");
                } catch (EOFException e1) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
