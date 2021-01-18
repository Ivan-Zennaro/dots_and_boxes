package sdm.examproject.dots_and_boxes;

public class TwoPlayersGame extends Game {

    public TwoPlayersGame(int nRows, int nCols, Player p1, Player p2, IOManager ioManager) {
        super(nRows, nCols, p1, p2, ioManager);
    }

    @Override
    public void startGame() {
        printScoreBoard();
        while (!isGameFinished()) {
            computeMove(ioManager.readMove());
            printScoreBoard();
        }
        endGame();
    }
}
