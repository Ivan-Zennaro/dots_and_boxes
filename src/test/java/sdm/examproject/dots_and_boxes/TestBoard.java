package sdm.examproject.dots_and_boxes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestBoard {

    @ParameterizedTest
    @CsvSource({"0,0,UP,TRUE","0,0,DOWN,TRUE","0,0,LEFT,FALSE","0,0,RIGHT,FALSE",
            "0,1,UP,TRUE","0,1,LEFT,FALSE","1,1,LEFT,TRUE","0,2,UP,FALSE","2,2,DOWN,FALSE","1,3,UP,FALSE"})
    public void move_is_allowed(int x, int y, Side side, boolean moveIsAllowed) {
        Board board = new Board(2, 2);
        board.drawLine(new Move(0, 0, Side.LEFT));
        board.drawLine(new Move(0, 0, Side.RIGHT));
        Move move = new Move(x,y,side);
        Assertions.assertEquals(moveIsAllowed, board.isMoveAllowed(move));
    }

    @ParameterizedTest
    @CsvSource({"0,0,RIGHT,0,1,LEFT", "0,0,DOWN,1,0,UP", "1,1,RIGHT,1,2,LEFT", "1,2,UP,0,2,DOWN", "0,0,UP,-1,-1,INVALID"})
    public void move_represents_the_same_line_as_neighbourMove(int x_1, int y_1, Side side_1, int x_2, int y_2, Side side_2) {
        Board board = new Board(3, 3);
        Move move = new Move(x_1, y_1, side_1);
        Move neighbourMove = new Move(x_2, y_2, side_2);
        Assertions.assertEquals(neighbourMove, board.getNeighbourSideMove(move));
    }

    @ParameterizedTest
    @CsvSource({"0,0,RIGHT,0,1,LEFT", "0,0,DOWN,1,0,UP", "1,1,RIGHT,1,2,LEFT", "1,2,UP,0,2,DOWN"})
    public void neighbourMove_is_correctly_update_in_the_board(int x_1, int y_1, Side side_1, int x_2, int y_2, Side side_2) {
        Board board = new Board(10, 10);
        Move move = new Move(x_1, y_1, side_1);
        Move neighbourMove = new Move(x_2, y_2, side_2);
        board.drawLine(move);
        Assertions.assertFalse(board.isMoveAllowed(neighbourMove));
    }

    @ParameterizedTest
    @CsvSource({"0,0,RIGHT", "0,0,DOWN", "0,0,UP", "0,1,UP", "1,1,UP"})
    public void box_of_the_drawn_line_is_not_completed(int x, int y, Side side) {
        Board board = new Board(2, 2);
        Move move = new Move(x, y, side);
        board.drawLine(move);
        Assertions.assertFalse(board.isBoxCompleted(move));
    }

    @ParameterizedTest
    @CsvSource({"0,0", "0,1", "1,0", "1,1"})
    public void box_is_completed(int x, int y) {
        Board board = new Board(2, 2);
        Move move = new Move(x, y, Side.UP);
        board.drawLine(move);
        move = new Move(x, y, Side.DOWN);
        board.drawLine(move);
        move = new Move(x, y, Side.LEFT);
        board.drawLine(move);
        move = new Move(x, y, Side.RIGHT);
        board.drawLine(move);
        Assertions.assertTrue(board.isBoxCompleted(move));
    }

    @Test
    public void two_near_boxes_are_completed() {
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
