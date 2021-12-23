package com.jaideepjatin.ludomaster;

public enum StateOfPiece {
    ACTIVE, //(When on board and not player's turn)
    REACHED_HOME, //(When the piece reaches center)
    INACTIVE, //(When at starting base but not your turn or when dice isn't rolled 6 when your turn),
    SAFE; //(When at safe passage)
}
