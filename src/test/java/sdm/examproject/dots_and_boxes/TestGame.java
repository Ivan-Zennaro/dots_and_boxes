package sdm.examproject.dots_and_boxes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertAll;

public class TestGame {
    private final int boardSize = 2;
    private final Player player1 = UtilityTest.getDummyP1();
    private final Player player2 = UtilityTest.getDummyP2();
    private final Cli cli = new Cli(boardSize, boardSize, player1, player2);

    @Test
    public void is_not_ended() {
        Game game = new TwoPlayersGame(2, 2, player1, player2, cli);
        game.computeMove(new Move(0, 0, Side.UP));

        Assertions.assertFalse(game.isGameFinished());
    }

    @Test
    public void is_ended() {
        Game game = new TwoPlayersGame(2, 2, player1, player2, cli);

        game.computeMove(new Move(0, 0, Side.LEFT));
        game.computeMove(new Move(0, 0, Side.RIGHT));
        game.computeMove(new Move(0, 0, Side.UP));
        game.computeMove(new Move(0, 0, Side.DOWN));

        game.computeMove(new Move(0, 1, Side.UP));
        game.computeMove(new Move(0, 1, Side.DOWN));
        game.computeMove(new Move(0, 1, Side.RIGHT));

        game.computeMove(new Move(1, 1, Side.RIGHT));
        game.computeMove(new Move(1, 1, Side.DOWN));

        game.computeMove(new Move(1, 0, Side.DOWN));
        game.computeMove(new Move(1, 0, Side.LEFT));

        game.computeMove(new Move(1, 1, Side.LEFT));
        game.endGame();

        Assertions.assertTrue(game.isGameFinished());
    }

    @Test
    public void one_point_to_player2() {
        Game game = new TwoPlayersGame(2, 2, player1, player2, cli);

        game.computeMove(new Move(0, 0, Side.LEFT));
        game.computeMove(new Move(0, 0, Side.DOWN));
        game.computeMove(new Move(0, 0, Side.UP));
        game.computeMove(new Move(0, 1, Side.LEFT));

        Assertions.assertEquals(1, game.player2.getPoints());
    }

    @Test
    public void two_points_to_player1() {
        Game game = new TwoPlayersGame(2, 2, player1, player2, cli);

        game.computeMove(new Move(0, 0, Side.LEFT));
        game.computeMove(new Move(0, 0, Side.DOWN));
        game.computeMove(new Move(0, 0, Side.UP));

        game.computeMove(new Move(0, 1, Side.RIGHT));
        game.computeMove(new Move(0, 1, Side.DOWN));
        game.computeMove(new Move(0, 1, Side.UP));
        game.computeMove(new Move(0, 1, Side.LEFT));

        Assertions.assertEquals(2, game.player1.getPoints());
    }


    @Test
    public void player1_plays_first() {
        Game game = new TwoPlayersGame(2, 2, player1, player2, cli);
        Assertions.assertEquals(game.player1, game.currentPlayer);
    }

    @Test
    public void player2_plays_second() {
        Game game = new TwoPlayersGame(2, 2, player1, player2, cli);
        game.computeMove(new Move(0, 0, Side.LEFT));

        Assertions.assertEquals(game.player2, game.currentPlayer);
    }

    @Test
    public void game_does_not_swap_players_after_a_point() {
        Game game = new TwoPlayersGame(2, 2, player1, player2, cli);

        game.computeMove(new Move(0, 0, Side.LEFT));
        game.computeMove(new Move(0, 0, Side.DOWN));
        game.computeMove(new Move(0, 0, Side.UP));
        game.computeMove(new Move(0, 1, Side.LEFT));

        assertAll(
                () -> Assertions.assertEquals(game.player2, game.currentPlayer),
                () -> Assertions.assertNotEquals(game.player1, game.currentPlayer)
        );

    }


    @ParameterizedTest
    @CsvSource({"0,0,RIGHT,TRUE","0,0,DOWN,FALSE","0,1,DOWN,FALSE","1,1,UP,FALSE"})
    public void box_of_the_move_has_been_closed(int x, int y, Side side, boolean hasCloseABox) {
        Game game = new TwoPlayersGame(2, 2, player1, player2, cli);
        game.computeMove(new Move(0, 0, Side.UP));
        game.computeMove(new Move(0, 0, Side.LEFT));
        game.computeMove(new Move(0, 0, Side.DOWN));
        Move move = new Move(x,y,side);
        game.computeMove(move);
        Assertions.assertEquals(hasCloseABox, game.boxOfTheMoveHasBeenClosed(move));
    }


}
