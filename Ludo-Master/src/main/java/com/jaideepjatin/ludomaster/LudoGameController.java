package com.jaideepjatin.ludomaster;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class LudoGameController {
    public static int[][] coordinates = new int[52][2];
    public static int[][] safeCoordinates = new int[10][2];
    public static int[][] homeCoordinates = new int[10][2];
    public static Piece[] mainLobby = new Piece[52];
    public static Piece[] team1Lobby = new Piece[51];
    public static Piece[] team2Lobby = new Piece[51];
    public static Piece[] team1SafeLobby = new Piece[5];
    public static Piece[] team2SafeLobby = new Piece[5];
    public static Piece[] allPieces = new Piece[8];
    public static int team1HomePieces;
    public static int team2HomePieces;
    public static Team team1;
    public static Team team2;
    private static int diceRoll;

    @FXML
    public Button rollDice1;
    public Button rollDice2;
    public Button team1p1;
    public Button team1p2;
    public Button team1p4;
    public Button team1p3;
    public Button team2p1;
    public Button team2p2;
    public Button team2p4;
    public Button team2p3;
    public Label winnerMsg;
    @FXML
    private ImageView dice1;
    @FXML
    private ImageView dice2;
    @FXML
    private ImageView arrow1;
    @FXML
    private ImageView arrow2;
    @FXML
    private Label p1Name;
    @FXML
    private Label p2Name;

    public void pieceMove(ActionEvent e){
        Button btn = (Button) e.getSource();
        Piece movedPiece = new Piece(Colors.RED,btn);

        for (int i = 0; i < 8; i++) {
            if(allPieces[i].getBtn() == btn){
                movedPiece = allPieces[i];
                break;
            }
        }

        int team2MainIdx;

        if(movedPiece.getState() == StateOfPiece.INACTIVE){
            team2MainIdx = 26;

            if(movedPiece.getColor() == team1.getColor()){
                if (mainLobby[0] != null) {
                    mainLobby[0].kill();
                }
                mainLobby[0] = movedPiece;

                if (team1Lobby[0] != null) {
                    team1Lobby[0].kill();
                }
                team1Lobby[0] = movedPiece;

                btn.setLayoutX(coordinates[0][0]);
                btn.setLayoutY(coordinates[0][1]);
            }
            else if(movedPiece.getColor() == team2.getColor()){
                if (mainLobby[team2MainIdx] != null) {
                    mainLobby[team2MainIdx].kill();
                }
                mainLobby[team2MainIdx] = movedPiece;

                if (team2Lobby[0] != null) {
                    team2Lobby[0].kill();
                }
                team2Lobby[0] = movedPiece;
                movedPiece.setPos(team2MainIdx);

                btn.setLayoutX(coordinates[team2MainIdx][0]);
                btn.setLayoutY(coordinates[team2MainIdx][1]);
            }

            movedPiece.setState(StateOfPiece.ACTIVE);

        }
        else if(movedPiece.getState() == StateOfPiece.ACTIVE){
            System.out.println("Active pies");
            System.out.println(movedPiece.getPos() + diceRoll);
            mainLobby[movedPiece.getPos() + diceRoll] = mainLobby[movedPiece.getPos()];
            mainLobby[movedPiece.getPos()] = null;
            if(movedPiece.getColor() == team1.getColor()){
                if(movedPiece.getPos() + diceRoll < 51) {
                    mainLobby[movedPiece.getPos() + diceRoll] = mainLobby[movedPiece.getPos()];
                    mainLobby[movedPiece.getPos()] = null;
                    team1Lobby[movedPiece.getPos() + diceRoll] = movedPiece;
                    team1Lobby[movedPiece.getPos()] = null;
                    movedPiece.setPos(movedPiece.getPos() + diceRoll);
                }
                else if((movedPiece.getPos() + diceRoll) > 50 && (movedPiece.getPos() + diceRoll) < 56){
                    team1SafeLobby[(movedPiece.getPos() + diceRoll) - 50] = mainLobby[movedPiece.getPos()];
                    mainLobby[movedPiece.getPos()] = null;
                    team1Lobby[movedPiece.getPos()] = null;
                    movedPiece.setPos(50 - (movedPiece.getPos() + diceRoll));
                    movedPiece.setState(StateOfPiece.SAFE);
                }
                else if(movedPiece.getPos() + diceRoll == 56){
                    team1SafeLobby[Math.abs(movedPiece.getPos())] = null;
                    team1HomePieces++;
                    movedPiece.setState(StateOfPiece.REACHED_HOME);
                    if(team1HomePieces == 4){
                        System.out.println( p1Name.getText() + "has won!!");
                        winnerMsg.setVisible(true);
                        winnerMsg.setText(p1Name.getText() + "has won!!");
                    }
                }

                if(movedPiece.getPos() < 51) {
                    btn.setLayoutX(coordinates[movedPiece.getPos()][0]);
                    btn.setLayoutY(coordinates[movedPiece.getPos()][1]);
                }
                else if (movedPiece.getPos() == 56){
                    btn.setLayoutX(homeCoordinates[team1HomePieces - 1][0]);
                    btn.setLayoutY(homeCoordinates[team1HomePieces - 1][1]);
                }
                else{
                    btn.setLayoutX(safeCoordinates[Math.abs(movedPiece.getPos())][0]);
                    btn.setLayoutY(safeCoordinates[Math.abs(movedPiece.getPos())][1]);
                }
            }
            else if(movedPiece.getColor() == team2.getColor()){
                team2MainIdx = movedPiece.getPos() + diceRoll;
                if(team2MainIdx < 52){
                    mainLobby[team2MainIdx] = mainLobby[movedPiece.getPos()];
                    mainLobby[movedPiece.getPos()] = null;
                    team2Lobby[team2MainIdx] = movedPiece;
                    team2Lobby[movedPiece.getPos()] = null;
                    movedPiece.setPos(team2MainIdx);
                }
                else{
                    if(team2MainIdx - 52 < 25){
                        mainLobby[team2MainIdx - 52] = mainLobby[movedPiece.getPos()];
                        mainLobby[movedPiece.getPos()] = null;
                        team2Lobby[team2MainIdx] = movedPiece;
                        team2Lobby[movedPiece.getPos()] = null;
                        movedPiece.setPos(team2MainIdx - 52);
                    }
                    else if(team2MainIdx - 52 < 30){
                        team2SafeLobby[team2MainIdx - 52 - 24] = mainLobby[movedPiece.getPos()];
                        mainLobby[movedPiece.getPos()] = null;
                        team2Lobby[movedPiece.getPos()] = null;
                        movedPiece.setPos(24 - team2MainIdx + 52);
                    }
                    else if(team2MainIdx - 52 == 30){
                        team2SafeLobby[Math.abs(movedPiece.getPos())] = null;
                        team2HomePieces++;
                        movedPiece.setState(StateOfPiece.REACHED_HOME);
                        if(team2HomePieces == 4){
                            System.out.println( p2Name.getText() + "has won!!");
                            winnerMsg.setVisible(true);
                            winnerMsg.setText(p2Name.getText() + "has won!!");
                        }
                    }
                }

                if(movedPiece.getPos() - 52 < 25) {
                    btn.setLayoutX(coordinates[movedPiece.getPos()][0]);
                    btn.setLayoutY(coordinates[movedPiece.getPos()][1]);
                }
                else if(movedPiece.getPos() - 52 < 30){
                    btn.setLayoutX(safeCoordinates[4 + Math.abs(movedPiece.getPos())][0]);
                    btn.setLayoutY(safeCoordinates[4 + Math.abs(movedPiece.getPos())][1]);
                }
                else if(movedPiece.getPos() - 52 == 30){
                    btn.setLayoutX(homeCoordinates[team2HomePieces - 1 + 4][0]);
                    btn.setLayoutY(homeCoordinates[team2HomePieces - 1 + 4][1]);
                }
            }
        }
        System.out.println("Position: " + movedPiece.getPos());


        System.out.println(Arrays.deepToString(mainLobby));
        System.out.println(Arrays.deepToString(team1Lobby));
        System.out.println(Arrays.deepToString(team2Lobby));
        System.out.println("");

        for (int i = 0; i < 4; i++) {
            team1.pieces[i].setBtnDisable();
            System.out.println(team1.pieces[i].getBtn());
        }
        for (int i = 0; i < 4; i++) {
            team2.pieces[i].setBtnDisable();
            System.out.println(team2.pieces[i].getBtn());
        }
    }

    public void main(ActionEvent e) {
        String name1 = Main.response.getP1Name();
        String name2 = Main.response.getP2Name();
        if(name1 != null){
            p1Name.setText(name1);
        }
        if(name2 != null){
            p2Name.setText(name2);
        }
        System.out.println("Names changed");

        Button[] team1Btns = {team1p1, team1p2, team1p3, team1p4};
        Button[] team2Btns = {team2p1, team2p2, team2p3, team2p4};

        if(Main.response.isBlueAndGreen()){
            team1 = new Team(Colors.BLUE,team1Btns);
            team2 = new Team(Colors.GREEN,team2Btns);
            System.out.println("blue and green");
        }
        else{
            team1 = new Team(Colors.RED,team1Btns);
            team2 = new Team(Colors.YELLOW,team2Btns);
            System.out.println("red and yellow");
        }

        System.out.println("Teams formed");
//
        for (int i = 0; i < 8; i++) {
            if(i<4){
                allPieces[i] = team1.pieces[i];
            }
            else{
                allPieces[i] = team2.pieces[i-4];
            }
        }

        System.out.println("Pieces been set");
        int i,j,k,l,m,n,o,p;

        for (i = 0; i < 5; i++) {
            coordinates[i][0] = 155;
            coordinates[i][1] = 522 - (23 * i);
        }

        for(j = i; j < i + 6; j++){
            coordinates[j][0] = 131 - (23 * (j - i));
            coordinates[j][1] = 400;
        }

        coordinates[11][0] = 13;
        coordinates[11][1] = 379;

        for(k = j + 1; k < j + 7; k++){
            coordinates[k][0] = 13 + (23 * (k - j - 1));
            coordinates[k][1] = 356;
        }

        for (l = k; l < k + 6; l++) {
            coordinates[l][0] = 155;
            coordinates[l][1] = 331 - (23 * (l - k));
        }

        coordinates[24][0] = 176;
        coordinates[24][1] = 216;

        for (m = l + 1; m < l + 7; m++) {
            coordinates[m][0] = 201;
            coordinates[m][1] = 216 + (23 * (m - l - 1));
        }

        for (n = m; n < m + 6; n++) {
            coordinates[n][0] = 224 + (23 * (n - m));
            coordinates[n][1] = 356;
        }

        coordinates[37][0] = 341;
        coordinates[37][1] = 380;

        for (o = n + 1; o < n + 7; o++) {
            coordinates[o][0] = 341 - (23 * (o - n - 1));
            coordinates[o][1] = 402;
        }

        for (p = o; p < o + 6; p++) {
            coordinates[p][0] = 200;
            coordinates[p][1] = 426 + (23 * (p - o));
        }

        coordinates[50][0] = 176;
        coordinates[50][1] = 544;

        coordinates[51][0] = 155;
        coordinates[51][1] = 544;

        if(team1.getColor() == Colors.RED){
            System.out.println("array changed");
            int[][] arr = new int[52][2];
            for (int q = 0; q < 52; q++) {
                if(q + 13 < 52){
                    arr[q] = coordinates[13 + q];
                }
                else{
                    arr[q] = coordinates[(q + 13) - 52];
                }
            }
            System.out.println(Arrays.deepToString(coordinates));
            coordinates = arr;
        }

        System.out.println(Arrays.deepToString(coordinates));

//        safeCoordinatesBG
        if(team1.getColor() == Colors.BLUE){
            for (int q = 0; q < 5; q++) {
                safeCoordinates[q][0] = 177;
                safeCoordinates[q][1] = 520 - 23 * q;
            }

            for (int q = 5; q < 10; q++) {
                safeCoordinates[q][0] = 177;
                safeCoordinates[q][1] = 334 - 23 * (q - 5);
            }
        }
        //        safeCoordinatesRY
        else if(team1.getColor() == Colors.RED){
            for (int q = 0; q < 5; q++) {
                safeCoordinates[q][0] = 37 + 23 * q;
                safeCoordinates[q][1] = 378;
            }

            for (int q = 5; q < 10; q++) {
                safeCoordinates[q][0] = 224 + 23 * (q-5);
                safeCoordinates[q][1] = 378;
            }
        }

        //homeCoordinatesBG
        if(team1.getColor() == Colors.BLUE){
            for (int q = 0; q < 4; q++) {
                homeCoordinates[q][0] = 155 + (9 * q);
                homeCoordinates[q][1] = 393;
            }

            for (int q = 5; q < 10; q++) {
                homeCoordinates[q][0] = 155 + (9 * (q-5));
                homeCoordinates[q][1] = 355;
            }
        }
        //homeCoordinatesRY
        else if(team1.getColor() == Colors.RED){
            for (int q = 0; q < 4; q++) {
                homeCoordinates[q][0] = 152;
                homeCoordinates[q][1] = 355 + (9 * q);
            }

            for (int q = 5; q < 10; q++) {
                homeCoordinates[q][0] = 192;
                homeCoordinates[q][1] = 355 + (9 * (q-5));
            }
        }

        System.out.println("Coordinates set");

        rollDice1.setDisable(true);
        dice1.setOpacity(0.8);
        rollDice2.setDisable(true);
        dice2.setOpacity(0.8);
        arrow1.setVisible(false);
        arrow2.setVisible(false);

        System.out.println("Game is ready to start");

        //First team1 turn
        rollDice1.setDisable(false);
        dice1.setOpacity(1);
        arrow1.setVisible(true);

    }

    @FXML
    public void rollDice(ActionEvent e) throws InterruptedException {
        Button btn = (Button) e.getSource();
        if(btn == rollDice1){
            rollTheDice(dice1,btn);
            System.out.println(diceRoll);
            if(diceRoll == 6){
                for (int i = 0; i < 4; i++) {
                    team1.pieces[i].setBtnAble();
                    System.out.println(team1.pieces[i].getBtn());
                }
                    //user clicks one of the buttons
                    //It will be translated and state set to ACTIVE if new piece

            }
            else
            {
                for (int i = 0; i < 4; i++) {
                    if(team1.pieces[i].getState() == StateOfPiece.ACTIVE){
                        team1.pieces[i].setBtnAble();
                        System.out.println(team1.pieces[i].getBtn());
                    }
                }
                    //user clicks one of the buttons
                    //It will be translated

                rollDice1.setDisable(true);
                dice1.setOpacity(0.8);
                arrow1.setVisible(false);
                rollDice2.setDisable(false);
                dice2.setOpacity(1);
                arrow2.setVisible(true);
            }
        }
        else if(btn == rollDice2){
            rollTheDice(dice2,btn);
            System.out.println(diceRoll);
            if(diceRoll == 6){
                for (int i = 0; i < 4; i++) {
                    team2.pieces[i].setBtnAble();
                    System.out.println(team2.pieces[i].getBtn());
                }
                //user clicks one of the buttons
                //It will be translated and state set to ACTIVE if new piece
                //Disable all and ask for another dice roll
            }
            else
            {
                for (int i = 0; i < 4; i++) {
                    if(team2.pieces[i].getState() == StateOfPiece.ACTIVE){
                        team2.pieces[i].setBtnAble();
                        System.out.println(team2.pieces[i].getBtn());
                    }
                }
                //user clicks one of the buttons
                //It will be translated
                //Disable all
                rollDice2.setDisable(true);
                dice2.setOpacity(0.8);
                arrow2.setVisible(false);
                rollDice1.setDisable(false);
                dice1.setOpacity(1);
                arrow1.setVisible(true);
            }
        }
//        System.out.println(diceRoll);
    }

    public void rollTheDice(ImageView dice, Button btn) throws InterruptedException {
        AtomicInteger roll = new AtomicInteger(0);
        Thread thread = new Thread(() -> {
            btn.setDisable(true);
            System.out.println("Thread Running");
            Random random = new Random();
            try {
                for (int i = 0; i < 10; i++) {
                    roll.set(random.nextInt(6) + 1);
                    File file = new File("src/main/resources/com/jaideepjatin/ludomaster/assets/Dice/dice" + (roll)+".jpg");
                    dice.setImage(new Image(file.toURI().toString()));
                    Thread.sleep(75);
                }
                diceRoll = roll.intValue();

            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.out.println(roll.intValue());

            System.out.println("Thread Stops");
            btn.setDisable(false);
        });
        thread.start();
        thread.join();
    }

    public static boolean isWin(){
        int team1HomeCount = 0;
        int team2HomeCount = 0;

        for (Piece allPiece : allPieces) {
            if (allPiece.getState() == StateOfPiece.REACHED_HOME) {
                if (allPiece.getColor() == team1.getColor()) {
                    team1HomeCount++;
                } else if (allPiece.getColor() == team2.getColor()) {
                    team2HomeCount++;
                }
            }
        }

        if(team1HomeCount == 4){
            System.out.println("Team " + team1.getColor() + " won!!");
            return true;
        }
        else if(team2HomeCount == 4){
            System.out.println("Team " + team2.getColor() + " won!!");
            return true;
        }
        else {
            return false;
        }
    }
}
