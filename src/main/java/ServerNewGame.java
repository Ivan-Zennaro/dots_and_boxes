import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerNewGame extends NewGame {

    private final int PORT = 1234;

    public ServerNewGame(int nRows, int nCols, Player p1, Player p2, IOManager ioManager) {
        super(nRows, nCols, p1, p2, ioManager);
    }

    @Override
    public void startGame() {

        try{
            ServerSocket server = new ServerSocket(PORT);
            Socket socket = server.accept();

            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);


        } catch(Exception e) {}

        // TODO dobbiamo far partire il server

        /*
        while (!isGameFinished()) {

            if (mio turno) {
                computeMove(ioManager.readMove());
                sendMove() //mando alla altro la mossa
            }
            else {
                readMove da stream di rete
                computeMove (move letta da stream di rete)
            }
        }*/
        endGame();
    }

}
