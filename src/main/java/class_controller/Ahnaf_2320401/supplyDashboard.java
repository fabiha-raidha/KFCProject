package class_controller.Ahnaf_2320401;

import class_controller.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class supplyDashboard {

    @javafx.fxml.FXML
    public void initialize() {
    }

    public void sceneSwitch(ActionEvent actionEvent, String fxmlFile, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle(title);
        stage.show();
    }

    @javafx.fxml.FXML
    public void goToSupplierONA(ActionEvent actionEvent) throws IOException {
        sceneSwitch(actionEvent, "Ahnaf_2320401/SupplyFXML/G1supplierFXML.fxml", "Supplier Management");
    }

    @javafx.fxml.FXML
    public void goToPurchaseOrderONA(ActionEvent actionEvent) throws IOException {
        sceneSwitch(actionEvent, "Ahnaf_2320401/SupplyFXML/G2purchaseOrder.fxml", "Purchase Order");
    }

    @javafx.fxml.FXML
    public void goToInventoryOverviewONA(ActionEvent actionEvent) throws IOException {
        sceneSwitch(actionEvent, "Ahnaf_2320401/SupplyFXML/G3InventoryOverview.fxml", "Inventory Overview");
    }

    @javafx.fxml.FXML
    public void goToStockTransfersONA(ActionEvent actionEvent) throws IOException {
        sceneSwitch(actionEvent, "Ahnaf_2320401/SupplyFXML/G4StockTransfers.fxml", "Stock Transfers");
    }

    @javafx.fxml.FXML
    public void goToSupplierReportsONA(ActionEvent actionEvent) throws IOException {
        sceneSwitch(actionEvent, "Ahnaf_2320401/SupplyFXML/G5supplierReports.fxml", "Supplier Reports");
    }

    @javafx.fxml.FXML
    public void goToReceiveShipmentONA(ActionEvent actionEvent) throws IOException {
        sceneSwitch(actionEvent, "Ahnaf_2320401/SupplyFXML/G6RecieveShipment.fxml", "Receive Shipment");
    }

    @javafx.fxml.FXML
    public void goToItemCatalogONA(ActionEvent actionEvent) throws IOException {
        sceneSwitch(actionEvent, "Ahnaf_2320401/SupplyFXML/G7itemCatalog.fxml", "Item Catalog");
    }

    @javafx.fxml.FXML
    public void goToLowStockAlertONA(ActionEvent actionEvent) throws IOException {
        sceneSwitch(actionEvent, "Ahnaf_2320401/SupplyFXML/G8lowStockAlert.fxml", "Low Stock Alerts");
    }
}
