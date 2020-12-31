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

            //TODO -- istance a game here when the server starts

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
                            // ask client which board
                            boolean flagIstanceNewBoard = false;

                            while (true) {  //loop until server gets command quit or client disconnect
                                //OR WHEN THE GAME ENDS?

                                if (!flagIstanceNewBoard){
                                    // new game
                                    bw.write("Devi scegliere una board");
                                    bw.flush();
                                }

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



                                // COMMAND OPTION: 1. MOVE 2. NEW GAME 3.ASK AGAIN

                                /* ---------------- DO MOVE WRITTEN TO THE SERVER ---------------- */
                                if (true) { //  TO DO !!!!!!!
                                    //DO THE MOVE (or game flow?)
                                    System.out.println("Test");
                                    bw.write("OK TEST" + System.lineSeparator());
                                    bw.flush();
                                } else {
                                    bw.write("ERR; Wrong format, try again" + System.lineSeparator());
                                    bw.flush();
                                }

                                //IF GAME IS FINISHED -> show winner and break loop while
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
