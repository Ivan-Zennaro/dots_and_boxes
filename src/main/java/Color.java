public enum Color {
    RED,
    BLU,
    GREEN,
    PURPLE;

    public String getColoredString(String string) {
        return (
                switch (this) {
                    case BLU -> "\u001B[34m";
                    case RED -> "\u001B[31m";
                    case PURPLE -> "\u001B[35m";
                    case GREEN -> "\u001B[32m";
                    default -> "\u001B[0m";
                }
        ) + string + "\u001B[0m";
    }

    public java.awt.Color getAwtColor() {
        switch (this) {
            case BLU : return new java.awt.Color(59, 105, 177);
            case RED : return new java.awt.Color(244, 67, 54);
            case PURPLE : return new java.awt.Color(135, 23, 191);
            case GREEN : return new java.awt.Color(117, 181, 43);
        }
        return null;
    }

    public String getRGBstring() {
        java.awt.Color awtColor = this.getAwtColor();
        return "rgb(" + awtColor.getRed() + "," + awtColor.getGreen() + "," + awtColor.getBlue() + ")";
    }
}

