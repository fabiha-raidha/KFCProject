package class_controller.Marzana_2432038.StoreManager;

import class_controller.AppendableObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;

public class CustomerFeedbackController
{
    @javafx.fxml.FXML
    private TableView<CustomerFeedback> feedbackTableView;
    @javafx.fxml.FXML
    private Label messageLabel;
    @javafx.fxml.FXML
    private DatePicker dateDP;
    @javafx.fxml.FXML
    private TableColumn<CustomerFeedback, String> productTC;
    @javafx.fxml.FXML
    private TextField ratingTF;
    @javafx.fxml.FXML
    private TextField commentTF;
    @javafx.fxml.FXML
    private TableColumn<CustomerFeedback, Integer> ratingTC;
    @javafx.fxml.FXML
    private TableColumn<CustomerFeedback, LocalDate> dateTC;
    @javafx.fxml.FXML
    private TableColumn<CustomerFeedback, String> customerIdTC;
    @javafx.fxml.FXML
    private TableColumn<CustomerFeedback, String> commentTC;
    @javafx.fxml.FXML
    private TextField customerIdTF;
    @javafx.fxml.FXML
    private TextField productTF;

    @javafx.fxml.FXML
    public void initialize() {

        dateTC.setCellValueFactory(new PropertyValueFactory<>("date"));
        customerIdTC.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        productTC.setCellValueFactory(new PropertyValueFactory<>("product"));
        ratingTC.setCellValueFactory(new PropertyValueFactory<>("rating"));
        commentTC.setCellValueFactory(new PropertyValueFactory<>("comment"));

        loadFeedbackFromFile();
    }

    @Deprecated
    public void handleFilterAction(ActionEvent actionEvent) {
        messageLabel.setText("Filtering is disabled.");
    }

    @Deprecated
    public void handleClearFilterAction(ActionEvent actionEvent) {
        feedbackTableView.getItems().clear();
        loadFeedbackFromFile();
        messageLabel.setText("Filters cleared.");
    }

    @javafx.fxml.FXML
    public void handleAddFeedbackOA(ActionEvent actionEvent) {

        messageLabel.setText("");

        if (dateDP.getValue() == null ||
                customerIdTF.getText().isEmpty() ||
                productTF.getText().isEmpty() ||
                ratingTF.getText().isEmpty() ||
                commentTF.getText().isEmpty()) {

            messageLabel.setText("Please fill all fields.");
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

        CustomerFeedback fb = new CustomerFeedback(
                dateDP.getValue(),
                customerIdTF.getText(),
                productTF.getText(),
                rating,
                commentTF.getText()
        );

        feedbackTableView.getItems().add(fb);

        saveFeedbackToFile(fb);

        messageLabel.setText("Feedback added successfully.");
        clearForm();
    }

    private void saveFeedbackToFile(CustomerFeedback fb) {
        try {
            File f = new File("data/CustomerFeedback.bin");

            if (f.exists()) {
                FileOutputStream fos = new FileOutputStream(f, true);
                ObjectOutputStream oos = new AppendableObjectOutputStream(fos);
                oos.writeObject(fb);
                oos.close();
            } else {
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(fb);
                oos.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Error saving feedback.");
        }
    }

    private void loadFeedbackFromFile() {

        feedbackTableView.getItems().clear();

        File f = new File("data/CustomerFeedback.bin");
        if (!f.exists()) {
            return;
        }

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(f))) {

            while (true) {
                try {
                    Object obj = ois.readObject();
                    if (obj instanceof CustomerFeedback) {
                        feedbackTableView.getItems().add((CustomerFeedback) obj);
                    }
                } catch (EOFException eof) {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Error loading feedback.");
        }
    }

    private void clearForm() {
        dateDP.setValue(null);
        customerIdTF.clear();
        productTF.clear();
        ratingTF.clear();
        commentTF.clear();
    }
}
