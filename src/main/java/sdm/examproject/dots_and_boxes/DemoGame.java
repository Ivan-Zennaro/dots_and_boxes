package sdm.examproject.dots_and_boxes;

public class DemoGame extends Game {

    private final ComputerSolver computerSolver;

    public DemoGame(int nRows, int nCols, Player p1, Player p2, IOManager ioManager) {
        super(nRows, nCols, p1, p2, ioManager);
        this.computerSolver = new ComputerSolver(board, Difficulty.HARD);
    }

    @Override
    public void startGame() {
        printScoreBoard();
        while (!isGameFinished()) {
            computeMove(getComputerMove());
            printScoreBoard();
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        endGame();
    }

    public Move getComputerMove() {
        return computerSolver.getComputerMove();
    }

}
