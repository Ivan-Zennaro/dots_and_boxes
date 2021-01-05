import java.util.stream.IntStream;

public class Cmd extends IOManager {

    private String graphicBoard[][];
    private static final String HORIZONTAL_LINE = " ---";
    private static final String VERTICAL_LINE = "|   ";

    public Cmd(int boardRows, int boardCols) {
        int mappedRows = boardRows * 2 + 1;
        int mappedCols = boardCols + 1;

        graphicBoard = IntStream.range(0, mappedRows)
                .mapToObj(r -> IntStream.range(0, mappedCols)
                        .mapToObj(c -> r % 2 == 0 ? HORIZONTAL_LINE : VERTICAL_LINE)
                        .toArray(String[]::new))
                .toArray(String[][]::new);
    }

    @Override
    public void updateMove(Move move, Player player) {
        Color color = player.getColor();
        int mappedX = getMappedX(move);
        int mappedY = getMappedY(move);
        if (mappedX % 2 == 0)
            graphicBoard[mappedX][mappedY] = ColorManager.getColoredString(HORIZONTAL_LINE, color);
        else
            graphicBoard[mappedX][mappedY] = ColorManager.getColoredString(VERTICAL_LINE, color);
        System.out.println(getStringBoard());
    }

    @Override
    public void updateCompletedBox(int x, int y, Player player) {
        Color color = player.getColor();
        String coloredId = ColorManager.getColoredString(Character.toString(player.getId()),color);
        graphicBoard[x * 2 + 1][y] = graphicBoard[x * 2 + 1][y].replace("   \u001B[0m", " \u001B[0m" + coloredId + " ");
        System.out.println(getStringBoard());
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

    @Override
    public Move readMove() {
        return null;
    }

    @Override
    public void showWinner(Player p1, Player p2) {
        if (p1.getPoints() == p2.getPoints())
            System.out.println("TIE!");
        else {
            Player winner = (p1.getPoints() > p2.getPoints() ? p1 : p2);
            System.out.println("Player " + winner.getId() + " WON!");
        }
    }

    @Override
    public void updateGameInfo(Player p1, Player p2, Player currentPlayer) {
        System.out.println("Player " + p1.getId() + " got " + p1.getPoints() + " points");
        System.out.println("Player " + p2.getId() + " got " + p2.getPoints() + " points");
        System.out.println("Is the turn of Player" + currentPlayer.getId());
    }
}


