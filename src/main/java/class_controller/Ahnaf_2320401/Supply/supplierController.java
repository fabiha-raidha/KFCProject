package class_controller.Ahnaf_2320401.Supply;

import class_controller.AppendableObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class supplierController
{
    @javafx.fxml.FXML
    private TableColumn<supplierClass, String> emailTableColumn;
    @javafx.fxml.FXML
    private TableColumn<supplierClass, String> nameTableColumn;
    @javafx.fxml.FXML
    private TextField emailTextField;
    @javafx.fxml.FXML
    private TextField nameTextField;
    @javafx.fxml.FXML
    private TableView<supplierClass> supplierTableView;
    @javafx.fxml.FXML
    private Label totalSuppliersLabel;
    @javafx.fxml.FXML
    private TextField contactTextField;
    @javafx.fxml.FXML
    private TableColumn<supplierClass, String> categoryTableColumn;
    @javafx.fxml.FXML
    private TableColumn<supplierClass, Integer> contactTableColumn;
    @javafx.fxml.FXML
    private TextField categoryTextField;
    @javafx.fxml.FXML
    private Label successLabel;
    @javafx.fxml.FXML
    private Label errorLabel;

    @javafx.fxml.FXML
    public void initialize() {
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        contactTableColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        emailTableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        loadSuppliersFromFile();
        updateTotalSuppliers();
    }

    @javafx.fxml.FXML
    public void addSupplierONA(ActionEvent actionEvent) {
        successLabel.setText("");
        errorLabel.setText("");

        if (nameTextField.getText().isEmpty() || categoryTextField.getText().isEmpty() || contactTextField.getText().isEmpty() || emailTextField.getText().isEmpty()) {
            errorLabel.setText("Please fill in all fields.");
            return;
        }

        String name = nameTextField.getText();
        String category = categoryTextField.getText();
        String contact = contactTextField.getText();
        String email = emailTextField.getText();

        if (!email.contains("@") || !email.contains(".")) {
            errorLabel.setText("Please enter a valid email address.");
            return;
        }

        if (contact.length() < 10) {
            errorLabel.setText("Contact number should be at least 10 digits.");
            return;
        }

        supplierClass supplier = new supplierClass(name, category, contact, email);

        supplierTableView.getItems().add(supplier);
        saveSupplierToFile(supplier);
        successLabel.setText("Supplier added successfully.");
        clearForm();
        updateTotalSuppliers();
    }

    private void saveSupplierToFile(supplierClass supplier) {
        try {
            File f = new File("data/Supplier.bin");

            if (f.exists()) {
                FileOutputStream fos = new FileOutputStream(f, true);
                ObjectOutputStream oos = new AppendableObjectOutputStream(fos);
                oos.writeObject(supplier);
                oos.close();
            } else {
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(supplier);
                oos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorLabel.setText("Error saving supplier.");
        }
    }

    private void loadSuppliersFromFile() {
        supplierTableView.getItems().clear();

        try {
            File f = new File("data/Supplier.bin");
            if (!f.exists()) {
                return;
            }

            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    supplierClass s = (supplierClass) ois.readObject();
                    supplierTableView.getItems().add(s);
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
        nameTextField.clear();
        categoryTextField.clear();
        contactTextField.clear();
        emailTextField.clear();
    }

    private void updateTotalSuppliers() {
        int total = supplierTableView.getItems().size();
        totalSuppliersLabel.setText("Total Suppliers: " + total);
    }
}