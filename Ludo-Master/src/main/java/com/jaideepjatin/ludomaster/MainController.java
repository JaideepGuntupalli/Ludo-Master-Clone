package com.jaideepjatin.ludomaster;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class MainController {

    @FXML
    public Button localMultiplayer;
    public static Rectangle black;

    @FXML
    protected void twoPlayerForm(ActionEvent e) throws IOException {
        Main.choosingColor();
        ChoosingNameAndColor.display();
    }
}