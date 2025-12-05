package class_controller.Marzana_2432038.StoreManager;

import class_controller.AppendableObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;

public class AddStoreExpenseController
{
    @javafx.fxml.FXML
    private TableColumn<Expense, Float> expAmtTC;
    @javafx.fxml.FXML
    private TextField expTypeTF;
    @javafx.fxml.FXML
    private TextField expNoteTF;
    @javafx.fxml.FXML
    private TableColumn<Expense, LocalDate> expDateTC;
    @javafx.fxml.FXML
    private DatePicker expDateDP;
    @javafx.fxml.FXML
    private TableColumn<Expense, String> expTypeTC;
    @javafx.fxml.FXML
    private TableColumn<Expense, String> expNoteTC;
    @javafx.fxml.FXML
    private TextField expAmountTF;
    @javafx.fxml.FXML
    private TableView<Expense> expenseTV;
    @javafx.fxml.FXML
    private Label expenseMsgL;
    @javafx.fxml.FXML
    private Label messageLabel;

    @javafx.fxml.FXML
    public void initialize() {

        expAmtTC.setCellValueFactory(new PropertyValueFactory<>("amount"));
        expTypeTC.setCellValueFactory(new PropertyValueFactory<>("type"));
        expDateTC.setCellValueFactory(new PropertyValueFactory<>("date"));
        expNoteTC.setCellValueFactory(new PropertyValueFactory<>("note"));

    }


    @javafx.fxml.FXML
    public void onAddExpenseOA(ActionEvent actionEvent) {

        expenseTV.getItems().clear();

        try {
            File f = new File ("data/Expense.bin");

            if (f.exists()){
                FileOutputStream fos = new FileOutputStream(f, true);
                ObjectOutputStream oos = new AppendableObjectOutputStream(fos);
                Expense e = new Expense(expTypeTF.getText(), expDateDP.getValue(), expNoteTF.getText(), Float.parseFloat(expAmountTF.getText()));
                oos.writeObject(e);
                oos.close();
            }

            else {
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                Expense e = new Expense(expTypeTF.getText(), expDateDP.getValue(), expNoteTF.getText(), Float.parseFloat(expAmountTF.getText()));
                oos.writeObject(e);
                oos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fis = new FileInputStream("data/Expense.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                try {
                    Expense e;
                    e = (Expense) ois.readObject();
                    expenseTV.getItems().add(e);
                    messageLabel.setText("Expense Added Successfully");
                } catch (EOFException e1) {
                    break;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
