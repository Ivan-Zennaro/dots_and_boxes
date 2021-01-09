public class MainClient {

    public static void main(String[] args) {
        Player player1 = new Player('A', Color.RED);
        Player player2 = new Player('B', Color.BLU);

        ClientNewGame game = new ClientNewGame(3, 3, player1, player2, new Cli(3, 3), "127.0.0.1");
        game.startGame();
    }
}
