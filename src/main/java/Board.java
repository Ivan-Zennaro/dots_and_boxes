public class Board {

    private Box[][] boxes;
    private int boardRows;
    private int boardColumns;

    public Board(int numberOfBoxesInARow, int numberOfBoxesInAColumn) {
        this.boxes = new Box[numberOfBoxesInARow][numberOfBoxesInAColumn];
        this.boardRows = numberOfBoxesInARow;
        this.boardColumns = numberOfBoxesInAColumn;
        for (int i = 0; i < numberOfBoxesInARow; i++) {
            for (int j = 0; j < numberOfBoxesInAColumn; j++) {
                boxes[i][j] = new Box();
            }
        }
    }

    public boolean boxHasAlreadyLine(Move move) {
        Box chosenBox = boxes[move.getX()][move.getY()];
        return chosenBox.hasLineBySide(move.getSide());
    }

    public void drawLine(Move move) {
        Box chosenBox = getBoxByMove(move);
        chosenBox.drawLine(move.getSide());

        Move otherMove = getNeighbourSideMove(move);
        if (otherMove.getSide() != Side.INVALID) {
            Box otherBox = getBoxByMove(otherMove);
            otherBox.drawLine(otherMove.getSide());
        }
    }

    public boolean isMoveInBoardRange(Move move) {
        return move.getX() < boardRows && move.getY() < boardColumns && move.getX() >= 0 && move.getY() >= 0;
    }

    public Move getNeighbourSideMove(Move move) {

        if (move.isSideHorizontal()) {
            Move neighbourHorizontalMove = new Move(move.getX() + move.getCoordShift(), move.getY(), move.getInvertedSide());
            if (isMoveInBoardRange(neighbourHorizontalMove)) {
                return neighbourHorizontalMove;
            }
        }

        if (move.isSideVertical()) {
            Move neighbourVerticalMove = new Move(move.getX(), move.getY() + move.getCoordShift(), move.getInvertedSide());
            if (isMoveInBoardRange(neighbourVerticalMove)) {
                return neighbourVerticalMove;
            }
        }

        return Move.getInvalidMove();
    }

    public Box getBoxByMove(Move move) {
        return boxes[move.getX()][move.getY()];
    }

    public boolean isBoxCompleted(Move move) {
        return getBoxByMove(move).isCompleted();
    }

    public Box[][] getBoxes() {
        return boxes;
    }

    public int getBoardRows() {
        return boardRows;
    }

    public int getBoardColumns() {
        return boardColumns;
    }
}
