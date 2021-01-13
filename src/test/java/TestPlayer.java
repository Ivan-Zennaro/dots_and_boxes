import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestPlayer {

    @Test
    public void update_point_from_0_to_1(){
        Player player = new Player("A", Color.RED);
        player.onePointDone();
        Assertions.assertEquals(1, player.getPoints());
    }

    @ParameterizedTest
    @CsvSource({"John,J", "Luke,L", "Mike,M", "Carl,C"})
    public void name_start_with_the_correct_letter(String name, char firstLetter){
        Player player = new Player(name, Color.RED);
        Assertions.assertEquals(firstLetter, player.getFirstLetterName());
    }

}
