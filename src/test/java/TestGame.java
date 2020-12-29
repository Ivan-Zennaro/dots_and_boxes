import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertAll;

public class TestGame {

    @Test
    public void game_is_not_end(){
        System.out.println("GAME HAS NOT ENDED TEST");
        TestingGame game = new TestingGame();
        Move move = new Move(0,0,Side.UP);

        game.setBoardSize(2);
        game.initializeBoard();
        game.turn(move);
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

    @Test
    public void game_one_points(){
        System.out.println("TEST GAME ONE POINT");
        TestingGame game = new TestingGame();
        game.setBoardSize(2);
        game.initializeBoard();

        game.turn("0 0 L");
        game.turn("0 0 U");
        game.turn("0 0 D");
        game.turn("0 1 L");


        Assertions.assertEquals(1,game.printCurrentPlayerScore());

    }

    @Test
    public void game_first_turn_player(){
        System.out.println("TEST GAME FIRST TURN");
        TestingGame game = new TestingGame();
        game.setBoardSize(2);
        game.initializeBoard();
        Assertions.assertEquals(game.player1.getId(),game.currentPlayer.getId());
    }

    @Test
    public void game_second_turn_player(){
        System.out.println("TEST GAME SECOND TURN");
        TestingGame game = new TestingGame();
        game.setBoardSize(2);
        game.initializeBoard();
        game.turn("0 0 L");
        Assertions.assertEquals(game.player2.getId(),game.currentPlayer.getId());
    }

    @Test
    public void  game_swap_players_after_one_point(){
        System.out.println("TEST GAME TURN AFTER POINT");
        TestingGame game = new TestingGame();
        game.setBoardSize(2);
        game.initializeBoard();
        game.turn("0 0 L");
        game.turn("0 0 U");
        game.turn("0 0 D");
        game.turn("0 1 L");

        assertAll(
                ()->Assertions.assertEquals(game.player2.getId(),game.currentPlayer.getId()),
                ()->Assertions.assertNotEquals(game.player1.getId(),game.currentPlayer.getId())
        );

    }
}
