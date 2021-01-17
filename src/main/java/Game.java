
public abstract class Game {

    protected Player player1;
    protected Player player2;
    protected Player currentPlayer;

    protected Board board;
    protected IOManager ioManager;

    public abstract void startGame();

    protected Game(int nRows, int nCols, Player p1, Player p2, IOManager ioManager) {
        this.player1 = this.currentPlayer = p1;
        this.player2 = p2;
        this.ioManager = ioManager;
        board = new Board(nRows, nCols);
    }

    public void endGame() {
        ioManager.showWinner();
    }

    protected boolean isMoveAllowed(Move move) {
        return board.isMoveInBoardRange(move) && move.getSide() != Side.INVALID && !board.boxHasAlreadyLine(move);
    }

    public void computeMove(Move move) {
        if (isMoveAllowed(move)) {

            board.drawLine(move);
            ioManager.updateMove(move, currentPlayer);
            Move otherMove = board.getNeighbourSideMove(move);

            boolean atLeastOnePointScoredByCurrentPlayer =
                    boxOfTheMoveHasBeenClosed(move) | boxOfTheMoveHasBeenClosed(otherMove);

            if (!atLeastOnePointScoredByCurrentPlayer)
                swapPlayers();
        }
    }

    public boolean boxOfTheMoveHasBeenClosed(Move move) {
        if (move.getSide() != Side.INVALID && board.isBoxCompleted(move)) {
            currentPlayer.onePointDone();
            ioManager.updateCompletedBox(move, currentPlayer);
            return true;
        }
        return false;
    }

    public void printScoreBoard() {
        ioManager.updateGameInfo(currentPlayer);
    }

    public void swapPlayers() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    public boolean isGameFinished() {
        return player1.getPoints() + player2.getPoints() >= board.getBoardColumns() * board.getBoardRows();
    }

}
