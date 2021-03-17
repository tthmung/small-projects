package tictactoe.process;

/**
 * Easy mode with and easy AI that make random moves.
 * @author Thawng Hmung
 */
public class easyMode {

    // INSTANCE VARIABLE
    char[][] gameBoard;
    private final char player;
    private final char AIPiece;
    private final boolean start;

    /**
     * Constructor for the easyMode class
     * @param start true if the user is starting
     */
    public easyMode(char[][] gameBoard, boolean start) {
        this.gameBoard = gameBoard;
        this.start = start;
        // If the player is starting make the piece equal to x and rotation to 0.
        if (this.start) {
            player = 'X';
            AIPiece = 'O';
        } else {
            player = 'O';
            AIPiece = 'X';
        }
    }

    /**
     * Play the game.
     */
    public void play(){
        String result;

        do {
            // If the player is starting make it start the move first.
            if (start) {
                // Player move
                gamePlay.makeMove(gameBoard, player);
                Process.show(gameBoard);
                // Check for the result
                result = Process.winner(gameBoard);
                if (result != null) {
                    System.out.println(result);
                    return;
                }
                // Easy AI move
                makeMove();
            } else {
                // Make the player move second.
                // Easy AI move
                makeMove();
                Process.show(gameBoard);
                result = Process.winner(gameBoard);
                // Check for the result
                if (result != null) {
                    System.out.println(result);
                    return;
                }
                // Player move
                gamePlay.makeMove(gameBoard, player);
            }
            Process.show(gameBoard);
            result = Process.winner(gameBoard);

        } while (result == null);

        System.out.println(result);
    }

    /**
     * Make a random move
     */
    private void makeMove() {
        while (true) {
            // Generate a random number between 0 and 2.
            int move1 = (int) (Math.random() * 3);
            int move2 = (int) (Math.random() * 3);

            // If the position of the generate random numbers is empty put a piece there.
            if (gameBoard[move1][move2] == ' ') {
                gameBoard[move1][move2] = AIPiece;
                return;
            }

        }
    }
}
