package com.jaideepjatin.ludomaster;

import javafx.scene.control.Button;

public class Piece {
/*

- First a generic class which helps to make the screen like I say blue and green toh uske related buttons and all
  should form

- Next a thread for each piece so that when clicked on piece at start, the thread should initiate and when
  clicked on it should move. Thread for just moving the piece

- Piece will have a state attribute where its values can be
    IN_PLAY(When on board and player's turn),
    ACTIVE(When on board and not player's turn),
    REACHED_HOME(When the piece reaches center),
    INACTIVE(When at starting base but not your turn or when dice isn't rolled 6 when your turn),
    READY_TO_PLAY(When at starting base but your turn and when dice is rolled 6)

- ALl the functions and buttons should work this way.

- An array will be maintained for killing people and when it reached the end of the player array it will be shifted
   to new player safe array.
   Main array => len = 52 => this will be loop array in the sense one player starts at index 0 and other will at
                             index 26.
   Player array => len = 51 => piece when turns to Active for the first time it will be placed on index 0
   Player safe array => len = 6 => when it reaches last position it will be turned to REACHED_HOME
   OK so each player will have their own array and a Main array

*/
    private StateOfPiece state;
    private final Colors color;
    private Button btn;
    private int spwnX;
    private int spwnY;
    private int pos;

    Piece(Colors color,Button btn){
        this.state = StateOfPiece.INACTIVE;
        this.color = color;
        this.btn = btn;
        this.spwnX = (int) btn.getLayoutX();
        this.spwnY = (int) btn.getLayoutY();
        this.pos = 0;
    }

    public Colors getColor() {
        return color;
    }

    public StateOfPiece getState() {
        return state;
    }

    public void setState(StateOfPiece state) {
        this.state = state;
    }

    public void setBtnDisable() {
        btn.setDisable(true);
    }

    public void setBtnAble() {
        btn.setDisable(false);
    }

    public Button getBtn() {
        return btn;
    }

    public void kill(){
        state = StateOfPiece.INACTIVE;
        btn.setLayoutX(spwnX);
        btn.setLayoutY(spwnY);
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }
}
