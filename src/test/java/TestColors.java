import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestColors {

    @ParameterizedTest
    @CsvSource({"-,RED,\u001B[31m"})
    public void colors_match_the_correct_strings(String s, Color c, String pattern){
        Assertions.assertEquals( "\u001B[31m"+s+"\u001B[0m", ColorManager.getColoredString(s,c));
    }

}
