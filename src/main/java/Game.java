
public abstract class Game {

    protected Player player1;
    protected Player player2;
    protected Player currentPlayer;

    protected Board board;
    protected Graphic graphic;

    public Game() {
        player1 = new Player('A', Color.BLU);
        player2 = new Player('B', Color.RED);
        currentPlayer = player1;
    }

    public abstract void startGame();

    public void endGame() {
        finalGraphics();
        printWinner();
    }


    public void turn(String stringMove) {
        Move move = Move.parseMove(stringMove);
        if (isMoveAllowed(move))
            computeMove(move);
    }

    private boolean isMoveAllowed(Move move) {
        return board.isMoveInBoardRange(move) && move.getSide() != Side.INVALID && !board.boxHasAlreadyLine(move);
    }


    public void computeMove(Move move) {
        board.drawLine(move);
        graphic.updateMove(move, currentPlayer);

        boolean atLeastOnePointScoredByCurrentPlayer = false;

        if (board.isBoxCompleted(move)) {
            currentPlayer.increasePoint(1);
            graphic.addCompletedBox(move.getX(), move.getY(), currentPlayer.getId());
            atLeastOnePointScoredByCurrentPlayer = true;
        }

        Move otherMove = board.getNeighbourSideMove(move);
        if (otherMove.getSide() != Side.INVALID && board.isBoxCompleted(otherMove)) {
            currentPlayer.increasePoint(1);
            graphic.addCompletedBox(otherMove.getX(), otherMove.getY(), currentPlayer.getId());
            atLeastOnePointScoredByCurrentPlayer = true;
        }

        if (!atLeastOnePointScoredByCurrentPlayer) {
            swapPlayers();
        }

    }

    public void printScoreBoard() {

        System.out.println(graphic.getStringBoard());
        System.out.println("Player " + player1.getId() + " got " + player1.getPoints() + " points");
        System.out.println("Player " + player2.getId() + " got " + player2.getPoints() + " points");
        System.out.println("Is the turn of Player" + currentPlayer.getId());
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
        System.out.println(graphic.getStringBoard());
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
