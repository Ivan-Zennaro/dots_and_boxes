import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertAll;

public class TestGame {

    @ParameterizedTest
    @ValueSource(strings = {"2","3","5"}) //Value Source quando ho solo un parametro
    public void check_input_grid_dimensions(String choice){
        ByteArrayInputStream in = new ByteArrayInputStream(choice.getBytes());
        System.setIn(in);
        Game game = new Game();
        Board board = game.initializeBoard();

        assertAll(
                () -> Assertions.assertEquals(Integer.parseInt(choice), board.getBoardRows()),
                () -> Assertions.assertEquals(Integer.parseInt(choice), board.getBoardColumns())
        );
    }

/*

    @Test
    public void game_is_not_end(){
        ByteArrayInputStream in = new ByteArrayInputStream("2".getBytes());
        System.setIn(in);
        Game game = new Game();
        game.initializeBoard();
        Assertions.assertEquals(false, game.isGameFinished());
    }

    @Test
    public void game_ended(){
        String inputString = "2\n\n0 0 L\n0 0 U\n0 0 D\n0 0 R\n0 1 U\n0 1 R\n0 1 D\n1 0 L\n1 0 R\n1 0 D\n1 1 D\n1 1 R\n";
        ByteArrayInputStream in = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(in);
        Game game = new Game();
        game.startGame();
        Assertions.assertTrue(game.isGameFinished());
    }
    @Test
    public void input_one_move(){
        String inputString = "2\n0 0 L\n";
        ByteArrayInputStream in = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(in);
        Move move = new Move(0 , 0 , Side.LEFT);
        Game game = new Game();
        game.startGame();
        board.get
        Assertions.assertTrue(game.isGameFinished());
    }
*/


}
