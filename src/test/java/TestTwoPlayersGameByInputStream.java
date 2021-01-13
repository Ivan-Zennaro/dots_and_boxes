
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertAll;

public class TestTwoPlayersGameByInputStream {

    private final Player p1 = UtilityTest.getMockP1();
    private final Player p2 = UtilityTest.getMockP2();

    @Test
    public void ended_with_correct_scores() {
        String inputString = """
                0 0 L
                0 0 R
                0 0 U
                0 0 D 
                0 1 U
                0 1 D
                0 1 R
                1 1 L
                1 1 R
                1 1 D
                1 0 D
                1 0 L""";
        System.setIn(new ByteArrayInputStream(inputString.getBytes()));
        Cli cli = new Cli(2, 2, p1, p2);
        TwoPlayersGame game = new TwoPlayersGame(2, 2, p1, p2, cli);
        game.startGame();

        assertAll(
                () -> Assertions.assertTrue(game.isGameFinished()),
                () -> Assertions.assertEquals(1, game.player1.getPoints()),
                () -> Assertions.assertEquals(3, game.player2.getPoints())
        );
    }

    @Test
    public void ended_even_with_repeated_moves() {
        String inputString = """
                0 0 L
                0 0 R
                0 0 U
                0 0 D
                0 0 D 
                0 1 U
                0 1 D
                0 1 R
                1 1 L
                1 1 R
                1 1 D
                1 0 D
                1 1 R
                1 0 L""";
        System.setIn(new ByteArrayInputStream(inputString.getBytes()));
        Cli cli = new Cli(2, 2, p1, p2);
        TwoPlayersGame game = new TwoPlayersGame(2, 2, p1, p2, cli);
        game.startGame();

        Assertions.assertTrue(game.isGameFinished());
    }
}


