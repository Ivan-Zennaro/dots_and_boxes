import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

public class TestInput {


    private final Input input = new Input();

    @Test
    public void testMoveParser(){
        Move inputMove = input.parseMove("23U");
        Move testMove = new Move(2,3,Side.UP);

        Assertions.assertEquals(testMove,inputMove);
    }

    @Test
    public void testInvalidMoveWrongDirection(){
        Move inputMove = input.parseMove("48k");
        Move testMove = new Move(-1,-1,Side.INVALID);

        Assertions.assertEquals(testMove,inputMove);
    }
    @Test
    public void testInvalidMoveWrongNumbers(){
        Move inputMove = input.parseMove("upk");
        Move testMove = new Move(-1,-1,Side.INVALID);

        Assertions.assertEquals(testMove,inputMove);
    }

    @Test
    public void testInvalidMoveTooBigInput(){
        Move inputMove = input.parseMove("487k");
        Move testMove = new Move(-1,-1,Side.INVALID);
        Assertions.assertEquals(testMove,inputMove);
    }
    @Test
    public void testInvalidMoveTooSmallInput(){
        Move inputMove = input.parseMove("4k");
        Move testMove = new Move(-1,-1,Side.INVALID);
        Assertions.assertEquals(testMove,inputMove);
    }
}
