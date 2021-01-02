public class Graphic {

    private static final String HORIZONTAL_LINE = " ---";
    private static final String VERTICAL_LINE = "|   ";

    private String graphicBoard[][];

    public Graphic(int boardRows, int boardCols) {
        int mappedRows = boardRows * 2 + 1;
        int mappedCols = boardCols + 1;
        graphicBoard = new String[mappedRows][mappedCols];
        for (int i = 0; i < mappedRows; i++) {
            for (int j = 0; j < mappedCols; j++) {
                if (i % 2 == 0)
                    graphicBoard[i][j] = HORIZONTAL_LINE;
                else
                    graphicBoard[i][j] = VERTICAL_LINE;
            }
        }
    }

    public void updateMove(Move move, Player player) {

        Color color = player.getColor();
        int mappedX = mapX(move);
        int mappedY = mapY(move);
        String stringToWrite;
        if (mappedX % 2 == 0)
            stringToWrite = ColorManager.getColoredString(HORIZONTAL_LINE, color);
        else
            stringToWrite = ColorManager.getColoredString(VERTICAL_LINE, color);
        graphicBoard[mappedX][mappedY] = stringToWrite;
    }

    public void addCompletedBox(int x, int y, char c) {
        graphicBoard[x * 2 + 1][y] = graphicBoard[x * 2 + 1][y].replace("   \u001B[0m", " \u001B[0m" + c + " ");
    }


    public static int mapY(Move move) {
        if (move.getSide() == Side.RIGHT)
            return move.getY() + 1;
        else return move.getY();
    }

    public static int mapX(Move move) {
        int tempX = move.getX() * 2 + 1;
        if (move.getSide() == Side.UP)
            return tempX - 1;
        if (move.getSide() == Side.DOWN)
            return tempX + 1;
        else return tempX;
    }

    public String getStringBoard() {
        String s = System.lineSeparator() + "    ";
        for (int i = 0; i < graphicBoard[0].length - 1; i++)
            s += i + "   ";
        for (int i = 0; i < graphicBoard.length; i++) {
            if (i % 2 == 0) s += System.lineSeparator() + "  ";
            else s += System.lineSeparator() + (((i + 1) / 2) - 1) + " ";
            for (int j = 0; (i % 2 == 0 && j < graphicBoard[0].length - 1) || (i % 2 != 0 && j < graphicBoard[0].length); j++) {
                s += graphicBoard[i][j];
            }
        }
        return s + System.lineSeparator();
    }

}


