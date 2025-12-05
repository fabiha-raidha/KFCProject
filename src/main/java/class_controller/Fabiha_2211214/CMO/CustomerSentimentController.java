package class_controller.Fabiha_2211214.CMO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;

public class CustomerSentimentController {

    @FXML private Label avgSentimentLabel;
    @FXML private Label totalReviewsLabel;
    @FXML private Label negativeCountLabel;
    @FXML private PieChart sentimentPieChart;
    @FXML private BarChart<String, Number> complaintsChart;
    @FXML private ComboBox<String> ratingFilterComboBox;
    @FXML private ComboBox<String> regionFilterComboBox;
    @FXML private TableView<CustomerSentiment> feedbackTableView;
    @FXML private TableColumn<CustomerSentiment, String> idColumn;
    @FXML private TableColumn<CustomerSentiment, String> customerColumn;
    @FXML private TableColumn<CustomerSentiment, String> regionColumn;
    @FXML private TableColumn<CustomerSentiment, Integer> ratingColumn;
    @FXML private TableColumn<CustomerSentiment, String> sentimentColumn;
    @FXML private TableColumn<CustomerSentiment, String> commentColumn;
    @FXML private TableColumn<CustomerSentiment, LocalDate> dateColumn;

    private ObservableList<CustomerSentiment> masterList = FXCollections.observableArrayList();
    private FilteredList<CustomerSentiment> filteredList;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("feedbackId"));
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        regionColumn.setCellValueFactory(new PropertyValueFactory<>("region"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        sentimentColumn.setCellValueFactory(new PropertyValueFactory<>("sentiment"));
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        loadFeedbackFromBin();

        filteredList = new FilteredList<>(masterList, p -> true);
        feedbackTableView.setItems(filteredList);

        ratingFilterComboBox.getItems().addAll("All", "5 Stars", "4 Stars", "3 Stars", "2 Stars", "1 Star");
        regionFilterComboBox.getItems().addAll("All", "Dhaka", "Chittagong", "Sylhet");

        updateDashboard();
    }

    private void loadFeedbackFromBin() {
        masterList.clear();
        File f = new File("CustomerFeedback.bin");
        if (f.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
                while (true) {
                    try {
                        CustomerSentiment obj = (CustomerSentiment) ois.readObject();
                        masterList.add(obj);
                    } catch (Exception e) {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            masterList.add(new CustomerSentiment("FB-001", "Rahim", "Dhaka", 5, "Amazing Zinger Burger!", LocalDate.now().minusDays(1)));
            masterList.add(new CustomerSentiment("FB-002", "Karim", "Chittagong", 2, "Delivery was very late.", LocalDate.now().minusDays(2)));
            masterList.add(new CustomerSentiment("FB-003", "Suma", "Sylhet", 4, "Good food but cold fries.", LocalDate.now().minusDays(3)));
        }
    }

    private void updateDashboard() {
        double totalRating = 0;
        int negativeCount = 0;
        int positiveCount = 0;
        int neutralCount = 0;

        for (CustomerSentiment fb : filteredList) {
            totalRating += fb.getRating();
            if (fb.getSentiment().equals("Negative")) negativeCount++;
            else if (fb.getSentiment().equals("Positive")) positiveCount++;
            else neutralCount++;
        }

        double avgRating = filteredList.isEmpty() ? 0 : totalRating / filteredList.size();

        avgSentimentLabel.setText(String.format("%.1f / 5.0", avgRating));
        totalReviewsLabel.setText(String.valueOf(filteredList.size()));
        negativeCountLabel.setText(String.valueOf(negativeCount));

        sentimentPieChart.getData().clear();
        sentimentPieChart.getData().add(new PieChart.Data("Positive", positiveCount));
        sentimentPieChart.getData().add(new PieChart.Data("Neutral", neutralCount));
        sentimentPieChart.getData().add(new PieChart.Data("Negative", negativeCount));

        complaintsChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Frequency");
        series.getData().add(new XYChart.Data<>("Food Quality", positiveCount * 0.8));
        series.getData().add(new XYChart.Data<>("Delivery Time", negativeCount * 0.6));
        series.getData().add(new XYChart.Data<>("Service", negativeCount * 0.4));
        complaintsChart.getData().add(series);
    }

    @FXML
    public void handleApplyFilterONA(ActionEvent event) {
        String ratingFilter = ratingFilterComboBox.getValue();
        String regionFilter = regionFilterComboBox.getValue();

        filteredList.setPredicate(fb -> {
            boolean matchRating = true;
            boolean matchRegion = true;

            if (ratingFilter != null && !ratingFilter.equals("All")) {
                int ratingVal = Integer.parseInt(ratingFilter.substring(0, 1));
                matchRating = fb.getRating() == ratingVal;
            }

            if (regionFilter != null && !regionFilter.equals("All")) {
                matchRegion = fb.getRegion().equals(regionFilter);
            }

            return matchRating && matchRegion;
        });

        updateDashboard();
    }

    @FXML
    public void handleResetFilterONA(ActionEvent event) {
        ratingFilterComboBox.setValue("All");
        regionFilterComboBox.setValue("All");
        filteredList.setPredicate(p -> true);
        updateDashboard();
    }

}