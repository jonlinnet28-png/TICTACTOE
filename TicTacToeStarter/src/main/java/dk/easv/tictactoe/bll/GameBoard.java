
package dk.easv.tictactoe.bll;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author EASV
 */
public class GameBoard implements IGameBoard
{
private int player = 0;

private boolean gameState = true;

private String winColor = "Green";

private int winner = -2; // Default value | -1 = Draw, 0 = Player X Win, 1 = Player O Win

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    public int getNextPlayer()
    {
        if (player == 0) {
            player = 1;
        } else if (player == 1) {
            player = 0;
        }
        return player;
    }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver == true
     * this method will always return false.
     */
    public boolean play(int col, int row)
    {
        //TODO Implement this method
        return true;
    }

    /**
     * Tells us if the game has ended either by draw or by meeting the winning
     * condition.
     *
     * @return true if the game is over, else it will retun false.
     */
    public boolean isGameOver()
    {
        return !gameState;
    }

    private boolean getWinnerHorizontal(Button[][] board) {
        for (Button[] b: board) {

            String b0Text = (b[0] == null) ? "" : b[0].getText();
            String b1Text = (b[1] == null) ? "" : b[1].getText();
            String b2Text = (b[2] == null) ? "" : b[2].getText();

            if (b0Text.equals("X") && b1Text.equals("X") && b2Text.equals("X")){
                b[0].setTextFill(Paint.valueOf(winColor));b[1].setTextFill(Paint.valueOf(winColor));b[2].setTextFill(Paint.valueOf(winColor));
                System.out.println("Player X Wins Horizontally");
                winner = 0;
                gameState = false;
                return true;
            }
            else if (b0Text.equals("O") && b1Text.equals("O") && b2Text.equals("O")) {
                b[0].setTextFill(Paint.valueOf(winColor));b[1].setTextFill(Paint.valueOf(winColor));b[2].setTextFill(Paint.valueOf(winColor));
                System.out.println("Player O Wins Horizontally");
                winner = 1;
                gameState = false;
                return true;
            }
        }


        return false;
    }

    private boolean getWinnerVertical(Button[][] board) {

        Button[][] tempBoard = new Button[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tempBoard[i][j] = board[j][i];
            }
        }
        for (Button[] b: tempBoard) {

            String b0Text = (b[0] == null) ? "" : b[0].getText();
            String b1Text = (b[1] == null) ? "" : b[1].getText();
            String b2Text = (b[2] == null) ? "" : b[2].getText();

            if (b0Text.equals("X") && b1Text.equals("X") && b2Text.equals("X")){
                b[0].setTextFill(Paint.valueOf(winColor));b[1].setTextFill(Paint.valueOf(winColor));b[2].setTextFill(Paint.valueOf(winColor));
                System.out.println("Player X Wins Vertically");
                winner = 0;
                gameState = false;
                return true;
            }
            else if (b0Text.equals("O") && b1Text.equals("O") && b2Text.equals("O")) {
                b[0].setTextFill(Paint.valueOf(winColor));b[1].setTextFill(Paint.valueOf(winColor));b[2].setTextFill(Paint.valueOf(winColor));
                System.out.println("Player O Wins Vertically");
                winner = 1;
                gameState = false;
                return true;
            }
        }
        return false;
    }

    private boolean getWinnerDiagonal(Button[][] board) {

        String topLeftBtnTxt = (board[0][0] == null) ? "" : board[0][0].getText();
        String topRightBtnTxt = (board[0][2] == null) ? "" : board[0][2].getText();
        String bottomLeftBtnTxt = (board[2][0] == null) ? "" : board[2][0].getText();
        String bottomRightBtnTxt = (board[2][2] == null) ? "" : board[2][2].getText();
        String centerBtnTxt = (board[1][1] == null) ? "" : board[1][1].getText();

        if (centerBtnTxt.equals("X")) {
            if (topLeftBtnTxt.equals("X") && bottomRightBtnTxt.equals("X")) {
                board[0][0].setTextFill(Paint.valueOf(winColor)); board[1][1].setTextFill(Paint.valueOf(winColor)); board[2][2].setTextFill(Paint.valueOf(winColor));
                System.out.println("Player X Wins Diagonally");
                winner = 0;
                gameState = false;
                return true;
            } else if (topRightBtnTxt.equals("X") && bottomLeftBtnTxt.equals("X")) {
                board[0][2].setTextFill(Paint.valueOf(winColor)); board[1][1].setTextFill(Paint.valueOf(winColor)); board[2][0].setTextFill(Paint.valueOf(winColor));
                System.out.println("Player X Wins Diagonally");
                winner = 0;
                gameState = false;
                return true;
            }
        } else if (centerBtnTxt.equals("O")) {
            if (topLeftBtnTxt.equals("O") && bottomRightBtnTxt.equals("O")) {
                board[0][0].setTextFill(Paint.valueOf(winColor)); board[1][1].setTextFill(Paint.valueOf(winColor)); board[2][2].setTextFill(Paint.valueOf(winColor));
                System.out.println("Player O Wins Diagonally");
                winner = 1;
                gameState = false;
                return true;
            } else if (topRightBtnTxt.equals("O") && bottomLeftBtnTxt.equals("O")) {
                board[0][2].setTextFill(Paint.valueOf(winColor)); board[1][1].setTextFill(Paint.valueOf(winColor)); board[2][0].setTextFill(Paint.valueOf(winColor));
                System.out.println("Player O Wins Diagonally");
                winner = 1;
                gameState = false;
                return true;
            }
        }

        return false;
    }

    private void getDraw(Button[][] board) {

        boolean filled = true;

        for (Button[] row: board) {
            for (Button btn: row) {
                String newBtnText = (btn == null) ? "" : btn.getText();
                if (newBtnText.isEmpty()) {
                    filled = false;
                    break;
                }
            }
            if (!filled) {
                break;
            }
        }
        if (filled) {
            winner = -1;
            gameState = false;
        }
    }

    public void checkWin(Button[][] board) {
        if (!getWinnerHorizontal(board) && !getWinnerVertical(board) && !getWinnerDiagonal(board)) {
            getDraw(board);
        }
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    public int getWinner()
    {
        return winner;
    }

    public boolean getGameState() {

        return gameState;

    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame()
    {
        player = 0;
        gameState = true;
        winner = -2;
    }
}
