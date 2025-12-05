package class_controller.Ahnaf_2320401.Quality;

import class_controller.AppendableObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;

public class storeAuditsController {

    @javafx.fxml.FXML
    private TableView<storeAuditsClass> auditsTableView;
    @javafx.fxml.FXML
    private TableColumn<storeAuditsClass, String> storeTableColumn;
    @javafx.fxml.FXML
    private TableColumn<storeAuditsClass, String> auditorTableColumn;
    @javafx.fxml.FXML
    private TableColumn<storeAuditsClass, LocalDate> dateTableColumn;
    @javafx.fxml.FXML
    private TableColumn<storeAuditsClass, String> checklistVersionTableColumn;
    @javafx.fxml.FXML
    private TableColumn<storeAuditsClass, String> statusTableColumn;
    @javafx.fxml.FXML
    private ComboBox<String> storeComboBox;
    @javafx.fxml.FXML
    private ComboBox<String> auditorComboBox;
    @javafx.fxml.FXML
    private DatePicker auditDatePicker;
    @javafx.fxml.FXML
    private TextField checklistVersionTextField;
    @javafx.fxml.FXML
    private Label totalAuditsLabel;
    @javafx.fxml.FXML
    private Label successLabel;
    @javafx.fxml.FXML
    private Label errorLabel;

    @javafx.fxml.FXML
    public void initialize() {
        storeTableColumn.setCellValueFactory(new PropertyValueFactory<>("store"));
        auditorTableColumn.setCellValueFactory(new PropertyValueFactory<>("auditor"));
        dateTableColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        checklistVersionTableColumn.setCellValueFactory(new PropertyValueFactory<>("checklistVersion"));
        statusTableColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        storeComboBox.getItems().addAll("Store A", "Store B", "Store C", "Store D");
        auditorComboBox.getItems().addAll("Auditor 1", "Auditor 2", "Auditor 3");

        loadAuditsFromFile();
        updateTotalAudits();
    }

    @javafx.fxml.FXML
    public void scheduleAuditONA(ActionEvent actionEvent) {
        successLabel.setText("");
        errorLabel.setText("");

        if (storeComboBox.getValue() == null || auditorComboBox.getValue() == null || auditDatePicker.getValue() == null ||
            checklistVersionTextField.getText().isEmpty()) {
            errorLabel.setText("Please fill in all fields.");
            return;
        }

        String store = storeComboBox.getValue();
        String auditor = auditorComboBox.getValue();
        LocalDate date = auditDatePicker.getValue();
        String checklistVersion = checklistVersionTextField.getText();

        if (date.isBefore(LocalDate.now())) {
            errorLabel.setText("Audit date cannot be in the past.");
            return;
        }

        storeAuditsClass audit = new storeAuditsClass(store, auditor, date, checklistVersion, "Scheduled");

        auditsTableView.getItems().add(audit);
        saveAuditToFile(audit);
        successLabel.setText("Audit scheduled successfully.");
        clearForm();
        updateTotalAudits();
    }

    private void saveAuditToFile(storeAuditsClass audit) {
        try {
            File f = new File("data/StoreAudits.bin");

            if (f.exists()) {
                FileOutputStream fos = new FileOutputStream(f, true);
                ObjectOutputStream oos = new AppendableObjectOutputStream(fos);
                oos.writeObject(audit);
                oos.close();
            } else {
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(audit);
                oos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorLabel.setText("Error saving audit to file.");
        }
    }

    private void loadAuditsFromFile() {
        try {
            File f = new File("data/StoreAudits.bin");

            if (f.exists()) {
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);

                while (true) {
                    try {
                        storeAuditsClass audit = (storeAuditsClass) ois.readObject();
                        auditsTableView.getItems().add(audit);
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
        storeComboBox.setValue(null);
        auditorComboBox.setValue(null);
        auditDatePicker.setValue(null);
        checklistVersionTextField.clear();
    }

    private void updateTotalAudits() {
        int total = auditsTableView.getItems().size();
        totalAuditsLabel.setText("Total Audits: " + total);
    }
}
