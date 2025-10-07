package dk.easv.tictactoe.gui.AI;

public class MinimaxAI {
    // Evaluate board state (win, lose, draw, or in-progress)
    private int evaluate(char[][] board) {
        // Example: +10 for AI win, -10 for human win, 0 for draw
        // Add your game’s win/draw logic here
        return 0;
    }

    // Check if moves are left
    private boolean isMovesLeft(char[][] board) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == '_')
                    return true;
        return false;
    }

    // Minimax function
    private int minimax(char[][] board, int depth, boolean isMax) {
        int score = evaluate(board);

        // If AI wins
        if (score == 10) return score - depth;

        // If Human wins
        if (score == -10) return score + depth;

        // If no moves left, it’s a draw
        if (!isMovesLeft(board)) return 0;

        if (isMax) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '_') {
                        board[i][j] = 'O'; // AI move
                        best = Math.max(best, minimax(board, depth + 1, false));
                        board[i][j] = '_';
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '_') {
                        board[i][j] = 'X'; // Human move
                        best = Math.min(best, minimax(board, depth + 1, true));
                        board[i][j] = '_';
                    }
                }
            }
            return best;
        }
    }

    // Find best move
    public int[] findBestMove(char[][] board) {
        int bestVal = Integer.MIN_VALUE;
        int[] bestMove = {-1, -1};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '_') {
                    board[i][j] = 'O'; // AI tries move
                    int moveVal = minimax(board, 0, false);
                    board[i][j] = '_';

                    if (moveVal > bestVal) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }
}
