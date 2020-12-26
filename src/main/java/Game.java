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

        //richiesta righe e colonne della griglia
        //MOLTO APPROSSIMATA
        initializeBoard();


        while (true) {       //fin a che la partita non è finita ciclo...
            boolean flagmove = false;

            while (!flagmove) {
                System.out.println("Insert move [x y side:U,D,L,R]?");
                Move move1 = Move.parseMove(keyboard.nextLine());

                if (move1.getSide() != Side.INVALID) {
                    flagmove = true;
                } else if (board.isMoveInBoardRange(move1)) {
                    flagmove = true;
                }
                //by there flagmove only says if the input is a valid line of the board

                //now we need to check if it is already written in the board...
                if (!board.boxHasAlreadyLine(move1)) {
                    flagmove = true;
                } else {
                    //... and falsify the flag if the answer is yes
                    flagmove = false;
                }
                //maybe this test could be refactored
            }
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
        if (points == null) return false;
        boolean complete = true;
        for (int i = 0; i < points.length && complete; i++) {
            for (int j = 0; j < points[0].length && complete; j++) {
                if (points[i][j] == 0)
                    complete = false;
            }
        }
        return complete;
    }
}
