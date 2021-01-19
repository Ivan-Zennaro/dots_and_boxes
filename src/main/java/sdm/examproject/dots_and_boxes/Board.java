package sdm.examproject.dots_and_boxes;

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

    public void drawLine(Move move) {
        Box chosenBox = getBoxByMove(move);
        chosenBox.drawLine(move.getSide());

        Move otherMove = getNeighbourSideMove(move);
        if (otherMove.isValid()) {
            Box otherBox = getBoxByMove(otherMove);
            otherBox.drawLine(otherMove.getSide());
        }
    }

    public boolean isMoveAllowed(Move move) {
        return isMoveInBoardRange(move) &&
                move.isValid() &&
                !boxHasAlreadyLine(move);
    }

    private boolean boxHasAlreadyLine(Move move) {
        Box chosenBox = boxes[move.getX()][move.getY()];
        return chosenBox.hasLineBySide(move.getSide());
    }

    private boolean isMoveInBoardRange(Move move) {
        int x = move.getX(), y = move.getY();
        return x < boardRows && y < boardColumns && x >= 0 && y >= 0;
    }

    public Move getNeighbourSideMove(Move move) {
        Move neighbourMove = move.getNeighbourMove();
        return isMoveInBoardRange(neighbourMove) ? neighbourMove : Move.getInvalidMove();
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
