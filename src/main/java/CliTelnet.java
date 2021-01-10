import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.IntStream;

public class CliTelnet extends IOManager {

    private final String HORIZONTAL_LINE = " ---";
    private final String VERTICAL_LINE = "|   ";

    private String graphicBoard[][];

    BufferedReader keyboard;
    BufferedWriter outputServer;

    public CliTelnet(int boardRows, int boardCols, BufferedReader br, BufferedWriter bw) {
        keyboard = br;
        outputServer = bw;

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
            graphicBoard[mappedX][mappedY] = Color.getColoredString(HORIZONTAL_LINE, color);
        else
            graphicBoard[mappedX][mappedY] = Color.getColoredString(VERTICAL_LINE, color);
    }

    @Override
    public void updateCompletedBox(int x, int y, Player player) {
        Color color = player.getColor();
        String coloredId = Color.getColoredString(Character.toString(player.getId()), color);
        graphicBoard[x * 2 + 1][y] = graphicBoard[x * 2 + 1][y].replace("   \u001B[0m", " \u001B[0m" + coloredId + " ");
    }

    public Move readMove(String move) {
        return Move.parseMove(move);
    }

    @Override
    public void showWinner(Player p1, Player p2) {
        try {
            if (p1.getPoints() == p2.getPoints()) {
                outputServer.write("TIE!" + System.lineSeparator());
                outputServer.flush();
            } else {
                Player winner = (p1.getPoints() > p2.getPoints() ? p1 : p2);
                outputServer.write("Player " + winner.getId() + " WON!" + System.lineSeparator());
                outputServer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateGameInfo(Player p1, Player p2, Player currentPlayer) {
        try {
            outputServer.write("Player " + p1.getId() + " got " + p1.getPoints() + " points" + System.lineSeparator());
            outputServer.flush();
            outputServer.write("Player " + p2.getId() + " got " + p2.getPoints() + " points" + System.lineSeparator());
            outputServer.flush();
            outputServer.write("Is the turn of Player" + currentPlayer.getId() + System.lineSeparator());
            outputServer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Move readMove() {
        return null;
    }

    @Override
    public void initialize() {
        try {
            outputServer.write(getStringBoard());
            outputServer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

