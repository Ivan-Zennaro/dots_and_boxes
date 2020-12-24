import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestGraphic {

    @Test
    public void draw_2x2_empty_board(){
        Graphic graphic = new Graphic(2,2);
        String boardString_2x2 =
                "\n"+ " --- ---\n" +
                "|   |   |   \n" +
                " --- ---\n" +
                "|   |   |   \n" +
                " --- ---\n";
        Assertions.assertEquals(boardString_2x2,graphic.getStringBoard());
    }


    @Test
    public void update_red_move_in_2x2_board(){
        Graphic graphic = new Graphic(2,2);
        graphic.updateMove(new Move(0,0,Side.UP),new Player('A',Color.BLU));
        String boardString_2x2 =
                "\n"+ ColorManager.getColoredString(" ---",Color.BLU) +" ---\n" +
                        "|   |   |   \n" +
                        " --- ---\n" +
                        "|   |   |   \n" +
                        " --- ---\n";

        Assertions.assertEquals(boardString_2x2,graphic.getStringBoard());
    }

}
