import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class TestMoveParsing {

    @ParameterizedTest
    @CsvSource({"10,5,UP,10 5 U", "0,0,DOWN,0 0 D", "1,0,RIGHT,1 0 R", "1,1,LEFT, 1 1 L"})
    public void string_to_move(int x, int y, Side side, String stringMove){
        Move inputMove = Move.parseMove(stringMove);
        Move testMove = new Move(x,y,side);

        Assertions.assertEquals(testMove,inputMove);
    }

    @Test
    public void null_to_invalid_move(){
        Move inputMove = Move.parseMove(null);
        Move testMove = new Move(-1,-1,Side.INVALID);

        Assertions.assertEquals(testMove,inputMove);
    }

    @ParameterizedTest
    @ValueSource(strings = {"4 5 k", "u p k", "487 K", "01D", "0", "", "0 0 D"}) //L'input è per le stringhe di lunghezza 3, la convenzione si può modificare come preferiamo, basta decidere
    public void string_to_invalid_move(String input){
        Move inputMove = Move.parseMove(input);
        Move testMove = new Move(-1,-1,Side.INVALID);

        Assertions.assertEquals(testMove,inputMove);
    }
}


