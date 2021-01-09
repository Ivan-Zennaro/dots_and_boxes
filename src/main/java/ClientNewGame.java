import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientNewGame extends NewGame {

    private final int PORT = 1234;
    private String ip;

    public ClientNewGame(int nRows, int nCols, Player player1, Player player2, IOManager ioManager, String ip) {
        super(nRows, nCols, player1, player2, ioManager);
        this.ip = ip;
    }

    @Override
    public void startGame() {
        try (Socket socket = new Socket(ip, PORT)) {

            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            printScoreBoard();

            while (!isGameFinished()) {
                Move move;

                if (currentPlayer == player2) {
                    move = ioManager.readMove();

                    if (isMoveAllowed(move)) { //...send the move to the Server
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

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //TODO torno al menu iniziale
    }
}



