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
                    if (player == 1) {


                        
                        for (Node n: gridPane.getChildren()) {
                            Button b = (Button) n;

                            if (b.getText().isEmpty()) {

                                b.setText("O");
                                break;

                            }

                        }

                    } else {
                        Button btn = (Button) event.getSource();

                        if (btn.getText().isEmpty()) {

                            String xOrO = player == 0 ? "X" : "O";
                            btn.setText(xOrO);

                        }
                    }

                    setPlayer();
                    game.getNextPlayer();

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
}
