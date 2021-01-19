package sdm.examproject.dots_and_boxes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertAll;

public class TestGameComputer {

    private final Player p1 = UtilityTest.getDummyP1();
    private final Player p2 = UtilityTest.getDummyP2();

    @Test
    public void draw_line_that_limit_the_opponent_points() {
        DemoGame game = new DemoGame(3, 3, p1, p2, new CliGameManager(3, 3, p1, p2));

        game.computeMove(new Move(2, 0, Side.DOWN));
        game.computeMove(new Move(2, 1, Side.DOWN));
        game.computeMove(new Move(2, 2, Side.DOWN));

        game.computeMove(new Move(2, 0, Side.LEFT));
        game.computeMove(new Move(2, 1, Side.LEFT));
        game.computeMove(new Move(2, 2, Side.LEFT));
        game.computeMove(new Move(2, 2, Side.RIGHT));

        game.computeMove(new Move(1, 0, Side.DOWN));
        game.computeMove(new Move(1, 1, Side.DOWN));
        game.computeMove(new Move(1, 2, Side.DOWN));

        game.computeMove(new Move(1, 0, Side.LEFT));
        game.computeMove(new Move(1, 1, Side.LEFT));
        game.computeMove(new Move(1, 2, Side.LEFT));

        game.computeMove(new Move(0, 0, Side.RIGHT));
        game.computeMove(new Move(0, 2, Side.UP));
        game.computeMove(new Move(0, 2, Side.RIGHT));

        game.computeMove(new Move(0, 0, Side.DOWN));
        game.computeMove(new Move(0, 1, Side.DOWN));

        Move move = game.getComputerMove();

        assertAll(
                () -> Assertions.assertEquals(0, move.getX()),
                () -> Assertions.assertEquals(0, move.getY())
        );
    }


    @Test
    public void draw_line_in_a_missing_position_of_a_box() {
        DemoGame game = new DemoGame(2, 2, p1, p2, new CliGameManager(2, 2, p1, p2));
        game.computeMove(new Move(1, 1, Side.LEFT));
        game.computeMove(new Move(1, 1, Side.RIGHT));
        game.computeMove(new Move(1, 1, Side.DOWN));
        Assertions.assertEquals(new Move(1, 1, Side.UP), game.getComputerMove());
    }

    @Test
    public void draw_line_in_a_random_box_with_2_side_not_completed() {
        DemoGame game = new DemoGame(2, 2, p1, p2, new CliGameManager(2, 2, p1, p2));
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


    @Test
    public void draw_line_in_a_random_box_with_2_side_not_completed_also_for_the_neighbour() {
        DemoGame game = new DemoGame(2, 2, p1, p2, new CliGameManager(2, 2, p1, p2));
        game.computeMove(new Move(1, 0, Side.UP));
        game.computeMove(new Move(1, 0, Side.DOWN));
        game.computeMove(new Move(1, 0, Side.RIGHT));
        game.computeMove(new Move(1, 0, Side.LEFT));
        game.computeMove(new Move(1, 1, Side.DOWN));
        game.computeMove(new Move(0, 1, Side.UP));
        game.computeMove(new Move(0, 1, Side.RIGHT));
        Set<Move> possibleMove = new HashSet<>();
        while (possibleMove.size() < 1)
            possibleMove.add(game.getComputerMove());
        for (Move move : possibleMove) {
            Assertions.assertAll(
                    () -> Assertions.assertEquals(0, move.getX()),
                    () -> Assertions.assertEquals(0, move.getY()),
                    () -> Assertions.assertNotEquals(Side.DOWN, move.getSide()),
                    () -> Assertions.assertNotEquals(Side.RIGHT, move.getSide())
            );
        }
    }
}
