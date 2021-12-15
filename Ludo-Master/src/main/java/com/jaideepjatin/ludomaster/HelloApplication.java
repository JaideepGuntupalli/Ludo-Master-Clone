package com.jaideepjatin.ludomaster;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        Image logo = new Image("ludo-master-logo.jpg");
        stage.getIcons().add(logo);
        stage.setTitle("Hello!");
        stage.setHeight(812);
        stage.setWidth(375);
        stage.setMaxHeight(812);
        stage.setMaxWidth(375);
        stage.setScene(scene);
        stage.show();
    }
}