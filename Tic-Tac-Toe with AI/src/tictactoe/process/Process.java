package tictactoe.process;

/**
 * Process game by checking winner and displaying out put.
 * @author Thawng Hmung
 */
public class Process {

    /**
     * Constant enum of for result of the game.
     */
    private enum result{
        X, O, DRAW, STATE
    }

    // INSTANCE VARIABLES
    private static result state = result.STATE;

    /**
     * Check the winner of the game.
     * @param gameBoard the gameBoard
     * @return the result of the game
     */
    public static String winner(char[][] gameBoard) {

        int length = gameBoard.length;
        int total = length * length;

        int count = 0;
        int empty = 0;

        if (empty == total) return null;

        // Check for winner.
        if (checkStraight(gameBoard) || checkDiagonal(gameBoard)) {
            if (state == result.X) return "X wins";
            else return "O wins";
        }

        // Check for draw
        for (int i = 0; i < length; i++) {
            for (char[] chars : gameBoard) {
                if (chars[i] != ' ') count++;
                else empty++;
            }
        }

        // If the count is equal to 2x length return a draw.
        if (count == total) {
            state = result.DRAW;
            return "Draw";
        }

        // Game is not over
        state = result.STATE;
        return null;
    }

    /**
     * Check rows and column for winner.
     * @param gameBoard the gameBoard
     * @return true if there is a winner
     */
    private static boolean checkStraight(char[][] gameBoard) {

        int length = gameBoard.length;
        int countRow = 0;
        int countColumn = 0;

        // Check base on the height for max efficiently.
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (gameBoard[i][i] == gameBoard[j][i] && gameBoard[j][i] != ' ') countRow++;
                if (gameBoard[i][i] == gameBoard[i][j] && gameBoard[i][i] != ' ') countColumn++;

                if ((countRow == length || countColumn == length) && (gameBoard[i][i] != ' '
                        || gameBoard[j][i] != ' ')) {
                    // If the element is x make enum X else make enum O and return true.
                    if (gameBoard[i][i] == 'X') state = result.X;
                    else state = result.O;
                    return true;
                }
            }
            // Reset the countRow and countColumn.
            countRow = 0;
            countColumn = 0;
        }

        // Return false if no matching is found.
        return false;
    }

    /**
     * Check the diagonal of the game.
     * @param gameBoard the gameBoard
     * @return true if there is a winner
     */
    private static boolean checkDiagonal(char[][] gameBoard) {

        int length = gameBoard.length;
        int half = (gameBoard.length - 1) / 2;
        int left = 0;
        int right = 0;

        // Find if there is matching in the left diagonal.
        for (int i = 0; i < length; i++) {
            if (gameBoard[i][i] != ' ' && gameBoard[half][half] == gameBoard[i][i]) left++;
        }

        // If the left is the same as length then there is a winner.
        if (left == length && gameBoard[half][half] != ' ') {
            if (gameBoard[half][half] == 'X') state = result.X;
            else state = result.O;
            return true;
        }

        // Find if there is matching in the right diagonal.
        for (int i = 0, j = length - 1; i < length; i++, j--) {
            if (gameBoard[half][half] != ' ' && gameBoard[half][half] == gameBoard[i][j]) right++;
        }
        // If the right is the same as length then there is a winner.
        if (right == length && gameBoard[half][half] != ' ') {
            if (gameBoard[half][half] == 'X') state = result.X;
            else state = result.O;
            return true;
        }
        return false;
    }

    /**
     * Print the board with all the pieces in the console.
     * @param gameBoard the board
     */
    public static void show(char[][] gameBoard) {
        int length = gameBoard.length;

        // Create a horizontal line on top of the board.
        printLine(length);
        // Loop through the array and print all the element in order.
        for (char[] chars : gameBoard) {
            for (int j = 0; j < length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.println();
        }
        // Create a horizontal line on the bottom of the board.
        printLine(length);
    }

    /**
     * Create a horizontal line
     * @param n the length of the array
     */
    private static void printLine(int n) {
        // Print a horizontal line depending on the length of the array.
        for (int i = 0; i < (n * 2); i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}