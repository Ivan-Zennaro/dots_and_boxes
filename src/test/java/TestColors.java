import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestColors {


    @Test
    public void colors_match_the_correct_strings(){
        assertAll(
                () -> assertEquals("\u001B[31m-\u001B[0m", ColorManager.getColoredString("-",Color.RED)),
                () -> assertEquals("\u001B[34m|\u001B[0m", ColorManager.getColoredString("|",Color.BLU))
        );
    }

}
