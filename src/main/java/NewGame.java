
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
    }

    public abstract void startGame();

    public void endGame() {
        finalGraphics();
        printWinner();
    }

    public void turn(String moveString){
        Move move = Move.parseMove(moveString);
        turn(move);
    }

    public void turn(Move move) {
        if (isMoveAllowed(move))
            computeMove(move);
        else{
            System.out.println("\nINVALID MOVE!!\n");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }

    private boolean isMoveAllowed(Move move) {
        return board.isMoveInBoardRange(move) && move.getSide() != Side.INVALID && !board.boxHasAlreadyLine(move);
    }


    public void computeMove(Move move) {
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

        if (!atLeastOnePointScoredByCurrentPlayer) {
            swapPlayers();
        }

    }

    public void printScoreBoard() {

        ioManager.initialize();
        System.out.println("Player " + player1.getId() + " got " + player1.getPoints() + " points");
        System.out.println("Player " + player2.getId() + " got " + player2.getPoints() + " points");
        System.out.println("Is the turn of Player" + currentPlayer.getId());
    }

    public int printCurrentPlayerScore(){
        return (currentPlayer.getPoints());
    }


    private void printWinner() {
        if (player1.getPoints() > player2.getPoints()) {
            System.out.println("Player" + player1.getId() + " WON!");
        } else if (player2.getPoints() > player1.getPoints()) {
            System.out.println("Player " + player2.getId() + " WON!");
        } else {
            System.out.println("TIE!");
        }
    }


    public void finalGraphics() {
        ioManager.initialize();
        System.out.println("Player " + player1.getId() + " got " + player1.getPoints() + " points");
        System.out.println("Player " + player2.getId() + " got " + player2.getPoints() + " points");
        System.out.println();
    }

    public void swapPlayers() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }


    public abstract Board initializeBoard();

    public boolean isGameFinished() {
        return player1.getPoints() + player2.getPoints() >= board.getBoardColumns() * board.getBoardRows();
    }

}
