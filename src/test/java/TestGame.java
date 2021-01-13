import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;

public class TestGame {
    private final int boardSize = 2;
    private final Player player1 = new Player("A",Color.BLU);
    private final Player player2 = new Player("B",Color.RED);
    private final Cli cli = new Cli(boardSize,boardSize,player1, player2);
    private final GameStub game = new GameStub(2,2,player1,player2,cli);

    @Test
    public void game_is_not_end(){
        System.out.println("GAME HAS NOT ENDED TEST");

        game.computeMoveByString("0 0 U");
        Assertions.assertFalse(game.isGameFinished());
    }

    @Test
    public void game_ended(){
        System.out.println("GAME ENDED TEST");



        game.computeMoveByString("0 0 L");

        game.computeMoveByString("0 0 R");
        game.computeMoveByString("0 0 U");
        game.computeMoveByString("0 0 D");

        game.computeMoveByString("0 0 R");
        game.computeMoveByString("0 0 U");
        game.computeMoveByString("0 0 D");
        game.computeMoveByString("0 1 U");
        game.computeMoveByString("0 1 D");
        game.computeMoveByString("0 1 R");
        game.computeMoveByString("1 1 L");
        game.computeMoveByString("1 1 R");
        game.computeMoveByString("1 1 D");
        game.computeMoveByString("1 0 D");
        game.computeMoveByString("1 0 L");
        game.endGame();

        Assertions.assertTrue(game.isGameFinished());
    }

    @Test
    public void game_one_points(){
        System.out.println("TEST GAME ONE POINT");


        game.computeMoveByString("0 0 L");
        game.computeMoveByString("0 0 U");
        game.computeMoveByString("0 0 D");
        game.computeMoveByString("0 1 L");


        Assertions.assertEquals(1,game.printCurrentPlayerScore());

    }

    @Test
    public void game_first_turn_player(){
        System.out.println("TEST GAME FIRST TURN");
        Assertions.assertEquals(game.player1.getFirstLetterName(),game.currentPlayer.getFirstLetterName());
    }

    @Test
    public void game_second_turn_player(){
        System.out.println("TEST GAME SECOND TURN");

        game.computeMoveByString("0 0 L");
        Assertions.assertEquals(game.player2.getFirstLetterName(),game.currentPlayer.getFirstLetterName());
    }

    @Test
    public void  game_swap_players_after_one_point(){
        System.out.println("TEST GAME TURN AFTER POINT");

        game.computeMoveByString("0 0 L");
        game.computeMoveByString("0 0 U");
        game.computeMoveByString("0 0 D");
        game.computeMoveByString("0 1 L");

        assertAll(
                ()->Assertions.assertEquals(game.player2.getFirstLetterName(),game.currentPlayer.getFirstLetterName()),
                ()->Assertions.assertNotEquals(game.player1.getFirstLetterName(),game.currentPlayer.getFirstLetterName())
        );

    }
}
