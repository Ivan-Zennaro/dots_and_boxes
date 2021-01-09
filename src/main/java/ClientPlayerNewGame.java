import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class ClientPlayerNewGame extends NewGame {
    BufferedReader keyboard;
    BufferedWriter outputServer;
    Cli cli;

    ClientPlayerNewGame(BufferedReader keyboard, BufferedWriter outputFromServer) {
        super(3,3, new Player('A', Color.RED), new Player('B', Color.BLU), new Cli (3,3));
        this.keyboard = keyboard;
        this.outputServer = outputFromServer;
        //TODO aggiungere metodo abstract che compare in Cli (getStringBoard) in IOmanager
        this.ioManager = new Cli(3, 3);
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

                if (command == null) {
                   // System.out.print(String.format("[%1$tY-%1$tm-%1$td %1$tT] ", System.currentTimeMillis()));
                   // System.out.println("Client abruptly closed connection ");
                    return;
                }
                command = command.trim();
                if (command.equals(quitCommand)) {
                    outputServer.write("You requested to end the game! So, the game ended as follow:" + System.lineSeparator());
                    outputServer.flush();
                    endGame();
                    return;
                }

                //TODO - compute move deve leggere dal buffered reader!!
                computeMove(cli.readMove());
            }
            endGame();
        }
    }


    public boolean initializeBoard(String quitCmd) throws IOException {
        int optionGrid = 0;
        String inputLineClient;

        do {
            outputServer.write("How big the grid? 2:[2x2]  3:[3x3] 5:[5x5]" + System.lineSeparator());
            outputServer.flush();

            inputLineClient = keyboard.readLine();

            if (inputLineClient == null || inputLineClient.equals(quitCmd)) {
                //System.out.print(String.format("[%1$tY-%1$tm-%1$td %1$tT] ", System.currentTimeMillis()));
                //System.out.println("Client disconnected or Client abruptly closed connection");
                return false;
            }

            try {
                optionGrid = Integer.parseInt(inputLineClient);
            } catch (Exception e) {
                e.getStackTrace();
            }
        } while (!(optionGrid == 2 || optionGrid == 3 || optionGrid == 5));

        board = new Board(optionGrid, optionGrid);
        cli = new Cli(optionGrid, optionGrid);

        return true;
    }

    public String printScoreBoardOnClient() {
        String printableScoreBoard;
        printableScoreBoard = cli.getStringBoard() + System.lineSeparator();
        printableScoreBoard += "Player " + player1.getId() + " got " + player1.getPoints() + " points" + System.lineSeparator();
        printableScoreBoard += "Player " + player2.getId() + " got " + player2.getPoints() + " points" + System.lineSeparator();
        printableScoreBoard += "Is the turn of Player" + currentPlayer.getId() + System.lineSeparator();
        return printableScoreBoard;
    }

    @Override
    public void endGame() {
        finalGraphics();
        printWinner();
        try {
            outputServer.write("The game is ended due to a quit request or to its natural end: " + System.lineSeparator());
            outputServer.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void finalGraphics() {
        String finalGraphicForClient;
        finalGraphicForClient = cli.getStringBoard() + System.lineSeparator();
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
            outputServer.write(System.lineSeparator());
            outputServer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO
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
