import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestPlayer {

    @Test
    public void update_point_from_0_to_1(){
        Player player = new Player('A', Color.RED);
        Assertions.assertEquals(1, player.onePointDone());
    }
}
