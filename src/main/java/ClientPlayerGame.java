import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class ClientPlayerGame extends Game {
    BufferedReader keyboard;

    public void startGameServer(BufferedReader input) throws IOException {
        initializeBoard(input);

        while (!isGameFinished()) {
            printScoreBoard();
            System.out.println("Insert move [x y side:U,D,L,R]?");
            turn(keyboard.readLine());
        }
        endGame();
        keyboard.close();
    }


    public Board initializeBoard(BufferedReader inputClient) throws IOException {
        keyboard = new BufferedReader(inputClient);

        int optionGrid = 1;
        do {
            System.out.println("How big the grid? 2:[2x2]  3:[3x3] 5:[5x5]");
            try {
                optionGrid = Integer.parseInt(keyboard.readLine());
            }catch (Exception e){
                e.getStackTrace();
            }
        } while (!(optionGrid == 2 || optionGrid == 3 || optionGrid == 5));

        board = new Board(optionGrid, optionGrid);
        graphic = new Graphic(optionGrid, optionGrid);

        return board;
    }

    public void startGame(){
        return;
    }
    public Board initializeBoard(){
        Board board1 = new Board(1, 1);
        return board1;
    }
}
