package dk.easv.tictactoe.gui.AI;

import javafx.scene.control.Button;

public class MinimaxAIForGameBoard {

    private static final int MAX_DEPTH = 6;
    private static final char AI_PLAYER = 'O';
    private static final char HUMAN_PLAYER = 'X';

    public static int[] getBestMove(Button[][] board) {
        // Convert Button[][] to char[][]
        char[][] charBoard = new char[3][3];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                String text = board[r][c].getText();
                if (text.equals("X")) charBoard[r][c] = HUMAN_PLAYER;
                else if (text.equals("O")) charBoard[r][c] = AI_PLAYER;
                else charBoard[r][c] = '\0';
            }
        }

        // Minimax algorithm
        int[] bestMove = new int[]{-1, -1};
        int bestValue = Integer.MAX_VALUE; // AI minimizes

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (charBoard[r][c] == '\0') {
                    charBoard[r][c] = AI_PLAYER;
                    int moveValue = minimax(charBoard, MAX_DEPTH, true);
                    charBoard[r][c] = '\0';
                    if (moveValue < bestValue) {
                        bestValue = moveValue;
                        bestMove[0] = r;
                        bestMove[1] = c;
                    }
                }
            }
        }

        return bestMove;
    }

    private static int minimax(char[][] board, int depth, boolean isMax) {
        int score = evaluate(board, depth);
        if (Math.abs(score) > 0 || depth == 0 || !hasMovesLeft(board)) return score;

        if (isMax) { // Human maximizes
            int best = Integer.MIN_VALUE;
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    if (board[r][c] == '\0') {
                        board[r][c] = HUMAN_PLAYER;
                        best = Math.max(best, minimax(board, depth - 1, false));
                        board[r][c] = '\0';
                    }
                }
            }
            return best;
        } else { // AI minimizes
            int best = Integer.MAX_VALUE;
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    if (board[r][c] == '\0') {
                        board[r][c] = AI_PLAYER;
                        best = Math.min(best, minimax(board, depth - 1, true));
                        board[r][c] = '\0';
                    }
                }
            }

            return best;
        }
    }

    private static boolean hasMovesLeft(char[][] board) {
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if (board[r][c] == '\0') return true;
        return false;
    }

    private static int evaluate(char[][] board, int depth) {
        // Rows, columns, diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '\0' && board[i][0] == board[i][1] && board[i][1] == board[i][2])
                return board[i][0] == HUMAN_PLAYER ? 10 + depth : -10 - depth;
            if (board[0][i] != '\0' && board[0][i] == board[1][i] && board[1][i] == board[2][i])
                return board[0][i] == HUMAN_PLAYER ? 10 + depth : -10 - depth;
        }
        if (board[0][0] != '\0' && board[0][0] == board[1][1] && board[1][1] == board[2][2])
            return board[0][0] == HUMAN_PLAYER ? 10 + depth : -10 - depth;
        if (board[0][2] != '\0' && board[0][2] == board[1][1] && board[1][1] == board[2][0])
            return board[0][2] == HUMAN_PLAYER ? 10 + depth : -10 - depth;

        return 0;
    }
}