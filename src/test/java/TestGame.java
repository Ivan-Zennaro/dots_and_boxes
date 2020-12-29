import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;

public class TestGame {

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

}
