
public abstract class NewGame {

    protected Player player1;
    protected Player player2;
    protected Player currentPlayer;

    protected Board board;
    protected IOManager ioManager;

    public NewGame(int nRows, int nCols, Player p1, Player p2, IOManager ioManager) {
        this.player1 = this.currentPlayer = p1;
        this.player2 = p2;
        this.ioManager = ioManager;
        board = new Board(nRows, nCols);
    }

    public abstract void startGame();

    public void endGame() {
        ioManager.showWinner(player1, player2);
    }

    private boolean isMoveAllowed(Move move) {
        return board.isMoveInBoardRange(move) && move.getSide() != Side.INVALID && !board.boxHasAlreadyLine(move);
    }

    public void computeMove(Move move) {
        if (!isMoveAllowed(move)) return;
        board.drawLine(move);

        ioManager.updateMove(move, currentPlayer);

        boolean atLeastOnePointScoredByCurrentPlayer = false;

        if (board.isBoxCompleted(move)) {
            currentPlayer.onePointDone();
            ioManager.updateCompletedBox(move.getX(), move.getY(), currentPlayer);
            atLeastOnePointScoredByCurrentPlayer = true;
        }

        Move otherMove = board.getNeighbourSideMove(move);
        if (otherMove.getSide() != Side.INVALID && board.isBoxCompleted(otherMove)) {
            currentPlayer.onePointDone();
            ioManager.updateCompletedBox(otherMove.getX(), otherMove.getY(), currentPlayer);
            atLeastOnePointScoredByCurrentPlayer = true;
        }
        if (!atLeastOnePointScoredByCurrentPlayer) swapPlayers();
    }

    public void printScoreBoard() {
        ioManager.initialize();
        ioManager.updateGameInfo(player1, player2, currentPlayer);
    }

    public void swapPlayers() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    public boolean isGameFinished() {
        return player1.getPoints() + player2.getPoints() >= board.getBoardColumns() * board.getBoardRows();
    }

}
