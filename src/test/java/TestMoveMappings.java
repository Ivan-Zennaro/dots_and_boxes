import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestMoveMappings {

    @ParameterizedTest
    @ValueSource(strings = {"UP", "DOWN"})
    public void horizontalAligned(Side side){
        Move testMove = new Move(0,0,side);

        Assertions.assertTrue(testMove.isHorizontal());
    }

    @ParameterizedTest
    @ValueSource(strings = {"LEFT", "RIGHT"})
    public void verticalAligned(Side side){
        Move testMove = new Move(0,0,side);

        Assertions.assertTrue(testMove.isVertical());
    }




}
