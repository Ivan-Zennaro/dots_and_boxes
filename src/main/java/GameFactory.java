public class GameFactory {

    //GUI Games
    public static NewGame create2PlayerGameWithGUI(int nRow, int nCols, Player p1, Player p2) {
        Gui gui = new Gui(nRow ,nCols, p1, p2);
        return new TwoPlayersNewGame(nRow, nCols, p1, p2, gui);
    }
    public static NewGame createComputerGameWithGUI(int nRow, int nCols, Player p1, Player p2) {
        Gui gui = new Gui(nRow ,nCols, p1, p2);
        return new ComputerGame(nRow, nCols, p1, p2, gui);
    }
    public static NewGame createClientGameWithGUI(int nRow, int nCols, Player p1, Player p2, String ip) {
        Gui gui = new Gui(nRow ,nCols, p1, p2);
        return new ClientNewGame(nRow, nCols, p1, p2, gui, ip);
    }
    public static NewGame createServerGameWithGUI(int nRow, int nCols, Player p1, Player p2, String ip) {
        Gui gui = new Gui(nRow ,nCols, p1, p2);
        return new ServerNewGame(nRow, nCols, p1, p2, gui);
    }

    //CLI Games
    public static NewGame create2PlayerGameWithCLI(int nRow, int nCols, Player p1, Player p2) {
        Cli cli = new Cli(nRow ,nCols, p1, p2);
        return new TwoPlayersNewGame(nRow, nCols, p1, p2, cli);
    }
    public static NewGame createComputerGameWithCLI(int nRow, int nCols, Player p1, Player p2) {
        Cli cli = new Cli(nRow ,nCols, p1, p2);
        return new ComputerGame(nRow, nCols, p1, p2, cli);
    }
    public static NewGame createClientGameWithCLI(int nRow, int nCols, Player p1, Player p2, String ip) {
        Cli cli = new Cli(nRow ,nCols, p1, p2);
        return new ClientNewGame(nRow, nCols, p1, p2, cli, ip);
    }
    public static NewGame createServerGameWithCLI(int nRow, int nCols, Player p1, Player p2, String ip) {
        Cli cli = new Cli(nRow ,nCols, p1, p2);
        return new ServerNewGame(nRow, nCols, p1, p2, cli);
    }

}
