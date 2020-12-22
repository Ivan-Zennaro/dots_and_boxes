
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestColors {

    @Test
    public void colors_match_the_correct_strings(){
        assertAll(
                () -> assertEquals("\u001B[31m-\u001B[0m", ColorManager.getColoredString("-",Color.RED)),
                () -> assertEquals("\u001B[34m|\u001B[0m", ColorManager.getColoredString("|",Color.BLU)),
                () -> assertEquals("\u001B[32m-\u001B[0m", ColorManager.getColoredString("-",Color.GREEN)),
                () -> assertEquals("\u001B[35m|\u001B[0m", ColorManager.getColoredString("|",Color.PURPLE))
        );
    }

}
