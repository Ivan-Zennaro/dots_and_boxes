public class Move {

    private int x;
    private int y;
    private Side side;

    public Move(int x, int y, Side side) {
        this.x = x;
        this.y = y;
        this.side = side;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Side getSide() {
        return side;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Move)) return false;
        Move move = (Move) o;

        return (this.getX() == move.getX() && this.getY() == move.getY() && this.getSide() == move.getSide());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.getX();
        result = 31 * result + this.getY();
        result = 31 * result + this.getSide().hashCode();
        return result;
    }

}