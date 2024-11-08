package edu.grinnell.csc207.fliptiles;

import java.util.Scanner;

/**
 * User interface for GAME (add here)
 * Manages game flow, user input, and displays game state.
 */
public class GameUI {
    private GameLogic game;
    private Scanner scanner;

    /**
     * Initializes the game UI with a new scanner.
     */
    public GameUI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Starts the game, allows player to select difficulty, 
     * and handles the main game loop.
     */
    public void startGame() {
        System.out.println("Welcome to Flip Tile!");
        System.out.println("Select difficulty: 1 (4x4), 2 (3x3), or 3 (5x5)");
        
        int difficulty = scanner.nextInt();
        int size = (difficulty == 1) ? 4 : (difficulty == 2) ? 3 : 5;

        String exitText = "";

        game = new GameLogic(size);

        System.out.println("Flip all the tiles to complete the game. Enter row and column to flip.");

        while (!game.isGameComplete()) {
            game.displayBoard();
            System.out.print("type \"QUIT\" to quit or enter anything to continue: ");
            String text = scanner.next();

            // if the person quit the game
            if (text.equals("QUIT")) {
                System.out.println("Hope you try again :) ");
                exitText = "QUIT";
                break;
            }

            System.out.print("Enter row: ");
            int row = scanner.nextInt();
            System.out.print("Enter col: ");
            int col = scanner.nextInt();

            if (!game.makeMove(row, col)) {
                System.out.println("Invalid move. Try again.");
                continue;
            }
        }

        // if the game is solved
        if (exitText.equals("")) {
        System.out.println("Congratulations! You've completed the game.");
        System.out.println("Moves taken: " + game.getMoveCount());
        System.out.println("Time taken: " + game.getElapsedTimeInSeconds() + " seconds.");
        }
    }
    
    /**
     * Main method to start the game.
     * 
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        GameUI ui = new GameUI();
        ui.startGame();
    }
}
