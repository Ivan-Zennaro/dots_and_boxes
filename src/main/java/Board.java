public class Board {

    private Box[][] board;
    private int boardRows;
    private int boardColumns;
    private boolean neighbourGetsPoint = false;


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
        Box otherBox = choosenBox;

        boolean modifyOtherBox = false;
        if (move.getSide() == Side.UP || move.getSide() == Side.DOWN)
            if (isMoveInBoardRange(new Move(move.getX() + move.getSide().coordShift(), move.getY(), move.getSide().invert()))) {
                otherBox = board[move.getX() + move.getSide().coordShift()][move.getY()];
                modifyOtherBox = true;
            }
        if (move.getSide() == Side.LEFT || move.getSide() == Side.RIGHT)
            if (isMoveInBoardRange(new Move(move.getX(), move.getY() + move.getSide().coordShift(), move.getSide().invert()))) {
                otherBox = board[move.getX()][move.getY() + move.getSide().coordShift()];
                modifyOtherBox = true;
            }

        if (modifyOtherBox)
            otherBox.drawLine(move.getSide().invert());
    }

    public int returnPoints(Move move) {

        int points = 0;


        Box choosenBox = board[move.getX()][move.getY()];
        if (choosenBox.isCompleted())
            points += 1;
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

        if (flagModifyOtherBox) {
            otherBox.drawLine(move.getSide().invert());
            if (otherBox.isCompleted()) {
                points += 1;
                neighbourGetsPoint = true;
            }

        }

        return points;
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

    public boolean getNeighbourGetsPoint() {
        return neighbourGetsPoint;
    }


}
