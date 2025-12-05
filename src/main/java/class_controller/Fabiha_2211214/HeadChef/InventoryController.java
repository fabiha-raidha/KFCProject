package class_controller.Fabiha_2211214.HeadChef;

import class_controller.BinaryFileRepository;
import class_controller.Repository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class InventoryController {

    private Repository<Ingredient> inventoryRepo;
    private ObservableList<Ingredient> ingredientList;
    private FilteredList<Ingredient> filteredData;
    private int idCounter = 1001;

    @FXML private TableView<Ingredient> ingredientTableView;
    @FXML private TableColumn<Ingredient, Integer> idTableColumn;
    @FXML private TableColumn<Ingredient, String> nameTableColumn;
    @FXML private TableColumn<Ingredient, Double> quantityTableColumn;
    @FXML private TableColumn<Ingredient, Double> requiredQuantityTableColumn;
    @FXML private TableColumn<Ingredient, String> stockWarningTableColumn;
    @FXML private TableColumn<Ingredient, String> usedInRecipesTableColumn;

    @FXML private TextField nameFilterTextField;
    @FXML private TextField recipeFilterTextField;
    @FXML private ComboBox<String> stockWarningFilterComboBox;

    @FXML private TextField newNameTextField;
    @FXML private TextField newCurrentQtyTextField;
    @FXML private TextField newRequiredQtyTextField;
    @FXML private TextField newUsedInTextField;
    @FXML private Label statusLabel;

    @FXML
    public void initialize() {

        inventoryRepo = new BinaryFileRepository<>("data/Ingredients.bin");
        ingredientList = FXCollections.observableArrayList(inventoryRepo.getAll());

        if (!ingredientList.isEmpty()) {
            idCounter = ingredientList.stream().mapToInt(Ingredient::getId).max().orElse(1000) + 1;
        }

        setupTable();

        filteredData = new FilteredList<>(ingredientList, p -> true);
        SortedList<Ingredient> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(ingredientTableView.comparatorProperty());
        ingredientTableView.setItems(sortedData);

        ingredientTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> populateInputs(newVal));
        updateStatusLabel();
    }

    private void setupTable() {
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityTableColumn.setCellValueFactory(new PropertyValueFactory<>("currentQty"));
        requiredQuantityTableColumn.setCellValueFactory(new PropertyValueFactory<>("requiredQty"));
        stockWarningTableColumn.setCellValueFactory(new PropertyValueFactory<>("stockStatus"));
        usedInRecipesTableColumn.setCellValueFactory(new PropertyValueFactory<>("usedInRecipes"));
        stockWarningFilterComboBox.getItems().addAll("All", "Sufficient", "Low Stock", "Out of Stock");
        stockWarningFilterComboBox.setValue("All");
    }

    @FXML
    public void handleAddIngredientONA(ActionEvent event) {
        if (!validateInputs()) return;

        Ingredient newItem = new Ingredient(
                idCounter++,
                newNameTextField.getText().trim(),
                Double.parseDouble(newCurrentQtyTextField.getText()),
                Double.parseDouble(newRequiredQtyTextField.getText()),
                newUsedInTextField.getText().trim()
        );

        inventoryRepo.add(newItem);
        ingredientList.add(newItem);

        clearInputs();
        updateStatusLabel();
    }

    @FXML
    public void handleUpdateStockONA(ActionEvent event) {
        Ingredient selected = ingredientTableView.getSelectionModel().getSelectedItem();
        if (selected == null) return;
        if (!validateInputs()) return;

        selected.setName(newNameTextField.getText().trim());
        selected.setCurrentQty(Double.parseDouble(newCurrentQtyTextField.getText()));
        selected.setRequiredQty(Double.parseDouble(newRequiredQtyTextField.getText()));
        selected.setUsedInRecipes(newUsedInTextField.getText().trim());

        inventoryRepo.update(selected);
        ingredientTableView.refresh();
        clearInputs();
    }

    @FXML
    public void handleFilterONA(ActionEvent event) {
        String n = nameFilterTextField.getText().toLowerCase().trim();
        String r = recipeFilterTextField.getText().toLowerCase().trim();
        String s = stockWarningFilterComboBox.getValue();

        filteredData.setPredicate(item -> {
            if (!n.isEmpty() && !item.getName().toLowerCase().contains(n)) return false;
            if (!r.isEmpty() && !item.getUsedInRecipes().toLowerCase().contains(r)) return false;
            if (s != null && !s.equals("All") && !item.getStockStatus().equals(s)) return false;
            return true;
        });
        updateStatusLabel();
    }

    @FXML
    public void handleResetFilterONA(ActionEvent event) {
        nameFilterTextField.clear();
        recipeFilterTextField.clear();
        stockWarningFilterComboBox.setValue("All");
        filteredData.setPredicate(p -> true);
        updateStatusLabel();
    }

    private void populateInputs(Ingredient i) {
        if (i == null) return;
        newNameTextField.setText(i.getName());
        newCurrentQtyTextField.setText(String.valueOf(i.getCurrentQty()));
        newRequiredQtyTextField.setText(String.valueOf(i.getRequiredQty()));
        newUsedInTextField.setText(i.getUsedInRecipes());
    }

    private boolean validateInputs() {
        if (newNameTextField.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Name Required").show(); return false; }
        return true;
    }
    private void clearInputs() { newNameTextField.clear(); newCurrentQtyTextField.clear(); newRequiredQtyTextField.clear(); newUsedInTextField.clear(); }
    private void updateStatusLabel() { statusLabel.setText("Total: " + filteredData.size()); }
}