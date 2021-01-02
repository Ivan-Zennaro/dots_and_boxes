import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServerGame {

    private final int port;
    private final String quitCommand;
    private final ExecutorService executorService;


    public ServerGame(int port, String quitCommand) {
        this.port = port;
        this.quitCommand = quitCommand;
        executorService = Executors.newCachedThreadPool();
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {


            while (true) {
                try {
                    final Socket socket = serverSocket.accept();
                    /* ---- START CLIENT HANDLING HERE ---- */
                    executorService.submit(() -> {
                        try (socket) {
                            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                            System.out.print(String.format("[%1$tY-%1$tm-%1$td %1$tT] ", System.currentTimeMillis()));
                            System.out.println("New connection from: " + socket.getInetAddress().getHostName()); // Log the new connection client

                            //TODO - welcome message on client screen

                            // istance new game
                            ClientPlayerGame gameForClient = new ClientPlayerGame(br, bw);
                            gameForClient.startGameServer(quitCommand);


                            while (true) {  //here the games is end or the client asked to quit

                                //possible line showed to client: "the game is ended due to a quit request or to its natural end:
                                // would you really quit (write QUIT) or restart a new game?"

                                //TODO - implement a flow to allow a user to restart a new game
                                String command = br.readLine();

                                if (command == null) {
                                    System.out.print(String.format("[%1$tY-%1$tm-%1$td %1$tT] ", System.currentTimeMillis()));
                                    System.out.println("Client abruptly closed connection " + socket.getInetAddress().getHostName());
                                    break;
                                }

                                command = command.trim();
                                if (command.equals(quitCommand)) {
                                    System.out.print(String.format("[%1$tY-%1$tm-%1$td %1$tT] ", System.currentTimeMillis()));
                                    System.out.println("Client " + socket.getInetAddress().getHostName() + " disconnected");
                                    break;
                                }
                            }
                        } catch (IOException e) {
                            System.out.print(String.format("[%1$tY-%1$tm-%1$td %1$tT] ", System.currentTimeMillis()));
                            System.out.printf("IO error: %s", e.getMessage());
                        } catch (Exception e) {
                            System.out.print(String.format("[%1$tY-%1$tm-%1$td %1$tT] ", System.currentTimeMillis()));
                            System.out.printf("EXCEPTION: %s", e.getMessage());
                        }
                    });
                } catch (IOException e) {
                    System.out.print(String.format("[%1$tY-%1$tm-%1$td %1$tT] ", System.currentTimeMillis()));
                    System.out.printf("Cannot accept connection due to %s", e);
                }
            }

        } finally {
            executorService.shutdown();
        }


    }
}
