public class GameFactory {

    //GUI Games
    public static Game create2PlayerGameWithGUI(int nRow, int nCols, Player p1, Player p2) {
        Gui gui = new Gui(nRow ,nCols, p1, p2);
        return new TwoPlayersGame(nRow, nCols, p1, p2, gui);
    }
    public static Game createComputerVsComputerGameWithGUI(int nRow, int nCols, Player p1, Player p2) {
        Gui gui = new Gui(nRow ,nCols, p1, p2);
        return new ComputerVsComputerGame(nRow, nCols, p1, p2, gui);
    }
    public static Game createPlayerVsComputerGameWithGUI(int nRow, int nCols, Player p1, Player p2, Difficulty difficulty) {
        Gui gui = new Gui(nRow ,nCols, p1, p2);
        return new PlayerVsComputerGame(nRow, nCols, p1, p2, gui,difficulty);
    }
    public static Game createServerGameWithGUI(int nRow, int nCols, Player p1, Player p2) {
        Gui gui = new Gui(nRow ,nCols, p1, p2);
        return new ServerGame(nRow, nCols, p1, p2, gui);
    }
    public static Game createClientGameWithGUI(int nRow, int nCols, Player p1, Player p2, String ip) {
        Gui gui = new Gui(nRow ,nCols, p1, p2);
        return new ClientGame(nRow, nCols, p1, p2, gui, ip);
    }


    //CLI Games
    public static Game create2PlayerGameWithCli(int nRow, int nCols, Player p1, Player p2) {
        Cli cli = new Cli(nRow ,nCols, p1, p2);
        return new TwoPlayersGame(nRow, nCols, p1, p2, cli);
    }
    public static Game createComputerVsComputerGameWithCli(int nRow, int nCols, Player p1, Player p2) {
        Cli cli = new Cli(nRow ,nCols, p1, p2);
        return new ComputerVsComputerGame(nRow, nCols, p1, p2, cli);
    }
    public static Game createPlayerVsComputerGameWithCli(int nRow, int nCols, Player p1, Player p2, Difficulty difficulty) {
        Cli cli = new Cli(nRow ,nCols, p1, p2);
        return new PlayerVsComputerGame(nRow, nCols, p1, p2, cli,difficulty);
    }
    public static Game createServerGameWithCli(int nRow, int nCols, Player p1, Player p2) {
        Cli cli = new Cli(nRow ,nCols, p1, p2);
        return new ServerGame(nRow, nCols, p1, p2, cli);
    }
    public static Game createClientGameWithCli(int nRow, int nCols, Player p1, Player p2, String ip) {
        Cli cli = new Cli(nRow ,nCols, p1, p2);
        return new ClientGame(nRow, nCols, p1, p2, cli, ip);
    }


}
