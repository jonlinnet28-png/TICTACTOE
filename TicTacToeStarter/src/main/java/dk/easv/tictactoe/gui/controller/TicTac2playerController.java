
package dk.easv.tictactoe.gui.controller;

// Java imports
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

// Project imports
import dk.easv.tictactoe.bll.GameBoard;
import dk.easv.tictactoe.bll.IGameBoard;
import javafx.scene.paint.Paint;

public class TicTac2playerController implements Initializable {

    @FXML private Label lblPlayer; // Top label displaying player turn and win
    @FXML public GridPane gridPane; // GridPane containing the 3x3 gameboard (buttons)

    private static final String TXT_PLAYER = "Player: ";
    public IGameBoard game; // GameBoard instance
    public Button[][] board = new Button[3][3]; // 2D array that will contain the buttons on the gameboard

    @FXML
    public void initializeBoardArray() {
        for (var node : gridPane.getChildren()) { // A for-loop that returns all nodes (components) in the grid pane
            if (node instanceof Button btn) {
                int row = GridPane.getRowIndex(btn) == null ? 0 : GridPane.getRowIndex(btn);
                int col = GridPane.getColumnIndex(btn) == null ? 0 : GridPane.getColumnIndex(btn);
                board[row][col] = btn;
                // Stores the buttons in a 2D array (rows and columns)
            }
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        try
        {
            // Inserting button in the 2D array
            Integer row = GridPane.getRowIndex((Button) event.getSource());
            Integer col = GridPane.getColumnIndex((Button) event.getSource());
            int r = (row == null) ? 0 : row;
            int c = (col == null) ? 0 : col;


            int player = game.getPlayer();
            if (game.getGameState()) {
                if (game.play(c, r)) {

                    Button btn = (Button) event.getSource();

                    if (btn.getText().isEmpty()) {

                        String xOrO = player == 0 ? "X" : "O";
                        btn.setText(xOrO);

                        if (btn.getText().equals("X")) {
                            btn.setTextFill(Paint.valueOf("Red"));
                        } else if (btn.getText().equals("O")) {
                            btn.setTextFill(Paint.valueOf("Blue"));
                        }

                        setPlayer();
                        game.setNextPlayer();

                        game.checkWin(board);

                        if (game.isGameOver()) {
                            int winner = game.getWinner();
                            displayWinner(winner);
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private void handleNewGame(ActionEvent event) {
        game.newGame();
        setPlayer();
        clearBoard();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        game = new GameBoard();
        initializeBoardArray();
        setPlayer();
    }

    public void setPlayer() {
        int player = game.getPlayer();

        if (player == 0) {
            lblPlayer.setText(TXT_PLAYER + "X");
        } else if (player == 1) {
            lblPlayer.setText(TXT_PLAYER + "O");
        }

    }

    public void displayWinner(int winner) {
        String message = "";
        switch (winner)
        {
            case -1:
                message = "It's a draw :-(";
                break;
            case 0:
                message = "X wins!!!";
                break;
            case 1:
                message = "O wins!!!";
        }
        lblPlayer.setText(message);
    }

    private void clearBoard() {
        for(Node n : gridPane.getChildren()) {
            Button btn = (Button) n;
            btn.setText("");
            btn.setTextFill(Paint.valueOf("Black"));
        }
        for (Button[] rows: board) {
        }
    }
}