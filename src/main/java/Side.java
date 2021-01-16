public enum Side {

    UP,
    DOWN,
    LEFT,
    RIGHT,
    INVALID, List;

    public char asChar() {
        return name().charAt(0);
    }

}
