import java.io.IOException;

public class MainServerHost {
    public static void main(String[] args) {
        ServerTelnet sg = new ServerTelnet(10000, "QUIT");
        try {
            sg.start();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


}
