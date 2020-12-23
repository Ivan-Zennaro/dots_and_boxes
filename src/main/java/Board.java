public class Board {

    private Box[][] board;


    public Board(int numberOfBoxesInARow, int numberOfBoxesInAColumn) {
        for (int i = 0; i < numberOfBoxesInARow; i++) {
            for (int j = 0; j < numberOfBoxesInARow; j++) {
                board[i][j] = new Box();
            }
        }
    }

    public boolean boxHasAlreadyLine(Move move) {       //AGGIUNGERE TEST!
        return board[move.getX()][move.getY()].hasLineBySide(move.getSide());
    }


}