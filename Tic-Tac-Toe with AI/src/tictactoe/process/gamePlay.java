package tictactoe.process;

import java.util.Scanner;

/**
 * Player movement process and checked for vladiation.
 *
 * @author Thawng
 */
public class gamePlay {

    // INSTANCE VARIABLE
    private static final Scanner in = new Scanner(System.in);

    /**
     * make a valid move.
     *
     * @param gameBoard the gameBoard
     * @param piece     the piece adding to the gameBoard
     */
    public static void makeMove(char[][] gameBoard, char piece) {

        String[] move = null;
        int move1 = 0;
        int move2 = 0;

        // Check for a valid movement
        do {
            System.out.print("Enter number between 1 and " + gameBoard.length +", eg. 1 3 \n");
            System.out.print("Make move: ");
            String[] cor = in.nextLine().split(" ");
            if (!isNumeric(cor[0]) || !isNumeric(cor[1])) System.out.println("Invalid input try again");
            else if (cor.length == gameBoard.length) System.out.println("Invalid input try again");
            // Check if the given input is greater than the gameBoard length.
            else if (Integer.parseInt(cor[0]) > gameBoard.length + 1 || Integer.parseInt(cor[1]) > gameBoard.length + 1) {
                System.out.println("Invalid input try again");
            } else {
                move = cor;
                move1 = Integer.parseInt(move[0]) - 1;
                move2 = Integer.parseInt(move[1]) - 1;
                // Check if there is a piece in the given coordinate
                if (gameBoard[move1][move2] != ' ') {
                    System.out.println("Move already taken try again");
                    move = null;
                }
            }
        } while (move == null);

        gameBoard[move1][move2] = piece;
    }

    /**
     * Check if the string is numeric.
     * @return true if the string is numeric
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
