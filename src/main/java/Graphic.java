public class Graphic {

    private static final String HORIZZONTAL_LINE = " ---";
    private static final String VERTICAL_LINE = "|   ";

    private String graphicBoard[][];

    public Graphic(int boardRows, int boardCols) {
        int mappedRows = boardRows * 2 + 1;
        int mappedCols = boardCols + 1;
        graphicBoard = new String[mappedRows][mappedCols];
        for (int i = 0; i < mappedRows; i++) {
            for (int j = 0; j < mappedCols; j++) {
                if (i % 2 == 0)
                    graphicBoard[i][j] = HORIZZONTAL_LINE;
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
            stringToWrite = ColorManager.getColoredString(HORIZZONTAL_LINE, color);
        else
            stringToWrite = ColorManager.getColoredString(VERTICAL_LINE, color);

        graphicBoard[mappedX][mappedY] = stringToWrite;
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
        String s = "\n";
        for (int i = 0; i < graphicBoard.length; i++) {
            for (int j = 0; (i % 2 == 0 && j < graphicBoard[0].length - 1) || (i % 2 != 0 && j < graphicBoard[0].length); j++) {
                s += graphicBoard[i][j];
            }
            s += "\n";
        }
        return s;
    }

}
