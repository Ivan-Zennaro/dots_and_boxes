import java.util.Scanner;

public class TwoPlayersNewGame extends NewGame {

    public TwoPlayersNewGame(int nRows, int nCols, Player p1, Player p2, IOManager ioManager) {
        super(nRows, nCols, p1, p2, ioManager);
    }

    @Override
    public void startGame() {

        while (!isGameFinished()) {
            printScoreBoard();
            computeMove(ioManager.readMove());
        }
        endGame();

    }



}
