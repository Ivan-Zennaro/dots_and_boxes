import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServerGame {

    private final int port;
    private final String quitCommand;
    private final ExecutorService executorService;
    private final ExecutorService executorMath;

    public ServerGame(int port, String quitCommand) {
        this.port = port;
        this.quitCommand = quitCommand;
        executorService = Executors.newCachedThreadPool();
        executorMath = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
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

                            while (true) {  //loop until server gets command quit or client disconnect
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

                                /* ---------------- DO MOVE WRITTEN TO THE SERVER ---------------- */
                                if (true) { //  TO DO !!!!!!!
                                    //DO THE MOVE (or game flow?)
                                } else {
                                    bw.write("ERR; Wrong format, try again" + System.lineSeparator());
                                    bw.flush();
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
            executorMath.shutdown();
        }


    }
}
