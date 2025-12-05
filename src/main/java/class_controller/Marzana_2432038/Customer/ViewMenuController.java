package class_controller.Marzana_2432038.Customer;

import class_controller.AppendableObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class ViewMenuController
{
    @javafx.fxml.FXML
    private TableColumn<MenuItem, Float> priceTC;
    @javafx.fxml.FXML
    private TextField nameTF;
    @javafx.fxml.FXML
    private TextField priceTF;
    @javafx.fxml.FXML
    private TableColumn<MenuItem, String> nameTC;
    @javafx.fxml.FXML
    private Label msgL;
    @javafx.fxml.FXML
    private TableColumn<MenuItem, String> descTC;
    @javafx.fxml.FXML
    private ComboBox<String> categoryCB;
    @javafx.fxml.FXML
    private TableColumn<MenuItem, String> categoryTC;
    @javafx.fxml.FXML
    private TableView<MenuItem> menuTableView;

    @javafx.fxml.FXML
    public void initialize() {

        nameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryTC.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceTC.setCellValueFactory(new PropertyValueFactory<>("price"));
        descTC.setCellValueFactory(new PropertyValueFactory<>("description"));

        categoryCB.getItems().addAll("Burgers", "Drinks", "Meals", "Desserts", "Deals");

        loadMenuFromFile();
    }

    @javafx.fxml.FXML
    public void onFilterOA(ActionEvent actionEvent) {

        menuTableView.getItems().clear();

        String name = (nameTF.getText() == null) ? "" : nameTF.getText().trim();
        String category = (categoryCB.getValue() == null) ? "" : categoryCB.getValue().trim();
        String priceText = (priceTF.getText() == null) ? "" : priceTF.getText().trim();

        boolean willAdd = !name.isEmpty() || !category.isEmpty() || !priceText.isEmpty();
        if (willAdd) {
            float price = 0f;
            if (!priceText.isEmpty()) {
                try {
                    price = Float.parseFloat(priceText);
                } catch (NumberFormatException e) {
                    msgL.setText("Price must be a valid number.");

                    loadMenuFromFile();
                    return;
                }
            }

            try {
                File f = new File("data/MenuItems.bin");

                if (f.exists()){
                    FileOutputStream fos = new FileOutputStream(f, true);
                    ObjectOutputStream oos = new AppendableObjectOutputStream(fos);
                    MenuItem m = new MenuItem(name, category, price, "");
                    oos.writeObject(m);
                    oos.close();
                } else {
                    FileOutputStream fos = new FileOutputStream(f);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    MenuItem m = new MenuItem(name, category, price, "");
                    oos.writeObject(m);
                    oos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                msgL.setText("Error saving menu item.");

            }
        }

        try {
            FileInputStream fis = new FileInputStream("data/MenuItems.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                try {
                    Object obj = ois.readObject();
                    if (obj instanceof MenuItem) {
                        menuTableView.getItems().add((MenuItem) obj);
                    }
                } catch (EOFException e1) {
                    break;
                }
            }
            ois.close();
            msgL.setText(willAdd ? "Menu item added and list reloaded." : "Menu loaded.");
        } catch (FileNotFoundException fnf) {
            msgL.setText("No menu file found.");
        } catch (Exception e) {
            e.printStackTrace();
            msgL.setText("Error loading menu.");
        }
    }

    private void loadMenuFromFile() {
        menuTableView.getItems().clear();
        try {
            FileInputStream fis = new FileInputStream("data/MenuItems.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                try {
                    Object obj = ois.readObject();
                    if (obj instanceof MenuItem) {
                        menuTableView.getItems().add((MenuItem) obj);
                    }
                } catch (EOFException e1) {
                    break;
                }
            }
            ois.close();
            msgL.setText("Menu loaded.");
        } catch (FileNotFoundException fnf) {
            msgL.setText("No menu file found.");
        } catch (Exception e) {
            e.printStackTrace();
            msgL.setText("Error loading menu.");
        }
    }
}
