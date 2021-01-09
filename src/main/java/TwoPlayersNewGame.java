import java.lang.reflect.InvocationTargetException;

public class TwoPlayersNewGame extends NewGame {

    public TwoPlayersNewGame(int nRows, int nCols, Player p1, Player p2, IOManager ioManager) {
        super(nRows, nCols, p1, p2, ioManager);
    }
    public TwoPlayersNewGame(int nRows, int nCols, Player p1, Player p2, Class c) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        super(nRows, nCols, p1, p2, (IOManager) c.getDeclaredConstructors()[0].newInstance(nRows, nCols, p1, p2));
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
