import java.util.Scanner;

public class Game {

    private Player player1;
    private Player player2;
    private Board board;
    private Graphic graphic;
    private char[][] points;

    public Game() {
        player1 = new Player('A', Color.BLU);
        player2 = new Player('B', Color.RED);
    }

    public void startGame() throws Exception {

        Scanner keyboard = new Scanner(System.in);
        initializeBoard();

        Player currentPlayer = player1;
        Player otherPlayer = player2;
        Player temp;

        while (!isGameFinished()) {
            System.out.println(graphic.getStringBoard());
            System.out.println("Player " + player1.getId() + " got " + player1.getPoints() + " points");
            System.out.println("Player " + player2.getId() + " got " + player2.getPoints() + " points");
            System.out.println("Is the turn of Player" + currentPlayer.getId());

            Move move;
            do {
                System.out.println("Insert move [x y side:U,D,L,R]?");
                move = Move.parseMove(keyboard.nextLine());
            } while (!board.isMoveInBoardRange(move) || move.getSide() == Side.INVALID || board.boxHasAlreadyLine(move));

            board.drawLine(move);
            graphic.updateMove(move, currentPlayer);
            int points = board.returnPoints(move);

            currentPlayer.increasePoint(points);


            if (points == 0) {
                //SWAP PLAYERS
                temp = currentPlayer;
                currentPlayer = otherPlayer;
                otherPlayer = temp;
            } else if (points == 1) {
                Move otherMove = board.getNeighbourSideMove(move);
                if (!board.getNeighbourGetsPoint()) {
                    graphic.addCompletedBox(move.getX(), move.getY(), currentPlayer.getId());
                    setPoint(move.getX(), move.getY(), currentPlayer.getId());
                } else {
                    graphic.addCompletedBox(otherMove.getX(), otherMove.getY(), currentPlayer.getId());
                    setPoint(otherMove.getX() + otherMove.getSide().coordShift(), otherMove.getY(), currentPlayer.getId());
                }
            } else {

                graphic.addCompletedBox(move.getX(), move.getY(), currentPlayer.getId());
                setPoint(move.getX(), move.getY(), currentPlayer.getId());


                Move otherMove = board.getNeighbourSideMove(move);
                graphic.addCompletedBox(move.getX(), move.getY() + move.getSide().coordShift(), currentPlayer.getId());
                setPoint(move.getX(), move.getY() + move.getSide().coordShift(), currentPlayer.getId());


            }

        }


        System.out.println(graphic.getStringBoard());
        System.out.println("Player " + player1.getId() + " got " + player1.getPoints() + " points");
        System.out.println("Player " + player2.getId() + " got " + player2.getPoints() + " points");
        System.out.println();
        if (player1.getPoints() > player2.getPoints()) {
            System.out.println("Player" + player1.getId() + " WON!");
        } else if (player2.getPoints() > player1.getPoints()) {
            System.out.println("Player " + player2.getId() + " WON!");
        } else {
            System.out.println("TIE!");
        }
        keyboard.close();
    }


    public Board initializeBoard() {
        Scanner keyboard = new Scanner(System.in);
        int optionGrid;
        do {
            System.out.println("How big the grid? 2:[2x2]  3:[3x3] 5:[5x5]");
            optionGrid = keyboard.nextInt();
        } while (!(optionGrid == 2 || optionGrid == 3 || optionGrid == 5));

        board = new Board(optionGrid, optionGrid);
        graphic = new Graphic(optionGrid, optionGrid);
        points = new char[optionGrid][optionGrid];

        return board;
    }

    public boolean isGameFinished() {
        if (points == null || board == null) return false;
        boolean complete = true;
        for (int i = 0; i < points.length && complete; i++) {
            for (int j = 0; j < points[0].length && complete; j++) {
                if (points[i][j] == 0)
                    complete = false;//can we apply some break? this double for cycle seems inefficient, maybe streams will solve it
            }
        }
        return complete;
    }

    private void setPoint(int x, int y, char c) {
        points[x][y] = c;
    }
}
