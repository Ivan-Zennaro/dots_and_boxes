import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBoard {

    @Test
    public void checkboxHasAlreadyLineUP(){
        Board board = new Board(1,1);
        Move move = new Move(0,0,Side.UP);
        board.drawLine(move);
        assertTrue(board.boxHasAlreadyLine(move));
    }

}
