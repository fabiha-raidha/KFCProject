package class_controller.Marzana_2432038.StoreManager;

import class_controller.AppendableObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class StaffEvaluationController
{
    @FXML
    private Label messageLabel;
    @FXML
    private TableColumn<StaffEvaluation, Double> attendanceTC;
    @FXML
    private TableColumn<StaffEvaluation, Double> pastRatingTC;
    @FXML
    private TextField ratingTF;
    @FXML
    private TableColumn<StaffEvaluation, String> staffNameTC;
    @FXML
    private TableColumn<StaffEvaluation, Integer> ratingTC;
    @FXML
    private ComboBox<String> staffNameCB;
    @FXML
    private TextField remarkTF;
    @FXML
    private TableColumn<StaffEvaluation, String> remarkTC;
    @FXML
    private TableView<StaffEvaluation> evaluationTableView;

    @FXML
    public void initialize() {

        staffNameTC.setCellValueFactory(new PropertyValueFactory<>("staffName"));
        attendanceTC.setCellValueFactory(new PropertyValueFactory<>("attendancePercentage"));
        pastRatingTC.setCellValueFactory(new PropertyValueFactory<>("pastRating"));
        ratingTC.setCellValueFactory(new PropertyValueFactory<>("rating"));
        remarkTC.setCellValueFactory(new PropertyValueFactory<>("remark"));

        staffNameCB.setEditable(true);

        loadEvaluationsFromFile();
    }

    @Deprecated
    public void handleSaveEvaluationAction(ActionEvent actionEvent) {
        handleEvaluateStaffOA(actionEvent);
    }

    @FXML
    public void handleEvaluateStaffOA(ActionEvent actionEvent) {

        messageLabel.setText("");

        String staffName = null;
        if (staffNameCB.getValue() != null && !staffNameCB.getValue().isEmpty()) {
            staffName = staffNameCB.getValue();
        } else if (staffNameCB.getEditor() != null &&
                !staffNameCB.getEditor().getText().isEmpty()) {
            staffName = staffNameCB.getEditor().getText();
        }

        if (staffName == null || staffName.isEmpty() || ratingTF.getText().isEmpty()) {
            messageLabel.setText("Please enter staff name and rating.");
            return;
        }

        int rating;
        try {
            rating = Integer.parseInt(ratingTF.getText());
        } catch (NumberFormatException e) {
            messageLabel.setText("Rating must be a number between 1 and 5.");
            return;
        }

        if (rating < 1 || rating > 5) {
            messageLabel.setText("Rating must be between 1 and 5.");
            return;
        }

        String remark = remarkTF.getText();

        double attendance = 0.0;
        double pastRating = getLastRatingForStaff(staffName);

        StaffEvaluation evaluation = new StaffEvaluation(
                staffName,
                attendance,
                pastRating,
                rating,
                remark
        );

        evaluationTableView.getItems().add(evaluation);
        saveEvaluationToFile(evaluation);

        messageLabel.setText("Evaluation saved.");
        clearForm();
    }

    private double getLastRatingForStaff(String staffName) {
        double lastRating = 0.0;

        File f = new File("data/StaffEvaluations.bin");
        if (!f.exists()) {
            return 0.0;
        }

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(f))) {

            while (true) {
                try {
                    Object obj = ois.readObject();
                    if (obj instanceof StaffEvaluation) {
                        StaffEvaluation ev = (StaffEvaluation) obj;
                        if (ev.getStaffName().equals(staffName)) {
                            lastRating = ev.getRating();
                        }
                    }
                } catch (EOFException eof) {
                    break;
                }
            }
        } catch (Exception ignored) { }

        return lastRating;
    }

    private void saveEvaluationToFile(StaffEvaluation evaluation) {
        try {
            File f = new File("data/StaffEvaluations.bin");

            if (f.exists()) {
                FileOutputStream fos = new FileOutputStream(f, true);
                ObjectOutputStream oos = new AppendableObjectOutputStream(fos);
                oos.writeObject(evaluation);
                oos.close();
            } else {
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(evaluation);
                oos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Error saving evaluation.");
        }
    }

    private void loadEvaluationsFromFile() {
        evaluationTableView.getItems().clear();

        File f = new File("data/StaffEvaluations.bin");
        if (!f.exists()) {
            return;
        }

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(f))) {

            while (true) {
                try {
                    Object obj = ois.readObject();
                    if (obj instanceof StaffEvaluation) {
                        evaluationTableView.getItems().add((StaffEvaluation) obj);
                    }
                } catch (EOFException eof) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Error loading evaluations.");
        }
    }

    private void clearForm() {
        ratingTF.clear();
        remarkTF.clear();
        staffNameCB.setValue(null);
        if (staffNameCB.getEditor() != null) {
            staffNameCB.getEditor().clear();
        }
    }
}
