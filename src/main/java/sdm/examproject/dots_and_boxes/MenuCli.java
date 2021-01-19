package sdm.examproject.dots_and_boxes;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class MenuCli {
    private static final Scanner keyboard = new Scanner(System.in);

    private static final Player player1 = new Player("Albert", Color.RED);
    private static final Player player2 = new Player("Ben", Color.BLU);

    private static final String rulesAndHelp = """
                                    
            !!Help and Rules!!                   
                  
            The rules are:
            1. The first player must select a side of a square;
            2. The format to choose is ["x y Side"]. x and y go select the square. 
            Side selects the side of the square and can be U, D, L or R. 
            2. At each Player's turn, a line from the game grid must be selected;
            3. The player who completes a box gets a point and has an extra turn;
            4. Two players compete to complete most boxes in the game grid\s
            and to get more points!
                                    
            Insert "1" to start a 2 player game.
            Insert "2" to start a game versus the computer.
            Insert "3" to start a demo of the game.
            Insert "4" to start a host a game.
            Insert "5" to join a game.
            Insert "quit" to end the program 
                        
            Enjoy the game!""";

    public static void main(String[] args) {

        boolean flagEndProgram = false;
        System.out.println("----------------------------\n");
        System.out.println("-Welcome to Dots and Boxes!-\n");
        System.out.println("----------------------------\n");
        System.out.println(rulesAndHelp);

        while (!flagEndProgram) {
            System.out.println("\n!!Main Menu!!");
            String input = getInput();
            switch (input) {
                case "?" -> System.out.println(rulesAndHelp);
                case "1" -> {
                    start2PlayerGameWithCLI();
                    System.out.println("\n--------------------------\nGAME ENDED\n--------------------------\n");
                }
                case "2" -> {
                    startComputerGameWithCLI();
                    System.out.println("\n--------------------------\nGAME ENDED\n--------------------------\n");
                }
                case "3" -> {

                    Game game = GameFactory.createComputerVsComputerGameWithCli(2, 2, player1, player2);
                    game.startGame();
                    System.out.println("\n--------------------------\nGAME ENDED\n--------------------------\n");
                }
                case "4" -> {
                    startServerGameWithCli();
                    System.out.println("\n--------------------------\nSERVER CLOSED\n--------------------------\n");

                }
                case "5" -> {
                    startClientGameWithCli();
                    System.out.println("\n--------------------------\nGAME ENDED\n--------------------------\n");

                }
                case "quit" -> flagEndProgram = true;
                default -> System.out.println("INVALID INPUT");
            }
        }

        keyboard.close();
        System.out.println("Goodbye!");
    }


    private static String getInput() {
        String input = "";
        System.out.println("Insert Game Mode (Insert \"?\" for Help,\"quit\" to quit the game)");
        try {
            input = keyboard.nextLine();
        } catch (NoSuchElementException | IllegalStateException e) {
            System.out.println("Input Error: " + e.toString());
        }
        return input;
    }

    private static void start2PlayerGameWithCLI() {
        int nRows = getIntegerValueFromKeyboard("Insert number of Rows (Default: 3):",3);
        int nCols = getIntegerValueFromKeyboard("Insert number of Columns (Default: 3):",3);
        Game game = GameFactory.create2PlayerGameWithCli(nRows, nCols, player1, player2);
        game.startGame();
    }



    private static void startComputerGameWithCLI() {
        int nRows = getIntegerValueFromKeyboard("Insert number of Rows (Default: 3):",3);
        int nCols = getIntegerValueFromKeyboard("Insert number of Columns (Default: 3):",3);

        Difficulty difficulty = Difficulty.EASY;
        System.out.println("Insert game difficulty: EASY, MEDIUM or HARD (Default: EASY):");
        try {
            String inputString = keyboard.nextLine();
            switch (inputString) {
                case "MEDIUM" -> difficulty = Difficulty.MEDIUM;
                case "HARD" -> difficulty = Difficulty.HARD;
                default -> System.out.println("Invalid Insertion. Default is taken.");
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            System.out.println("Invalid Insertion. Default is taken.");
        }
        Game game = GameFactory.createPlayerVsComputerGameWithCli(nRows, nCols, player1, player2, difficulty);
        game.startGame();
    }

    private static void startServerGameWithCli() {
        Game game = GameFactory.createServerGameWithCli(player1, player2);
        game.startGame();
    }

    private static void startClientGameWithCli() {
        String ip;
        System.out.println("Insert IP address:");
        ip = keyboard.nextLine();

        System.out.println("Connecting...");
        Game game = GameFactory.createClientGameWithCli(player1, player2, ip);
        game.startGame();

    }

    private static int getIntegerValueFromKeyboard(String stringToShow, int defaultValue) {
        int input;
        System.out.println(stringToShow);
        try {
            input = Integer.parseInt(keyboard.nextLine());
            if (input < 1) throw new NumberFormatException();
        } catch (NumberFormatException | IllegalStateException | NoSuchElementException e) {
            input = defaultValue;
            System.out.println("Invalid Insertion. Default is taken.");
        }
        return input;
    }
}
