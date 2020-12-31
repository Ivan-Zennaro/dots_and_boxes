public class Move {

    private int x;
    private int y;
    private Side side;

    public Move(int x, int y, Side side) {
        this.x = x;
        this.y = y;
        this.side = side;
    }

    //two matrix representation TMR
    public Move(int xTMR, int yTMR, boolean horizontal, int boxesInARow, int boxesInAColumn) {
        if (horizontal) {
            this.x = xTMR;
            this.y = yTMR;
            if (xTMR > boxesInARow) {
                this.side = Side.DOWN;
            } else {
                this.side = Side.UP;
            }
        } else {
            this.x = xTMR;
            this.y = yTMR;
            if (yTMR > boxesInAColumn) {
                this.side = Side.RIGHT;
            } else {
                this.side = Side.LEFT;
            }
        }
    }


    public static Move getInvalidMove() {
        return new Move(-1, -1, Side.INVALID);
    }

    public boolean isValid() {
        return this.side != Side.INVALID;
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

    public static Move parseMove(String input) {
        try {

            String[] splitInput = input.split(" ");
            if (splitInput.length != 3 && splitInput[2].length() != 1)
                throw new Exception();
            int x = Integer.parseInt(String.valueOf(splitInput[0]));
            int y = Integer.parseInt(String.valueOf(splitInput[1]));
            Side side;
            char sideAsChar = splitInput[2].charAt(0);
            for (Side refSide : Side.values()) {
                if (refSide.asChar() == sideAsChar)
                    return new Move(x, y, refSide);
            }
        } catch (Exception e) {
            return getInvalidMove();
        }
        return getInvalidMove();
    }

    public boolean isHorizontal() {
        return this.side == Side.UP || this.side == Side.DOWN;
    }

    public boolean isVertical() {
        return this.side == Side.LEFT || this.side == Side.RIGHT;
    }

    public int getXtwoMatrixRepresentation() {
        if (this.side == Side.DOWN) {
            return x + 1;
        } else
            return x;
    }

    public int getYtwoMatrixRepresentation() {
        if (this.side == Side.RIGHT) {
            return y + 1;
        } else
            return y;
    }


}