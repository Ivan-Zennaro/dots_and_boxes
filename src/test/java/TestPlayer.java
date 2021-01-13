import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class TestPlayer {

    @Test
    public void update_point_from_0_to_1(){
        Player player = new Player("A", Color.RED);
        Assertions.assertEquals(1, player.onePointDone());
    }

    @ParameterizedTest
    @CsvSource({"RED", "BLU", "PURPLE", "GREEN"})
    public void color_is_mapped_to_the_correct_awtColor(Color color){
        Player player = new Player("A", color);
        Assertions.assertEquals(color.getAwtColor(), player.getColor().getAwtColor());
    }
}
