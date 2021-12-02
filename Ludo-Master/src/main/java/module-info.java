module com.jaideepjatin.ludomaster {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jaideepjatin.ludomaster to javafx.fxml;
    exports com.jaideepjatin.ludomaster;
}