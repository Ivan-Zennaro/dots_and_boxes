import java.util.Scanner;

public class Game {

    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Board board;
    private Graphic graphic;
    private char[][] points;

    public Game() {
        player1 = new Player('A', Color.BLU);
        player2 = new Player('B', Color.RED);
        currentPlayer = player1;
    }

    public void startGame(){

        Scanner keyboard = new Scanner(System.in);
        initializeBoard();



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

            boolean atLeastOnePointByCurrentPlayer = false;

            if( board.isBoxCompleted(move) ){
                currentPlayer.increasePoint(1);
                graphic.addCompletedBox(move.getX(), move.getY(), currentPlayer.getId());
                atLeastOnePointByCurrentPlayer = true;
            }
            Move otherMove = board.getNeighbourSideMove(move);
            if( otherMove.getSide() != Side.INVALID && board.isBoxCompleted(otherMove)){
                currentPlayer.increasePoint(1);
                graphic.addCompletedBox(otherMove.getX(), otherMove.getY(), currentPlayer.getId());
                atLeastOnePointByCurrentPlayer = true;
            }

            if(!atLeastOnePointByCurrentPlayer){
                swapPlayers();
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

    private void swapPlayers() {
        if(currentPlayer == player1) {
            currentPlayer = player2;
        }else{
            currentPlayer = player1;
        }
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
        return player1.getPoints() + player2.getPoints() >= board.getBoardColumns() * board.getBoardRows();
    }

}
