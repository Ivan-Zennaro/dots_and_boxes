public class Board {

    private Box[][] board;
    private int boardRows;
    private int boardColumns;



    public Board(int numberOfBoxesInARow, int numberOfBoxesInAColumn) {
        board = new Box[numberOfBoxesInARow][numberOfBoxesInAColumn];
        this.boardRows = numberOfBoxesInARow;
        this.boardColumns = numberOfBoxesInAColumn;
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

    public void drawLine(Move move) {
        //devo disegnare anche la linea nella box adiacente
        Box choosenBox = board[move.getX()][move.getY()];
        choosenBox.drawLine(move.getSide());


        Box otherBox = choosenBox;


        boolean flagModifyOtherBox = false;
        if (move.getSide() == Side.UP || move.getSide() == Side.DOWN)
            if (isMoveInBoardRange(new Move(move.getX() + move.getSide().coordShift(), move.getY(), move.getSide().invert()))) {
                otherBox = board[move.getX() + move.getSide().coordShift()][move.getY()];
                flagModifyOtherBox = true;
            }
        if (move.getSide() == Side.LEFT || move.getSide() == Side.RIGHT)
            if (isMoveInBoardRange(new Move(move.getX(), move.getY() + move.getSide().coordShift(), move.getSide().invert()))) {
                otherBox = board[move.getX()][move.getY() + move.getSide().coordShift()];
                flagModifyOtherBox = true;
            }

        if (flagModifyOtherBox)
            otherBox.drawLine(move.getSide().invert());
    }

    public boolean isMoveInBoardRange(Move move) {
        return move.getX() < boardRows && move.getY() < boardColumns && move.getX() >= 0 && move.getY() >= 0;
    }
    public int getBoardRows() {
        return boardRows;
    }
    public int getBoardColumns() {
        return boardColumns;
    }


}
