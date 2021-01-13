import java.util.Scanner;
import java.util.stream.IntStream;

public class Cli extends IOManager {

    private final String HORIZONTAL_LINE = " ---";
    private final String VERTICAL_LINE = "|   ";

    private String graphicBoard[][];
    private Scanner keyboard;

    public Cli(int boardRows, int boardCols, Player p1, Player p2) {
        keyboard = new Scanner(System.in);

        player1 = p1;
        player2 = p2;

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
            graphicBoard[mappedX][mappedY] = color.getColoredString(HORIZONTAL_LINE);
        else
            graphicBoard[mappedX][mappedY] = color.getColoredString(VERTICAL_LINE);
    }

    @Override
    public void updateCompletedBox(int x, int y, Player player) {
        Color color = player.getColor();
        String coloredId = color.getColoredString(Character.toString(player.getFirstLetterName()));
        graphicBoard[x * 2 + 1][y] = graphicBoard[x * 2 + 1][y].replace("   \u001B[0m", " \u001B[0m" + coloredId + " ");
    }


    @Override
    public Move readMove() {
        System.out.println("Insert move [x y side:U,D,L,R]?");
        return Move.parseMove(keyboard.nextLine());
    }

    @Override
    public void showWinner() {
        if (player1.getPoints() == player2.getPoints())
            System.out.println("TIE!");
        else {
            Player winner = (player1.getPoints() > player2.getPoints() ? player1 : player2);
            System.out.println("Player " + winner.getFirstLetterName() + " WON!");
        }
    }

    @Override
    public void errorHandler(String msg) {
        System.out.println(msg);
    }

    @Override
    public void updateGameInfo(Player currentPlayer) {
        System.out.println(getStringBoard());
        System.out.println("Player " + player1.getFirstLetterName() + " got " + player1.getPoints() + " points");
        System.out.println("Player " + player2.getFirstLetterName() + " got " + player2.getPoints() + " points");
        System.out.println("Is the turn of Player" + currentPlayer.getFirstLetterName());
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


