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

        try (ServerSocket server = new ServerSocket(PORT)) {

            System.out.println("server ready....");
            Socket socket = server.accept();

            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);


            printScoreBoard();

            while (!isGameFinished()) {


                Move move;

                if (currentPlayer == player1) {
                    move = ioManager.readMove();

                    if (isMoveAllowed(move)) {
                        objectOutputStream.writeObject(move);
                        objectOutputStream.flush();
                    }

                } else {
                    move = (Move) objectInputStream.readObject();
                }
                computeMove(move);
                printScoreBoard();
            }

            endGame();

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("Ciao alla prossima");
        }

        //TODO torno al menu iniziale
    }
}
