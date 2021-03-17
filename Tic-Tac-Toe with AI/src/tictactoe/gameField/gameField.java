package tictactoe.gameField;

import tictactoe.process.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Play the tic-tac-toe games with custom gameField.
 *
 * @author Thawng
 */
public class gameField {

    /**
     * Main method of the gameField class.
     * @param args array of strings
     * @throws FileNotFoundException throw if file is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        char[][] gameBoard;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter dimension: ");
        // Get the custom gameField e.g. 3x3, 5x5, etc...
        int length = input.nextInt();
        // If the given input is less than 2 throw an exception.
        if (length < 3) {
            throw new RuntimeException("Length need to be greater than or equal to 3");
        }

        // Initlize the gameBoard and clear all position.
        gameBoard = new char[length][length];
        cleanBoard(gameBoard);

        // Read in the input file and print out all the options.
        Scanner readFile = new Scanner(new File("C:\\Programming Projects\\Tic-Tac-Toe with AI\\src\\tictactoe\\gameField\\inputs.txt"));
        while (readFile.hasNextLine()) System.out.println(readFile.nextLine());

        // Play the game until 1 is selected from the inputs.
        while (true) {
            System.out.print("Enter mode: ");
            int mode = input.nextInt();
            if (mode == 2) {
                noAI noAI = new noAI(gameBoard);
                noAI.play();
                cleanBoard(gameBoard);
            } else if (mode == 3 || mode == 4) {
                easyMode easyMode = new easyMode(gameBoard, mode == 3);
                easyMode.play();
                cleanBoard(gameBoard);
            }  else if (mode == 5 || mode == 6) {
                mediumMode mediumMode = new mediumMode(gameBoard, mode == 5);
                mediumMode.play();
                cleanBoard(gameBoard);
            } else if (mode == 7 || mode == 8) {
                hardMode hardMode = new hardMode(gameBoard, mode == 7);
                hardMode.play();
                cleanBoard(gameBoard);
            } else {
                break;
            }
        }
    }

    /**
     * Make all position gameBoard equal to space char.
     * @param gameBoard the given gameBoard
     */
    private static void cleanBoard(char[][] gameBoard) {
        // Loop through all items.
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                gameBoard[i][j] = ' ';
            }
        }
    }
}
