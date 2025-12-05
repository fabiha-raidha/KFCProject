package class_controller.Ahnaf_2320401;

import class_controller.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class qualityDashboard {

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
    public void goToQualityIncidentsONA(ActionEvent actionEvent) throws IOException {
        sceneSwitch(actionEvent, "Ahnaf_2320401/QualityFXML/G1qualityIncidents.fxml", "Quality Incidents");
    }

    @javafx.fxml.FXML
    public void goToStoreAuditsONA(ActionEvent actionEvent) throws IOException {
        sceneSwitch(actionEvent, "Ahnaf_2320401/QualityFXML/G2storeAudits.fxml", "Store Audits");
    }

    @javafx.fxml.FXML
    public void goToTrainingComplianceONA(ActionEvent actionEvent) throws IOException {
        sceneSwitch(actionEvent, "Ahnaf_2320401/QualityFXML/G3trainingCompliance.fxml", "Training Compliance");
    }
}
