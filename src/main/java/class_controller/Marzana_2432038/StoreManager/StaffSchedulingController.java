package class_controller.Marzana_2432038.StoreManager;

import class_controller.AppendableObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;

public class StaffSchedulingController
{
    @FXML
    private Label summaryLabel;
    @FXML
    private TextField endTimeTextField;
    @FXML
    private TextField staffIdTextField;
    @FXML
    private DatePicker shiftDatePicker;
    @FXML
    private TableView<StaffSchedule> scheduleTableView;
    @FXML
    private TextField nameTextField;
    @FXML
    private ComboBox<String> roleComboBox;
    @FXML
    private ComboBox<String> statusComboBox;
    @FXML
    private TextField startTimeTextField;

    @FXML
    private TableColumn<StaffSchedule, String> staffIdColumn;
    @FXML
    private TableColumn<StaffSchedule, String> nameColumn;
    @FXML
    private TableColumn<StaffSchedule, String> roleColumn;
    @FXML
    private TableColumn<StaffSchedule, LocalDate> shiftDateColumn;
    @FXML
    private TableColumn<StaffSchedule, String> startTimeColumn;
    @FXML
    private TableColumn<StaffSchedule, String> endTimeColumn;
    @FXML
    private TableColumn<StaffSchedule, String> statusColumn;
    @FXML
    private TableColumn<StaffSchedule, Void> modifyColumn;

    @FXML
    public void initialize() {

        staffIdColumn.setCellValueFactory(new PropertyValueFactory<>("staffId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        shiftDateColumn.setCellValueFactory(new PropertyValueFactory<>("shiftDate"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        roleComboBox.getItems().addAll("Cashier", "Chef", "Waiter", "Cleaner");
        statusComboBox.getItems().addAll("Scheduled", "Completed", "Cancelled");

    }

    @FXML
    public void handleDoneOnAction(ActionEvent actionEvent) {

        scheduleTableView.getItems().clear();

        try {
            File f = new File("data/StaffSchedule.bin");

            StaffSchedule s = new StaffSchedule(
                    staffIdTextField.getText(),
                    nameTextField.getText(),
                    roleComboBox.getValue(),
                    shiftDatePicker.getValue(),
                    startTimeTextField.getText(),
                    endTimeTextField.getText(),
                    statusComboBox.getValue()
            );

            if (f.exists()) {
                FileOutputStream fos = new FileOutputStream(f, true);
                ObjectOutputStream oos = new AppendableObjectOutputStream(fos);
                oos.writeObject(s);
                oos.close();
            } else {
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(s);
                oos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fis = new FileInputStream("data/StaffSchedule.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    StaffSchedule s = (StaffSchedule) ois.readObject();
                    scheduleTableView.getItems().add(s);
                    summaryLabel.setText("Schedule saved successfully.");
                } catch (EOFException e1) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
