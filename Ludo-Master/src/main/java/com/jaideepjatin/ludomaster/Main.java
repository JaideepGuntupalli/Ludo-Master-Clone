package com.jaideepjatin.ludomaster;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    public static ChoosingNameAndColor response = new ChoosingNameAndColor();
    private static Stage stage;
    private static Scene scene;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Main.scene = scene;
        Main.stage = stage;

//        Image logo = new Image("@../../../assets/ludo-master-logo.jpg");
//        stage.getIcons().add(logo);
        stage.setTitle("Hello!");
        stage.setHeight(812);
        stage.setWidth(375);
        stage.setMaxHeight(808);
        stage.setMaxWidth(375);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void choosingColor() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view-choosing.fxml"));
        Scene choosingNameNColor = new Scene(fxmlLoader.load(), 320, 240);
        Main.stage.setScene(choosingNameNColor);
    }

    public static void closingChoosingColor(){
        Main.stage.setScene(scene);
    }

    public static void startingLudo() throws IOException {
        FXMLLoader fxmlLoader;

        if(response.isBlueAndGreen()){
            fxmlLoader = new FXMLLoader(Main.class.getResource("ludo-game-bluegreen.fxml"));
        }
        else {
            fxmlLoader = new FXMLLoader(Main.class.getResource("ludo-game-redyellow.fxml"));
        }

        Scene ludoGame = new Scene(fxmlLoader.load(), 320, 240);
        Main.stage.setScene(ludoGame);
    }

//    public static void responseUpdate(){
//        LudoGameController.main(response.getP1Name(),response.getP2Name(),response.isBlueAndGreen());
//    }
}