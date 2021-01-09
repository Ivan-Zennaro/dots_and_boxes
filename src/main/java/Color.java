public enum Color {
    RED,
    BLU,
    GREEN,
    PURPLE;

    public static String getColoredString(String string, Color color) {
        return (
                switch (color) {
                    case BLU -> "\u001B[34m";
                    case RED ->"\u001B[31m";
                    case PURPLE ->"\u001B[35m";
                    case GREEN ->"\u001B[32m";
                    default -> "\u001B[0m";
                }
        ) + string + "\u001B[0m";
    }

    public java.awt.Color getAwtColor(){
        if(this == RED) return new java.awt.Color(244, 67, 54);
        if(this == BLU) return new java.awt.Color(59, 105, 177);
        if(this == GREEN) return java.awt.Color.GREEN;
        if(this == PURPLE) return java.awt.Color.PINK;
        return null;
    }
}

