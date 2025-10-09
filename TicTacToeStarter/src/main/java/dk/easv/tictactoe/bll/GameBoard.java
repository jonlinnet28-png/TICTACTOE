
package dk.easv.tictactoe.bll;

import javafx.scene.control.Button;
import javafx.scene.paint.Paint;

public class GameBoard implements IGameBoard
{
private int player = 0;

private boolean gameState = true;

private String winColor = "Green";

private int winner = -2; // Default value | -1 = Draw, 0 = Player X Win, 1 = Player O Win

    public int getNextPlayer() {
        if (player == 0) {
            player = 1;
        } else if (player == 1) {
            player = 0;
        }
        return player;
    }

    public boolean play(int col, int row)
    {
        return true;
    }

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
                winner = 0;
                gameState = false;
                return true;
            }
            else if (b0Text.equals("O") && b1Text.equals("O") && b2Text.equals("O")) {
                b[0].setTextFill(Paint.valueOf(winColor));b[1].setTextFill(Paint.valueOf(winColor));b[2].setTextFill(Paint.valueOf(winColor));
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
                winner = 0;
                gameState = false;
                return true;
            }
            else if (b0Text.equals("O") && b1Text.equals("O") && b2Text.equals("O")) {
                b[0].setTextFill(Paint.valueOf(winColor));b[1].setTextFill(Paint.valueOf(winColor));b[2].setTextFill(Paint.valueOf(winColor));
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
                winner = 0;
                gameState = false;
                return true;
            } else if (topRightBtnTxt.equals("X") && bottomLeftBtnTxt.equals("X")) {
                board[0][2].setTextFill(Paint.valueOf(winColor)); board[1][1].setTextFill(Paint.valueOf(winColor)); board[2][0].setTextFill(Paint.valueOf(winColor));
                winner = 0;
                gameState = false;
                return true;
            }
        } else if (centerBtnTxt.equals("O")) {
            if (topLeftBtnTxt.equals("O") && bottomRightBtnTxt.equals("O")) {
                board[0][0].setTextFill(Paint.valueOf(winColor)); board[1][1].setTextFill(Paint.valueOf(winColor)); board[2][2].setTextFill(Paint.valueOf(winColor));
                winner = 1;
                gameState = false;
                return true;
            } else if (topRightBtnTxt.equals("O") && bottomLeftBtnTxt.equals("O")) {
                board[0][2].setTextFill(Paint.valueOf(winColor)); board[1][1].setTextFill(Paint.valueOf(winColor)); board[2][0].setTextFill(Paint.valueOf(winColor));
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

    public int getWinner()
    {
        return winner;
    }

    public boolean getGameState() {

        return gameState;

    }

    public void newGame() {
        player = 0;
        gameState = true;
        winner = -2;
    }
}