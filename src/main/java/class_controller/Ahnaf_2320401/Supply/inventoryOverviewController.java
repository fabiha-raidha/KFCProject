package class_controller.Ahnaf_2320401.Supply;

import class_controller.AppendableObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class inventoryOverviewController
{
    @javafx.fxml.FXML
    private Label lowStockCountLabel;
    @javafx.fxml.FXML
    private TableColumn<inventoryOverviewClass, Integer> minLevelTableColumn;
    @javafx.fxml.FXML
    private ComboBox<String> ingredientFilterComboBox;
    @javafx.fxml.FXML
    private TableColumn<inventoryOverviewClass, Integer> currentQtyTableColumn;
    @javafx.fxml.FXML
    private ComboBox<String> outletFilterComboBox;
    @javafx.fxml.FXML
    private TableView<inventoryOverviewClass> inventoryTableView;
    @javafx.fxml.FXML
    private TableColumn<inventoryOverviewClass, String> stockWarningTableColumn;
    @javafx.fxml.FXML
    private TableColumn<inventoryOverviewClass, String> ingredientTableColumn;
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private TableColumn<inventoryOverviewClass, String> outletTableColumn;
    @javafx.fxml.FXML
    private ComboBox<String> stockWarningFilterComboBox;

    private javafx.collections.ObservableList<inventoryOverviewClass> allInventoryData;

    @javafx.fxml.FXML
    public void initialize() {
        outletTableColumn.setCellValueFactory(new PropertyValueFactory<>("outlet"));
        ingredientTableColumn.setCellValueFactory(new PropertyValueFactory<>("ingredient"));
        currentQtyTableColumn.setCellValueFactory(new PropertyValueFactory<>("currentQty"));
        minLevelTableColumn.setCellValueFactory(new PropertyValueFactory<>("minLevel"));
        stockWarningTableColumn.setCellValueFactory(new PropertyValueFactory<>("stockWarning"));

        ingredientFilterComboBox.getItems().addAll("All", "Chicken", "Buns", "Lettuce");
        outletFilterComboBox.getItems().addAll("All", "Dhaka", "Chittagong", "Sylhet");
        stockWarningFilterComboBox.getItems().addAll("All", "Low", "Normal", "Out of Stock");

        loadInventoryFromFile();
        populateFilterComboBoxes();
        updateLowStockCount();
    }

    @javafx.fxml.FXML
    public void raisePurchaseRequestONA(ActionEvent actionEvent) {
        statusLabel.setText("");

        inventoryOverviewClass selectedItem = inventoryTableView.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            statusLabel.setText("Please select an item to raise purchase request.");
            return;
        }

        if (!selectedItem.getStockWarning().equals("Low") && !selectedItem.getStockWarning().equals("Out of Stock")) {
            statusLabel.setText("Selected item does not require purchase request.");
            return;
        }

        statusLabel.setText("Purchase request raised for " + selectedItem.getIngredient() + " at " + selectedItem.getOutlet());
    }

    @javafx.fxml.FXML
    public void applyfiltersONA(ActionEvent actionEvent) {
        statusLabel.setText("");

        String ingredientFilter = ingredientFilterComboBox.getValue();
        String outletFilter = outletFilterComboBox.getValue();
        String stockWarningFilter = stockWarningFilterComboBox.getValue();

        javafx.collections.ObservableList<inventoryOverviewClass> filteredList = javafx.collections.FXCollections.observableArrayList();

        for (inventoryOverviewClass item : allInventoryData) {
            boolean matches = true;

            if (ingredientFilter != null && !ingredientFilter.equals("All") && !item.getIngredient().equals(ingredientFilter)) {
                matches = false;
            }

            if (outletFilter != null && !outletFilter.equals("All") && !item.getOutlet().equals(outletFilter)) {
                matches = false;
            }

            if (stockWarningFilter != null && !stockWarningFilter.equals("All") && !item.getStockWarning().equals(stockWarningFilter)) {
                matches = false;
            }

            if (matches) {
                filteredList.add(item);
            }
        }

        inventoryTableView.setItems(filteredList);
        updateLowStockCount();
        statusLabel.setText("Filters applied.");
    }

    private void loadInventoryFromFile() {
        allInventoryData = javafx.collections.FXCollections.observableArrayList();

        try {
            File f = new File("data/Inventory.bin");
            if (!f.exists()) {
                return;
            }

            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    inventoryOverviewClass item = (inventoryOverviewClass) ois.readObject();
                    allInventoryData.add(item);
                } catch (EOFException eof) {
                    break;
                }
            }

            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        inventoryTableView.setItems(allInventoryData);
    }

    private void populateFilterComboBoxes() {
        for (inventoryOverviewClass item : allInventoryData) {
            if (!ingredientFilterComboBox.getItems().contains(item.getIngredient())) {
                ingredientFilterComboBox.getItems().add(item.getIngredient());
            }
            if (!outletFilterComboBox.getItems().contains(item.getOutlet())) {
                outletFilterComboBox.getItems().add(item.getOutlet());
            }
            if (!stockWarningFilterComboBox.getItems().contains(item.getStockWarning())) {
                stockWarningFilterComboBox.getItems().add(item.getStockWarning());
            }
        }
    }

    private void updateLowStockCount() {
        int count = 0;

        for (inventoryOverviewClass item : inventoryTableView.getItems()) {
            if (item.getStockWarning().equals("Low") || item.getStockWarning().equals("Out of Stock")) {
                count++;
            }
        }

        lowStockCountLabel.setText("Low/Out Count: " + count);
    }
}