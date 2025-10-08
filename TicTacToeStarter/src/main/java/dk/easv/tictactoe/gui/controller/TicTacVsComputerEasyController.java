package dk.easv.tictactoe.gui.controller;

import dk.easv.tictactoe.bll.GameBoard;
import dk.easv.tictactoe.bll.IGameBoard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import java.net.URL;
import java.util.ResourceBundle;

public class TicTacVsComputerEasyController extends TicTac2playerController implements Initializable {

    public  TicTacVsComputerEasyController() {
        super();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        try
        {

            Integer row = GridPane.getRowIndex((Button) event.getSource());
            Integer col = GridPane.getColumnIndex((Button) event.getSource());
            int r = (row == null) ? 0 : row;
            int c = (col == null) ? 0 : col;
            board[r][c] = (Button) event.getSource(); // 2D array af knapper.
            int player = game.getNextPlayer();
            if (game.getGameState()) {
                if (game.play(c, r)) {
                    if (player == 0) {

                        Button btn = (Button) event.getSource();

                        if (btn.getText().isEmpty()) {

                            String xOrO = player == 0 ? "X" : "O";
                            btn.setText(xOrO);


                            btn.setTextFill(Paint.valueOf("Red"));


                            playComputer();

                        }

                    }

                    setPlayer();

                    game.checkWin(board);

                    if (game.isGameOver()) {
                        int winner = game.getWinner();
                        displayWinner(winner);
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void playComputer() {

        if (game.isGameOver()) {return;}
        Object[] buttonList = gridPane.getChildren().toArray();
        Random random = new Random();
        int min = 0; int max = buttonList.length;

        int nullCount = 0;

        for (Button[] row: board) {

            for (Button btn: row) {

                if (btn == null) {

                    nullCount++;
                    System.out.println(nullCount);

                }

            }

        }


        Button randomBtn = (Button) buttonList[random.nextInt(max-min) + min];
        if (randomBtn.getText().isEmpty() && nullCount != 0) {


            for (Button[] rows: board) {

                List<Button> btnList = Arrays.asList(rows);

                if (!btnList.contains(randomBtn)) {

                    System.out.println("contains button");
                    Integer row = GridPane.getRowIndex(randomBtn);
                    Integer col = GridPane.getColumnIndex(randomBtn);
                    int r = (row == null) ? 0 : row;
                    int c = (col == null) ? 0 : col;

                    board[r][c] = randomBtn;

                    randomBtn.setText("O");
                    randomBtn.setTextFill(Paint.valueOf("Blue"));
                    break;

                }

            }
        } else if (!randomBtn.getText().isEmpty() && nullCount != 0) {

            playComputer();

        }

    }
}
