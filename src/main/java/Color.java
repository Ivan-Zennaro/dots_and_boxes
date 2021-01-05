public enum Color {
    RED,
    BLU,
    GREEN,
    PURPLE;

    public java.awt.Color getAwtColor(){
        if(this == RED) return new java.awt.Color(244, 67, 54);
        if(this == BLU) return new java.awt.Color(59, 105, 177);
        if(this == GREEN) return java.awt.Color.GREEN;
        if(this == PURPLE) return java.awt.Color.PINK;
        return null;
    }
}

