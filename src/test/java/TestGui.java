import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;


public class TestGui {

    @Test
    public void a_line_isnt_colored_at_init() {
        Player p1 = new Player('A', Color.RED);
        Player p2 = new Player('B', Color.BLU);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Gui g = new Gui(3, 3, frame, p1, p2);
        Move testMove = new Move(1, 1, Side.UP);
        Assertions.assertFalse(g.isSetEdge(IOManager.getMappedX(testMove), IOManager.getMappedY(testMove)));
    }

    @Test
    public void line_colored_when_move_inserted() {
        Player p1 = new Player('A', Color.RED);
        Player p2 = new Player('B', Color.BLU);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Gui g = new Gui(3, 3, frame, p1, p2);
/*        TwoPlayersNewGame t = new TwoPlayersNewGame(3,3, p1, p2, g);
        t.startGame();*/
        Move testMove = new Move(1, 1, Side.UP);
        g.updateMove(testMove, p1);
        Assertions.assertTrue(g.isSetEdge(IOManager.getMappedX(testMove), IOManager.getMappedY(testMove)));
    }
*/




}
