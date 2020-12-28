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

        Box choosenBox = board[move.getX()][move.getY()];
        choosenBox.drawLine(move.getSide());

        Move otherMove = getNeighbourSideMove(move);
        if (otherMove.getSide() != Side.INVALID) {
            Box otherBox = board[otherMove.getX()][otherMove.getY()];
            otherBox.drawLine(otherMove.getSide());
        }
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
    
    public Move getNeighbourSideMove(Move move){
        if (move.getSide() == Side.UP || move.getSide() == Side.DOWN)
            if (isMoveInBoardRange(new Move(move.getX() + move.getSide().coordShift(), move.getY(), move.getSide().invert()))) {
                return new Move (move.getX()+ move.getSide().coordShift(),move.getY() ,move.getSide().invert());

            }
        if (move.getSide() == Side.LEFT || move.getSide() == Side.RIGHT)
            if (isMoveInBoardRange(new Move(move.getX(), move.getY() + move.getSide().coordShift(), move.getSide().invert()))) {
                return new Move (move.getX(),move.getY() + move.getSide().coordShift(),move.getSide().invert());
            }

        return new Move(-1,-1,Side.INVALID);
    }

    public Box getBoxByMove(Move move) {
        return board[move.getX()][move.getY()];
    }

    public boolean isBoxCompleted(Move move){
        return getBoxByMove(move).isCompleted();
    }
}
