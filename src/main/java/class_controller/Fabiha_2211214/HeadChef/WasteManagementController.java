package class_controller.Fabiha_2211214.HeadChef;

import javafx.event.ActionEvent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class WasteManagementController {
    @javafx.fxml.FXML
    private TextField ingredientIdTextField;
    @javafx.fxml.FXML
    private Label costImpactLabel;
    @javafx.fxml.FXML
    private Label primaryCategoryLabel;
    @javafx.fxml.FXML
    private LineChart wasteTrendLineChart;
    @javafx.fxml.FXML
    private PieChart wasteCategoryPieChart;
    @javafx.fxml.FXML
    private TextArea wasteNotesTextArea;
    @javafx.fxml.FXML
    private Label todayWasteLabel;
    @javafx.fxml.FXML
    private TextField wasteQuantityTextField;
    @javafx.fxml.FXML
    private ComboBox wasteReasonComboBox;

    @javafx.fxml.FXML
    public void handleDownloadReportPdfONA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleSubmitWasteLogONA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleGenerateWeeklyReportONA(ActionEvent actionEvent) {
    }
}
