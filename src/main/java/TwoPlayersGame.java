import java.util.Scanner;

public class TwoPlayersGame extends Game {
    Scanner keyboard;

    @Override
    public void startGame() {
        initializeBoard();

        while (!isGameFinished()) {
            printScoreBoard();
            System.out.println("Insert move [x y side:U,D,L,R]?");
            turn(keyboard.nextLine());
        }
        keyboard.close();
    }

    @Override
    public Board initializeBoard() {
        keyboard = new Scanner(System.in);

        int optionGrid;
        do {
            System.out.println("How big the grid? 2:[2x2]  3:[3x3] 5:[5x5]");
            optionGrid = keyboard.nextInt();
        } while (!(optionGrid == 2 || optionGrid == 3 || optionGrid == 5));

        board = new Board(optionGrid, optionGrid);
        graphic = new Graphic(optionGrid, optionGrid);

        return board;
    }

}
