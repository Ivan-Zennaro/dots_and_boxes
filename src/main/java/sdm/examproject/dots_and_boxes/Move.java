package sdm.examproject.dots_and_boxes;

import java.io.Serializable;

public class Move implements Serializable {

    private int x;
    private int y;
    private Side side;

    public Move(int x, int y, Side side) {
        this.x = x;
        this.y = y;
        this.side = side;
    }

    public boolean isSideVertical(){
        return side == Side.RIGHT || side == Side.LEFT;
    }
    public boolean isSideHorizontal(){
        return side == Side.UP || side == Side.DOWN;
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

    public Move getNeighbourMove (){
        if (isSideHorizontal())
            return new Move(x + getCoordShift(), y, side.inverse);
        else
            return new Move( x, y + getCoordShift(), side.inverse);
    }


    private int getCoordShift() {
        return switch (this.side) {
            case LEFT,UP -> -1;
            case DOWN,RIGHT -> +1;
            default -> 0;
        };
    }



    public static Move parseMove(String input) {
        try {
            String[] splitInput = input.split(" ");

            if (splitInput.length == 3 && splitInput[2].length() == 1) {

                int x = Integer.parseInt(String.valueOf(splitInput[0]));
                int y = Integer.parseInt(String.valueOf(splitInput[1]));
                char sideAsChar = splitInput[2].charAt(0);

                for (Side refSide : Side.values()) {
                    if (refSide.asChar() == sideAsChar)
                        return new Move(x, y, refSide);
                }
            }
        } catch (NumberFormatException e) {
            return getInvalidMove();
        }
        return getInvalidMove();

    }
}