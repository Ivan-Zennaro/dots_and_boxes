
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertAll;

public class TestGameByInputStream {

    private final int boardSize = 2;
    private final Player player1 = new Player('A', Color.BLU);
    private final Player player2 = new Player('B', Color.RED);

    @Test
    public void game_run_from_input_stream_with_repeated_moves() {
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
        Cli cli = new Cli(boardSize, boardSize);
        TwoPlayersNewGame game = new TwoPlayersNewGame(2, 2, player1, player2, cli);

        //cli.initialize();
        game.startGame();
        //game.endGame();
        Assertions.assertTrue(game.isGameFinished());

    }
}


