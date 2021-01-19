package sdm.examproject.dots_and_boxes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;

public class TestColors {

    @Test
    public void string_is_colored_with_the_right_color(){
        assertAll(
                () -> Assertions.assertEquals("\u001B[31m-\u001B[0m", Color.RED.getColoredString("-")),
                () -> Assertions.assertEquals("\u001B[34m|\u001B[0m", Color.BLU.getColoredString("|")),
                () -> Assertions.assertEquals("\u001B[32m-\u001B[0m", Color.GREEN.getColoredString("-")),
                () -> Assertions.assertEquals("\u001B[35m|\u001B[0m", Color.PURPLE.getColoredString("|"))
        );
    }

}
