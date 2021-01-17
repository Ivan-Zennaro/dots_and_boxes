public enum Side {

    UP,
    DOWN,
    LEFT,
    RIGHT,
    INVALID;

    public Side reverse() {
        return switch (this) {
            case UP -> Side.DOWN;
            case DOWN -> Side.UP;
            case LEFT -> Side.RIGHT;
            case RIGHT -> Side.LEFT;
            default -> Side.INVALID;
        };
    }

    public char asChar() {
        return name().charAt(0);
    }

}
