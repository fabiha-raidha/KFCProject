package class_controller.Ahnaf_2320401.Supply;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class supplierReportsController
{
    @javafx.fxml.FXML
    private DatePicker endDatePicker;
    @javafx.fxml.FXML
    private TableView<supplierReportClass> reportTableView;
    @javafx.fxml.FXML
    private DatePicker startDatePicker;
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private ComboBox<String> supplierComboBox;
    @javafx.fxml.FXML
    private TableColumn<supplierReportClass, Integer> valueTableColumn;
    @javafx.fxml.FXML
    private TableColumn<supplierReportClass, String> remarksTableColumn;
    @javafx.fxml.FXML
    private TableColumn<supplierReportClass, String> metricTableColumn;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void exportReportONA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void generateReportONA(ActionEvent actionEvent) {
    }
}