
public class MainComputer {
    public static void main(String[] args) {
        /*PlayerVsComputerGame game =new PlayerVsComputerGame();
        game.startGame();
        game.endGame();*/

        Player p1 = new Player('A',Color.RED);
        Player p2 = new Player('B',Color.BLU);
        TwoPlayersNewGame game = new TwoPlayersNewGame(3,3,p1 ,p2,new Cli(3,3));
        game.startGame();
        game.endGame();
    }
}
