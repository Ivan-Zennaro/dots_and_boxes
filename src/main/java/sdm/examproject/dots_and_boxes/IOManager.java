package sdm.examproject.dots_and_boxes;

public abstract class IOManager {

    public abstract Move readMove();

    public abstract void updateMove(Move move, Player p);

    public abstract void updateCompletedBox(Move move, Player p);

    public abstract void updateGameInfo(Player currentPlayer);

    public abstract void showWinner();

    public abstract void errorHandler(String msg, boolean fatalError);

    protected final Player player1;
    protected final Player player2;
    protected final int mappedRows;
    protected final int mappedCols;
    protected boolean backPress = false;

    protected IOManager (int boardRows, int boardCols, Player p1, Player p2){
        this.player1 = p1;
        this.player2 = p2;

        this.mappedRows = boardRows * 2 + 1;
        this.mappedCols = boardCols + 1;
    }

    public boolean getBackPress() {
        return backPress;
    }

    protected static int getMappedY(Move move) {
        return move.getSide() == Side.RIGHT ? move.getY() + 1 : move.getY();
    }

    protected static int getMappedX(Move move) {
        int tempX = move.getX() * 2 + 1;
        if (move.getSide() == Side.UP)
            return tempX - 1;
        if (move.getSide() == Side.DOWN)
            return tempX + 1;
        else return tempX;
    }

    protected boolean isValidPositionInMatrix(int indexRow, int indexCol) {
        return isAHorizontalValidLineInMatrix(indexRow, indexCol) || isAVerticalValidLineInMatrix(indexRow, indexCol);
    }

    private boolean isAHorizontalValidLineInMatrix(int indexRow, int indexCol) {
        return indexRow % 2 == 0 && indexCol < mappedCols - 1;
    }

    private boolean isAVerticalValidLineInMatrix(int indexRow, int indexCol) {
        return indexRow % 2 != 0 && indexCol < mappedCols;
    }

}
