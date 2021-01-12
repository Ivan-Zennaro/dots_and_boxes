public class TestingGame extends Game {

    public TestingGame(int nRows, int nCols, Player p1, Player p2, IOManager ioManager) {
        super(nRows, nCols, p1, p2, ioManager);
    }


    @Override
    public void startGame(){}

    public void computeMoveByString(String input){
        computeMove(Move.parseMove(input));

    }

    public int printCurrentPlayerScore(){
        return currentPlayer.getPoints();

    }

}
