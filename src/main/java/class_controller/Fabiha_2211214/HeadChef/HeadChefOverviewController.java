package class_controller.Fabiha_2211214.HeadChef;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class HeadChefOverviewController {

    @FXML private Label activeOrdersLabel;
    @FXML private Label staffOnDutyLabel;
    @FXML private Label lowStockLabel;
    @FXML private LineChart<String, Number> orderTrafficChart;
    @FXML private BarChart<String, Number> topDishesChart;

    @FXML
    public void initialize() {
        loadSummaryData();
        loadCharts();
    }

    private void loadSummaryData() {
        activeOrdersLabel.setText("12");
        staffOnDutyLabel.setText("8/12");
        lowStockLabel.setText("3 Items");
    }

    private void loadCharts() {
        XYChart.Series<String, Number> trafficSeries = new XYChart.Series<>();
        trafficSeries.setName("Today's Orders");
        trafficSeries.getData().add(new XYChart.Data<>("10 AM", 5));
        trafficSeries.getData().add(new XYChart.Data<>("12 PM", 45));
        trafficSeries.getData().add(new XYChart.Data<>("2 PM", 30));
        trafficSeries.getData().add(new XYChart.Data<>("4 PM", 15));
        trafficSeries.getData().add(new XYChart.Data<>("6 PM", 60));
        orderTrafficChart.getData().add(trafficSeries);

        XYChart.Series<String, Number> dishSeries = new XYChart.Series<>();
        dishSeries.setName("Sales Qty");
        dishSeries.getData().add(new XYChart.Data<>("Zinger Burger", 120));
        dishSeries.getData().add(new XYChart.Data<>("Hot Wings", 95));
        dishSeries.getData().add(new XYChart.Data<>("Popcorn Chicken", 80));
        topDishesChart.getData().add(dishSeries);
    }
}