import java.util.Scanner;

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
        printStarter();
        Move move;
        move = Move.parseMove(stringMove);
        if (board.isMoveInBoardRange(move) && move.getSide() != Side.INVALID && !board.boxHasAlreadyLine(move))
            computeMove(move);
    }


    public void computeMove(Move move) {
        board.drawLine(move);
        graphic.updateMove(move, currentPlayer);

        boolean atLeastOnePointByCurrentPlayer = false;

        if (board.isBoxCompleted(move)) {
            currentPlayer.increasePoint(1);
            graphic.addCompletedBox(move.getX(), move.getY(), currentPlayer.getId());
            atLeastOnePointByCurrentPlayer = true;
        }
        Move otherMove = board.getNeighbourSideMove(move);
        if (otherMove.getSide() != Side.INVALID && board.isBoxCompleted(otherMove)) {
            currentPlayer.increasePoint(1);
            graphic.addCompletedBox(otherMove.getX(), otherMove.getY(), currentPlayer.getId());
            atLeastOnePointByCurrentPlayer = true;
        }

        if (!atLeastOnePointByCurrentPlayer) {
            swapPlayers();
        }

    }

    public void printStarter() {

        System.out.println(graphic.getStringBoard());
        System.out.println("Player " + player1.getId() + " got " + player1.getPoints() + " points");
        System.out.println("Player " + player2.getId() + " got " + player2.getPoints() + " points");
        System.out.println("Is the turn of Player" + currentPlayer.getId());
    }

    public void turn(Scanner keyboard) {
        printStarter();

        Move move;
        do {
            System.out.println("Insert move [x y side:U,D,L,R]?");
            move = Move.parseMove(keyboard.nextLine());
        } while (!board.isMoveInBoardRange(move) || move.getSide() == Side.INVALID || board.boxHasAlreadyLine(move));
        computeMove(move);

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
