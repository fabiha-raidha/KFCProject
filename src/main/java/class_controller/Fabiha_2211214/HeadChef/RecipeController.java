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

public class RecipeController {

    private Repository<Recipe> recipeRepo;
    private ObservableList<Recipe> recipeList;
    private FilteredList<Recipe> filteredData;
    private int idCounter = 101;

    @FXML private TableView<Recipe> recipeTableView;
    @FXML private TableColumn<Recipe, Integer> dishIdTableColumn;
    @FXML private TableColumn<Recipe, String> nameTableColumn;
    @FXML private TableColumn<Recipe, Double> priceTableColumn;
    @FXML private TableColumn<Recipe, String> statusTableColumn;
    @FXML private TableColumn<Recipe, String> regionsTableColumn;
    @FXML private TableColumn<Recipe, String> actionsTableColumn;

    @FXML private ComboBox<String> statusFilterComboBox;
    @FXML private ComboBox<String> regionFilterComboBox;
    @FXML private TextField dishSearchTextField;

    @FXML private TextField newDishNameTextField;
    @FXML private TextField newPriceTextField;
    @FXML private ComboBox<String> newStatusComboBox;
    @FXML private ComboBox<String> newRegionComboBox;
    @FXML private Label totalRecipeLabel;

    @FXML
    public void initialize() {
        recipeRepo = new BinaryFileRepository<>("data/Recipes.bin");
        recipeList = FXCollections.observableArrayList(recipeRepo.getAll());

        if (!recipeList.isEmpty()) {
            idCounter = recipeList.stream().mapToInt(Recipe::getId).max().orElse(100) + 1;
        }

        dishIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        statusTableColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        regionsTableColumn.setCellValueFactory(new PropertyValueFactory<>("regions"));

        setupFiltering();
        setupComboBoxes();
        updateTotalCount();

        recipeTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> populateInputs(newVal));
    }

    @FXML
    public void handleSaveNewRecipeONA(ActionEvent actionEvent) {
        if (!validateInputs()) return;
        if (checkDuplicate(newDishNameTextField.getText().trim())) return;

        Recipe newRecipe = new Recipe(
                idCounter++,
                newDishNameTextField.getText().trim(),
                Double.parseDouble(newPriceTextField.getText()),
                newStatusComboBox.getValue(),
                newRegionComboBox.getValue()
        );

        recipeRepo.add(newRecipe);

        recipeList.add(newRecipe);
        clearInputs();
        updateTotalCount();
    }

    @FXML
    public void handleUpdateRecipeONA(ActionEvent actionEvent) {
        Recipe selected = recipeTableView.getSelectionModel().getSelectedItem();
        if (selected == null) { showAlert("No Selection", "Select a recipe to update."); return; }
        if (!validateInputs()) return;

        selected.setName(newDishNameTextField.getText().trim());
        selected.setPrice(Double.parseDouble(newPriceTextField.getText()));
        selected.setStatus(newStatusComboBox.getValue());
        selected.setRegions(newRegionComboBox.getValue());

        recipeRepo.update(selected);

        recipeTableView.refresh();
        clearInputs();
    }


    private void setupComboBoxes() {
        statusFilterComboBox.getItems().addAll("All", "Active", "Proposed", "Discontinued");
        statusFilterComboBox.setValue("All");
        regionFilterComboBox.getItems().addAll("All", "Dhaka", "Chittagong", "Sylhet", "Global");
        regionFilterComboBox.setValue("All");
        newStatusComboBox.getItems().addAll("Active", "Proposed", "Discontinued");
        newRegionComboBox.getItems().addAll("Dhaka", "Chittagong", "Sylhet", "Global");
    }

    private void setupFiltering() {
        filteredData = new FilteredList<>(recipeList, p -> true);
        SortedList<Recipe> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(recipeTableView.comparatorProperty());
        recipeTableView.setItems(sortedData);
    }

    @FXML
    public void handleFilterRecipesONA(ActionEvent event) {
        String s = statusFilterComboBox.getValue();
        String r = regionFilterComboBox.getValue();
        String t = dishSearchTextField.getText().toLowerCase().trim();

        filteredData.setPredicate(item -> {
            if (s != null && !s.equals("All") && !item.getStatus().equals(s)) return false;
            if (r != null && !r.equals("All") && !item.getRegions().equals(r)) return false;
            if (!t.isEmpty() && !item.getName().toLowerCase().contains(t)) return false;
            return true;
        });
        updateTotalCount();
    }

    @FXML
    public void handleResetFilterONA(ActionEvent event) {
        statusFilterComboBox.setValue("All");
        regionFilterComboBox.setValue("All");
        dishSearchTextField.clear();
        filteredData.setPredicate(p -> true);
        updateTotalCount();
    }

    private void populateInputs(Recipe r) {
        if (r == null) return;
        newDishNameTextField.setText(r.getName());
        newPriceTextField.setText(String.valueOf(r.getPrice()));
        newStatusComboBox.setValue(r.getStatus());
        newRegionComboBox.setValue(r.getRegions());
    }

    private boolean validateInputs() {
        if (newDishNameTextField.getText().isEmpty() || newPriceTextField.getText().isEmpty()) {
            showAlert("Error", "Fill all fields."); return false;
        }
        return true;
    }

    private boolean checkDuplicate(String name) {
        if (recipeList.stream().anyMatch(r -> r.getName().equalsIgnoreCase(name))) {
            showAlert("Duplicate", "Name exists."); return true;
        }
        return false;
    }

    private void clearInputs() { newDishNameTextField.clear(); newPriceTextField.clear(); newStatusComboBox.setValue(null); newRegionComboBox.setValue(null); }
    private void updateTotalCount() { totalRecipeLabel.setText("Total: " + filteredData.size()); }
    private void showAlert(String t, String c) { new Alert(Alert.AlertType.WARNING, c).showAndWait(); }
}