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
                System.out.println("Insert move [x y side:U,D,L,R]?");
                Input input= new Input();
                Move move1 = Move.parseMove(keyboard.nextLine());

                if(move1.getSide()!= Side.INVALID){
                    flagmove = true;
                }
                else if (isMoveInBoardRange(move1)){
                    flagmove = true;
                }
                //by there flagmove only says if the input is a valid line of the board

                //now we need to check if it is already written in the board...
                if (!board.boxHasAlreadyLine(move1)){
                    flagmove = true;
                }else{
                    //... and falsify the flag if the answer is yes
                    flagmove = false;}
                //maybe this test could be refactored
            }
        }
    }


    public boolean isMoveInBoardRange(Move move){
        return move.getX() < boxesInRow && move.getY() < boxesInColumn && move.getX() > 0 && move.getY() >0;
    }



}
