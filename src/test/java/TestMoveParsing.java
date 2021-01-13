import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class TestMoveParsing {

    @ParameterizedTest
    @CsvSource({"10 5 U,10,5,UP", "0 0 D,0,0,DOWN", "1 0 R,1,0,RIGHT", "1 1 L,1,1,LEFT"})
    public void string_to_move(String stringMove, int x, int y, Side side) {
        Move inputMove = Move.parseMove(stringMove);
        Move testMove = new Move(x, y, side);

        Assertions.assertEquals(testMove, inputMove);
    }

    @Test
    public void null_to_invalid_move() {
        Move inputMove = Move.parseMove(null);

        Assertions.assertEquals(Move.getInvalidMove(), inputMove);
    }

    @ParameterizedTest
    @ValueSource(strings = {"4 5 k", "u p k", "487 K", "01D", "0", "", "0 0 D"})
    public void string_to_invalid_move(String input) {
        Move inputMove = Move.parseMove(input);
        Move testMove = new Move(-1, -1, Side.INVALID);

        Assertions.assertEquals(testMove, inputMove);
    }
}


