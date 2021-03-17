package tictactoe.process;

/**
 * Hard mode where the AI is unbeatable.
 * @author Thawng Hmung
 */
public class hardMode {

    // INSTANCE VARIABLE
    private final char[][] gameBoard;
    private final boolean start;
    private final char player;
    private final char AIPiece;
    private final int length;

    /**
     * Constructor for the hardMode
     * @param gameBoard the game field where they will play
     * @param start true if the player is starting else false
     */
    public hardMode(char[][] gameBoard, boolean start) {
        this.start = start;
        this.gameBoard = gameBoard;
        if (this.start) {
            player = 'X';
            AIPiece = 'O';
        } else {
            player = 'O';
            AIPiece = 'X';
        }
        length = gameBoard.length;
    }

    /**
     * Play the game.
     */
    public void play() {
        String result;
        do {
            // If start the player make the first move.
            if (start) {
                // Player move
                gamePlay.makeMove(gameBoard, player);
                // Show the movement and check for winner.
                Process.show(gameBoard);
                result = Process.winner(gameBoard);
                if (result != null) {
                    // Print out the result and end the method.
                    System.out.println(result);
                    return;
                }
                // AI move
                bestMove();
            } else {
                // AI move
                bestMove();
                // Show the movement and check for winner.
                Process.show(gameBoard);
                result = Process.winner(gameBoard);
                if (result != null) {
                    // Print out result and end the method.
                    System.out.println(result);
                    return;
                }
                // Player move
                gamePlay.makeMove(gameBoard, player);
            }
            // Show the game board and check for winner.
            Process.show(gameBoard);
            result = Process.winner(gameBoard);
        } while (result == null);

        // Print out result
        System.out.println(result);
    }

    /**
     * Evaluate the game state.
     * @return the score
     */
    private int evaluate() {
        // Check if result is null.
        String result = Process.winner(gameBoard);
        if (result != null) {
            // If the result contain X and AI piece is X return 10.
            if (result.contains("X") && AIPiece == 'X') return 10;
            else if (result.contains("O") && AIPiece == 'O') return 10;
            else if (result.contains("win")) { // If result contain wins and its not AI piece return -10.
                return -10;
            }
        }

        // If all condition return 0.
        return 0;
    }

    /**
     * Minimax algorithm to find the best move possible.
     * @param depth the depth of the analysis
     * @param isMax true if the score is at max
     * @return the score
     */
    private int minimax(int depth, Boolean isMax) {

        // Check if the game is over.
        String result = Process.winner(gameBoard);
        if (result != null) {
            if (result.contains("win")) {
                return evaluate();
            } else return 0;
        }

        int best;
        if (isMax) {
            best = Integer.MIN_VALUE;

            // Go through the whole position.
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    // If a position is allowed to play evaluate.
                    if (gameBoard[i][j] == ' ') {

                        // Add piece to the position and check if move is efficient.
                        gameBoard[i][j] = AIPiece;

                        // Compare using Math.max and call recursive.
                        best = Math.max(best, minimax(depth + 1, false));

                        // Clear the position
                        gameBoard[i][j] = ' ';
                    }
                }
            }

        } else {
            best = Integer.MAX_VALUE;

            // Go through the whole position.
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    // If a position is allowed to play evaluate.
                    if (gameBoard[i][j] == ' ') {

                        // Add piece to the position and check if move is efficient.
                        gameBoard[i][j] = player;

                        // Compare using Math.min and call recursive
                        best = Math.min(best, minimax(depth + 1, true));

                        // Clear the position
                        gameBoard[i][j] = ' ';
                    }
                }
            }
        }
        // Return the best score.
        return best;
    }

    /**
     * Get the best move and add the piece to the game board.
     */
    private void bestMove() {

        // Check if the game is over.
        String result = Process.winner(gameBoard);
        if (result != null) return;

        // Variable
        int bestVal = -1000;
        int move1 = -1;
        int move2 = -1;

        // Go through the whole game board.
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                // If position is free, evaluate using mini max.
                if (gameBoard[i][j] == ' ') {

                    gameBoard[i][j] = AIPiece;
                    // Call the minimax algorithm.
                    int score = minimax(0, false);
                    gameBoard[i][j] = ' ';

                    /* If the score from miniMax is greater than bestVal, make bestVal equal to score and update
                    * move1 and move2 to corresponding i and j.
                    */
                    if (score > bestVal) {
                        bestVal = score;
                        move1 = i;
                        move2 = j;
                    }
                }
            }
        }

        // Make the move.
        gameBoard[move1][move2] = AIPiece;
    }
}
