public class Board {

    private Box[][] board;
    int boxRows;
    int boxColumns;

    public Board(int numberOfBoxesInARow, int numberOfBoxesInAColumn) {
        board = new Box[numberOfBoxesInARow][numberOfBoxesInAColumn];
        this.boxRows = numberOfBoxesInARow;
        this.boxColumns = numberOfBoxesInAColumn;
        for (int i = 0; i < numberOfBoxesInARow; i++) {
            for (int j = 0; j < numberOfBoxesInAColumn; j++) {
                board[i][j] = new Box();
            }
        }
    }

    public boolean boxHasAlreadyLine(Move move) {       //AGGIUNGERE TEST!
        Box choosenBox = board[move.getX()][move.getY()];
        return choosenBox.hasLineBySide(move.getSide());
    }

    public void drawLine(Move move){
        //devo disegnare anche la linea nella box adiacente
        Box choosenBox = board[move.getX()][move.getY()];
        choosenBox.drawLine(move.getSide());
        Box otherBox = choosenBox;


        boolean flag = false;
            if(move.getSide() == Side.UP || move.getSide() == Side.DOWN)
                if(isMoveInBoardX(move.getX()+move.getSide().new_coord())){
                     otherBox = board[move.getX()+move.getSide().new_coord()][move.getY()];
                     flag = true;
              }
            if(move.getSide() == Side.LEFT || move.getSide() == Side.RIGHT)
                 if(isMoveInBoardY(move.getY()+move.getSide().new_coord())){
                     otherBox = board[move.getX()][move.getY()+move.getSide().new_coord()];
                     flag = true;
                 }
                 if (flag)
                        otherBox.drawLine(move.getSide().invert());
    }
    public boolean isMoveInBoardRange(Move move){
        return move.getX() < boxRows && move.getY() < boxColumns && move.getX() > 0 && move.getY() >0;
    }
    public boolean isMoveInBoardX(int coord){
        return coord < boxRows && coord > 0 ;
    }
    public boolean isMoveInBoardY(int coord){
        return  coord < boxColumns && coord > 0 ;
    }

}
