package com.jaideepjatin.ludomaster;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ChoosingNameAndColorController {
    @FXML
    public Button backButton;
    public TextField p1Name;
    public TextField p2Name;
    public ToggleGroup Color;
    public Button playButton;

    @FXML
    protected void close(ActionEvent e){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void play(ActionEvent e){
        Stage stage = (Stage) playButton.getScene().getWindow();
        System.out.println(p1Name.getText());
        System.out.println(p2Name.getText());
        System.out.println(Color.getSelectedToggle());
        stage.close();
    }
}
