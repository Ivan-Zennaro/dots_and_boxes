import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainComputer {
    public static void main(String[] args) {
        Player p1 = new Player('A',Color.RED);
        Player p2 = new Player('B',Color.BLU);
        ComputerGame game = new ComputerGame(3,3,p1 ,p2,new Cli(3,3));
        game.startGame();

    }
}
