public class TestingGame extends Game{


    public int boardSize = 2;
    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }
    @Override
    public void startGame(){}

    @Override
    public Board initializeBoard() {

        board = new Board(boardSize,boardSize);
        cmd = new Cmd(boardSize,boardSize);
        return board;
    }


}
