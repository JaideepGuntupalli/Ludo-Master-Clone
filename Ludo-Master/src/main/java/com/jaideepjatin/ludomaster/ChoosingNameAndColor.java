package com.jaideepjatin.ludomaster;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ChoosingNameAndColor {
    static void display() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MenuController.class.getResource("choosing-name-and-color.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),320, 240);
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL); // Pauses the before window
        stage.setTitle("Choose Color And Name");
        stage.setWidth(275);
        stage.setHeight(512);

        stage.setScene(scene);
        stage.show();
    }

}
