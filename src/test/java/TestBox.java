import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TestBox {

    @Test
    public void is_empty_at_creation() {
        Box box = new Box();
        assertAll(
                () -> assertFalse(box.hasLineUp()),
                () -> assertFalse(box.hasLineDown()),
                () -> assertFalse(box.hasLineLeft()),
                () -> assertFalse(box.hasLineRight())
        );
    }

    @Test
    public void has_correct_lines_drawn() {
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
    public void has_line_drawn(Box box, Move move) {
        assertTrue(box.hasLineBySide(move.getSide()));
    }


    private static Stream<Arguments> boxAndNumberOfDrawnLinesGenerator() {
        return Stream.of(
                Arguments.of(new Box(true, true, true, true), 4),
                Arguments.of(new Box(false, true, true, true), 3),
                Arguments.of(new Box(true, false, false, false), 1),
                Arguments.of(new Box(false, false, false, false), 0)
        );
    }

    @ParameterizedTest
    @MethodSource("boxAndNumberOfDrawnLinesGenerator")
    public void has_correct_number_of_drawn_line(Box box, int n) {
        assertEquals(n, box.getNumberOfDrawnLine());
    }

}
