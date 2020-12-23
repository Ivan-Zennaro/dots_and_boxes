import java.util.Scanner;

public class Game {

    //instantiating 2 players
    Player player1 =new Player('1',Color.BLU);
    Player player2 = new Player('2',Color.GREEN);

    /*instantiating a board 2*2. Usless usign Boxesinrow/Column?
    [Luca]*/
    int boxesInRow = 2;
    int boxesInColumn = 2;
    Board board = new Board(boxesInRow, boxesInColumn);

    public void setBoard(Board board) {
        this.board = board;
    }

    public void startGame(){
        Scanner keyboard = new Scanner(System.in);
        while(true){//fin a che la partita non è finita ciclo...
            boolean flagmove = false;

            while(!flagmove){
                System.out.println("Insert Input/Move?");
                Input input= new Input();
                Move move1 = input.parseMove(keyboard.nextLine());

                if(move1.getSide()!= Side.INVALID){
                    flagmove = true;
                }
                if (isMoveInBoardRange(move1)){
                    flagmove = true;
                }
                if (!board.boxHasAlreadyLine(move1)){
                    flagmove = true;
                }
            }
        }
    }


    public boolean isMoveInBoardRange(Move move){
        return move.getX() < boxesInRow && move.getY() < boxesInColumn;
    }



}
