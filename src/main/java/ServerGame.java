import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerGame extends Game {

    private final int PORT = 1234;

    public ServerGame(int nRows, int nCols, Player player1, Player player2, IOManager ioManager) {
        super(nRows, nCols, player1, player2, ioManager);
    }

    @Override
    public void startGame() {

        try (ServerSocket server = new ServerSocket(PORT)) {

            System.out.println("Server ready....");
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
                } else
                    move = (Move) objectInputStream.readObject();

                computeMove(move);
                printScoreBoard();
            }

            endGame();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Server is closed.");
            System.out.println("Goodbye, see you next time!");
        }
    }
}
