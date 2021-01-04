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


                            // istance new game
                            ClientPlayerGame gameForClient = new ClientPlayerGame(br, bw);

                            bw.write("Welcome on Server 'Dot and Boxes'!" + System.lineSeparator());
                            bw.write("Now you can play a game in your terminal!" + System.lineSeparator());
                            bw.write("If you wanna exit the game type 'QUIT'" + System.lineSeparator());
                            bw.flush();
                            gameForClient.startGameServer(quitCommand);

                            while (true) {
                                bw.write("you can restart a new game (type 'NEW') or actually quit (type 'QUIT')" + System.lineSeparator());
                                bw.flush();

                                String command = br.readLine();

                                if (command == null) {
                                    System.out.print(String.format("[%1$tY-%1$tm-%1$td %1$tT] ", System.currentTimeMillis()));
                                    System.out.println("Client abruptly closed connection " + socket.getInetAddress().getHostName());
                                    break;
                                }

                                command = command.trim();
                                if (command.equals(quitCommand)) {
                                    bw.write("You chose to close the connection with the Server!" + System.lineSeparator());
                                    bw.write("Thank you, we hope that enjoyed your game! See you");
                                    bw.write("Type whatever you want to close the connection" + System.lineSeparator());
                                    bw.flush();
                                    try{
                                        br.readLine();
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                    System.out.print(String.format("[%1$tY-%1$tm-%1$td %1$tT] ", System.currentTimeMillis()));
                                    System.out.println("Client " + socket.getInetAddress().getHostName() + " disconnected");
                                    break;
                                }

                                if (command.equals("NEW")) {
                                    gameForClient = new ClientPlayerGame(br, bw);
                                    gameForClient.startGameServer(quitCommand);
                                }

                            }
                        } catch (IOException e) {
                            System.out.print(String.format("[%1$tY-%1$tm-%1$td %1$tT] ", System.currentTimeMillis()));
                            System.out.printf("IO error (Client " + socket.getInetAddress().getHostName() +"): %s", e.getMessage() + "\n");
                        } catch (Exception e) {
                            System.out.print(String.format("[%1$tY-%1$tm-%1$td %1$tT] ", System.currentTimeMillis()));
                            System.out.printf("EXCEPTION: %s", e.getMessage() + "\n");
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
