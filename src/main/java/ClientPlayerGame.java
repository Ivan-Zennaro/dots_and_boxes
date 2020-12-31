import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

public class ClientPlayerGame extends Game {
    BufferedReader keyboard;

    public void startGameServer(BufferedReader input, BufferedWriter output, String quitCommand) throws IOException {
        initializeBoard(input, output);

        while (!isGameFinished()) {
            String command = input.readLine();

            if (command == null) {
                System.out.print(String.format("[%1$tY-%1$tm-%1$td %1$tT] ", System.currentTimeMillis()));
                System.out.println("Client abruptly closed connection ");
                break;
            }

            command = command.trim();
            if (command.equals(quitCommand)) {
                System.out.print(String.format("[%1$tY-%1$tm-%1$td %1$tT] ", System.currentTimeMillis()));
                System.out.println("Client disconnected");
                break;
            }
            printScoreBoard();
            System.out.println("Insert move [x y side:U,D,L,R]?");
            output.write("Insert move [x y side:U,D,L,R]?" + System.lineSeparator());
            output.flush();
            turn(keyboard.readLine());
            output.write(graphic.getStringBoard() + System.lineSeparator());
            output.flush();
        }
        endGame();
        keyboard.close();
    }


    public Board initializeBoard(BufferedReader inputClient, BufferedWriter outputClient) throws IOException {
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
