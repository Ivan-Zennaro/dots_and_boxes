import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBox {

    /*
    Does this test make some sense? to test the instance of an object....dont think so.
    [Dario]
     */
    //test1

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

    @Test
    public void drawSomeLines(){
        Box box = new Box();
        box.drawLineUp();
        box.drawLineLeft();
        assertAll(
                () -> assertTrue(box.hasLineUp()),
                () -> assertFalse(box.hasLineDown()),
                () -> assertTrue(box.hasLineLeft()),
                () -> assertFalse(box.hasLineRight())
        );
    }

}
