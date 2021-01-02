import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestGraphic {

    @Test
    public void draw_1x1_empty_board() {
        Graphic graphic = new Graphic(1, 1);
        String boardString_1x1 =
                System.lineSeparator() +
                        "    0   "+System.lineSeparator() +
                        "   ---"+System.lineSeparator() +
                        "0 |   |   "+System.lineSeparator() +
                        "   ---"+System.lineSeparator();
        Assertions.assertEquals(boardString_1x1, graphic.getStringBoard());
    }

    @Test
    public void draw_2x2_empty_board() {
        Graphic graphic = new Graphic(2, 2);
        String boardString_2x2 =
                System.lineSeparator() +
                        "    0   1   "+System.lineSeparator() +
                        "   --- ---"+System.lineSeparator() +
                        "0 |   |   |   "+System.lineSeparator() +
                        "   --- ---"+System.lineSeparator() +
                        "1 |   |   |   "+System.lineSeparator() +
                        "   --- ---"+System.lineSeparator();
        Assertions.assertEquals(boardString_2x2, graphic.getStringBoard());
    }

    @Test
    public void draw_3x3_empty_board() {
        Graphic graphic = new Graphic(3, 3);
        String boardString_3x3 =
                System.lineSeparator() + "    0   1   2   "+System.lineSeparator() +
                        "   --- --- ---"+System.lineSeparator() +
                        "0 |   |   |   |   "+System.lineSeparator() +
                        "   --- --- ---"+System.lineSeparator() +
                        "1 |   |   |   |   "+System.lineSeparator() +
                        "   --- --- ---"+System.lineSeparator() +
                        "2 |   |   |   |   "+System.lineSeparator() +
                        "   --- --- ---"+System.lineSeparator();
        Assertions.assertEquals(boardString_3x3, graphic.getStringBoard());
    }


    @Test
    public void update_blu_move_in_2x2_board() {
        Graphic graphic = new Graphic(2, 2);
        graphic.updateMove(new Move(0, 0, Side.UP), new Player('A', Color.BLU));
        String boardString_2x2 =
                System.lineSeparator() +
                        "    0   1   "+System.lineSeparator() +
                        "  "+ ColorManager.getColoredString(" ---",Color.BLU) + " ---"+System.lineSeparator() +
                        "0 |   |   |   "+System.lineSeparator() +
                        "   --- ---"+System.lineSeparator() +
                        "1 |   |   |   "+System.lineSeparator() +
                        "   --- ---"+System.lineSeparator();

        Assertions.assertEquals(boardString_2x2, graphic.getStringBoard());
    }

    @Test
    public void update_blu_move_in_3x3_board() {
        Graphic graphic = new Graphic(3, 3);
        graphic.updateMove(new Move(2, 2, Side.RIGHT), new Player('A', Color.BLU));
        String boardString_3x3 = System.lineSeparator() +
                "    0   1   2   "+System.lineSeparator() +
                "   --- --- ---"+System.lineSeparator() +
                "0 |   |   |   |   "+System.lineSeparator() +
                "   --- --- ---"+System.lineSeparator() +
                "1 |   |   |   |   "+System.lineSeparator() +
                "   --- --- ---"+System.lineSeparator() +
                "2 |   |   |   " + ColorManager.getColoredString("|   ", Color.BLU) + System.lineSeparator() +
                "   --- --- ---"+System.lineSeparator();

        Assertions.assertEquals(boardString_3x3, graphic.getStringBoard());
    }



    @Test
    public void update_blu_move_in_3x3_board_and_draw_a_taken_box() {
        Graphic graphic = new Graphic(3, 3);
        Player player = new Player('A', Color.GREEN);
        graphic.updateMove(new Move(0, 0, Side.DOWN), player);
        graphic.updateMove(new Move(0, 0, Side.LEFT), player);
        graphic.addCompletedBox(0, 0, player.getId());
        String boardString_3x3 =
                System.lineSeparator() +"    0   1   2   " +  System.lineSeparator() +
                        "   --- --- ---" + System.lineSeparator() +
                        "0 " +ColorManager.getColoredString("| ", Color.GREEN) +"A" + " |   |   |   "+ System.lineSeparator() +
                        "  " + ColorManager.getColoredString(" ---", Color.GREEN) + " --- ---" + System.lineSeparator() +
                        "1 |   |   |   |   " + System.lineSeparator() +
                        "   --- --- ---" + System.lineSeparator() +
                        "2 |   |   |   |   " + System.lineSeparator() +
                        "   --- --- ---" + System.lineSeparator() ;

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
