import java.io.IOException;

public class MainServer {
    public static void main(String[] args) {
        ServerGame sg = new ServerGame(10000, "QUIT");
        try {
            sg.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
