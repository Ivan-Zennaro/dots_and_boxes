import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGameComputer {

    Player p1 = UtilityTest.getMockP1();
    Player p2 = UtilityTest.getMockP2();

    @Test
    public void matrix_converted_into_list() {
        Integer[][] matrix = {{1, 2}, {3, 4}};
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        Assertions.assertEquals(list, ComputerSolver.matrixToList(matrix));
    }

    @Test
    public void get_missing_side_from_box() {
        assertAll(
                () -> assertEquals(Side.LEFT, ComputerSolver.getMissingSideFromBox(new Box(true, false, true, true))),
                () -> assertEquals(Side.RIGHT, ComputerSolver.getMissingSideFromBox(new Box(true, true, true, false))),
                () -> assertEquals(Side.DOWN, ComputerSolver.getMissingSideFromBox(new Box(true, true, false, true))),
                () -> assertEquals(Side.UP, ComputerSolver.getMissingSideFromBox(new Box(false, true, true, true))),
                () -> assertEquals(Side.INVALID, ComputerSolver.getMissingSideFromBox(new Box(false, false, true, true)))
        );
    }

    @Test
    public void draw_line_that_allow_to_close_two_consecutive_boxes() {
        ComputerVsComputerGame game = new ComputerVsComputerGame(3, 3, p1, p2, new Cli(3, 3, p1, p2));
        game.computeMove(new Move(0, 0, Side.UP));
        game.computeMove(new Move(0, 0, Side.DOWN));
        game.computeMove(new Move(0, 0, Side.LEFT));

        game.computeMove(new Move(2, 0, Side.LEFT));
        game.computeMove(new Move(2, 0, Side.DOWN));
        game.computeMove(new Move(2, 0, Side.UP));

        game.computeMove(new Move(2, 1, Side.RIGHT));
        game.computeMove(new Move(2, 1, Side.UP));
        Assertions.assertEquals(new Move(2, 0, Side.RIGHT), game.getComputerMove());
    }


    @Test
    public void draw_line_in_a_missing_position_of_a_box() {
        ComputerVsComputerGame game = new ComputerVsComputerGame(2, 2, p1, p2, new Cli(2, 2, p1, p2));
        game.computeMove(new Move(1, 1, Side.LEFT));
        game.computeMove(new Move(1, 1, Side.RIGHT));
        game.computeMove(new Move(1, 1, Side.DOWN));
        Assertions.assertEquals(new Move(1, 1, Side.UP), game.getComputerMove());
    }

    @Test
    public void draw_line_in_a_random_box_with_2_side_not_completed() {
        ComputerVsComputerGame game = new ComputerVsComputerGame(2, 2, p1, p2, new Cli(2, 2, p1, p2));
        game.computeMove(new Move(1, 1, Side.LEFT));
        game.computeMove(new Move(1, 1, Side.DOWN));
        game.computeMove(new Move(0, 0, Side.UP));
        game.computeMove(new Move(0, 0, Side.LEFT));
        game.computeMove(new Move(0, 1, Side.UP));
        game.computeMove(new Move(0, 1, Side.RIGHT));
        Move move = game.getComputerMove();
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, move.getX()),
                () -> Assertions.assertEquals(0, move.getY())
        );
    }

}
