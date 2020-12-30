import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

    @ParameterizedTest
    @CsvSource({"0,0,UP,0", "0,0,LEFT,0", "0,0,DOWN,1", "0,0,RIGHT,0"})
    public void coordinateXinTwoMatrixRepresentation(int x, int y, Side side, int newXcoordinate){
        Move testMove = new Move(x,y,side);

        Assertions.assertEquals(newXcoordinate, testMove.getXtwoMatrixRepresentation());
    }




}
