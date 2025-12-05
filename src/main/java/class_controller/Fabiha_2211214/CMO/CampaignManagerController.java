package class_controller.Fabiha_2211214.CMO;

import class_controller.BinaryFileRepository;
import class_controller.Repository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;

public class CampaignManagerController {

    private Repository<Campaign> campaignRepo;
    private ObservableList<Campaign> campaignList;

    @FXML private TableView<Campaign> campaignTableView;
    @FXML private TableColumn<Campaign, String> campaignNameColumn;
    @FXML private TableColumn<Campaign, String> targetRegionColumn;
    @FXML private TableColumn<Campaign, LocalDate> startDateColumn;
    @FXML private TableColumn<Campaign, LocalDate> endDateColumn;
    @FXML private TableColumn<Campaign, Double> budgetColumn;
    @FXML private TableColumn<Campaign, String> statusColumn;
    @FXML private PieChart budgetPieChart;
    @FXML private TextField newCampaignNameTextField;
    @FXML private ComboBox<String> targetRegionComboBox;
    @FXML private TextField newBudgetTextField;
    @FXML private DatePicker newStartDatePicker;
    @FXML private DatePicker newEndDatePicker;
    @FXML private Label statusLabel;

    @FXML
    public void initialize() {
        campaignRepo = new BinaryFileRepository<>("data/Campaigns.bin");
        campaignList = FXCollections.observableArrayList(campaignRepo.getAll());

        setupTable();
        campaignTableView.setItems(campaignList);
        updateChart();
        targetRegionComboBox.getItems().addAll("Dhaka", "Chittagong", "Sylhet", "Rajshahi");
    }

    private void setupTable() {
        campaignNameColumn.setCellValueFactory(new PropertyValueFactory<>("campaignName"));
        targetRegionColumn.setCellValueFactory(new PropertyValueFactory<>("targetRegion"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        budgetColumn.setCellValueFactory(new PropertyValueFactory<>("budget"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    @FXML
    public void handleCreateCampaignONA(ActionEvent event) {
        if (newCampaignNameTextField.getText().isEmpty()) return;

        Campaign newCampaign = new Campaign(
                newCampaignNameTextField.getText(),
                targetRegionComboBox.getValue(),
                Double.parseDouble(newBudgetTextField.getText()),
                newStartDatePicker.getValue(),
                newEndDatePicker.getValue(),
                "Active"
        );

        campaignRepo.add(newCampaign);

        campaignList.add(newCampaign);
        updateChart();
        statusLabel.setText("Saved!");
    }

    @FXML
    public void handleDeleteCampaignONA(ActionEvent event) {
        Campaign selected = campaignTableView.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        campaignRepo.delete(selected);

        campaignList.remove(selected);
        updateChart();
    }

    private void updateChart() {
        ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList();
        for (Campaign c : campaignList) {
            chartData.add(new PieChart.Data(c.getCampaignName(), c.getBudget()));
        }
        budgetPieChart.setData(chartData);
    }
}