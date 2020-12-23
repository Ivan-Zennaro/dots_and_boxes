import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TestBox {

    /*
    Does this test make some sense? to test the instance of an object....dont think so.
    [Dario]
     */
    //test1

    @Test
    public void empty_instance() {
        Box box = new Box();
        assertAll(
                () -> assertFalse(box.hasLineUp()),
                () -> assertFalse(box.hasLineDown()),
                () -> assertFalse(box.hasLineLeft()),
                () -> assertFalse(box.hasLineRight())
        );
    }

    @Test
    public void drawSomeLines() {
        Box box = new Box();
        box.drawLineUp();
        box.drawLineLeft();
        assertAll(
                () -> assertTrue(box.hasLineUp()),
                () -> assertFalse(box.hasLineDown()),
                () -> assertTrue(box.hasLineLeft()),
                () -> assertFalse(box.hasLineRight())
        );
    }





    private static Stream<Arguments> boxAndMoveGenerator() {

        return Stream.of(
                Arguments.of(new Box(true, true, false, false), new Move(1, 2, Side.UP)),
                Arguments.of(new Box(false, true, true, false), new Move(1, 2, Side.DOWN)),
                Arguments.of(new Box(false, true, false, false), new Move(1, 2, Side.LEFT))
                );
    }
    @ParameterizedTest
    @MethodSource("boxAndMoveGenerator")
    public void checkLineInBoxByMove(Box box,Move move) {
        assertTrue(box.hasLineBySide(move.getSide()));
    }

}
