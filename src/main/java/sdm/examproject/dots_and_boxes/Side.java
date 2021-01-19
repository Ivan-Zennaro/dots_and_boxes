package sdm.examproject.dots_and_boxes;

public enum Side {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    INVALID;

    public Side inverse;

    static {
        UP.inverse = DOWN;
        DOWN.inverse = UP;
        LEFT.inverse = RIGHT;
        RIGHT.inverse = LEFT;
    }

    public char asChar() {
        return name().charAt(0);
    }
}
