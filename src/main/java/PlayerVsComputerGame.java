import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PlayerVsComputerGame extends Game {

    @Override
    public void startGame() {

        initializeBoard();

        Scanner keyboard = new Scanner(System.in);

        while (!isGameFinished()) {
            printScoreBoard();
            if (currentPlayer == player1) {
                System.out.println("Insert move [x y side:U,D,L,R]?");
                turn(keyboard.nextLine());
            }
            else {
                turn("");
            }
        }
        keyboard.close();
    }

    @Override
    public Board initializeBoard() {
        Scanner keyboard = new Scanner(System.in);
        int boardRow, boardCols;
        do {
            System.out.println("Insert boardRow:");
            boardRow = keyboard.nextInt();
            System.out.println("Insert boardCols:");
            boardCols = keyboard.nextInt();
        } while (!validCoordinate(boardRow,boardCols));

        board = new Board(boardRow, boardCols);
        graphic = new Graphic(boardRow, boardCols);

        keyboard.close();
        return board;
    }

    public boolean validCoordinate (int boardRow, int boardCols){
        return boardRow > 0 && boardCols > 0;
    }

    public Move getComputerMove(){
        List<Box> boxes = new ArrayList<>();

        return null;
    }

    public static <T> List<T> matrixToList(T[][] twoDArray) {
        List<T> list = new ArrayList<T>();
        for (T[] array : twoDArray) {
            list.addAll(Arrays.asList(array));
        }
        return list;
    }
}
