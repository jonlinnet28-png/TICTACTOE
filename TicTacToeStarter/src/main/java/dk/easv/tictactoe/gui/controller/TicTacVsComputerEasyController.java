package dk.easv.tictactoe.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import java.util.Random;

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

            int player = game.getPlayer();
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
        catch (Exception e) {System.out.println(e.getMessage());}
    }

    private void playComputer() {
        if (game.isGameOver()) {return;}
        int fillCount = 0;
        int min = 0; int max = board.length;
        Random random = new Random();
        Button randomBtn = (Button) board[random.nextInt(max-min) + min][random.nextInt(max-min) + min];

        for (Button[] rows: board) {
            for (Button btn: rows) {
                if (!btn.getText().isEmpty()) {

                    fillCount++;
                }
            }
        }

        if (randomBtn.getText().isEmpty() && fillCount != 9) {
            randomBtn.setText("O");
            randomBtn.setTextFill(Paint.valueOf("Blue"));

        } else if (!randomBtn.getText().isEmpty() && fillCount !=9) {

            playComputer();
        }
    }
}