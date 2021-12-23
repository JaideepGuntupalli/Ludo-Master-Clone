package com.jaideepjatin.ludomaster;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class ChoosingNameAndColorController {
    @FXML
    public Button backButton;
    public TextField p1NameBG;
    public TextField p2NameBG;
    public TextField p1NameRY;
    public TextField p2NameRY;
    public Button playButton;
    public CheckBox ColorBG;
    public CheckBox ColorRY;
    public ImageView redYellow;
    public ImageView blueGreen;
    public CheckBox magicProps;
    public ImageView magicPropsImg;

    @FXML
    protected void close(ActionEvent e) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        Main.closingChoosingColor();
    }

    @FXML
    protected void play(ActionEvent e) throws IOException {
        Stage stage = (Stage) playButton.getScene().getWindow();
        if(ColorBG.isSelected()){
            Main.response.setBlueAndGreen(true);
            Main.response.setP1Name(p1NameBG.getText());
            Main.response.setP2Name(p2NameBG.getText());
        }
        else if(ColorRY.isSelected()){
            Main.response.setBlueAndGreen(false);
            Main.response.setP1Name(p1NameRY.getText());
            Main.response.setP2Name(p2NameRY.getText());
        }
        Main.response.setMagicProps(magicProps.isSelected());
        Main.closingChoosingColor();
        Main.startingLudo();
        stage.close();
    }

    @FXML
    protected void magicProps(ActionEvent e){
        magicPropsImg.setVisible(!magicProps.isSelected());
    }

    @FXML
    protected void blueAndGreen(ActionEvent e){
        p1NameBG.setVisible(true);
        p2NameBG.setVisible(true);
        p1NameRY.setVisible(false);
        p1NameRY.clear();
        p2NameRY.setVisible(false);
        p2NameRY.clear();
        p1NameBG.setEditable(true);
        p2NameBG.setEditable(true);
        ColorRY.setSelected(false);
        blueGreen.setVisible(true);
    }

    @FXML
    protected void redAndYellow(ActionEvent e){
        p1NameRY.setVisible(true);
        p2NameRY.setVisible(true);
        p1NameBG.setVisible(false);
        p1NameBG.clear();
        p2NameBG.setVisible(false);
        p2NameBG.clear();
        p1NameRY.setEditable(true);
        p2NameRY.setEditable(true);
        ColorBG.setSelected(false);
        blueGreen.setVisible(false);
    }
}
