import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TestGui {

    @Test
    public void a_line_isnt_colored_at_init() {
        Player p1 = new Player('A', Color.RED);
        Player p2 = new Player('B', Color.BLU);

        Gui g = new Gui(3, 3, p1, p2);
        Move testMove = new Move(1, 1, Side.UP);
        Assertions.assertFalse(g.isSetLine(IOManager.getMappedX(testMove), IOManager.getMappedY(testMove)));
    }

    @Test
    public void line_colored_when_move_inserted() {
        Player p1 = new Player('A', Color.RED);
        Player p2 = new Player('B', Color.BLU);

        Gui g = new Gui(3, 3, p1, p2);
        Move testMove = new Move(1, 1, Side.UP);
        g.updateMove(testMove, p1);
        Assertions.assertTrue(g.isSetLine(IOManager.getMappedX(testMove), IOManager.getMappedY(testMove)));
    }

    @Test
    public void box_colored_when_requested() {

        Player p1 = new Player('A', Color.RED);
        Player p2 = new Player('B', Color.BLU);

        Gui g = new Gui(3, 3, p1, p2);
        Move moveThatCompletesABox = new Move(1, 1, Side.UP);
        int x = moveThatCompletesABox.getX();
        int y = moveThatCompletesABox.getX();
        g.updateCompletedBox(x, y, p1);

        Assertions.assertTrue( g.isSetBox(x, y) );
    }


}
