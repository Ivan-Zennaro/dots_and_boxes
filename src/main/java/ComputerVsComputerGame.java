public class ComputerVsComputerGame extends NewGame {

    private ComputerSolver computerSolver;

    public ComputerVsComputerGame(int nRows, int nCols, Player p1, Player p2, IOManager ioManager, int difficulty) {
        super(nRows, nCols, p1, p2, ioManager);
        this.computerSolver = new ComputerSolver(board,difficulty);
    }

    @Override
    public void startGame() {
        printScoreBoard();
        while (!isGameFinished()) {
            computeMove(computerSolver.getComputerMove());
            printScoreBoard();
        }
        endGame();
    }


}
