import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestGraphic {

    @Test
    public void draw_2x2_board(){
        String boardString_2x2 =
                " --- ---\n" +
                "|   |   |\n" +
                " --- ---\n" +
                "|   |   |\n" +
                " --- ---\n" +
                "\n";
        Board board = new Board(2,2);
        Assertions.assertEquals(boardString_2x2,Graphic.getStringBoard(board));
    }

}
