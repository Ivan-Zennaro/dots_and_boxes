import java.util.Scanner;

public class TerminalMainUI {
    private static Scanner keyboard = new Scanner(System.in);

    private static String rulesAndHelp = """
                                    
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
            Insert "quit" to end the program 
                        
            Enjoy the game!""";

    public static void main(String[] args) {

        boolean flagEndProgram = false;
        System.out.println("Welcome to Dots and Boxes!\n");
        while (!flagEndProgram) {
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

                    Player player1 = new Player("1", Color.RED);
                    Player player2 = new Player("2", Color.BLU);
                    Game game = GameFactory.createComputerVsComputerGameWithCli(4, 4, player1, player2);
                    game.startGame();
                    System.out.println("\n--------------------------\nGAME ENDED\n--------------------------\n");
                }
                case "quit" -> flagEndProgram = true;
                default -> System.out.println("INVALID INPUT");
            }
        }

        keyboard.close();
        System.out.println("Goodbye!");
    }


    public static String getInput(){


        String input = "";
        System.out.println("Insert Game Mode (Insert \"?\" for Help,\"quit\" to quit the game)");
        try {
            input = keyboard.nextLine();
        } catch (Exception e) {
            System.out.println("Input Error: "+e.toString());
        }
        return input;
    }

    public static void start2PlayerGameWithCLI() {
        int input = 2;
        System.out.println("Insert board size (Default: 2):");
        try {
            input = Integer.parseInt(keyboard.nextLine());

        } catch (Exception e) {
            System.out.println("Invalid Insertion. Default is taken.");
        }
         Player player1 = new Player("1", Color.RED);
         Player player2 = new Player("2", Color.BLU);
        Game game = GameFactory.create2PlayerGameWithCli(input, input, player1, player2);
        game.startGame();
    }

    public static void startComputerGameWithCLI() {
        int input = 3;
        System.out.println("Insert board size (Default: 3):");
        try {
            input = Integer.parseInt(keyboard.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid Insertion. Default is taken.");
        }
        Difficulty difficulty = Difficulty.EASY;
        System.out.println("Insert game difficulty: EASY, MEDIUM or HARD(Default: EASY):");
        try {
            String inputString = keyboard.nextLine();
            switch (inputString) {
                case "MEDIUM" -> difficulty = Difficulty.MEDIUM;
                case "HARD" -> difficulty = Difficulty.HARD;
                default -> System.out.println("Invalid Insertion. Default is taken.");
            }
        } catch (Exception e) {
            System.out.println("Invalid Insertion. Default is taken.");
        }
        Player player1 = new Player("1", Color.RED);
        Player player2 = new Player("2", Color.BLU);
        Game game = GameFactory.createPlayerVsComputerGameWithCli(input, input, player1, player2,difficulty);
        game.startGame();
    }
}
