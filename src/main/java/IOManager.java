public abstract class IOManager{

    public abstract Move readMove ();
    public abstract void updateMove (Move move, Player p);
    public abstract void updateCompletedBox (int x, int y, Player p);
    public abstract void updateGameInfo (Player currentPlayer);
    public abstract void showWinner ();
    public abstract void errorHandler(String msg);

    public Player player1, player2;


    protected boolean backPress = false;
    public boolean getBackPress(){
        return backPress;
    }


    public static int getMappedY(Move move) {
        if (move.getSide() == Side.RIGHT)
            return move.getY() + 1;
        else return move.getY();
    }

    public static int getMappedX(Move move) {
        int tempX = move.getX() * 2 + 1;
        if (move.getSide() == Side.UP)
            return tempX - 1;
        if (move.getSide() == Side.DOWN)
            return tempX + 1;
        else return tempX;
    }

}
