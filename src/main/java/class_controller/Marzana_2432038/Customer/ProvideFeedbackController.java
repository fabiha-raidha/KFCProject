package class_controller.Marzana_2432038.Customer;

import class_controller.AppendableObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;

public class ProvideFeedbackController {

    @FXML private TextField orderIdTF;
    @FXML private ComboBox<Integer> ratingCB;
    @FXML private TextField commentTF;
    @FXML private DatePicker feedbackDateDP;
    @FXML private Label messageLabel;

    @FXML private TableView<CustomerFeedback> feedbackTV;
    @FXML private TableColumn<CustomerFeedback, String> orderIdTC;
    @FXML private TableColumn<CustomerFeedback, Integer> ratingTC;
    @FXML private TableColumn<CustomerFeedback, String> commentTC;
    @FXML private TableColumn<CustomerFeedback, LocalDate> dateTC;

    private static final String FEEDBACK_FILE = "data/CustomerFeedback.bin";

    @FXML
    public void initialize() {
        ratingCB.getItems().addAll(1, 2, 3, 4, 5);

        orderIdTC.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        ratingTC.setCellValueFactory(new PropertyValueFactory<>("rating"));
        commentTC.setCellValueFactory(new PropertyValueFactory<>("comment"));
        dateTC.setCellValueFactory(new PropertyValueFactory<>("date"));

        loadFeedbackFromFile();
    }

    @FXML
    public void onSubmitFeedback(ActionEvent actionEvent) {
        String orderId = orderIdTF.getText();
        Integer rating = ratingCB.getValue();
        String comment = commentTF.getText();
        LocalDate date = feedbackDateDP.getValue();

        if (orderId == null || orderId.trim().isEmpty()) {
            messageLabel.setText("Please enter an Order ID.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        if (rating == null) {
            messageLabel.setText("Please select a rating.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        if (comment == null || comment.trim().isEmpty()) {
            messageLabel.setText("Please enter a comment.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        if (date == null) {
            messageLabel.setText("Please select a date.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        CustomerFeedback feedback = new CustomerFeedback(orderId, rating, comment, date);

        saveFeedbackToFile(feedback);

        orderIdTF.clear();
        ratingCB.setValue(null);
        commentTF.clear();
        feedbackDateDP.setValue(null);

        messageLabel.setText("Thank you for your feedback!");
        messageLabel.setStyle("-fx-text-fill: lime;");

        loadFeedbackFromFile();
    }

    private void saveFeedbackToFile(CustomerFeedback feedback) {
        try {
            File f = new File(FEEDBACK_FILE);
            if (f.exists()) {
                FileOutputStream fos = new FileOutputStream(f, true);
                ObjectOutputStream oos = new AppendableObjectOutputStream(fos);
                oos.writeObject(feedback);
                oos.close();
            } else {
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(feedback);
                oos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Error saving feedback.");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }

    private void loadFeedbackFromFile() {
        feedbackTV.getItems().clear();
        File f = new File(FEEDBACK_FILE);
        if (!f.exists()) return;

        try (FileInputStream fis = new FileInputStream(f);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (true) {
                try {
                    CustomerFeedback feedback = (CustomerFeedback) ois.readObject();
                    feedbackTV.getItems().add(feedback);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
