package sdm.examproject.dots_and_boxes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TestBox {

    @Test
    public void is_empty_at_creation() {
        Box box = new Box();
        assertAll(
                () -> Assertions.assertFalse(box.hasLineBySide(Side.UP)),
                () -> Assertions.assertFalse(box.hasLineBySide(Side.DOWN)),
                () -> Assertions.assertFalse(box.hasLineBySide(Side.LEFT)),
                () -> Assertions.assertFalse(box.hasLineBySide(Side.RIGHT))
        );
    }

    @Test
    public void has_up_and_left_lines_drawn() {
        Box box = new Box();
        box.drawLine(Side.UP);
        box.drawLine(Side.LEFT);
        assertAll(
                () -> Assertions.assertTrue(box.hasLineBySide(Side.UP)),
                () -> Assertions.assertFalse(box.hasLineBySide(Side.DOWN)),
                () -> Assertions.assertTrue(box.hasLineBySide(Side.LEFT)),
                () -> Assertions.assertFalse(box.hasLineBySide(Side.RIGHT))
        );
    }

    @Test
    public void has_down_and_right_lines_drawn() {
        Box box = new Box();
        box.drawLine(Side.DOWN);
        box.drawLine(Side.RIGHT);
        assertAll(
                () -> Assertions.assertFalse(box.hasLineBySide(Side.UP)),
                () -> Assertions.assertTrue(box.hasLineBySide(Side.DOWN)),
                () -> Assertions.assertFalse(box.hasLineBySide(Side.LEFT)),
                () -> Assertions.assertTrue(box.hasLineBySide(Side.RIGHT))
        );
    }

    private static Stream<Arguments> boxAndMoveGenerator() {


        return Stream.of(
                Arguments.of(new Box(new HashSet<>(Arrays.asList(Side.UP, Side.LEFT))) , new Move(1, 2, Side.UP)),
                Arguments.of(new Box(new HashSet<>(Arrays.asList(Side.DOWN, Side.LEFT))),  new Move(1, 2, Side.DOWN)),
                Arguments.of(new Box(new HashSet<>(Arrays.asList(Side.LEFT))), new Move(1, 2, Side.LEFT))
        );
    }

    @ParameterizedTest
    @MethodSource("boxAndMoveGenerator")
    public void has_line_drawn(Box box, Move move) {
        Assertions.assertTrue(box.hasLineBySide(move.getSide()));
    }


    private static Stream<Arguments> boxAndNumberOfDrawnLinesGenerator() {
        return Stream.of(
                Arguments.of(new Box(new HashSet<>(Arrays.asList(Side.UP, Side.LEFT, Side.RIGHT, Side.DOWN))), 4),
                Arguments.of(new Box(new HashSet<>(Arrays.asList(Side.LEFT, Side.RIGHT, Side.DOWN))), 3),
                Arguments.of(new Box(new HashSet<>(Arrays.asList(Side.UP))), 1),
                Arguments.of(new Box(), 0)
        );
    }

    @ParameterizedTest
    @MethodSource("boxAndNumberOfDrawnLinesGenerator")
    public void has_correct_number_of_drawn_line(Box box, int n) {
        Assertions.assertEquals(n, box.getNumberOfDrawnLine());
    }

}
