import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class ClientPlayerGame extends Game {
    BufferedReader keyboard;
    BufferedWriter outputServer;

    ClientPlayerGame(BufferedReader keyboard, BufferedWriter outputFromServer) {
        super();
        this.keyboard = keyboard;
        this.outputServer = outputFromServer;
    }

    public void startGameServer(String quitCommand) throws IOException {
        boolean completedInitializationBoard;
        completedInitializationBoard = initializeBoard(quitCommand);

        if (completedInitializationBoard) {
            while (!isGameFinished()) {

                outputServer.write(printScoreBoardOnClient());
                outputServer.flush();
                outputServer.write("Insert move [x y side:U,D,L,R]?" + System.lineSeparator());
                outputServer.flush();

                String command = keyboard.readLine();

                //TODO - with quit command exit the game and show the result before quit: is it ok?
                if (command == null) {
                    System.out.print(String.format("[%1$tY-%1$tm-%1$td %1$tT] ", System.currentTimeMillis()));
                    System.out.println("Client abruptly closed connection ");
                    break;
                }
                command = command.trim();
                if (command.equals(quitCommand)) {
                    System.out.print(String.format("[%1$tY-%1$tm-%1$td %1$tT] ", System.currentTimeMillis()));
                    System.out.println("Client disconnected");
                    break;
                }

                turn(command);

                //SERVONO????
                // output.write(graphic.getStringBoard() + System.lineSeparator());
                // output.flush();

            }


            endGame();
        }
        // keyboard.close();
    }


    public boolean initializeBoard(String quitCmd) throws IOException {
        int optionGrid = 1;
        String inputLineClient;

        do {
            System.out.println("How big the grid? 2:[2x2]  3:[3x3] 5:[5x5]");
            outputServer.write("How big the grid? 2:[2x2]  3:[3x3] 5:[5x5]" + System.lineSeparator());
            outputServer.flush();

            inputLineClient = keyboard.readLine();

            if (inputLineClient == null || inputLineClient.equals(quitCmd)) {
                System.out.print(String.format("[%1$tY-%1$tm-%1$td %1$tT] ", System.currentTimeMillis()));
                System.out.println("Client disconnected or Client abruptly closed connection");
                return false;
            }

            try {
                optionGrid = Integer.parseInt(inputLineClient);
            } catch (Exception e) {
                e.getStackTrace();
            }
        } while (!(optionGrid == 2 || optionGrid == 3 || optionGrid == 5));

        board = new Board(optionGrid, optionGrid);
        graphic = new Graphic(optionGrid, optionGrid);

        return true;
    }

    public String printScoreBoardOnClient() {
        String printableScoreBoard;
        printableScoreBoard = graphic.getStringBoard() + System.lineSeparator();
        printableScoreBoard += "Player " + player1.getId() + " got " + player1.getPoints() + " points" + System.lineSeparator();
        printableScoreBoard += "Player " + player2.getId() + " got " + player2.getPoints() + " points" + System.lineSeparator();
        printableScoreBoard += "Is the turn of Player" + currentPlayer.getId() + System.lineSeparator();
        return printableScoreBoard;
    }

    @Override
    public void endGame() {
        finalGraphics();
        printWinner();
    }

    @Override
    public void finalGraphics() {
        String finalGraphicForClient;
        finalGraphicForClient = graphic.getStringBoard() + System.lineSeparator();
        finalGraphicForClient += "Player " + player1.getId() + " got " + player1.getPoints() + " points" + System.lineSeparator();
        finalGraphicForClient += "Player " + player2.getId() + " got " + player2.getPoints() + " points" + System.lineSeparator();

        try {
            outputServer.write(finalGraphicForClient);
            outputServer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void printWinner() {
        try {
            if (player1.getPoints() > player2.getPoints()) {
                outputServer.write("Player" + player1.getId() + " WON!");
            } else {
                if (player2.getPoints() > player1.getPoints()) {
                    outputServer.write("Player " + player2.getId() + " WON!");
                } else {
                    outputServer.write("TIE!");
                }
            }
            outputServer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //NON SERVE A NIENTE, SOLO PERCHE GAME E' ABSTRACT...Come ottimizzare???
    public void startGame() {
        return;
    }

    //NON SERVE A NIENTE, SOLO PERCHE GAME E' ABSTRACT...
    public Board initializeBoard() {
        Board board1 = new Board(1, 1);
        return board1;
    }
}
