package class_controller.Fabiha_2211214.HeadChef;

import class_controller.Marzana_2432038.StoreManager.StaffSchedule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;

public class AttendanceController {

    @FXML private ComboBox<String> durationComboBox;
    @FXML private TextField employeeSearchTextField;
    @FXML private TextField lineSearchTextField;
    @FXML private TableView<AttendanceRecord> attendanceTableView;
    @FXML private TableColumn<AttendanceRecord, String> workerNameColumn;
    @FXML private TableColumn<AttendanceRecord, String> employeeIdColumn;
    @FXML private TableColumn<AttendanceRecord, String> workLineColumn;
    @FXML private TableColumn<AttendanceRecord, String> statusColumn;
    @FXML private TableColumn<AttendanceRecord, String> dateColumn;
    @FXML private Label statusLabel;

    private ObservableList<AttendanceRecord> attendanceList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        workerNameColumn.setCellValueFactory(new PropertyValueFactory<>("workerName"));
        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        workLineColumn.setCellValueFactory(new PropertyValueFactory<>("workLine"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp"));

        loadAttendanceFromStoreManager();

        attendanceTableView.setItems(attendanceList);

        durationComboBox.getItems().addAll("All", "Today", "This Week");
        durationComboBox.setValue("All");

        updateStatusLabel();
    }

    @FXML
    public void handleFetchAttendanceONA(ActionEvent actionEvent) {
        String duration = durationComboBox.getValue();
        String searchName = employeeSearchTextField.getText().toLowerCase().trim();
        String searchLine = lineSearchTextField.getText().toLowerCase().trim();

        ObservableList<AttendanceRecord> filtered = FXCollections.observableArrayList();

        for (AttendanceRecord rec : attendanceList) {
            boolean matchName = searchName.isEmpty() ||
                    rec.getWorkerName().toLowerCase().contains(searchName) ||
                    rec.getEmployeeId().toLowerCase().contains(searchName);
            boolean matchLine = searchLine.isEmpty() ||
                    rec.getWorkLine().toLowerCase().contains(searchLine);

            if (matchName && matchLine) {
                filtered.add(rec);
            }
        }

        attendanceTableView.setItems(filtered);
        statusLabel.setText("Showing " + filtered.size() + " records.");
    }

    private void loadAttendanceFromStoreManager() {
        attendanceList.clear();

        File f = new File("data/StaffSchedule.bin");

        if (!f.exists()) {
            statusLabel.setText("No schedule file found from Store Manager.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            while (true) {
                try {
                    StaffSchedule schedule = (StaffSchedule) ois.readObject();

                    String timestamp = schedule.getShiftDate().toString() + " " + schedule.getStartTime();

                    AttendanceRecord record = new AttendanceRecord(
                            schedule.getName(),
                            schedule.getStaffId(),
                            schedule.getRole(),
                            schedule.getStatus(),
                            timestamp
                    );

                    attendanceList.add(record);

                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load attendance data.");
        }
    }

    private void updateStatusLabel() {
        statusLabel.setText("Total Records: " + attendanceList.size());
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public static class AttendanceRecord {
        private String workerName;
        private String employeeId;
        private String workLine;
        private String status;
        private String timestamp;

        public AttendanceRecord(String workerName, String employeeId, String workLine, String status, String timestamp) {
            this.workerName = workerName;
            this.employeeId = employeeId;
            this.workLine = workLine;
            this.status = status;
            this.timestamp = timestamp;
        }

        public String getWorkerName() { return workerName; }
        public String getEmployeeId() { return employeeId; }
        public String getWorkLine() { return workLine; }
        public String getStatus() { return status; }
        public String getTimestamp() { return timestamp; }
    }
}