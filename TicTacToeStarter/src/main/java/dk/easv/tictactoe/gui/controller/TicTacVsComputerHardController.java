package dk.easv.tictactoe.gui.controller;


import dk.easv.tictactoe.bll.GameBoard;
import dk.easv.tictactoe.gui.AI.MinimaxAIForGameBoard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;


import java.net.URL;
import java.util.ResourceBundle;


public class TicTacVsComputerHardController extends TicTac2playerController implements Initializable {

    private static final String TXT_PLAYER = "Player: ";


    public  TicTacVsComputerHardController() {
        super();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        Button btn = (Button) event.getSource();
        int row = GridPane.getRowIndex(btn) == null ? 0 : GridPane.getRowIndex(btn);
        int col = GridPane.getColumnIndex(btn) == null ? 0 : GridPane.getColumnIndex(btn);


        int player = game.getNextPlayer(); // toggle and get current
        if (game.getGameState() && game.play(col, row) && btn.getText().isEmpty()) {
            btn.setText(player == 0 ? "X" : "O");
            btn.setTextFill(Paint.valueOf("Red"));


            game.checkWin(board);
            if (game.isGameOver()) {
                displayWinner(game.getWinner());
                return;
            }


            // After Player plays, let AI move
            if (player == 0) {
                makeAIMove();
            }
        }
    }


    private void makeAIMove() {
        int[] move = MinimaxAIForGameBoard.getBestMove(board); // Applies the AI
        if (move[0] != -1 && move[1] != -1) {
            int row = move[0];
            int col = move[1];


            int player = game.getNextPlayer(); // Sets the AI as player 1
            if (game.play(col, row)) { // Applies the game's logic
                board[row][col].setText(player == 0 ? "X" : "O");
                board[row][col].setTextFill(Paint.valueOf("Blue"));


                game.checkWin(board);
                if (game.isGameOver()) {
                    displayWinner(game.getWinner());
                }
            }
        }
    }
}