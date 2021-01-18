
public abstract class Game {

    protected Player player1;
    protected Player player2;
    protected Player currentPlayer;

    protected Board board;
    protected IOManager ioManager;

    protected abstract void startGame();

    protected Game(int nRows, int nCols, Player p1, Player p2, IOManager ioManager) {
        this.player1 = this.currentPlayer = p1;
        this.player2 = p2;
        this.ioManager = ioManager;
        board = new Board(nRows, nCols);
    }

    protected void endGame() {
        ioManager.showWinner();
    }

    protected void computeMove(Move move) {
        if (board.isMoveAllowed(move)) {

            board.drawLine(move);
            ioManager.updateMove(move, currentPlayer);
            Move otherMove = board.getNeighbourSideMove(move);

            boolean atLeastOnePointScoredByCurrentPlayer =
                    boxOfTheMoveHasBeenClosed(move) | boxOfTheMoveHasBeenClosed(otherMove);

            if (!atLeastOnePointScoredByCurrentPlayer)
                swapPlayers();
        }
    }

    protected boolean boxOfTheMoveHasBeenClosed(Move move) {
        if (move.isValid() && board.isBoxCompleted(move)) {
            currentPlayer.onePointDone();
            ioManager.updateCompletedBox(move, currentPlayer);
            return true;
        }
        return false;
    }

    protected void printScoreBoard() {
        ioManager.updateGameInfo(currentPlayer);
    }

    protected void swapPlayers() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    protected boolean isGameFinished() {
        return player1.getPoints() + player2.getPoints() >= board.getBoardColumns() * board.getBoardRows();
    }

}
