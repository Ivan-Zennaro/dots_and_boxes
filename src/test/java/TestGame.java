import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertAll;

public class TestGame {

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
    public void game_is_not_end(){
        System.out.println("GAME HAS NOT ENDED TEST");
        TestingGame game = new TestingGame();

        game.setBoardSize(2);
        game.initializeBoard();
        game.turn("0 0 U");
        Assertions.assertFalse(game.isGameFinished());
    }

    @Test
    public void game_ended(){
        System.out.println("GAME ENDED TEST");
        TestingGame game = new TestingGame();

        game.setBoardSize(2);
        game.initializeBoard();
        game.turn("0 0 L");

        game.turn("0 0 R");
        game.turn("0 0 U");
        game.turn("0 0 D");

        game.turn("0 0 R");
        game.turn("0 0 U");
        game.turn("0 0 D");
        game.turn("0 1 U");
        game.turn("0 1 D");
        game.turn("0 1 R");
        game.turn("1 1 L");
        game.turn("1 1 R");
        game.turn("1 1 D");
        game.turn("1 0 D");
        game.turn("1 0 L");
        game.endGame();

        Assertions.assertTrue(game.isGameFinished());
    }

    /*
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

    @ParameterizedTest
    @CsvSource({"5,2,TRUE", "-1,2,FALSE","1,1,TRUE","0,0,FALSE","0,1,FALSE"})
    public void board_cols_and_row_are_valid(int boardRow, int boardCols, boolean validity){
        PlayerVsComputerGame game = new PlayerVsComputerGame();
        Assertions.assertEquals(validity,game.validCoordinate(boardRow,boardCols));
    }

}
