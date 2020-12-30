import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestMoveMappings {


    @Test
    public void horizontalAligned(){
        Move testMove = new Move(0,0,Side.UP);

        Assertions.assertTrue(testMove.isHorizontal());
    }
}
