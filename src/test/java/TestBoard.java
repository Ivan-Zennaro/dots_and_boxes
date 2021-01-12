import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class TestBoard {

    @ParameterizedTest
    @ValueSource(strings = {"UP", "DOWN", "LEFT"})
    public void checkboxHasAlreadyLineUP(Side side) {
        Board board = new Board(1, 1);
        Move move = new Move(0, 0, side);
        board.drawLine(move);
        assertTrue(board.boxHasAlreadyLine(move));
    }

    @ParameterizedTest
    @ValueSource(strings = {"UP", "DOWN", "LEFT", "RIGHT"})
    public void boxIsUpdated(Side side) {
        Board board = new Board(1, 2);
        Move move = new Move(0, 0, side);
        board.drawLine(move);
        assertTrue(board.boxHasAlreadyLine(move));
    }

    @Test
    public void neighbourBoxIsUpdated() {
        Board board = new Board(1, 2);
        Move move = new Move(0, 0, Side.RIGHT);
        Move neighbourMove = new Move(0, 1, Side.LEFT);
        board.drawLine(move);
        assertTrue(board.boxHasAlreadyLine(neighbourMove));
    }

    @Test
    public void neighbourBoxIsUpdatedOnALargerBoard() {
        Board board = new Board(10, 10);
        Move move = new Move(5, 5, Side.UP);
        Move neighbourMove = new Move(4, 5, Side.DOWN);
        board.drawLine(move);
        assertTrue(board.boxHasAlreadyLine(neighbourMove));
    }

    @Test
    public void boxIsNotCompleted() {
        Board board = new Board(2, 2);
        Move move = new Move(0, 0, Side.UP);
        board.drawLine(move);
        Assertions.assertFalse(board.isBoxCompleted(move));
    }

    @Test
    public void boxIsCompleted() {
        Board board = new Board(2, 2);
        Move move = new Move(0, 0, Side.UP);
        board.drawLine(move);
        move = new Move(0, 0, Side.DOWN);
        board.drawLine(move);
        move = new Move(0, 0, Side.LEFT);
        board.drawLine(move);
        move = new Move(0, 0, Side.RIGHT);
        board.drawLine(move);
        assertTrue(board.isBoxCompleted(move));
    }

    @Test
    public void twoCompletedBox() {
        Board board = new Board(2, 2);
        Move moveInBox1;
        Move moveInBox2;
        Move move = new Move(0, 0, Side.UP);
        board.drawLine(move);
        move = new Move(0, 0, Side.DOWN);
        board.drawLine(move);
        move = new Move(0, 0, Side.LEFT);
        moveInBox1 = move;
        board.drawLine(move);
        move = new Move(0, 1, Side.UP);
        board.drawLine(move);
        move = new Move(0, 1, Side.DOWN);
        board.drawLine(move);
        move = new Move(0, 1, Side.RIGHT);
        board.drawLine(move);
        move = new Move(0, 1, Side.LEFT);
        moveInBox2 = move;
        board.drawLine(move);
        assertAll(
                () -> Assertions.assertTrue(board.isBoxCompleted(moveInBox1)),
                () -> Assertions.assertTrue(board.isBoxCompleted(moveInBox2))
        );
    }
}
