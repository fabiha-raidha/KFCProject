package class_controller.Marzana_2432038.StoreManager;

import class_controller.AppendableObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class MaintenanceRequestController
{
    @javafx.fxml.FXML
    private ComboBox<String> urgencyCB;
    @javafx.fxml.FXML
    private TableView<MaintenanceRequest> requestTableView;
    @javafx.fxml.FXML
    private TextField issueTypeTF;
    @javafx.fxml.FXML
    private TextField descriptionTF;
    @javafx.fxml.FXML
    private TableColumn<MaintenanceRequest, String> urgencyTC;
    @javafx.fxml.FXML
    private TableColumn<MaintenanceRequest, String> issueTC;
    @javafx.fxml.FXML
    private Label messageLabel;
    @javafx.fxml.FXML
    private TableColumn<MaintenanceRequest, String> descriptionTC;

    @javafx.fxml.FXML
    public void initialize() {

        issueTC.setCellValueFactory(new PropertyValueFactory<>("issueType"));
        descriptionTC.setCellValueFactory(new PropertyValueFactory<>("description"));
        urgencyTC.setCellValueFactory(new PropertyValueFactory<>("urgency"));

        urgencyCB.getItems().addAll("Low", "Medium", "High");

        loadRequestsFromFile();
    }

    @Deprecated
    public void onNewRequestOA(ActionEvent actionEvent) {
        clearForm();
        messageLabel.setText("Ready for new request.");
    }

    @Deprecated
    public void onRefreshRequestsOA(ActionEvent actionEvent) {
        loadRequestsFromFile();
        messageLabel.setText("Requests reloaded.");
    }

    @javafx.fxml.FXML
    public void handleSaveRequestOA(ActionEvent actionEvent) {

        messageLabel.setText("");

        if (issueTypeTF.getText().isEmpty()
                || descriptionTF.getText().isEmpty()
                || urgencyCB.getValue() == null) {

            messageLabel.setText("Please fill issue type, description and urgency.");
            return;
        }

        MaintenanceRequest request = new MaintenanceRequest(
                issueTypeTF.getText(),
                descriptionTF.getText(),
                urgencyCB.getValue()
        );

        requestTableView.getItems().add(request);
        saveRequestToFile(request);

        messageLabel.setText("Maintenance request saved.");
        clearForm();
    }

    private void saveRequestToFile(MaintenanceRequest request) {
        try {
            File f = new File("data/MaintenanceRequests.bin");

            if (f.exists()) {
                FileOutputStream fos = new FileOutputStream(f, true);
                ObjectOutputStream oos = new AppendableObjectOutputStream(fos);
                oos.writeObject(request);
                oos.close();
            } else {
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(request);
                oos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Error saving request.");
        }
    }

    private void loadRequestsFromFile() {
        requestTableView.getItems().clear();

        File f = new File("data/MaintenanceRequests.bin");
        if (!f.exists()) {
            return;
        }

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(f))) {

            while (true) {
                try {
                    Object obj = ois.readObject();
                    if (obj instanceof MaintenanceRequest) {
                        requestTableView.getItems().add((MaintenanceRequest) obj);
                    }
                } catch (EOFException eof) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Error loading requests.");
        }
    }

    private void clearForm() {
        issueTypeTF.clear();
        descriptionTF.clear();
        urgencyCB.setValue(null);
    }
}
