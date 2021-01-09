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
        String inputString = "2"+System.lineSeparator()+
                                "0 0 L"+System.lineSeparator()+
                             "0 0 R"+System.lineSeparator()+
                "0 0 U"+System.lineSeparator()+
                "0 0 D"+System.lineSeparator()+
                "0 1 U"+System.lineSeparator()+
                "0 1 D"+System.lineSeparator()+
                "0 1 R"+System.lineSeparator()+
                "1 1 L" +System.lineSeparator()+
                "1 1 R" +System.lineSeparator()+
                "1 1 D" +System.lineSeparator()+
                "1 0 D" +System.lineSeparator()+
                "1 0 L"+System.lineSeparator();
        ByteArrayInputStream in = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(in);
        TwoPlayersGame game = new TwoPlayersGame();
        game.startGame();
        game.endGame();
        Assertions.assertTrue(game.isGameFinished());
    }

}
