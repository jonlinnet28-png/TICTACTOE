
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

/**
 *
 * @author EASV
 */
public class TicTac2playerController implements Initializable {

    @FXML private Label lblPlayer;
    @FXML public GridPane gridPane;
    @FXML private Button btnNewGame, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    private static final String TXT_PLAYER = "Player: ";
    public IGameBoard game;
    public Button[][] board = new Button[3][3];

    /**
     * Event handler for the grid buttons
     *
     * @param event
     */
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

                    Button btn = (Button) event.getSource();

                    if (btn.getText().isEmpty()) {

                        String xOrO = player == 0 ? "X" : "O";
                        btn.setText(xOrO);
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
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Event handler for starting a new game
     *
     * @param event
     */
    @FXML
    private void handleNewGame(ActionEvent event) {
        game.newGame();
        setPlayer();
        clearBoard();


    }

    /**
     * Initializes a new controller
     *
     * @param url
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param rb
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        game = new GameBoard();
        setPlayer();

    }

    /**
     * Set the next player
     */
    public void setPlayer() {
        lblPlayer.setText(TXT_PLAYER + game.getNextPlayer());
    }


    /**
     * Finds a winner or a draw and displays a message based
     * @param winner
     */
    public void displayWinner(int winner) {
        String message = "";
        switch (winner)
        {
            case -1:
                message = "It's a draw :-(";
                break;
            default:
                message = "Player " + winner + " wins!!!";
                break;
        }
        lblPlayer.setText(message);
    }

    /**
     * Clears the game board in the GUI
     */
    private void clearBoard() {
        for(Node n : gridPane.getChildren()) {
            Button btn = (Button) n;
            btn.setText("");
            btn.setTextFill(Paint.valueOf("Black"));
        }
    }
}