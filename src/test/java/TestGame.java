import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;

public class TestGame {
    private final int boardSize = 2;
    private final Player player1 = new Player("A", Color.BLU);
    private final Player player2 = new Player("B", Color.RED);
    private final Cli cli = new Cli(boardSize, boardSize, player1, player2);
    private final TwoPlayersGame game = new TwoPlayersGame(2, 2, player1, player2, cli);

    @Test
    public void is_not_ended() {
        game.computeMove(new Move(0, 0, Side.UP));

        Assertions.assertFalse(game.isGameFinished());
    }

    @Test
    public void is_ended() {
        game.computeMove(new Move(0, 0, Side.LEFT));
        game.computeMove(new Move(0, 0, Side.RIGHT));
        game.computeMove(new Move(0, 0, Side.UP));
        game.computeMove(new Move(0, 0, Side.DOWN));


        game.computeMove(new Move(0, 0, Side.LEFT));
        game.computeMove(new Move(0, 0, Side.RIGHT));
        game.computeMove(new Move(0, 0, Side.UP));
        game.computeMove(new Move(0, 0, Side.DOWN));

        game.computeMove(new Move(0, 1, Side.UP));
        game.computeMove(new Move(0, 1, Side.DOWN));
        game.computeMove(new Move(0, 1, Side.RIGHT));

        game.computeMove(new Move(1, 1, Side.LEFT));
        game.computeMove(new Move(1, 1, Side.RIGHT));
        game.computeMove(new Move(1, 1, Side.DOWN));

        game.computeMove(new Move(1, 0, Side.DOWN));
        game.computeMove(new Move(1, 0, Side.LEFT));
        game.endGame();

        Assertions.assertTrue(game.isGameFinished());
    }

    @Test
    public void one_point_to_player2() {
        game.computeMove(new Move(0, 0, Side.LEFT));
        game.computeMove(new Move(0, 0, Side.DOWN));
        game.computeMove(new Move(0, 0, Side.UP));
        game.computeMove(new Move(0, 1, Side.LEFT));

        Assertions.assertEquals(1, game.player2.getPoints());

    }

    @Test
    public void player1_plays_first() {
        Assertions.assertEquals(game.player1, game.currentPlayer);
    }

    @Test
    public void player2_plays_second() {
        game.computeMove(new Move(0, 0, Side.LEFT));

        Assertions.assertEquals(game.player2, game.currentPlayer);
    }

    @Test
    public void game_swap_players_after_one_point() {
        game.computeMove(new Move(0, 0, Side.LEFT));
        game.computeMove(new Move(0, 0, Side.DOWN));
        game.computeMove(new Move(0, 0, Side.UP));
        game.computeMove(new Move(0, 1, Side.LEFT));

        assertAll(
                () -> Assertions.assertEquals(game.player2, game.currentPlayer),
                () -> Assertions.assertNotEquals(game.player1, game.currentPlayer)
        );

    }
}
