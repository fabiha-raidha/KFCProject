package class_controller.Fabiha_2211214.HeadChef;

import class_controller.AppendableObjectOutputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;

public class QualityControlController {

    @FXML private TableView<InspectionLog> inspectionLogTableView;
    @FXML private TableColumn<InspectionLog, String> logTypeColumn;
    @FXML private TableColumn<InspectionLog, LocalDate> lastCheckColumn;
    @FXML private TableColumn<InspectionLog, LocalDate> nextScheduleColumn;
    @FXML private TableColumn<InspectionLog, Double> complianceScoreColumn;
    @FXML private TableColumn<InspectionLog, String> actionsColumn;

    @FXML private TableView<TemperatureLog> tempLogTableView;
    @FXML private TableColumn<TemperatureLog, String> tempItemColumn;
    @FXML private TableColumn<TemperatureLog, Double> tempReadingColumn;
    @FXML private TableColumn<TemperatureLog, String> timestampColumn;
    @FXML private TableColumn<TemperatureLog, String> tempStatusColumn;

    @FXML private TextField tempItemTextField;
    @FXML private TextField tempReadingTextField;
    @FXML private Label checklistStatusLabel;
    @FXML private Label expiryAlertLabel;
    @FXML private Button viewChecklistButton;

    private ObservableList<TemperatureLog> tempList = FXCollections.observableArrayList();
    private ObservableList<InspectionLog> inspectionList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        logTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        lastCheckColumn.setCellValueFactory(new PropertyValueFactory<>("lastCheck"));
        nextScheduleColumn.setCellValueFactory(new PropertyValueFactory<>("nextSchedule"));
        complianceScoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        actionsColumn.setCellValueFactory(new PropertyValueFactory<>("action"));

        tempItemColumn.setCellValueFactory(new PropertyValueFactory<>("item"));
        tempReadingColumn.setCellValueFactory(new PropertyValueFactory<>("reading"));
        timestampColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        tempStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadData();
        tempLogTableView.setItems(tempList);
        inspectionLogTableView.setItems(inspectionList);

        expiryAlertLabel.setText("Expiry Alerts: 2 items expiring in < 3 days (Milk, Cheese)");
    }

    @FXML
    public void handleSaveNewTemperatureONA(ActionEvent actionEvent) {
        String item = tempItemTextField.getText();
        String readingStr = tempReadingTextField.getText();

        if (item == null || item.isEmpty() || readingStr == null || readingStr.isEmpty()) {
            showAlert("Error", "Please enter Item Name and Temperature.");
            return;
        }

        try {
            double reading = Double.parseDouble(readingStr);

            TemperatureLog newLog = new TemperatureLog(item, reading);

            tempList.add(newLog);
            saveTempLogToFile(newLog);

            if (newLog.getStatus().equals("Danger Zone")) {
                showAlert("Warning", "Temperature is in the Danger Zone (5°C - 60°C)!\nPlease take corrective action.");
            }

            tempItemTextField.clear();
            tempReadingTextField.clear();

        } catch (NumberFormatException e) {
            showAlert("Error", "Temperature must be a valid number.");
        }
    }

    @FXML
    public void handleDailyChecklistONA(ActionEvent actionEvent) {
        checklistStatusLabel.setText("Checklist Status: Completed (" + LocalDate.now() + ")");
        checklistStatusLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
        viewChecklistButton.setDisable(true);

        InspectionLog log = new InspectionLog("Daily Hygiene", LocalDate.now(), LocalDate.now().plusDays(1), 100.0, "Routine Check Passed");
        inspectionList.add(log);
        saveInspectionLogToFile(log);
    }

    private void saveTempLogToFile(TemperatureLog log) {
        File f = new File("data/TemperatureLogs.bin");
        appendObject(f, log);
    }

    private void saveInspectionLogToFile(InspectionLog log) {
        File f = new File("data/InspectionLogs.bin");
        appendObject(f, log);
    }

    private void appendObject(File f, Object obj) {
        try {
            if (f.exists()) {
                FileOutputStream fos = new FileOutputStream(f, true);
                ObjectOutputStream oos = new AppendableObjectOutputStream(fos);
                oos.writeObject(obj);
                oos.close();
            } else {
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(obj);
                oos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        tempList.clear();
        loadListFromFile("data/TemperatureLogs.bin", tempList);

        inspectionList.clear();
        loadListFromFile("data/InspectionLogs.bin", inspectionList);

        if (inspectionList.isEmpty()) {
            inspectionList.add(new InspectionLog("Weekly Deep Clean", LocalDate.now().minusDays(5), LocalDate.now().plusDays(2), 95.0, "All clear"));
        }
    }

    private <T> void loadListFromFile(String fileName, ObservableList<T> list) {
        File f = new File(fileName);
        if (!f.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            while (true) {
                try {
                    list.add((T) ois.readObject());
                } catch (EOFException e) { break; }
            }
        } catch (IOException | ClassNotFoundException e) { }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}