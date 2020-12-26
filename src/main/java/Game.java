import java.util.Scanner;

public class Game {

    private Player player1;
    private Player player2;
    private Board board;
    private char [][] points;

    public Game() {
        player1 = new Player('1', Color.BLU);
        player2 = new Player('2', Color.GREEN);
    }

    public void startGame() {

        Scanner keyboard = new Scanner(System.in);
        initializeBoard();

        Player currentPlayer = player1;

        while (!isGameFinished()) {

            System.out.println ("Is the turn of " + currentPlayer.getId());

            Move move;
            do{
                System.out.println("Insert move [x y side:U,D,L,R]?");
                move = Move.parseMove(keyboard.nextLine());
            }while (!board.isMoveInBoardRange(move) || move.getSide() == Side.INVALID || board.boxHasAlreadyLine(move));

            board.drawLine(move);

            //bisogna controllare che la nuova mossa abbia generato un quadrato


        }
    }


    public Board initializeBoard() {
        Scanner keyboard = new Scanner(System.in);
        int optionGrid;
        do {
            System.out.println("How big the grid? 2:[2x2]  3:[3x3] 5:[5x5]");
            optionGrid = Integer.parseInt(keyboard.nextLine());
        } while (!(optionGrid == 2 || optionGrid == 3 || optionGrid == 5));

        board = new Board(optionGrid, optionGrid);
        points = new char[optionGrid][optionGrid];
        keyboard.close();
        return board;
    }

    public boolean isGameFinished() {
        if (points == null || board == null) return false;
        boolean complete = true;
        for (int i = 0; i < points.length && complete; i++) {
            for (int j = 0; j < points[0].length && complete; j++) {
                if (points[i][j] == 0)
                    complete = false;
            }
        }
        return complete;
    }

    private void setPoint (int x, int y, char c){
        points[x][y] = c;
    }
}
