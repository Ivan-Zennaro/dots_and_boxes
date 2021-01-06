import java.io.IOException;

public class MainServer {
    public static void main(String[] args) {


        /*ServerGame sg = new ServerGame(10000, "QUIT");
        try {
            sg.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        Player p1 = new Player('A',Color.RED);
        Player p2 = new Player('B',Color.BLU);
        ServerNewGame game = new ServerNewGame(3,3,p1 ,p2,new Cli(3,3));
        game.startGame();

    }



}
