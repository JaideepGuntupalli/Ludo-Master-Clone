package com.jaideepjatin.ludomaster;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HelloController {

    @FXML
    public Button localMultiplayer;

    @FXML
    protected void print(ActionEvent e) throws IOException {
        ChoosingNameAndColor.display();
    }
}