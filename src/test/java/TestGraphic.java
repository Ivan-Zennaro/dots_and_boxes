import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestGraphic {

    @Test
    public void draw_2x2_empty_board(){
        Graphic graphic = new Graphic();
        String boardString_2x2 =
                " --- ---\n" +
                "|   |   |\n" +
                " --- ---\n" +
                "|   |   |\n" +
                " --- ---\n" +
                "\n";
        Board board = new Board(2,2);
        Assertions.assertEquals(boardString_2x2,graphict ad.getStringBoard(board));
    }


    /*
    @Test
    public void check_red_move_in_2x2_board(){
        String boardString_2x2 =
                " "+ ColorManager.getColoredString("---",Color.RED) +" ---\n" +
                        "|   |   |\n" +
                        " --- ---\n" +
                        "|   |   |\n" +
                        " --- ---\n" +
                        "\n";
        Board board = new Board(2,2);
        Assertions.assertEquals(boardString_2x2,Graphic.getStringBoard(board));
    }
*/

    /*@Test
    public void draw_3x3_empty_board(){
        String boardString_3x3 =
                " --- --- ---\n" +
                "|   |   |   |\n" +
                " --- --- ---\n" +
                "|   |   |   |\n" +
                " --- --- ---\n" +
                "|   |   |   |\n" +
                " --- --- ---\n";
        Board board = new Board(3,3);
        Assertions.assertEquals(boardString_3x3,Graphic.getStringBoard(board));
    }*/

}
