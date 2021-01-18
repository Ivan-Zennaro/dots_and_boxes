import java.io.*;
import java.net.BindException;
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

            serverAndSocketCloserWhenBackButtonPressed(server, socket);

            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            printScoreBoard();

            while (!isGameFinished()) {
                Move move;

                if (currentPlayer == player1) {
                    move = ioManager.readMove();

                    if (board.isMoveAllowed(move)) {
                        objectOutputStream.writeObject(move);
                        objectOutputStream.flush();

                    }
                } else
                    move = (Move) objectInputStream.readObject();

                computeMove(move);
                printScoreBoard();
            }

            socket.close();
            server.close();
            endGame();

        }catch (BindException e){
            ioManager.errorHandler("Port already used");
        }
        catch (IOException e) {
            ioManager.errorHandler("Impossible to connect to Client");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void serverAndSocketCloserWhenBackButtonPressed(ServerSocket server, Socket socket) {
        new Thread(() -> {
            boolean timeToStopThread = false;
            while (!timeToStopThread) {
                if (ioManager.getBackPress()) {
                    try {
                        server.close();
                        socket.close();
                        timeToStopThread = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(200);
                } catch (IllegalArgumentException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
