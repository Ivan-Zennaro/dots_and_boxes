import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestBox {

    /*
    Does this test make some sense? to test the instance of an object....dont think so.
    [Dario]
     */

    @Test
    public void empty_instance(){
        Box box = new Box();
        assertAll(
                () -> assertFalse(box.hasLineUp()),
                () -> assertFalse(box.hasLineDown()),
                () -> assertFalse(box.hasLineLeft()),
                () -> assertFalse(box.hasLineRight())
        );
    }

}
