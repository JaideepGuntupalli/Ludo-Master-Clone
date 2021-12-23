package com.jaideepjatin.ludomaster;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ChoosingNameAndColor {
    static void display() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("choosing-name-and-color.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),320, 240);
        Stage stage = new Stage();
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();


        stage.initModality(Modality.APPLICATION_MODAL); // Pauses the before window
        stage.setTitle("Choose Color And Name");
        stage.setWidth(313);
        stage.setHeight(472);
        stage.setMaxWidth(313);
        stage.setMaxHeight(472);
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    private boolean blueAndGreen;
    private boolean magicProps;
    private String p1Name;
    private String p2Name;

    public boolean isBlueAndGreen() {
        return blueAndGreen;
    }

    public boolean isMagicProps() {
        return magicProps;
    }

    public String getP1Name() {
        return p1Name;
    }

    public String getP2Name() {
        return p2Name;
    }

    public void setBlueAndGreen(boolean blueAndGreen) {
        this.blueAndGreen = blueAndGreen;
    }

    public void setMagicProps(boolean magicProps) {
        this.magicProps = magicProps;
    }

    public void setP1Name(String p1Name) {
        this.p1Name = p1Name;
    }

    public void setP2Name(String p2Name) {
        this.p2Name = p2Name;
    }

}
