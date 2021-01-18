package sdm.examproject.dots_and_boxes;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Cli extends IOManager {

    private final static String HORIZONTAL_LINE = " ---";
    private final static String VERTICAL_LINE = "|   ";

    private String[][] graphicBoard;
    private Scanner keyboard;

    public Cli(int boardRows, int boardCols, Player p1, Player p2) {

        super(boardRows,boardCols,p1,p2);

        keyboard = new Scanner(System.in);

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
    public void updateCompletedBox(Move move, Player player) {
        Color color = player.getColor();
        String coloredId = color.getColoredString(Character.toString(player.getFirstLetterName()));
        graphicBoard[move.getX() * 2 + 1 ][move.getY()] = graphicBoard[move.getX() * 2 + 1][move.getY()].replace("   \u001B[0m", " \u001B[0m" + coloredId + " ");
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
    public void errorHandler(String msg, boolean fatalError) {
        System.out.println(msg);
    }

    @Override
    public void updateGameInfo(Player currentPlayer) {
        System.out.println(getStringBoard());
        System.out.println(getStringPlayerPoint(player1));
        System.out.println(getStringPlayerPoint(player2));
        System.out.println("Is the turn of Player " + currentPlayer.getFirstLetterName());
    }

    public String getStringPlayerPoint (Player player){
        return "Player " + player.getFirstLetterName() + " got " + player.getPoints() + " points";
    }

    public String getStringBoard() {
        StringBuilder stringBuilder = new StringBuilder(System.lineSeparator() + "    ");
        for (int i = 0; i < graphicBoard[0].length - 1; i++)
            stringBuilder.append(i + "   ");
        for (int i = 0; i < graphicBoard.length; i++) {
            if (i % 2 == 0) stringBuilder.append(System.lineSeparator() + "  ");
            else stringBuilder.append(System.lineSeparator() + (((i + 1) / 2) - 1) + " ");
            for (int j = 0; isValidPositionInMatrix(i, j); j++) {
                stringBuilder.append(graphicBoard[i][j]);
            }
        }
        return stringBuilder.toString() + System.lineSeparator();
    }

}


