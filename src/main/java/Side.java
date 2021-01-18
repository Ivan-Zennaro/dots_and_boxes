public enum Side {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    INVALID;

    public char asChar() {
        return name().charAt(0);
    }
}
