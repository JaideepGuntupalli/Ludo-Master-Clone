package com.jaideepjatin.ludomaster;

import javafx.scene.control.Button;

public class Team {
    private final Colors color;
    public Piece[] pieces = new Piece[4];
    public Button[] piecesBtns = new Button[4];

    Team(Colors color, Button[] piecesBtns){
        this.color = color;
        this.piecesBtns = piecesBtns;
        for (int i = 0; i < 4; i++) {
            pieces[i] = new Piece(color,piecesBtns[i]);
        }
    }

    public Colors getColor() {
        return color;
    }

}
