package tictactoe.process;

/**
 * Medium mode of the tic tac toe game.
 * @author Thawng Hmung
 */
public class mediumMode {

    // INSTANCE VARIABLES
    private final char[][] gameBoard;
    private final char player;
    private final char AIPiece;
    private final boolean start;
    private final int length;

    /**
     * Constructor for the medium mode
     * @param gameBoard the game board in which the game will be played
     * @param start true if the player is starting otherwise false
     */
    public mediumMode(char[][] gameBoard, boolean start) {
        this.gameBoard = gameBoard;
        this.start = start;
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
     * Play the game
     */
    public void play() {
        String result;

        do {
            // If start is true make the user move first.
            if (start) {
                gamePlay.makeMove(gameBoard, player);
                Process.show(gameBoard);
                // Check for the result of the game
                result = Process.winner(gameBoard);
                if (result != null) {
                    System.out.println(result);
                    return;
                }
                // Rotate the piece and make the computer move.
                makeMove();
            } else {
                // Make the computer move.
                makeMove();
                Process.show(gameBoard);
                // Check for winner.
                result = Process.winner(gameBoard);
                if (result != null) {
                    System.out.println(result);
                    return;
                }
                // Rotate and make the player make a move.
                gamePlay.makeMove(gameBoard, player);
            }
            // Rotate the piece and check for winner.
            Process.show(gameBoard);
            result = Process.winner(gameBoard);
        } while (result == null);

        System.out.println(result);
    }

    /**
     * If there is a winning or losing condition make a move
     * else make a random move.
     */
    private void makeMove() {

        // Variables to keep track
        final int size = length;
        int countX = 0;
        int countO = 0;
        int move1 = 0;
        int move2 = 0;

        // Check diagonal
        for (int i = 0; i < size; i++) {

            // Check top to bottom
            for (int j = 0; j < size; j++) {
                if (gameBoard[j][i] == 'X') countX++;
                else if (gameBoard[j][i] == 'O') countO++;
                else if (gameBoard[j][i] == ' ') {
                    move1 = j;
                    move2 = i;
                }
            }

            // If there is a winning condition move to that location
            if ((countO == size - 1 || countX == size - 1) && gameBoard[move1][move2] == ' ') {
                gameBoard[move1][move2] = AIPiece;
                return;
            } else {
                countX = 0;
                countO = 0;
            }

            // Check left to right
            for (int j = 0; j < size; j++) {
                if (gameBoard[i][j] == 'O') countO++;
                else if (gameBoard[i][j] == 'X') countX++;
                else if (gameBoard[i][j] == ' '){
                    move1 = i;
                    move2 = j;
                }
            }

            // If there is a winning condition move to that location
            if ((countO == size - 1 || countX == size - 1) && gameBoard[move1][move2] == ' ') {
                gameBoard[move1][move2] = AIPiece;
                return;
            } else {
                countX = 0;
                countO = 0;
            }
        }

        countO = 0;
        countX = 0;

        // Check left diagonal
        for (int i = 0; i < size; i++) {
            if (gameBoard[i][i] == 'X') countX++;
            else if (gameBoard[i][i] == 'O') countO++;
            else {
                move1 = i;
                move2 = i;
            }
        }
        // Find if there is a winning condition.
        if ((countO == size - 1 || countX == size - 1) && gameBoard[move1][move2] == ' ') {
            gameBoard[move1][move2] = AIPiece;
            return;
        }

        countX = 0;
        countO = 0;

        // Find if there is matching in the right diagonal.
        for (int i = 0, j = size - 1; i < size; i++, j--) {
            if (gameBoard[i][j] == 'O') countO++;
            else if (gameBoard[i][j] == 'X') countX++;
            else {
                move1 = i;
                move2 = j;
            }
        }

        // Find if there is a winning condition
        if ((countO == size - 1 || countX == size - 1) && gameBoard[move1][move2] == ' ') {
            gameBoard[move1][move2] = AIPiece;
            return;
        }

        // If there is no winning condition make a random move.
        randomMove();
    }

    /**
     * Make a random move, with random generated number.
     */
    private void randomMove() {
        while (true) {
            // Generate a random number between 0 and 2.
            int move1 = (int) (Math.random() * 3);
            int move2 = (int) (Math.random() * 3);

            // If the position is free make a move at the position.
            if (gameBoard[move1][move2] == ' ') {
                gameBoard[move1][move2] = AIPiece;
                return;
            }
        }
    }
}
