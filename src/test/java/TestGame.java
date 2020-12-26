import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class TestGame {

    @Test
    public void check_input_grid_dimensions_2x2(){
        ByteArrayInputStream in = new ByteArrayInputStream("2".getBytes());
        System.setIn(in);
        Game game = new Game();
        Board board = game.initializeBoard();
        Assertions.assertEquals(2, board.getBoardRows());
    }

}
