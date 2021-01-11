import java.io.IOException;

public class MainServer {
    public static void main(String[] args) {
        Player player1 = new Player('A', Color.RED);
        Player player2 = new Player('B', Color.BLU);
        ServerNewGame game = new ServerNewGame(3, 3, player1, player2, new Cli(3, 3,player1,player2));
        game.startGame();
    }
}
