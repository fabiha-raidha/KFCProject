package class_controller.Ahnaf_2320401.Quality;

import class_controller.AppendableObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class qualityIncidentsController {
    @javafx.fxml.FXML
    private TableView<qualityIncidentClass> incidentTableView;
    @javafx.fxml.FXML
    private TableColumn<qualityIncidentClass, String> storeTableColumn;
    @javafx.fxml.FXML
    private TableColumn<qualityIncidentClass, String> productTableColumn;
    @javafx.fxml.FXML
    private TableColumn<qualityIncidentClass, String> lotNumberTableColumn;
    @javafx.fxml.FXML
    private TableColumn<qualityIncidentClass, String> typeTableColumn;
    @javafx.fxml.FXML
    private TableColumn<qualityIncidentClass, String> descriptionTableColumn;
    @javafx.fxml.FXML
    private TextField storeTextField;
    @javafx.fxml.FXML
    private TextField productTextField;
    @javafx.fxml.FXML
    private TextField lotNumberTextField;
    @javafx.fxml.FXML
    private ComboBox<String> typeComboBox;
    @javafx.fxml.FXML
    private TextArea descriptionTextArea;
    @javafx.fxml.FXML
    private Label totalIncidentsLabel;
    @javafx.fxml.FXML
    private Label successLabel;
    @javafx.fxml.FXML
    private Label errorLabel;

    @javafx.fxml.FXML
    public void initialize() {
        storeTableColumn.setCellValueFactory(new PropertyValueFactory<>("store"));
        productTableColumn.setCellValueFactory(new PropertyValueFactory<>("product"));
        lotNumberTableColumn.setCellValueFactory(new PropertyValueFactory<>("lotNumber"));
        typeTableColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        descriptionTableColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        typeComboBox.getItems().addAll("Contamination", "Spoilage", "Foreign Object", "Temperature Issue", "Other");

        loadIncidentsFromFile();
        updateTotalIncidents();
    }

    @javafx.fxml.FXML
    public void saveIncidentONA(ActionEvent actionEvent) {
        successLabel.setText("");
        errorLabel.setText("");

        if (storeTextField.getText().isEmpty() || productTextField.getText().isEmpty() || lotNumberTextField.getText().isEmpty() || typeComboBox.getValue() == null || descriptionTextArea.getText().isEmpty()) {errorLabel.setText("Please fill in all fields.");
            return;
        }

        String store = storeTextField.getText();
        String product = productTextField.getText();
        String lotNumber = lotNumberTextField.getText();
        String type = typeComboBox.getValue();
        String description = descriptionTextArea.getText();

        qualityIncidentClass incident = new qualityIncidentClass(store, product, lotNumber, type, description);

        incidentTableView.getItems().add(incident);
        saveIncidentToFile(incident);
        successLabel.setText("Incident saved successfully.");
        clearForm();
        updateTotalIncidents();
    }

    private void saveIncidentToFile(qualityIncidentClass incident) {
        try {
            File f = new File("data/QualityIncidents.bin");

            if (f.exists()) {
                FileOutputStream fos = new FileOutputStream(f, true);
                ObjectOutputStream oos = new AppendableObjectOutputStream(fos);
                oos.writeObject(incident);
                oos.close();
            } else {
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(incident);
                oos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorLabel.setText("Error saving incident.");
        }
    }

    private void loadIncidentsFromFile() {
        incidentTableView.getItems().clear();

        try {
            File f = new File("data/QualityIncidents.bin");
            if (!f.exists()) {
                return;
            }

            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    qualityIncidentClass incident = (qualityIncidentClass) ois.readObject();
                    incidentTableView.getItems().add(incident);
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
        storeTextField.clear();
        productTextField.clear();
        lotNumberTextField.clear();
        typeComboBox.setValue(null);
        descriptionTextArea.clear();
    }

    private void updateTotalIncidents() {
        int total = incidentTableView.getItems().size();
        totalIncidentsLabel.setText("Total Incidents: " + total);
    }
}
