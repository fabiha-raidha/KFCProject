package class_controller.Ahnaf_2320401.Quality;

import class_controller.AppendableObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;

public class trainingComplianceController {

    @javafx.fxml.FXML
    private TableView<trainingComplianceClass> trainingTableView;
    @javafx.fxml.FXML
    private TableColumn<trainingComplianceClass, String> staffMemberTableColumn;
    @javafx.fxml.FXML
    private TableColumn<trainingComplianceClass, String> moduleTableColumn;
    @javafx.fxml.FXML
    private TableColumn<trainingComplianceClass, LocalDate> dueDateTableColumn;
    @javafx.fxml.FXML
    private TableColumn<trainingComplianceClass, String> statusTableColumn;
    @javafx.fxml.FXML
    private ComboBox<String> staffComboBox;
    @javafx.fxml.FXML
    private ComboBox<String> moduleComboBox;
    @javafx.fxml.FXML
    private DatePicker dueDatePicker;
    @javafx.fxml.FXML
    private Label totalTrainingsLabel;
    @javafx.fxml.FXML
    private Label successLabel;
    @javafx.fxml.FXML
    private Label errorLabel;

    @javafx.fxml.FXML
    public void initialize() {
        staffMemberTableColumn.setCellValueFactory(new PropertyValueFactory<>("staffMember"));
        moduleTableColumn.setCellValueFactory(new PropertyValueFactory<>("module"));
        dueDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        statusTableColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        staffComboBox.getItems().addAll("Ahnaf", "Tahmid", "Pushon", "Farhan", "Rafiq");
        moduleComboBox.getItems().addAll("Food Safety", "Customer Service", "Hygiene Standards", "Fire Safety");

        loadTrainingsFromFile();
        updateTotalTrainings();
    }

    @javafx.fxml.FXML
    public void assignTrainingONA(ActionEvent actionEvent) {
        successLabel.setText("");
        errorLabel.setText("");

        if (staffComboBox.getValue() == null || moduleComboBox.getValue() == null ||
            dueDatePicker.getValue() == null) {
            errorLabel.setText("Please fill in all fields.");
            return;
        }

        String staffMember = staffComboBox.getValue();
        String module = moduleComboBox.getValue();
        LocalDate dueDate = dueDatePicker.getValue();

        if (dueDate.isBefore(LocalDate.now())) {
            errorLabel.setText("Due date cannot be in the past.");
            return;
        }

        trainingComplianceClass training = new trainingComplianceClass(staffMember, module, dueDate, "Assigned");

        trainingTableView.getItems().add(training);
        saveTrainingToFile(training);
        successLabel.setText("Training assigned successfully.");
        clearForm();
        updateTotalTrainings();
    }

    private void saveTrainingToFile(trainingComplianceClass training) {
        try {
            File f = new File("data/TrainingCompliance.bin");

            if (f.exists()) {
                FileOutputStream fos = new FileOutputStream(f, true);
                ObjectOutputStream oos = new AppendableObjectOutputStream(fos);
                oos.writeObject(training);
                oos.close();
            } else {
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(training);
                oos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorLabel.setText("Error saving training to file.");
        }
    }

    private void loadTrainingsFromFile() {
        try {
            File f = new File("data/TrainingCompliance.bin");

            if (f.exists()) {
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);

                while (true) {
                    try {
                        trainingComplianceClass training = (trainingComplianceClass) ois.readObject();
                        trainingTableView.getItems().add(training);
                    } catch (EOFException e) {
                        break;
                    }
                }
                ois.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearForm() {
        staffComboBox.setValue(null);
        moduleComboBox.setValue(null);
        dueDatePicker.setValue(null);
    }

    private void updateTotalTrainings() {
        int total = trainingTableView.getItems().size();
        totalTrainingsLabel.setText("Total: " + total);
    }
}

