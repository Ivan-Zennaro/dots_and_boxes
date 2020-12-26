public enum Side {

    UP,
    DOWN,
    LEFT,
    RIGHT,
    INVALID;

    public char asChar(){return name().charAt(0);}
    public Side invert(){
        if(this == UP) return DOWN;
        if(this == DOWN) return UP;
        if(this == LEFT) return RIGHT;
        if(this == RIGHT) return LEFT;
        return this;
    }
    public int coordShift(){
        if(this == UP) return -1;
        if(this == DOWN) return +1;
        if(this == LEFT) return -1;
        if(this == RIGHT) return +1;
        return 0;
    }
}
