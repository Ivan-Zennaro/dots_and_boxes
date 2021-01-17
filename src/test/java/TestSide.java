import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestSide {

    @ParameterizedTest
    @CsvSource({"UP,DOWN", "LEFT,RIGHT", "RIGHT,LEFT", "DOWN,UP"})
    public void get_invert_side(Side side, Side invertedSide) {
        Assertions.assertEquals(invertedSide, side.reverse());
    }

}
