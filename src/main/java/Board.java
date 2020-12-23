public class Board {

    private Box[][] board;


    public Board(int numberOfBoxesInARow, int numberOfBoxesInAColumn) {
        board = new Box[numberOfBoxesInARow][numberOfBoxesInAColumn];
        for (int i = 0; i < numberOfBoxesInARow; i++) {
            for (int j = 0; j < numberOfBoxesInAColumn; j++) {
                board[i][j] = new Box();
            }
        }
    }

    public boolean boxHasAlreadyLine(Move move) {       //AGGIUNGERE TEST!
        return board[move.getX()][move.getY()].hasLineBySide(move.getSide());
    }

    public void drawLine(Move move){
        //devo disegnare anche la linea nella box adiacente
        board[move.getX()][move.getY()].drawLine(move.getSide());
    }


}
