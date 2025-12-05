package class_controller.Ahnaf_2320401.Supply;

import class_controller.AppendableObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class itemCatalogController
{
    @javafx.fxml.FXML
    private ComboBox<String> categoryComboBox;
    @javafx.fxml.FXML
    private TableView<itemCatalogClass> itemTableView;
    @javafx.fxml.FXML
    private TableColumn<itemCatalogClass, String> categoryTableColumn;
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private TableColumn<itemCatalogClass, String> itemNameTableColumn;
    @javafx.fxml.FXML
    private TableColumn<itemCatalogClass, String> supplierTableColumn;
    @javafx.fxml.FXML
    private ComboBox<String> supplierComboBox;
    @javafx.fxml.FXML
    private TextField itemNametextField;

    private int itemIdCounter = 1000;

    @javafx.fxml.FXML
    public void initialize() {
        itemNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        categoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        supplierTableColumn.setCellValueFactory(new PropertyValueFactory<>("supplier"));


        categoryComboBox.getItems().addAll("Raw Materials", "Packaging", "Cleaning Supplies", "Kitchen Equipment", "Food Ingredients", "Beverages");

        loadSuppliersIntoComboBox();

        loadItemsFromFile();

        updateItemIdCounter();

        statusLabel.setText("");
    }

    @javafx.fxml.FXML
    public void addNewItemONA(ActionEvent actionEvent) {
        statusLabel.setText("");

        if (itemNametextField.getText().isEmpty() || categoryComboBox.getValue() == null || supplierComboBox.getValue() == null) {
            statusLabel.setText("Please fill in all fields.");
            return;
        }

        String itemName = itemNametextField.getText();
        String category = categoryComboBox.getValue();
        String supplier = supplierComboBox.getValue();
        int itemId = itemIdCounter++;
        String status = "Active";

        itemCatalogClass item = new itemCatalogClass(itemId, itemName, category, supplier, status);

        itemTableView.getItems().add(item);

        saveItemToFile(item);

        clearForm();

        statusLabel.setText("Item added successfully.");
    }

    @javafx.fxml.FXML
    public void openItemCatalogONA(ActionEvent actionEvent) {
        statusLabel.setText("");

        loadItemsFromFile();

        statusLabel.setText("Item catalog loaded.");
    }

    @javafx.fxml.FXML
    public void saveItemONA(ActionEvent actionEvent) {
        statusLabel.setText("");

        itemCatalogClass selectedItem = itemTableView.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            statusLabel.setText("Please select an item to update.");
            return;
        }

        if (itemNametextField.getText().isEmpty() || categoryComboBox.getValue() == null || supplierComboBox.getValue() == null) {
            statusLabel.setText("Please fill in all fields.");
            return;
        }

        selectedItem.setItemName(itemNametextField.getText());
        selectedItem.setCategory(categoryComboBox.getValue());
        selectedItem.setSupplier(supplierComboBox.getValue());

        itemTableView.refresh();

        saveAllItemsToFile();

        clearForm();

        statusLabel.setText("Item updated successfully.");
    }

    private void saveItemToFile(itemCatalogClass item) {
        try {
            File f = new File("data/ItemCatalog.bin");

            if (f.exists()) {
                FileOutputStream fos = new FileOutputStream(f, true);
                ObjectOutputStream oos = new AppendableObjectOutputStream(fos);
                oos.writeObject(item);
                oos.close();
            } else {
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(item);
                oos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadItemsFromFile() {
        itemTableView.getItems().clear();

        try {
            File f = new File("data/ItemCatalog.bin");
            if (!f.exists()) {
                return;
            }

            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    itemCatalogClass item = (itemCatalogClass) ois.readObject();
                    itemTableView.getItems().add(item);
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

    private void saveAllItemsToFile() {
        try {
            File f = new File("data/ItemCatalog.bin");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (itemCatalogClass item : itemTableView.getItems()) {
                oos.writeObject(item);
            }

            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateItemIdCounter() {
        if (!itemTableView.getItems().isEmpty()) {
            for (itemCatalogClass item : itemTableView.getItems()) {
                if (item.getItemId() >= itemIdCounter) {
                    itemIdCounter = item.getItemId() + 1;
                }
            }
        }
    }

    private void clearForm() {
        itemNametextField.clear();
        categoryComboBox.setValue(null);
        supplierComboBox.setValue(null);
    }
}