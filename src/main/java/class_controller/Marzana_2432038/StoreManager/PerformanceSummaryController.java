package class_controller.Marzana_2432038.StoreManager;

import class_controller.AppendableObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class PerformanceSummaryController {

    @FXML
    private TableView<PerformanceSummary> performanceTableView;
    @FXML
    private TableColumn<PerformanceSummary, Double> totalSalesTC;
    @FXML
    private TableColumn<PerformanceSummary, Double> averageRatingTC;
    @FXML
    private TableColumn<PerformanceSummary, Double> attendancePercentageTC;

    @FXML
    private TextField totalSalesTF;
    @FXML
    private TextField averageRatingTF;
    @FXML
    private TextField attendancePercentageTF;
    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {
        totalSalesTC.setCellValueFactory(new PropertyValueFactory<>("totalSales"));
        averageRatingTC.setCellValueFactory(new PropertyValueFactory<>("averageRating"));
        attendancePercentageTC.setCellValueFactory(new PropertyValueFactory<>("attendancePercentage"));

        loadSummariesFromFile();
    }

    @FXML
    public void handleSaveSummaryOA(ActionEvent actionEvent) {

        messageLabel.setText("");

        if (totalSalesTF.getText().isEmpty()
                || averageRatingTF.getText().isEmpty()
                || attendancePercentageTF.getText().isEmpty()) {
            messageLabel.setText("Please enter Total Sales, Average Rating and Attendance %.");
            return;
        }

        double totalSales;
        double averageRating;
        double attendancePercentage;

        try {
            totalSales = Double.parseDouble(totalSalesTF.getText());
            averageRating = Double.parseDouble(averageRatingTF.getText());
            attendancePercentage = Double.parseDouble(attendancePercentageTF.getText());
        } catch (NumberFormatException e) {
            messageLabel.setText("All values must be numeric.");
            return;
        }

        if (averageRating < 0 || averageRating > 5) {
            messageLabel.setText("Average Rating should be between 0 and 5.");
            return;
        }

        if (attendancePercentage < 0 || attendancePercentage > 100) {
            messageLabel.setText("Attendance % should be between 0 and 100.");
            return;
        }

        PerformanceSummary summary = new PerformanceSummary(
                totalSales,
                averageRating,
                attendancePercentage
        );

        performanceTableView.getItems().add(summary);
        saveSummaryToFile(summary);
        messageLabel.setText("Performance summary saved.");
        clearForm();
    }

    private void saveSummaryToFile(PerformanceSummary summary) {
        try {
            File f = new File("data/PerformanceSummary.bin");

            if (f.exists()) {
                FileOutputStream fos = new FileOutputStream(f, true);
                ObjectOutputStream oos = new AppendableObjectOutputStream(fos);
                oos.writeObject(summary);
                oos.close();
            } else {
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(summary);
                oos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Error saving summary.");
        }
    }

    private void loadSummariesFromFile() {
        performanceTableView.getItems().clear();

        try {
            File f = new File("data/PerformanceSummary.bin");

            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    PerformanceSummary s = (PerformanceSummary) ois.readObject();
                    performanceTableView.getItems().add(s);
                } catch (EOFException eof) {
                    break;
                }
            }

            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearForm() {
        totalSalesTF.clear();
        averageRatingTF.clear();
        attendancePercentageTF.clear();
    }
}
