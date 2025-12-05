package class_controller.Fabiha_2211214.CMO;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class CMOOverviewController {

    @FXML private Label activeCampaignsLabel;
    @FXML private Label budgetLabel;
    @FXML private Label ratingLabel;
    @FXML private BarChart<String, Number> campaignRoiChart;
    @FXML private LineChart<String, Number> salesTrendChart;

    @FXML
    public void initialize() {
        loadKpis();
        loadCharts();
    }

    private void loadKpis() {
        activeCampaignsLabel.setText("4");
        budgetLabel.setText("15,400");
        ratingLabel.setText("4.5/5");
    }

    private void loadCharts() {
        XYChart.Series<String, Number> roiSeries = new XYChart.Series<>();
        roiSeries.setName("Return on Investment");
        roiSeries.getData().add(new XYChart.Data<>("Winter Promo", 150));
        roiSeries.getData().add(new XYChart.Data<>("Student Deal", 210));
        roiSeries.getData().add(new XYChart.Data<>("Family Bundle", 180));
        campaignRoiChart.getData().add(roiSeries);

        XYChart.Series<String, Number> trendSeries = new XYChart.Series<>();
        trendSeries.setName("Revenue");
        trendSeries.getData().add(new XYChart.Data<>("Jan", 50000));
        trendSeries.getData().add(new XYChart.Data<>("Feb", 65000));
        trendSeries.getData().add(new XYChart.Data<>("Mar", 62000));
        salesTrendChart.getData().add(trendSeries);
    }
}