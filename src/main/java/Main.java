import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        //consoleGame();
        guiGame();
    }

    public static void consoleGame(){
        TwoPlayersGame game =new TwoPlayersGame();
        game.startGame();
    }

    public static void guiGame(){
        Player p1 = new Player('A', Color.RED);
        Player p2 = new Player('B', Color.BLU);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Gui g = new Gui(3,3, frame, p1, p2);
        TwoPlayersNewGame t = new TwoPlayersNewGame(3,3, p1, p2, g);
        t.startGame();
    }
}
