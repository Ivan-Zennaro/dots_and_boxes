import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestInput {


    private final Input input = new Input();

    @Test
    public void testMoveParser(){
        Move inputMove = input.parseMove("45U");
        Move testMove = new Move(4,5,Side.UP);

        Assertions.assertEquals(testMove,inputMove);
    }


    @ParameterizedTest
    @ValueSource(strings = {"45k", "upk", "487K", "4k"}) //L'input è per le stringhe di lunghezza 3, la convezione si può modificare come preferiamo, basta decidere
    public void testInvalidMoveFromInput(String inpt){
        Move inputMove = input.parseMove(inpt);
        Move testMove = new Move(-1,-1,Side.INVALID);

        Assertions.assertEquals(testMove,inputMove);
    }

}
