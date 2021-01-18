package sdm.examproject.dots_and_boxes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


public class TestGui {

    Player p1 = UtilityTest.getDummyP1();
    Player p2 = UtilityTest.getDummyP2();

    @ParameterizedTest
    @ValueSource(strings = {"UP", "DOWN", "LEFT", "RIGHT"})
    public void a_line_is_not_colored_at_init(Side side) {
        Gui g = new Gui(1, 1, p1, p2,"TEST");
        Move testMove = new Move(0, 0, side);
        Assertions.assertFalse(g.isSetLine(IOManager.getMappedX(testMove), IOManager.getMappedY(testMove)));
    }

    @ParameterizedTest
    @CsvSource({"0,0,LEFT", "1,1,RIGHT", "1,2,LEFT", "0,0,DOWN", "2,2,UP"})
    public void line_colored_when_move_inserted(int x, int y, Side side) {
        Gui g = new Gui(3, 3, p1, p2,"TEST");
        Move testMove = new Move(x, y, side);
        g.updateMove(testMove, p1);
        Assertions.assertTrue(g.isSetLine(IOManager.getMappedX(testMove), IOManager.getMappedY(testMove)));
    }

    @ParameterizedTest
    @CsvSource({"0,0,LEFT", "1,1,RIGHT", "1,2,LEFT", "0,0,DOWN", "2,2,UP"})
    public void box_colored_when_requested(int x, int y, Side side) {
        Gui g = new Gui(3, 3, p1, p2,"TEST");
        g.updateCompletedBox(new Move(x, y, side), p1);

        Assertions.assertTrue(g.isSetBox(x, y));
    }


}
