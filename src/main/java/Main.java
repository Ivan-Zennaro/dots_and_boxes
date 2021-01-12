public class Main {

    public static void main(String[] args) {
        //consoleGame();
        guiGame();
    }

    public static void consoleGame(){
        Player p1 = new Player('A', Color.RED);
        Player p2 = new Player('B', Color.BLU);
        Cli cli = new Cli (3,3, p1, p2);
        TwoPlayersGame game =new TwoPlayersGame(3,3,p1,p2,cli);
        game.startGame();
    }

    public static void guiGame(){
        Player p1 = new Player('A', Color.RED);
        Player p2 = new Player('B', Color.BLU);


        Gui g = new Gui(3,3, p1, p2);
        TwoPlayersGame t = null;
        try {
            t = new TwoPlayersGame(3,3, p1, p2, Gui.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        t.startGame();
    }
}
