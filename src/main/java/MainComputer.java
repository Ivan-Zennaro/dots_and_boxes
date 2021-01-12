
public class MainComputer {
    public static void main(String[] args) {
        Player p1 = new Player('A',Color.RED);
        Player p2 = new Player('B',Color.BLU);
        Game game = GameFactory.createPlayerVsComputerGameWithGUI(3,3,p1,p2,Difficulty.HARD);
        game.startGame();

    }
}
