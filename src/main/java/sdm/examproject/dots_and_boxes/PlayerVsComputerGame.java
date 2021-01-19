package sdm.examproject.dots_and_boxes;

public class PlayerVsComputerGame extends Game {

    private final ComputerSolver computerSolver;

    public PlayerVsComputerGame(int nRows, int nCols, Player p1, Player p2, IOManager ioManager, Difficulty difficulty) {
        super(nRows, nCols, p1, p2, ioManager);
        this.computerSolver = new ComputerSolver(board, difficulty);
    }

    @Override
    public void startGame() {
        printScoreBoard();
        while (!isGameFinished()) {
            if (currentPlayer == player1)
                computeMove(ioManager.readMove());
            else
                computeMove(computerSolver.getComputerMove());
            printScoreBoard();
            try{
                Thread.sleep(500);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        endGame();
    }
}
