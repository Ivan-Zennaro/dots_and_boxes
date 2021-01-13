import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestColors {

    @Test
    public void string_is_colored_to_the_right_color(){
        assertAll(
                () -> assertEquals("\u001B[31m-\u001B[0m", Color.RED.getColoredString("-")),
                () -> assertEquals("\u001B[34m|\u001B[0m", Color.BLU.getColoredString("|")),
                () -> assertEquals("\u001B[32m-\u001B[0m", Color.GREEN.getColoredString("-")),
                () -> assertEquals("\u001B[35m|\u001B[0m", Color.PURPLE.getColoredString("|"))
        );
    }

}
