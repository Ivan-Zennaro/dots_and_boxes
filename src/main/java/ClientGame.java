import java.io.*;
import java.net.Socket;

public class ClientGame extends Game {

    private final int PORT = 1234;
    private String ip;

    public ClientGame(int nRows, int nCols, Player player1, Player player2, IOManager ioManager, String ip) {
        super(nRows, nCols, player1, player2, ioManager);
        this.ip = ip;
    }

    @Override
    public void startGame() {
        try (Socket socket = new Socket(ip, PORT)) {

            socketCloserWhenBackButtonPressed(socket);

            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            printScoreBoard();

            while (!isGameFinished()) {
                Move move;

                if (currentPlayer == player2) {
                    move = ioManager.readMove();

                    if (board.isMoveAllowed(move)) {
                        objectOutputStream.writeObject(move);
                        objectOutputStream.flush();
                    }
                } else {
                    move = (Move) objectInputStream.readObject();
                }

                computeMove(move);
                printScoreBoard();
            }
            socket.close();
            endGame();

        } catch (IOException e) {
            ioManager.errorHandler("Impossible to connect to Server");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void socketCloserWhenBackButtonPressed(Socket socket) {
        new Thread(() -> {
            boolean timeToStopThread = false;
            while (!timeToStopThread) {
                if (ioManager.getBackPress()) {
                    try {
                        socket.close();
                        timeToStopThread = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}



