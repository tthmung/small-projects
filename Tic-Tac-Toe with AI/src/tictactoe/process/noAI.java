package tictactoe.process;

/**
 * A game mode between two players, there is no AI presented.
 *
 * @author Thawng Hmung
 */
public class noAI {

    // INSTANCE VARIABLES
    private int turn = 0;
    private char piece = 'X';
    char[][] gameBoard;

    /**
     * Constructor for the noAI.
     * @param gameBoard the gameBoard of the tic tac toe
     */
    public noAI(char[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * Play the noAI game.
     */
    public void play() {
        String result = Process.winner(gameBoard);

        // Play the game until the result is no longer null.
        while (result == null) {
            System.out.println(piece + "'s turn");
            // Player move
            gamePlay.makeMove(gameBoard, piece);
            Process.show(gameBoard);
            // Check the winner
            result = Process.winner(gameBoard);
            // Rotate the piece
            if (turn == 0) {
                turn++;
                piece = 'O';
            } else {
                turn--;
                piece = 'X';
            }
        }
        System.out.println(result);
    }
}
