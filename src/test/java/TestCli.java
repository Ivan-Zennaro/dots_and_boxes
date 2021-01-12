import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestCli {

    Player p1 = UtilityTest.getMockP1();
    Player p2 = UtilityTest.getMockP2();

    @Test
    public void draw_1x1_empty_board() {
        Cli cli = new Cli(1, 1, p1, p2);
        String boardString_1x1 =
                System.lineSeparator() +
                        "    0   " + System.lineSeparator() +
                        "   ---" + System.lineSeparator() +
                        "0 |   |   " + System.lineSeparator() +
                        "   ---" + System.lineSeparator();
        Assertions.assertEquals(boardString_1x1, cli.getStringBoard());
    }

    @Test
    public void draw_2x2_empty_board() {
        Cli cli = new Cli(2, 2, p1, p2);
        String boardString_2x2 =
                System.lineSeparator() +
                        "    0   1   " + System.lineSeparator() +
                        "   --- ---" + System.lineSeparator() +
                        "0 |   |   |   " + System.lineSeparator() +
                        "   --- ---" + System.lineSeparator() +
                        "1 |   |   |   " + System.lineSeparator() +
                        "   --- ---" + System.lineSeparator();
        Assertions.assertEquals(boardString_2x2, cli.getStringBoard());
    }

    @Test
    public void draw_3x3_empty_board() {
        Cli cli = new Cli(3, 3, p1, p2);
        String boardString_3x3 =
                System.lineSeparator() + "    0   1   2   " + System.lineSeparator() +
                        "   --- --- ---" + System.lineSeparator() +
                        "0 |   |   |   |   " + System.lineSeparator() +
                        "   --- --- ---" + System.lineSeparator() +
                        "1 |   |   |   |   " + System.lineSeparator() +
                        "   --- --- ---" + System.lineSeparator() +
                        "2 |   |   |   |   " + System.lineSeparator() +
                        "   --- --- ---" + System.lineSeparator();
        Assertions.assertEquals(boardString_3x3, cli.getStringBoard());
    }


    @Test
    public void update_blu_move_in_2x2_board() {
        Cli cli = new Cli(2, 2, p1, p2);
        cli.updateMove(new Move(0, 0, Side.UP), new Player('A', Color.BLU));
        String boardString_2x2 =
                System.lineSeparator() +
                        "    0   1   " + System.lineSeparator() +
                        "  " + Color.BLU.getColoredString(" ---") + " ---" + System.lineSeparator() +
                        "0 |   |   |   " + System.lineSeparator() +
                        "   --- ---" + System.lineSeparator() +
                        "1 |   |   |   " + System.lineSeparator() +
                        "   --- ---" + System.lineSeparator();

        Assertions.assertEquals(boardString_2x2, cli.getStringBoard());
    }

    @Test
    public void update_blu_move_in_3x3_board() {
        Cli cli = new Cli(3, 3, p1, p2);
        cli.updateMove(new Move(2, 2, Side.RIGHT), new Player('A', Color.BLU));
        String boardString_3x3 = System.lineSeparator() +
                "    0   1   2   " + System.lineSeparator() +
                "   --- --- ---" + System.lineSeparator() +
                "0 |   |   |   |   " + System.lineSeparator() +
                "   --- --- ---" + System.lineSeparator() +
                "1 |   |   |   |   " + System.lineSeparator() +
                "   --- --- ---" + System.lineSeparator() +
                "2 |   |   |   " + Color.BLU.getColoredString("|   ") + System.lineSeparator() +
                "   --- --- ---" + System.lineSeparator();

        Assertions.assertEquals(boardString_3x3, cli.getStringBoard());
    }


    @Test
    public void update_blu_move_in_3x3_board_and_draw_a_taken_box() {
        Cli cli = new Cli(3, 3,p1 ,p2);
        Player player = new Player('A', Color.GREEN);
        cli.updateMove(new Move(0, 0, Side.DOWN), player);
        cli.updateMove(new Move(0, 0, Side.LEFT), player);
        cli.updateCompletedBox(0, 0, player);
        String boardString_3x3 =
                System.lineSeparator() + "    0   1   2   " + System.lineSeparator() +
                        "   --- --- ---" + System.lineSeparator() +
                        "0 " + Color.GREEN.getColoredString("| ") + Color.GREEN.getColoredString("A") + " |   |   |   " + System.lineSeparator() +
                        "  " + Color.GREEN.getColoredString(" ---") + " --- ---" + System.lineSeparator() +
                        "1 |   |   |   |   " + System.lineSeparator() +
                        "   --- --- ---" + System.lineSeparator() +
                        "2 |   |   |   |   " + System.lineSeparator() +
                        "   --- --- ---" + System.lineSeparator();

        Assertions.assertEquals(boardString_3x3, cli.getStringBoard());
    }


    @ParameterizedTest
    @CsvSource({"1,UP,1", "2,DOWN,2", "3,RIGHT,4", "5,LEFT,5"})
    public void map_y_value_of_game_board_with_y_value_of_graphic_board(int gameBoardY, Side side, int graphicBoardY) {
        Assertions.assertEquals(graphicBoardY, Cli.getMappedY(new Move(0, gameBoardY, side)));
    }

    @ParameterizedTest
    @CsvSource({"0,UP,0", "2,DOWN,6", "1,RIGHT,3", "2,UP,4"})
    public void map_x_value_of_game_board_with_x_value_of_graphic_board(int gameBoardX, Side side, int graphicBoardX) {
        Assertions.assertEquals(graphicBoardX, Cli.getMappedX(new Move(gameBoardX, 0, side)));
    }

    @ParameterizedTest
    @CsvSource({"0,2,2", "2,5,2", "1,1,0", "6,3,1"})
    public void check_if_the_winner_is_correct(int points1, int points2, int indexWinner) {

        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        Player player1 = new Player('A', Color.BLU);
        while (points1-- > 0) player1.onePointDone();

        Player player2 = new Player('B', Color.RED);
        while (points2-- > 0) player2.onePointDone();

        String winnerString = "";

        switch (indexWinner) {
            case 1:
                winnerString = "Player " + player1.getId() + " WON!";
                break;
            case 2:
                winnerString = "Player " + player2.getId() + " WON!";
                break;
            default:
                winnerString = "TIE!";
        }
        new Cli(0, 0,player1,player2).showWinner();
        Assertions.assertEquals(winnerString, outputStreamCaptor.toString().trim());
    }
}
