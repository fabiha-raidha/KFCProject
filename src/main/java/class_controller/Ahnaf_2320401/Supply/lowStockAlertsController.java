package class_controller.Ahnaf_2320401.Supply;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class lowStockAlertsController
{
    @javafx.fxml.FXML
    private ComboBox<String> categoryFilterComboBox;
    @javafx.fxml.FXML
    private TableView<lowStockAlertsClass> lowStockTableView;
    @javafx.fxml.FXML
    private TableColumn<lowStockAlertsClass, Integer> minLevelTableColumn;
    @javafx.fxml.FXML
    private TableColumn<lowStockAlertsClass, Integer> currentQtyTableColumn;
    @javafx.fxml.FXML
    private ComboBox<String> outletFilterComboBox;
    @javafx.fxml.FXML
    private TableColumn<lowStockAlertsClass, String> itemTableColumn;
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private TableColumn<lowStockAlertsClass, String> outletTableColumn;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void applyFiltersONA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void openLowStockAlertsONA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void createPurchaseONA(ActionEvent actionEvent) {
    }
}