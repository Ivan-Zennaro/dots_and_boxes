public enum Side {

    UP,
    DOWN,
    LEFT,
    RIGHT,
    INVALID;

    private Side inverse;

    static {
        UP.inverse = DOWN;
        DOWN.inverse = UP;
        RIGHT.inverse = LEFT;
        LEFT.inverse = RIGHT;
    }

    public Side reverse() {
        return inverse;
    }

    public char asChar() {
        return name().charAt(0);
    }

}
