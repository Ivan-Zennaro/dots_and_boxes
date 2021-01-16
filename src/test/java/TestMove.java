import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestMove {

    @ParameterizedTest
    @CsvSource({"UP,true", "DOWN,true", "RIGHT,false", "LEFT,false"})
    public void is_a_horizontal_move(Side side, boolean isHorizontal) {
        Move move = new Move(0,0,side);
        Assertions.assertEquals(isHorizontal, move.isSideHorizontal());
    }

    @ParameterizedTest
    @CsvSource({"UP,false", "DOWN,false", "RIGHT,true", "LEFT,true"})
    public void is_a_vertical_move(Side side, boolean isVertical) {
        Move move = new Move(0,0,side);
        Assertions.assertEquals(isVertical, move.isSideVertical());
    }
}
