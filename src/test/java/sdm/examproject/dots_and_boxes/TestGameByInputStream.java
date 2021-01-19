package sdm.examproject.dots_and_boxes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.assertAll;

public class TestGameByInputStream {

    private final Player p1 = UtilityTest.getDummyP1();
    private final Player p2 = UtilityTest.getDummyP2();


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

        Game game = GameFactory.create2PlayerGameWithCli(2, 2, p1, p2);
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
        Game game = GameFactory.create2PlayerGameWithCli(2, 2, p1, p2);
        game.startGame();

        Assertions.assertTrue(game.isGameFinished());
    }
}


