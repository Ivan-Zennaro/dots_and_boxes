import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertAll;

public class TestGameByInputStream {
    @ParameterizedTest
    @ValueSource(strings = {"2","3","5"}) //Value Source quando ho solo un parametro
    public void check_input_grid_dimensions(String choice){
        ByteArrayInputStream in = new ByteArrayInputStream(choice.getBytes());
        System.setIn(in);
        Game game = new TwoPlayersGame();
        Board board = game.initializeBoard();

        assertAll(
                () -> Assertions.assertEquals(Integer.parseInt(choice), board.getBoardRows()),
                () -> Assertions.assertEquals(Integer.parseInt(choice), board.getBoardColumns())
        );
    }

    @Test
    public void game_run_from_input_stream(){
        String inputString = "2\n0 0 L\n0 0 R\n0 0 U\n0 0 D\n0 1 U\n0 1 D\n0 1 R\n1 1 L\n1 1 R\n1 1 D\n1 0 D\n1 0 L\n";
        ByteArrayInputStream in = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(in);
        TwoPlayersGame game = new TwoPlayersGame();
        game.startGame();
        game.endGame();
        Assertions.assertTrue(game.isGameFinished());
    }

}
