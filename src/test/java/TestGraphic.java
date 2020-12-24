import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestGraphic {

    @Test
    public void draw_2x2_empty_board() {
        Graphic graphic = new Graphic(2, 2);
        String boardString_2x2 =
                "\n" +
                        "    0   1   \n" +
                        "   --- ---\n" +
                        "0 |   |   |   \n" +
                        "   --- ---\n" +
                        "1 |   |   |   \n" +
                        "   --- ---\n";
        Assertions.assertEquals(boardString_2x2, graphic.getStringBoard());
    }

    @Test
    public void draw_3x3_empty_board() {
        Graphic graphic = new Graphic(3, 3);
        String boardString_3x3 =
                "\n" + "    0   1   2   \n" +
                        "   --- --- ---\n" +
                        "0 |   |   |   |   \n" +
                        "   --- --- ---\n" +
                        "1 |   |   |   |   \n" +
                        "   --- --- ---\n" +
                        "2 |   |   |   |   \n" +
                        "   --- --- ---\n";
        Assertions.assertEquals(boardString_3x3, graphic.getStringBoard());
    }


    @Test
    public void update_blu_move_in_2x2_board() {
        Graphic graphic = new Graphic(2, 2);
        graphic.updateMove(new Move(0, 0, Side.UP), new Player('A', Color.BLU));
        String boardString_2x2 =
                "\n" +
                        "    0   1   \n" +
                        "  "+ ColorManager.getColoredString(" ---",Color.BLU) + " ---\n" +
                        "0 |   |   |   \n" +
                        "   --- ---\n" +
                        "1 |   |   |   \n" +
                        "   --- ---\n";

        Assertions.assertEquals(boardString_2x2, graphic.getStringBoard());
    }

    @Test
    public void update_blu_move_in_3x3_board() {
        Graphic graphic = new Graphic(3, 3);
        graphic.updateMove(new Move(2, 2, Side.RIGHT), new Player('A', Color.BLU));
        String boardString_3x3 = "\n" +
                "    0   1   2   \n" +
                "   --- --- ---\n" +
                "0 |   |   |   |   \n" +
                "   --- --- ---\n" +
                "1 |   |   |   |   \n" +
                "   --- --- ---\n" +
                "2 |   |   |   " + ColorManager.getColoredString("|   ", Color.BLU) + "\n" +
                "   --- --- ---\n";

        Assertions.assertEquals(boardString_3x3, graphic.getStringBoard());
    }


    @Test
    public void update_blu_move_in_3x3_board_and_draw_a_taken_box() {
        Graphic graphic = new Graphic(3, 3);
        Player player = new Player('A', Color.GREEN);
        graphic.updateMove(new Move(0, 0, Side.DOWN), player);
        graphic.addCopletedBox(0, 0, player.getId());
        String boardString_3x3 =
                "\n    0   1   2   \n" +
                        "   --- --- ---\n" +
                        "0 | A |   |   |   \n" +
                        "  " + ColorManager.getColoredString(" ---", Color.GREEN) + " --- ---\n" +
                        "1 |   |   |   |   \n" +
                        "   --- --- ---\n" +
                        "2 |   |   |   |   \n" +
                        "   --- --- ---\n";

        Assertions.assertEquals(boardString_3x3, graphic.getStringBoard());
    }


    @ParameterizedTest
    @CsvSource({"1,UP,1", "2,DOWN,2", "3,RIGHT,4", "5,LEFT,5"})
    public void map_y_value_of_game_board_with_y_value_of_graphic_board(int gameBoardY, Side side, int graphicBoardY) {
        Assertions.assertEquals(graphicBoardY, Graphic.mapY(new Move(0, gameBoardY, side)));
    }

    @ParameterizedTest
    @CsvSource({"0,UP,0", "2,DOWN,6", "1,RIGHT,3", "2,UP,4"})
    public void map_x_value_of_game_board_with_x_value_of_graphic_board(int gameBoardX, Side side, int graphicBoardX) {
        Assertions.assertEquals(graphicBoardX, Graphic.mapX(new Move(gameBoardX, 0, side)));
    }
}
