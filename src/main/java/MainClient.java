public class MainClient {

    public static void main(String[] args) {

        Player p1 = new Player('A',Color.RED);
        Player p2 = new Player('B',Color.BLU);
        ClientNewGame game = new ClientNewGame(3,3,p1 ,p2,new Cli(3,3),"127.0.0.1");
        game.startGame();


    }
}
