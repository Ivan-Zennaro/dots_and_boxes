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
    @Test
    public void boxIsUpdated(){
        Board board = new Board(1,2);
        Move move = new Move(0,0,Side.RIGHT);
        board.drawLine(move);
        assertTrue(board.boxHasAlreadyLine(move));
    }


    @Test
    public void neighbourBoxIsUpdated(){
        Board board = new Board(1,2);
        Move move = new Move(0,0,Side.RIGHT);
        Move neighbourMove = new Move(0,1,Side.LEFT);
        board.drawLine(move);
        assertTrue(board.boxHasAlreadyLine(neighbourMove));
    }
    @Test
    public void neighbourBoxIsUpdatedOnALargerBoard(){
        Board board = new Board(10,10);
        Move move = new Move(5,5,Side.UP);
        Move neighbourMove = new Move(4,5,Side.DOWN);
        board.drawLine(move);
        assertTrue(board.boxHasAlreadyLine(neighbourMove));
    }

}
