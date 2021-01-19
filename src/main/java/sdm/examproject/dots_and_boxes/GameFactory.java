package sdm.examproject.dots_and_boxes;

public class GameFactory {

    private GameFactory(){}

    //GUI Games
    public static Game create2PlayerGameWithGUI(int nRow, int nCols, Player p1, Player p2) {
        GuiGameManager guiGameManager = new GuiGameManager(nRow, nCols, p1, p2,"PvP Game - Local");
        return new TwoPlayersGame(nRow, nCols, p1, p2, guiGameManager);
    }

    public static Game createDemoGameWithGUI(int nRow, int nCols, Player p1, Player p2) {
        GuiGameManager guiGameManager = new GuiGameManager(nRow, nCols, p1, p2,"DEMO - Local");
        return new DemoGame(nRow, nCols, p1, p2, guiGameManager);
    }

    public static Game createPlayerVsComputerGameWithGUI(int nRow, int nCols, Player p1, Player p2, Difficulty difficulty) {
        GuiGameManager guiGameManager = new GuiGameManager(nRow, nCols, p1, p2,"PvComputer - Local");
        return new PlayerVsComputerGame(nRow, nCols, p1, p2, guiGameManager, difficulty);
    }

    public static Game createServerGameWithGUI(Player p1, Player p2) {
        GuiGameManager guiGameManager = new GuiGameManager(3, 3, p1, p2, "PvP Game - Host");
        return new ServerGame(3, 3, p1, p2, guiGameManager);
    }

    public static Game createClientGameWithGUI(Player p1, Player p2, String ip) {
        GuiGameManager guiGameManager = new GuiGameManager(3, 3, p1, p2, "PvP Game - Client");
        return new ClientGame(3, 3, p1, p2, guiGameManager, ip);
    }


    //CLI Games
    public static Game create2PlayerGameWithCli(int nRow, int nCols, Player p1, Player p2) {
        CliGameManager cliGameManager = new CliGameManager(nRow, nCols, p1, p2);
        return new TwoPlayersGame(nRow, nCols, p1, p2, cliGameManager);
    }

    public static Game createDemoGameWithCli(int nRow, int nCols, Player p1, Player p2) {
        CliGameManager cliGameManager = new CliGameManager(nRow, nCols, p1, p2);
        return new DemoGame(nRow, nCols, p1, p2, cliGameManager);
    }

    public static Game createPlayerVsComputerGameWithCli(int nRow, int nCols, Player p1, Player p2, Difficulty difficulty) {
        CliGameManager cliGameManager = new CliGameManager(nRow, nCols, p1, p2);
        return new PlayerVsComputerGame(nRow, nCols, p1, p2, cliGameManager, difficulty);
    }

    public static Game createServerGameWithCli(Player p1, Player p2) {
        CliGameManager cliGameManager = new CliGameManager(3, 3, p1, p2);
        return new ServerGame(3, 3, p1, p2, cliGameManager);
    }

    public static Game createClientGameWithCli(Player p1, Player p2, String ip) {
        CliGameManager cliGameManager = new CliGameManager(3, 3, p1, p2);
        return new ClientGame(3, 3, p1, p2, cliGameManager, ip);
    }


}
