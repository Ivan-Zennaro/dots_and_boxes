import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGameComputer {

    Player p1 = new Player('A', Color.RED);
    Player p2 = new Player('B', Color.BLU);

    @Test
    public void matrix_converted_into_list() {
        Integer[][] matrix = {{1, 2}, {3, 4}};
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Assertions.assertEquals(list, ComputerGame.matrixToList(matrix));
    }

    @Test
    public void get_missing_side_from_box() {
        assertAll(
                () -> assertEquals(Side.LEFT, ComputerGame.getMissingSideFromBox(new Box(true, false, true, true))),
                () -> assertEquals(Side.RIGHT, ComputerGame.getMissingSideFromBox(new Box(true, true, true, false))),
                () -> assertEquals(Side.DOWN, ComputerGame.getMissingSideFromBox(new Box(true, true, false, true))),
                () -> assertEquals(Side.UP, ComputerGame.getMissingSideFromBox(new Box(false, true, true, true))),
                () -> assertEquals(Side.INVALID, ComputerGame.getMissingSideFromBox(new Box(false, false, true, true)))
        );
    }


    @Test
    public void draw_line_in_a_missing_position_of_a_box() {
        ComputerGame game = new ComputerGame(2, 2, p1, p2, new Cli(2, 2));
        game.computeMove(new Move(1, 1, Side.LEFT));
        game.computeMove(new Move(1, 1, Side.RIGHT));
        game.computeMove(new Move(1, 1, Side.DOWN));
        Assertions.assertEquals(new Move(1, 1, Side.UP), game.getComputerMove());
    }

    @Test
    public void draw_line_in_a_random_box_with_not_2_side_completed() {
        ComputerGame game = new ComputerGame(2, 2, p1, p2, new Cli(2, 2));
        game.computeMove(new Move(1, 1, Side.LEFT));
        game.computeMove(new Move(1, 1, Side.DOWN));
        game.computeMove(new Move(0, 0, Side.UP));
        game.computeMove(new Move(0, 0, Side.LEFT));
        game.computeMove(new Move(0, 1, Side.UP));
        game.computeMove(new Move(0, 1, Side.RIGHT));
        Move move = game.getComputerMove();
        Assertions.assertEquals(1, move.getX());
        Assertions.assertEquals(0, move.getY());
    }

}
