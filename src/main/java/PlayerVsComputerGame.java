import java.util.Scanner;

public class PlayerVsComputerGame extends Game {

    @Override
    public void startGame() {
    }

    @Override
    public Board initializeBoard() {
        return null;
    }

    public boolean validCoordinate (int boardRow, int boardCols){
        return boardRow > 0 && boardCols > 0;
    }

}
