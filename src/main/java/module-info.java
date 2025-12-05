module oop.kfcglobaloperation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; // Often needed



    exports class_controller;

    opens class_controller to javafx.fxml;
    opens class_controller.Fabiha_2211214.CMO to javafx.fxml, javafx.base;
    opens class_controller.Fabiha_2211214.HeadChef to javafx.fxml, javafx.base;
    opens class_controller.Marzana_2432038.StoreManager to javafx.fxml, javafx.base;
    opens class_controller.Marzana_2432038.Customer to javafx.fxml, javafx.base;
    opens class_controller.Ahnaf_2320401 to javafx.fxml, javafx.base;
    opens class_controller.Ahnaf_2320401.Quality to javafx.fxml, javafx.base;
    opens class_controller.Ahnaf_2320401.Supply to javafx.fxml, javafx.base;

    opens class_controller.users to javafx.fxml;
}