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

            new Thread(() -> {
                while (true){
                    if (ioManager.getBackPress()){
                        try {
                            server.close();
                            socket.close();
                            Thread.currentThread().interrupt();
                            Thread.currentThread().stop();
                        } catch (IOException e){
                            System.out.println("errore in server.close()");
                        }
                    }
                    System.out.println(ioManager.getBackPress());
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e){
                        System.out.println("errore in sleep");
                    }
                }
            }).start();


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

            socket.close();
            server.close();
            endGame();

        } catch (IOException e) {
            ioManager.errorHandler("Port already used");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
